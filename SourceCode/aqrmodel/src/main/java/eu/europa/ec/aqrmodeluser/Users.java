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
package eu.europa.ec.aqrmodeluser;

import eu.europa.ec.aqrmodel.Attainment;
import eu.europa.ec.aqrmodel.Evaluationscenario;
import eu.europa.ec.aqrmodel.Measures;
import eu.europa.ec.aqrmodel.Plan;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodel.Sourceapportionment;
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUuid", query = "SELECT u FROM Users u WHERE u.uuid = :uuid"),
    @NamedQuery(name = "Users.findByLastmodified", query = "SELECT u FROM Users u WHERE u.lastmodified = :lastmodified"),
    @NamedQuery(name = "Users.findByDatecreation", query = "SELECT u FROM Users u WHERE u.datecreation = :datecreation"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByCountry", query = "SELECT u FROM Users u WHERE u.country = :country"),
    @NamedQuery(name = "Users.findByUserrole", query = "SELECT u FROM Users u WHERE u.userrole = :userrole"),
    @NamedQuery(name = "Users.findByCountryAndUserrole", query = "SELECT u FROM Users u WHERE u.country = :country AND u.userrole = :userrole")
})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;

    @JoinColumn(name = "userrole", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Userrole userrole;
    @JoinColumn(name = "provider", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Relatedparty provider;
    @JoinColumn(name = "country", referencedColumnName = "uuid")
    @ManyToOne
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Sourceapportionment> sourceapportionmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Attainment> attainmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Evaluationscenario> evaluationscenarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Plan> planList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Measures> measuresList;

    public Users() {
    }

    public Users(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodeluser.Users[ uuid=" + uuid + " ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public Userrole getUserrole() {
        return userrole;
    }

    public void setUserrole(Userrole userrole) {
        this.userrole = userrole;
    }

    public Relatedparty getProvider() {
        return provider;
    }

    public void setProvider(Relatedparty provider) {
        this.provider = provider;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @XmlTransient
    public List<Sourceapportionment> getSourceapportionmentList() {
        return sourceapportionmentList;
    }

    public void setSourceapportionmentList(List<Sourceapportionment> sourceapportionmentList) {
        this.sourceapportionmentList = sourceapportionmentList;
    }

    @XmlTransient
    public List<Attainment> getAttainmentList() {
        return attainmentList;
    }

    public void setAttainmentList(List<Attainment> attainmentList) {
        this.attainmentList = attainmentList;
    }

    @XmlTransient
    public List<Evaluationscenario> getEvaluationscenarioList() {
        return evaluationscenarioList;
    }

    public void setEvaluationscenarioList(List<Evaluationscenario> evaluationscenarioList) {
        this.evaluationscenarioList = evaluationscenarioList;
    }

    @XmlTransient
    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    @XmlTransient
    public List<Measures> getMeasuresList() {
        return measuresList;
    }

    public void setMeasuresList(List<Measures> measuresList) {
        this.measuresList = measuresList;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

}
