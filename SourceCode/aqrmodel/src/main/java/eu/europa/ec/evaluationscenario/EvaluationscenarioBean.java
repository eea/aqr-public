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
package eu.europa.ec.evaluationscenario;

import eu.europa.ec.common.HeaderInterface;
import eu.europa.ec.evaluationscenario.scenario.ScenarioBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.common.relatedparty.RelatedpartyBean;
import java.util.Date;

public class EvaluationscenarioBean implements HeaderInterface {

    private String uuid;

    private String inspireidLocalid;
    private String inspireidNamespace;
    private String inspireidVersionid;

    private String codeofscenario;

    private String attainmentyearId;
    private String attainmentyearPeriodtime;

    private String startyearId;
    private String startyearPeriodtime;

    private boolean completed;
    private boolean editable;
    private RelatedpartyBean providerBean;

    private boolean changes;
    private String descriptionofchanges;
    private String reportingstartdate;
    private String reportingenddate;

    private Date datecreation;
    private Date datelastupdate;

    private ScenarioBean baselinescenario;
    private ScenarioBean projectionscenario;

    private SourceapportionmentBean sourceapportionment;
    private PlanBean plan;

    public EvaluationscenarioBean() {
    }

    public EvaluationscenarioBean(String uuid) {
        this.uuid = uuid;
    }

    public EvaluationscenarioBean(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, String codeofscenario, String attainmentyearId, String attainmentyearPeriodtime, String startyearId, String startyearPeriodtime, Date datecreation) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
        this.codeofscenario = codeofscenario;
        this.attainmentyearId = attainmentyearId;
        this.attainmentyearPeriodtime = attainmentyearPeriodtime;
        this.startyearId = startyearId;
        this.startyearPeriodtime = startyearPeriodtime;
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

    public String getCodeofscenario() {
        return codeofscenario;
    }

    public void setCodeofscenario(String codeofscenario) {
        this.codeofscenario = codeofscenario;
    }

    public String getAttainmentyearId() {
        return attainmentyearId;
    }

    public void setAttainmentyearId(String attainmentyearId) {
        this.attainmentyearId = attainmentyearId;
    }

    public String getAttainmentyearPeriodtime() {
        return attainmentyearPeriodtime;
    }

    public void setAttainmentyearPeriodtime(String attainmentyearPeriodtime) {
        this.attainmentyearPeriodtime = attainmentyearPeriodtime;
    }

    public String getStartyearId() {
        return startyearId;
    }

    public void setStartyearId(String startyearId) {
        this.startyearId = startyearId;
    }

    public String getStartyearPeriodtime() {
        return startyearPeriodtime;
    }

    public void setStartyearPeriodtime(String startyearPeriodtime) {
        this.startyearPeriodtime = startyearPeriodtime;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ScenarioBean getBaselinescenario() {
        return baselinescenario;
    }

    public void setBaselinescenario(ScenarioBean baselinescenario) {
        this.baselinescenario = baselinescenario;
    }

    public ScenarioBean getProjectionscenario() {
        return projectionscenario;
    }

    public void setProjectionscenario(ScenarioBean projectionscenario) {
        this.projectionscenario = projectionscenario;
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

    public SourceapportionmentBean getSourceapportionment() {
        return sourceapportionment;
    }

    public void setSourceapportionment(SourceapportionmentBean sourceapportionment) {
        this.sourceapportionment = sourceapportionment;
    }

    public PlanBean getPlan() {
        return plan;
    }

    public void setPlan(PlanBean plan) {
        this.plan = plan;
    }

    @Override
    public RelatedpartyBean getProviderBean() {
        return providerBean;
    }

    @Override
    public void setProviderBean(RelatedpartyBean providerBean) {
        this.providerBean = providerBean;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }

}
