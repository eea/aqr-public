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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "exceedancedescription")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exceedancedescription.findAll", query = "SELECT e FROM Exceedancedescription e"),
    @NamedQuery(name = "Exceedancedescription.findByUuid", query = "SELECT e FROM Exceedancedescription e WHERE e.uuid = :uuid"),
    @NamedQuery(name = "Exceedancedescription.findByDatecreation", query = "SELECT e FROM Exceedancedescription e WHERE e.datecreation = :datecreation"),
    @NamedQuery(name = "Exceedancedescription.findByDatelastupdate", query = "SELECT e FROM Exceedancedescription e WHERE e.datelastupdate = :datelastupdate"),
    @NamedQuery(name = "Exceedancedescription.findByComment", query = "SELECT e FROM Exceedancedescription e WHERE e.comment = :comment"),
    @NamedQuery(name = "Exceedancedescription.deleteByUuid", query = "DELETE FROM Exceedancedescription m WHERE m.uuid = :uuid")
})
public class Exceedancedescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "exceedance")
    private Boolean exceedance;
    @Column(name = "numericalexceedance")
    private String numericalexceedance;
    @Column(name = "numberexceedances")
    private String numberexceedances;
    @Column(name = "otherreason")
    private String otherreason;
    @Basic(optional = false)
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "datelastupdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datelastupdate;
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "exceedanceexposure", referencedColumnName = "uuid")
    @ManyToOne
    private Exceedanceexposure exceedanceexposure;
    @JoinColumn(name = "exceedancearea", referencedColumnName = "uuid")
    @ManyToOne
    private Exceedancearea exceedancearea;
    @JoinColumn(name = "deductionassessmentmethod", referencedColumnName = "uuid")
    @ManyToOne
    private Deductionassessmentmethod deductionassessmentmethod;

    public Exceedancedescription() {
    }

    public Exceedancedescription(String uuid) {
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
        if (!(object instanceof Exceedancedescription)) {
            return false;
        }
        Exceedancedescription other = (Exceedancedescription) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Exceedancedescription[ uuid=" + uuid + " ]";
    }

    public Boolean getExceedance() {
        return exceedance;
    }

    public void setExceedance(Boolean exceedance) {
        this.exceedance = exceedance;
    }

    public String getNumericalexceedance() {
        return numericalexceedance;
    }

    public void setNumericalexceedance(String numericalexceedance) {
        this.numericalexceedance = numericalexceedance;
    }

    public String getNumberexceedances() {
        return numberexceedances;
    }

    public void setNumberexceedances(String numberexceedances) {
        this.numberexceedances = numberexceedances;
    }

    public String getOtherreason() {
        return otherreason;
    }

    public void setOtherreason(String otherreason) {
        this.otherreason = otherreason;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Exceedanceexposure getExceedanceexposure() {
        return exceedanceexposure;
    }

    public void setExceedanceexposure(Exceedanceexposure exceedanceexposure) {
        this.exceedanceexposure = exceedanceexposure;
    }

    public Exceedancearea getExceedancearea() {
        return exceedancearea;
    }

    public void setExceedancearea(Exceedancearea exceedancearea) {
        this.exceedancearea = exceedancearea;
    }

    public Deductionassessmentmethod getDeductionassessmentmethod() {
        return deductionassessmentmethod;
    }

    public void setDeductionassessmentmethod(Deductionassessmentmethod deductionassessmentmethod) {
        this.deductionassessmentmethod = deductionassessmentmethod;
    }
}
