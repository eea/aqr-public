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

import eu.europa.ec.measures.administrativelevel.AdministrativelevelBean;
import eu.europa.ec.measures.classification.ClassificationBean;
import eu.europa.ec.measures.measuretype.MeasuretypeBean;
import eu.europa.ec.measures.quantificationnumerical.QuantificationnumericalBean;
import eu.europa.ec.measures.sourcesectors.SourcesectorsBean;
import eu.europa.ec.measures.spatialscale.SpatialscaleBean;
import eu.europa.ec.measures.specificationofhours.SpecificationofhoursBean;
import eu.europa.ec.measures.statusplannedimplementation.StatusplannedimplementationBean;
import eu.europa.ec.measures.timescale.TimescaleBean;
import eu.europa.ec.measures.MeasuresINSPIRELocalIDAlreadyExistingException;
import eu.europa.ec.measures.MeasuresBean;
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
 * The controller of the edit measure view.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class EditMeasureActionBean extends BaseMeasureActionBean {

    /**
     * Location of the edit measure view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/measure/edit.jsp";

    /**
     * The default action of the controller, which shows details of a measure.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * Saving the modifications to the measure.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${measure.editable}", "administrator if ${measure.editable}"})
    public Resolution save() throws Exception {
        String newLocalId = measure.getInspireidLocalid();
        try {
            measuresManager.saveMeasuresDraft(measure);
        } catch (MeasuresINSPIRELocalIDAlreadyExistingException e) {
            String oldLocalId = measuresManager.getMeasureByID(measure.getUuid(), email).getInspireidLocalid();
            context.getValidationErrors().addGlobalError(new LocalizableError("measure.error.duplicatelocalid", HtmlUtil.encode(newLocalId), HtmlUtil.encode(oldLocalId)));
        }
        addGlobalInformationError();

        List<String> nonCompletedFields = updateCompleteness(measure, res);
        context.getMessages().add(new LocalizableMessage("measure.update.message", HtmlUtil.encode(measure.getInspireidLocalid())));
        getMessageAboutIncompleteFields("measure", nonCompletedFields);

        return new RedirectResolution(MeasureActionBean.class);
    }

    /**
     * Check if the measure is complete and update its status.
     *
     * @param measure The measure of consideration.
     * @param res The resources available.
     * @return A list of fields which have not been completed.
     * @throws Exception
     */
    public static List<String> updateCompleteness(MeasuresBean measure, ResourceBundle res) throws Exception {
        ArrayList<String> result = new ArrayList<String>();

        if (measure.getProviderBean() == null || !isDefined(measure.getProviderBean().getOrganisationname())) {
            result.add(res.getString("measure.providerBean.organisationname"));
        }
        if (measure.getProviderBean() == null || !isDefined(measure.getProviderBean().getWebsite())) {
            result.add(res.getString("measure.providerBean.website"));
        }
        if (measure.getProviderBean() == null || !isDefined(measure.getProviderBean().getIndividualname())) {
            result.add(res.getString("measure.providerBean.individualname"));
        }
        if (measure.getProviderBean() == null || !isDefined(measure.getProviderBean().getAddress())) {
            result.add(res.getString("measure.providerBean.address"));
        }
        if (measure.getProviderBean() == null || !isDefined(measure.getProviderBean().getTelephonevoice())) {
            result.add(res.getString("measure.providerBean.telephonevoice"));
        }
        if (measure.getProviderBean() == null || !isDefined(measure.getProviderBean().getElectronicmailaddress())) {
            result.add(res.getString("measure.providerBean.electronicmailaddress"));
        }

        if (!isDefined(measure.getDescriptionofchanges())) {
            result.add(res.getString("measure.descriptionofchanges"));
        }

        if (!isDefined(measure.getReportingstartdate())) {
            result.add(res.getString("measure.reportingstartdate"));
        }
        if (!isDefined(measure.getReportingenddate())) {
            result.add(res.getString("measure.reportingenddate"));
        }

        if (!isDefined(measure.getCode())) {
            result.add(res.getString("measure.code"));
        }
        if (!isDefined(measure.getName())) {
            result.add(res.getString("measure.name"));
        }
        if (!isDefined(measure.getDescription())) {
            result.add(res.getString("measure.description"));
        }
        if (!isDefined(measure.getClassification_uri())) {
            result.add(res.getString("measure.classification_uri"));
        }
        if (!isDefined(measure.getMeasuretype_uri())) {
            result.add(res.getString("measure.measuretype_uri"));
        }
        if (!isDefined(measure.getAdministrationlevel_uri())) {
            result.add(res.getString("measure.administrationlevel_uri"));
        }
        if (!isDefined(measure.getTimescale_uri())) {
            result.add(res.getString("measure.timescale_uri"));
        }
        if (!isDefined(measure.getSourcesector_uri())) {
            result.add(res.getString("measure.sourcesector_uri"));
        }
        if (!isDefined(measure.getSpatialscale_uri())) {
            result.add(res.getString("measure.spatialscale_uri"));
        }
        if (measure.getPlannedimplementationBean() == null || !isDefined(measure.getPlannedimplementationBean().getImplementationplannedtimeperiodBeginposition())) {
            result.add(res.getString("measure.plannedimplementationBean.implementationplannedtimeperiodBeginposition"));
        }
        if (measure.getPlannedimplementationBean() == null || !isDefined(measure.getPlannedimplementationBean().getImplementationplannedtimeperiodEndposition())) {
            result.add(res.getString("measure.plannedimplementationBean.implementationplannedtimeperiodEndposition"));
        }
        if (measure.getPlannedimplementationBean() == null || (!isDefined(measure.getPlannedimplementationBean().getPlannedfulleffectdateTimeposition()) && !measure.getPlannedimplementationBean().isPlannedfulleffectdateTimeposition_nil())) {
            result.add(res.getString("measure.plannedimplementationBean.plannedfulleffectdateTimeposition"));
        }
        if (measure.getPlannedimplementationBean() == null || (!isDefined(measure.getPlannedimplementationBean().getMonitoringprogressindicators()) && !measure.getPlannedimplementationBean().isMonitoringprogressindicators_nil())) {
            result.add(res.getString("measure.plannedimplementationBean.monitoringprogressindicators"));
        }

        if (!isDefined(measure.getReductionofemission()) && !measure.isReductionofemission_nil()) {
            result.add(res.getString("measure.reductionofemission"));
        }
        if (!isDefined(measure.getQuantificationnumerical_uri())) {
            result.add(res.getString("measure.quantificationnumerical_uri"));
        }

        if (!isDefined(measure.getSourceapportionmentBeanList())) {
            result.add(res.getString("measure.sourceapportionmentBeanList"));
        }
        measuresManager.setCompletnessByMeasureID(measure.getUuid(), result.isEmpty());
        return result;
    }

    /**
     * Getter.
     *
     * @return The edited measure.
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
        @Validate(field = "description", required = false, on = "save"),
        @Validate(field = "plannedimplementationBean.implementationplannedtimeperiodBeginposition", maxlength = 10, mask = ValidationMasks.date, required = false, on = "save"),
        @Validate(field = "plannedimplementationBean.implementationplannedtimeperiodEndposition", maxlength = 10, mask = ValidationMasks.date, required = false, on = "save"),
        @Validate(field = "plannedimplementationBean.implementationactualtimeperiodBeginposition", maxlength = 10, mask = ValidationMasks.date, required = false, on = "save"),
        @Validate(field = "plannedimplementationBean.implementationactualtimeperiodEndposition", maxlength = 10, mask = ValidationMasks.date, required = false, on = "save"),
        @Validate(field = "plannedimplementationBean.plannedfulleffectdateTimeposition", maxlength = 10, mask = ValidationMasks.date, required = false, on = "save"),
        @Validate(field = "plannedimplementationBean.otherdate", maxlength = 250, required = false, on = "save"),
        @Validate(field = "plannedimplementationBean.monitoringprogressindicators", maxlength = 250, required = false, on = "save"),
        @Validate(field = "plannedimplementationBean.comment", required = false, on = "save"),
        @Validate(field = "reductionofemission", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "comment", required = false, on = "save"),
        @Validate(field = "expectedimpactBean.levelofconcentration", required = false, mask = ValidationMasks.number, on = "save"),
        @Validate(field = "expectedimpactBean.numberofexceedence", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "expectedimpactBean.comment", required = false, on = "save"),
        @Validate(field = "commentForClarification", required = false, on = "save")
    })
    @Override
    public MeasuresBean getMeasure() {
        return super.getMeasure();
    }
    /**
     * The list of possible values for field measure.measuretype_uri.
     */
    protected static List<MeasuretypeBean> possibleTypes;

    /**
     * @return The list of possible values for field measure.type.
     */
    public List<MeasuretypeBean> getPossibleTypes() {
        if (possibleTypes == null) {
            possibleTypes = measuresManager.getAllMeasuretype();
        }
        return possibleTypes;
    }
    /**
     * The list of possible values for field measure.classification_uri.
     */
    protected static List<ClassificationBean> possibleClassifications;

    /**
     * @return The list of possible values for field measure.classification_uri.
     */
    public List<ClassificationBean> getPossibleClassifications() {
        if (possibleClassifications == null) {
            possibleClassifications = measuresManager.getAllClassification();
        }
        return possibleClassifications;
    }
    /**
     * The list of possible values for field administrationlevel_uri.
     */
    protected static List<AdministrativelevelBean> possibleAdminLevels;

    /**
     * @return The list of possible values for field
     * measure.administrationlevel_uri.
     */
    public List<AdministrativelevelBean> getPossibleAdminLevels() {
        if (possibleAdminLevels == null) {
            possibleAdminLevels = measuresManager.getAllAdministrativelevel();
        }
        return possibleAdminLevels;
    }
    /**
     * The list of possible values for field measure.timescale_uri.
     */
    protected static List<TimescaleBean> possibleTimeScales;

    /**
     * @return The list of possible values for field measure.timescale_uri.
     */
    public List<TimescaleBean> getPossibleTimeScales() {
        if (possibleTimeScales == null) {
            possibleTimeScales = measuresManager.getAllTimescale();
        }
        return possibleTimeScales;
    }
    /**
     * The list of possible values for field measure.sourcesector_uri.
     */
    protected static List<SourcesectorsBean> possibleSourceSectors;

    /**
     * @return The list of possible values for field measure.sourcesector_uri.
     */
    public List<SourcesectorsBean> getPossibleSourceSectors() {
        if (possibleSourceSectors == null) {
            possibleSourceSectors = measuresManager.getAllSourcesectors();
        }
        return possibleSourceSectors;
    }
    /**
     * The list of possible values for field measure.spatialscale_uri.
     */
    protected static List<SpatialscaleBean> possibleSpatialScales;

    /**
     * @return The list of possible values for field measure.spatialscale_uri.
     */
    public List<SpatialscaleBean> getPossibleSpatialScales() {
        if (possibleSpatialScales == null) {
            possibleSpatialScales = measuresManager.getAllSpatialscale();
        }
        return possibleSpatialScales;
    }
    /**
     * The list of possible values for field
     * measure.plannedimplementationBean.statusplannedimplementation_uri.
     */
    protected static List<StatusplannedimplementationBean> possibleImplementationStatuses;

    /**
     * @return The list of possible values for field
     * measure.plannedimplementationBean.statusplannedimplementation_uri.
     */
    public List<StatusplannedimplementationBean> getPossibleImplementationStatuses() {
        if (possibleImplementationStatuses == null) {
            possibleImplementationStatuses = measuresManager.getAllMeasureimplementationstatus();
        }
        return possibleImplementationStatuses;
    }
    /**
     * The list of possible values for field
     * measure.quantificationnumerical_uri.
     */
    protected static List<QuantificationnumericalBean> possibleQuantificationNummericals;

    /**
     * @return The list of possible values for field
     * measure.quantificationnumerical_uri.
     */
    public List<QuantificationnumericalBean> getPossibleQuantificationNummericals() {
        if (possibleQuantificationNummericals == null) {
            possibleQuantificationNummericals = measuresManager.getAllQuantificationnumerical();
        }
        return possibleQuantificationNummericals;
    }
    /**
     * The list of possible values for field impact.specificationofhours_uri.
     */
    protected static List<SpecificationofhoursBean> possibleSpecificationOfHours;

    /**
     * @return The list of possible values for field
     * impact.specificationofhours_uri.
     */
    public List<SpecificationofhoursBean> getPossibleSpecificationOfHours() {
        if (possibleSpecificationOfHours == null) {
            possibleSpecificationOfHours = measuresManager.getAllSpecificationofhoursBean();
        }
        return possibleSpecificationOfHours;
    }

    private void addGlobalInformationError() throws NumberFormatException {
        /**
         * reporting date
         */
        String reportingDateStart = measure.getReportingstartdate();
        String reportingDateEnd = measure.getReportingenddate();
        if (reportingDateStart != null && reportingDateEnd != null) {
            String[] startArray = reportingDateStart.split("-");
            String[] endArray = reportingDateEnd.split("-");

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date startDate = new Date(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date endDate = new Date(Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));

            if (startDate.after(endDate)) {
                context.getValidationErrors().addGlobalError(new LocalizableError("measures.error.reportingdatestart.after.reportingdateend", HtmlUtil.encode(reportingDateStart), HtmlUtil.encode(reportingDateEnd)));
            }
        }

        /**
         * Implementation planned time period
         */
        String plannedImplementationPlannedBeginPosition = measure.getPlannedimplementationBean().getImplementationplannedtimeperiodBeginposition();
        String plannedImplementationPlannedEndPosition = measure.getPlannedimplementationBean().getImplementationplannedtimeperiodEndposition();
        if (plannedImplementationPlannedBeginPosition != null && plannedImplementationPlannedEndPosition != null) {
            String[] startArray = plannedImplementationPlannedBeginPosition.split("-");
            String[] endArray = plannedImplementationPlannedEndPosition.split("-");

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date startDate = new Date(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date endDate = new Date(Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));

            if (startDate.after(endDate)) {
                context.getValidationErrors().addGlobalError(new LocalizableError("measures.error.implementationplannedtimeperiod.beginposition.after.endposition", HtmlUtil.encode(plannedImplementationPlannedBeginPosition), HtmlUtil.encode(plannedImplementationPlannedEndPosition)));
            }
        }

        /**
         * Implementation actual time period
         */
        String plannedImplementationActualBeginPosition = measure.getPlannedimplementationBean().getImplementationactualtimeperiodBeginposition();
        String plannedImplementationActualEndPosition = measure.getPlannedimplementationBean().getImplementationactualtimeperiodEndposition();
        if (plannedImplementationActualBeginPosition != null && plannedImplementationActualEndPosition != null) {
            String[] startArray = plannedImplementationActualBeginPosition.split("-");
            String[] endArray = plannedImplementationActualEndPosition.split("-");

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date startDate = new Date(Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
            Date endDate = new Date(Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));

            if (startDate.after(endDate)) {
                context.getValidationErrors().addGlobalError(new LocalizableError("measures.error.implementationactualtimeperiod.beginposition.after.endposition", HtmlUtil.encode(plannedImplementationActualBeginPosition), HtmlUtil.encode(plannedImplementationActualEndPosition)));
            }
        }

    }
}
