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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "databasechangelog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Databasechangelog.findAll", query = "SELECT d FROM Databasechangelog d"),
    @NamedQuery(name = "Databasechangelog.findById", query = "SELECT d FROM Databasechangelog d WHERE d.databasechangelogPK.id = :id"),
    @NamedQuery(name = "Databasechangelog.findByAuthor", query = "SELECT d FROM Databasechangelog d WHERE d.databasechangelogPK.author = :author"),
    @NamedQuery(name = "Databasechangelog.findByFilename", query = "SELECT d FROM Databasechangelog d WHERE d.databasechangelogPK.filename = :filename"),
    @NamedQuery(name = "Databasechangelog.findByDateexecuted", query = "SELECT d FROM Databasechangelog d WHERE d.dateexecuted = :dateexecuted"),
    @NamedQuery(name = "Databasechangelog.findByOrderexecuted", query = "SELECT d FROM Databasechangelog d WHERE d.orderexecuted = :orderexecuted"),
    @NamedQuery(name = "Databasechangelog.findByExectype", query = "SELECT d FROM Databasechangelog d WHERE d.exectype = :exectype"),
    @NamedQuery(name = "Databasechangelog.findByMd5sum", query = "SELECT d FROM Databasechangelog d WHERE d.md5sum = :md5sum"),
    @NamedQuery(name = "Databasechangelog.findByDescription", query = "SELECT d FROM Databasechangelog d WHERE d.description = :description"),
    @NamedQuery(name = "Databasechangelog.findByComments", query = "SELECT d FROM Databasechangelog d WHERE d.comments = :comments"),
    @NamedQuery(name = "Databasechangelog.findByTag", query = "SELECT d FROM Databasechangelog d WHERE d.tag = :tag"),
    @NamedQuery(name = "Databasechangelog.findByLiquibase", query = "SELECT d FROM Databasechangelog d WHERE d.liquibase = :liquibase")})
public class Databasechangelog implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatabasechangelogPK databasechangelogPK;
    @Basic(optional = false)
    @Column(name = "dateexecuted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateexecuted;
    @Basic(optional = false)
    @Column(name = "orderexecuted")
    private int orderexecuted;
    @Basic(optional = false)
    @Column(name = "exectype")
    private String exectype;
    @Column(name = "md5sum")
    private String md5sum;
    @Column(name = "description")
    private String description;
    @Column(name = "comments")
    private String comments;
    @Column(name = "tag")
    private String tag;
    @Column(name = "liquibase")
    private String liquibase;

    public Databasechangelog() {
    }

    public Databasechangelog(DatabasechangelogPK databasechangelogPK) {
        this.databasechangelogPK = databasechangelogPK;
    }

    public Databasechangelog(DatabasechangelogPK databasechangelogPK, Date dateexecuted, int orderexecuted, String exectype) {
        this.databasechangelogPK = databasechangelogPK;
        this.dateexecuted = dateexecuted;
        this.orderexecuted = orderexecuted;
        this.exectype = exectype;
    }

    public Databasechangelog(String id, String author, String filename) {
        this.databasechangelogPK = new DatabasechangelogPK(id, author, filename);
    }

    public DatabasechangelogPK getDatabasechangelogPK() {
        return databasechangelogPK;
    }

    public void setDatabasechangelogPK(DatabasechangelogPK databasechangelogPK) {
        this.databasechangelogPK = databasechangelogPK;
    }

    public Date getDateexecuted() {
        return dateexecuted;
    }

    public void setDateexecuted(Date dateexecuted) {
        this.dateexecuted = dateexecuted;
    }

    public int getOrderexecuted() {
        return orderexecuted;
    }

    public void setOrderexecuted(int orderexecuted) {
        this.orderexecuted = orderexecuted;
    }

    public String getExectype() {
        return exectype;
    }

    public void setExectype(String exectype) {
        this.exectype = exectype;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLiquibase() {
        return liquibase;
    }

    public void setLiquibase(String liquibase) {
        this.liquibase = liquibase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (databasechangelogPK != null ? databasechangelogPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Databasechangelog)) {
            return false;
        }
        Databasechangelog other = (Databasechangelog) object;
        if ((this.databasechangelogPK == null && other.databasechangelogPK != null) || (this.databasechangelogPK != null && !this.databasechangelogPK.equals(other.databasechangelogPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Databasechangelog[ databasechangelogPK=" + databasechangelogPK + " ]";
    }

}
