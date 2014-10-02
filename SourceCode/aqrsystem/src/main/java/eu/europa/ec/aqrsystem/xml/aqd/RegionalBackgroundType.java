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

import eu.europa.ec.sourceapprotionment.regionalbackground.RegionalbackgroundBean;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:RegionalBackgroundType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class RegionalBackgroundType {

    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType total = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType fromWithinMS = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType transboundary = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType natural = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType other;

    public void populate(RegionalbackgroundBean p) {
        total.populate(p.getTotal(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getTotalcomment(), p.isTotal_nil(), p.getTotal_nilreason());
        fromWithinMS.populate(p.getFromwithinms(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getFromwithinmscomment(), p.isFromwithinms_nil(), p.getTotal_nilreason());
        transboundary.populate(p.getTransboundary(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getTransboundarycomment(), p.isTransboundary_nil(), p.getTransboundary_nilreason());
        natural.populate(p.getNaturalregionalbackground(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getNaturalregionalbackgroundcomment(), p.isNaturalregionalbackground_nil(), p.getNaturalregionalbackground_nilreason());
        if (BaseUtils.isDefined(p.getOther()) || p.isOther_nil()) {
            other = new QuantityCommentedPropertyType();
            other.populate(p.getOther(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", p.getOthercomment(), p.isOther_nil(), p.getOther_nilreason());
        }
    }

    public void setObject(RegionalbackgroundBean p) {
        p.setTotal(total.getValue());
        p.setTotalcomment(total.getComment());
        p.setTotal_nil(total.isNilReason());
        p.setTotal_nilreason(total.getNilReason());

        p.setFromwithinms(fromWithinMS.getValue());
        p.setFromwithinmscomment(fromWithinMS.getComment());
        p.setFromwithinms_nil(fromWithinMS.isNilReason());
        p.setFromwithinms_nilreason(fromWithinMS.getNilReason());

        p.setTransboundary(transboundary.getValue());
        p.setTransboundarycomment(transboundary.getComment());
        p.setTransboundary_nil(transboundary.isNilReason());
        p.setTransboundary_nilreason(transboundary.getNilReason());

        p.setNaturalregionalbackground(natural.getValue());
        p.setNaturalregionalbackgroundcomment(natural.getComment());
        p.setNaturalregionalbackground_nil(natural.isNilReason());
        p.setNaturalregionalbackground_nilreason(natural.getNilReason());

        if (other != null) {
            p.setOther(other.getValue());
            p.setOthercomment(other.getComment());
            p.setOther_nil(other.isNilReason());
            p.setOther_nilreason(other.getNilReason());
        }
    }
}
