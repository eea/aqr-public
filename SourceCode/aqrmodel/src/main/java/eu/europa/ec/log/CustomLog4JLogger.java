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

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class CustomLog4JLogger extends Logger
        implements LogClass {

    private static LoggerFactory myFactory = new CustomLog4JLogFactory();
    static String FQCN;

    public CustomLog4JLogger(String name) {
        super(name);
    }

    public static Category getInstance(String name) {
        return Logger.getLogger(name, myFactory);
    }

    public static Logger getLogger(String name) {
        return Logger.getLogger(name, myFactory);
    }

    static Class _mthclass$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static {
        FQCN = (CustomLog4JLogger.class).getName() + ".";
    }
}
