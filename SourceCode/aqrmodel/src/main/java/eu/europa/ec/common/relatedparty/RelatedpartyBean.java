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
package eu.europa.ec.common.relatedparty;

public class RelatedpartyBean {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String individualname;
    private String organisationname;

    private String website;

    private String address;

    private String electronicmailaddress;
    private String telephonevoice;

    public RelatedpartyBean() {
    }

    public RelatedpartyBean(String uuid) {
        this.uuid = uuid;
    }

    public RelatedpartyBean(String uuid, String individualname) {
        this.uuid = uuid;
        this.individualname = individualname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIndividualname() {
        return individualname;
    }

    public void setIndividualname(String individualname) {
        this.individualname = individualname;
    }

    public String getOrganisationname() {
        return organisationname;
    }

    public void setOrganisationname(String organisationname) {
        this.organisationname = organisationname;
    }

    public String getElectronicmailaddress() {
        return electronicmailaddress;
    }

    public void setElectronicmailaddress(String electronicmailaddress) {
        this.electronicmailaddress = electronicmailaddress;
    }

    public String getTelephonevoice() {
        return telephonevoice;
    }

    public void setTelephonevoice(String telephonevoice) {
        this.telephonevoice = telephonevoice;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
