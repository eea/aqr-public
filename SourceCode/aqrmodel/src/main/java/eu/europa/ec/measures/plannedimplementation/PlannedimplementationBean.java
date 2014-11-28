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
package eu.europa.ec.measures.plannedimplementation;

public class PlannedimplementationBean {

    private String uuid;
    private String implementationplannedtimeperiodId;
    private String implementationplannedtimeperiodBeginposition;
    private String implementationplannedtimeperiodEndposition;
    private String implementationactualtimeperiodId;
    private String implementationactualtimeperiodBeginposition;
    private String implementationactualtimeperiodEndposition;
    private String plannedfulleffectdateId;
    private String plannedfulleffectdateTimeposition;
    private boolean plannedfulleffectdateTimeposition_nil;
    private String plannedfulleffectdateTimeposition_nilreason;
    private String otherdate;
    private String monitoringprogressindicators;
    private boolean monitoringprogressindicators_nil;
    private String monitoringprogressindicators_nilreason;
    private String comment;
    private String statusplannedimplementation_uri;
    private String statusplannedimplementation_label;

    public PlannedimplementationBean() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImplementationplannedtimeperiodId() {
        return implementationplannedtimeperiodId;
    }

    public void setImplementationplannedtimeperiodId(String implementationplannedtimeperiodId) {
        this.implementationplannedtimeperiodId = implementationplannedtimeperiodId;
    }

    public String getImplementationactualtimeperiodId() {
        return implementationactualtimeperiodId;
    }

    public void setImplementationactualtimeperiodId(String implementationactualtimeperiodId) {
        this.implementationactualtimeperiodId = implementationactualtimeperiodId;
    }

    public String getPlannedfulleffectdateId() {
        return plannedfulleffectdateId;
    }

    public void setPlannedfulleffectdateId(String plannedfulleffectdateId) {
        this.plannedfulleffectdateId = plannedfulleffectdateId;
    }

    public String getOtherdate() {
        return otherdate;
    }

    public void setOtherdate(String otherdate) {
        this.otherdate = otherdate;
    }

    public String getMonitoringprogressindicators() {
        return monitoringprogressindicators;
    }

    public void setMonitoringprogressindicators(String monitoringprogressindicators) {
        this.monitoringprogressindicators = monitoringprogressindicators;
    }

    public String getStatusplannedimplementation_uri() {
        return statusplannedimplementation_uri;
    }

    public void setStatusplannedimplementation_uri(String statusplannedimplementation_uri) {
        this.statusplannedimplementation_uri = statusplannedimplementation_uri;
    }

    public String getStatusplannedimplementation_label() {
        return statusplannedimplementation_label;
    }

    public void setStatusplannedimplementation_label(String statusplannedimplementation_label) {
        this.statusplannedimplementation_label = statusplannedimplementation_label;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImplementationplannedtimeperiodBeginposition() {
        return implementationplannedtimeperiodBeginposition;
    }

    public void setImplementationplannedtimeperiodBeginposition(String implementationplannedtimeperiodBeginposition) {
        this.implementationplannedtimeperiodBeginposition = implementationplannedtimeperiodBeginposition;
    }

    public String getImplementationplannedtimeperiodEndposition() {
        return implementationplannedtimeperiodEndposition;
    }

    public void setImplementationplannedtimeperiodEndposition(String implementationplannedtimeperiodEndposition) {
        this.implementationplannedtimeperiodEndposition = implementationplannedtimeperiodEndposition;
    }

    public String getImplementationactualtimeperiodBeginposition() {
        return implementationactualtimeperiodBeginposition;
    }

    public void setImplementationactualtimeperiodBeginposition(String implementationactualtimeperiodBeginposition) {
        this.implementationactualtimeperiodBeginposition = implementationactualtimeperiodBeginposition;
    }

    public String getImplementationactualtimeperiodEndposition() {
        return implementationactualtimeperiodEndposition;
    }

    public void setImplementationactualtimeperiodEndposition(String implementationactualtimeperiodEndposition) {
        this.implementationactualtimeperiodEndposition = implementationactualtimeperiodEndposition;
    }

    public String getPlannedfulleffectdateTimeposition() {
        return plannedfulleffectdateTimeposition;
    }

    public void setPlannedfulleffectdateTimeposition(String plannedfulleffectdateTimeposition) {
        this.plannedfulleffectdateTimeposition = plannedfulleffectdateTimeposition;
    }

    public boolean isPlannedfulleffectdateTimeposition_nil() {
        return plannedfulleffectdateTimeposition_nil;
    }

    public void setPlannedfulleffectdateTimeposition_nil(boolean plannedfulleffectdateTimeposition_nil) {
        this.plannedfulleffectdateTimeposition_nil = plannedfulleffectdateTimeposition_nil;
    }

    public String getPlannedfulleffectdateTimeposition_nilreason() {
        return plannedfulleffectdateTimeposition_nilreason;
    }

    public void setPlannedfulleffectdateTimeposition_nilreason(String plannedfulleffectdateTimeposition_nilreason) {
        this.plannedfulleffectdateTimeposition_nilreason = plannedfulleffectdateTimeposition_nilreason;
    }

    public boolean isMonitoringprogressindicators_nil() {
        return monitoringprogressindicators_nil;
    }

    public void setMonitoringprogressindicators_nil(boolean monitoringprogressindicators_nil) {
        this.monitoringprogressindicators_nil = monitoringprogressindicators_nil;
    }

    public String getMonitoringprogressindicators_nilreason() {
        return monitoringprogressindicators_nilreason;
    }

    public void setMonitoringprogressindicators_nilreason(String monitoringprogressindicators_nilreason) {
        this.monitoringprogressindicators_nilreason = monitoringprogressindicators_nilreason;
    }
}
