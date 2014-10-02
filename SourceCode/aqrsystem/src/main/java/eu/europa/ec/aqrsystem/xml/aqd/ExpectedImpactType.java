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

import eu.europa.ec.measures.expectedimpact.ExpectedimpactBean;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.gml.MeasureType;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Represents aqd:ExpectedImpactType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ExpectedImpactType {

    @XmlElement(namespace = Namespaces.aqd)
    protected MeasureType levelOfConcentration;
    @XmlElement(namespace = Namespaces.aqd)
    protected String numberOfExceedances;
    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType specificationOfHours;
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;

    public void populate(ExpectedimpactBean p) {
        if (BaseUtils.isDefined(p.getLevelofconcentration())) {
            levelOfConcentration = new MeasureType();
            levelOfConcentration.populate(p.getLevelofconcentration(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", false, null, false);
        }
        if (BaseUtils.isDefined(p.getNumberofexceedence())) {
            numberOfExceedances = p.getNumberofexceedence();
        }
        if (BaseUtils.isDefined(p.getSpecificationofhours_uri())) {
            specificationOfHours = new ReferenceType().setHref(p.getSpecificationofhours_uri(), false);
        }
        if (BaseUtils.isDefined(p.getComment())) {
            comment = p.getComment();
        }
    }

    public void setObject(ExpectedimpactBean p, ActionBeanContext context) {
        if (levelOfConcentration != null) {
            p.setLevelofconcentration(levelOfConcentration.getValue());
        }
        p.setNumberofexceedence(numberOfExceedances);
        p.setSpecificationofhours_uri(specificationOfHours.getHref());
        p.setComment(comment);
    }
}
