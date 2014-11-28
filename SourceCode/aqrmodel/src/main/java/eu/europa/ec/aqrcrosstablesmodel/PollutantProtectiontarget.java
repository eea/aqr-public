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

import eu.europa.ec.aqrmodel.Pollutant;
import eu.europa.ec.aqrmodel.Protectiontarget;
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
@Table(name = "pollutant_protectiontarget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PollutantProtectiontarget.findAll", query = "SELECT p FROM PollutantProtectiontarget p"),
    @NamedQuery(name = "PollutantProtectiontarget.findByUuid", query = "SELECT p FROM PollutantProtectiontarget p WHERE p.uuid = :uuid"),
    @NamedQuery(name = "PollutantProtectiontarget.findByPollutant", query = "SELECT p FROM PollutantProtectiontarget p WHERE p.pollutant = :pollutant")
})
public class PollutantProtectiontarget implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @JoinColumn(name = "protectiontarget", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Protectiontarget protectiontarget;
    @JoinColumn(name = "pollutant", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Pollutant pollutant;

    public PollutantProtectiontarget() {
    }

    public PollutantProtectiontarget(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Protectiontarget getProtectiontarget() {
        return protectiontarget;
    }

    public void setProtectiontarget(Protectiontarget protectiontarget) {
        this.protectiontarget = protectiontarget;
    }

    public Pollutant getPollutant() {
        return pollutant;
    }

    public void setPollutant(Pollutant pollutant) {
        this.pollutant = pollutant;
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
        if (!(object instanceof PollutantProtectiontarget)) {
            return false;
        }
        PollutantProtectiontarget other = (PollutantProtectiontarget) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrbean.PollutantProtectiontarget[ uuid=" + uuid + " ]";
    }
}
