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
package eu.europa.ec.aqrcrosstablesmodel;

import eu.europa.ec.aqrmodel.Assesmentmethods;
import eu.europa.ec.aqrmodel.Deductionassessmentmethod;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "deductionassessmentmethod_assesmentmethods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeductionassessmentmethodAssesmentmethods.findAll", query = "SELECT d FROM DeductionassessmentmethodAssesmentmethods d"),
    @NamedQuery(name = "DeductionassessmentmethodAssesmentmethods.findByUuid", query = "SELECT d FROM DeductionassessmentmethodAssesmentmethods d WHERE d.uuid = :uuid"),
    @NamedQuery(name = "DeductionassessmentmethodAssesmentmethods.findByDeductionassessmentmethod", query = "SELECT d FROM DeductionassessmentmethodAssesmentmethods d WHERE d.deductionassessmentmethod = :deductionassessmentmethod"),
    @NamedQuery(name = "DeductionassessmentmethodAssesmentmethods.findByAssesmentmethods", query = "SELECT d FROM DeductionassessmentmethodAssesmentmethods d WHERE d.assesmentmethods = :assesmentmethods"),
    @NamedQuery(name = "DeductionassessmentmethodAssesmentmethods.deleteByAssesmentmethods", query = "DELETE FROM DeductionassessmentmethodAssesmentmethods m WHERE m.assesmentmethods = :assesmentmethods"),
    @NamedQuery(name = "DeductionassessmentmethodAssesmentmethods.deleteByDeductionassessmentmethod", query = "DELETE FROM DeductionassessmentmethodAssesmentmethods a WHERE a.deductionassessmentmethod = :deductionassessmentmethod")
})
public class DeductionassessmentmethodAssesmentmethods implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    @JoinColumn(name = "deductionassessmentmethod", referencedColumnName = "uuid")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Deductionassessmentmethod deductionassessmentmethod;

    @JoinColumn(name = "assesmentmethods", referencedColumnName = "uuid")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Assesmentmethods assesmentmethods;

    public DeductionassessmentmethodAssesmentmethods() {
    }

    public DeductionassessmentmethodAssesmentmethods(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Deductionassessmentmethod getDeductionassessmentmethod() {
        return deductionassessmentmethod;
    }

    public void setDeductionassessmentmethod(Deductionassessmentmethod deductionassessmentmethod) {
        this.deductionassessmentmethod = deductionassessmentmethod;
    }

    public Assesmentmethods getAssesmentmethods() {
        return assesmentmethods;
    }

    public void setAssesmentmethods(Assesmentmethods assesmentmethods) {
        this.assesmentmethods = assesmentmethods;
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
        if (!(object instanceof DeductionassessmentmethodAssesmentmethods)) {
            return false;
        }
        DeductionassessmentmethodAssesmentmethods other = (DeductionassessmentmethodAssesmentmethods) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.DeductionassessmentmethodAssesmentmethods[ uuid=" + uuid + " ]";
    }

}
