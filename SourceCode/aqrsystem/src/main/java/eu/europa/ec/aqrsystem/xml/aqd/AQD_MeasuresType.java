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

import eu.europa.ec.measures.cost.CostsBean;
import eu.europa.ec.measures.expectedimpact.ExpectedimpactBean;
import eu.europa.ec.measures.plannedimplementation.PlannedimplementationBean;
import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.measures.MeasuresBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.evaluationscenario.EvaluationScenarioManager;
import eu.europa.ec.sourceapprotionment.SourceapportionmentManager;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
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
 * Represents aqd:AQD_MeasuresType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AQD_MeasuresType {

    @XmlAttribute(namespace = Namespaces.gml)
    protected String id;

    @XmlElement(namespace = Namespaces.aqd)
    protected IdentifierPropertyType inspireId = new IdentifierPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String code;
    @XmlElement(namespace = Namespaces.aqd)
    protected String name;
    @XmlElement(namespace = Namespaces.aqd)
    protected String description;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<ReferenceType> classification = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType measureType = new ReferenceType();
    @XmlElement(namespace = Namespaces.aqd)
    protected List<ReferenceType> administrativeLevel = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType timeScale = new ReferenceType();
    @XmlElement(namespace = Namespaces.aqd)
    protected CostsPropertyType costs;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<ReferenceType> sourceSectors = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected List<ReferenceType> spatialScale = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected PlannedImplementationPropertyType plannedImplementation = new PlannedImplementationPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected QuantityCommentedPropertyType reductionOfEmissions = new QuantityCommentedPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected ExpectedImpactPropertyType expectedImpact;
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<AQD_SourceApportionmentPropertyType> exceedanceAffected = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected List<AQD_EvaluationScenarioPropertyType> usedForScenario = new ArrayList();

    /**
     * Populating the object
     *
     * @param m MeasuresBean
     * @param userEmail
     */
    public void populate(MeasuresBean m, String userEmail) {
        String namespace = m.getInspireidNamespace();

        id = "ATTR_" + XMLManager.removeProblematicCharacters(m.getInspireidLocalid());
        inspireId.populate(m, m.getInspireidLocalid());
        code = m.getCode();
        name = m.getName();
        description = m.getDescription();

        for (String c : m.getClassification_uri()) {
            classification.add(new ReferenceType().setHref(c, false));
        }
        if (classification.isEmpty()) {
            classification.add(new ReferenceType().setHref(null, false));
        }

        measureType.setHref(m.getMeasuretype_uri(), false);

        for (String a : m.getAdministrationlevel_uri()) {
            administrativeLevel.add(new ReferenceType().setHref(a, false));
        }
        if (administrativeLevel.isEmpty()) {
            administrativeLevel.add(new ReferenceType().setHref(null, false));
        }

        timeScale.setHref(m.getTimescale_uri(), false);
        CostsBean c = m.getCostsBean();
        if (c != null) {
            costs = new CostsPropertyType();
            costs.populate(c);
        }

        for (String s : m.getSourcesector_uri()) {
            sourceSectors.add(new ReferenceType().setHref(s, false));
        }
        if (sourceSectors.isEmpty()) {
            sourceSectors.add(new ReferenceType().setHref(null, false));
        }

        for (String s : m.getSpatialscale_uri()) {
            spatialScale.add(new ReferenceType().setHref(s, false));
        }
        if (spatialScale.isEmpty()) {
            spatialScale.add(new ReferenceType().setHref(null, false));
        }

        plannedImplementation.populate(m.getPlannedimplementationBean(), m.getUuid());
        reductionOfEmissions.populate(m.getReductionofemission(), m.getQuantificationnumerical_uri(), m.getComment(), m.isReductionofemission_nil(), m.getReductionofemission_nilreason());
        if (m.getExpectedimpactBean() != null) {
            expectedImpact = new ExpectedImpactPropertyType();
            expectedImpact.populate(m.getExpectedimpactBean());
        }
        if (BaseUtils.isDefined(m.getCommentForClarification())) {
            comment = m.getCommentForClarification();
        }

        if (m.getSourceapportionmentBeanList() != null) {
            for (String id : m.getSourceapportionmentBeanList()) {
                exceedanceAffected.add(new AQD_SourceApportionmentPropertyType().populate(id, namespace, userEmail));
            }
        }
        if (exceedanceAffected.isEmpty()) {
            exceedanceAffected.add(new AQD_SourceApportionmentPropertyType().populate(null, null, userEmail));
        }

        if (m.getEvaluationscenarioBeanList() != null) {
            for (String id : m.getEvaluationscenarioBeanList()) {
                usedForScenario.add(new AQD_EvaluationScenarioPropertyType().populate(id, namespace, userEmail));
            }
        }
    }

    /**
     * Populating the MeasuresBean with the data from the object
     *
     * @param p
     * @param context
     * @param userEmail
     * @throws java.lang.Exception
     */
    public void setObject(MeasuresBean p, ActionBeanContext context, String userEmail) throws Exception {
        p.setInspireidLocalid(inspireId.getLocalId());
        String measureId = p.getUuid();

        p.setCode(code);
        p.setName(name);
        p.setDescription(description);
        p.setClassification_uri(XMLManager.convertListOfReferenceTypeToListOfString(classification));
        p.setMeasuretype_uri(measureType.getHref());
        p.setAdministrationlevel_uri(XMLManager.convertListOfReferenceTypeToListOfString(administrativeLevel));
        p.setTimescale_uri(timeScale.getHref());
        if (costs != null) {
            costs.setObject(measureId, context);
        }
        p.setSourcesector_uri(XMLManager.convertListOfReferenceTypeToListOfString(sourceSectors));
        p.setSpatialscale_uri(XMLManager.convertListOfReferenceTypeToListOfString(spatialScale));

        PlannedimplementationBean pib = new PlannedimplementationBean();
        plannedImplementation.setObject(pib, context);
        p.setPlannedimplementationBean(pib);

        p.setReductionofemission_nil(reductionOfEmissions.isNilReason());
        if (!reductionOfEmissions.isNilReason()) {
            p.setReductionofemission(reductionOfEmissions.getValue());
        } else {
            p.setReductionofemission_nilreason(reductionOfEmissions.getNilReason());
        }
        p.setQuantificationnumerical_uri(reductionOfEmissions.getUoM());
        p.setComment(reductionOfEmissions.getComment());

        if (expectedImpact != null) {
            ExpectedimpactBean eib = new ExpectedimpactBean();
            expectedImpact.setObject(eib, context);
            p.setExpectedimpactBean(eib);
        }

        p.setCommentForClarification(comment);

        p.setSourceapportionmentBeanList(new ArrayList<String>());
        if (exceedanceAffected != null) {
            for (AQD_SourceApportionmentPropertyType sa : exceedanceAffected) {
                String href = sa.getHref();
                if (href != null) {
                    SourceapportionmentBean sab = new SourceapportionmentManager().getSourceapportionmentByINSPIRELocalID(XMLManager.convertHrefToLocalId(href), userEmail);
                    if (sab != null) {
                        p.getSourceapportionmentBeanList().add(sab.getUuid());
                    } else {
                        context.getValidationErrors().addGlobalError(new LocalizableError("import.error.measure.nosource", HtmlUtil.encode(href)));
                    }
                }
            }
        }

        p.setEvaluationscenarioBeanList(new ArrayList<String>());
        if (usedForScenario != null) {
            for (AQD_EvaluationScenarioPropertyType es : usedForScenario) {
                String href = es.getHref();
                if (href != null) {
                    EvaluationscenarioBean esb = new EvaluationScenarioManager().getEvaluationscenarioByINSPIRELocalID(XMLManager.convertHrefToLocalId(href), userEmail);
                    if (esb != null) {
                        p.getEvaluationscenarioBeanList().add(esb.getUuid());
                    } else {
                        context.getValidationErrors().addGlobalError(new LocalizableError("import.error.measure.noevaluation", HtmlUtil.encode(href)));
                    }
                }
            }
        }
    }
}
