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

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "plannedimplementation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plannedimplementation.findAll", query = "SELECT p FROM Plannedimplementation p"),
    @NamedQuery(name = "Plannedimplementation.findByUuid", query = "SELECT p FROM Plannedimplementation p WHERE p.uuid = :uuid"),
    @NamedQuery(name = "Plannedimplementation.findByImplementationplannedtimeperiodId", query = "SELECT p FROM Plannedimplementation p WHERE p.implementationplannedtimeperiodId = :implementationplannedtimeperiodId"),
    @NamedQuery(name = "Plannedimplementation.findByImplementationplannedtimeperiodBeginposition", query = "SELECT p FROM Plannedimplementation p WHERE p.implementationplannedtimeperiodBeginposition = :implementationplannedtimeperiodBeginposition"),
    @NamedQuery(name = "Plannedimplementation.findByImplementationplannedtimeperiodEndposition", query = "SELECT p FROM Plannedimplementation p WHERE p.implementationplannedtimeperiodEndposition = :implementationplannedtimeperiodEndposition"),
    @NamedQuery(name = "Plannedimplementation.findByImplementationactualtimeperiodId", query = "SELECT p FROM Plannedimplementation p WHERE p.implementationactualtimeperiodId = :implementationactualtimeperiodId"),
    @NamedQuery(name = "Plannedimplementation.findByImplementationactualtimeperiodBeginposition", query = "SELECT p FROM Plannedimplementation p WHERE p.implementationactualtimeperiodBeginposition = :implementationactualtimeperiodBeginposition"),
    @NamedQuery(name = "Plannedimplementation.findByImplementationactualtimeperiodEndposition", query = "SELECT p FROM Plannedimplementation p WHERE p.implementationactualtimeperiodEndposition = :implementationactualtimeperiodEndposition"),
    @NamedQuery(name = "Plannedimplementation.findByPlannedfulleffectdateId", query = "SELECT p FROM Plannedimplementation p WHERE p.plannedfulleffectdateId = :plannedfulleffectdateId"),
    @NamedQuery(name = "Plannedimplementation.findByPlannedfulleffectdateTimeposition", query = "SELECT p FROM Plannedimplementation p WHERE p.plannedfulleffectdateTimeposition = :plannedfulleffectdateTimeposition"),
    @NamedQuery(name = "Plannedimplementation.findByOtherdate", query = "SELECT p FROM Plannedimplementation p WHERE p.otherdate = :otherdate"),
    @NamedQuery(name = "Plannedimplementation.findByMonitoringprogressindicators", query = "SELECT p FROM Plannedimplementation p WHERE p.monitoringprogressindicators = :monitoringprogressindicators"),
    @NamedQuery(name = "Plannedimplementation.findByComment", query = "SELECT p FROM Plannedimplementation p WHERE p.comment = :comment"),
    @NamedQuery(name = "Plannedimplementation.deleteByUuid", query = "DELETE FROM Plannedimplementation p WHERE p.uuid = :uuid")
})
public class Plannedimplementation implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plannedimplementation")
    private List<Measures> measuresList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "implementationplannedtimeperiod_id")
    private String implementationplannedtimeperiodId;
    @Basic(optional = false)
    @Column(name = "implementationplannedtimeperiod_beginposition")
    private String implementationplannedtimeperiodBeginposition;
    @Basic(optional = false)
    @Column(name = "implementationplannedtimeperiod_endposition")
    private String implementationplannedtimeperiodEndposition;
    @Column(name = "implementationactualtimeperiod_id")
    private String implementationactualtimeperiodId;
    @Column(name = "implementationactualtimeperiod_beginposition")
    private String implementationactualtimeperiodBeginposition;
    @Column(name = "implementationactualtimeperiod_endposition")
    private String implementationactualtimeperiodEndposition;
    @Basic(optional = false)
    @Column(name = "plannedfulleffectdate_id")
    private String plannedfulleffectdateId;
    @Basic(optional = false)
    @Column(name = "plannedfulleffectdate_timeposition")
    private String plannedfulleffectdateTimeposition;
    @Column(name = "plannedfulleffectdate_timeposition_nil")
    private Boolean plannedfulleffectdateTimepositionNil;
    @Column(name = "plannedfulleffectdate_timeposition_nilreason")
    private String plannedfulleffectdateTimepositionNilreason;
    @Column(name = "otherdate")
    private String otherdate;
    @Basic(optional = false)
    @Column(name = "monitoringprogressindicators")
    private String monitoringprogressindicators;
    @Column(name = "monitoringprogressindicators_nil")
    private Boolean monitoringprogressindicatorsNil;
    @Column(name = "monitoringprogressindicators_nilreason")
    private String monitoringprogressindicatorsNilreason;
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "statusplannedimplementation", referencedColumnName = "uuid")
    @ManyToOne
    private Statusplannedimplementation statusplannedimplementation;

    public Plannedimplementation() {
    }

    public Plannedimplementation(String uuid) {
        this.uuid = uuid;
    }

    public Plannedimplementation(String uuid, String implementationplannedtimeperiodId, String implementationplannedtimeperiodBeginposition, String implementationplannedtimeperiodEndposition, String plannedfulleffectdateId, String plannedfulleffectdateTimeposition, String monitoringprogressindicators) {
        this.uuid = uuid;
        this.implementationplannedtimeperiodId = implementationplannedtimeperiodId;
        this.implementationplannedtimeperiodBeginposition = implementationplannedtimeperiodBeginposition;
        this.implementationplannedtimeperiodEndposition = implementationplannedtimeperiodEndposition;
        this.plannedfulleffectdateId = plannedfulleffectdateId;
        this.plannedfulleffectdateTimeposition = plannedfulleffectdateTimeposition;
        this.monitoringprogressindicators = monitoringprogressindicators;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImplementationplannedtimeperiodId() {
        return implementationplannedtimeperiodId;
    }

    public void setImplementationplannedtimeperiodId(String implementationplannedtimeperiodId) {
        this.implementationplannedtimeperiodId = implementationplannedtimeperiodId;
    }

    public String getImplementationplannedtimeperiodBeginposition() {
        return implementationplannedtimeperiodBeginposition;
    }

    public void setImplementationplannedtimeperiodBeginposition(String implementationplannedtimeperiodBeginposition) {
        this.implementationplannedtimeperiodBeginposition = implementationplannedtimeperiodBeginposition;
    }

    public String getImplementationplannedtimeperiodEndposition() {
        return implementationplannedtimeperiodEndposition;
    }

    public void setImplementationplannedtimeperiodEndposition(String implementationplannedtimeperiodEndposition) {
        this.implementationplannedtimeperiodEndposition = implementationplannedtimeperiodEndposition;
    }

    public String getImplementationactualtimeperiodId() {
        return implementationactualtimeperiodId;
    }

    public void setImplementationactualtimeperiodId(String implementationactualtimeperiodId) {
        this.implementationactualtimeperiodId = implementationactualtimeperiodId;
    }

    public String getImplementationactualtimeperiodBeginposition() {
        return implementationactualtimeperiodBeginposition;
    }

    public void setImplementationactualtimeperiodBeginposition(String implementationactualtimeperiodBeginposition) {
        this.implementationactualtimeperiodBeginposition = implementationactualtimeperiodBeginposition;
    }

    public String getImplementationactualtimeperiodEndposition() {
        return implementationactualtimeperiodEndposition;
    }

    public void setImplementationactualtimeperiodEndposition(String implementationactualtimeperiodEndposition) {
        this.implementationactualtimeperiodEndposition = implementationactualtimeperiodEndposition;
    }

    public String getPlannedfulleffectdateId() {
        return plannedfulleffectdateId;
    }

    public void setPlannedfulleffectdateId(String plannedfulleffectdateId) {
        this.plannedfulleffectdateId = plannedfulleffectdateId;
    }

    public String getPlannedfulleffectdateTimeposition() {
        return plannedfulleffectdateTimeposition;
    }

    public void setPlannedfulleffectdateTimeposition(String plannedfulleffectdateTimeposition) {
        this.plannedfulleffectdateTimeposition = plannedfulleffectdateTimeposition;
    }

    public String getOtherdate() {
        return otherdate;
    }

    public void setOtherdate(String otherdate) {
        this.otherdate = otherdate;
    }

    public String getMonitoringprogressindicators() {
        return monitoringprogressindicators;
    }

    public void setMonitoringprogressindicators(String monitoringprogressindicators) {
        this.monitoringprogressindicators = monitoringprogressindicators;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Statusplannedimplementation getStatusplannedimplementation() {
        return statusplannedimplementation;
    }

    public void setStatusplannedimplementation(Statusplannedimplementation statusplannedimplementation) {
        this.statusplannedimplementation = statusplannedimplementation;
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
        if (!(object instanceof Plannedimplementation)) {
            return false;
        }
        Plannedimplementation other = (Plannedimplementation) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Plannedimplementation[ uuid=" + uuid + " ]";
    }

    @XmlTransient
    public List<Measures> getMeasuresList() {
        return measuresList;
    }

    public void setMeasuresList(List<Measures> measuresList) {
        this.measuresList = measuresList;
    }

    public Boolean getPlannedfulleffectdateTimepositionNil() {
        return plannedfulleffectdateTimepositionNil;
    }

    public void setPlannedfulleffectdateTimepositionNil(Boolean plannedfulleffectdateTimepositionNil) {
        this.plannedfulleffectdateTimepositionNil = plannedfulleffectdateTimepositionNil;
    }

    public String getPlannedfulleffectdateTimepositionNilreason() {
        return plannedfulleffectdateTimepositionNilreason;
    }

    public void setPlannedfulleffectdateTimepositionNilreason(String plannedfulleffectdateTimepositionNilreason) {
        this.plannedfulleffectdateTimepositionNilreason = plannedfulleffectdateTimepositionNilreason;
    }

    public Boolean getMonitoringprogressindicatorsNil() {
        return monitoringprogressindicatorsNil;
    }

    public void setMonitoringprogressindicatorsNil(Boolean monitoringprogressindicatorsNil) {
        this.monitoringprogressindicatorsNil = monitoringprogressindicatorsNil;
    }

    public String getMonitoringprogressindicatorsNilreason() {
        return monitoringprogressindicatorsNilreason;
    }

    public void setMonitoringprogressindicatorsNilreason(String monitoringprogressindicatorsNilreason) {
        this.monitoringprogressindicatorsNilreason = monitoringprogressindicatorsNilreason;
    }
}
