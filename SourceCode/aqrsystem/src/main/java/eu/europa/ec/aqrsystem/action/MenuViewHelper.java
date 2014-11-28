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
 *  Date: 23/03/2014
 *  Authors: European Commission, Joint Research Centre
 *  Lucasz Cyra, Emanuela Epure, Daniele Francioli
 *  aq-dev@jrc.ec.europa.eu
 */
package eu.europa.ec.aqrsystem.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * A class for drawing the top application specific menu.
 */
public class MenuViewHelper extends BaseActionBean {

    /**
     * Getting all the sections.
     *
     * @return All the sections.
     */
    public Section[] getSections() {
        return Section.values();
    }

    /**
     * Setter.
     *
     * @param currentSection The currently open tab.
     */
    public void setCurrentSection(Section currentSection) {
        this.currentSection = currentSection;
    }
    /**
     * Location of the menu partial JSP.
     */
    private static final String MENU = "WEB-INF/jsp/common/main_menu.jsp";

    /**
     * Generating the main menu.
     *
     * @return The standard Stripes Resolution.
     */
    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution(MENU);
    }

    /**
     * Enumeration for the system tabs.
     */
    public enum Section {

        Home("home", HomeActionBean.class),
        Plan("plan", PlanActionBean.class),
        Apportionment("sourceapp", ApportionmentActionBean.class),
        Evaluation("evsc", EvaluationActionBean.class),
        Measures("measures", MeasureActionBean.class),
        Settings("settings", SettingsActionBean.class);
        /**
         * The resource key for the menu label.
         */
        private final String textKey;
        /**
         * The beanclass that manages the tab.
         */
        private final String beanclass;

        /**
         * Constructor.
         *
         * @param textKey The resource key for the menu label.
         * @param beanclass The beanclass that manages the tab.
         */
        Section(String textKey, Class<? extends ActionBean> beanclass) {
            this.textKey = textKey;
            this.beanclass = beanclass.getName();
        }

        /**
         * Get the resource key with the label for the menu.
         *
         * @return The kay.
         */
        public String getTextKey() {
            return textKey;
        }

        /**
         * Get the beanclass that manages the tab.
         *
         * @return The beanclass.
         */
        public String getBeanclass() {
            return beanclass;
        }
    }
}
