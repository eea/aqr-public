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

import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.measures.MeasuresBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * A base class for all the actions of the main home tab.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class HomeActionBean extends BaseActionBean {

    /**
     * Location of the tab view
     */
    protected static String MAIN_VIEW = "WEB-INF/jsp/home/tab.jsp";

    /**
     * The default action of the controller, which displays the tab.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution showTab() {
        return new ForwardResolution(MAIN_VIEW);
    }
    private Statistics statistics;

    /**
     * @return Statistics about the number of items to be displayed to the user.
     */
    public Statistics getStatistics() {
        if (statistics == null) {
            statistics = new Statistics();
        }
        return statistics;
    }

    /**
     * Statistics about the items created for the Home tab.
     */
    public class Statistics {

        /**
         * The number of plan drafts.
         */
        private int planDrafts = 0;
        /**
         * The number of completed plans.
         */
        private int planComplete = 0;
        /**
         * The number of source apportionment drafts.
         */
        private int sourceDrafts = 0;
        /**
         * The number of completed source apportionments.
         */
        private int sourceComplete = 0;
        /**
         * The number of evaluation scenario drafts.
         */
        private int scenarioDrafts = 0;
        /**
         * The number of completed evaluation scenarios.
         */
        private int scenarioComplete = 0;
        /**
         * The number of measure drafts.
         */
        private int measureDrafts = 0;
        /**
         * The number of completed measures.
         */
        private int measureComplete = 0;

        /**
         * Calculating the statistics
         */
        public Statistics() {
            List<PlanBean> plans = getExistingPlans();

            for (PlanBean p : plans) {
                if (p.isCompleted()) {
                    planComplete++;
                } else {
                    planDrafts++;
                }
            }

            List<SourceapportionmentBean> sources = getExistingSourceApportionments();

            for (SourceapportionmentBean p : sources) {
                if (p.isCompleted()) {
                    sourceComplete++;
                } else {
                    sourceDrafts++;
                }
            }

            List<EvaluationscenarioBean> scenarios = getExistingEvaluationScenarios();

            for (EvaluationscenarioBean p : scenarios) {
                if (p.isCompleted()) {
                    scenarioComplete++;
                } else {
                    scenarioDrafts++;
                }
            }

            List<MeasuresBean> measures = getExistingMeasures();

            for (MeasuresBean p : measures) {
                if (p.isCompleted()) {
                    measureComplete++;
                } else {
                    measureDrafts++;
                }
            }
        }

        /**
         * Getter
         *
         * @return
         */
        public int getPlanDrafts() {
            return planDrafts;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getPlanComplete() {
            return planComplete;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getSourceDrafts() {
            return sourceDrafts;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getSourceComplete() {
            return sourceComplete;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getScenarioDrafts() {
            return scenarioDrafts;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getScenarioComplete() {
            return scenarioComplete;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getMeasureDrafts() {
            return measureDrafts;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getMeasureComplete() {
            return measureComplete;
        }
    }
}
