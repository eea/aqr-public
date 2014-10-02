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

import eu.europa.ec.aqrcrosstablesmodel.MeasuresAdministrationlevel;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresClassification;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresEvaluationscenario;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresScenario;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresSourcesector;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresSpatialscale;
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
@Table(name = "measures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measures.findAll", query = "SELECT m FROM Measures m"),
    @NamedQuery(name = "Measures.findByUuid", query = "SELECT m FROM Measures m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "Measures.findByInspireidLocalid", query = "SELECT m FROM Measures m WHERE m.inspireidLocalid = :inspireidLocalid"),
    @NamedQuery(name = "Measures.findByInspireidNamespace", query = "SELECT m FROM Measures m WHERE m.inspireidNamespace = :inspireidNamespace"),
    @NamedQuery(name = "Measures.findByInspireidVersionid", query = "SELECT m FROM Measures m WHERE m.inspireidVersionid = :inspireidVersionid"),
    @NamedQuery(name = "Measures.findByCode", query = "SELECT m FROM Measures m WHERE m.code = :code"),
    @NamedQuery(name = "Measures.findByDescription", query = "SELECT m FROM Measures m WHERE m.description = :description"),
    @NamedQuery(name = "Measures.findByReductionofemission", query = "SELECT m FROM Measures m WHERE m.reductionofemission = :reductionofemission"),
    @NamedQuery(name = "Measures.findByComment", query = "SELECT m FROM Measures m WHERE m.comment = :comment"),
    @NamedQuery(name = "Measures.findByDatecreation", query = "SELECT m FROM Measures m WHERE m.datecreation = :datecreation"),
    @NamedQuery(name = "Measures.findByDatelastupdate", query = "SELECT m FROM Measures m WHERE m.datelastupdate = :datelastupdate"),
    @NamedQuery(name = "Measures.findAllByUser", query = "SELECT p FROM Measures p WHERE p.users = :users"),
    @NamedQuery(name = "Measures.deleteByUuid", query = "DELETE FROM Measures m WHERE m.uuid = :uuid")
})
public class Measures implements Serializable {

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

    @Column(name = "completed")
    private Boolean completed;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @Column(name = "commentforclarification")
    private String commentforclarification;

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

    @Column(name = "reductionofemission")
    private String reductionofemission;
    @Column(name = "reductionofemission_nil")
    private Boolean reductionofemissionNil;
    @Column(name = "reductionofemission_nilreason")
    private String reductionofemissionNilreason;

    @JoinColumn(name = "quantificationnumerical", referencedColumnName = "uuid")
    private Quantificationnumerical quantificationnumerical;
    @Column(name = "comment")
    private String comment;

