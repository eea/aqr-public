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

import eu.europa.ec.aqrcrosstablesmodel.EvaluationscenarioPublication;
import eu.europa.ec.aqrcrosstablesmodel.PlanPublication;
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
@Table(name = "publication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publication.findAll", query = "SELECT p FROM Publication p"),
    @NamedQuery(name = "Publication.findByUuid", query = "SELECT p FROM Publication p WHERE p.uuid = :uuid"),
    @NamedQuery(name = "Publication.findByTitle", query = "SELECT p FROM Publication p WHERE p.title = :title"),
    @NamedQuery(name = "Publication.findByDescription", query = "SELECT p FROM Publication p WHERE p.description = :description"),
    @NamedQuery(name = "Publication.findByAuthor", query = "SELECT p FROM Publication p WHERE p.author = :author"),
    @NamedQuery(name = "Publication.findByPublicationdateId", query = "SELECT p FROM Publication p WHERE p.publicationdateId = :publicationdateId"),
    @NamedQuery(name = "Publication.findByPublicationdateTimeposition", query = "SELECT p FROM Publication p WHERE p.publicationdateTimeposition = :publicationdateTimeposition"),
    @NamedQuery(name = "Publication.findByPublisher", query = "SELECT p FROM Publication p WHERE p.publisher = :publisher"),
    @NamedQuery(name = "Publication.findByWeblink", query = "SELECT p FROM Publication p WHERE p.weblink = :weblink"),
    /**
     * Custom query
     */
    @NamedQuery(name = "Publication.deleteByUuid", query = "DELETE FROM Publication p WHERE p.uuid = :uuid")
})
public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @Column(name = "publicationdate_id")
    private String publicationdateId;
    @Basic(optional = false)
    @Column(name = "publicationdate_timeposition")
    private String publicationdateTimeposition;
    @Basic(optional = false)
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "weblink")
    private String weblink;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publication")
    private List<EvaluationscenarioPublication> evaluationscenarioPublicationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publication")
    private List<PlanPublication> planPublicationList;

    public Publication() {
    }

    public Publication(String uuid) {
        this.uuid = uuid;
    }

    public Publication(String uuid, String title, String description, String author, String publicationdateId, String publicationdateTimeposition, String publisher) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.author = author;
        this.publicationdateId = publicationdateId;
        this.publicationdateTimeposition = publicationdateTimeposition;
        this.publisher = publisher;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationdateId() {
        return publicationdateId;
    }

    public void setPublicationdateId(String publicationdateId) {
        this.publicationdateId = publicationdateId;
    }

    public String getPublicationdateTimeposition() {
        return publicationdateTimeposition;
    }

    public void setPublicationdateTimeposition(String publicationdateTimeposition) {
        this.publicationdateTimeposition = publicationdateTimeposition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
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
        if (!(object instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Publication[ uuid=" + uuid + " ]";
    }

    @XmlTransient
    public List<EvaluationscenarioPublication> getEvaluationscenarioPublicationList() {
        return evaluationscenarioPublicationList;
    }

    public void setEvaluationscenarioPublicationList(List<EvaluationscenarioPublication> evaluationscenarioPublicationList) {
        this.evaluationscenarioPublicationList = evaluationscenarioPublicationList;
    }

    @XmlTransient
    public List<PlanPublication> getPlanPublicationList() {
        return planPublicationList;
    }

    public void setPlanPublicationList(List<PlanPublication> planPublicationList) {
        this.planPublicationList = planPublicationList;
    }

}
