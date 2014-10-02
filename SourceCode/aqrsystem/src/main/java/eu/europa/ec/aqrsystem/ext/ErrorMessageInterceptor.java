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

import eu.europa.ec.aqrsystem.utils.BaseUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.FlashScope;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.apache.log4j.Logger;

/**
 * An interceptor assuring that global error messages are passed on redirect.
 */
@Intercepts(LifecycleStage.EventHandling)
public class ErrorMessageInterceptor implements Interceptor {

    /**
     * The logger
     */
    private static final Logger log = BaseUtils.getLog();

    /**
     * The name of the flash scope variable with the maximum size of a file that
     * can be uploaded. Set only when there is an upload error.
     */
    public static final String MAXIMUM_SIZE = "maximumSize";

    /**
     * The name of the flash scope variable with the posted size of a file that
     * can be uploaded. Set only when there is an upload error.
     */
    public static final String POSTED_SIZE = "postedSize";

    /**
     * The name of the flash scope variable with the global errors.
     */
    private static final String GLOBAL_ERRORS = "BACKED_UP_GLOBAL_ERRORS";

    /**
     * An error about lack of support for a language
     */
    private static final LocalizableError languageNotSupportedError = new LocalizableError("common.language.notsupported");
    /**
     * A flag whether to show or hide the error message
     */
    private static boolean showLanguageNotSupportedError = false;

    /**
     * A method called whenever there is a server call. It is used to manage
     * global errors.
     *
     * @param ctx Execution Context
     * @return Standard Stripes Resolution object
     * @throws Exception
     */
    @Override
    public Resolution intercept(ExecutionContext ctx) throws Exception {
        HttpServletRequest req = ctx.getActionBeanContext().getRequest();
        ValidationErrors errors = ctx.getActionBeanContext().getValidationErrors();

        List<ValidationError> globalErrors = getGlobalErrors(req);
        if (globalErrors != null) {
            for (ValidationError globalError : globalErrors) {
                if (!globalError.equals(languageNotSupportedError)) {
                    errors.addGlobalError(globalError);
                }
            }
            clearGlobalErrorsVariable(req);
        }
        handleFileUploadErrors(req, errors);
        if (showLanguageNotSupportedError) {
            errors.addGlobalError(languageNotSupportedError);
        }

        //execute the event
        Resolution resolution = ctx.proceed();

        req = ctx.getActionBeanContext().getRequest();
        errors = ctx.getActionBeanContext().getValidationErrors();
        globalErrors = errors.get(ValidationErrors.GLOBAL_ERROR);
        if (globalErrors != null && globalErrors.size() > 0 && resolution instanceof RedirectResolution) {
            for (ValidationError globalError : globalErrors) {
                addGlobalError(req, globalError);
            }
        }

        return resolution;
    }

    /**
     * Adding a global error
     *
     * @param req HTTP servlet request
     * @param err An error message
     */
    public static void addGlobalError(HttpServletRequest req, ValidationError err) {
        FlashScope scope = FlashScope.getCurrent(req, true);
        List<ValidationError> list = (List<ValidationError>) scope.get(GLOBAL_ERRORS);
        if (list == null) {
            list = new ArrayList<ValidationError>();
        }
        list.add(err);
        scope.put(GLOBAL_ERRORS, list);
    }

    /**
     * Getting the global errors stored in the flash variable.
     *
     * @param req HTTP servlet request
     * @return A list of errors
     */
    protected List<ValidationError> getGlobalErrors(HttpServletRequest req) {
        return (List<ValidationError>) req.getAttribute(GLOBAL_ERRORS);
    }

    /**
     * Clear the flash variable with the global errors
     *
     * @param req HTTP servlet request
     */
    protected void clearGlobalErrorsVariable(HttpServletRequest req) {
        req.removeAttribute(GLOBAL_ERRORS);
    }

    /**
     * Add an file upload error message if upload parameters present
     *
     * @param req HTTP servlet request
     * @param errors An object with global errors.
     */
    protected void handleFileUploadErrors(HttpServletRequest req, ValidationErrors errors) {
        String maximumSize = req.getParameter(MAXIMUM_SIZE);
        String postedSize = req.getParameter(POSTED_SIZE);
        if (maximumSize != null) {
            errors.addGlobalError(new LocalizableError("xmlFile.error.maximumSize", postedSize, maximumSize));
        }
    }

    /**
     * The API to show/hide the message about the lack of support for the
     * language.
     *
     * @param p
     */
    public static void setLanguageNotSupportedMessage(boolean p) {
        showLanguageNotSupportedError = p;
    }
}
