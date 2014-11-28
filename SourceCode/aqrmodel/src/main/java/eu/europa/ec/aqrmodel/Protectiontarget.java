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
@Table(name = "protectiontarget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Protectiontarget.findAll", query = "SELECT p FROM Protectiontarget p"),
    @NamedQuery(name = "Protectiontarget.findByUuid", query = "SELECT p FROM Protectiontarget p WHERE p.uuid = :uuid"),
    @NamedQuery(name = "Protectiontarget.findByUri", query = "SELECT p FROM Protectiontarget p WHERE p.uri = :uri"),
    @NamedQuery(name = "Protectiontarget.findByLabel", query = "SELECT p FROM Protectiontarget p WHERE p.label = :label"),
    @NamedQuery(name = "Protectiontarget.findByDefinition", query = "SELECT p FROM Protectiontarget p WHERE p.definition = :definition"),
    @NamedQuery(name = "Protectiontarget.findByNotation", query = "SELECT p FROM Protectiontarget p WHERE p.notation = :notation")})
public class Protectiontarget implements Serializable {

    @OneToMany(mappedBy = "protectiontarget")
    private List<Environmentalobjective> environmentalobjectiveList;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protectiontarget")
    private List<PollutantProtectiontarget> pollutantProtectiontargetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "protectiontarget")
    private List<PlanPollutantProtectiontarget> planPollutantProtectiontargetList;

    public Protectiontarget() {
    }

    public Protectiontarget(String uuid) {
        this.uuid = uuid;
    }

    public Protectiontarget(String uuid, String uri, String label) {
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
        if (!(object instanceof Protectiontarget)) {
            return false;
        }
        Protectiontarget other = (Protectiontarget) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Protectiontarget[ uuid=" + uuid + " ]";
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

    @XmlTransient
    public List<Environmentalobjective> getEnvironmentalobjectiveList() {
        return environmentalobjectiveList;
    }

    public void setEnvironmentalobjectiveList(List<Environmentalobjective> environmentalobjectiveList) {
        this.environmentalobjectiveList = environmentalobjectiveList;
    }
}
