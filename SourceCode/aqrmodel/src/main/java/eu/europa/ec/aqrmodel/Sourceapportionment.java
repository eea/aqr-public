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

import eu.europa.ec.aqrcrosstablesmodel.SourceapportionmentMeasures;
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
@Table(name = "sourceapportionment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sourceapportionment.findAll", query = "SELECT s FROM Sourceapportionment s"),
    @NamedQuery(name = "Sourceapportionment.findByUuid", query = "SELECT s FROM Sourceapportionment s WHERE s.uuid = :uuid"),
    @NamedQuery(name = "Sourceapportionment.findByInspireidLocalid", query = "SELECT s FROM Sourceapportionment s WHERE s.inspireidLocalid = :inspireidLocalid"),
    @NamedQuery(name = "Sourceapportionment.findByInspireidNamespace", query = "SELECT s FROM Sourceapportionment s WHERE s.inspireidNamespace = :inspireidNamespace"),
    @NamedQuery(name = "Sourceapportionment.findByInspireidVersionid", query = "SELECT s FROM Sourceapportionment s WHERE s.inspireidVersionid = :inspireidVersionid"),
    @NamedQuery(name = "Sourceapportionment.findByReferenceyearId", query = "SELECT s FROM Sourceapportionment s WHERE s.referenceyearId = :referenceyearId"),
    @NamedQuery(name = "Sourceapportionment.findByReferenceyearTimeperiod", query = "SELECT s FROM Sourceapportionment s WHERE s.referenceyearTimeperiod = :referenceyearTimeperiod"),
    @NamedQuery(name = "Sourceapportionment.findByComment", query = "SELECT s FROM Sourceapportionment s WHERE s.comment = :comment"),
    @NamedQuery(name = "Sourceapportionment.findAllByUser", query = "SELECT s FROM Sourceapportionment s WHERE s.users = :users"),
    @NamedQuery(name = "Sourceapportionment.findByCompletedAndUser", query = "SELECT s FROM Sourceapportionment s WHERE s.completed = :completed AND s.users = :users"),
    @NamedQuery(name = "Sourceapportionment.findByDatecreation", query = "SELECT s FROM Sourceapportionment s WHERE s.datecreation = :datecreation"),
    @NamedQuery(name = "Sourceapportionment.findByDatelastupdate", query = "SELECT s FROM Sourceapportionment s WHERE s.datelastupdate = :datelastupdate"),
    @NamedQuery(name = "Sourceapportionment.findAllByUser", query = "SELECT s FROM Sourceapportionment s WHERE s.users = :users"),
    @NamedQuery(name = "Sourceapportionment.deleteByUuid", query = "DELETE FROM Sourceapportionment s WHERE s.uuid = :uuid")
})
public class Sourceapportionment implements Serializable {

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

    @Column(name = "referenceyear_id")
    private String referenceyearId;
    @Column(name = "referenceyear_timeperiod")
    private String referenceyearTimeperiod;

    @Column(name = "comment")
    private String comment;
    @Column(name = "completed")
    private Boolean completed;

    @JoinColumn(name = "users", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Users users;

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

    @JoinColumn(name = "urbanbackground", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Urbanbackground urbanbackground;
    @JoinColumn(name = "regionalbackground", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Regionalbackground regionalbackground;
    @JoinColumn(name = "plan", referencedColumnName = "uuid")
    @ManyToOne
    private Plan plan;
    @JoinColumn(name = "localincrement", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Localincrement localincrement;
    @JoinColumn(name = "exceedancedescription", referencedColumnName = "uuid")
    @ManyToOne
    private Exceedancedescription exceedancedescription;
    @JoinColumn(name = "attainment", referencedColumnName = "uuid")
    @ManyToOne
    private Attainment attainment;
    @OneToMany(mappedBy = "sourceapportionment")
    private List<Evaluationscenario> evaluationscenarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceapportionment")
    private List<SourceapportionmentMeasures> sourceapportionmentMeasuresList;

    public Sourceapportionment() {
    }

    public Sourceapportionment(String uuid) {
        this.uuid = uuid;
    }

    public Sourceapportionment(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, Date datecreation) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sourceapportionment)) {
            return false;
        }
        Sourceapportionment other = (Sourceapportionment) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Sourceapportionment[ uuid=" + uuid + " ]";
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

    public Urbanbackground getUrbanbackground() {
        return urbanbackground;
    }

    public void setUrbanbackground(Urbanbackground urbanbackground) {
        this.urbanbackground = urbanbackground;
    }

    public Regionalbackground getRegionalbackground() {
        return regionalbackground;
    }

    public void setRegionalbackground(Regionalbackground regionalbackground) {
        this.regionalbackground = regionalbackground;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Localincrement getLocalincrement() {
        return localincrement;
    }

    public void setLocalincrement(Localincrement localincrement) {
        this.localincrement = localincrement;
    }

    public Exceedancedescription getExceedancedescription() {
        return exceedancedescription;
    }

    public void setExceedancedescription(Exceedancedescription exceedancedescription) {
        this.exceedancedescription = exceedancedescription;
    }

    public Attainment getAttainment() {
        return attainment;
    }

    public void setAttainment(Attainment attainment) {
        this.attainment = attainment;
    }

    @XmlTransient
    public List<Evaluationscenario> getEvaluationscenarioList() {
        return evaluationscenarioList;
    }

    public void setEvaluationscenarioList(List<Evaluationscenario> evaluationscenarioList) {
        this.evaluationscenarioList = evaluationscenarioList;
    }

    @XmlTransient
    public List<SourceapportionmentMeasures> getSourceapportionmentMeasuresList() {
        return sourceapportionmentMeasuresList;
    }

    public void setSourceapportionmentMeasuresList(List<SourceapportionmentMeasures> sourceapportionmentMeasuresList) {
        this.sourceapportionmentMeasuresList = sourceapportionmentMeasuresList;
    }

}
