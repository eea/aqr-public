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
import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.measures.MeasuresBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.evaluationscenario.EvaluationScenarioManager;
import eu.europa.ec.measures.MeasuresManager;
import eu.europa.ec.plan.PlanManager;
import eu.europa.ec.sourceapprotionment.SourceapportionmentManager;
import eu.europa.ec.user.UserManager;
import eu.europa.ec.aqrsystem.ext.CustomLocalePicker;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.utils.CustomSecurityManager;
import eu.europa.ec.attainment.AttainmentManager;
import java.util.List;
import java.util.ResourceBundle;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * This is the base class for all ActionBean classes of the project. It provides
 * the context handling methods as well as variables for localisation and
 * configuration.
 */
public class BaseActionBean extends BaseUtils implements ActionBean {

    /**
     * Setting up all the variables of the BaseActionBean class.
     *
     * @throws java.lang.Exception
     */
    @Before(stages = LifecycleStage.BindingAndValidation)
    protected void setCommonVariables() throws Exception {
        res = new CustomLocalePicker().getLocalisationBundle(context.getRequest());

        currentSection = MenuViewHelper.Section.Home;

        email = CustomSecurityManager.getEmail(context);
    }
    /**
     * The email address of the currently logged in user.
     */
    protected String email;
    /**
     * The object with the values from StripesResources.
     */
    protected ResourceBundle res;

    /**
     * Getter.
     *
     * @return The object with the values from StripesResources.
     */
    public ResourceBundle getRes() {
        return res;
    }
    /**
     * The ActionBean context required for each ActionBean object.
     */
    protected ActionBeanContext context;

    /**
     * Getter. A method required by the ActionBean interface.
     *
     * @return The context object.
     */
    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    /**
     * Setter. A method required by the ActionBean interface.
     *
     * @param ctx servlet context object.
     */
    @Override
    public void setContext(ActionBeanContext ctx) {
        this.context = ctx;
    }
    /**
     * The active tab.
     */
    protected MenuViewHelper.Section currentSection;

    /**
     * Getter.
     *
     * @return The active tab.
     */
    public MenuViewHelper.Section getCurrentSection() {
        return currentSection;
    }
    /**
     * The message sent to an AJAX caller on success.
     */
    protected static final String SUCCESS_AJAX_MESSAGE = "<Success/>";

    /**
     * An AJAX operation success Stripes standard object.
     *
     * @return Resolution indicating success of an AJAX operation.
     */
    protected Resolution ajaxSuccess() {
        return new StreamingResolution("text/plain", SUCCESS_AJAX_MESSAGE);
    }
    /**
     * The header of the message sent to an AJAX caller on error.
     */
    protected static final String ERROR_AJAX_MESSAGE = "<Error/>";

    /**
     * An AJAX operation error Stripes standard object.
     *
     * @param key The key of the error message
     * @return Resolution indicating error of an AJAX operation and contains the
     * error message.
     */
    protected Resolution ajaxError(String key) {
        return new StreamingResolution("text/plain", ERROR_AJAX_MESSAGE + getErrorMessage(key));
    }
    /**
     * The object for communication with the DB.
     */
    protected static final SourceapportionmentManager sourceManager = new SourceapportionmentManager();
    /**
     * The object for communication with the DB.
     */
    protected static final EvaluationScenarioManager evaluationManager = new EvaluationScenarioManager();
    /**
     * The object for communication with the DB.
     */
    protected static final PlanManager planManager = new PlanManager();
    /**
     * The object for communication with the DB.
     */
    protected static final MeasuresManager measuresManager = new MeasuresManager();
    /**
     * The object for communication with the DB.
     */
    protected static final AttainmentManager attainmentManager = new AttainmentManager();
    /**
     * The object for communication with the DB.
     */
    protected static final UserManager userManager = new UserManager();

    /**
     * Setting a message depending on whether the item is complete or not.
     *
     * @param resourceObject The name of the item, e.g. "plan"
     * @param nonCompletedFields A list of non-completed fields
     */
    protected void getMessageAboutIncompleteFields(String resourceObject, List<String> nonCompletedFields) {
        if (nonCompletedFields.size() > 0) { // incomplete
            String listOfFields = "";
            for (String field : nonCompletedFields) {
                listOfFields += field + ", ";
            }
            listOfFields = listOfFields.substring(0, listOfFields.length() - 2);

            context.getMessages().add(new SimpleMessage(res.getString(resourceObject + ".incomplete"), listOfFields));
        } else { // complete
            context.getMessages().add(new SimpleMessage(res.getString(resourceObject + ".complete")));
        }
    }
    /**
     * An xml file uploaded by a user in a form
     */
    protected FileBean xmlFile;

    /**
     * Getter
     *
     * @return An xml file uploaded by a user in a form
     */
    public FileBean getXmlFile() {
        return xmlFile;
    }

    /**
     * Setter
     *
     * @param xmlFile An xml file uploaded by a user in a form
     */
    public void setXmlFile(FileBean xmlFile) {
        this.xmlFile = xmlFile;
    }
    protected LocalizableError errorMessage;

    public LocalizableError getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(LocalizableError errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Getter
     *
     * @return The list of plans in the system for this user.
     */
    public List<PlanBean> getExistingPlans() {
        return planManager.getAllPlansByUser(email);
    }

    /**
     * Getter
     *
     * @return The list of source apportionments in the system for this user.
     * evaluation.sourceapportionmentBeanList.
     */
    public List<SourceapportionmentBean> getExistingSourceApportionments() {
        return sourceManager.getAllSourceapportionmentByUser(email);
    }

    /**
     * Getter
     *
     * @return The list of evaluation scenarios in the system for this user.
     */
    public List<EvaluationscenarioBean> getExistingEvaluationScenarios() {
        return evaluationManager.getAllEvaluationscenarioByUser(email);
    }

    /**
     * Getter
     *
     * @return The list of measures in the system for this user.
     */
    public List<MeasuresBean> getExistingMeasures() {
        return measuresManager.getAllMeasuresByUser(email);
    }

    /**
     * Getter
     *
     * @return The list of attainments in the system for this user.
     */
    public List<AttainmentBean> getExistingAttainments() {
        return userManager.getAllAttainmentBeanByUser(email);
    }

    /**
     * Getter
     *
     * @return The email of the currently authenticated user
     */
    public String getUserEmail() {
        return CustomSecurityManager.getEmail(context);
    }

    /**
     * Getting an error message for the given resource key
     *
     * @param key Resource key
     * @return Error message
     */
    protected String getErrorMessage(String key) {
        return new LocalizableError(key).getMessage(new CustomLocalePicker().pickLocale(context.getRequest()));
    }
}
