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

import eu.cec.digit.ecas.client.jaas.DetailedUser;
import eu.europa.ec.user.UserBean;
import eu.europa.ec.user.UserManager;
import eu.europa.ec.aqrsystem.action.LoginActionBean;
import eu.europa.ec.aqrsystem.ext.ErrorMessageInterceptor;
import java.lang.reflect.Method;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.LocalizableError;
import org.apache.log4j.Logger;
import org.stripesstuff.plugin.security.InstanceBasedSecurityManager;
import org.stripesstuff.plugin.security.SecurityHandler;

/**
 * The security manager of the application.
 */
public class CustomSecurityManager extends InstanceBasedSecurityManager implements SecurityHandler {

    /**
     * The logger
     */
    private static final Logger log = BaseUtils.getLog();

    /**
     * The object to communicate with the DB.
     */
    private static final UserManager userManager = new UserManager();

    /**
     * Checking if the user is authenticated
     *
     * @param actionBean The action bean of the server call.
     * @param handler
     * @return true/false
     */
    @Override
    protected Boolean isUserAuthenticated(ActionBean actionBean, Method handler) {
        return getEmail(actionBean.getContext()) != null;
    }

    /**
     * Deciding whether the user has the right to access the resource
     *
     * @param actionBean of the request
     * @param handler
     * @param role required in the annotation
     * @return true/false
     */
    @Override
    protected Boolean hasRoleName(ActionBean actionBean, Method handler, String role) {
        //Roles used: superuser, administrator, user
//        String userRole = userManager.getUserByEmail(getEmail(actionBean.getContext())).getUserroleBean().getRolename();
        UserBean user = userManager.getUserByEmail(getEmail(actionBean.getContext()));
        if (user == null) {
            return false;
        }
        return role.equals(user.getUserroleBean().getRolename()) && user.isEnable();
    }

    /**
     * Getting the email address of the currently logged in user
     *
     * @param context of the server call
     * @return The email address
     */
    public static String getEmail(ActionBeanContext context) {
        DetailedUser detailedUser = (DetailedUser) context.getRequest().getUserPrincipal();
        if (detailedUser == null) {
            return null;
        }
        return detailedUser.getEmail();
    }

    /**
     * Handling unauthorised access attempt.
     *
     * @param bean The action bean called.
     * @param handler
     * @return Standard Resolution object.
     */
    @Override
    public Resolution handleAccessDenied(ActionBean bean, Method handler) {
        ErrorMessageInterceptor.addGlobalError(bean.getContext().getRequest(), new LocalizableError("login.error.unauthoriseduser", getEmail(bean.getContext())));
        return new RedirectResolution(LoginActionBean.class);
    }
}
