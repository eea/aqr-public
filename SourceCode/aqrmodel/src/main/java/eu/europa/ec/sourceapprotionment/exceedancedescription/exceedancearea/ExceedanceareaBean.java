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
package eu.europa.ec.sourceapprotionment.exceedancedescription.exceedancearea;

import java.io.Serializable;
import java.util.List;

public class ExceedanceareaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String area;
    private String areaestimate;

    private String roadlenghtestimate;
    private String administrativeunits;

    private String modelused;
    private String stationused;

    public List<String> areaclassificationList_uri;

    public ExceedanceareaBean() {
    }

    public ExceedanceareaBean(String uuid) {
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

    public List<String> getAreaclassificationList_uri() {
        return areaclassificationList_uri;
    }

    public void setAreaclassificationList_uri(List<String> areaclassificationList_uri) {
        this.areaclassificationList_uri = areaclassificationList_uri;
    }

    public String getStationused() {
        return stationused;
    }

    public void setStationused(String stationused) {
        this.stationused = stationused;
    }

}
