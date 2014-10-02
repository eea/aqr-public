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

import eu.europa.ec.aqrsystem.xml.Namespaces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import org.apache.log4j.Logger;

/**
 * Represents gml:TimePeriodType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class TimePeriodType {

    protected Logger log = Logger.getLogger(getClass());
    protected static final String SUFFIX = "T00:00:00Z";

    @XmlAttribute(namespace = Namespaces.gml)
    protected String id;
    @XmlElement(namespace = Namespaces.gml)
    protected String beginPosition;
    @XmlElement(namespace = Namespaces.gml)
    protected String endPosition;

    public void populate(String id, String beginPosition, String endPosition) {
        this.id = id;
        this.beginPosition = beginPosition + SUFFIX;
        this.endPosition = endPosition + SUFFIX;
    }

    public String getBeginPosition() {
        return dropTime(beginPosition);
    }

    public String getEndPosition() {
        return dropTime(endPosition);
    }

    /**
     * Dropping everything after 'T' in th string
     *
     * @param v
     * @return
     */
    private String dropTime(String v) {
        int index = v.indexOf('T');
        return index == -1 ? v : v.substring(0, index);
    }
}
