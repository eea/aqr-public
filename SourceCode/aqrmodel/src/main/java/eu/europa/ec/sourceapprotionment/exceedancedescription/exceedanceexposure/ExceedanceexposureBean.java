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
package eu.europa.ec.sourceapprotionment.exceedancedescription.exceedanceexposure;

import java.io.Serializable;

public class ExceedanceexposureBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String exposedpopulation;
    private String exposedarea;

    private String sensitiveresidentpopulation;
    private String relevantinfrastructure;
    private String referenceyear;

    public ExceedanceexposureBean() {
    }

    public ExceedanceexposureBean(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getExposedpopulation() {
        return exposedpopulation;
    }

    public void setExposedpopulation(String exposedpopulation) {
        this.exposedpopulation = exposedpopulation;
    }

    public String getExposedarea() {
        return exposedarea;
    }

    public void setExposedarea(String exposedarea) {
        this.exposedarea = exposedarea;
    }

    public String getSensitiveresidentpopulation() {
        return sensitiveresidentpopulation;
    }

    public void setSensitiveresidentpopulation(String sensitiveresidentpopulation) {
        this.sensitiveresidentpopulation = sensitiveresidentpopulation;
    }

    public String getRelevantinfrastructure() {
        return relevantinfrastructure;
    }

    public void setRelevantinfrastructure(String relevantinfrastructure) {
        this.relevantinfrastructure = relevantinfrastructure;
    }

    public String getReferenceyear() {
        return referenceyear;
    }

    public void setReferenceyear(String referenceyear) {
        this.referenceyear = referenceyear;
    }

}
