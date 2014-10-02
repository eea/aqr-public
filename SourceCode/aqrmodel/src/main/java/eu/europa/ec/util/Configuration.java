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
package eu.europa.ec.util;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class Configuration {

    private static Configuration instance;
    private Properties props;
    private boolean fileReady;

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    private Configuration() {
        fileReady = true;
        try {
            props = new Properties(System.getProperties());
            File propertiesFile = new File(this.getClass().getClassLoader().getResource("StripesResources.properties").toURI());
            if (!propertiesFile.exists()) {
                fileReady = false;
                throw new FileNotFoundException("Properties file not found: " + propertiesFile.getAbsolutePath());
            }
            InputStream inStream = new FileInputStream(propertiesFile);
            props.load(inStream);
            inStream.close();
        } catch (Exception e) {
            System.err.println("ERROR READING CONFIGURATION: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isFileReady() {
        return fileReady;
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public int getProperty(String key, int defaultValue) {
        String value = props.getProperty(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        } else {
            return Integer.parseInt(value);
        }
    }

    public long getProperty(String key, long defaultValue) {
        String value = props.getProperty(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        } else {
            return Long.parseLong(value);
        }
    }

    public boolean getProperty(String key, boolean defaultValue) {
        String value = props.getProperty(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        } else {
            return Boolean.valueOf(value).booleanValue();
        }
    }

    public int getServerId() {
        return getProperty("server", 0);
    }

    public Properties getProperties() {
        return props;
    }

    public Properties getProperties(String prefix) {
        Properties subProps = new Properties();
        Enumeration<Object> enumeration = props.keys();
        do {
            if (!enumeration.hasMoreElements()) {
                break;
            }
            String key = (String) enumeration.nextElement();
            if (key.startsWith(prefix)) {
                subProps.setProperty(key, props.getProperty(key));
            }
        } while (true);
        return subProps;
    }
}
