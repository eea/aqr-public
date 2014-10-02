/*
 *  Copyright (c) 2010-2014 EUROPEAN UNION
 *  Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 *  the European Commission - subsequent versions of the EUPL (the "Licence");
 *  You may not use this work except in compliance with the Licence.
 *  You may obtain a copy of the Licence at: 
 *  http://ec.europa.eu/idabc/eupl
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the Licence is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the Licence for the specific language governing permissions and
 *  limitations under the Licence.
 * 
 *  Date: __/__/____
 *  Authors: European Commission, Joint Research Centre
 *  Lucasz Cyra, Emanuela Epure, Daniele Francioli
 *  aq-dev@jrc.ec.europa.eu
 */
package eu.europa.ec.aqrsystem.xml;

import eu.europa.ec.common.HeaderInterface;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.utils.Resources;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.LocalizableError;
import org.xml.sax.SAXException;

/**
 * An object to provide the XML functionalities
 */
public class XMLManager {

    /**
     * Exporting an object to XML
     *
     * @param exportedClass The class to be exported, e.g. PlanXML.class
     * @param exportedObject The object to be exported
     * @param fileName The name of the file returned
     * @return StreamingResolution
     * @throws JAXBException
     * @throws IOException
     */
    public static StreamingResolution export(Class exportedClass, Object exportedObject, String fileName) throws JAXBException, IOException {
        Marshaller marshaller = JAXBContext.newInstance(exportedClass).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, Namespaces.aqd + " " + Namespaces.aqdSchemaLocation);

        ByteArrayOutputStream xml = new ByteArrayOutputStream();
        xml.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes());
        marshaller.marshal(exportedObject, xml);

        return new StreamingResolution("application/octet-stream", xml.toString("UTF-8")).setFilename(fileName);
    }

    /**
     * Getting the filename of the exported file for a plan
     *
     * @param p PlanBean
     * @return filename
     */
    public static String getFilename(HeaderInterface p) {
        return removeProblematicCharacters(p.getInspireidNamespace() + '_' + p.getInspireidLocalid() + '_' + p.getInspireidVersionid()) + ".xml";
    }

    /**
     * Replacing all characters which are not a latter or a number with the
     * underscore.
     *
     * @param value to be converted
     * @return Result
     */
    public static String removeProblematicCharacters(String value) {
        String result = "";
        for (char c : value.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                result += c;
            } else {
                result += '_';
            }
        }
        return result;
    }

    /**
     * Location of the schema file
     */
    private static final String SCHEMA_FILE = "AirQualityReporting.xsd";

    /**
     * Validating the uploaded xml file.
     *
     * @param context The ActionBeanContext to report errors
     * @param xmlFile The file to be validated
     * @return true/false
     * @throws java.net.URISyntaxException
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public static boolean xmlIsValid(ActionBeanContext context, FileBean xmlFile) throws URISyntaxException {
        Source schemaFile = new StreamSource(new File(Resources.class.getClassLoader().getResource(SCHEMA_FILE).toURI()));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        try {
            schema = schemaFactory.newSchema(schemaFile);
        } catch (SAXException e) {
            context.getValidationErrors().addGlobalError(new LocalizableError("xmlFile.error.wrongschema"));
            e.printStackTrace();
            return false;
        }
        Validator validator = schema.newValidator();

        Source xmlFileSource;
        try {
            xmlFileSource = new StreamSource(xmlFile.getInputStream());
        } catch (IOException e) {
            context.getValidationErrors().addGlobalError(new LocalizableError("xmlFile.error.ioerror"));
            e.printStackTrace();
            return false;
        }

        try {
            validator.validate(xmlFileSource);
        } catch (IOException e) {
            context.getValidationErrors().addGlobalError(new LocalizableError("xmlFile.error.ioerror"));
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            BaseUtils.logDebug("Validation error: " + e.getLocalizedMessage());
            context.getValidationErrors().addGlobalError(new LocalizableError("xmlFile.error.notvalid", e.getLocalizedMessage()));
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * The action for importing a file to the system
     *
     * @param xmlFile
     * @param context
     * @param exportedClass
     * @param userEmail
     * @param res
     * @throws javax.xml.bind.JAXBException
     */
    public static void importData(FileBean xmlFile, ActionBeanContext context, Class exportedClass, String userEmail, ResourceBundle res) throws JAXBException, Exception {
        if (xmlFile == null) {
            context.getValidationErrors().addGlobalError(new LocalizableError("xmlFile.error.nofile"));
        } else if (xmlIsValid(context, xmlFile)) {
            try {
                JAXBContext jc = JAXBContext.newInstance(exportedClass);
                Unmarshaller u = jc.createUnmarshaller();
                XMLSaveableObject xml = (XMLSaveableObject) u.unmarshal(xmlFile.getInputStream());
                xml.save(userEmail, context, res);
            } catch (IOException e) {
                context.getValidationErrors().addGlobalError(new LocalizableError("xmlFile.error.nofile"));
            }
        }
    }

    /**
     * Converting the Href, i.e. namespace + localId to localId
     *
     * @param href
     * @return
     */
    public static String convertHrefToLocalId(String href) {
        int index = href.lastIndexOf("/") + 1;
        if (index == -1) {
            return null;
        }
        return href.substring(index);
    }

    /**
     * Converting List<ReferenceType> to List<String>
     *
     * @param list
     * @return
     */
    public static List<String> convertListOfReferenceTypeToListOfString(List<ReferenceType> list) {
        List<String> result = new ArrayList();
        for (ReferenceType p : list) {
            if (BaseUtils.isDefined(p.getHref())) {
                result.add(p.getHref());
            }
        }
        return result;
    }
}
