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
package eu.europa.ec.sourceapprotionment.reasonvalue;

import eu.europa.ec.aqrmodel.Reasonvalue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReasonvalueWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReasonvalueWrapper() {
    }

    public static List<ReasonvalueBean> convertReasonvalueListInReasonvalueBeanList(List<Reasonvalue> reasonvalueList) {
        List<ReasonvalueBean> reasonvalueBeanList = new ArrayList<ReasonvalueBean>();

        for (Reasonvalue reasonvalue : reasonvalueList) {
            reasonvalueBeanList.add(convertReasonvalueInReasonvalueBean(reasonvalue));
        }

        return reasonvalueBeanList;
    }

    public static ReasonvalueBean convertReasonvalueInReasonvalueBean(Reasonvalue reasonvalue) {
        ReasonvalueBean reasonvalueBean = new ReasonvalueBean();

        reasonvalueBean.setUuid(reasonvalue.getUuid());
        reasonvalueBean.setUri(reasonvalue.getUri());
        reasonvalueBean.setLabel(reasonvalue.getLabel());
        reasonvalueBean.setDefinition(reasonvalue.getDefinition());
        reasonvalueBean.setNotation(reasonvalue.getNotation());

        return reasonvalueBean;
    }
}
