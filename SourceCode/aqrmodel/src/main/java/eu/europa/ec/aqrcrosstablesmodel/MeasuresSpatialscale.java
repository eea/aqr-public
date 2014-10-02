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
import eu.europa.ec.aqrmodel.Spatialscale;
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
@Table(name = "measures_spatialscale")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeasuresSpatialscale.findAll", query = "SELECT m FROM MeasuresSpatialscale m"),
    @NamedQuery(name = "MeasuresSpatialscale.findByUuid", query = "SELECT m FROM MeasuresSpatialscale m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "MeasuresSpatialscale.findByMeasures", query = "SELECT m FROM MeasuresSpatialscale m WHERE m.measures = :measures"),
    @NamedQuery(name = "MeasuresSpatialscale.deleteByUuid", query = "DELETE FROM MeasuresSpatialscale m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "MeasuresSpatialscale.deleteByMeasures", query = "DELETE FROM MeasuresSpatialscale m WHERE m.measures = :measures")
})
public class MeasuresSpatialscale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    @JoinColumn(name = "spatialscale", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Spatialscale spatialscale;

    @JoinColumn(name = "measures", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Measures measures;

    public MeasuresSpatialscale() {
    }

    public MeasuresSpatialscale(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Spatialscale getSpatialscale() {
        return spatialscale;
    }

    public void setSpatialscale(Spatialscale spatialscale) {
        this.spatialscale = spatialscale;
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
        if (!(object instanceof MeasuresSpatialscale)) {
            return false;
        }
        MeasuresSpatialscale other = (MeasuresSpatialscale) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrcrosstablesmodel.MeasuresSpatialscale[ uuid=" + uuid + " ]";
    }

}
