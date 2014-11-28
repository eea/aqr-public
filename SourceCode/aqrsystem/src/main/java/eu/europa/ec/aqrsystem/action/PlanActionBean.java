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

import static eu.europa.ec.aqrsystem.action.BaseActionBean.planManager;
import eu.europa.ec.plan.DeletePlanException;
import eu.europa.ec.aqrsystem.xml.PlanXML;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.plan.PlanBean;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    /**
     * Location of the dialog view.
     */
    private static final String DIALOG_VIEW = "WEB-INF/jsp/plan/exportallDialogContent.jsp";

    /**
     * The main edit cost form.
     *
     * @return A standard Stripes object.
     */
    public Resolution form() {
        return new ForwardResolution(DIALOG_VIEW);
    }

    /**
     * Exporting the content of all the plans to an XML file.
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.UnsupportedEncodingException
     */
    public Resolution exportall() throws JAXBException, UnsupportedEncodingException, IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate);
        calendar.add(Calendar.DATE, 1);
        Date nextDateToDate = calendar.getTime(); 
        
        List<PlanBean> planBeanList = planManager.getAllCompletedPlansByUser(email, completed, fromDate, nextDateToDate);
        return XMLManager.export(PlanXML.class, new PlanXML().populateMultiple(planBeanList, fromDate, toDate, email), "Plan_Multiple_" + new Date().toString() + ".xml");
    }
    protected boolean completed;
    protected String radio;

    public void setRadio(String radio) {
        this.radio = radio;
        if (radio.equals("all")) {
            completed = false;
        } else {
            completed = true;
        }
    }

    public String getRadio() {
        return radio;
    }
    protected String from;
    protected String to;
    protected Date fromDate;
    protected Date toDate;

    public void setFrom(String from) {
        this.from = from;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(from);
            this.fromDate = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(to);
            this.toDate = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTo() {
        return to;
    }
}
