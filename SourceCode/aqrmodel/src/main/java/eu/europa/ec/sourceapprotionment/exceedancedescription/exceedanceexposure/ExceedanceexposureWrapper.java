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
package eu.europa.ec.sourceapprotionment.exceedancedescription.exceedanceexposure;

import eu.europa.ec.aqrmodel.Exceedanceexposure;
import java.io.Serializable;

public class ExceedanceexposureWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    public ExceedanceexposureWrapper() {
    }

    public static ExceedanceexposureBean convertExceedanceexposureInExceedanceexposureBean(Exceedanceexposure exceedanceexposure) {
        ExceedanceexposureBean exceedanceexposureBean = new ExceedanceexposureBean();

        exceedanceexposureBean.setUuid(exceedanceexposure.getUuid());

        exceedanceexposureBean.setExposedpopulation(exceedanceexposure.getExposedpopulation());
        exceedanceexposureBean.setExposedarea(exceedanceexposure.getExposedarea());

        exceedanceexposureBean.setSensitiveresidentpopulation(exceedanceexposure.getSensitiveresidentpopulation());
        exceedanceexposureBean.setRelevantinfrastructure(exceedanceexposure.getRelevantinfrastructure());

        exceedanceexposureBean.setReferenceyear(exceedanceexposure.getReferenceyear());

        return exceedanceexposureBean;
    }
}
