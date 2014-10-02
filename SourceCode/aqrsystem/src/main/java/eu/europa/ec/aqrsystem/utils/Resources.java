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
package eu.europa.ec.aqrsystem.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * A class to manage the resources in StripesResources.properties.
 */
public class Resources extends BaseUtils {

    public static final String FILE = "StripesResources.properties";
    public static final String ECAS_FILE = "ecas-config.properties";

    /**
     * This method gives access to the properties stored in
     * StripesResources.properties.
     *
     * @return A resource bundle.
     * @throws java.net.URISyntaxException
     * @throws java.io.FileNotFoundException
     */
    public static Properties getResourceBundle() throws URISyntaxException, FileNotFoundException, IOException {
        return getProperties(FILE);
    }

    /**
     * This method gives access to the properties stored in
     * ecas-config.properties.
     *
     * @return A resource bundle.
     * @throws java.net.URISyntaxException
     * @throws java.io.FileNotFoundException
     */
    public static Properties getECASResourceBundle() throws URISyntaxException, FileNotFoundException, IOException {
        return getProperties(ECAS_FILE);
    }

    /**
     * Get a resource bundle
     *
     * @param path Name of the file
     * @return
     * @throws URISyntaxException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static Properties getProperties(String path) throws URISyntaxException, FileNotFoundException, IOException {
        Properties props = new Properties(System.getProperties());
        File propertiesFile = new File(Resources.class.getClassLoader().getResource(path).toURI());
        if (!propertiesFile.exists()) {
            throw new FileNotFoundException("Properties file not found: " + propertiesFile.getAbsolutePath());
        }
        InputStream inStream = new FileInputStream(propertiesFile);
        props.load(inStream);
        return props;
    }
}
