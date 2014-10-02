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
package eu.europa.ec.aqrsystem.xml.aqd;

import eu.europa.ec.measures.cost.CostsBean;
import eu.europa.ec.measures.MeasuresManager;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.NillableString;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Represents aqd:CostsType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class CostsType {

    @XmlElement(namespace = Namespaces.aqd)
    protected NillableString estimatedImplementationCosts = new NillableString();
    @XmlElement(namespace = Namespaces.aqd)
    protected String finalImplementationCosts;
    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType currency = new ReferenceType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;

    public void populate(CostsBean c) {
        if (!c.isExtimatedimplementationcosts_nil()) {
            estimatedImplementationCosts.populate(c.getExtimatedimplementationcosts(), false, null);
            if (BaseUtils.isDefined(c.getFinalimplementationcosts())) {
                finalImplementationCosts = c.getFinalimplementationcosts();
            }
            currency.setHref(c.getCurrency_uri(), true);
        } else {
            estimatedImplementationCosts.populate(null, true, c.getExtimatedimplementationcosts_nilreason());
            estimatedImplementationCosts.setValue("0");
            currency.setNilReason(c.getExtimatedimplementationcosts_nilreason());
        }
        if (BaseUtils.isDefined(c.getComment())) {
            comment = c.getComment();
        }
    }

    public void setObject(String measureId, ActionBeanContext context) throws Exception {
        MeasuresManager measureManger = new MeasuresManager();
        CostsBean c = new CostsBean();
        if (!estimatedImplementationCosts.isNilReason()) {
            c.setExtimatedimplementationcosts(estimatedImplementationCosts.getValue());
            c.setFinalimplementationcosts(finalImplementationCosts);
            c.setCurrency_uri(currency.getHref());
        } else {
            c.setExtimatedimplementationcosts_nil(true);
            c.setExtimatedimplementationcosts_nilreason(estimatedImplementationCosts.getNilReason());
        }
        c.setComment(comment);
        measureManger.saveCostByMeasueresID(measureId, c);
    }
}
