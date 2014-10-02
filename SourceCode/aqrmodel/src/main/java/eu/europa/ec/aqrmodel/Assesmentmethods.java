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
@Table(name = "assesmentmethods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assesmentmethods.findAll", query = "SELECT a FROM Assesmentmethods a"),
    @NamedQuery(name = "Assesmentmethods.findByUuid", query = "SELECT a FROM Assesmentmethods a WHERE a.uuid = :uuid"),
    @NamedQuery(name = "Assesmentmethods.findByMetadata", query = "SELECT a FROM Assesmentmethods a WHERE a.metadata = :metadata"),
    @NamedQuery(name = "Assesmentmethods.findByAssesmenttypedescription", query = "SELECT a FROM Assesmentmethods a WHERE a.assesmenttypedescription = :assesmenttypedescription"),
    @NamedQuery(name = "Assesmentmethods.deleteByUuid", query = "DELETE FROM Assesmentmethods m WHERE m.uuid = :uuid")
})
public class Assesmentmethods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "assesmenttypedescription")
    private String assesmenttypedescription;
    @Column(name = "metadata")
    private String metadata;
    @JoinColumn(name = "assesmenttype", referencedColumnName = "uuid")
    @ManyToOne
    private Assesmenttype assesmenttype;

    public Assesmentmethods() {
    }

    public Assesmentmethods(String uuid) {
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
        if (!(object instanceof Assesmentmethods)) {
            return false;
        }
        Assesmentmethods other = (Assesmentmethods) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Assesmentmethods[ uuid=" + uuid + " ]";
    }

    public String getAssesmenttypedescription() {
        return assesmenttypedescription;
    }

    public void setAssesmenttypedescription(String assesmenttypedescription) {
        this.assesmenttypedescription = assesmenttypedescription;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Assesmenttype getAssesmenttype() {
        return assesmenttype;
    }

    public void setAssesmenttype(Assesmenttype assesmenttype) {
        this.assesmenttype = assesmenttype;
    }

}
