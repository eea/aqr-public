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
@XmlSchema(
        xmlns = {
            @XmlNs(namespaceURI = Namespaces.xsi, prefix = "xsi"),
            @XmlNs(namespaceURI = Namespaces.aqd, prefix = "aqd"),
            @XmlNs(namespaceURI = Namespaces.base, prefix = "base"),
            @XmlNs(namespaceURI = Namespaces.base2, prefix = "base2"),
            @XmlNs(namespaceURI = Namespaces.ef, prefix = "ef"),
            @XmlNs(namespaceURI = Namespaces.gml, prefix = "gml"),
            @XmlNs(namespaceURI = Namespaces.ompr, prefix = "ompr"),
            @XmlNs(namespaceURI = Namespaces.sam, prefix = "sam"),
            @XmlNs(namespaceURI = Namespaces.sams, prefix = "sams"),
            @XmlNs(namespaceURI = Namespaces.xlink, prefix = "xlink"),
            @XmlNs(namespaceURI = Namespaces.gmd, prefix = "gmd"),
            @XmlNs(namespaceURI = Namespaces.gco, prefix = "gco"),
            @XmlNs(namespaceURI = Namespaces.om, prefix = "om"),
            @XmlNs(namespaceURI = Namespaces.swe, prefix = "swe"),
            @XmlNs(namespaceURI = Namespaces.am, prefix = "am"),
            @XmlNs(namespaceURI = Namespaces.ad, prefix = "ad"),
            @XmlNs(namespaceURI = Namespaces.gn, prefix = "gn")
        },
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        attributeFormDefault = javax.xml.bind.annotation.XmlNsForm.UNQUALIFIED)

package eu.europa.ec.aqrsystem.xml;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;
