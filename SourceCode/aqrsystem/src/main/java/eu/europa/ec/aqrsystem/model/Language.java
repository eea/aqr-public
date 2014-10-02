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
package eu.europa.ec.aqrsystem.model;

import eu.europa.ec.aqrsystem.utils.BaseUtils;

/**
 * The class represents a language supported by the application
 */
public class Language extends BaseUtils {

    /**
     * A code of a language.
     */
    private String localeCode;
    /**
     * A label of a language.
     */
    private String localeLabel;

    /**
     * A constructor
     *
     * @param localeCode The code of a language.
     * @param localeLabel The label of a language.
     */
    public Language(String localeCode, String localeLabel) {
        this.localeCode = localeCode;
        this.localeLabel = localeLabel;
    }

    /**
     * Getter
     *
     * @return Code of a language.
     */
    public String getLocaleCode() {
        return localeCode;
    }

    /**
     * Setter.
     *
     * @param localeCode Code of a language.
     */
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    /**
     * Getter.
     *
     * @return Label of a language.
     */
    public String getLocaleLabel() {
        return localeLabel;
    }

    /**
     * Setter.
     *
     * @param localeLabel The label of a language.
     */
    public void setLocaleLabel(String localeLabel) {
        this.localeLabel = localeLabel;
    }
}
