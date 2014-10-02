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
package eu.europa.ec.measures;

import eu.europa.ec.measures.plannedimplementation.PlannedimplementationBean;
import eu.europa.ec.measures.expectedimpact.ExpectedimpactBean;
import eu.europa.ec.measures.cost.CostsBean;
import eu.europa.ec.common.HeaderInterface;
import eu.europa.ec.common.relatedparty.RelatedpartyBean;
import java.util.Date;
import java.util.List;

public class MeasuresBean implements HeaderInterface {

    private String uuid;
    /**
     * inspire id
     */
    private String inspireidLocalid;
    private String inspireidNamespace;
    private String inspireidVersionid;

    /**
     * code
     */
    private String code;
    private String name;
    private String description;

    private boolean completed;
    private boolean editable;
    private RelatedpartyBean providerBean;

    private boolean changes;
    private String descriptionofchanges;
    private String reportingstartdate;
    private String reportingenddate;

    private Date datecreation;
    private Date datelastupdate;

    /**
     * Classification
     */
    private List<String> classification_uri;
    /**
     * Type Measuretype
     */
    private String measuretype_uri;

    /**
     * Administrative level
     */
    private List<String> administrationlevel_uri;
    /**
     * Time scale
     */
    private String timescale_uri;

    /**
     * Cost
     */
    private CostsBean costsBean;
    /**
     * Source sector
     */
    private List<String> sourcesector_uri;
    /**
     * Spatial scale
     */
    private List<String> spatialscale_uri;
    /**
     * Planned Implementation
     */
    private PlannedimplementationBean plannedimplementationBean;

    /**
     * Reduction of emissions
     */
    private String reductionofemission;
    private boolean reductionofemission_nil;
    private String reductionofemission_nilreason;

    private String quantificationnumerical_uri;
    private String comment;

    /**
     * Expect Impact
     */
    private ExpectedimpactBean expectedimpactBean;
    /**
     * Comment
     */
    private String commentForClarification;

    /**
     * Exceedance situation/Sourceapportionment list
     */
    private List<String> sourceapportionmentBeanList;
    /**
     * Evaluation scenario list
     */
    private List<String> evaluationscenarioBeanList;

    public MeasuresBean() {
    }

    public MeasuresBean(String uuid) {
        this.uuid = uuid;
    }

    public MeasuresBean(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, String code, String name, String description, Date datecreation) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
        this.code = code;
        this.name = name;
        this.description = description;
        this.datecreation = datecreation;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getInspireidLocalid() {
        return inspireidLocalid;
    }

    @Override
    public void setInspireidLocalid(String inspireidLocalid) {
        this.inspireidLocalid = inspireidLocalid;
    }

    @Override
    public String getInspireidNamespace() {
        return inspireidNamespace;
    }

    @Override
    public void setInspireidNamespace(String inspireidNamespace) {
        this.inspireidNamespace = inspireidNamespace;
    }

    @Override
    public String getInspireidVersionid() {
        return inspireidVersionid;
    }

