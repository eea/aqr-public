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

import eu.europa.ec.sourceapprotionment.regionalbackground.RegionalbackgroundBean;
import eu.europa.ec.aqrmodel.Regionalbackground;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.StringUtils;

public class RegionalbackgroundWrapper {

    private static final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public RegionalbackgroundWrapper() {
    }

    public static RegionalbackgroundBean convertRegionalbackgroundInRegionalbackgroundBean(Regionalbackground regionalbackground) {
        RegionalbackgroundBean regionalbackgroundBean = new RegionalbackgroundBean();

        regionalbackgroundBean.setUuid(regionalbackground.getUuid());

        regionalbackgroundBean.setTotal(regionalbackground.getTotal());
        regionalbackgroundBean.setTotalcomment(regionalbackground.getTotalcomment());
        regionalbackgroundBean.setTotal_nil(regionalbackground.getTotalNil());
        regionalbackgroundBean.setTotal_nilreason(regionalbackground.getTotalNilreason());

        regionalbackgroundBean.setFromwithinms(regionalbackground.getFromwithinms());
        regionalbackgroundBean.setFromwithinmscomment(regionalbackground.getFromwithinmscomment());
        regionalbackgroundBean.setFromwithinms_nil(regionalbackground.getFromwithinmsNil());
        regionalbackgroundBean.setFromwithinms_nilreason(regionalbackground.getFromwithinmsNilreason());

        regionalbackgroundBean.setNaturalregionalbackground(regionalbackground.getNaturalregionalbackground());
        regionalbackgroundBean.setNaturalregionalbackgroundcomment(regionalbackground.getNaturalregionalbackgroundcomment());
        regionalbackgroundBean.setNaturalregionalbackground_nil(regionalbackground.getNaturalregionalbackgroundNil());
        regionalbackgroundBean.setNaturalregionalbackground_nilreason(regionalbackground.getNaturalregionalbackgroundNilreason());

        regionalbackgroundBean.setTransboundary(regionalbackground.getTransboundary());
        regionalbackgroundBean.setTransboundarycomment(regionalbackground.getTransboundarycomment());
        regionalbackgroundBean.setTransboundary_nil(regionalbackground.getTransboundaryNil());
        regionalbackgroundBean.setTransboundary_nilreason(regionalbackground.getTransboundaryNilreason());

        regionalbackgroundBean.setOther(regionalbackground.getOther());
        regionalbackgroundBean.setOthercomment(regionalbackground.getOthercomment());
        regionalbackgroundBean.setOther_nil(regionalbackground.getOtherNil());
        regionalbackgroundBean.setOther_nilreason(regionalbackground.getOtherNilreason());

        regionalbackgroundBean.setUnitmisure(regionalbackground.getUnitmisure());
        regionalbackgroundBean.setUnitmisurecomment(regionalbackground.getUnitmisurecomment());
        regionalbackgroundBean.setUnitmisure_nil(regionalbackground.getUnitmisureNil());
        regionalbackgroundBean.setUnitmisure_nilreason(regionalbackground.getUnitmisureNilreason());

        return regionalbackgroundBean;
    }

    public static Regionalbackground createDraftRegionalbackgroundForSourceapportionment(String sourceapportionmentUuid) throws Exception {
        Regionalbackground regionalbackground = new Regionalbackground();

        String regionalbackgroundUuid = StringUtils.createUUID(sourceapportionmentUuid + dateFormatUtil.getToday(), Regionalbackground.class);
        regionalbackground.setUuid(regionalbackgroundUuid);

        regionalbackground.setTotal("");
        regionalbackground.setTotalNil(Boolean.FALSE);

        regionalbackground.setFromwithinms("");
        regionalbackground.setFromwithinmsNil(Boolean.FALSE);

        regionalbackground.setNaturalregionalbackground("");
        regionalbackground.setNaturalregionalbackgroundNil(Boolean.FALSE);

        regionalbackground.setTransboundary("");
        regionalbackground.setTransboundaryNil(Boolean.FALSE);

        regionalbackground.setOtherNil(Boolean.FALSE);

        regionalbackground.setUnitmisureNil(Boolean.FALSE);

        return regionalbackground;
    }
}
