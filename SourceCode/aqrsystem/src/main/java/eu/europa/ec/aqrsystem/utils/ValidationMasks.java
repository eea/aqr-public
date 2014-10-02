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

/**
 * A class to store validation masks used in the ActionBeans
 */
public class ValidationMasks {

    public static final String inspireId = "[A-Za-z_][A-Za-z0-9_\\-\\.]*";
    public static final String url = "((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)";
    public static final String date = "\\d{4}-\\d{2}-\\d{2}";
    public static final String year = "\\d{4}";
    public static final String number = "\\d+\\.?\\d*";
    public static final String integer = "\\d+";
    public static final String namespace = "(" + inspireId + ")|(" + url + ")";
    public static final String listOfInspiereIdTags = "(" + inspireId + ")(," + inspireId + ")*";
}