    @Override
    public void setInspireidVersionid(String inspireidVersionid) {
        this.inspireidVersionid = inspireidVersionid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public RelatedpartyBean getProviderBean() {
        return providerBean;
    }

    @Override
    public void setProviderBean(RelatedpartyBean providerBean) {
        this.providerBean = providerBean;
    }

    @Override
    public boolean isChanges() {
        return changes;
    }

    @Override
    public void setChanges(boolean changes) {
        this.changes = changes;
    }

    @Override
    public String getDescriptionofchanges() {
        return descriptionofchanges;
    }

    @Override
    public void setDescriptionofchanges(String descriptionofchanges) {
        this.descriptionofchanges = descriptionofchanges;
    }

    @Override
    public String getReportingstartdate() {
        return reportingstartdate;
    }

    @Override
    public void setReportingstartdate(String reportingstartdate) {
        this.reportingstartdate = reportingstartdate;
    }

    @Override
    public String getReportingenddate() {
        return reportingenddate;
    }

    @Override
    public void setReportingenddate(String reportingenddate) {
        this.reportingenddate = reportingenddate;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDatelastupdate() {
        return datelastupdate;
    }

    public void setDatelastupdate(Date datelastupdate) {
        this.datelastupdate = datelastupdate;
    }

    public List<String> getClassification_uri() {
        return classification_uri;
    }

    public void setClassification_uri(List<String> classification_uri) {
        this.classification_uri = classification_uri;
    }

    public String getMeasuretype_uri() {
        return measuretype_uri;
    }

    public void setMeasuretype_uri(String measuretype_uri) {
        this.measuretype_uri = measuretype_uri;
    }

    public List<String> getAdministrationlevel_uri() {
        return administrationlevel_uri;
    }

    public void setAdministrationlevel_uri(List<String> administrationlevel_uri) {
        this.administrationlevel_uri = administrationlevel_uri;
    }

    public String getTimescale_uri() {
        return timescale_uri;
    }

    public void setTimescale_uri(String timescale_uri) {
        this.timescale_uri = timescale_uri;
    }

    public CostsBean getCostsBean() {
        return costsBean;
    }

    public void setCostsBean(CostsBean costsBean) {
        this.costsBean = costsBean;
    }

    public List<String> getSourcesector_uri() {
        return sourcesector_uri;
    }

    public void setSourcesector_uri(List<String> sourcesector_uri) {
        this.sourcesector_uri = sourcesector_uri;
    }

    public List<String> getSpatialscale_uri() {
        return spatialscale_uri;
    }

    public void setSpatialscale_uri(List<String> spatialscale_uri) {
        this.spatialscale_uri = spatialscale_uri;
    }

    public PlannedimplementationBean getPlannedimplementationBean() {
        return plannedimplementationBean;
    }

    public void setPlannedimplementationBean(PlannedimplementationBean plannedimplementationBean) {
        this.plannedimplementationBean = plannedimplementationBean;
    }

    public String getReductionofemission() {
        return reductionofemission;
    }

    public void setReductionofemission(String reductionofemission) {
        this.reductionofemission = reductionofemission;
    }

    public boolean isReductionofemission_nil() {
        return reductionofemission_nil;
    }

    public void setReductionofemission_nil(boolean reductionofemission_nil) {
        this.reductionofemission_nil = reductionofemission_nil;
    }

    public String getReductionofemission_nilreason() {
        return reductionofemission_nilreason;
    }

    public void setReductionofemission_nilreason(String reductionofemission_nilreason) {
        this.reductionofemission_nilreason = reductionofemission_nilreason;
    }

    public String getQuantificationnumerical_uri() {
        return quantificationnumerical_uri;
    }

    public void setQuantificationnumerical_uri(String quantificationnumerical_uri) {
        this.quantificationnumerical_uri = quantificationnumerical_uri;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ExpectedimpactBean getExpectedimpactBean() {
        return expectedimpactBean;
    }

    public void setExpectedimpactBean(ExpectedimpactBean expectedimpactBean) {
        this.expectedimpactBean = expectedimpactBean;
    }

    public String getCommentForClarification() {
        return commentForClarification;
    }

    public void setCommentForClarification(String commentForClarification) {
        this.commentForClarification = commentForClarification;
    }

    public List<String> getSourceapportionmentBeanList() {
        return sourceapportionmentBeanList;
    }

    public void setSourceapportionmentBeanList(List<String> sourceapportionmentBeanList) {
        this.sourceapportionmentBeanList = sourceapportionmentBeanList;
    }

    public List<String> getEvaluationscenarioBeanList() {
        return evaluationscenarioBeanList;
    }

    public void setEvaluationscenarioBeanList(List<String> evaluationscenarioBeanList) {
        this.evaluationscenarioBeanList = evaluationscenarioBeanList;
    }

}
