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
package eu.europa.ec.aqrsystem.ext;

import eu.europa.ec.aqrsystem.model.Language;
import eu.europa.ec.aqrsystem.utils.Resources;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.localization.DefaultLocalePicker;

/**
 * The class for customising locale picking to support the language select box.
 */
public class CustomLocalePicker extends DefaultLocalePicker {

    /**
     * The name of a session variable in which the locale information is stored.
     */
    public static final String LOCALE = "locale";
    /**
     * The name of the localisation bundle files.
     */
    private static final String LOCALIZATION_FILE = "StripesResources";
    /**
     * A list of supported languages.
     */
    private static ArrayList<Language> availableLanguages;
    /**
     * The separator of the available languages list in the resource file.
     */
    private static final String AVAILABLE_LANGUAGE_SEPARATOR = "-";
    /**
     * The key in the resource file of the available languages list.
     */
    private static final String AVAILABLE_LANGUAGE_PROPERTY_KEY = "application.language.available";
    /**
     * The prefix of the available languages labels.
     */
    private static final String AVAILABLE_LANGUAGE_LABEL_PROPERTY_KEY = "application.language.label.";

    /**
     * This method picks the language for Stripes.
     *
     * @param request The Servlet request.
     * @return The locale.
     */
    @Override
    public Locale pickLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String locale = (String) session.getAttribute(LOCALE);
        if (locale != null) {
            return new Locale(locale);
        }

        Locale defVal = super.pickLocale(request);
        session.setAttribute(LOCALE, defVal.toString());

        return defVal;
    }

    /**
     * Setting the encoding to UTF-8.
     *
     * @param request The servlet request.
     * @param locale The locale of the user.
     * @return UTF-8
     */
    @Override
    public String pickCharacterEncoding(HttpServletRequest request, Locale locale) {
        return "UTF-8";
    }

    /**
     * Setting the locale for the system.
     *
     * @param request The servlet request.
     * @param localeCode The language code.
     */
    public void setLocale(HttpServletRequest request, String localeCode) {
        HttpSession session = request.getSession();

        if (localeCode != null && !localeCode.trim().equals("")) {
            session.setAttribute(LOCALE, localeCode);
        }
    }

    /**
     * Getting the currently selected locale code.
     *
     * @param request The servlet request.
     * @return The language.
     */
    public String getLocaleCode(HttpServletRequest request) {
        return pickLocale(request).getLanguage();
    }

    /**
     * Getting the resource bundle for the current locale.
     *
     * @param request Servlet request.
     * @return The resource bundle.
     */
    public ResourceBundle getLocalisationBundle(HttpServletRequest request) {
        return ResourceBundle.getBundle(LOCALIZATION_FILE, pickLocale(request));
    }

    /**
     * This method loads the list of all the official EU languages.
     *
     * @return A list of the supported languages
     * @throws java.lang.Exception
     */
    public static List<Language> getSupportedLanguages() throws Exception {
        if (availableLanguages != null) {
            return availableLanguages;
        }
        Properties props = Resources.getResourceBundle();
        String languagesString = props.getProperty(AVAILABLE_LANGUAGE_PROPERTY_KEY);
        if (languagesString == null || languagesString.trim().equals("")) {
            throw new Exception("The " + AVAILABLE_LANGUAGE_PROPERTY_KEY + " is not set in " + Resources.FILE);
        }

        String[] languages = languagesString.split(AVAILABLE_LANGUAGE_SEPARATOR);
        if (languages.length == 0) {
            throw new Exception("The " + AVAILABLE_LANGUAGE_PROPERTY_KEY + " in " + Resources.FILE + " does not define the supported languages.");
        }

        availableLanguages = new ArrayList<Language>();
        for (String localeCode : languages) {
            String localeLabel = props.getProperty(AVAILABLE_LANGUAGE_LABEL_PROPERTY_KEY + localeCode);
            Language localisation = new Language(localeCode, localeLabel);
            availableLanguages.add(localisation);
        }
        return availableLanguages;
    }
}
