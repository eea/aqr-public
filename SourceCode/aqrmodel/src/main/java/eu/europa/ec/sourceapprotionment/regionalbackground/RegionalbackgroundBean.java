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
package eu.europa.ec.sourceapprotionment.regionalbackground;

import java.io.Serializable;

public class RegionalbackgroundBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String total;
    private String totalcomment;
    private boolean total_nil;
    private String total_nilreason;

    private String fromwithinms;
    private String fromwithinmscomment;
    private boolean fromwithinms_nil;
    private String fromwithinms_nilreason;

    private String transboundary;
    private String transboundarycomment;
    private boolean transboundary_nil;
    private String transboundary_nilreason;

    private String naturalregionalbackground;
    private String naturalregionalbackgroundcomment;
    private boolean naturalregionalbackground_nil;
    private String naturalregionalbackground_nilreason;

    private String other;
    private String othercomment;
    private boolean other_nil;
    private String other_nilreason;

    private String unitmisure;
    private String unitmisurecomment;
    private boolean unitmisure_nil;
    private String unitmisure_nilreason;

    public RegionalbackgroundBean() {
    }

    public RegionalbackgroundBean(String uuid) {
        this.uuid = uuid;
    }

    public RegionalbackgroundBean(String uuid, String total, String fromwithinms, String transboundary, String naturalregionalbackground) {
        this.uuid = uuid;
        this.total = total;
        this.fromwithinms = fromwithinms;
        this.transboundary = transboundary;
        this.naturalregionalbackground = naturalregionalbackground;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalcomment() {
        return totalcomment;
    }

    public void setTotalcomment(String totalcomment) {
        this.totalcomment = totalcomment;
    }

    public String getFromwithinms() {
        return fromwithinms;
    }

    public void setFromwithinms(String fromwithinms) {
        this.fromwithinms = fromwithinms;
    }

    public String getFromwithinmscomment() {
        return fromwithinmscomment;
    }

    public void setFromwithinmscomment(String fromwithinmscomment) {
        this.fromwithinmscomment = fromwithinmscomment;
    }

    public String getTransboundary() {
        return transboundary;
    }

    public void setTransboundary(String transboundary) {
        this.transboundary = transboundary;
    }

    public String getTransboundarycomment() {
        return transboundarycomment;
    }

    public void setTransboundarycomment(String transboundarycomment) {
        this.transboundarycomment = transboundarycomment;
    }

    public String getNaturalregionalbackground() {
        return naturalregionalbackground;
    }

    public void setNaturalregionalbackground(String naturalregionalbackground) {
        this.naturalregionalbackground = naturalregionalbackground;
    }

    public String getNaturalregionalbackgroundcomment() {
        return naturalregionalbackgroundcomment;
    }

    public void setNaturalregionalbackgroundcomment(String naturalregionalbackgroundcomment) {
        this.naturalregionalbackgroundcomment = naturalregionalbackgroundcomment;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOthercomment() {
        return othercomment;
    }

    public void setOthercomment(String othercomment) {
        this.othercomment = othercomment;
    }

    public String getUnitmisure() {
        return unitmisure;
    }

    public void setUnitmisure(String unitmisure) {
        this.unitmisure = unitmisure;
    }

    public String getUnitmisurecomment() {
        return unitmisurecomment;
    }

    public void setUnitmisurecomment(String unitmisurecomment) {
        this.unitmisurecomment = unitmisurecomment;
    }

    public boolean isTotal_nil() {
        return total_nil;
    }

    public void setTotal_nil(boolean total_nil) {
        this.total_nil = total_nil;
    }

    public String getTotal_nilreason() {
        return total_nilreason;
    }

    public void setTotal_nilreason(String total_nilreason) {
        this.total_nilreason = total_nilreason;
    }

    public boolean isFromwithinms_nil() {
        return fromwithinms_nil;
    }

    public void setFromwithinms_nil(boolean fromwithinms_nil) {
        this.fromwithinms_nil = fromwithinms_nil;
    }

    public String getFromwithinms_nilreason() {
        return fromwithinms_nilreason;
    }

    public void setFromwithinms_nilreason(String fromwithinms_nilreason) {
        this.fromwithinms_nilreason = fromwithinms_nilreason;
    }

    public boolean isTransboundary_nil() {
        return transboundary_nil;
    }

    public void setTransboundary_nil(boolean transboundary_nil) {
        this.transboundary_nil = transboundary_nil;
    }

    public String getTransboundary_nilreason() {
        return transboundary_nilreason;
    }

    public void setTransboundary_nilreason(String transboundary_nilreason) {
        this.transboundary_nilreason = transboundary_nilreason;
    }

    public boolean isNaturalregionalbackground_nil() {
        return naturalregionalbackground_nil;
    }

    public void setNaturalregionalbackground_nil(boolean naturalregionalbackground_nil) {
        this.naturalregionalbackground_nil = naturalregionalbackground_nil;
    }

    public String getNaturalregionalbackground_nilreason() {
        return naturalregionalbackground_nilreason;
    }

    public void setNaturalregionalbackground_nilreason(String naturalregionalbackground_nilreason) {
        this.naturalregionalbackground_nilreason = naturalregionalbackground_nilreason;
    }

    public boolean isOther_nil() {
        return other_nil;
    }

    public void setOther_nil(boolean other_nil) {
        this.other_nil = other_nil;
    }

    public String getOther_nilreason() {
        return other_nilreason;
    }

    public void setOther_nilreason(String other_nilreason) {
        this.other_nilreason = other_nilreason;
    }

    public boolean isUnitmisure_nil() {
        return unitmisure_nil;
    }

    public void setUnitmisure_nil(boolean unitmisure_nil) {
        this.unitmisure_nil = unitmisure_nil;
    }

    public String getUnitmisure_nilreason() {
        return unitmisure_nilreason;
    }

    public void setUnitmisure_nilreason(String unitmisure_nilreason) {
        this.unitmisure_nilreason = unitmisure_nilreason;
    }

}
