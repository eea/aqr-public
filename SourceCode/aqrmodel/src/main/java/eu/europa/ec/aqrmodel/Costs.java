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
@Table(name = "costs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Costs.findAll", query = "SELECT c FROM Costs c"),
    @NamedQuery(name = "Costs.findByUuid", query = "SELECT c FROM Costs c WHERE c.uuid = :uuid"),
    @NamedQuery(name = "Costs.findByExtimatedimplementationcosts", query = "SELECT c FROM Costs c WHERE c.extimatedimplementationcosts = :extimatedimplementationcosts"),
    @NamedQuery(name = "Costs.findByFinalimplementationcosts", query = "SELECT c FROM Costs c WHERE c.finalimplementationcosts = :finalimplementationcosts"),
    @NamedQuery(name = "Costs.findByComment", query = "SELECT c FROM Costs c WHERE c.comment = :comment"),
    @NamedQuery(name = "Costs.deleteByUuid", query = "DELETE FROM Costs c WHERE c.uuid = :uuid")
})
public class Costs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "extimatedimplementationcosts")
    private String extimatedimplementationcosts;
    @Column(name = "extimatedimplementationcosts_nil")
    private Boolean extimatedimplementationcostsNil;
    @Column(name = "extimatedimplementationcosts_nilreason")
    private String extimatedimplementationcostsNilreason;
    @Column(name = "finalimplementationcosts")
    private String finalimplementationcosts;
    @Column(name = "comment")
    private String comment;
    @JoinColumn(name = "currency", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Currency currency;
    @OneToMany(mappedBy = "costs")
    private List<Measures> measuresList;

    public Costs() {
    }

    public Costs(String uuid) {
        this.uuid = uuid;
    }

    public Costs(String uuid, String extimatedimplementationcosts) {
        this.uuid = uuid;
        this.extimatedimplementationcosts = extimatedimplementationcosts;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFinalimplementationcosts() {
        return finalimplementationcosts;
    }

    public void setFinalimplementationcosts(String finalimplementationcosts) {
        this.finalimplementationcosts = finalimplementationcosts;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
        if (!(object instanceof Costs)) {
            return false;
        }
        Costs other = (Costs) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Costs[ uuid=" + uuid + " ]";
    }

    @XmlTransient
    public List<Measures> getMeasuresList() {
        return measuresList;
    }

    public void setMeasuresList(List<Measures> measuresList) {
        this.measuresList = measuresList;
    }

    public String getExtimatedimplementationcosts() {
        return extimatedimplementationcosts;
    }

    public void setExtimatedimplementationcosts(String extimatedimplementationcosts) {
        this.extimatedimplementationcosts = extimatedimplementationcosts;
    }

    public Boolean getExtimatedimplementationcostsNil() {
        return extimatedimplementationcostsNil;
    }

    public void setExtimatedimplementationcostsNil(Boolean extimatedimplementationcostsNil) {
        this.extimatedimplementationcostsNil = extimatedimplementationcostsNil;
    }

    public String getExtimatedimplementationcostsNilreason() {
        return extimatedimplementationcostsNilreason;
    }

    public void setExtimatedimplementationcostsNilreason(String extimatedimplementationcostsNilreason) {
        this.extimatedimplementationcostsNilreason = extimatedimplementationcostsNilreason;
    }
}
