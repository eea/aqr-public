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
@Table(name = "assesmenttype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assesmenttype.findAll", query = "SELECT a FROM Assesmenttype a"),
    @NamedQuery(name = "Assesmenttype.findByUuid", query = "SELECT a FROM Assesmenttype a WHERE a.uuid = :uuid"),
    @NamedQuery(name = "Assesmenttype.findByUri", query = "SELECT a FROM Assesmenttype a WHERE a.uri = :uri"),
    @NamedQuery(name = "Assesmenttype.findByLabel", query = "SELECT a FROM Assesmenttype a WHERE a.label = :label"),
    @NamedQuery(name = "Assesmenttype.findByDefinition", query = "SELECT a FROM Assesmenttype a WHERE a.definition = :definition"),
    @NamedQuery(name = "Assesmenttype.findByNotation", query = "SELECT a FROM Assesmenttype a WHERE a.notation = :notation")})
public class Assesmenttype implements Serializable {

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
    @OneToMany(mappedBy = "assesmenttype")
    private List<Assesmentmethods> assesmentmethodsList;

    public Assesmenttype() {
    }

    public Assesmenttype(String uuid) {
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
        if (!(object instanceof Assesmenttype)) {
            return false;
        }
        Assesmenttype other = (Assesmenttype) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Assesmenttype[ uuid=" + uuid + " ]";
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
    public List<Assesmentmethods> getAssesmentmethodsList() {
        return assesmentmethodsList;
    }

    public void setAssesmentmethodsList(List<Assesmentmethods> assesmentmethodsList) {
        this.assesmentmethodsList = assesmentmethodsList;
    }

}
