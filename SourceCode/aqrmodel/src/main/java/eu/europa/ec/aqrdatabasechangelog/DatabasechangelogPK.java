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
 *  Date: 25/02/2014
 *  Authors: European Commission, Joint Research Centre
 *  Lucasz Cyra, Emanuela Epure, Daniele Francioli
 *  aq-dev@jrc.ec.europa.eu
 */
package eu.europa.ec.aqrdatabasechangelog;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DatabasechangelogPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @Column(name = "filename")
    private String filename;

    public DatabasechangelogPK() {
    }

    public DatabasechangelogPK(String id, String author, String filename) {
        this.id = id;
        this.author = author;
        this.filename = filename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        hash += (author != null ? author.hashCode() : 0);
        hash += (filename != null ? filename.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatabasechangelogPK)) {
            return false;
        }
        DatabasechangelogPK other = (DatabasechangelogPK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if ((this.author == null && other.author != null) || (this.author != null && !this.author.equals(other.author))) {
            return false;
        }
        if ((this.filename == null && other.filename != null) || (this.filename != null && !this.filename.equals(other.filename))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.DatabasechangelogPK[ id=" + id + ", author=" + author + ", filename=" + filename + " ]";
    }

}
