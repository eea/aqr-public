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
@Table(name = "exceedancearea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exceedancearea.findAll", query = "SELECT e FROM Exceedancearea e"),
    @NamedQuery(name = "Exceedancearea.findByUuid", query = "SELECT e FROM Exceedancearea e WHERE e.uuid = :uuid"),
    @NamedQuery(name = "Exceedancearea.deleteByUuid", query = "DELETE FROM Exceedancearea m WHERE m.uuid = :uuid")
})
public class Exceedancearea implements Serializable {

    @OneToMany(mappedBy = "exceedancearea")
    private List<Exceedancedescription> exceedancedescriptionList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "area")
    private String area;

    @Column(name = "areaestimate")
    private String areaestimate;
    @Column(name = "roadlenghtestimate")
    private String roadlenghtestimate;

    @Column(name = "administrativeunits")
    private String administrativeunits;

    @Column(name = "modelused")
    private String modelused;

    @Column(name = "stationused")
    private String stationused;

    public Exceedancearea() {
    }

    public Exceedancearea(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaestimate() {
        return areaestimate;
    }

    public void setAreaestimate(String areaestimate) {
        this.areaestimate = areaestimate;
    }

    public String getRoadlenghtestimate() {
        return roadlenghtestimate;
    }

    public void setRoadlenghtestimate(String roadlenghtestimate) {
        this.roadlenghtestimate = roadlenghtestimate;
    }

    public String getAdministrativeunits() {
        return administrativeunits;
    }

    public void setAdministrativeunits(String administrativeunits) {
        this.administrativeunits = administrativeunits;
    }

    public String getModelused() {
        return modelused;
    }

    public void setModelused(String modelused) {
        this.modelused = modelused;
    }

    public String getStationused() {
        return stationused;
    }

    public void setStationused(String stationused) {
        this.stationused = stationused;
    }

    @XmlTransient
    public List<Exceedancedescription> getExceedancedescriptionList() {
        return exceedancedescriptionList;
    }

    public void setExceedancedescriptionList(List<Exceedancedescription> exceedancedescriptionList) {
        this.exceedancedescriptionList = exceedancedescriptionList;
    }

}
