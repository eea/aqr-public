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

import eu.europa.ec.aqrmodel.Plan;
import eu.europa.ec.aqrmodel.Publication;
import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "plan_publication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanPublication.findAll", query = "SELECT p FROM PlanPublication p"),
    @NamedQuery(name = "PlanPublication.findByUuid", query = "SELECT p FROM PlanPublication p WHERE p.uuid = :uuid"),
    @NamedQuery(name = "PlanPublication.findByPlan", query = "SELECT p FROM PlanPublication p WHERE p.plan = :plan"),
    @NamedQuery(name = "PlanPublication.findByPublication", query = "SELECT p FROM PlanPublication p WHERE p.publication = :publication"),
    /**
     * Custom query
     */
    @NamedQuery(name = "PlanPublication.deleteByPublication", query = "DELETE FROM PlanPublication p WHERE p.publication = :publication"),
    @NamedQuery(name = "PlanPublication.deleteByPlan", query = "DELETE FROM PlanPublication p WHERE p.plan = :plan"),
    @NamedQuery(name = "PlanPublication.deleteByPlanAndPublication", query = "DELETE FROM PlanPublication p WHERE p.plan = :plan AND p.publication = :publication")
})
public class PlanPublication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @JoinColumn(name = "publication", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Publication publication;
    @JoinColumn(name = "plan", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Plan plan;

    public PlanPublication() {
    }

    public PlanPublication(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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
        if (!(object instanceof PlanPublication)) {
            return false;
        }
        PlanPublication other = (PlanPublication) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrcrosstablesmodel.PlanPublication[ uuid=" + uuid + " ]";
    }
}
