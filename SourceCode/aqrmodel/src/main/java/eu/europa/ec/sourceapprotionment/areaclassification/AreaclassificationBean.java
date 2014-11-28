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
package eu.europa.ec.sourceapprotionment.areaclassification;

import java.io.Serializable;

public class AreaclassificationBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private String areaclassificationvalue;
    private String uri;
    private String label;
    private String definition;
    private String notation;

    public AreaclassificationBean() {
    }

    public AreaclassificationBean(String uuid) {
        this.uuid = uuid;
    }

    public AreaclassificationBean(String uuid, String areaclassificationvalue) {
        this.uuid = uuid;
        this.areaclassificationvalue = areaclassificationvalue;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAreaclassificationvalue() {
        return areaclassificationvalue;
    }

    public void setAreaclassificationvalue(String areaclassificationvalue) {
        this.areaclassificationvalue = areaclassificationvalue;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }
}
