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
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "expectedimpact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expectedimpact.findAll", query = "SELECT e FROM Expectedimpact e"),
    @NamedQuery(name = "Expectedimpact.findByUuid", query = "SELECT e FROM Expectedimpact e WHERE e.uuid = :uuid"),
    @NamedQuery(name = "Expectedimpact.findByLevelofconcentration", query = "SELECT e FROM Expectedimpact e WHERE e.levelofconcentration = :levelofconcentration"),
    @NamedQuery(name = "Expectedimpact.findByNumberofexceedence", query = "SELECT e FROM Expectedimpact e WHERE e.numberofexceedence = :numberofexceedence"),
    @NamedQuery(name = "Expectedimpact.findByComment", query = "SELECT e FROM Expectedimpact e WHERE e.comment = :comment"),
    @NamedQuery(name = "Expectedimpact.deleteByUuid", query = "DELETE FROM Expectedimpact e WHERE e.uuid = :uuid")
})
public class Expectedimpact implements Serializable {

    @OneToMany(mappedBy = "expectedimpact")
    private List<Measures> measuresList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "levelofconcentration")
    private String levelofconcentration;
    @Column(name = "numberofexceedence")
    private String numberofexceedence;
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "specificationofhours", referencedColumnName = "uuid")
    @ManyToOne
    private Specificationofhours specificationofhours;

    public Expectedimpact() {
    }

    public Expectedimpact(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNumberofexceedence() {
        return numberofexceedence;
    }

    public void setNumberofexceedence(String numberofexceedence) {
        this.numberofexceedence = numberofexceedence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLevelofconcentration() {
        return levelofconcentration;
    }

    public void setLevelofconcentration(String levelofconcentration) {
        this.levelofconcentration = levelofconcentration;
    }

    public Specificationofhours getSpecificationofhours() {
        return specificationofhours;
    }

    public void setSpecificationofhours(Specificationofhours specificationofhours) {
        this.specificationofhours = specificationofhours;
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
        if (!(object instanceof Expectedimpact)) {
            return false;
        }
        Expectedimpact other = (Expectedimpact) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Expectedimpact[ uuid=" + uuid + " ]";
    }

    @XmlTransient
    public List<Measures> getMeasuresList() {
        return measuresList;
    }

    public void setMeasuresList(List<Measures> measuresList) {
        this.measuresList = measuresList;
    }
}
