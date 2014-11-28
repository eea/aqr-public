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
package eu.europa.ec.attainment;

import eu.europa.ec.plan.protectiontarget.ProtectiontargetBean;
import java.io.Serializable;

public class EnvironmentalobjectiveBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private String objectivetypevalue;
    private String reportingmetricvalue;
    private ProtectiontargetBean protectiontargetBean;

    public EnvironmentalobjectiveBean() {
    }

    public EnvironmentalobjectiveBean(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getObjectivetypevalue() {
        return objectivetypevalue;
    }

    public void setObjectivetypevalue(String objectivetypevalue) {
        this.objectivetypevalue = objectivetypevalue;
    }

    public String getReportingmetricvalue() {
        return reportingmetricvalue;
    }

    public void setReportingmetricvalue(String reportingmetricvalue) {
        this.reportingmetricvalue = reportingmetricvalue;
    }

    public ProtectiontargetBean getProtectiontargetBean() {
        return protectiontargetBean;
    }

    public void setProtectiontargetBean(ProtectiontargetBean protectiontargetBean) {
        this.protectiontargetBean = protectiontargetBean;
    }
}
