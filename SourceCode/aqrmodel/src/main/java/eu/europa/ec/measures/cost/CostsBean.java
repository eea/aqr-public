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
package eu.europa.ec.measures.cost;

public class CostsBean {

    private String uuid;
    private String extimatedimplementationcosts;
    private boolean extimatedimplementationcosts_nil;
    private String extimatedimplementationcosts_nilreason;
    private String finalimplementationcosts;
    private String comment;
    private String currency_uri;
    private String currency_label;

    public CostsBean() {
    }

    public CostsBean(String uuid) {
        this.uuid = uuid;
    }

    public CostsBean(String uuid, String extimatedimplementationcosts) {
        this.uuid = uuid;
        this.extimatedimplementationcosts = extimatedimplementationcosts;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getExtimatedimplementationcosts() {
        return extimatedimplementationcosts;
    }

    public void setExtimatedimplementationcosts(String extimatedimplementationcosts) {
        this.extimatedimplementationcosts = extimatedimplementationcosts;
    }

    public String getFinalimplementationcosts() {
        return finalimplementationcosts;
    }

    public void setFinalimplementationcosts(String finalimplementationcosts) {
        this.finalimplementationcosts = finalimplementationcosts;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCurrency_label() {
        return currency_label;
    }

    public void setCurrency_label(String currency_label) {
        this.currency_label = currency_label;
    }

    public String getCurrency_uri() {
        return currency_uri;
    }

    public void setCurrency_uri(String currency_uri) {
        this.currency_uri = currency_uri;
    }

    public boolean isExtimatedimplementationcosts_nil() {
        return extimatedimplementationcosts_nil;
    }

    public void setExtimatedimplementationcosts_nil(boolean extimatedimplementationcosts_nil) {
        this.extimatedimplementationcosts_nil = extimatedimplementationcosts_nil;
    }

    public String getExtimatedimplementationcosts_nilreason() {
        return extimatedimplementationcosts_nilreason;
    }

    public void setExtimatedimplementationcosts_nilreason(String extimatedimplementationcosts_nilreason) {
        this.extimatedimplementationcosts_nilreason = extimatedimplementationcosts_nilreason;
    }
}
