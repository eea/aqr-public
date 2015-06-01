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

import static eu.europa.ec.aqrsystem.action.BaseActionBean.measuresManager;
import eu.europa.ec.measures.DeleteMeasuresException;
import eu.europa.ec.aqrsystem.xml.MeasuresXML;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.measures.MeasuresBean;
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
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.util.HtmlUtil;

/**
 * The action bean for handling the Measure tab of the system.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class MeasureActionBean extends BaseMeasureActionBean {

    /**
     * Location of the tab view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/measure/tab.jsp";

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
     * The action for creating a new measure.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution create() throws Exception {
        measureId = measuresManager.createMeasuresDraftForUser(email);
        context.getMessages().add(new LocalizableMessage("measure.create.message", HtmlUtil.encode(getMeasure().getInspireidLocalid())));
        return new RedirectResolution(EditMeasureActionBean.class).addParameter("measureId", measureId);
    }

    /**
     * Exporting the content of the plan to an XML file.
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.IOException
     */
    public Resolution export() throws JAXBException, IOException {
        return XMLManager.export(MeasuresXML.class, new MeasuresXML().populate(getMeasure(), email), "Measure_" + XMLManager.getFilename(getMeasure()));
    }

    /**
     * The action for importing a new measure
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution importData() throws Exception {
        XMLManager.importData(xmlFile, context, MeasuresXML.class, email, res);
        return new RedirectResolution(getClass());
    }

    /**
     * The action for deleting a measure
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${measure.editable}", "administrator if ${measure.editable}"})
    public Resolution delete() throws Exception {
        try {
            measuresManager.deleteMeasuresByID(measureId);
        } catch (DeleteMeasuresException e) {
            return ajaxError("measures.error.delete");
        }
        return ajaxSuccess();
    }

    /**
     * The action for cloning a measure
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution cloneItem() throws Exception {
        measuresManager.cloneMeasuresByMeasuresIDAndUser(measureId, email);
        return ajaxSuccess();
    }
    /**
     * The location of the table content view.
     */
    private static final String TABLE_CONTENT = "WEB-INF/jsp/measure/measuresTableContent.jsp";

    /**
     * An event handler that renders the table.
     *
     * @return The table view.
     */
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT);
    }

    /**
     * Exporting the content of all the plans to an XML file.
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.UnsupportedEncodingException
     */
    @RolesAllowed({"administrator", "user"})
    public Resolution exportall() throws JAXBException, UnsupportedEncodingException, IOException {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date toDateDB = null;
        try {
            Date date = formatter.parse(to);
            toDateDB = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(toDateDB);
        calendar.add(Calendar.DATE, 1);
        Date nextDateToDate = calendar.getTime();

        Date fromDateDB = null;
        try {
            Date date = formatter.parse(from);
            fromDateDB = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] fromArray = from.split("-");
        String dayFrom = fromArray[0];
        String monthFrom = fromArray[1];
        String yearFrom = fromArray[2];

        String fromReverse = yearFrom + "-" + monthFrom + "-" + dayFrom;

        String[] toArray = to.split("-");
        String dayTo = toArray[0];
        String monthTo = toArray[1];
        String yearTo = toArray[2];

        String toReverse = yearTo + "-" + monthTo + "-" + dayTo;

        List<MeasuresBean> measuresBeanList = measuresManager.getAllCompletedMeasuresByCountryOfTheActualUser(email, completed, fromDateDB, nextDateToDate);
        return XMLManager.export(MeasuresXML.class, new MeasuresXML().populateMultiple(measuresBeanList, fromReverse, toReverse, email), "Measure_Multiple_" + new Date().toString() + ".xml");
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
    protected String fromDate;
    protected String toDate;

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }
}
