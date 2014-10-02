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

import eu.europa.ec.sourceapprotionment.exceedancedescription.deductionassessmentmethod.DeductionassessmentmethodBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedancearea.ExceedanceareaBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedanceexposure.ExceedanceexposureBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.ExceedancedescriptionBean;
import eu.europa.ec.aqrmodel.Exceedancedescription;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:ExceedanceDescriptionType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ExceedanceDescriptionType {

    @XmlElement(namespace = Namespaces.aqd)
    protected boolean exceedance;
    @XmlElement(namespace = Namespaces.aqd)
    protected String numericalExceedance;
    @XmlElement(namespace = Namespaces.aqd)
    protected String numberExceedances;
    @XmlElement(namespace = Namespaces.aqd)
    protected AdjustmentMethodPropertyType deductionAssessmentMethod = new AdjustmentMethodPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected ExceedanceAreaPropertyType exceedanceArea = new ExceedanceAreaPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected ExceedanceExposurePropertyType exceedanceExposure = new ExceedanceExposurePropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected List<ReferenceType> reason = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected String reasonOther;
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;

    public void populate(ExceedancedescriptionBean p, String namespace) {
        exceedance = p.getExceedance() != null && p.getExceedance() != false;
        if (BaseUtils.isDefined(p.getNumericalexceedance())) {
            numericalExceedance = p.getNumericalexceedance();
        }
        if (BaseUtils.isDefined(p.getNumberexceedances())) {
            numberExceedances = p.getNumberexceedances();
        }
        deductionAssessmentMethod.populate(p.getDeductionassessmentmethodBean(), namespace);
        exceedanceArea.populate(p.getExceedanceareaBean(), namespace);
        exceedanceExposure.populate(p.getExceedenceexposureBean());
        for (String r : p.getReasonvalueList_uri()) {
            reason.add(new ReferenceType().setHref(r, false));
        }
        reasonOther = p.getOtherreason();
        comment = p.getComment();
    }

    /**
     * Saving the attributes of the object in Exceedancedescription
     *
     * @param ed Exceedancedescription to which the values are copied
     */
    public void setObject(Exceedancedescription ed) {
        ed.setExceedance(exceedance);
    }

    public void setObject(ExceedancedescriptionBean p, String sourceId) throws Exception {
        p.setExceedance(exceedance);
        p.setNumericalexceedance(numericalExceedance);
        p.setNumberexceedances(numberExceedances);

        DeductionassessmentmethodBean damb = new DeductionassessmentmethodBean();
        deductionAssessmentMethod.setObject(damb, sourceId);
        p.setDeductionassessmentmethodBean(damb);

        ExceedanceareaBean eeb = new ExceedanceareaBean();
        exceedanceArea.setObject(eeb);
        p.setExceedanceareaBean(eeb);

        ExceedanceexposureBean eexb = new ExceedanceexposureBean();
        exceedanceExposure.setObject(eexb);
        p.setExceedenceexposureBean(eexb);

        p.setReasonvalueList_uri(new ArrayList<String>());
        if (reason != null) {
            for (ReferenceType r : reason) {
                p.getReasonvalueList_uri().add(r.getHref());
            }
        }

        p.setOtherreason(reasonOther);
        p.setComment(comment);
    }
}
