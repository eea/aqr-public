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
@Table(name = "specificationofhours")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specificationofhours.findAll", query = "SELECT s FROM Specificationofhours s"),
    @NamedQuery(name = "Specificationofhours.findByUuid", query = "SELECT s FROM Specificationofhours s WHERE s.uuid = :uuid"),
    @NamedQuery(name = "Specificationofhours.findByUri", query = "SELECT s FROM Specificationofhours s WHERE s.uri = :uri"),
    @NamedQuery(name = "Specificationofhours.findByLabel", query = "SELECT s FROM Specificationofhours s WHERE s.label = :label"),
    @NamedQuery(name = "Specificationofhours.findByDefinition", query = "SELECT s FROM Specificationofhours s WHERE s.definition = :definition"),
    @NamedQuery(name = "Specificationofhours.findByNotation", query = "SELECT s FROM Specificationofhours s WHERE s.notation = :notation")})
public class Specificationofhours implements Serializable {

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
    @OneToMany(mappedBy = "specificationofhours")
    private List<Expectedimpact> expectedimpactList;

    public Specificationofhours() {
    }

    public Specificationofhours(String uuid) {
        this.uuid = uuid;
    }

    public Specificationofhours(String uuid, String uri, String label) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specificationofhours)) {
            return false;
        }
        Specificationofhours other = (Specificationofhours) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Specificationofhours[ uuid=" + uuid + " ]";
    }

    @XmlTransient
    public List<Expectedimpact> getExpectedimpactList() {
        return expectedimpactList;
    }

    public void setExpectedimpactList(List<Expectedimpact> expectedimpactList) {
        this.expectedimpactList = expectedimpactList;
    }
}
