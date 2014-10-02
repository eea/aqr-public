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

import eu.europa.ec.common.publication.PublicationBean;
import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.evaluationscenario.scenario.ScenarioBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.evaluationscenario.EvaluationScenarioManager;
import eu.europa.ec.plan.PlanManager;
import eu.europa.ec.sourceapprotionment.SourceapportionmentManager;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.gml.TimeInstantPropertyType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Represents aqd:AQD_EvaluationScenarioType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AQD_EvaluationScenarioType {

    @XmlAttribute(namespace = Namespaces.gml)
    protected String id;

    @XmlElement(namespace = Namespaces.aqd)
    protected IdentifierPropertyType inspireId = new IdentifierPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String codeOfScenario;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<PublicationPropertyType> publication = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected TimeInstantPropertyType attainmentYear = new TimeInstantPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected TimeInstantPropertyType startYear = new TimeInstantPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected ScenarioPropertyType baselineScenario = new ScenarioPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected ScenarioPropertyType projectionScenario = new ScenarioPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected AQD_PlanPropertyType usedInPlan = new AQD_PlanPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected AQD_SourceApportionmentPropertyType sourceApportionment = new AQD_SourceApportionmentPropertyType();

    /**
     * Populating the object with data
     *
     * @param p EvaluationscenarioBean
     * @param userEmail
     */
    public void populate(EvaluationscenarioBean p, String userEmail) {
        EvaluationScenarioManager evaluationManager = new EvaluationScenarioManager();
        String evaluationId = p.getUuid();
        String namespace = p.getInspireidNamespace();

        id = "ATTR_" + XMLManager.removeProblematicCharacters(p.getInspireidLocalid());
        inspireId.populate(p, p.getInspireidLocalid());
        codeOfScenario = p.getCodeofscenario();

        for (PublicationBean pub : evaluationManager.getAllPublicationBeanByEvaluationscenario(evaluationId)) {
            publication.add(new PublicationPropertyType().populate(pub));
        }
        if (publication.isEmpty()) {
            publication.add(new PublicationPropertyType().populate(null));
        }

        attainmentYear.populate(p.getAttainmentyearPeriodtime(), "ATTAINMENT_YEAR_" + p.getUuid(), false, null);
        startYear.populate(p.getStartyearPeriodtime(), "START_YEAR_" + p.getUuid(), false, null);

        baselineScenario.populate(p.getBaselinescenario(), userEmail, namespace);
        projectionScenario.populate(p.getProjectionscenario(), userEmail, namespace);

        if (p.getPlan() != null) {
            usedInPlan.populate(p.getPlan().getUuid(), namespace, userEmail);
        } else {
            usedInPlan.populate(null, null, userEmail);
        }

        if (p.getSourceapportionment() != null) {
            sourceApportionment.populate(p.getSourceapportionment().getUuid(), namespace, userEmail);
        } else {
            sourceApportionment.populate(null, null, userEmail);
        }
    }

    /**
     * Populating the EvaluationscenarioBean with the data from the object
     *
     * @param p
     * @param context
     * @param userEmail
     * @throws java.lang.Exception
     */
    public void setObject(EvaluationscenarioBean p, ActionBeanContext context, String userEmail) throws Exception {
        p.setInspireidLocalid(inspireId.getLocalId());
        p.setCodeofscenario(codeOfScenario);
        if (publication != null) {
            for (PublicationPropertyType pub : publication) {
                pub.saveToScenario(p.getUuid());
            }
        }
        p.setAttainmentyearPeriodtime(attainmentYear.getValue());
        p.setStartyearPeriodtime(startYear.getValue());
        ScenarioBean bs = new ScenarioBean();
        baselineScenario.setObject(bs, context, userEmail);
        p.setBaselinescenario(bs);
        ScenarioBean ps = new ScenarioBean();
        projectionScenario.setObject(ps, context, userEmail);
        p.setProjectionscenario(ps);
        if (usedInPlan != null) {
            String href = usedInPlan.getHref();
            if (href != null) {
                PlanBean pb = new PlanManager().getPlanByINSPIRELocalID(XMLManager.convertHrefToLocalId(href), userEmail);
                if (pb != null) {
                    p.setPlan(pb);
                } else {
                    context.getValidationErrors().addGlobalError(new LocalizableError("import.error.scenario.noplan", HtmlUtil.encode(href)));
                }
            }
        }
        if (sourceApportionment != null) {
            String href = sourceApportionment.getHref();
            if (href != null) {
                SourceapportionmentBean sab = new SourceapportionmentManager().getSourceapportionmentByINSPIRELocalID(XMLManager.convertHrefToLocalId(href), userEmail);
                if (sab != null) {
                    p.setSourceapportionment(sab);
                } else {
                    context.getValidationErrors().addGlobalError(new LocalizableError("import.error.scenario.nosource", HtmlUtil.encode(href)));
                }
            }
        }
    }
}
