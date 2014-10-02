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
package eu.europa.ec.log;

import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LogManager {

    private static LogManager instance = new LogManager();

    private LogManager() {
    }

    public static synchronized LogManager getInstance() {
        return instance;
    }

    public static LogClass getLogClass(String categoryName) {
        return (CustomLog4JLogger) CustomLog4JLogger.getLogger(categoryName);
    }

    public static Logger getRootLogger() {
        return CustomLog4JLogger.getRootLogger();
    }

    public static synchronized void config(String confBaseUrl) {
        DOMConfigurator.configure(confBaseUrl);
    }

    public static synchronized void config(URL confBaseUrl) {
        DOMConfigurator.configure(confBaseUrl);
    }
}
