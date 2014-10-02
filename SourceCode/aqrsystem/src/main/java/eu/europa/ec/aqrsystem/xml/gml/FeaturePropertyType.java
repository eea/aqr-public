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
package eu.europa.ec.aqrsystem.xml.gml;

import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.measures.MeasuresBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_EvaluationScenarioType;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_MeasuresType;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_PlanType;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_SourceApportionmentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Represents gml:FeaturePropertyType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class FeaturePropertyType {

    @XmlElement(namespace = Namespaces.aqd)
    protected AQD_PlanType AQD_Plan;
    @XmlElement(namespace = Namespaces.aqd)
    protected AQD_SourceApportionmentType AQD_SourceApportionment;
    @XmlElement(namespace = Namespaces.aqd)
    protected AQD_EvaluationScenarioType AQD_EvaluationScenario;
    @XmlElement(namespace = Namespaces.aqd)
    protected AQD_MeasuresType AQD_Measures;

    /**
     * Populating the object with data
     *
     * @param p PlanBean
     */
    public void populate(PlanBean p) {
        AQD_Plan = new AQD_PlanType();
        AQD_Plan.populate(p);
    }

    /**
     * Populating the object with data
     *
     * @param p SourceapportionmentBean
     * @param userEmail
     */
    public void populate(SourceapportionmentBean p, String userEmail) {
        AQD_SourceApportionment = new AQD_SourceApportionmentType();
        AQD_SourceApportionment.populate(p, userEmail);
    }

    /**
     * Populating the object with data
     *
     * @param m MeasuresBean
     * @param userEmail
     */
    public void populate(MeasuresBean m, String userEmail) {
        AQD_Measures = new AQD_MeasuresType();
        AQD_Measures.populate(m, userEmail);
    }

    /**
     * Populating the object with data
     *
     * @param p EvaluationscenarioBean
     * @param userEmail
     */
    public void populate(EvaluationscenarioBean p, String userEmail) {
        AQD_EvaluationScenario = new AQD_EvaluationScenarioType();
        AQD_EvaluationScenario.populate(p, userEmail);
    }

    /**
     * Checking if a plan has been imported
     *
     * @return
     */
    public boolean containsPlan() {
        return AQD_Plan != null && AQD_SourceApportionment == null && AQD_Measures == null && AQD_EvaluationScenario == null;
    }

    /**
     * Checking if a source apportionment has been imported
     *
     * @return
     */
    public boolean containsSource() {
        return AQD_Plan == null && AQD_SourceApportionment != null && AQD_Measures == null && AQD_EvaluationScenario == null;
    }

    /**
     * Checking if a measure has been imported
     *
     * @return
     */
    public boolean containsMeasure() {
        return AQD_Plan == null && AQD_SourceApportionment == null && AQD_Measures != null && AQD_EvaluationScenario == null;
    }

    /**
     * Checking if an evaluation scenario has been imported
     *
     * @return
     */
    public boolean containsScenario() {
        return AQD_Plan == null && AQD_SourceApportionment == null && AQD_Measures == null && AQD_EvaluationScenario != null;
    }

    /**
     * Populating the PlanBean with the data from the object
     *
     * @param p
     * @param context
     * @param email
     * @throws java.lang.Exception
     */
    public void setObject(PlanBean p, ActionBeanContext context, String email) throws Exception {
        AQD_Plan.setObject(p, context, email);
    }

    /**
     * Populating the SourceapportionmentBean with the data from the object
     *
     * @param p
     * @param context
     * @param email
     * @throws java.lang.Exception
     */
    public void setObject(SourceapportionmentBean p, ActionBeanContext context, String email) throws Exception {
        AQD_SourceApportionment.setObject(p, context, email);
    }

    /**
     * Populating the MeasuresBean with the data from the object
     *
     * @param p
     * @param context
     * @param email
     * @throws java.lang.Exception
     */
    public void setObject(MeasuresBean p, ActionBeanContext context, String email) throws Exception {
        AQD_Measures.setObject(p, context, email);
    }

    /**
     * Populating the EvaluationscenarioBean with the data from the object
     *
     * @param p
     * @param context
     * @param email
     * @throws java.lang.Exception
     */
    public void setObject(EvaluationscenarioBean p, ActionBeanContext context, String email) throws Exception {
        AQD_EvaluationScenario.setObject(p, context, email);
    }
}
