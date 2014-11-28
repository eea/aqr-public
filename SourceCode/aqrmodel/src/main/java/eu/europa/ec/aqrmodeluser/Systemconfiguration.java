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
@Table(name = "systemconfiguration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Systemconfiguration.findAll", query = "SELECT s FROM Systemconfiguration s"),
    @NamedQuery(name = "Systemconfiguration.findByUuid", query = "SELECT s FROM Systemconfiguration s WHERE s.uuid = :uuid"),
    @NamedQuery(name = "Systemconfiguration.findByNamespace", query = "SELECT s FROM Systemconfiguration s WHERE s.namespace = :namespace"),
    @NamedQuery(name = "Systemconfiguration.findByCountry", query = "SELECT s FROM Systemconfiguration s WHERE s.country = :country")
})
public class Systemconfiguration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "namespace")
    private String namespace;
    @JoinColumn(name = "country", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Country country;

    public Systemconfiguration() {
    }

    public Systemconfiguration(String uuid) {
        this.uuid = uuid;
    }

    public Systemconfiguration(String uuid, String namespace) {
        this.uuid = uuid;
        this.namespace = namespace;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
        if (!(object instanceof Systemconfiguration)) {
            return false;
        }
        Systemconfiguration other = (Systemconfiguration) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodeluser.Systemconfiguration[ uuid=" + uuid + " ]";
    }
}
