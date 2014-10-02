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
@Table(name = "statusplan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statusplan.findAll", query = "SELECT s FROM Statusplan s"),
    @NamedQuery(name = "Statusplan.findByUuid", query = "SELECT s FROM Statusplan s WHERE s.uuid = :uuid"),
    @NamedQuery(name = "Statusplan.findByPreferredLabel", query = "SELECT s FROM Statusplan s WHERE s.preferredLabel = :preferredLabel"),
    @NamedQuery(name = "Statusplan.findByLink", query = "SELECT s FROM Statusplan s WHERE s.link = :link"),
    /**
     * custom query
     */
    @NamedQuery(name = "Statusplan.findByPreferredLabelAndLink", query = "SELECT s FROM Statusplan s WHERE s.preferredLabel = :preferredLabel AND s.link = :link")
})
public class Statusplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "preferred_label")
    private String preferredLabel;
    @Basic(optional = false)
    @Column(name = "link")
    private String link;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusplan")
    private List<Plan> planList;

    public Statusplan() {
    }

    public Statusplan(String uuid) {
        this.uuid = uuid;
    }

    public Statusplan(String uuid, String preferredLabel, String link) {
        this.uuid = uuid;
        this.preferredLabel = preferredLabel;
        this.link = link;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPreferredLabel() {
        return preferredLabel;
    }

    public void setPreferredLabel(String preferredLabel) {
        this.preferredLabel = preferredLabel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        if (!(object instanceof Statusplan)) {
            return false;
        }
        Statusplan other = (Statusplan) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Statusplan[ uuid=" + uuid + " ]";
    }

}
