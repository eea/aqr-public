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

import eu.europa.ec.common.HeaderInterface;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.base2.RelatedPartyPropertyType;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:AQD_ReportingHeaderType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AQD_ReportingHeaderType {

    @XmlAttribute(namespace = Namespaces.gml)
    protected String id;

    @XmlElement(namespace = Namespaces.aqd)
    protected boolean change;
    @XmlElement(namespace = Namespaces.aqd)
    protected String changeDescription;

    @XmlElement(namespace = Namespaces.aqd)
    protected IdentifierPropertyType inspireId = new IdentifierPropertyType();

    @XmlElement(namespace = Namespaces.aqd)
    protected RelatedPartyPropertyType reportingAuthority = new RelatedPartyPropertyType();

    @XmlElement(namespace = Namespaces.aqd)
    protected ReportingPeriod reportingPeriod = new ReportingPeriod();

    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType content = new ReferenceType();

    public void populate(HeaderInterface h) {
        id = "ATTR_HDR_" + XMLManager.removeProblematicCharacters(h.getInspireidLocalid());
        inspireId.populate(h, "HDR_" + h.getInspireidLocalid());
        reportingAuthority.populate(h.getProviderBean());
        change = h.isChanges();
        if (change) {
            changeDescription = h.getDescriptionofchanges();
        }
        reportingPeriod.populate(h);

        String namespace = h.getInspireidNamespace();
        if (namespace.charAt(namespace.length() - 1) != '/') {
            namespace += '/';
        }
        content.setHref(namespace + h.getInspireidLocalid(), false);
    }

    public void setObject(HeaderInterface p) {
        p.setChanges(change);
        p.setDescriptionofchanges(changeDescription);
        reportingAuthority.setObject(p.getProviderBean());
        p.setReportingstartdate(reportingPeriod.getBeginPosition());
        p.setReportingenddate(reportingPeriod.getEndPosition());
    }
}
