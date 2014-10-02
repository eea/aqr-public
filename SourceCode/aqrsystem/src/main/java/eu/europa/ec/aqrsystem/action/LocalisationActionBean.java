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

import eu.europa.ec.aqrsystem.ext.CustomLocalePicker;
import eu.europa.ec.aqrsystem.ext.ErrorMessageInterceptor;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * This is the ActionBean for the localisation form present in each of the
 * pages.
 */
public class LocalisationActionBean extends BaseActionBean {

    /**
     * Changing the language. The default event handler.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution changeLanguage() {
        CustomLocalePicker lp = new CustomLocalePicker();
        lp.setLocale(context.getRequest(), currentLanguage);
        res = lp.getLocalisationBundle(context.getRequest());
        if (currentLanguage != null && res != null) {
            ErrorMessageInterceptor.setLanguageNotSupportedMessage(!currentLanguage.equals("en") && res.getString("common.langsupported").equals("no"));
        }

        String currentURL = context.getRequest().getHeader("referer");
        if (currentURL == null) {
            return new RedirectResolution(LoginActionBean.class);
        }
        return new RedirectResolution(currentURL, false);
    }

    /**
     * Location of the select language partial JSP.
     */
    private static final String LANGUAGE_SELECT = "WEB-INF/jsp/common/language_select.jsp";

    /**
     * Generating the language select.
     *
     * @return The standard Stripes Resolution.
     */
    public Resolution showSelect() {
        return new ForwardResolution(LANGUAGE_SELECT);
    }

    /**
     * The localisation manager of the system.
     */
    protected CustomLocalePicker lp = new CustomLocalePicker();

    /**
     * The currently selected locale.
     */
    protected String currentLanguage;

    /**
     * Getter
     *
     * @return The currently selected locale.
     */
    public String getCurrentLanguage() {
        return lp.getLocaleCode(context.getRequest());
    }

    /**
     * Setter.
     *
     * @param currentLanguage The currently selected locale.
     */
    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    /**
     * Getter.
     *
     * @return All the languages selectable.
     * @throws java.lang.Exception
     */
    public List getAvailableLanguages() throws Exception {
        return CustomLocalePicker.getSupportedLanguages();
    }
}
