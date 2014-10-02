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
package eu.europa.ec.measures.expectedimpact;

import eu.europa.ec.aqrmodel.Expectedimpact;
import eu.europa.ec.aqrmodel.Specificationofhours;

public class ExpectedimpactWrapper {

    public static ExpectedimpactBean convertExpectedimpactInExpectedimpactBean(Expectedimpact expectedimpact) {
        ExpectedimpactBean expectedimpactBean = new ExpectedimpactBean();

        expectedimpactBean.setUuid(expectedimpact.getUuid());

        expectedimpactBean.setLevelofconcentration(expectedimpact.getLevelofconcentration());
        expectedimpactBean.setNumberofexceedence(expectedimpact.getNumberofexceedence());

        expectedimpactBean.setComment(expectedimpact.getComment());

        Specificationofhours specificationofhours = expectedimpact.getSpecificationofhours();

        if (specificationofhours != null) {
            expectedimpactBean.setSpecificationofhours_uri(specificationofhours.getUri());
            expectedimpactBean.setSpecificationofhours_label(specificationofhours.getLabel());
        } else {
            expectedimpactBean.setSpecificationofhours_uri(null);
            expectedimpactBean.setSpecificationofhours_label(null);
        }

        return expectedimpactBean;
    }
}
