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

import eu.europa.ec.measures.MeasuresBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 * A base class for all the actions of the main measures tab.
 */
public class BaseMeasureActionBean extends BaseActionBean {

    /**
     * Setting the common variables for all the actions of the measure
     * controllers.
     *
     * @throws java.lang.Exception
     */
    @Override
    @Before(stages = LifecycleStage.BindingAndValidation)
    protected void setCommonVariables() throws Exception {
        super.setCommonVariables();
        currentSection = MenuViewHelper.Section.Measures;
    }
    /**
     * The uuid of the selected measure.
     */
    protected String measureId;

    /**
     * @param measureId The uuid of the selected measure.
     */
    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    /**
     * @return The uuid of the selected measure.
     */
    public String getMeasureId() {
        return measureId;
    }
    /**
     * The current measure.
     */
    protected MeasuresBean measure;

    /**
     * @return the measure for the measureId stored in the action bean.
     */
    public MeasuresBean getMeasure() {
        if (measureId != null && (measure == null || !measure.getUuid().equals(measureId))) {
            measure = measuresManager.getMeasureByID(measureId, email);
        }
        return measure;
    }

    /**
     * Setting a measure.
     *
     * @param measure Set a new value.
     */
    public void setMeasure(MeasuresBean measure) {
        this.measure = measure;
    }
}
