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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.FileUploadLimitExceededException;
import net.sourceforge.stripes.exception.ActionBeanNotFoundException;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;

/**
 * A class that handles all exceptions in the system
 */
public class CustomExceptionHandler extends DefaultExceptionHandler {

    /**
     * The view for errors.
     */
    private static final String ERROR_VIEW = "/WEB-INF/jsp/error/error.jsp";

    /**
     * A handler for all errors that do not have a dedicated method.
     *
     * @param exc Exception that is to be handled.
     * @param req The servlet request object.
     * @param resp The servlet response object.
     * @return The standard Stripes object.
     */
    public Resolution catchAll(Throwable exc, HttpServletRequest req, HttpServletResponse resp) {
        BaseUtils.logError(exc.getMessage());
        exc.printStackTrace();
        return new ForwardResolution(ERROR_VIEW);
    }

    /**
     * A handler for the page not found error.
     *
     * @param exc Exception that is to be handled.
     * @param req The servlet request object.
     * @param resp The servlet response object.
     * @return
     */
    public Resolution catchActionBeanNotFound(ActionBeanNotFoundException exc, HttpServletRequest req, HttpServletResponse resp) {
        return new ErrorResolution(HttpServletResponse.SC_NOT_FOUND);
    }

    /**
     * Catching file upload errors and redirecting to the calling tab.
     *
     * @param exc Exception
     * @param req HTTP request
     * @param resp HTTP response
     * @return Resolution
     */
    public Resolution catchAttachmentsTooBig(FileUploadLimitExceededException exc, HttpServletRequest req, HttpServletResponse resp) {
        String currentURL = req.getHeader("referer");
        int paramPos = currentURL.indexOf('?');
        if (paramPos != -1) {
            currentURL = currentURL.substring(0, paramPos);
        }
        return new RedirectResolution(currentURL, false)
                .addParameter(ErrorMessageInterceptor.MAXIMUM_SIZE, exc.getMaximum())
                .addParameter(ErrorMessageInterceptor.POSTED_SIZE, exc.getPosted());
    }
}
