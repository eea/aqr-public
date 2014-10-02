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

import eu.europa.ec.plan.PlanINSPIRELocalIDAlreadyExistingException;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.aqrmodel.Statusplan;
import static eu.europa.ec.aqrsystem.action.BaseActionBean.planManager;
import eu.europa.ec.aqrsystem.utils.ValidationMasks;
import java.util.ArrayList;
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
 * The controller of the edit plan view.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class EditPlanActionBean extends BasePlanActionBean {

    /**
     * Location of the edit plan view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/plan/edit.jsp";

    /**
     * The default action of the controller, which shows details of a plan.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * Saving the modifications to the plan.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${plan.editable}", "administrator if ${plan.editable}"})
    public Resolution save() throws Exception {
        String newLocalId = plan.getInspireidLocalid();
        try {
            planManager.savePlanDraft(plan);
        } catch (PlanINSPIRELocalIDAlreadyExistingException e) {
            String oldLocalId = planManager.getPlanByID(plan.getUuid(), email).getInspireidLocalid();
            context.getValidationErrors().addGlobalError(new LocalizableError("plan.error.duplicatelocalid", HtmlUtil.encode(newLocalId), HtmlUtil.encode(oldLocalId)));
        }
        planId = plan.getUuid();
        List<String> nonCompletedFields = updateCompleteness(plan, res);
        context.getMessages().add(new LocalizableMessage("plan.update.message", HtmlUtil.encode(plan.getInspireidLocalid())));

        getMessageAboutIncompleteFields("plan", nonCompletedFields);
        return new RedirectResolution(PlanActionBean.class);
    }

    /**
     * Check if the plan is complete and update its status.
     *
     * @param plan The plan of consideration.
     * @param res The resources available.
     * @return A list of fields which have not been completed.
     * @throws Exception
     */
    public static List<String> updateCompleteness(PlanBean plan, ResourceBundle res) throws Exception {
        ArrayList<String> result = new ArrayList<String>();

        if (plan.getProviderBean() == null || !isDefined(plan.getProviderBean().getOrganisationname())) {
            result.add(res.getString("plan.providerBean.organisationname"));
        }
        if (plan.getProviderBean() == null || !isDefined(plan.getProviderBean().getWebsite())) {
            result.add(res.getString("plan.providerBean.website"));
        }
        if (plan.getProviderBean() == null || !isDefined(plan.getProviderBean().getIndividualname())) {
            result.add(res.getString("plan.providerBean.individualname"));
        }
        if (plan.getProviderBean() == null || !isDefined(plan.getProviderBean().getAddress())) {
            result.add(res.getString("plan.providerBean.address"));
        }
        if (plan.getProviderBean() == null || !isDefined(plan.getProviderBean().getTelephonevoice())) {
            result.add(res.getString("plan.providerBean.telephonevoice"));
        }
        if (plan.getProviderBean() == null || !isDefined(plan.getProviderBean().getElectronicmailaddress())) {
            result.add(res.getString("plan.providerBean.electronicmailaddress"));
        }

        if (plan.isChanges() && !isDefined(plan.getDescriptionofchanges())) {
            result.add(res.getString("plan.descriptionofchanges"));
        }

        if (!isDefined(plan.getReportingstartdate())) {
            result.add(res.getString("plan.reportingstartdate"));
        }
        if (!isDefined(plan.getReportingenddate())) {
            result.add(res.getString("plan.reportingenddate"));
        }

        if (!isDefined(plan.getCode())) {
            result.add(res.getString("plan.code"));
        }
        if (!isDefined(plan.getName())) {
            result.add(res.getString("plan.name"));
        }

        if (plan.getRelatedpartyBean() == null || !isDefined(plan.getRelatedpartyBean().getOrganisationname())) {
            result.add(res.getString("plan.relatedpartyBean.organisationname"));
        }
        if (plan.getRelatedpartyBean() == null || !isDefined(plan.getRelatedpartyBean().getWebsite())) {
            result.add(res.getString("plan.relatedpartyBean.website"));
        }
        if (plan.getRelatedpartyBean() == null || !isDefined(plan.getRelatedpartyBean().getIndividualname())) {
            result.add(res.getString("plan.relatedpartyBean.individualname"));
        }
        if (plan.getRelatedpartyBean() == null || !isDefined(plan.getRelatedpartyBean().getAddress())) {
            result.add(res.getString("plan.relatedpartyBean.address"));
        }
        if (plan.getRelatedpartyBean() == null || !isDefined(plan.getRelatedpartyBean().getTelephonevoice())) {
            result.add(res.getString("plan.relatedpartyBean.telephonevoice"));
        }
        if (plan.getRelatedpartyBean() == null || !isDefined(plan.getRelatedpartyBean().getElectronicmailaddress())) {
            result.add(res.getString("plan.relatedpartyBean.electronicmailaddress"));
        }

        if (!isDefined(plan.getFirstexceedanceyearTimeposition())) {
            result.add(res.getString("plan.firstexceedanceyearTimeposition"));
        }
        if (!isDefined(plan.getLink())) {
            result.add(res.getString("plan.link"));
        }

        if (!isDefined(planManager.getAllPollutantByPlan(plan.getUuid()))) {
            result.add(res.getString("plan.legend.pollutantcovered"));
        }

        if (!isDefined(plan.getTimetable())) {
            result.add(res.getString("plan.timetable"));
        }
        if (!isDefined(plan.getReferenceaqplan())) {
            result.add(res.getString("plan.referenceaqplan"));
        }
        if (!isDefined(plan.getReferenceimplementation())) {
            result.add(res.getString("plan.referenceimplementation"));
        }

        if (!isDefined(planManager.getAllPublicationByPlan(plan.getUuid()))) {
            result.add(res.getString("plan.legend.relevantpublication"));
        }

        if (!isDefined(plan.getAttainmentBeanList())) {
            result.add(res.getString("plan.attainmentBeanList"));
        }

        planManager.setCompletnessByPlanID(plan.getUuid(), result.isEmpty());
        return result;
    }

    /**
     * @return The current plan.
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

        @Validate(field = "code", required = false, maxlength = 200, on = "save"),
        @Validate(field = "name", required = false, maxlength = 200, on = "save"),

        @Validate(field = "relatedpartyBean.organisationname", required = false, maxlength = 200, on = "save"),
        @Validate(field = "relatedpartyBean.website", required = false, maxlength = 250, mask = ValidationMasks.url, on = "save"),
        @Validate(field = "relatedpartyBean.individualname", required = false, maxlength = 250, on = "save"),
        @Validate(field = "relatedpartyBean.address", required = false, maxlength = 200, on = "save"),
        @Validate(field = "relatedpartyBean.telephonevoice", required = false, maxlength = 50, on = "save"),
        @Validate(field = "relatedpartyBean.electronicmailaddress", required = false, maxlength = 250, converter = EmailTypeConverter.class, on = "save"),

        @Validate(field = "firstexceedanceyearTimeposition", required = false, maxlength = 4, mask = ValidationMasks.year, on = "save"),
        @Validate(field = "adoptiondateTimeposition", required = false, maxlength = 10, mask = ValidationMasks.date, on = "save"),
        @Validate(field = "timetable", required = false, maxlength = 300, on = "save"),
        @Validate(field = "referenceaqplan", required = false, maxlength = 300, mask = ValidationMasks.url, on = "save"),
        @Validate(field = "referenceimplementation", required = false, maxlength = 300, mask = ValidationMasks.url, on = "save"),
        @Validate(field = "comment", required = false, on = "save")
    })
    @Override
    public PlanBean getPlan() {
        return super.getPlan();
    }

    /**
     * The list of possible values for field plan.statusplan.
     */
    protected static List<Statusplan> possibleStatus;

    /**
     * @return The list of possible values for field plan.statusplan.
     * @throws java.lang.Exception
     */
    public List<Statusplan> getPossibleStatus() throws Exception {
        if (possibleStatus == null) {
            possibleStatus = planManager.getAllStatusPlan();
        }
        return possibleStatus;
    }

    @Override
    public List<AttainmentBean> getExistingAttainments() {
        return planManager.getAllAttainmentBeanByUser(planId);
    }
}
