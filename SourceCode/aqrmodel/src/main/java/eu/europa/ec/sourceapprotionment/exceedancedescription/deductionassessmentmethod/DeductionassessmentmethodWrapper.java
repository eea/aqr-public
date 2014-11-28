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
package eu.europa.ec.sourceapprotionment.exceedancedescription.deductionassessmentmethod;

import eu.europa.ec.sourceapprotionment.assesmentmethods.AssesmentmethodsWrapper;
import eu.europa.ec.sourceapprotionment.SourceapportionmentManager;
import eu.europa.ec.aqrmodel.Adjustmentsource;
import eu.europa.ec.aqrmodel.Assesmentmethods;
import eu.europa.ec.aqrmodel.Deductionassessmentmethod;
import eu.europa.ec.sourceapprotionment.adjustmentsource.AdjustmentsourceWrapper;
import java.io.Serializable;
import java.util.List;

public class DeductionassessmentmethodWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    public DeductionassessmentmethodWrapper() {
    }

    public static DeductionassessmentmethodBean convertDeductionassessmentmethodBeanInDeductionassessmentmethodBeanBean(Deductionassessmentmethod deductionassessmentmethod) {
        DeductionassessmentmethodBean deductionassessmentmethodBean = new DeductionassessmentmethodBean();

        deductionassessmentmethodBean.setUuid(deductionassessmentmethod.getUuid());

        if (deductionassessmentmethod.getAdjustmenttype() != null) {
//            deductionassessmentmethodBean.setAdjustmenttype_uri(CodelistWrapper.convertAdjustmenttypeUriInAdjustmenttypeBeanUri(deductionassessmentmethod.getAdjustmenttype()));
            deductionassessmentmethodBean.setAdjustmenttype_uri(deductionassessmentmethod.getAdjustmenttype().getUri());
        } else {
            deductionassessmentmethodBean.setAdjustmenttype_uri(null);
        }

        SourceapportionmentManager sourceapportionmentManager = new SourceapportionmentManager();

        List<Adjustmentsource> adjustmentsourceList = sourceapportionmentManager.getAdjustmentsourceListByDeductionassessmentmethod(deductionassessmentmethod);
        if (!adjustmentsourceList.isEmpty()) {
            deductionassessmentmethodBean.setAdjustmentsourceList_uri(AdjustmentsourceWrapper.convertAdjustmentsourceListUriInAdjustmentsourceBeanListUri(adjustmentsourceList));
        } else {
            deductionassessmentmethodBean.setAdjustmentsourceList_uri(null);
        }

        List<Assesmentmethods> assesmentmethodsList = sourceapportionmentManager.getAssesmentmethodsListByDeductionassessmentmethod(deductionassessmentmethod);
        if (!assesmentmethodsList.isEmpty()) {
            deductionassessmentmethodBean.setAssesmentmethodsBeanList(AssesmentmethodsWrapper.convertAssesmentmethodsListInAssesmentmethodsBeanList(assesmentmethodsList));
        } else {
            deductionassessmentmethodBean.setAssesmentmethodsBeanList(null);
        }

        return deductionassessmentmethodBean;
    }
}
