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
package eu.europa.ec.measures.cost;

import eu.europa.ec.aqrmodel.Costs;
import eu.europa.ec.aqrmodel.Currency;

public class CostWrapper {

    public static CostsBean convertCostsInCostsBean(Costs costs) {
        CostsBean costsBean = new CostsBean();

        costsBean.setUuid(costs.getUuid());

        costsBean.setExtimatedimplementationcosts(costs.getExtimatedimplementationcosts());
        costsBean.setExtimatedimplementationcosts_nil(costs.getExtimatedimplementationcostsNil());
        if (costs.getExtimatedimplementationcostsNilreason() != null) {
            costsBean.setExtimatedimplementationcosts_nilreason(costs.getExtimatedimplementationcostsNilreason());
        } else {
            costsBean.setExtimatedimplementationcosts_nilreason("");
        }

        costsBean.setFinalimplementationcosts(costs.getFinalimplementationcosts());
        costsBean.setComment(costs.getComment());

        Currency currency = costs.getCurrency();
        if (currency != null) {
            costsBean.setCurrency_uri(currency.getUri());
            costsBean.setCurrency_label(currency.getLabel());
        } else {
            costsBean.setCurrency_uri(null);
            costsBean.setCurrency_label(null);
        }

        return costsBean;
    }
}
