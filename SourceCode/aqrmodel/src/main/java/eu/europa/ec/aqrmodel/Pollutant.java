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

import eu.europa.ec.aqrcrosstablesmodel.PlanPollutantProtectiontarget;
import eu.europa.ec.aqrcrosstablesmodel.PollutantProtectiontarget;
import java.io.Serializable;
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
@Table(name = "pollutant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pollutant.findAll", query = "SELECT p FROM Pollutant p"),
    @NamedQuery(name = "Pollutant.findAllOrderByID", query = "SELECT p FROM Pollutant p ORDER BY p.uuid ASC"),
    @NamedQuery(name = "Pollutant.findByUuid", query = "SELECT p FROM Pollutant p WHERE p.uuid = :uuid"),
    @NamedQuery(name = "Pollutant.findByUri", query = "SELECT p FROM Pollutant p WHERE p.uri = :uri"),
    @NamedQuery(name = "Pollutant.findByLabel", query = "SELECT p FROM Pollutant p WHERE p.label = :label"),
    @NamedQuery(name = "Pollutant.findByDefinition", query = "SELECT p FROM Pollutant p WHERE p.definition = :definition"),
    @NamedQuery(name = "Pollutant.findByNotation", query = "SELECT p FROM Pollutant p WHERE p.notation = :notation")})
public class Pollutant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "uri")
    private String uri;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "definition")
    private String definition;
    @Column(name = "notation")
    private String notation;
    @OneToMany(mappedBy = "pollutant")
    private List<Attainment> attainmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pollutant")
    private List<PollutantProtectiontarget> pollutantProtectiontargetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pollutant")
    private List<PlanPollutantProtectiontarget> planPollutantProtectiontargetList;

    public Pollutant() {
    }

    public Pollutant(String uuid) {
        this.uuid = uuid;
    }

    public Pollutant(String uuid, String uri, String label) {
        this.uuid = uuid;
        this.uri = uri;
        this.label = label;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
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
        if (!(object instanceof Pollutant)) {
            return false;
        }
        Pollutant other = (Pollutant) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Pollutant[ uuid=" + uuid + " ]";
    }

    @XmlTransient
    public List<Attainment> getAttainmentList() {
        return attainmentList;
    }

    public void setAttainmentList(List<Attainment> attainmentList) {
        this.attainmentList = attainmentList;
    }

    @XmlTransient
    public List<PollutantProtectiontarget> getPollutantProtectiontargetList() {
        return pollutantProtectiontargetList;
    }

    public void setPollutantProtectiontargetList(List<PollutantProtectiontarget> pollutantProtectiontargetList) {
        this.pollutantProtectiontargetList = pollutantProtectiontargetList;
    }

    @XmlTransient
    public List<PlanPollutantProtectiontarget> getPlanPollutantProtectiontargetList() {
        return planPollutantProtectiontargetList;
    }

    public void setPlanPollutantProtectiontargetList(List<PlanPollutantProtectiontarget> planPollutantProtectiontargetList) {
        this.planPollutantProtectiontargetList = planPollutantProtectiontargetList;
    }

}
