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

import eu.europa.ec.aqrmodel.Evaluationscenario;
import eu.europa.ec.aqrmodel.Measures;
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
@Table(name = "measures_evaluationscenario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeasuresEvaluationscenario.findAll", query = "SELECT m FROM MeasuresEvaluationscenario m"),
    @NamedQuery(name = "MeasuresEvaluationscenario.findByUuid", query = "SELECT m FROM MeasuresEvaluationscenario m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "MeasuresEvaluationscenario.findByMeasures", query = "SELECT m FROM MeasuresEvaluationscenario m WHERE m.measures = :measures"),
    @NamedQuery(name = "MeasuresEvaluationscenario.deleteByMeasures", query = "DELETE FROM MeasuresEvaluationscenario m WHERE m.measures = :measures"),
    @NamedQuery(name = "MeasuresEvaluationscenario.deleteByEvaluationscenario", query = "DELETE FROM MeasuresEvaluationscenario m WHERE m.evaluationscenario = :evaluationscenario")
})
public class MeasuresEvaluationscenario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @JoinColumn(name = "measures", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Measures measures;
    @JoinColumn(name = "evaluationscenario", referencedColumnName = "uuid")
    @ManyToOne(optional = false)
    private Evaluationscenario evaluationscenario;

    public MeasuresEvaluationscenario() {
    }

    public MeasuresEvaluationscenario(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

    public Evaluationscenario getEvaluationscenario() {
        return evaluationscenario;
    }

    public void setEvaluationscenario(Evaluationscenario evaluationscenario) {
        this.evaluationscenario = evaluationscenario;
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
        if (!(object instanceof MeasuresEvaluationscenario)) {
            return false;
        }
        MeasuresEvaluationscenario other = (MeasuresEvaluationscenario) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrcrosstablesmodel.MeasuresEvaluationscenario[ uuid=" + uuid + " ]";
    }
}
