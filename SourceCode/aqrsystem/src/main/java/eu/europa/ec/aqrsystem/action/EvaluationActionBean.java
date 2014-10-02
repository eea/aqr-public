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

import eu.europa.ec.evaluationscenario.DeleteEvaluationscenarioException;
import eu.europa.ec.aqrsystem.xml.EvaluationXML;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.annotation.security.RolesAllowed;
import javax.xml.bind.JAXBException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.util.HtmlUtil;

/**
 * A base class for all the actions of the main evaluation scenario tab.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class EvaluationActionBean extends BaseEvaluationActionBean {

    /**
     * Location of the tab view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/evaluation/tab.jsp";

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
     * The action for creating a new evaluation scenario.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution create() throws Exception {
        evaluationId = evaluationManager.createEvaluationScenarioDraftForUser(email);
        context.getMessages().add(new LocalizableMessage("evaluation.create.message", HtmlUtil.encode(getEvaluation().getInspireidLocalid())));
        return new RedirectResolution(EditEvaluationActionBean.class).addParameter("evaluationId", evaluationId);
    }

    /**
     * Exporting the content of the evaluation to an XML file.
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.UnsupportedEncodingException
     */
    public Resolution export() throws JAXBException, UnsupportedEncodingException, IOException {
        return XMLManager.export(EvaluationXML.class, new EvaluationXML().populate(getEvaluation(), email), "Evaluation_" + XMLManager.getFilename(getEvaluation()));
    }

    /**
     * The action for importing a new evaluation scenario
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution importData() throws Exception {
        XMLManager.importData(xmlFile, context, EvaluationXML.class, email, res);
        return new RedirectResolution(getClass());
    }

    /**
     * The action for deleting an evaluation scenario
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${evaluation.editable}", "administrator if ${evaluation.editable}"})
    public Resolution delete() throws Exception {
        try {
            evaluationManager.deleteEvaluationscenarioByID(evaluationId);
        } catch (DeleteEvaluationscenarioException e) {
            return ajaxError("evaluationscenario.error.delete");
        }
        return ajaxSuccess();
    }

    /**
     * The action for cloning a scenario
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution cloneItem() throws Exception {
        evaluationManager.cloneEvaluationscenarioByEvaluationscenarioIDAndUser(evaluationId, email);
        return ajaxSuccess();
    }

    /**
     * The location of the table content view.
     */
    private static final String TABLE_CONTENT = "WEB-INF/jsp/evaluation/evaluationsTableContent.jsp";

    /**
     * An event handler that renders the table.
     *
     * @return The table view.
     */
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT);
    }
}
