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

import eu.europa.ec.common.publication.PublicationBean;
import eu.europa.ec.aqrsystem.utils.ValidationMasks;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * The controller for the publication dialog and table (from the edit plan
 * view).
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class PublicationActionBean extends BasePlanActionBean {

    /**
     * The function provides the content of the publication table.
     *
     * @return The view.
     */
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT_VIEW);
    }

    /**
     * The main edit publication form.
     *
     * @return A standard Stripes object.
     */
    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(DIALOG_VIEW);
    }

    /**
     * The action to save a publication.
     *
     * @return The error message or "Success"
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${plan.editable}", "administrator if ${plan.editable}"})
    public Resolution save() throws Exception {
        planManager.savePublicationToPlan(planId, publication);
        EditPlanActionBean.updateCompleteness(planManager.getPlanByID(planId, email), res);
        return ajaxSuccess();
    }

    /**
     * The action to delete the publication.
     *
     * @return The error message or "Success"
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${plan.editable}", "administrator if ${plan.editable}"})
    public Resolution delete() throws Exception {
        planManager.deletePublicationByPublicationID(publicationId);
        EditPlanActionBean.updateCompleteness(planManager.getPlanByID(planId, email), res);
        return ajaxSuccess();
    }

    /**
     * Location of the table content view.
     */
    private static final String TABLE_CONTENT_VIEW = "WEB-INF/jsp/plan/publicationTableContent.jsp";
    /**
     * Location of the dialog view.
     */
    private static final String DIALOG_VIEW = "WEB-INF/jsp/plan/publicationDialogContent.jsp";

    /**
     * Publication of the plan
     */
    protected List<PublicationBean> publications;

    /**
     * Get all publications of the plan.
     *
     * @return Get all publications of the plan.
     */
    public List<PublicationBean> getPublications() {
        if (planId != null) {
            publications = planManager.getAllPublicationByPlan(planId);
        }
        return publications;
    }

    /**
     * The currently selected publication's id.
     */
    protected String publicationId;

    /**
     * @param publicationId The new value.
     */
    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    /**
     * @return The currently selected publication's id.
     */
    public String getPublicationId() {
        return publicationId;
    }

    /**
     * The publication processed.
     */
    @ValidateNestedProperties({
        @Validate(field = "description", required = true, maxlength = 300, on = "save"),
        @Validate(field = "title", required = true, maxlength = 200, on = "save"),
        @Validate(field = "author", required = false, maxlength = 100, on = "save"),
        @Validate(field = "publicationdateTimeposition", required = true, maxlength = 4, mask = ValidationMasks.year, on = "save"),
        @Validate(field = "publisher", required = true, maxlength = 50, on = "save"),
        @Validate(field = "weblink", required = false, maxlength = 250, mask = ValidationMasks.url, on = "save")
    })
    protected PublicationBean publication;

    /**
     * Getter.
     *
     * @return The publication processed.
     */
    public PublicationBean getPublication() {
        if (publicationId != null && (publication == null || !publication.getUuid().equals(publicationId))) {
            publication = planManager.getPublicationByID(publicationId);
        }
        return publication;
    }

    /**
     * Setter.
     *
     * @param publication
     */
    public void setPublication(PublicationBean publication) {
        this.publication = publication;
    }
}
