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
import eu.europa.ec.sourceapprotionment.exceedancedescription.deductionassessmentmethod.DeductionassessmentmethodBean;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:AdjustmentMethodType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AdjustmentMethodType {

    @XmlElement(namespace = Namespaces.aqd)
    protected List<AssessmentMethodsPropertyType> assessmentMethod = new ArrayList<AssessmentMethodsPropertyType>();
    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType adjustmentType = new ReferenceType();
    @XmlElement(namespace = Namespaces.aqd)
    protected List<ReferenceType> adjustmentSource = new ArrayList<ReferenceType>();

    public void populate(DeductionassessmentmethodBean p, String namespace) {
        adjustmentType.setHref(p.getAdjustmenttype_uri(), false);
        if (p.getAdjustmentsourceList_uri() != null) {
            for (String uri : p.getAdjustmentsourceList_uri()) {
                adjustmentSource.add(new ReferenceType().setHref(uri, false));
            }
        }
        if (p.getAssesmentmethodsBeanList() != null) {
            for (AssesmentmethodsBean b : p.getAssesmentmethodsBeanList()) {
                assessmentMethod.add(new AssessmentMethodsPropertyType().populate(b, namespace));
            }
        }
    }

    public void setObject(DeductionassessmentmethodBean p, String sourceId) throws Exception {
        if (assessmentMethod != null) {
            for (AssessmentMethodsPropertyType ampt : assessmentMethod) {
                ampt.save(sourceId);
            }
        }

        p.setAdjustmenttype_uri(adjustmentType.getHref());

        p.setAdjustmentsourceList_uri(new ArrayList<String>());
        if (adjustmentSource != null) {
            for (ReferenceType as : adjustmentSource) {
                p.getAdjustmentsourceList_uri().add(as.getHref());
            }
        }
    }
}
