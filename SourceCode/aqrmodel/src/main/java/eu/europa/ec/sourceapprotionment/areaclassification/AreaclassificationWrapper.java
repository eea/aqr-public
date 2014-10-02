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
 *  Daniele Francioli, Emanuela Epure
 *  aq-dev@jrc.ec.europa.eu
 */
package eu.europa.ec.sourceapprotionment.areaclassification;

import eu.europa.ec.aqrmodel.Areaclassification;
import java.util.ArrayList;
import java.util.List;

public class AreaclassificationWrapper {

    public static List<String> convertAreaclassificationListUriInAreaclassificationBeanListUri(List<Areaclassification> areaclassificationList) {
        List<String> areaclassificationBeanList = new ArrayList<String>();

        for (Areaclassification areaclassification : areaclassificationList) {
            areaclassificationBeanList.add(convertAreaclassificationInAreaclassificationBean(areaclassification).getUri());
        }

        return areaclassificationBeanList;
    }

    public static AreaclassificationBean convertAreaclassificationInAreaclassificationBean(Areaclassification areaclassification) {
        AreaclassificationBean areaclassificationBean = new AreaclassificationBean();

        areaclassificationBean.setUuid(areaclassification.getUuid());
        areaclassificationBean.setUri(areaclassification.getUri());
        areaclassificationBean.setLabel(areaclassification.getLabel());
        areaclassificationBean.setDefinition(areaclassification.getDefinition());
        areaclassificationBean.setNotation(areaclassification.getNotation());

        return areaclassificationBean;
    }
}
