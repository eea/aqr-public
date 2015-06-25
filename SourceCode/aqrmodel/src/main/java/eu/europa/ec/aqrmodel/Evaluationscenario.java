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
package eu.europa.ec.aqrmodel;

import eu.europa.ec.aqrcrosstablesmodel.EvaluationscenarioPublication;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresEvaluationscenario;
import eu.europa.ec.aqrmodeluser.Users;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "evaluationscenario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluationscenario.findAll", query = "SELECT e FROM Evaluationscenario e"),
    @NamedQuery(name = "Evaluationscenario.findByUuid", query = "SELECT e FROM Evaluationscenario e WHERE e.uuid = :uuid"),
    @NamedQuery(name = "Evaluationscenario.findByInspireidLocalid", query = "SELECT e FROM Evaluationscenario e WHERE e.inspireidLocalid = :inspireidLocalid"),
    @NamedQuery(name = "Evaluationscenario.findByInspireidNamespace", query = "SELECT e FROM Evaluationscenario e WHERE e.inspireidNamespace = :inspireidNamespace"),
    @NamedQuery(name = "Evaluationscenario.findByInspireidVersionid", query = "SELECT e FROM Evaluationscenario e WHERE e.inspireidVersionid = :inspireidVersionid"),
    @NamedQuery(name = "Evaluationscenario.findByCodeofscenario", query = "SELECT e FROM Evaluationscenario e WHERE e.codeofscenario = :codeofscenario"),
    @NamedQuery(name = "Evaluationscenario.findByAttainmentyearId", query = "SELECT e FROM Evaluationscenario e WHERE e.attainmentyearId = :attainmentyearId"),
    @NamedQuery(name = "Evaluationscenario.findByAttainmentyearPeriodtime", query = "SELECT e FROM Evaluationscenario e WHERE e.attainmentyearPeriodtime = :attainmentyearPeriodtime"),
    @NamedQuery(name = "Evaluationscenario.findByStartyearId", query = "SELECT e FROM Evaluationscenario e WHERE e.startyearId = :startyearId"),
    @NamedQuery(name = "Evaluationscenario.findByStartyearPeriodtime", query = "SELECT e FROM Evaluationscenario e WHERE e.startyearPeriodtime = :startyearPeriodtime"),
    @NamedQuery(name = "Evaluationscenario.findByCompletedAndUser", query = "SELECT e FROM Evaluationscenario e WHERE e.completed = :completed AND e.users = :users"),
    @NamedQuery(name = "Evaluationscenario.findByDatecreation", query = "SELECT e FROM Evaluationscenario e WHERE e.datecreation = :datecreation"),
    @NamedQuery(name = "Evaluationscenario.findByDatelastupdate", query = "SELECT e FROM Evaluationscenario e WHERE e.datelastupdate = :datelastupdate"),
    @NamedQuery(name = "Evaluationscenario.findAllByUser", query = "SELECT p FROM Evaluationscenario p WHERE p.users = :users"),
    @NamedQuery(name = "Evaluationscenario.findAllByUserLastUpdate", query = "SELECT p FROM Evaluationscenario p WHERE p.userlastupdate = :userlastupdate"),
    @NamedQuery(name = "Evaluationscenario.findAllByUserInInterval", query = "SELECT p FROM Evaluationscenario p WHERE p.users = :users AND ((p.datecreation >= :fromDate AND p.datecreation <= :toDate) OR (p.datelastupdate >= :fromDate AND p.datelastupdate <= :toDate)) ORDER BY p.datecreation ASC"),
    @NamedQuery(name = "Evaluationscenario.deleteByUuid", query = "DELETE FROM Evaluationscenario m WHERE m.uuid = :uuid")
})
public class Evaluationscenario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "inspireid_localid")
    private String inspireidLocalid;
    @Basic(optional = false)
    @Column(name = "inspireid_namespace")
    private String inspireidNamespace;
    @Basic(optional = false)
    @Column(name = "inspireid_versionid")
    private String inspireidVersionid;
    @JoinColumn(name = "provider", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Relatedparty provider;
    @Basic(optional = false)
    @Column(name = "codeofscenario")
    private String codeofscenario;
    @Basic(optional = false)
    @Column(name = "attainmentyear_id")
    private String attainmentyearId;
    @Basic(optional = false)
    @Column(name = "attainmentyear_periodtime")
    private String attainmentyearPeriodtime;
    @Basic(optional = false)
    @Column(name = "startyear_id")
    private String startyearId;
    @Basic(optional = false)
    @Column(name = "startyear_periodtime")
    private String startyearPeriodtime;
    @Column(name = "completed")
    private Boolean completed;
    @Basic(optional = false)
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "datelastupdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datelastupdate;
    @Basic(optional = false)
    @Column(name = "changes")
    private boolean changes;
    @Column(name = "descriptionofchanges")
    private String descriptionofchanges;
    @Basic(optional = false)
    @Column(name = "reportingstartdate")
    private String reportingstartdate;
    @Basic(optional = false)
    @Column(name = "reportingenddate")
    private String reportingenddate;
    @JoinColumn(name = "baselinescenario", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Scenario baselinescenario;
    @JoinColumn(name = "projectionscenario", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Scenario projectionscenario;
    @JoinColumn(name = "sourceapportionment", referencedColumnName = "uuid")
    @ManyToOne
    private Sourceapportionment sourceapportionment;
    @JoinColumn(name = "plan", referencedColumnName = "uuid")
    @ManyToOne
    private Plan plan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluationscenario")
    private List<MeasuresEvaluationscenario> measuresEvaluationscenarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluationscenario")
    private List<EvaluationscenarioPublication> evaluationscenarioPublicationList;
    @JoinColumn(name = "users", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "userlastupdate", referencedColumnName = "uuid")
    @OneToOne
    private Users userlastupdate;

    public Evaluationscenario() {
    }

    public Evaluationscenario(String uuid) {
        this.uuid = uuid;
    }

    public Evaluationscenario(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, String codeofscenario, String attainmentyearId, String attainmentyearPeriodtime, String startyearId, String startyearPeriodtime, Date datecreation) {
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getInspireidLocalid() {
        return inspireidLocalid;
    }

    public void setInspireidLocalid(String inspireidLocalid) {
        this.inspireidLocalid = inspireidLocalid;
    }

    public String getInspireidNamespace() {
        return inspireidNamespace;
    }

    public void setInspireidNamespace(String inspireidNamespace) {
        this.inspireidNamespace = inspireidNamespace;
    }

    public String getInspireidVersionid() {
        return inspireidVersionid;
    }

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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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

    public Scenario getBaselinescenario() {
        return baselinescenario;
    }

    public void setBaselinescenario(Scenario baselinescenario) {
        this.baselinescenario = baselinescenario;
    }

    public Scenario getProjectionscenario() {
        return projectionscenario;
    }

    public void setProjectionscenario(Scenario projectionscenario) {
        this.projectionscenario = projectionscenario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluationscenario)) {
            return false;
        }
        Evaluationscenario other = (Evaluationscenario) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Evaluationscenario[ uuid=" + uuid + " ]";
    }

    public boolean isChanges() {
        return changes;
    }

    public void setChanges(boolean changes) {
        this.changes = changes;
    }

    public String getDescriptionofchanges() {
        return descriptionofchanges;
    }

    public void setDescriptionofchanges(String descriptionofchanges) {
        this.descriptionofchanges = descriptionofchanges;
    }

    public String getReportingstartdate() {
        return reportingstartdate;
    }

    public void setReportingstartdate(String reportingstartdate) {
        this.reportingstartdate = reportingstartdate;
    }

    public String getReportingenddate() {
        return reportingenddate;
    }

    public void setReportingenddate(String reportingenddate) {
        this.reportingenddate = reportingenddate;
    }

    public Sourceapportionment getSourceapportionment() {
        return sourceapportionment;
    }

    public void setSourceapportionment(Sourceapportionment sourceapportionment) {
        this.sourceapportionment = sourceapportionment;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Relatedparty getProvider() {
        return provider;
    }

    public void setProvider(Relatedparty provider) {
        this.provider = provider;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getUserlastupdate() {
        return userlastupdate;
    }

    public void setUserlastupdate(Users userlastupdate) {
        this.userlastupdate = userlastupdate;
    }

    @XmlTransient
    public List<MeasuresEvaluationscenario> getMeasuresEvaluationscenarioList() {
        return measuresEvaluationscenarioList;
    }

    public void setMeasuresEvaluationscenarioList(List<MeasuresEvaluationscenario> measuresEvaluationscenarioList) {
        this.measuresEvaluationscenarioList = measuresEvaluationscenarioList;
    }

    @XmlTransient
    public List<EvaluationscenarioPublication> getEvaluationscenarioPublicationList() {
        return evaluationscenarioPublicationList;
    }

    public void setEvaluationscenarioPublicationList(List<EvaluationscenarioPublication> evaluationscenarioPublicationList) {
        this.evaluationscenarioPublicationList = evaluationscenarioPublicationList;
    }
}
