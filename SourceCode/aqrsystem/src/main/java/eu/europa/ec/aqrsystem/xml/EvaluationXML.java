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
package eu.europa.ec.aqrsystem.xml;

import eu.europa.ec.evaluationscenario.EvaluationscenarioINSPIRELocalIDAlreadyExistingException;
import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.evaluationscenario.EvaluationScenarioManager;
import eu.europa.ec.aqrsystem.action.EditEvaluationActionBean;
import eu.europa.ec.aqrsystem.xml.gml.FeatureMember;
import eu.europa.ec.attainment.AttainmentBean;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * The main object responsible for defining the structure of the evaluation
 * scenario XML files when they are exported. In XML it represents
 * gml:FeatureCollection
 */
@XmlRootElement(name = "FeatureCollection", namespace = Namespaces.gml)
@XmlAccessorType(XmlAccessType.NONE)
public class EvaluationXML implements XMLSaveableObject {

    @XmlAttribute(namespace = Namespaces.gml)
    protected String id;

    @XmlElement(namespace = Namespaces.gml)
    protected List<FeatureMember> featureMember = new ArrayList<FeatureMember>();

    /**
     * Populating the object using the data from a evaluation bean.
     *
     * @param p
     * @param userEmail
     * @return
     */
    public EvaluationXML populate(EvaluationscenarioBean p, String userEmail) {
        id = "Evaluation_Scenario";
        FeatureMember header = new FeatureMember();
        FeatureMember scenario = new FeatureMember();
        header.populateHeader(p);
        scenario.populate(p, userEmail);
        featureMember.add(header);
        featureMember.add(scenario);
        return this;
    }

    /**
     * Saving the object
     *
     * @param userEmail
     * @param context of the ActionBean
     * @throws java.lang.Exception
     */
    @Override
    public void save(String userEmail, ActionBeanContext context, ResourceBundle res) throws Exception {
        if (featureMember != null && featureMember.size() == 2 && featureMember.get(0).containsHeader() && featureMember.get(1).containsScenario()) {
            EvaluationScenarioManager scenarioManager = new EvaluationScenarioManager();
            String scenarioId = scenarioManager.createEvaluationScenarioDraftForUser(userEmail);
            EvaluationscenarioBean scenario = scenarioManager.getEvaluationscenarioByID(scenarioId, userEmail);

            featureMember.get(0).setObjectHeader(scenario);
            featureMember.get(1).setObject(scenario, context, userEmail);

            String newLocalId = scenario.getInspireidLocalid();
            try {
                scenarioManager.saveEvaluationScenarioDraft(scenario);
            } catch (EvaluationscenarioINSPIRELocalIDAlreadyExistingException e) {
                String oldLocalId = scenarioManager.getEvaluationscenarioByID(scenario.getUuid(), userEmail).getInspireidLocalid();
                context.getValidationErrors().addGlobalError(new LocalizableError("evaluation.error.duplicatelocalid", HtmlUtil.encode(newLocalId), HtmlUtil.encode(oldLocalId)));
            }
            EditEvaluationActionBean.updateCompleteness(scenario, res);

            context.getMessages().add(new LocalizableMessage("import.message.scenario", HtmlUtil.encode(scenario.getInspireidLocalid())));
        } else {
            context.getValidationErrors().addGlobalError(new LocalizableError("import.error.measure.noscenario"));
        }
    }

    @Override
    public void save(String userEmail, ActionBeanContext context, ResourceBundle res, ArrayList<AttainmentBean> localId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
