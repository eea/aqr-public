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

import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.gml.MeasureType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:QuantityCommentedType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class QuantityCommentedType {

    @XmlElement(namespace = Namespaces.aqd)
    protected MeasureType quantity = new MeasureType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;

    public void populate(String value, String unit, String comment, boolean nil, String nilReason) {
        quantity.populate(value, unit, nil, nilReason, true);
        if (BaseUtils.isDefined(comment)) {
            this.comment = comment;
        }
    }

    public String getUoM() {
        return quantity.getUoM();
    }

    public String getValue() {
        return quantity.getValue();
    }

    public boolean isNilReason() {
        return quantity.isNilReason();
    }

    public String getNilReason() {
        return quantity.getNilReason();
    }

    public String getComment() {
        return comment;
    }
}
