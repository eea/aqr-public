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

import eu.europa.ec.measures.MeasuresBean;
import eu.europa.ec.measures.MeasuresManager;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Represents aqd:AQD_MeasuresPropertyType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AQD_MeasuresPropertyType {

    @XmlAttribute(namespace = Namespaces.xlink)
    protected String href;

    public AQD_MeasuresPropertyType populate(String id, String namespace, String userEmail) {
        if (id != null) {
            MeasuresBean mb = new MeasuresManager().getMeasureByID(id, userEmail);
            if (namespace.charAt(namespace.length() - 1) != '/') {
                namespace += '/';
            }
            if (mb != null) {
                href = namespace + mb.getInspireidLocalid();
            }
        }
        return this;
    }

    public String getHref() {
        return href;
    }
}
