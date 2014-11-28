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

import eu.europa.ec.measures.cost.CostsBean;
import eu.europa.ec.measures.currencies.CurrenciesBean;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.utils.ValidationMasks;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * The controller for measure.CostBean
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class CostActionBean extends BaseMeasureActionBean {

    /**
     * Location of the dialog view.
     */
    private static final String DIALOG_VIEW = "WEB-INF/jsp/measure/costDialogContent.jsp";

    /**
     * The main edit cost form.
     *
     * @return A standard Stripes object.
     */
    @DefaultHandler
    public Resolution form() {
        return new ForwardResolution(DIALOG_VIEW);
    }
    /**
     * Location of the partial cost view.
     */
    private static final String PARTIAL_VIEW = "WEB-INF/jsp/measure/costView.jsp";

    /**
     * The partial cost view in the edit measure form.
     *
     * @return A standard Stripes object.
     */
    public Resolution reloadView() {
        return new ForwardResolution(PARTIAL_VIEW);
    }

    /**
     * The action to save the cost.
     *
     * @return The error message or "Success"
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${measure.editable}", "administrator if ${measure.editable}"})
    public Resolution save() throws Exception {
        if (!BaseUtils.isDefined(cost.getExtimatedimplementationcosts())) {
            cost.setExtimatedimplementationcosts_nil(true);
        }
        measuresManager.saveCostByMeasueresID(measureId, cost);
        return ajaxSuccess();
    }

    /**
     * The action to delete the cost.
     *
     * @return The error message or "Success"
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user if ${measure.editable}", "administrator if ${measure.editable}"})
    public Resolution delete() throws Exception {
        measuresManager.deleteCostByMeasureID(measureId);
        return ajaxSuccess();
    }
    /**
     * The list of possible values for field measure.costsBean.currency_uri.
     */
    protected static List<CurrenciesBean> possibleCurrencies;

    /**
     * @return The list of possible values for field
     * measure.costsBean.currency_uri.
     */
    public List<CurrenciesBean> getPossibleCurrencies() {
        if (possibleCurrencies == null) {
            possibleCurrencies = measuresManager.getAllCurrencies();
        }
        return possibleCurrencies;
    }
    /**
     * The cost processed.
     */
    @ValidateNestedProperties({
        @Validate(field = "extimatedimplementationcosts", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "finalimplementationcosts", required = false, mask = ValidationMasks.integer, on = "save"),
        @Validate(field = "comment", required = false, on = "save")
    })
    protected CostsBean cost;

    /**
     * Getter.
     *
     * @return The impact processed.
     * @throws java.lang.Exception
     */
    public CostsBean getCost() throws Exception {
        CostsBean objectVal = getMeasure().getCostsBean();
        if (cost == null && objectVal != null) {
            cost = objectVal;
        }
        return cost;
    }

    /**
     * Setter.
     *
     * @param cost
     */
    public void setCost(CostsBean cost) {
        this.cost = cost;
    }
}
