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
package eu.europa.ec.evaluationscenario.scenario;

import java.util.List;

public class ScenarioBean {

    private static final long serialVersionUID = 1L;
    private String uuid;

    private String inspireidLocalid;
    private String inspireidNamespace;
    private String inspireidVersionid;

    private String description;

    private String expectedexceedence;
    private String comment;

    private String totalemissions;
    private String expectedconcentration;

    private List<String> measuresUuid;

    public ScenarioBean() {
    }

    public ScenarioBean(String uuid) {
        this.uuid = uuid;
    }

    public ScenarioBean(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, String description, String totalemissions, String comment) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
        this.description = description;
        this.totalemissions = totalemissions;
        this.comment = comment;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getInspireidLocalid() {
        return inspireidLocalid;
    }

    public void setInspireidLocalid(String inspireidLocalid) {
        this.inspireidLocalid = inspireidLocalid;
    }

    public String getInspireidNamespace() {
        return inspireidNamespace;
    }

    public void setInspireidNamespace(String inspireidNamespace) {
        this.inspireidNamespace = inspireidNamespace;
    }

    public String getInspireidVersionid() {
        return inspireidVersionid;
    }

    public void setInspireidVersionid(String inspireidVersionid) {
        this.inspireidVersionid = inspireidVersionid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpectedexceedence() {
        return expectedexceedence;
    }

    public void setExpectedexceedence(String expectedexceedence) {
        this.expectedexceedence = expectedexceedence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTotalemissions() {
        return totalemissions;
    }

    public void setTotalemissions(String totalemissions) {
        this.totalemissions = totalemissions;
    }

    public String getExpectedconcentration() {
        return expectedconcentration;
    }

    public void setExpectedconcentration(String expectedconcentration) {
        this.expectedconcentration = expectedconcentration;
    }

    public List<String> getMeasuresUuid() {
        return measuresUuid;
    }

    public void setMeasuresUuid(List<String> measuresUuid) {
        this.measuresUuid = measuresUuid;
    }

}
