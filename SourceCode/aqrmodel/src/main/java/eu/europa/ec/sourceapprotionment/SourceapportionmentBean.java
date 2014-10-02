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
package eu.europa.ec.sourceapprotionment;

import eu.europa.ec.sourceapprotionment.localincrement.LocalincrementBean;
import eu.europa.ec.sourceapprotionment.regionalbackground.RegionalbackgroundBean;
import eu.europa.ec.sourceapprotionment.urbanbackground.UrbanbackgroundBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.common.relatedparty.RelatedpartyBean;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.ExceedancedescriptionBean;
import eu.europa.ec.common.HeaderInterface;
import java.util.Date;

public class SourceapportionmentBean implements HeaderInterface {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String inspireidLocalid;
    private String inspireidNamespace;
    private String inspireidVersionid;

    private String referenceyearId;
    private String referenceyearTimeperiod;

    private String comment;
    private boolean completed;

    private boolean editable;
    private RelatedpartyBean providerBean;

    private Date datecreation;
    private Date datelastupdate;

    private boolean changes;
    private String descriptionofchanges;
    private String reportingstartdate;
    private String reportingenddate;

    private UrbanbackgroundBean urbanbackgroundBean;
    private RegionalbackgroundBean regionalbackgroundBean;
    private LocalincrementBean localincrementBean;

    private ExceedancedescriptionBean exceedancedescriptionBean;

    private PlanBean planBean;
    private AttainmentBean attainmentBean;

    public SourceapportionmentBean() {
    }

    public SourceapportionmentBean(String uuid) {
        this.uuid = uuid;
    }

    public SourceapportionmentBean(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, Date datecreation) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
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

    public String getReferenceyearId() {
        return referenceyearId;
    }

    public void setReferenceyearId(String referenceyearId) {
        this.referenceyearId = referenceyearId;
    }

    public String getReferenceyearTimeperiod() {
        return referenceyearTimeperiod;
    }

    public void setReferenceyearTimeperiod(String referenceyearTimeperiod) {
        this.referenceyearTimeperiod = referenceyearTimeperiod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
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

    @Override
    public RelatedpartyBean getProviderBean() {
        return providerBean;
    }

    @Override
    public void setProviderBean(RelatedpartyBean provierBean) {
        this.providerBean = provierBean;
    }

    public UrbanbackgroundBean getUrbanbackgroundBean() {
        return urbanbackgroundBean;
    }

    public void setUrbanbackgroundBean(UrbanbackgroundBean urbanbackgroundBean) {
        this.urbanbackgroundBean = urbanbackgroundBean;
    }

    public RegionalbackgroundBean getRegionalbackgroundBean() {
        return regionalbackgroundBean;
    }

    public void setRegionalbackgroundBean(RegionalbackgroundBean regionalbackgroundBean) {
        this.regionalbackgroundBean = regionalbackgroundBean;
    }

    public PlanBean getPlanBean() {
        return planBean;
    }

    public void setPlanBean(PlanBean planBean) {
        this.planBean = planBean;
    }

    public LocalincrementBean getLocalincrementBean() {
        return localincrementBean;
    }

    public void setLocalincrementBean(LocalincrementBean localincrementBean) {
        this.localincrementBean = localincrementBean;
    }

    public ExceedancedescriptionBean getExceedancedescriptionBean() {
        return exceedancedescriptionBean;
    }

    public void setExceedancedescriptionBean(ExceedancedescriptionBean exceedancedescriptionBean) {
        this.exceedancedescriptionBean = exceedancedescriptionBean;
    }

    public AttainmentBean getAttainmentBean() {
        return attainmentBean;
    }

    public void setAttainmentBean(AttainmentBean attainmentBean) {
        this.attainmentBean = attainmentBean;
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

}
