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

import eu.europa.ec.evaluationscenario.EvaluationscenarioINSPIRELocalIDAlreadyExistingException;
import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.aqrsystem.utils.ValidationMasks;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.security.RolesAllowed;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * The controller of the edit evaluation view.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class EditEvaluationActionBean extends BaseEvaluationActionBean {

    /**
     * Location of the edit scenario view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/evaluation/edit.jsp";

    /**
     * The default action of the controller, which shows details of a scenario.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * Saving the modifications to the scenario.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${evaluation.editable}", "administrator if ${evaluation.editable}"})
    public Resolution save() throws Exception {
        String newLocalId = evaluation.getInspireidLocalid();
        try {
            evaluationManager.saveEvaluationScenarioDraft(evaluation, email);
        } catch (EvaluationscenarioINSPIRELocalIDAlreadyExistingException e) {
            String oldLocalId = evaluationManager.getEvaluationscenarioByID(evaluation.getUuid(), email).getInspireidLocalid();
            context.getValidationErrors().addGlobalError(new LocalizableError("evaluation.error.duplicatelocalid", HtmlUtil.encode(newLocalId), HtmlUtil.encode(oldLocalId)));
        }
        addGlobalInformationError();

        List<String> nonCompletedFields = updateCompleteness(evaluation, res);
        context.getMessages().add(new LocalizableMessage("evaluation.update.message", HtmlUtil.encode(evaluation.getInspireidLocalid())));
        getMessageAboutIncompleteFields("evaluation", nonCompletedFields);
        return new RedirectResolution(EvaluationActionBean.class);
    }

    /**
     * Check if the scenario is complete and update its status.
     *
     * @param evaluation The scenario of consideration.
     * @param res The resources available.
     * @return A list of fields which have not been completed.
     * @throws Exception
     */
    public static List<String> updateCompleteness(EvaluationscenarioBean evaluation, ResourceBundle res) throws Exception {
        ArrayList<String> result = new ArrayList<String>();

        if (evaluation.getProviderBean() == null || !isDefined(evaluation.getProviderBean().getOrganisationname())) {
            result.add(res.getString("evaluation.providerBean.organisationname"));
        }
        if (evaluation.getProviderBean() == null || !isDefined(evaluation.getProviderBean().getWebsite())) {
            result.add(res.getString("evaluation.providerBean.website"));
        }
        if (evaluation.getProviderBean() == null || !isDefined(evaluation.getProviderBean().getIndividualname())) {
            result.add(res.getString("evaluation.providerBean.individualname"));
        }
        if (evaluation.getProviderBean() == null || !isDefined(evaluation.getProviderBean().getAddress())) {
            result.add(res.getString("evaluation.providerBean.address"));
        }
        if (evaluation.getProviderBean() == null || !isDefined(evaluation.getProviderBean().getTelephonevoice())) {
            result.add(res.getString("evaluation.providerBean.telephonevoice"));
        }
        if (evaluation.getProviderBean() == null || !isDefined(evaluation.getProviderBean().getElectronicmailaddress())) {
            result.add(res.getString("evaluation.providerBean.electronicmailaddress"));
        }

        if (!isDefined(evaluation.getDescriptionofchanges())) {
            result.add(res.getString("evaluation.descriptionofchanges"));
        }

        if (!isDefined(evaluation.getReportingstartdate())) {
            result.add(res.getString("evaluation.reportingstartdate"));
        }
        if (!isDefined(evaluation.getReportingenddate())) {
            result.add(res.getString("evaluation.reportingenddate"));
        }

        if (!isDefined(evaluation.getCodeofscenario())) {
            result.add(res.getString("evaluation.codeofscenario"));
        }

        if (!isDefined(evaluationManager.getAllPublicationBeanByEvaluationscenario(evaluation.getUuid()))) {
            result.add(res.getString("evaluation.legend.relevantpublication"));
        }

        if (!isDefined(evaluation.getAttainmentyearPeriodtime())) {
            result.add(res.getString("evaluation.attainmentyearPeriodtime"));
        }
        if (!isDefined(evaluation.getStartyearPeriodtime())) {
            result.add(res.getString("evaluation.startyearPeriodtime"));
        }

        if (evaluation.getBaselinescenario() == null || !isDefined(evaluation.getBaselinescenario().getDescription())) {
            result.add(res.getString("evaluation.baselinescenario.description"));
        }
        if (evaluation.getBaselinescenario() == null || !isDefined(evaluation.getBaselinescenario().getTotalemissions())) {
            result.add(res.getString("evaluation.baselinescenario.totalemissions"));
        }
        if (evaluation.getBaselinescenario() == null || !isDefined(evaluation.getBaselinescenario().getMeasuresUuid())) {
            result.add(res.getString("evaluation.baselinescenario.measuresUuid"));
        }

        if (evaluation.getProjectionscenario() == null || !isDefined(evaluation.getProjectionscenario().getDescription())) {
            result.add(res.getString("evaluation.projectionscenario.description"));
        }
        if (evaluation.getProjectionscenario() == null || !isDefined(evaluation.getProjectionscenario().getTotalemissions())) {
            result.add(res.getString("evaluation.projectionscenario.totalemissions"));
        }
        if (evaluation.getProjectionscenario() == null || !isDefined(evaluation.getProjectionscenario().getMeasuresUuid())) {
            result.add(res.getString("evaluation.projectionscenario.measuresUuid"));
        }

        evaluationManager.setCompletnessByEvaluationScenarioID(evaluation.getUuid(), result.isEmpty());
        return result;
    }

    /**
     * Getter.
     *
     * @return The current scenario.
     */
    @ValidateNestedProperties({
        @Validate(field = "inspireidLocalid", required = true, maxlength = 100, mask = ValidationMasks.inspireId, on = "save"),
        @Validate(field = "providerBean.organisationname", required = false, maxlength = 200, on = "save"),
        @Validate(field = "providerBean.website", required = false, maxlength = 250, mask = ValidationMasks.url, on = "save"),
        @Validate(field = "providerBean.individualname", required = false, maxlength = 250, on = "save"),
        @Validate(field = "providerBean.address", required = false, maxlength = 200, on = "save"),
        @Validate(field = "providerBean.telephonevoice", required = false, maxlength = 50, on = "save"),
        @Validate(field = "providerBean.electronicmailaddress", required = false, maxlength = 250, converter = EmailTypeConverter.class, on = "save"),
        @Validate(field = "descriptionofchanges", required = false, maxlength = 250, on = "save"),
        @Validate(field = "reportingstartdate", required = false, maxlength = 10, mask = ValidationMasks.date, on = "save"),
        @Validate(field = "reportingenddate", required = false, maxlength = 10, mask = ValidationMasks.date, on = "save"),
        @Validate(field = "codeOfScenario", required = false, maxlength = 200, on = "save"),
        @Validate(field = "attainmentyearPeriodtime", required = false, maxlength = 4, mask = ValidationMasks.year, on = "save"),
        @Validate(field = "startyearPeriodtime", required = false, maxlength = 4, mask = ValidationMasks.year, on = "save"),
        @Validate(field = "baselinescenario.description", required = false, maxlength = 1000, on = "save"),
        @Validate(field = "baselinescenario.totalemissions", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "baselinescenario.expectedconcentration", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "baselinescenario.expectedexceedence", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "baselinescenario.comment", required = false, on = "save"),
        @Validate(field = "projectionscenario.description", required = false, maxlength = 1000, on = "save"),
        @Validate(field = "projectionscenario.totalemissions", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "projectionscenario.expectedconcentration", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "projectionscenario.expectedexceedence", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "projectionscenario.comment", required = false, on = "save")
    })
    @Override
    public EvaluationscenarioBean getEvaluation() {
        return super.getEvaluation();
    }

    private void addGlobalInformationError() throws NumberFormatException {
        /**
         * reporting date
         */
        String reportingDateStart = evaluation.getReportingstartdate();
        String reportingDateEnd = evaluation.getReportingenddate();
        if (reportingDateStart != null && reportingDateEnd != null) {
            String[] startArray = reportingDateStart.split("-");
            String[] endArray = reportingDateEnd.split("-");

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date startDate = new Date(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date endDate = new Date(Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));

            if (startDate.after(endDate)) {
                context.getValidationErrors().addGlobalError(new LocalizableError("evaluation.error.reportingdatestart.after.reportingdateend", HtmlUtil.encode(reportingDateStart), HtmlUtil.encode(reportingDateEnd)));
            }
        }


    }
}
