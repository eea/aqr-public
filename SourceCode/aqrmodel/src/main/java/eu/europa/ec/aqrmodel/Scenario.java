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

import eu.europa.ec.aqrcrosstablesmodel.MeasuresScenario;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "scenario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scenario.findAll", query = "SELECT s FROM Scenario s"),
    @NamedQuery(name = "Scenario.findByUuid", query = "SELECT s FROM Scenario s WHERE s.uuid = :uuid"),
    @NamedQuery(name = "Scenario.findByInspireidLocalid", query = "SELECT s FROM Scenario s WHERE s.inspireidLocalid = :inspireidLocalid"),
    @NamedQuery(name = "Scenario.findByInspireidNamespace", query = "SELECT s FROM Scenario s WHERE s.inspireidNamespace = :inspireidNamespace"),
    @NamedQuery(name = "Scenario.findByInspireidVersionid", query = "SELECT s FROM Scenario s WHERE s.inspireidVersionid = :inspireidVersionid"),
    @NamedQuery(name = "Scenario.findByDescription", query = "SELECT s FROM Scenario s WHERE s.description = :description"),
    @NamedQuery(name = "Scenario.findByTotalemissions", query = "SELECT s FROM Scenario s WHERE s.totalemissions = :totalemissions"),
    @NamedQuery(name = "Scenario.findByExpectedconcentration", query = "SELECT s FROM Scenario s WHERE s.expectedconcentration = :expectedconcentration"),
    @NamedQuery(name = "Scenario.findByExpectedexceedence", query = "SELECT s FROM Scenario s WHERE s.expectedexceedence = :expectedexceedence"),
    @NamedQuery(name = "Scenario.findByComment", query = "SELECT s FROM Scenario s WHERE s.comment = :comment"),
    @NamedQuery(name = "Scenario.deleteByUuid", query = "DELETE FROM Scenario m WHERE m.uuid = :uuid")
})
public class Scenario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scenario")
    private List<MeasuresScenario> measuresScenarioList;
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
    @Column(name = "description")
    private String description;
    @Column(name = "expectedexceedence")
    private String expectedexceedence;
    @Basic(optional = false)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "totalemissions")
    private String totalemissions;
    @Column(name = "expectedconcentration")
    private String expectedconcentration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baselinescenario")
    private List<Evaluationscenario> evaluationscenarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectionscenario")
    private List<Evaluationscenario> evaluationscenarioList1;

    public Scenario() {
    }

    public Scenario(String uuid) {
        this.uuid = uuid;
    }

    public Scenario(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, String description, String totalemissions, String comment) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
        this.description = description;
        this.totalemissions = totalemissions;
        this.comment = comment;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpectedexceedence() {
        return expectedexceedence;
    }

    public void setExpectedexceedence(String expectedexceedence) {
        this.expectedexceedence = expectedexceedence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        if (!(object instanceof Scenario)) {
            return false;
        }
        Scenario other = (Scenario) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Scenario[ uuid=" + uuid + " ]";
    }

    public String getTotalemissions() {
        return totalemissions;
    }

    public void setTotalemissions(String totalemissions) {
        this.totalemissions = totalemissions;
    }

    public String getExpectedconcentration() {
        return expectedconcentration;
    }

    public void setExpectedconcentration(String expectedconcentration) {
        this.expectedconcentration = expectedconcentration;
    }

    @XmlTransient
    public List<Evaluationscenario> getEvaluationscenarioList() {
        return evaluationscenarioList;
    }

    public void setEvaluationscenarioList(List<Evaluationscenario> evaluationscenarioList) {
        this.evaluationscenarioList = evaluationscenarioList;
    }

    @XmlTransient
    public List<Evaluationscenario> getEvaluationscenarioList1() {
        return evaluationscenarioList1;
    }

    public void setEvaluationscenarioList1(List<Evaluationscenario> evaluationscenarioList1) {
        this.evaluationscenarioList1 = evaluationscenarioList1;
    }

    @XmlTransient
    public List<MeasuresScenario> getMeasuresScenarioList() {
        return measuresScenarioList;
    }

    public void setMeasuresScenarioList(List<MeasuresScenario> measuresScenarioList) {
        this.measuresScenarioList = measuresScenarioList;
    }
}
