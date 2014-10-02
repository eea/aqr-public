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
import eu.europa.ec.sourceapprotionment.assesmenttype.AssesmenttypeBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentManager;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:AssessmentMethodsType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AssessmentMethodsType {

    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType assessmentType = new ReferenceType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String assessmentTypeDescription;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<AQD_ModelPropertyType> modelAssessmentMetadata;

    public void populate(AssesmentmethodsBean p, String namespace) {
        if (p.getAssesmenttypeBean() != null) {
            assessmentType.setHref(p.getAssesmenttypeBean().getUri(), false);
        }
        assessmentTypeDescription = p.getAssesmenttypedescription();
        if (BaseUtils.isDefined(p.getMetadata())) {
            modelAssessmentMetadata = new ArrayList<AQD_ModelPropertyType>();
            if (namespace.charAt(namespace.length() - 1) != '/') {
                namespace += '/';
            }

            for (String s : BaseUtils.stringToListOfTags(p.getMetadata())) {
                modelAssessmentMetadata.add(new AQD_ModelPropertyType().setHref(namespace + s));
            }
        }
    }

    public void save(String sourceId) throws Exception {
        SourceapportionmentManager sourceManager = new SourceapportionmentManager();
        AssesmentmethodsBean p = new AssesmentmethodsBean();

        if (assessmentType != null && BaseUtils.isDefined(assessmentType.getHref())) {
            AssesmenttypeBean atb = new AssesmenttypeBean();
            atb.setUri(assessmentType.getHref());
            p.setAssesmenttypeBean(atb);
        }
        p.setAssesmenttypedescription(assessmentTypeDescription);

        String metadata = null;
        if (modelAssessmentMetadata != null) {
            for (AQD_ModelPropertyType a : modelAssessmentMetadata) {
                String href = a.getHref();
                if (href.length() >= 1) {
                    if (metadata == null) {
                        metadata = XMLManager.convertHrefToLocalId(href);
                    } else {
                        metadata += "," + XMLManager.convertHrefToLocalId(href);
                    }
                }
            }
        }
        p.setMetadata(metadata);

        sourceManager.saveAssesmentmethodsToSourceapportionmentID(sourceId, p);
    }

}
