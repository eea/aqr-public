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
package eu.europa.ec.sourceapprotionment.exceedancedescription.exceedancearea;

import eu.europa.ec.sourceapprotionment.SourceapportionmentManager;
import eu.europa.ec.aqrmodel.Areaclassification;
import eu.europa.ec.aqrmodel.Exceedancearea;
import eu.europa.ec.sourceapprotionment.areaclassification.AreaclassificationWrapper;
import java.io.Serializable;
import java.util.List;

public class ExceedanceareaWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    public ExceedanceareaWrapper() {
    }

    public static ExceedanceareaBean convertExceedanceareaInExceedanceareaBean(Exceedancearea exceedancearea) {
        ExceedanceareaBean exceedanceareaBean = new ExceedanceareaBean();

        exceedanceareaBean.setUuid(exceedancearea.getUuid());

        exceedanceareaBean.setArea(exceedancearea.getArea());
        exceedanceareaBean.setAreaestimate(exceedancearea.getAreaestimate());

        exceedanceareaBean.setRoadlenghtestimate(exceedancearea.getRoadlenghtestimate());
        exceedanceareaBean.setAdministrativeunits(exceedancearea.getAdministrativeunits());
        exceedanceareaBean.setModelused(exceedancearea.getModelused());
        exceedanceareaBean.setStationused(exceedancearea.getStationused());

        SourceapportionmentManager sourceapportionmentManager = new SourceapportionmentManager();
        List<Areaclassification> areaclassificationList = sourceapportionmentManager.getAreaclassificationListByExceedancearea(exceedancearea);
        if (!areaclassificationList.isEmpty()) {
            exceedanceareaBean.setAreaclassificationList_uri(AreaclassificationWrapper.convertAreaclassificationListUriInAreaclassificationBeanListUri(areaclassificationList));
        } else {
            exceedanceareaBean.setAreaclassificationList_uri(null);
        }

        return exceedanceareaBean;
    }

}
