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
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "exceedanceexposure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exceedanceexposure.findAll", query = "SELECT e FROM Exceedanceexposure e"),
    @NamedQuery(name = "Exceedanceexposure.findByUuid", query = "SELECT e FROM Exceedanceexposure e WHERE e.uuid = :uuid"),
    @NamedQuery(name = "Exceedanceexposure.findByExposedpopulation", query = "SELECT e FROM Exceedanceexposure e WHERE e.exposedpopulation = :exposedpopulation"),
    @NamedQuery(name = "Exceedanceexposure.deleteByUuid", query = "DELETE FROM Exceedanceexposure m WHERE m.uuid = :uuid")
})
public class Exceedanceexposure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "exposedpopulation")
    private String exposedpopulation;
    @Column(name = "exposedarea")
    private String exposedarea;
    @Column(name = "sensitiveresidentpopulation")
    private String sensitiveresidentpopulation;
    @Column(name = "relevantinfrastructure")
    private String relevantinfrastructure;
    @Column(name = "referenceyear")
    private String referenceyear;
    @OneToMany(mappedBy = "exceedanceexposure")
    private List<Exceedancedescription> exceedancedescriptionList;

    public Exceedanceexposure() {
    }

    public Exceedanceexposure(String uuid) {
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
        if (!(object instanceof Exceedanceexposure)) {
            return false;
        }
        Exceedanceexposure other = (Exceedanceexposure) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Exceedanceexposure[ uuid=" + uuid + " ]";
    }

    public String getExposedpopulation() {
        return exposedpopulation;
    }

    public void setExposedpopulation(String exposedpopulation) {
        this.exposedpopulation = exposedpopulation;
    }

    public String getExposedarea() {
        return exposedarea;
    }

    public void setExposedarea(String exposedarea) {
        this.exposedarea = exposedarea;
    }

    public String getSensitiveresidentpopulation() {
        return sensitiveresidentpopulation;
    }

    public void setSensitiveresidentpopulation(String sensitiveresidentpopulation) {
        this.sensitiveresidentpopulation = sensitiveresidentpopulation;
    }

    public String getRelevantinfrastructure() {
        return relevantinfrastructure;
    }

    public void setRelevantinfrastructure(String relevantinfrastructure) {
        this.relevantinfrastructure = relevantinfrastructure;
    }

    public String getReferenceyear() {
        return referenceyear;
    }

    public void setReferenceyear(String referenceyear) {
        this.referenceyear = referenceyear;
    }

    @XmlTransient
    public List<Exceedancedescription> getExceedancedescriptionList() {
        return exceedancedescriptionList;
    }

    public void setExceedancedescriptionList(List<Exceedancedescription> exceedancedescriptionList) {
        this.exceedancedescriptionList = exceedancedescriptionList;
    }

}
