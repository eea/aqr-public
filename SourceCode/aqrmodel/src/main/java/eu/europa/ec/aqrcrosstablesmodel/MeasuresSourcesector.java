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

import eu.europa.ec.aqrmodel.Measures;
import eu.europa.ec.aqrmodel.Sourcesector;
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
@Table(name = "measures_sourcesector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeasuresSourcesector.findAll", query = "SELECT m FROM MeasuresSourcesector m"),
    @NamedQuery(name = "MeasuresSourcesector.findByUuid", query = "SELECT m FROM MeasuresSourcesector m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "MeasuresSourcesector.findByMeasures", query = "SELECT m FROM MeasuresSourcesector m WHERE m.measures = :measures"),
    @NamedQuery(name = "MeasuresSourcesector.deleteByUuid", query = "DELETE FROM MeasuresSourcesector m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "MeasuresSourcesector.deleteByMeasures", query = "DELETE FROM MeasuresSourcesector m WHERE m.measures = :measures")
})
public class MeasuresSourcesector implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    @JoinColumn(name = "sourcesector", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Sourcesector sourcesector;

    @JoinColumn(name = "measures", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Measures measures;

    public MeasuresSourcesector() {
    }

    public MeasuresSourcesector(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Sourcesector getSourcesector() {
        return sourcesector;
    }

    public void setSourcesector(Sourcesector sourcesector) {
        this.sourcesector = sourcesector;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
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
        if (!(object instanceof MeasuresSourcesector)) {
            return false;
        }
        MeasuresSourcesector other = (MeasuresSourcesector) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrcrosstablesmodel.MeasuresSourcesector[ uuid=" + uuid + " ]";
    }

}
