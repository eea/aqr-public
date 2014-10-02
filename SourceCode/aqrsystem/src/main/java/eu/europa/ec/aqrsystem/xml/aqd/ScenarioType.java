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
import eu.europa.ec.evaluationscenario.scenario.ScenarioBean;
import eu.europa.ec.measures.MeasuresManager;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.gml.MeasureType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Represents aqd:AQD_EvaluationScenarioType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ScenarioType {

    @XmlElement(namespace = Namespaces.aqd)
    protected String description;
    @XmlElement(namespace = Namespaces.aqd)
    protected MeasureType totalEmissions = new MeasureType();
    @XmlElement(namespace = Namespaces.aqd)
    protected MeasureType expectedConcentration;
    @XmlElement(namespace = Namespaces.aqd)
    protected String expectedExceedances;
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<AQD_MeasuresPropertyType> measuresApplied = new ArrayList();

    /**
     * Populating the object with data
     *
     * @param p ScenarioBean
     * @param userEmail
     * @param namespace
     */
    public void populate(ScenarioBean p, String userEmail, String namespace) {
        description = p.getDescription();
        totalEmissions.populate(p.getTotalemissions(), "http://dd.eionet.europa.eu/vocabulary/uom/emission/kt.year-1", false, null, false);
        if (BaseUtils.isDefined(p.getExpectedconcentration())) {
            expectedConcentration = new MeasureType();
            expectedConcentration.populate(p.getExpectedconcentration(), "http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3", false, null, false);
        }
        if (BaseUtils.isDefined(p.getExpectedexceedence())) {
            expectedExceedances = p.getExpectedexceedence();
        }
        if (BaseUtils.isDefined(p.getComment())) {
            comment = p.getComment();
        }

        if (p.getMeasuresUuid() != null) {
            for (String id : p.getMeasuresUuid()) {
                measuresApplied.add(new AQD_MeasuresPropertyType().populate(id, namespace, userEmail));
            }
        }
        if (measuresApplied.isEmpty()) {
            measuresApplied.add(new AQD_MeasuresPropertyType().populate(null, null, userEmail));
        }
    }

    /**
     * Populating the ScenarioBean with the data from the object
     *
     * @param p
     * @param context
     * @param userEmail
     */
    public void setObject(ScenarioBean p, ActionBeanContext context, String userEmail) {
        p.setDescription(description);
        if (totalEmissions != null) {
            p.setTotalemissions(totalEmissions.getValue());
        }
        if (expectedConcentration != null) {
            p.setExpectedconcentration(expectedConcentration.getValue());
        }
        p.setExpectedexceedence(expectedExceedances);
        p.setComment(comment);
        p.setMeasuresUuid(new ArrayList<String>());
        if (measuresApplied != null) {
            for (AQD_MeasuresPropertyType m : measuresApplied) {
                String href = m.getHref();
                if (href != null) {
                    MeasuresBean mb = new MeasuresManager().getMeasureByINSPIRELocalID(XMLManager.convertHrefToLocalId(href), userEmail);
                    if (mb != null) {
                        p.getMeasuresUuid().add(mb.getUuid());
                    } else {
                        context.getValidationErrors().addGlobalError(new LocalizableError("import.error.scenario.nomeasure", HtmlUtil.encode(href)));
                    }
                }
            }
        }
    }
}
