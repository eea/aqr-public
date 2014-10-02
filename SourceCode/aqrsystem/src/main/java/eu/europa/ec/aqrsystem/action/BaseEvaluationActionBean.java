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
package eu.europa.ec.aqrsystem.action;

import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 * A base class for all the actions of the main evaluation scenario tab.
 */
public class BaseEvaluationActionBean extends BaseActionBean {

    /**
     * Setting the common variables for all the actions of the evaluation
     * scenario controllers.
     *
     * @throws java.lang.Exception
     */
    @Override
    @Before(stages = LifecycleStage.BindingAndValidation)
    protected void setCommonVariables() throws Exception {
        super.setCommonVariables();
        currentSection = MenuViewHelper.Section.Evaluation;
    }

    /**
     * The uuid of the selected evaluation scenario.
     */
    protected String evaluationId;

    /**
     * @param evaluationId The uuid of the selected evaluation scenario.
     */
    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    /**
     * @return The uuid of the selected evaluation scenario.
     */
    public String getEvaluationId() {
        return evaluationId;
    }

    /**
     * The current evaluation scenario.
     */
    protected EvaluationscenarioBean evaluation;

    /**
     * @return the scenario for the evaluationId stored in the action bean.
     */
    public EvaluationscenarioBean getEvaluation() {
        if (evaluationId != null && (evaluation == null || !evaluation.getUuid().equals(evaluationId))) {
            evaluation = evaluationManager.getEvaluationscenarioByID(evaluationId, email);
        }
        return evaluation;
    }

    /**
     * Setting an evaluation scenario.
     *
     * @param evaluation Set a new value.
     */
    public void setEvaluation(EvaluationscenarioBean evaluation) {
        this.evaluation = evaluation;
    }
}
