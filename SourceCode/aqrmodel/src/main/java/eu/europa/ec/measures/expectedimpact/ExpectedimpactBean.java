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
package eu.europa.ec.measures.expectedimpact;

public class ExpectedimpactBean {

    private String uuid;
    private String levelofconcentration;
    private String numberofexceedence;
    private String comment;

    private String specificationofhours_uri;
    private String specificationofhours_label;

    public ExpectedimpactBean() {
    }

    public ExpectedimpactBean(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLevelofconcentration() {
        return levelofconcentration;
    }

    public void setLevelofconcentration(String levelofconcentration) {
        this.levelofconcentration = levelofconcentration;
    }

    public String getNumberofexceedence() {
        return numberofexceedence;
    }

    public void setNumberofexceedence(String numberofexceedence) {
        this.numberofexceedence = numberofexceedence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSpecificationofhours_uri() {
        return specificationofhours_uri;
    }

    public void setSpecificationofhours_uri(String specificationofhours_uri) {
        this.specificationofhours_uri = specificationofhours_uri;
    }

    public String getSpecificationofhours_label() {
        return specificationofhours_label;
    }

    public void setSpecificationofhours_label(String specificationofhours_label) {
        this.specificationofhours_label = specificationofhours_label;
    }

}
