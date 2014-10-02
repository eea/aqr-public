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
@Table(name = "attainment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attainment.findAll", query = "SELECT a FROM Attainment a"),
    @NamedQuery(name = "Attainment.findByUuid", query = "SELECT a FROM Attainment a WHERE a.uuid = :uuid"),
    @NamedQuery(name = "Attainment.findByInspireidLocalid", query = "SELECT a FROM Attainment a WHERE a.inspireidLocalid = :inspireidLocalid"),
    @NamedQuery(name = "Attainment.findByInspireidNamespace", query = "SELECT a FROM Attainment a WHERE a.inspireidNamespace = :inspireidNamespace"),
    @NamedQuery(name = "Attainment.findByInspireidVersionid", query = "SELECT a FROM Attainment a WHERE a.inspireidVersionid = :inspireidVersionid"),
    @NamedQuery(name = "Attainment.findByComment", query = "SELECT a FROM Attainment a WHERE a.comment = :comment"),
    @NamedQuery(name = "Attainment.findByValidityperiodId", query = "SELECT a FROM Attainment a WHERE a.validityperiodId = :validityperiodId"),
    @NamedQuery(name = "Attainment.findByValidityperiodBeginposition", query = "SELECT a FROM Attainment a WHERE a.validityperiodBeginposition = :validityperiodBeginposition"),
    @NamedQuery(name = "Attainment.findByValidityperiodEndposition", query = "SELECT a FROM Attainment a WHERE a.validityperiodEndposition = :validityperiodEndposition"),
    @NamedQuery(name = "Attainment.findAllByUser", query = "SELECT a FROM Attainment a WHERE a.users = :users"),
    @NamedQuery(name = "Attainment.findByDatecreation", query = "SELECT a FROM Attainment a WHERE a.datecreation = :datecreation"),
    @NamedQuery(name = "Attainment.findByDatelastupdate", query = "SELECT a FROM Attainment a WHERE a.datelastupdate = :datelastupdate"),
    @NamedQuery(name = "Attainment.deleteByUuid", query = "DELETE FROM Attainment a WHERE a.uuid = :uuid")
})
public class Attainment implements Serializable {

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

    @Column(name = "comment")
    private String comment;

    @Column(name = "validityperiod_id")
    private String validityperiodId;
    @Column(name = "validityperiod_beginposition")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validityperiodBeginposition;
    @Column(name = "validityperiod_endposition")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validityperiodEndposition;

    @Basic(optional = false)
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "datelastupdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datelastupdate;

    @OneToMany(mappedBy = "attainment")
    private List<Sourceapportionment> sourceapportionmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attainment")
    private List<AttainmentPlan> attainmentPlanList;
    @JoinColumn(name = "users", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "pollutant", referencedColumnName = "uuid")
    @ManyToOne
    private Pollutant pollutant;
    @JoinColumn(name = "environmentalobject", referencedColumnName = "uuid")
    @ManyToOne
    private Environmentalobjective environmentalobject;

    public Attainment() {
    }

    public Attainment(String uuid) {
        this.uuid = uuid;
    }

    public Attainment(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, Date datecreation) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getValidityperiodId() {
        return validityperiodId;
    }

    public void setValidityperiodId(String validityperiodId) {
        this.validityperiodId = validityperiodId;
    }

    public Date getValidityperiodBeginposition() {
        return validityperiodBeginposition;
    }

    public void setValidityperiodBeginposition(Date validityperiodBeginposition) {
        this.validityperiodBeginposition = validityperiodBeginposition;
    }

    public Date getValidityperiodEndposition() {
        return validityperiodEndposition;
    }

    public void setValidityperiodEndposition(Date validityperiodEndposition) {
        this.validityperiodEndposition = validityperiodEndposition;
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
        if (!(object instanceof Attainment)) {
            return false;
        }
        Attainment other = (Attainment) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Attainment[ uuid=" + uuid + " ]";
    }

    @XmlTransient
    public List<Sourceapportionment> getSourceapportionmentList() {
        return sourceapportionmentList;
    }

    public void setSourceapportionmentList(List<Sourceapportionment> sourceapportionmentList) {
        this.sourceapportionmentList = sourceapportionmentList;
    }

    @XmlTransient
    public List<AttainmentPlan> getAttainmentPlanList() {
        return attainmentPlanList;
    }

    public void setAttainmentPlanList(List<AttainmentPlan> attainmentPlanList) {
        this.attainmentPlanList = attainmentPlanList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Pollutant getPollutant() {
        return pollutant;
    }

    public void setPollutant(Pollutant pollutant) {
        this.pollutant = pollutant;
    }

    public Environmentalobjective getEnvironmentalobject() {
        return environmentalobject;
    }

    public void setEnvironmentalobject(Environmentalobjective environmentalobject) {
        this.environmentalobject = environmentalobject;
    }

}
