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
package eu.europa.ec.aqrsystem.action;

import eu.europa.ec.sourceapprotionment.DeleteSourceapportionmentException;
import eu.europa.ec.aqrsystem.xml.SourceXML;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.annotation.security.RolesAllowed;
import javax.xml.bind.JAXBException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.util.HtmlUtil;

/**
 * A base class for all the actions of the main source apportionment tab.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class ApportionmentActionBean extends BaseApportionmentActionBean {

    /**
     * Location of the tab view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/apportionment/tab.jsp";

    /**
     * The default action of the controller, which displays the tab.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution showTab() {
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * The action for creating a new source apportionement.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution create() throws Exception {
        source = sourceManager.createSourceapportionmentDraftForUser(email);
        context.getMessages().add(new LocalizableMessage("source.create.message", HtmlUtil.encode(source.getInspireidLocalid())));
        return new RedirectResolution(EditApportionmentActionBean.class).addParameter("sourceId", source.getUuid());
    }

    /**
     * Exporting the content of the source apportionment to an XML file.
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.UnsupportedEncodingException
     */
    public Resolution export() throws JAXBException, UnsupportedEncodingException, IOException {
        return XMLManager.export(SourceXML.class, new SourceXML().populate(getSource(), email), "Source_" + XMLManager.getFilename(getSource()));
    }

    /**
     * The action for importing a new plan
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution importData() throws JAXBException, Exception {
        XMLManager.importData(xmlFile, context, SourceXML.class, email, res);
        return new RedirectResolution(getClass());
    }

    /**
     * The action for deleting a source apportionment
     *
     * @return Default Stripes object.
     */
    @RolesAllowed({"user if ${source.editable}", "administrator if ${source.editable}"})
    public Resolution delete() {
        try {
            sourceManager.deleteSourceapportionmentByID(sourceId);
        } catch (DeleteSourceapportionmentException e) {
            return ajaxError("source.error.delete");
        }
        return ajaxSuccess();
    }

    /**
     * The action for cloning a source apportionment
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution cloneItem() throws Exception {
        sourceManager.cloneSourceapportionmentByPlanIDAndUser(sourceId, email);
        return ajaxSuccess();
    }

    /**
     * The location of the table content view.
     */
    private static final String TABLE_CONTENT = "WEB-INF/jsp/apportionment/sourcesTableContent.jsp";

    /**
     * An event handler that renders the table.
     *
     * @return The table view.
     */
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT);
    }
}
