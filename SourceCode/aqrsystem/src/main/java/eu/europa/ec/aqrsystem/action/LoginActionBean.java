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

import eu.europa.ec.aqrsystem.ext.ErrorMessageInterceptor;
import eu.europa.ec.aqrsystem.utils.Resources;
import java.io.IOException;
import java.net.URISyntaxException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * The controller for the action bean
 */
public class LoginActionBean extends BaseActionBean {

    /**
     * Location of the login view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/login/login.jsp";

    /**
     * The default action of the controller, which displays the login page.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution showLoginPage() {
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * Logging a user out.
     *
     * @return Default Stripes object.
     * @throws java.net.URISyntaxException
     * @throws java.io.IOException
     */
    public Resolution logout() throws URISyntaxException, IOException {
        String url = context.getRequest().getHeader("referer");
        String contextPath = context.getRequest().getContextPath();
        String homeUrl = "";
        if (contextPath != null) {
            int indexOfContextPath = url.indexOf(contextPath);
            int contextPathLength = contextPath.length();
            if (indexOfContextPath > 0 && contextPathLength > 0) {
                int homeUrlLength = indexOfContextPath + contextPathLength;
                homeUrl = url.substring(0, homeUrlLength) + "/";
            }
        }
        String logoutLink = Resources.getECASResourceBundle().getProperty("eu.cec.digit.ecas.client.filter.ecasBaseUrl") + "/cas/logout?url=" + homeUrl;
        context.getRequest().getSession(false).invalidate();
        ErrorMessageInterceptor.setLanguageNotSupportedMessage(false);
        return new RedirectResolution(logoutLink, false);
    }
}
