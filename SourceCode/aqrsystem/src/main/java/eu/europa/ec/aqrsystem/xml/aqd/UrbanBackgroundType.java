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
package eu.europa.ec.aqrsystem.xml.aqd;

import eu.europa.ec.sourceapprotionment.urbanbackground.UrbanbackgroundBean;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:UrbanBackgroundType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class UrbanBackgroundType {

    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType total = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType traffic = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType heatAndPowerProduction = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType agriculture = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType commercialAndResidential = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType shipping = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType offRoadMobileMachinery = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType natural = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType transboundary = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType other;

    public void populate(UrbanbackgroundBean p) {
        total.populate(p.getTotal(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getTotalcomment(), p.isTotal_nil(), p.getTotal_nilreason());
        traffic.populate(p.getTraffic(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getTrafficcomment(), p.isTraffic_nil(), p.getTraffic_nilreason());
        heatAndPowerProduction.populate(p.getHeatandpowerproduction(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getHeatandpowerproductioncomment(), p.isHeatandpowerproduction_nil(), p.getHeatandpowerproduction_nilreason());
        agriculture.populate(p.getAgriculture(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getAgriculturecomment(), p.isAgriculture_nil(), p.getAgriculture_nilreason());
        commercialAndResidential.populate(p.getCommercialandresidential(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getCommercialandresidentialcomment(), p.isCommercialandresidential_nil(), p.getCommercialandresidential_nilreason());
        shipping.populate(p.getShipping(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getShippingcomment(), p.isShipping_nil(), p.getShipping_nilreason());
        offRoadMobileMachinery.populate(p.getOffroadmobilemachinery(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getOffroadmobilemachinerycomment(), p.isOffroadmobilemachinery_nil(), p.getOffroadmobilemachinery_nilreason());
        natural.populate(p.getNaturalurbanbackground(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getNaturalurbanbackgroundcomment(), p.isNaturalurbanbackground_nil(), p.getNaturalurbanbackground_nilreason());
        transboundary.populate(p.getTransboundary(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getTransboundarycomment(), p.isTransboundary_nil(), p.getTransboundary_nilreason());
        if (BaseUtils.isDefined(p.getOther()) || p.isOther_nil()) {
            other = new QuantityCommentedPropertyType();
            other.populate(p.getOther(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getOthercomment(), p.isOther_nil(), p.getOther_nilreason());
        }
    }

    public void setObject(UrbanbackgroundBean p) {
        p.setTotal(total.getValue());
        p.setTotalcomment(total.getComment());
        p.setTotal_nil(total.isNilReason());
        p.setTotal_nilreason(total.getNilReason());

        p.setTraffic(traffic.getValue());
        p.setTrafficcomment(traffic.getComment());
        p.setTraffic_nil(traffic.isNilReason());
        p.setTraffic_nilreason(traffic.getNilReason());

        p.setHeatandpowerproduction(heatAndPowerProduction.getValue());
        p.setHeatandpowerproductioncomment(heatAndPowerProduction.getComment());
        p.setHeatandpowerproduction_nil(heatAndPowerProduction.isNilReason());
        p.setHeatandpowerproduction_nilreason(heatAndPowerProduction.getNilReason());

        p.setAgriculture(agriculture.getValue());
        p.setAgriculturecomment(agriculture.getComment());
        p.setAgriculture_nil(agriculture.isNilReason());
        p.setAgriculture_nilreason(agriculture.getNilReason());

        p.setCommercialandresidential(commercialAndResidential.getValue());
        p.setCommercialandresidentialcomment(commercialAndResidential.getComment());
        p.setCommercialandresidential_nil(commercialAndResidential.isNilReason());
        p.setCommercialandresidential_nilreason(commercialAndResidential.getNilReason());

        p.setShipping(shipping.getValue());
        p.setShippingcomment(shipping.getComment());
        p.setShipping_nil(shipping.isNilReason());
        p.setShipping_nilreason(shipping.getNilReason());

        p.setOffroadmobilemachinery(offRoadMobileMachinery.getValue());
        p.setOffroadmobilemachinerycomment(offRoadMobileMachinery.getComment());
        p.setOffroadmobilemachinery_nil(offRoadMobileMachinery.isNilReason());
        p.setOffroadmobilemachinery_nilreason(offRoadMobileMachinery.getNilReason());

        p.setNaturalurbanbackground(natural.getValue());
        p.setNaturalurbanbackgroundcomment(natural.getComment());
        p.setNaturalurbanbackground_nil(natural.isNilReason());
        p.setNaturalurbanbackground_nilreason(natural.getNilReason());

        p.setTransboundary(transboundary.getValue());
        p.setTransboundarycomment(transboundary.getComment());
        p.setTransboundary_nil(transboundary.isNilReason());
        p.setTransboundary_nilreason(transboundary.getNilReason());

        if (other != null) {
            p.setOther(other.getValue());
            p.setOthercomment(other.getComment());
            p.setOther_nil(other.isNilReason());
            p.setOther_nilreason(other.getNilReason());
        }
    }
}
