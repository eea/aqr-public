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

import eu.europa.ec.plan.pollutant.PollutantBean;
import eu.europa.ec.plan.protectiontarget.ProtectiontargetBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

/**
 * The controller for the pollutants dialog and table (from the edit plan view).
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class PollutantActionBean extends BasePlanActionBean {

    /**
     * The function provides the content of the pollutants table.
     *
     * @return The view.
     */
    @DefaultHandler
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT_VIEW);
    }

    /**
     * The main edit pollutant and targets form.
     *
     * @return A standard Stripes object.
     */
    public Resolution form() {
        return new ForwardResolution(DIALOG_VIEW);
    }

    /**
     * The fragment of the pollutants dialog with the targets part.
     *
     * @return A standard Stripes object.
     */
    public Resolution formTargetsFragment() {
        return new ForwardResolution(TARGETS_DIALOG_VIEW_FRAGMENT);
    }

    /**
     * The action to save the pollutant.
     *
     * @return The error message or "Success"
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${plan.editable}", "administrator if ${plan.editable}"})
    public Resolution save() throws Exception {
        planManager.saveProtectiontargetForPolutantAndPlan(planId, pollutant.getUuid(), targetIds);
        EditPlanActionBean.updateCompleteness(planManager.getPlanByID(planId, email), res);
        return ajaxSuccess();
    }

    /**
     * The action to delete the pollutant.
     *
     * @return The error message or "Success"
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${plan.editable}", "administrator if ${plan.editable}"})
    public Resolution delete() throws Exception {
        planManager.deletePollutant(planId, pollutantId);
        EditPlanActionBean.updateCompleteness(planManager.getPlanByID(planId, email), res);
        return ajaxSuccess();
    }

    /**
     * Location of the table content view.
     */
    private static final String TABLE_CONTENT_VIEW = "WEB-INF/jsp/plan/pollutantsTableContent.jsp";
    /**
     * Location of the dialog view.
     */
    private static final String DIALOG_VIEW = "WEB-INF/jsp/plan/pollutantDialogContent.jsp";
    /**
     * Location of the targets dialog view fragment.
     */
    private static final String TARGETS_DIALOG_VIEW_FRAGMENT = "WEB-INF/jsp/plan/targetsDialogFragment.jsp";

    /**
     * The list of possible pollutants.
     */
    protected static List<PollutantBean> possiblePollutants;

    /**
     * @return The list of possible pollutants.
     */
    public List<PollutantBean> getPossiblePollutants() {
        if (possiblePollutants == null) {
            possiblePollutants = planManager.getAllPollutantOrderByID();
        }
        return possiblePollutants;
    }

    /**
     * @return The list of possible protection targets.
     */
    public List<ProtectiontargetBean> getPossibleTargets() {
        if (pollutantId == null) {
            pollutantId = getPossiblePollutants().get(0).getUuid();
        }
        return planManager.getAllProtectiontargetByPollutantID(pollutantId);
    }

    /**
     * The currently selected pollutant's id.
     */
    protected String pollutantId;

    /**
     * @param pollutantId The new value.
     */
    public void setPollutantId(String pollutantId) {
        this.pollutantId = pollutantId;
    }

    /**
     * @return The currently selected pollutant's id.
     */
    public String getPollutantId() {
        return pollutantId;
    }

    /**
     * The pollutant processed.
     */
    protected PollutantBean pollutant;

    /**
     * @return The pollutant processed.
     */
    public PollutantBean getPollutant() {
        if (pollutantId != null && (pollutant == null || !pollutant.getUuid().equals(pollutantId))) {
            pollutant = planManager.getPollutantByID(pollutantId);
        }
        return pollutant;
    }

    /**
     * Setter.
     *
     * @param pollutant
     */
    public void setPollutant(PollutantBean pollutant) {
        this.pollutant = pollutant;
    }

    /**
     * Pollutants of the plan
     */
    protected List<PollutantBean> pollutants;

    /**
     * Get all pollutants of the plan.
     *
     * @return Get all pollutants of the plan.
     */
    public List<PollutantBean> getPollutants() {
        if (planId != null) {
            pollutants = planManager.getAllPollutantByPlan(planId);
        }
        return pollutants;
    }

    /**
     * The list of targets for the considered pollutant.
     */
    protected List<ProtectiontargetBean> targets;

    /**
     * @return The list of targets for the considered pollutant.
     */
    public List<ProtectiontargetBean> getTargets() {
        if (planId != null && pollutantId != null) {
            targets = planManager.getAllProtectiontargetForPlanAndPolutant(planId, pollutantId);
        }
        return targets;
    }

    /**
     * A list of target Ids for the plan and pollutant.
     */
    @Validate(required = true, on = "save")
    protected List<String> targetIds;

    /**
     * @param targetIds A list of target Ids for the plan and pollutant.
     */
    public void setTargetIds(List<String> targetIds) {
        this.targetIds = targetIds;
    }

    /**
     * @return A list of target Ids for the plan and pollutant.
     */
    public List<String> getTargetIds() {
        List<ProtectiontargetBean> tl = getTargets();
        if (tl != null) {
            targetIds = new ArrayList<String>();
            for (ProtectiontargetBean t : tl) {
                targetIds.add(t.getUuid());
            }
        }
        return targetIds;
    }
}
