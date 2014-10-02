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
package eu.europa.ec.sourceapprotionment.urbanbackground;

import eu.europa.ec.aqrmodel.Urbanbackground;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.StringUtils;

public class UrbanbackgroundWrapper {

    private static final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public UrbanbackgroundWrapper() {
    }

    public static UrbanbackgroundBean convertUrbanbackgroundInUrbanbackgroundBean(Urbanbackground urbanbackground) {
        UrbanbackgroundBean urbanbackgroundBean = new UrbanbackgroundBean();

        urbanbackgroundBean.setUuid(urbanbackground.getUuid());

        urbanbackgroundBean.setTotal(urbanbackground.getTotal());
        urbanbackgroundBean.setTotalcomment(urbanbackground.getTotalcomment());
        urbanbackgroundBean.setTotal_nil(urbanbackground.getTotalNil());
        urbanbackgroundBean.setTotal_nilreason(urbanbackground.getTotalNilreason());

        urbanbackgroundBean.setTraffic(urbanbackground.getTraffic());
        urbanbackgroundBean.setTrafficcomment(urbanbackground.getTrafficcomment());
        urbanbackgroundBean.setTraffic_nil(urbanbackground.getTrafficNil());
        urbanbackgroundBean.setTraffic_nilreason(urbanbackground.getTrafficNilreason());

        urbanbackgroundBean.setHeatandpowerproduction(urbanbackground.getHeatandpowerproduction());
        urbanbackgroundBean.setHeatandpowerproductioncomment(urbanbackground.getHeatandpowerproductioncomment());
        urbanbackgroundBean.setHeatandpowerproduction_nil(urbanbackground.getHeatandpowerproductionNil());
        urbanbackgroundBean.setHeatandpowerproduction_nilreason(urbanbackground.getHeatandpowerproductionNilreason());

        urbanbackgroundBean.setAgriculture(urbanbackground.getAgriculture());
        urbanbackgroundBean.setAgriculturecomment(urbanbackground.getAgriculturecomment());
        urbanbackgroundBean.setAgriculture_nil(urbanbackground.getAgricultureNil());
        urbanbackgroundBean.setAgriculture_nilreason(urbanbackground.getAgricultureNilreason());

        urbanbackgroundBean.setCommercialandresidential(urbanbackground.getCommercialandresidential());
        urbanbackgroundBean.setCommercialandresidentialcomment(urbanbackground.getCommercialandresidentialcomment());
        urbanbackgroundBean.setCommercialandresidential_nil(urbanbackground.getCommercialandresidentialNil());
        urbanbackgroundBean.setCommercialandresidential_nilreason(urbanbackground.getCommercialandresidentialNilreason());

        urbanbackgroundBean.setShipping(urbanbackground.getShipping());
        urbanbackgroundBean.setShippingcomment(urbanbackground.getShippingcomment());
        urbanbackgroundBean.setShipping_nil(urbanbackground.getShippingNil());
        urbanbackgroundBean.setShipping_nilreason(urbanbackground.getShippingNilreason());

        urbanbackgroundBean.setOffroadmobilemachinery(urbanbackground.getOffroadmobilemachinery());
        urbanbackgroundBean.setOffroadmobilemachinerycomment(urbanbackground.getOffroadmobilemachinerycomment());
        urbanbackgroundBean.setOffroadmobilemachinery_nil(urbanbackground.getOffroadmobilemachineryNil());
        urbanbackgroundBean.setOffroadmobilemachinery_nilreason(urbanbackground.getOffroadmobilemachineryNilreason());

        urbanbackgroundBean.setNaturalurbanbackground(urbanbackground.getNaturalurbanbackground());
        urbanbackgroundBean.setNaturalurbanbackgroundcomment(urbanbackground.getNaturalurbanbackgroundcomment());
        urbanbackgroundBean.setNaturalurbanbackground_nil(urbanbackground.getNaturalurbanbackgroundNil());
        urbanbackgroundBean.setNaturalurbanbackground_nilreason(urbanbackground.getNaturalurbanbackgroundNilreason());

        urbanbackgroundBean.setTransboundary(urbanbackground.getTransboundary());
        urbanbackgroundBean.setTransboundarycomment(urbanbackground.getTransboundarycomment());
        urbanbackgroundBean.setTransboundary_nil(urbanbackground.getTransboundaryNil());
        urbanbackgroundBean.setTransboundary_nilreason(urbanbackground.getTransboundaryNilreason());

        urbanbackgroundBean.setOther(urbanbackground.getOther());
        urbanbackgroundBean.setOthercomment(urbanbackground.getOthercomment());
        urbanbackgroundBean.setOther_nil(urbanbackground.getOtherNil());
        urbanbackgroundBean.setOther_nilreason(urbanbackground.getOtherNilreason());

        urbanbackgroundBean.setUnitmisure(urbanbackground.getUnitmisure());
        urbanbackgroundBean.setUnitmisurecomment(urbanbackground.getUnitmisurecomment());
        urbanbackgroundBean.setUnitmisure_nil(urbanbackground.getUnitmisureNil());
        urbanbackgroundBean.setUnitmisure_nilreason(urbanbackground.getUnitmisureNilreason());

        return urbanbackgroundBean;
    }

    public static Urbanbackground createDraftUrbanbackgroundForSourceapportionment(String sourceapportionmentUuid) throws Exception {
        Urbanbackground urbanbackground = new Urbanbackground();

        String urbanbackgroundUuid = StringUtils.createUUID(sourceapportionmentUuid + dateFormatUtil.getToday(), Urbanbackground.class);
        urbanbackground.setUuid(urbanbackgroundUuid);

        urbanbackground.setTotal("");
        urbanbackground.setTotalNil(Boolean.FALSE);

        urbanbackground.setTraffic("");
        urbanbackground.setTrafficNil(Boolean.FALSE);

        urbanbackground.setHeatandpowerproduction("");
        urbanbackground.setHeatandpowerproductionNil(Boolean.FALSE);

        urbanbackground.setAgriculture("");
        urbanbackground.setAgricultureNil(Boolean.FALSE);

        urbanbackground.setCommercialandresidential("");
        urbanbackground.setCommercialandresidentialNil(Boolean.FALSE);

        urbanbackground.setShipping("");
        urbanbackground.setShippingNil(Boolean.FALSE);

        urbanbackground.setOffroadmobilemachinery("");
        urbanbackground.setOffroadmobilemachineryNil(Boolean.FALSE);

        urbanbackground.setNaturalurbanbackground("");
        urbanbackground.setNaturalurbanbackgroundNil(Boolean.FALSE);

        urbanbackground.setTransboundary("");
        urbanbackground.setTransboundaryNil(Boolean.FALSE);

        urbanbackground.setOtherNil(Boolean.FALSE);

        urbanbackground.setUnitmisureNil(Boolean.FALSE);

        return urbanbackground;
    }

}
