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

import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 * A base class for all the actions of the main source apportionment tab.
 */
public class BaseApportionmentActionBean extends BaseActionBean {

    /**
     * Setting the common variables for all the actions of the source
     * apportionment controllers.
     *
     * @throws java.lang.Exception
     */
    @Override
    @Before(stages = LifecycleStage.BindingAndValidation)
    protected void setCommonVariables() throws Exception {
        super.setCommonVariables();
        currentSection = MenuViewHelper.Section.Apportionment;
    }
    /**
     * The uuid of the selected source.
     */
    protected String sourceId;

    /**
     * @param sourceId The uuid of the selected source.
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return The uuid of the selected source.
     */
    public String getSourceId() {
        return sourceId;
    }
    /**
     * The current source apportionment.
     */
    protected SourceapportionmentBean source;

    /**
     * @return the source apportionment for the sourceId stored in the action
     * bean.
     */
    public SourceapportionmentBean getSource() {
        if (sourceId != null && (source == null || !source.getUuid().equals(sourceId))) {
            source = sourceManager.getSourceapportionmentByID(sourceId, email);
        }
        return source;
    }

    /**
     * Setting a source apportionment.
     *
     * @param source Set a new value.
     */
    public void setSource(SourceapportionmentBean source) {
        this.source = source;
    }
}
