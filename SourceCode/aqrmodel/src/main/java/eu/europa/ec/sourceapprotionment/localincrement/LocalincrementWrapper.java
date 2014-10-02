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

import eu.europa.ec.aqrmodel.Localincrement;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.StringUtils;

public class LocalincrementWrapper {

    private static final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public LocalincrementWrapper() {
    }

    public static LocalincrementBean convertUrbanbackgroundInUrbanbackgroundBean(Localincrement localincrement) {
        LocalincrementBean localincrementBean = new LocalincrementBean();

        localincrementBean.setUuid(localincrement.getUuid());

        localincrementBean.setTotal(localincrement.getTotal());
        localincrementBean.setTotalcomment(localincrement.getTotalcomment());
        localincrementBean.setTotal_nil(localincrement.getTotalNil());
        localincrementBean.setTotal_nilreason(localincrement.getTotalNilreason());

        localincrementBean.setTraffic(localincrement.getTraffic());
        localincrementBean.setTrafficcomment(localincrement.getTrafficcomment());
        localincrementBean.setTraffic_nil(localincrement.getTrafficNil());
        localincrementBean.setTraffic_nilreason(localincrement.getTrafficNilreason());

        localincrementBean.setHeatandpowerproduction(localincrement.getHeatandpowerproduction());
        localincrementBean.setHeatandpowerproductioncomment(localincrement.getHeatandpowerproductioncomment());
        localincrementBean.setHeatandpowerproduction_nil(localincrement.getHeatandpowerproductionNil());
        localincrementBean.setHeatandpowerproduction_nilreason(localincrement.getHeatandpowerproductionNilreason());

        localincrementBean.setAgriculture(localincrement.getAgriculture());
        localincrementBean.setAgriculturecomment(localincrement.getAgriculturecomment());
        localincrementBean.setAgriculture_nil(localincrement.getAgricultureNil());
        localincrementBean.setAgriculture_nilreason(localincrement.getAgricultureNilreason());

        localincrementBean.setCommercialandresidential(localincrement.getCommercialandresidential());
        localincrementBean.setCommercialandresidentialcomment(localincrement.getCommercialandresidentialcomment());
        localincrementBean.setCommercialandresidential_nil(localincrement.getCommercialandresidentialNil());
        localincrementBean.setCommercialandresidential_nilreason(localincrement.getCommercialandresidentialNilreason());

        localincrementBean.setShipping(localincrement.getShipping());
        localincrementBean.setShippingcomment(localincrement.getShippingcomment());
        localincrementBean.setShipping_nil(localincrement.getShippingNil());
        localincrementBean.setShipping_nilreason(localincrement.getShippingNilreason());

        localincrementBean.setOffroadmobilemachinery(localincrement.getOffroadmobilemachinery());
        localincrementBean.setOffroadmobilemachinerycomment(localincrement.getOffroadmobilemachinerycomment());
        localincrementBean.setOffroadmobilemachinery_nil(localincrement.getOffroadmobilemachineryNil());
        localincrementBean.setOffroadmobilemachinery_nilreason(localincrement.getOffroadmobilemachineryNilreason());

        localincrementBean.setNaturallocalincrement(localincrement.getNaturallocalincrement());
        localincrementBean.setNaturallocalincrementcomment(localincrement.getNaturallocalincrementcomment());
        localincrementBean.setNaturallocalincrement_nil(localincrement.getNaturallocalincrementNil());
        localincrementBean.setNaturallocalincrement_nilreason(localincrement.getNaturallocalincrementNilreason());

        localincrementBean.setTransboundary(localincrement.getTransboundary());
        localincrementBean.setTransboundarycomment(localincrement.getTransboundarycomment());
        localincrementBean.setTransboundary_nil(localincrement.getTransboundaryNil());
        localincrementBean.setTransboundary_nilreason(localincrement.getTransboundaryNilreason());

        localincrementBean.setOther(localincrement.getOther());
        localincrementBean.setOthercomment(localincrement.getOthercomment());
        localincrementBean.setOther_nil(localincrement.getOtherNil());
        localincrementBean.setOther_nilreason(localincrement.getOtherNilreason());

        localincrementBean.setUnitmisure(localincrement.getUnitmisure());
        localincrementBean.setUnitmisurecomment(localincrement.getUnitmisurecomment());
        localincrementBean.setUnitmisure_nil(localincrement.getUnitmisureNil());
        localincrementBean.setUnitmisure_nilreason(localincrement.getUnitmisureNilreason());

        return localincrementBean;
    }

    public static Localincrement createDraftLocalincrementForSourceapportionment(String sourceapportionmentUuid) throws Exception {
        Localincrement localincrement = new Localincrement();

        String localincrementUuid = StringUtils.createUUID(sourceapportionmentUuid + dateFormatUtil.getToday(), Localincrement.class);
        localincrement.setUuid(localincrementUuid);

        localincrement.setTotal("");
        localincrement.setTotalNil(Boolean.FALSE);

        localincrement.setTraffic("");
        localincrement.setTrafficNil(Boolean.FALSE);

        localincrement.setHeatandpowerproduction("");
        localincrement.setHeatandpowerproductionNil(Boolean.FALSE);

        localincrement.setAgriculture("");
        localincrement.setAgricultureNil(Boolean.FALSE);

        localincrement.setCommercialandresidential("");
        localincrement.setCommercialandresidentialNil(Boolean.FALSE);

        localincrement.setShipping("");
        localincrement.setShippingNil(Boolean.FALSE);

        localincrement.setOffroadmobilemachinery("");
        localincrement.setOffroadmobilemachineryNil(Boolean.FALSE);

        localincrement.setNaturallocalincrement("");
        localincrement.setNaturallocalincrementNil(Boolean.FALSE);

        localincrement.setTransboundary("");
        localincrement.setTransboundaryNil(Boolean.FALSE);

        localincrement.setOtherNil(Boolean.FALSE);

        localincrement.setUnitmisureNil(Boolean.FALSE);

        return localincrement;
    }
}
