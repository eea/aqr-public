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

import eu.europa.ec.sourceapprotionment.adjustmentsource.AdjustmentsourceBean;
import eu.europa.ec.sourceapprotionment.adjustmenttype.AdjustmenttypeBean;
import eu.europa.ec.sourceapprotionment.areaclassification.AreaclassificationBean;
import eu.europa.ec.sourceapprotionment.reasonvalue.ReasonvalueBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentINSPIRELocalIDAlreadyExistingException;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
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
 * The controller of the edit source apportionment view.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class EditApportionmentActionBean extends BaseApportionmentActionBean {

    /**
     * Location of the edit source apportionment view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/apportionment/edit.jsp";

    /**
     * The default action of the controller, which shows details of a source
     * apportionment.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * Saving the modifications to the source apportionment.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${source.editable}", "administrator if ${source.editable}"})
    public Resolution save() throws Exception {
        String newLocalId = source.getInspireidLocalid();
        try {
            sourceManager.saveSourceapportionmentDraft(source);
        } catch (SourceapportionmentINSPIRELocalIDAlreadyExistingException e) {
            String oldLocalId = sourceManager.getSourceapportionmentByID(source.getUuid(), email).getInspireidLocalid();
            context.getValidationErrors().addGlobalError(new LocalizableError("source.error.duplicatelocalid", HtmlUtil.encode(newLocalId), HtmlUtil.encode(oldLocalId)));
        }

        sourceId = source.getUuid();

        addGlobalInformationError();

        List<String> nonCompletedFields = updateCompleteness(source, res);
        context.getMessages().add(new LocalizableMessage("source.update.message", HtmlUtil.encode(source.getInspireidLocalid())));

        getMessageAboutIncompleteFields("source", nonCompletedFields);
        return new RedirectResolution(ApportionmentActionBean.class);
    }

    /**
     * Check if the source apportionment is complete and update its status.
     *
     * @param source The source apportionment of consideration.
     * @param res The resources available.
     * @return A list of fields which have not been completed.
     * @throws Exception
     */
    public static List<String> updateCompleteness(SourceapportionmentBean source, ResourceBundle res) throws Exception {
        ArrayList<String> result = new ArrayList<String>();

        if (source.getProviderBean() == null || !isDefined(source.getProviderBean().getOrganisationname())) {
            result.add(res.getString("source.providerBean.organisationname"));
        }
        if (source.getProviderBean() == null || !isDefined(source.getProviderBean().getWebsite())) {
            result.add(res.getString("source.providerBean.website"));
        }
        if (source.getProviderBean() == null || !isDefined(source.getProviderBean().getIndividualname())) {
            result.add(res.getString("source.providerBean.individualname"));
        }
        if (source.getProviderBean() == null || !isDefined(source.getProviderBean().getAddress())) {
            result.add(res.getString("source.providerBean.address"));
        }
        if (source.getProviderBean() == null || !isDefined(source.getProviderBean().getTelephonevoice())) {
            result.add(res.getString("source.providerBean.telephonevoice"));
        }
        if (source.getProviderBean() == null || !isDefined(source.getProviderBean().getElectronicmailaddress())) {
            result.add(res.getString("source.providerBean.electronicmailaddress"));
        }

        if (!isDefined(source.getDescriptionofchanges())) {
            result.add(res.getString("source.descriptionofchanges"));
        }

        if (!isDefined(source.getReportingstartdate())) {
            result.add(res.getString("source.reportingstartdate"));
        }
        if (!isDefined(source.getReportingenddate())) {
            result.add(res.getString("source.reportingenddate"));
        }

        if (source.getRegionalbackgroundBean() == null || (!isDefined(source.getRegionalbackgroundBean().getTotal()) && !source.getRegionalbackgroundBean().isTotal_nil())) {
            result.add(res.getString("source.regionalbackgroundBean.label.total"));
        }
        if (source.getRegionalbackgroundBean() == null || (!isDefined(source.getRegionalbackgroundBean().getFromwithinms()) && !source.getRegionalbackgroundBean().isFromwithinms_nil())) {
            result.add(res.getString("source.regionalbackgroundBean.label.fromwithinms"));
        }
        if (source.getRegionalbackgroundBean() == null || (!isDefined(source.getRegionalbackgroundBean().getTransboundary()) && !source.getRegionalbackgroundBean().isTransboundary_nil())) {
            result.add(res.getString("source.regionalbackgroundBean.label.transboundary"));
        }
        if (source.getRegionalbackgroundBean() == null || (!isDefined(source.getRegionalbackgroundBean().getNaturalregionalbackground()) && !source.getRegionalbackgroundBean().isNaturalregionalbackground_nil())) {
            result.add(res.getString("source.regionalbackgroundBean.label.naturalregionalbackground"));
        }
        if (source.getRegionalbackgroundBean() == null || (!isDefined(source.getRegionalbackgroundBean().getOther()) && !source.getRegionalbackgroundBean().isOther_nil())) {
            result.add(res.getString("source.regionalbackgroundBean.label.other"));
        }

        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getTotal()) && !source.getUrbanbackgroundBean().isTotal_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.total"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getTraffic()) && !source.getUrbanbackgroundBean().isTraffic_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.traffic"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getHeatandpowerproduction()) && !source.getUrbanbackgroundBean().isHeatandpowerproduction_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.heatandpowerproduction"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getAgriculture()) && !source.getUrbanbackgroundBean().isAgriculture_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.agriculture"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getCommercialandresidential()) && !source.getUrbanbackgroundBean().isCommercialandresidential_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.commercialandresidential"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getShipping()) && !source.getUrbanbackgroundBean().isShipping_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.shipping"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getOffroadmobilemachinery()) && !source.getUrbanbackgroundBean().isOffroadmobilemachinery_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.offroadmobilemachinery"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getNaturalurbanbackground()) && !source.getUrbanbackgroundBean().isNaturalurbanbackground_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.naturalurbanbackground"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getTransboundary()) && !source.getUrbanbackgroundBean().isTransboundary_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.transboundary"));
        }
        if (source.getUrbanbackgroundBean() == null || (!isDefined(source.getUrbanbackgroundBean().getOther()) && !source.getUrbanbackgroundBean().isOther_nil())) {
            result.add(res.getString("source.urbanbackgroundBean.label.other"));
        }

        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getTotal()) && !source.getLocalincrementBean().isTotal_nil())) {
            result.add(res.getString("source.localincrementBean.label.total"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getTraffic()) && !source.getLocalincrementBean().isTraffic_nil())) {
            result.add(res.getString("source.localincrementBean.label.traffic"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getHeatandpowerproduction()) && !source.getLocalincrementBean().isHeatandpowerproduction_nil())) {
            result.add(res.getString("source.localincrementBean.label.heatandpowerproduction"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getAgriculture()) && !source.getLocalincrementBean().isAgriculture_nil())) {
            result.add(res.getString("source.localincrementBean.label.agriculture"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getCommercialandresidential()) && !source.getLocalincrementBean().isCommercialandresidential_nil())) {
            result.add(res.getString("source.localincrementBean.label.commercialandresidential"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getShipping()) && !source.getLocalincrementBean().isShipping_nil())) {
            result.add(res.getString("source.localincrementBean.label.shipping"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getOffroadmobilemachinery()) && !source.getLocalincrementBean().isOffroadmobilemachinery_nil())) {
            result.add(res.getString("source.localincrementBean.label.offroadmobilemachinery"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getNaturallocalincrement()) && !source.getLocalincrementBean().isNaturallocalincrement_nil())) {
            result.add(res.getString("source.localincrementBean.label.naturallocalincrement"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getTransboundary()) && !source.getLocalincrementBean().isTransboundary_nil())) {
            result.add(res.getString("source.localincrementBean.label.transboundary"));
        }
        if (source.getLocalincrementBean() == null || (!isDefined(source.getLocalincrementBean().getOther()) && !source.getLocalincrementBean().isOther_nil())) {
            result.add(res.getString("source.localincrementBean.label.other"));
        }

        if (source.getExceedancedescriptionBean() == null || source.getExceedancedescriptionBean().getExceedanceareaBean() == null || !isDefined(source.getExceedancedescriptionBean().getExceedanceareaBean().getAreaclassificationList_uri())) {
            result.add(res.getString("source.exceedancedescriptionBean.exceedanceareaBean.areaclassificationList_uri"));
        }

        if (source.getPlanBean() == null) {
            result.add(res.getString("source.planBean.uuid"));
        }
        if (source.getAttainmentBean() == null) {
            result.add(res.getString("source.attainmentBean.uuid"));
        }

        sourceManager.setCompletnessBySourceapportionmentID(source.getUuid(), result.isEmpty());
        return result;
    }

    /**
     * @return The current source apportionment.
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
        @Validate(field = "referenceyearTimeperiod", required = false, maxlength = 4, mask = ValidationMasks.year, on = "save"),
        @Validate(field = "regionalbackgroundBean.total", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "regionalbackgroundBean.totalcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "regionalbackgroundBean.fromwithinms", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "regionalbackgroundBean.fromwithinmscomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "regionalbackgroundBean.transboundary", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "regionalbackgroundBean.transboundarycomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "regionalbackgroundBean.naturalregionalbackground", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "regionalbackgroundBean.naturalregionalbackgroundcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "regionalbackgroundBean.other", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "regionalbackgroundBean.othercomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.total", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.totalcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.traffic", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.trafficcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.heatandpowerproduction", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.heatandpowerproductioncomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.agriculture", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.agriculturecomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.commercialandresidential", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.commercialandresidentialcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.shipping", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.shippingcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.offroadmobilemachinery", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.offroadmobilemachinerycomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.naturalurbanbackground", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.naturalurbanbackgroundcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.transboundary", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.transboundarycomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "urbanbackgroundBean.other", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "urbanbackgroundBean.othercomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.total", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.totalcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.traffic", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.trafficcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.heatandpowerproduction", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.heatandpowerproductioncomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.agriculture", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.agriculturecomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.commercialandresidential", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.commercialandresidentialcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.shipping", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.shippingcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.offroadmobilemachinery", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.offroadmobilemachinerycomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.naturalurbanbackground", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.naturalurbanbackgroundcomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.transboundary", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.transboundarycomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "localincrementBean.other", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "localincrementBean.othercomment", required = false, maxlength = 500, on = "save"),
        @Validate(field = "exceedancedescriptionBean.numericalexceedance", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "exceedancedescriptionBean.numberexceedances", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedanceareaBean.areaestimate", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedanceareaBean.roadlenghtestimate", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedanceareaBean.stationused", required = false, mask = ValidationMasks.listOfInspiereIdTags, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedanceareaBean.modelused", required = false, mask = ValidationMasks.listOfInspiereIdTags, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedenceexposureBean.exposedpopulation", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedenceexposureBean.exposedarea", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedenceexposureBean.sensitiveresidentpopulation", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedenceexposureBean.relevantinfrastructure", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "exceedancedescriptionBean.exceedenceexposureBean.referenceyear", required = false, maxlength = 4, mask = ValidationMasks.year, on = "save"),
        @Validate(field = "exceedancedescriptionBean.otherreason", required = false, maxlength = 250, on = "save"),
        @Validate(field = "exceedancedescriptionBean.comment", required = false, on = "save"),
        @Validate(field = "comment", required = false, on = "save")
    })
    @Override
    public SourceapportionmentBean getSource() {
        return super.getSource();
    }
    /**
     * The list of possible values for field exceedancedescriptionBean.
     * deductionassessmentmethodBean.adjustmenttypeBean.
     */
    protected static List<AdjustmenttypeBean> possibleAdjustmentTypes;

    /**
     * @return The list of possible values for field exceedancedescriptionBean.
     * deductionassessmentmethodBean.adjustmenttypeBean.
     */
    public List<AdjustmenttypeBean> getPossibleAdjustmentTypes() {
        if (possibleAdjustmentTypes == null) {
            possibleAdjustmentTypes = sourceManager.getAllAdjustmentType();
        }
        return possibleAdjustmentTypes;
    }
    /**
     * The list of possible values for field exceedancedescriptionBean.
     * deductionassessmentmethodBean.adjustmentsourceBeans.
     */
    protected static List<AdjustmentsourceBean> possibleAdjustmentSources;

    /**
     * @return The list of possible values for field exceedancedescriptionBean.
     * deductionassessmentmethodBean.adjustmentsourceBeans.
     */
    public List<AdjustmentsourceBean> getPossibleAdjustmentSources() {
        if (possibleAdjustmentSources == null) {
            possibleAdjustmentSources = sourceManager.getAllAdjustmentSource();
        }
        return possibleAdjustmentSources;
    }
    /**
     * The list of possible values for field exceedancedescriptionBean.
     * exceedanceareaBean.areaclassificationBeanList.
     */
    protected static List<AreaclassificationBean> possibleClassifications;

    /**
     * @return The list of possible values for field exceedancedescriptionBean.
     * exceedanceareaBean.areaclassificationBeanList.
     */
    public List<AreaclassificationBean> getPossibleClassifications() {
        if (possibleClassifications == null) {
            possibleClassifications = sourceManager.getAllAreaclassification();
        }
        return possibleClassifications;
    }
    /**
     * The list of possible values for field exceedancedescriptionBean.
     * reasonvalueBeanList.
     */
    protected static List<ReasonvalueBean> possibleReasons;

    /**
     * @return The list of possible values for field exceedancedescriptionBean.
     * reasonvalueBeanList.
     */
    public List<ReasonvalueBean> getPossibleReasons() {
        if (possibleReasons == null) {
            possibleReasons = sourceManager.getAllExceedancereason();
        }
        return possibleReasons;
    }

    @Override
    public List<AttainmentBean> getExistingAttainments() {
        return sourceManager.getAllAttainmentBeanByUser(sourceId);
    }

    private void addGlobalInformationError() throws NumberFormatException {
        /**
         * reporting date
         */
        String reportingDateStart = source.getReportingstartdate();
        String reportingDateEnd = source.getReportingenddate();
        if (reportingDateStart != null && reportingDateEnd != null) {
            String[] startArray = reportingDateStart.split("-");
            String[] endArray = reportingDateEnd.split("-");

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date startDate = new Date(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date endDate = new Date(Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));

            if (startDate.after(endDate)) {
                context.getValidationErrors().addGlobalError(new LocalizableError("apportionment.error.reportingdatestart.after.reportingdateend", HtmlUtil.encode(reportingDateStart), HtmlUtil.encode(reportingDateEnd)));
            }
        }

        /**
         * RegionalbackgroundBean
         */
        double partialTotalRegional = 0;
        if (source.getRegionalbackgroundBean().getFromwithinms() != null) {
            double fromwithinms = Double.parseDouble(source.getRegionalbackgroundBean().getFromwithinms());
            partialTotalRegional += fromwithinms;
        }
        if (source.getRegionalbackgroundBean().getTransboundary() != null) {
            double transboundary = Double.parseDouble(source.getRegionalbackgroundBean().getTransboundary());
            partialTotalRegional += transboundary;
        }
        if (source.getRegionalbackgroundBean().getNaturalregionalbackground() != null) {
            double natural = Double.parseDouble(source.getRegionalbackgroundBean().getNaturalregionalbackground());
            partialTotalRegional += natural;
        }
        if (source.getRegionalbackgroundBean().getOther() != null) {
            double other = Double.parseDouble(source.getRegionalbackgroundBean().getOther());
            partialTotalRegional += other;
        }

        if (source.getRegionalbackgroundBean().getTotal() != null) {
            double totalRegional = Double.parseDouble(source.getRegionalbackgroundBean().getTotal());
            if (totalRegional != partialTotalRegional) {
                context.getValidationErrors().addGlobalError(new LocalizableError("plan.regionalbackground.total.differ", HtmlUtil.encode(source.getLocalincrementBean().getTotal()), HtmlUtil.encode(String.valueOf(partialTotalRegional))));
            }
        } else if (partialTotalRegional > 0) {
            context.getValidationErrors().addGlobalError(new LocalizableError("plan.regionalbackground.total.differ", HtmlUtil.encode(source.getLocalincrementBean().getTotal()), HtmlUtil.encode(String.valueOf(partialTotalRegional))));
        }

        /**
         * UrbanbackgroundBean
         */
        double partialUrbanTotal = 0;

        if (source.getUrbanbackgroundBean().getTraffic() != null) {
            double traffic = Double.parseDouble(source.getUrbanbackgroundBean().getTraffic());
            partialUrbanTotal += traffic;
        }
        if (source.getUrbanbackgroundBean().getHeatandpowerproduction() != null) {
            double industry = Double.parseDouble(source.getUrbanbackgroundBean().getHeatandpowerproduction());
            partialUrbanTotal += industry;
        }
        if (source.getUrbanbackgroundBean().getAgriculture() != null) {
            double agriculture = Double.parseDouble(source.getUrbanbackgroundBean().getAgriculture());
            partialUrbanTotal += agriculture;
        }
        if (source.getUrbanbackgroundBean().getCommercialandresidential() != null) {
            double commercialAndResidual = Double.parseDouble(source.getUrbanbackgroundBean().getCommercialandresidential());
            partialUrbanTotal += commercialAndResidual;
        }
        if (source.getUrbanbackgroundBean().getShipping() != null) {
            double shipping = Double.parseDouble(source.getUrbanbackgroundBean().getShipping());
            partialUrbanTotal += shipping;
        }
        if (source.getUrbanbackgroundBean().getOffroadmobilemachinery() != null) {
            double offRoad = Double.parseDouble(source.getUrbanbackgroundBean().getOffroadmobilemachinery());
            partialUrbanTotal += offRoad;
        }
        if (source.getUrbanbackgroundBean().getNaturalurbanbackground() != null) {
            double natural = Double.parseDouble(source.getUrbanbackgroundBean().getNaturalurbanbackground());
            partialUrbanTotal += natural;
        }
        if (source.getUrbanbackgroundBean().getTransboundary() != null) {
            double transboundary = Double.parseDouble(source.getUrbanbackgroundBean().getTransboundary());
            partialUrbanTotal += transboundary;
        }
        if (source.getUrbanbackgroundBean().getOther() != null) {
            double other = Double.parseDouble(source.getUrbanbackgroundBean().getOther());
            partialUrbanTotal += other;
        }

        if (source.getUrbanbackgroundBean().getTotal() != null) {
            double totalUrban = Double.parseDouble(source.getUrbanbackgroundBean().getTotal());
            if (totalUrban != partialUrbanTotal) {
                context.getValidationErrors().addGlobalError(new LocalizableError("plan.urbanbackground.total.differ", HtmlUtil.encode(source.getUrbanbackgroundBean().getTotal()), HtmlUtil.encode(String.valueOf(partialUrbanTotal))));
            }
        } else if (partialUrbanTotal > 0) {
            context.getValidationErrors().addGlobalError(new LocalizableError("plan.urbanbackground.total.differ", HtmlUtil.encode(source.getUrbanbackgroundBean().getTotal()), HtmlUtil.encode(String.valueOf(partialUrbanTotal))));
        }

        /**
         * LocalincrementBean
         */
        double partialTotalLocal = 0;

        if (source.getLocalincrementBean().getTraffic() != null) {
            double traffic = Double.parseDouble(source.getLocalincrementBean().getTraffic());
            partialTotalLocal += traffic;
        }
        if (source.getLocalincrementBean().getHeatandpowerproduction() != null) {
            double industry = Double.parseDouble(source.getLocalincrementBean().getHeatandpowerproduction());
            partialTotalLocal += industry;
        }
        if (source.getLocalincrementBean().getAgriculture() != null) {
            double agriculture = Double.parseDouble(source.getLocalincrementBean().getAgriculture());
            partialTotalLocal += agriculture;
        }
        if (source.getLocalincrementBean().getCommercialandresidential() != null) {
            double commercialAndResidual = Double.parseDouble(source.getLocalincrementBean().getCommercialandresidential());
            partialTotalLocal += commercialAndResidual;
        }
        if (source.getLocalincrementBean().getShipping() != null) {
            double shipping = Double.parseDouble(source.getLocalincrementBean().getShipping());
            partialTotalLocal += shipping;
        }
        if (source.getLocalincrementBean().getOffroadmobilemachinery() != null) {
            double offRoad = Double.parseDouble(source.getLocalincrementBean().getOffroadmobilemachinery());
            partialTotalLocal += offRoad;
        }
        if (source.getLocalincrementBean().getNaturallocalincrement() != null) {
            double natural = Double.parseDouble(source.getLocalincrementBean().getNaturallocalincrement());
            partialTotalLocal += natural;
        }
        if (source.getLocalincrementBean().getTransboundary() != null) {
            double transboundary = Double.parseDouble(source.getLocalincrementBean().getTransboundary());
            partialTotalLocal += transboundary;
        }
        if (source.getLocalincrementBean().getOther() != null) {
            double other = Double.parseDouble(source.getLocalincrementBean().getOther());
            partialTotalLocal += other;
        }

        if (source.getLocalincrementBean().getTotal() != null) {
            double totalLocal = Double.parseDouble(source.getLocalincrementBean().getTotal());
            if (totalLocal != partialTotalLocal) {
                context.getValidationErrors().addGlobalError(new LocalizableError("plan.localincrement.total.differ", HtmlUtil.encode(source.getLocalincrementBean().getTotal()), HtmlUtil.encode(String.valueOf(partialTotalLocal))));
            }
        } else if (partialTotalLocal > 0) {
            context.getValidationErrors().addGlobalError(new LocalizableError("plan.localincrement.total.differ", HtmlUtil.encode(source.getLocalincrementBean().getTotal()), HtmlUtil.encode(String.valueOf(partialTotalLocal))));
        }

    }
}
