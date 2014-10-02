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

import eu.europa.ec.sourceapprotionment.assesmentmethods.AssesmentmethodsBean;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:AssessmentMethodsPropertyType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AssessmentMethodsPropertyType {

    @XmlElement(namespace = Namespaces.aqd)
    protected AssessmentMethodsType AssessmentMethods = new AssessmentMethodsType();

    public AssessmentMethodsPropertyType populate(AssesmentmethodsBean p, String namespace) {
        AssessmentMethods.populate(p, namespace);
        return this;
    }

    public void save(String sourceId) throws Exception {
        AssessmentMethods.save(sourceId);
    }
}
