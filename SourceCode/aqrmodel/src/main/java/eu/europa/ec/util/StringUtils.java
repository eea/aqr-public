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

import java.security.MessageDigest;

public class StringUtils {

    /**
     * Converts a string to a MD5 hash
     *
     * @param in the string to be converted
     * @param objectType the type of the object
     * @return out the in string converted in MD5 hash
     * @throws Exception
     */
    public static String getMD5(String in, Class objectType) throws Exception {
        String out = null;
        in = objectType.getName() + "-" + in;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(in.getBytes());

            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            out = sb.toString();

        } catch (Exception e) {
            throw new Exception("@@ Error while calculating MD5 digest for string: " + in + " - " + e.getMessage());
        }

        return out;
    }

    /**
     * Normalizes the string given as input: removes any spaces and convert it
     * in lowercase;
     *
     * @param in the string to be converted
     * @return out the in string normalized
     * @throws Exception
     */
    public static String normalize(String in) {
        return (in != null) ? in.trim().replace(" ", "") : null;
    }

    /**
     * Converts a string to a UUID: converts the string in lowercase and removes
     * any spaces; finally converts it to MD5
     *
     * @param in the string to be converted
     * @return out the in string converted as UUID
     * @throws Exception
     */
    public static String createUUID(String in, Class objectType) throws Exception {
        return (in != null) ? getMD5(normalize(in), objectType) : null;
    }

    /**
     * Converts a string to a code
     *
     * @param in the string to be converted
     * @param replaceRegex the tegular expression to find the character to
     * replace
     * @return out the in string with no space and specialchars replaced
     * @throws Exception
     */
    public static String generateCode(String in, String replaceRegex) throws Exception {
        String out = null;
        try {
            out = in.replaceAll(replaceRegex, "");
        } catch (Exception e) {
            throw new Exception("@@ Error while generating code for string: " + in + " - " + e.getMessage());
        }

        return out;
    }
}
