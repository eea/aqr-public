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

import eu.europa.ec.plan.PlanBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 * A base class for all the actions of the main plan tab.
 */
public class BasePlanActionBean extends BaseActionBean {

    /**
     * Setting the common variables for all the actions of the plan controllers.
     *
     * @throws java.lang.Exception
     */
    @Override
    @Before(stages = LifecycleStage.BindingAndValidation)
    protected void setCommonVariables() throws Exception {
        super.setCommonVariables();
        currentSection = MenuViewHelper.Section.Plan;
    }

    /**
     * The uuid of the selected plan.
     */
    protected String planId;

    /**
     * @param planId The uuid of the selected plan.
     */
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * @return The uuid of the selected plan.
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * The current plan.
     */
    protected PlanBean plan;

    /**
     * @return the plan for the planId stored in the action bean.
     */
    public PlanBean getPlan() {
        if (planId != null && (plan == null || !plan.getUuid().equals(planId))) {
            plan = planManager.getPlanByID(planId, email);
        }
        return plan;
    }

    /**
     * Setting a plan.
     *
     * @param plan Set a new value.
     */
    public void setPlan(PlanBean plan) {
        this.plan = plan;
    }
}
