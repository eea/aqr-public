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

import eu.europa.ec.aqrmodeluser.Users;
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
@Table(name = "relatedparty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relatedparty.findAll", query = "SELECT r FROM Relatedparty r"),
    @NamedQuery(name = "Relatedparty.findByUuid", query = "SELECT r FROM Relatedparty r WHERE r.uuid = :uuid"),
    @NamedQuery(name = "Relatedparty.findByIndividualname", query = "SELECT r FROM Relatedparty r WHERE r.individualname = :individualname"),
    @NamedQuery(name = "Relatedparty.findByOrganisationname", query = "SELECT r FROM Relatedparty r WHERE r.organisationname = :organisationname"),
    @NamedQuery(name = "Relatedparty.findByAddress", query = "SELECT r FROM Relatedparty r WHERE r.address = :address"),
    @NamedQuery(name = "Relatedparty.findByElectronicmailaddress", query = "SELECT r FROM Relatedparty r WHERE r.electronicmailaddress = :electronicmailaddress"),
    @NamedQuery(name = "Relatedparty.findByTelephonevoice", query = "SELECT r FROM Relatedparty r WHERE r.telephonevoice = :telephonevoice"),
    @NamedQuery(name = "Relatedparty.findByWebsite", query = "SELECT r FROM Relatedparty r WHERE r.website = :website"),
    /**
     * Custom query
     */
    @NamedQuery(name = "Relatedparty.deleteByUuid", query = "DELETE FROM Relatedparty p WHERE p.uuid = :uuid")
})
public class Relatedparty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "individualname")
    private String individualname;
    @Basic(optional = false)
    @Column(name = "organisationname")
    private String organisationname;
    @Basic(optional = false)
    @Column(name = "website")
    private String website;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "electronicmailaddress")
    private String electronicmailaddress;
    @Basic(optional = false)
    @Column(name = "telephonevoice")
    private String telephonevoice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competentauthority")
    private List<Plan> planList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Users> usersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Sourceapportionment> sourceapportionmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Evaluationscenario> evaluationscenarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Measures> measuresList;

    public Relatedparty() {
    }

    public Relatedparty(String uuid) {
        this.uuid = uuid;
    }

    public Relatedparty(String uuid, String individualname) {
        this.uuid = uuid;
        this.individualname = individualname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIndividualname() {
        return individualname;
    }

    public void setIndividualname(String individualname) {
        this.individualname = individualname;
    }

    public String getOrganisationname() {
        return organisationname;
    }

    public void setOrganisationname(String organisationname) {
        this.organisationname = organisationname;
    }

    public String getElectronicmailaddress() {
        return electronicmailaddress;
    }

    public void setElectronicmailaddress(String electronicmailaddress) {
        this.electronicmailaddress = electronicmailaddress;
    }

    public String getTelephonevoice() {
        return telephonevoice;
    }

    public void setTelephonevoice(String telephonevoice) {
        this.telephonevoice = telephonevoice;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @XmlTransient
    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
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
        if (!(object instanceof Relatedparty)) {
            return false;
        }
        Relatedparty other = (Relatedparty) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Relatedparty[ uuid=" + uuid + " ]";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @XmlTransient
    public List<Measures> getMeasuresList() {
        return measuresList;
    }

    public void setMeasuresList(List<Measures> measuresList) {
        this.measuresList = measuresList;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
