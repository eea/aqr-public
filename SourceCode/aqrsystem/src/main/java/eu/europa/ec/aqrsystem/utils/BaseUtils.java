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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * The base class from which all the others can inherit.
 */
public class BaseUtils {

    /**
     * The logger to be used in all the classes.
     */
    protected static final Logger log = Logger.getLogger(BaseUtils.class);

    /**
     * Checking if an attribute is defined.
     *
     * @param field The attribute.
     * @return true/false
     */
    public static boolean isDefined(String field) {
        return field != null && !field.equals("");
    }

    /**
     * Checking if the list is not empty.
     *
     * @param list The list.
     * @return true/false
     */
    public static boolean isDefined(List<?> list) {
        return list != null && list.size() > 0;
    }

    /**
     * Checking if the BigInteger is set.
     *
     * @param no The number.
     * @return true/false
     */
    public static boolean isDefined(BigInteger no) {
        return no != null;
    }

    /**
     * Getting the logger
     *
     * @return Logger
     */
    public static Logger getLog() {
        return log;
    }

    /**
     * Logging a debug message
     *
     * @param message The message
     */
    public static void logDebug(String message) {
        log.debug(message);
    }

    /**
     * Logging a warn message
     *
     * @param message The message
     */
    public static void logWarn(String message) {
        log.warn(message);
    }

    /**
     * Logging an error message
     *
     * @param message The message
     */
    public static void logError(String message) {
        log.error(message);
    }

    /**
     * Converting a string with a list of tags separated with commas to a list
     * of strings
     *
     * @param p
     * @return
     */
    public static List<String> stringToListOfTags(String p) {
        return Arrays.asList(p.split(","));
    }
}
