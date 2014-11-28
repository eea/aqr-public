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

import eu.europa.ec.aqrcrosstablesmodel.AttainmentPlan;
import eu.europa.ec.aqrcrosstablesmodel.PlanPollutantProtectiontarget;
import eu.europa.ec.aqrcrosstablesmodel.PlanPublication;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
    @NamedQuery(name = "Plan.findByUuid", query = "SELECT p FROM Plan p WHERE p.uuid = :uuid"),
    @NamedQuery(name = "Plan.findByInspireidLocalid", query = "SELECT p FROM Plan p WHERE p.inspireidLocalid = :inspireidLocalid"),
    @NamedQuery(name = "Plan.findByInspireidNamespace", query = "SELECT p FROM Plan p WHERE p.inspireidNamespace = :inspireidNamespace"),
    @NamedQuery(name = "Plan.findByInspireidVersionid", query = "SELECT p FROM Plan p WHERE p.inspireidVersionid = :inspireidVersionid"),
    @NamedQuery(name = "Plan.findByCode", query = "SELECT p FROM Plan p WHERE p.code = :code"),
    @NamedQuery(name = "Plan.findByName", query = "SELECT p FROM Plan p WHERE p.name = :name"),
    @NamedQuery(name = "Plan.findByFirstexceedanceyearId", query = "SELECT p FROM Plan p WHERE p.firstexceedanceyearId = :firstexceedanceyearId"),
    @NamedQuery(name = "Plan.findByFirstexceedanceyearTimeposition", query = "SELECT p FROM Plan p WHERE p.firstexceedanceyearTimeposition = :firstexceedanceyearTimeposition"),
    @NamedQuery(name = "Plan.findByAdoptiondateId", query = "SELECT p FROM Plan p WHERE p.adoptiondateId = :adoptiondateId"),
    @NamedQuery(name = "Plan.findByAdoptiondateTimeposition", query = "SELECT p FROM Plan p WHERE p.adoptiondateTimeposition = :adoptiondateTimeposition"),
    @NamedQuery(name = "Plan.findByTimetable", query = "SELECT p FROM Plan p WHERE p.timetable = :timetable"),
    @NamedQuery(name = "Plan.findByReferenceaqplan", query = "SELECT p FROM Plan p WHERE p.referenceaqplan = :referenceaqplan"),
    @NamedQuery(name = "Plan.findByReferenceimplementation", query = "SELECT p FROM Plan p WHERE p.referenceimplementation = :referenceimplementation"),
    @NamedQuery(name = "Plan.findByComment", query = "SELECT p FROM Plan p WHERE p.comment = :comment"),
    @NamedQuery(name = "Plan.findByDatecreation", query = "SELECT p FROM Plan p WHERE p.datecreation = :datecreation"),
    @NamedQuery(name = "Plan.findByDatelastupdate", query = "SELECT p FROM Plan p WHERE p.datelastupdate = :datelastupdate"),
    @NamedQuery(name = "Plan.findAllByUser", query = "SELECT p FROM Plan p WHERE p.users = :users"),
    @NamedQuery(name = "Plan.findAllByUserInInterval", query = "SELECT p FROM Plan p WHERE p.users = :users AND ((p.datecreation >= :fromDate AND p.datecreation <= :toDate) OR (p.datelastupdate >= :fromDate AND p.datelastupdate <= :toDate)) ORDER BY p.datecreation ASC"),
    @NamedQuery(name = "Plan.deleteByUuid", query = "DELETE FROM Plan p WHERE p.uuid = :uuid")
})
public class Plan implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "provider", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Relatedparty provider;
    @JoinColumn(name = "competentauthority", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Relatedparty competentauthority;
    @JoinColumn(name = "users", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Users users;
    @Basic(optional = false)
    @Column(name = "firstexceedanceyear_id")
    private String firstexceedanceyearId;
    @Basic(optional = false)
    @Column(name = "firstexceedanceyear_timeposition")
    private String firstexceedanceyearTimeposition;
    @Column(name = "adoptiondate_id")
    private String adoptiondateId;
    @Column(name = "adoptiondate_timeposition")
    private String adoptiondateTimeposition;
    @Basic(optional = false)
    @Column(name = "timetable")
    private String timetable;
    @Basic(optional = false)
    @Column(name = "referenceaqplan")
    private String referenceaqplan;
    @Basic(optional = false)
    @Column(name = "referenceimplementation")
    private String referenceimplementation;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "statusplan", referencedColumnName = "uuid")
    private Statusplan statusplan;
    @Column(name = "completed")
    private Boolean completed;
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
    @Basic(optional = false)
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "datelastupdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datelastupdate;
    @OneToMany(mappedBy = "plan")
    private List<Sourceapportionment> sourceapportionmentList;
    @OneToMany(mappedBy = "plan")
    private List<Evaluationscenario> evaluationscenarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plan")
    private List<PlanPollutantProtectiontarget> planPollutantProtectiontargetList;
    @OneToMany(mappedBy = "plan")
    private List<AttainmentPlan> attainmentPlanList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plan")
    private List<PlanPublication> planPublicationList;

    public Plan() {
    }

    public Plan(String uuid) {
        this.uuid = uuid;
    }

    public Plan(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, String code, String name, String firstexceedanceyearId, String firstexceedanceyearTimeposition, String adoptiondateId, String adoptiondateTimeposition, String timetable, String referenceaqplan, String referenceimplementation, String comment, Date datecreation) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
        this.code = code;
        this.name = name;
        this.firstexceedanceyearId = firstexceedanceyearId;
        this.firstexceedanceyearTimeposition = firstexceedanceyearTimeposition;
        this.adoptiondateId = adoptiondateId;
        this.adoptiondateTimeposition = adoptiondateTimeposition;
        this.timetable = timetable;
        this.referenceaqplan = referenceaqplan;
        this.referenceimplementation = referenceimplementation;
        this.comment = comment;
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

    public String getFirstexceedanceyearId() {
        return firstexceedanceyearId;
    }

    public void setFirstexceedanceyearId(String firstexceedanceyearId) {
        this.firstexceedanceyearId = firstexceedanceyearId;
    }

    public String getFirstexceedanceyearTimeposition() {
        return firstexceedanceyearTimeposition;
    }

    public void setFirstexceedanceyearTimeposition(String firstexceedanceyearTimeposition) {
        this.firstexceedanceyearTimeposition = firstexceedanceyearTimeposition;
    }

    public String getAdoptiondateId() {
        return adoptiondateId;
    }

    public void setAdoptiondateId(String adoptiondateId) {
        this.adoptiondateId = adoptiondateId;
    }

    public String getAdoptiondateTimeposition() {
        return adoptiondateTimeposition;
    }

    public void setAdoptiondateTimeposition(String adoptiondateTimeposition) {
        this.adoptiondateTimeposition = adoptiondateTimeposition;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getReferenceaqplan() {
        return referenceaqplan;
    }

    public void setReferenceaqplan(String referenceaqplan) {
        this.referenceaqplan = referenceaqplan;
    }

    public String getReferenceimplementation() {
        return referenceimplementation;
    }

    public void setReferenceimplementation(String referenceimplementation) {
        this.referenceimplementation = referenceimplementation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Statusplan getStatusplan() {
        return statusplan;
    }

    public void setStatusplan(Statusplan statusplan) {
        this.statusplan = statusplan;
    }

    public Relatedparty getCompetentauthority() {
        return competentauthority;
    }

    public void setCompetentauthority(Relatedparty competentauthority) {
        this.competentauthority = competentauthority;
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
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Plan[ uuid=" + uuid + " ]";
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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

    @XmlTransient
    public List<Sourceapportionment> getSourceapportionmentList() {
        return sourceapportionmentList;
    }

    public void setSourceapportionmentList(List<Sourceapportionment> sourceapportionmentList) {
        this.sourceapportionmentList = sourceapportionmentList;
    }

    @XmlTransient
    public List<Evaluationscenario> getEvaluationscenarioList() {
        return evaluationscenarioList;
    }

    public void setEvaluationscenarioList(List<Evaluationscenario> evaluationscenarioList) {
        this.evaluationscenarioList = evaluationscenarioList;
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

    @XmlTransient
    public List<PlanPollutantProtectiontarget> getPlanPollutantProtectiontargetList() {
        return planPollutantProtectiontargetList;
    }

    public void setPlanPollutantProtectiontargetList(List<PlanPollutantProtectiontarget> planPollutantProtectiontargetList) {
        this.planPollutantProtectiontargetList = planPollutantProtectiontargetList;
    }

    @XmlTransient
    public List<AttainmentPlan> getAttainmentPlanList() {
        return attainmentPlanList;
    }

    public void setAttainmentPlanList(List<AttainmentPlan> attainmentPlanList) {
        this.attainmentPlanList = attainmentPlanList;
    }

    @XmlTransient
    public List<PlanPublication> getPlanPublicationList() {
        return planPublicationList;
    }

    public void setPlanPublicationList(List<PlanPublication> planPublicationList) {
        this.planPublicationList = planPublicationList;
    }
}
