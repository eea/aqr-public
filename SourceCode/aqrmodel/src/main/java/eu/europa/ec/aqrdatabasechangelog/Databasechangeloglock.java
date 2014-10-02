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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "databasechangeloglock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Databasechangeloglock.findAll", query = "SELECT d FROM Databasechangeloglock d"),
    @NamedQuery(name = "Databasechangeloglock.findById", query = "SELECT d FROM Databasechangeloglock d WHERE d.id = :id"),
    @NamedQuery(name = "Databasechangeloglock.findByLocked", query = "SELECT d FROM Databasechangeloglock d WHERE d.locked = :locked"),
    @NamedQuery(name = "Databasechangeloglock.findByLockgranted", query = "SELECT d FROM Databasechangeloglock d WHERE d.lockgranted = :lockgranted"),
    @NamedQuery(name = "Databasechangeloglock.findByLockedby", query = "SELECT d FROM Databasechangeloglock d WHERE d.lockedby = :lockedby")})
public class Databasechangeloglock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "locked")
    private boolean locked;
    @Column(name = "lockgranted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockgranted;
    @Column(name = "lockedby")
    private String lockedby;

    public Databasechangeloglock() {
    }

    public Databasechangeloglock(Integer id) {
        this.id = id;
    }

    public Databasechangeloglock(Integer id, boolean locked) {
        this.id = id;
        this.locked = locked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getLockgranted() {
        return lockgranted;
    }

    public void setLockgranted(Date lockgranted) {
        this.lockgranted = lockgranted;
    }

    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Databasechangeloglock)) {
            return false;
        }
        Databasechangeloglock other = (Databasechangeloglock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Databasechangeloglock[ id=" + id + " ]";
    }

}