    @JoinColumn(name = "timescale", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Timescale timescale;

    @JoinColumn(name = "plannedimplementation", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Plannedimplementation plannedimplementation;

    @JoinColumn(name = "measuretype", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Measuretype measuretype;

    @JoinColumn(name = "expectedimpact", referencedColumnName = "uuid")
    @ManyToOne
    private Expectedimpact expectedimpact;

    @JoinColumn(name = "costs", referencedColumnName = "uuid")
    @ManyToOne
    private Costs costs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measures")
    private List<MeasuresScenario> measuresScenarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measures")
    private List<MeasuresEvaluationscenario> measuresEvaluationscenarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measures")
    private List<MeasuresSpatialscale> measuresSpatialscaleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measures")
    private List<SourceapportionmentMeasures> sourceapportionmentMeasuresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measures")
    private List<MeasuresSourcesector> measuresSourcesectorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measures")
    private List<MeasuresAdministrationlevel> measuresAdministrationlevelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measures")
    private List<MeasuresClassification> measuresClassificationList;
    @JoinColumn(name = "users", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Users users;

    public Measures() {
    }

    public Measures(String uuid) {
        this.uuid = uuid;
    }

    public Measures(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, String code, String name, String description, Date datecreation) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
        this.code = code;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measures)) {
            return false;
        }
        Measures other = (Measures) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Measures[ uuid=" + uuid + " ]";
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

    public Timescale getTimescale() {
        return timescale;
    }

    public void setTimescale(Timescale timescale) {
        this.timescale = timescale;
    }

    public Plannedimplementation getPlannedimplementation() {
        return plannedimplementation;
    }

    public void setPlannedimplementation(Plannedimplementation plannedimplementation) {
        this.plannedimplementation = plannedimplementation;
    }

    public Measuretype getMeasuretype() {
        return measuretype;
    }

    public void setMeasuretype(Measuretype measuretype) {
        this.measuretype = measuretype;
    }

    public Expectedimpact getExpectedimpact() {
        return expectedimpact;
    }

    public void setExpectedimpact(Expectedimpact expectedimpact) {
        this.expectedimpact = expectedimpact;
    }

    public Costs getCosts() {
        return costs;
    }

    public void setCosts(Costs costs) {
        this.costs = costs;
    }

    public String getReductionofemission() {
        return reductionofemission;
    }

    public void setReductionofemission(String reductionofemission) {
        this.reductionofemission = reductionofemission;
    }

    public Quantificationnumerical getQuantificationnumerical() {
        return quantificationnumerical;
    }

    public void setQuantificationnumerical(Quantificationnumerical quantificationnumerical) {
        this.quantificationnumerical = quantificationnumerical;
    }

    public String getCommentforclarification() {
        return commentforclarification;
    }

    public void setCommentforclarification(String commentforclarification) {
        this.commentforclarification = commentforclarification;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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
    public List<MeasuresScenario> getMeasuresScenarioList() {
        return measuresScenarioList;
    }

    public void setMeasuresScenarioList(List<MeasuresScenario> measuresScenarioList) {
        this.measuresScenarioList = measuresScenarioList;
    }

    @XmlTransient
    public List<MeasuresEvaluationscenario> getMeasuresEvaluationscenarioList() {
        return measuresEvaluationscenarioList;
    }

    public void setMeasuresEvaluationscenarioList(List<MeasuresEvaluationscenario> measuresEvaluationscenarioList) {
        this.measuresEvaluationscenarioList = measuresEvaluationscenarioList;
    }

    @XmlTransient
    public List<MeasuresSpatialscale> getMeasuresSpatialscaleList() {
        return measuresSpatialscaleList;
    }

    public void setMeasuresSpatialscaleList(List<MeasuresSpatialscale> measuresSpatialscaleList) {
        this.measuresSpatialscaleList = measuresSpatialscaleList;
    }

    @XmlTransient
    public List<SourceapportionmentMeasures> getSourceapportionmentMeasuresList() {
        return sourceapportionmentMeasuresList;
    }

    public void setSourceapportionmentMeasuresList(List<SourceapportionmentMeasures> sourceapportionmentMeasuresList) {
        this.sourceapportionmentMeasuresList = sourceapportionmentMeasuresList;
    }

    @XmlTransient
    public List<MeasuresSourcesector> getMeasuresSourcesectorList() {
        return measuresSourcesectorList;
    }

    public void setMeasuresSourcesectorList(List<MeasuresSourcesector> measuresSourcesectorList) {
        this.measuresSourcesectorList = measuresSourcesectorList;
    }

    @XmlTransient
    public List<MeasuresAdministrationlevel> getMeasuresAdministrationlevelList() {
        return measuresAdministrationlevelList;
    }

    public void setMeasuresAdministrationlevelList(List<MeasuresAdministrationlevel> measuresAdministrationlevelList) {
        this.measuresAdministrationlevelList = measuresAdministrationlevelList;
    }

    @XmlTransient
    public List<MeasuresClassification> getMeasuresClassificationList() {
        return measuresClassificationList;
    }

    public void setMeasuresClassificationList(List<MeasuresClassification> measuresClassificationList) {
        this.measuresClassificationList = measuresClassificationList;
    }

    public Boolean getReductionofemissionNil() {
        return reductionofemissionNil;
    }

    public void setReductionofemissionNil(Boolean reductionofemissionNil) {
        this.reductionofemissionNil = reductionofemissionNil;
    }

    public String getReductionofemissionNilreason() {
        return reductionofemissionNilreason;
    }

    public void setReductionofemissionNilreason(String reductionofemissionNilreason) {
        this.reductionofemissionNilreason = reductionofemissionNilreason;
    }

}
