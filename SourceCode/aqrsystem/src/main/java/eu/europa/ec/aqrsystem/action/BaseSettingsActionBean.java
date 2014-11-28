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

import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.user.UserBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 * A base class for the settings tab action beans.
 */
public class BaseSettingsActionBean extends BaseActionBean {

    /**
     * Setting the common variables for all the actions of the settings
     * controllers.
     *
     * @throws java.lang.Exception
     */
    @Override
    @Before(stages = LifecycleStage.BindingAndValidation)
    protected void setCommonVariables() throws Exception {
        super.setCommonVariables();
        currentSection = MenuViewHelper.Section.Settings;
    }
    /**
     * The sub-tab actually selected
     */
    protected String activeTab;

    /**
     * Getter
     *
     * @return
     */
    public String getActiveTab() {
        return activeTab;
    }

    /**
     * Setter
     *
     * @param activeTab
     */
    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }
    /**
     * User bean
     */
    protected UserBean currentUser;

    /**
     * @return the UserBean for the current user
     */
    public UserBean getCurrentUser() {
        if (currentUser == null) {
            currentUser = userManager.getUserByEmail(email);
        }
        return currentUser;
    }

    /**
     * Setting a user.
     *
     * @param currentUser Set a new value.
     */
    public void setCurrentUser(UserBean currentUser) {
        this.currentUser = currentUser;
    }
    /**
     * The uuid of the selected attainment.
     */
    protected String attainmentId;

    /**
     * @return The uuid of the selected attainment.
     */
    public String getAttainmentId() {
        return attainmentId;
    }

    /**
     * @param attainmentId The uuid of the selected attainment.
     */
    public void setAttainmentId(String attainmentId) {
        this.attainmentId = attainmentId;
    }
    /**
     * The current plan.
     */
    protected AttainmentBean attainment;

    /**
     * @return the plan for the planId stored in the action bean.
     */
    public AttainmentBean getAttainment() {
        if (attainmentId != null && (attainment == null || !attainment.getUuid().equals(attainmentId))) {
            attainment = attainmentManager.getAttainmentBeanByAttaimentID(attainmentId);
        }
        return attainment;
    }

    /**
     * Setting a plan.
     *
     * @param attainment Set a new value.
     */
    public void setAttainment(AttainmentBean attainment) {
        this.attainment = attainment;
    }
}
