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

import eu.europa.ec.plan.DeletePlanException;
import eu.europa.ec.aqrsystem.xml.PlanXML;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.annotation.security.RolesAllowed;
import javax.xml.bind.JAXBException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.util.HtmlUtil;

/**
 * The action bean for handling the Plan tab of the system.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class PlanActionBean extends BasePlanActionBean {

    /**
     * Location of the tab view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/plan/tab.jsp";

    /**
     * The default action of the controller, which displays the tab.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution showTab() {
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * The action for creating a new plan.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution create() throws Exception {
        plan = planManager.createPlanDraftForUser(email);
        context.getMessages().add(new LocalizableMessage("plan.create.message", HtmlUtil.encode(plan.getInspireidLocalid())));
        return new RedirectResolution(EditPlanActionBean.class).addParameter("planId", plan.getUuid());
    }

    /**
     * Exporting the content of the plan to an XML file.
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.UnsupportedEncodingException
     */
    public Resolution export() throws JAXBException, UnsupportedEncodingException, IOException {
        return XMLManager.export(PlanXML.class, new PlanXML().populate(getPlan()), "Plan_" + XMLManager.getFilename(getPlan()));
    }

    /**
     * The action for importing a new plan
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution importData() throws JAXBException, Exception {
        XMLManager.importData(xmlFile, context, PlanXML.class, email, res);
        return new RedirectResolution(getClass());
    }

    /**
     * The action for deleting a plan
     *
     * @return Default Stripes object.
     */
    @RolesAllowed({"user if ${plan.editable}", "administrator if ${plan.editable}"})
    public Resolution delete() {
        try {
            planManager.deletePlanByID(planId);
        } catch (DeletePlanException e) {
            return ajaxError("plan.error.delete");
        }
        return ajaxSuccess();
    }

    /**
     * The action for cloning a plan
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution cloneItem() throws Exception {
        planManager.clonePlanByPlanIDAndUser(planId, email);
        return ajaxSuccess();
    }

    /**
     * The location of the table content view.
     */
    private static final String TABLE_CONTENT = "WEB-INF/jsp/plan/plansTableContent.jsp";

    /**
     * An event handler that renders the table.
     *
     * @return The table view.
     */
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT);
    }
}
