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

import eu.europa.ec.sourceapprotionment.assesmentmethods.AssesmentmethodsBean;
import eu.europa.ec.sourceapprotionment.assesmenttype.AssesmenttypeBean;
import eu.europa.ec.aqrsystem.utils.ValidationMasks;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * The controller for the assessment dialog and table.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class AssessmentActionBean extends BaseApportionmentActionBean {

    /**
     * Location of the table content view.
     */
    private static final String TABLE_CONTENT_VIEW = "WEB-INF/jsp/apportionment/assessmentTableContent.jsp";

    /**
     * The function provides the content of the assessment table.
     *
     * @return The view.
     */
    @DefaultHandler
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT_VIEW);
    }

    /**
     * Location of the dialog view.
     */
    private static final String DIALOG_VIEW = "WEB-INF/jsp/apportionment/assessmentDialogContent.jsp";

    /**
     * The main edit assessment form.
     *
     * @return A standard Stripes object.
     */
    public Resolution form() {
        return new ForwardResolution(DIALOG_VIEW);
    }

    /**
     * The action to save the assessment.
     *
     * @return The error message or "Success"
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${source.editable}", "administrator if ${source.editable}"})
    public Resolution save() throws Exception {
        sourceManager.saveAssesmentmethodsToSourceapportionmentID(sourceId, getAssessment());
        EditApportionmentActionBean.updateCompleteness(sourceManager.getSourceapportionmentByID(sourceId, email), res);
        return ajaxSuccess();
    }

    /**
     * The action to delete the assessment.
     *
     * @return The error message or "Success"
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${source.editable}", "administrator if ${source.editable}"})
    public Resolution delete() throws Exception {
        sourceManager.deleteAssesmentmethodsByAssesmentmethodsID(assessmentId);
        EditApportionmentActionBean.updateCompleteness(sourceManager.getSourceapportionmentByID(sourceId, email), res);
        return ajaxSuccess();
    }

    /**
     * The currently selected assessment's id.
     */
    protected String assessmentId;

    /**
     * @param assessmentId The new value.
     */
    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    /**
     * @return The currently selected assessment's id.
     */
    public String getAssessmentId() {
        return assessmentId;
    }

    /**
     * The assessment processed.
     */
    @ValidateNestedProperties({
        @Validate(field = "assesmenttypedescription", required = true, maxlength = 300, on = "save"),
        @Validate(field = "metadata", required = false, mask = ValidationMasks.listOfInspiereIdTags, on = "save")
    })
    protected AssesmentmethodsBean assessment;

    /**
     * @return The assessment processed.
     */
    public AssesmentmethodsBean getAssessment() {
        if (assessmentId != null && (assessment == null || !assessment.getUuid().equals(assessmentId))) {
            assessment = sourceManager.getAssesmentmethodsByAssesmentmethodsID(assessmentId);
        }
        return assessment;
    }

    /**
     * Setter.
     *
     * @param assessment
     */
    public void setAssessment(AssesmentmethodsBean assessment) {
        this.assessment = assessment;
    }

    /**
     * Assessments of the source apportionment
     */
    protected List<AssesmentmethodsBean> assessments;

    /**
     * Get all assessments of the source apportionment.
     *
     * @return Get all pollutants of the plan.
     */
    public List<AssesmentmethodsBean> getAssessments() {
        if (sourceId != null) {
            assessments = sourceManager.getAllAssesmentmethodsBySourceapportionmentID(sourceId);
        }
        return assessments;
    }

    /**
     * The list of possible values for field source.exceedancedescriptionBean.
     * deductionassessmentmethodBean.assesmenttypeBean.
     */
    protected static List<AssesmenttypeBean> possibleAssessmentTypes;

    /**
     * @return The list of possible values for field source.
     * exceedancedescriptionBean.deductionassessmentmethodBean.assesmenttypeBean
     */
    public List<AssesmenttypeBean> getPossibleAssessmentTypes() {
        if (possibleAssessmentTypes == null) {
            possibleAssessmentTypes = sourceManager.getAllAssesmenttype();
        }
        return possibleAssessmentTypes;
    }
}
