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
package eu.europa.ec.aqrsystem.xml.gml;

import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Represents gml:MeasureType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class MeasureType {

    protected static final String UNKNOWN = "Unknown";

    @XmlAttribute
    protected String uom;
    @XmlAttribute
    protected String nilReason;
    @XmlAttribute(namespace = Namespaces.xsi)
    protected String nil;
    @XmlValue
    protected String value;

    public void populate(String value, String unit, boolean nil, String nilReason, boolean nillable) {
        if (!nil) {
            if (BaseUtils.isDefined(unit)) {
                uom = unit;
            } else {
                uom = UNKNOWN;
            }
            if (BaseUtils.isDefined(value)) {
                this.value = value;
            } else {
                this.value = "0";
            }
            if (nillable) {
                this.nil = "false";
            }
        } else {
            this.nilReason = nilReason;
            uom = UNKNOWN;
            this.nil = "true";
        }
    }

    public String getUoM() {
        return UNKNOWN.equals(uom) ? null : uom;
    }

    public String getValue() {
        return value;
    }

    public boolean isNilReason() {
        return BaseUtils.isDefined(nilReason);
    }

    public String getNilReason() {
        return nilReason;
    }
}
