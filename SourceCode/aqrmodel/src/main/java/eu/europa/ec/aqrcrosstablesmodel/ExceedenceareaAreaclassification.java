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

import eu.europa.ec.aqrmodel.Areaclassification;
import eu.europa.ec.aqrmodel.Exceedancearea;
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
@Table(name = "exceedencearea_areaclassification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExceedenceareaAreaclassification.findAll", query = "SELECT e FROM ExceedenceareaAreaclassification e"),
    @NamedQuery(name = "ExceedenceareaAreaclassification.findByUuid", query = "SELECT e FROM ExceedenceareaAreaclassification e WHERE e.uuid = :uuid"),
    @NamedQuery(name = "ExceedenceareaAreaclassification.findByExceedancearea", query = "SELECT e FROM ExceedenceareaAreaclassification e WHERE e.exceedancearea = :exceedancearea"),
    @NamedQuery(name = "ExceedenceareaAreaclassification.deleteByExceedancearea", query = "DELETE FROM ExceedenceareaAreaclassification a WHERE a.exceedancearea = :exceedancearea")
})
public class ExceedenceareaAreaclassification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    @JoinColumn(name = "exceedancearea", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Exceedancearea exceedancearea;

    @JoinColumn(name = "areaclassification", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Areaclassification areaclassification;

    public ExceedenceareaAreaclassification() {
    }

    public ExceedenceareaAreaclassification(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Exceedancearea getExceedancearea() {
        return exceedancearea;
    }

    public void setExceedancearea(Exceedancearea exceedancearea) {
        this.exceedancearea = exceedancearea;
    }

    public Areaclassification getAreaclassification() {
        return areaclassification;
    }

    public void setAreaclassification(Areaclassification areaclassification) {
        this.areaclassification = areaclassification;
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
        if (!(object instanceof ExceedenceareaAreaclassification)) {
            return false;
        }
        ExceedenceareaAreaclassification other = (ExceedenceareaAreaclassification) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrcrosstablesmodel.ExceedenceareaAreaclassification[ uuid=" + uuid + " ]";
    }

}
