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
@Table(name = "measuretype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measuretype.findAll", query = "SELECT m FROM Measuretype m"),
    @NamedQuery(name = "Measuretype.findByUuid", query = "SELECT m FROM Measuretype m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "Measuretype.findByUri", query = "SELECT m FROM Measuretype m WHERE m.uri = :uri"),
    @NamedQuery(name = "Measuretype.findByLabel", query = "SELECT m FROM Measuretype m WHERE m.label = :label"),
    @NamedQuery(name = "Measuretype.findByDefinition", query = "SELECT m FROM Measuretype m WHERE m.definition = :definition"),
    @NamedQuery(name = "Measuretype.findByNotation", query = "SELECT m FROM Measuretype m WHERE m.notation = :notation"),
    @NamedQuery(name = "Measuretype.deleteByUuid", query = "DELETE FROM Measuretype m WHERE m.uuid = :uuid")
})
public class Measuretype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "uri")
    private String uri;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "definition")
    private String definition;
    @Column(name = "notation")
    private String notation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measuretype")
    private List<Measures> measuresList;

    public Measuretype() {
    }

    public Measuretype(String uuid) {
        this.uuid = uuid;
    }

    public Measuretype(String uuid, String uri, String label) {
        this.uuid = uuid;
        this.uri = uri;
        this.label = label;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    @XmlTransient
    public List<Measures> getMeasuresList() {
        return measuresList;
    }

    public void setMeasuresList(List<Measures> measuresList) {
        this.measuresList = measuresList;
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
        if (!(object instanceof Measuretype)) {
            return false;
        }
        Measuretype other = (Measuretype) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Measuretype[ uuid=" + uuid + " ]";
    }
}
