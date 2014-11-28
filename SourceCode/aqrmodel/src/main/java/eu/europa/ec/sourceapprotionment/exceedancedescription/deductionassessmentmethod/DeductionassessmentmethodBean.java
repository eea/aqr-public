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

import eu.europa.ec.sourceapprotionment.assesmentmethods.AssesmentmethodsBean;
import java.io.Serializable;
import java.util.List;

public class DeductionassessmentmethodBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private String adjustmenttype_uri;
    private List<String> adjustmentsourceList_uri;
    private List<AssesmentmethodsBean> assesmentmethodsBeanList;

    public DeductionassessmentmethodBean() {
    }

    public DeductionassessmentmethodBean(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAdjustmenttype_uri() {
        return adjustmenttype_uri;
    }

    public void setAdjustmenttype_uri(String adjustmenttype_uri) {
        this.adjustmenttype_uri = adjustmenttype_uri;
    }

    public List<String> getAdjustmentsourceList_uri() {
        return adjustmentsourceList_uri;
    }

    public void setAdjustmentsourceList_uri(List<String> adjustmentsourceList_uri) {
        this.adjustmentsourceList_uri = adjustmentsourceList_uri;
    }

    public List<AssesmentmethodsBean> getAssesmentmethodsBeanList() {
        return assesmentmethodsBeanList;
    }

    public void setAssesmentmethodsBeanList(List<AssesmentmethodsBean> assesmentmethodsBeanList) {
        this.assesmentmethodsBeanList = assesmentmethodsBeanList;
    }
}
