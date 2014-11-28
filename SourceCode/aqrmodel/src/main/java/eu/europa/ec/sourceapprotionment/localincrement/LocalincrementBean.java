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
package eu.europa.ec.sourceapprotionment.localincrement;

import java.io.Serializable;

public class LocalincrementBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private String total;
    private String totalcomment;
    private boolean total_nil;
    private String total_nilreason;
    private String traffic;
    private String trafficcomment;
    private boolean traffic_nil;
    private String traffic_nilreason;
    private String heatandpowerproduction;
    private String heatandpowerproductioncomment;
    private boolean heatandpowerproduction_nil;
    private String heatandpowerproduction_nilreason;
    private String agriculture;
    private String agriculturecomment;
    private boolean agriculture_nil;
    private String agriculture_nilreason;
    private String commercialandresidential;
    private String commercialandresidentialcomment;
    private boolean commercialandresidential_nil;
    private String commercialandresidential_nilreason;
    private String shipping;
    private String shippingcomment;
    private boolean shipping_nil;
    private String shipping_nilreason;
    private String offroadmobilemachinery;
    private String offroadmobilemachinerycomment;
    private boolean offroadmobilemachinery_nil;
    private String offroadmobilemachinery_nilreason;
    private String naturallocalincrement;
    private String naturallocalincrementcomment;
    private boolean naturallocalincrement_nil;
    private String naturallocalincrement_nilreason;
    private String transboundary;
    private String transboundarycomment;
    private boolean transboundary_nil;
    private String transboundary_nilreason;
    private String other;
    private String othercomment;
    private boolean other_nil;
    private String other_nilreason;
    private String unitmisure;
    private String unitmisurecomment;
    private boolean unitmisure_nil;
    private String unitmisure_nilreason;

    public LocalincrementBean() {
    }

    public LocalincrementBean(String uuid) {
        this.uuid = uuid;
    }

    public LocalincrementBean(String uuid, String total, String traffic, String heatandpowerproduction, String agriculture, String commercialandresidential, String shipping, String offroadmobilemachinery, String naturallocalincrement, String transboundary) {
        this.uuid = uuid;
        this.total = total;
        this.traffic = traffic;
        this.heatandpowerproduction = heatandpowerproduction;
        this.agriculture = agriculture;
        this.commercialandresidential = commercialandresidential;
        this.shipping = shipping;
        this.offroadmobilemachinery = offroadmobilemachinery;
        this.naturallocalincrement = naturallocalincrement;
        this.transboundary = transboundary;
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

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTrafficcomment() {
        return trafficcomment;
    }

    public void setTrafficcomment(String trafficcomment) {
        this.trafficcomment = trafficcomment;
    }

    public String getHeatandpowerproduction() {
        return heatandpowerproduction;
    }

    public void setHeatandpowerproduction(String heatandpowerproduction) {
        this.heatandpowerproduction = heatandpowerproduction;
    }

    public String getHeatandpowerproductioncomment() {
        return heatandpowerproductioncomment;
    }

    public void setHeatandpowerproductioncomment(String heatandpowerproductioncomment) {
        this.heatandpowerproductioncomment = heatandpowerproductioncomment;
    }

    public String getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(String agriculture) {
        this.agriculture = agriculture;
    }

    public String getAgriculturecomment() {
        return agriculturecomment;
    }

    public void setAgriculturecomment(String agriculturecomment) {
        this.agriculturecomment = agriculturecomment;
    }

    public String getCommercialandresidential() {
        return commercialandresidential;
    }

    public void setCommercialandresidential(String commercialandresidential) {
        this.commercialandresidential = commercialandresidential;
    }

    public String getCommercialandresidentialcomment() {
        return commercialandresidentialcomment;
    }

    public void setCommercialandresidentialcomment(String commercialandresidentialcomment) {
        this.commercialandresidentialcomment = commercialandresidentialcomment;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getShippingcomment() {
        return shippingcomment;
    }

    public void setShippingcomment(String shippingcomment) {
        this.shippingcomment = shippingcomment;
    }

    public String getOffroadmobilemachinery() {
        return offroadmobilemachinery;
    }

    public void setOffroadmobilemachinery(String offroadmobilemachinery) {
        this.offroadmobilemachinery = offroadmobilemachinery;
    }

    public String getOffroadmobilemachinerycomment() {
        return offroadmobilemachinerycomment;
    }

    public void setOffroadmobilemachinerycomment(String offroadmobilemachinerycomment) {
        this.offroadmobilemachinerycomment = offroadmobilemachinerycomment;
    }

    public String getNaturallocalincrement() {
        return naturallocalincrement;
    }

    public void setNaturallocalincrement(String naturallocalincrement) {
        this.naturallocalincrement = naturallocalincrement;
    }

    public String getNaturallocalincrementcomment() {
        return naturallocalincrementcomment;
    }

    public void setNaturallocalincrementcomment(String naturallocalincrementcomment) {
        this.naturallocalincrementcomment = naturallocalincrementcomment;
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

    public boolean isTraffic_nil() {
        return traffic_nil;
    }

    public void setTraffic_nil(boolean traffic_nil) {
        this.traffic_nil = traffic_nil;
    }

    public String getTraffic_nilreason() {
        return traffic_nilreason;
    }

    public void setTraffic_nilreason(String traffic_nilreason) {
        this.traffic_nilreason = traffic_nilreason;
    }

    public boolean isHeatandpowerproduction_nil() {
        return heatandpowerproduction_nil;
    }

    public void setHeatandpowerproduction_nil(boolean heatandpowerproduction_nil) {
        this.heatandpowerproduction_nil = heatandpowerproduction_nil;
    }

    public String getHeatandpowerproduction_nilreason() {
        return heatandpowerproduction_nilreason;
    }

    public void setHeatandpowerproduction_nilreason(String heatandpowerproduction_nilreason) {
        this.heatandpowerproduction_nilreason = heatandpowerproduction_nilreason;
    }

    public boolean isAgriculture_nil() {
        return agriculture_nil;
    }

    public void setAgriculture_nil(boolean agriculture_nil) {
        this.agriculture_nil = agriculture_nil;
    }

    public String getAgriculture_nilreason() {
        return agriculture_nilreason;
    }

    public void setAgriculture_nilreason(String agriculture_nilreason) {
        this.agriculture_nilreason = agriculture_nilreason;
    }

    public boolean isCommercialandresidential_nil() {
        return commercialandresidential_nil;
    }

    public void setCommercialandresidential_nil(boolean commercialandresidential_nil) {
        this.commercialandresidential_nil = commercialandresidential_nil;
    }

    public String getCommercialandresidential_nilreason() {
        return commercialandresidential_nilreason;
    }

    public void setCommercialandresidential_nilreason(String commercialandresidential_nilreason) {
        this.commercialandresidential_nilreason = commercialandresidential_nilreason;
    }

    public boolean isShipping_nil() {
        return shipping_nil;
    }

    public void setShipping_nil(boolean shipping_nil) {
        this.shipping_nil = shipping_nil;
    }

    public String getShipping_nilreason() {
        return shipping_nilreason;
    }

    public void setShipping_nilreason(String shipping_nilreason) {
        this.shipping_nilreason = shipping_nilreason;
    }

    public boolean isOffroadmobilemachinery_nil() {
        return offroadmobilemachinery_nil;
    }

    public void setOffroadmobilemachinery_nil(boolean offroadmobilemachinery_nil) {
        this.offroadmobilemachinery_nil = offroadmobilemachinery_nil;
    }

    public String getOffroadmobilemachinery_nilreason() {
        return offroadmobilemachinery_nilreason;
    }

    public void setOffroadmobilemachinery_nilreason(String offroadmobilemachinery_nilreason) {
        this.offroadmobilemachinery_nilreason = offroadmobilemachinery_nilreason;
    }

    public boolean isNaturallocalincrement_nil() {
        return naturallocalincrement_nil;
    }

    public void setNaturallocalincrement_nil(boolean naturallocalincrement_nil) {
        this.naturallocalincrement_nil = naturallocalincrement_nil;
    }

    public String getNaturallocalincrement_nilreason() {
        return naturallocalincrement_nilreason;
    }

    public void setNaturallocalincrement_nilreason(String naturallocalincrement_nilreason) {
        this.naturallocalincrement_nilreason = naturallocalincrement_nilreason;
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
