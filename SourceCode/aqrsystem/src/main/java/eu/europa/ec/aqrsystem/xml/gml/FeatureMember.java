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
 *  Date: 14/05/2014
 *  Authors: European Commission, Joint Research Centre
*  Lucasz Cyra, Emanuela Epure, Daniele Francioli
 *  aq-dev@jrc.ec.europa.eu
 */
package eu.europa.ec.aqrsystem.xml.gml;


import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_AttainmentType;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_EvaluationScenarioType;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_MeasuresType;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_PlanType;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_ReportingHeaderType;
import eu.europa.ec.aqrsystem.xml.aqd.AQD_SourceApportionmentType;
import eu.europa.ec.common.HeaderInterface;
import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.measures.MeasuresBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Represents gml:featureMember
 */
@XmlAccessorType(XmlAccessType.NONE)
public class FeatureMember {
    @XmlElement(namespace=Namespaces.aqd)
    protected AQD_ReportingHeaderType AQD_ReportingHeader;
    @XmlElement(namespace=Namespaces.aqd)
    protected AQD_PlanType AQD_Plan;
    @XmlElement(namespace=Namespaces.aqd)
    protected AQD_SourceApportionmentType AQD_SourceApportionment;
    @XmlElement(namespace=Namespaces.aqd)
    protected AQD_EvaluationScenarioType AQD_EvaluationScenario;
    @XmlElement(namespace=Namespaces.aqd)
    protected AQD_MeasuresType AQD_Measures;
    @XmlElement(namespace=Namespaces.aqd)
    protected AQD_AttainmentType AQD_Attainment;
    
    /**
     * Populating the object with the data from a plan.
     * @param p PlanBean
     */
    public void populateHeader(HeaderInterface p) {
        AQD_ReportingHeader = new AQD_ReportingHeaderType();
        AQD_ReportingHeader.populate(p);
    }
    
    /**
     * Populating the object with the data from a plan.
     * @param p PlanBean
     */
    public void populate(PlanBean p) {
        AQD_Plan = new AQD_PlanType();
        AQD_Plan.populate(p);
    }

    /**
     * Populating the object with the data from a source apportionment.
     * @param p SourceapportionmentBean
     * @param userEmail
     */
    public void populate(SourceapportionmentBean p, String userEmail) {
        AQD_SourceApportionment = new AQD_SourceApportionmentType();
        AQD_SourceApportionment.populate(p, userEmail);
    }
    
    /**
     * Populating the object with the data from a measure.
     * @param m MeasuresBean
     * @param userEmail
     */
    public void populate(MeasuresBean m, String userEmail) {
        AQD_Measures = new AQD_MeasuresType();
        AQD_Measures.populate(m, userEmail);
    }
    
    /**
     * Populating the object with the data from an evaluation scenario.
     * @param p EvaluatonscenarioBean
     * @param userEmail
     */
    public void populate(EvaluationscenarioBean p, String userEmail) {
        AQD_EvaluationScenario = new AQD_EvaluationScenarioType();
        AQD_EvaluationScenario.populate(p, userEmail);
    }
    
    /**
     * Populating the Object Header with data from the object.
     * @param p 
     * @throws java.lang.Exception 
     */
    public void setObjectHeader(HeaderInterface p) throws Exception {
        AQD_ReportingHeader.setObject(p);
    }

    /**
     * Populating the PlanBean with data from the object.
     * @param p 
     * @param context 
     * @param email 
     * @throws java.lang.Exception 
     */
    public void setObject(PlanBean p, ActionBeanContext context, String email) throws Exception {
        AQD_Plan.setObject(p, context, email);
    }

    /**
     * Populating the SourceapportionmentBean with data from the object.
     * @param p 
     * @param context 
     * @param email 
     * @throws java.lang.Exception 
     */
    public void setObject(SourceapportionmentBean p, ActionBeanContext context, String email) throws Exception {
        AQD_SourceApportionment.setObject(p, context, email);
    }
    
    /**
     * Populating the MeasuresBean with data from the object.
     * @param p 
     * @param context 
     * @param email 
     * @throws java.lang.Exception 
     */
    public void setObject(MeasuresBean p, ActionBeanContext context, String email) throws Exception {
        AQD_Measures.setObject(p, context, email);
    }

    /**
     * Populating the EvaluationscenarioBean with data from the object.
     * @param p 
     * @param context 
     * @param email 
     * @throws java.lang.Exception 
     */
    public void setObject(EvaluationscenarioBean p, ActionBeanContext context, String email) throws Exception {
        AQD_EvaluationScenario.setObject(p, context, email);
    }

    /**
     * Checking if the object contains a header
     * @return 
     */
    public boolean containsHeader() {
        return AQD_ReportingHeader != null &&
               AQD_Plan == null &&
               AQD_SourceApportionment == null &&
               AQD_EvaluationScenario == null &&
               AQD_Measures == null &&
               AQD_Attainment == null;
    }

    /**
     * Checking if a plan has been imported
     * @return 
     */
    public boolean containsPlan() {
        return AQD_ReportingHeader == null &&
               AQD_Plan != null &&
               AQD_SourceApportionment == null &&
               AQD_EvaluationScenario == null &&
               AQD_Measures == null &&
               AQD_Attainment == null;
    }

    /**
     * Checking if a measure has been imported
     * @return 
     */
    public boolean containsMeasure() {
        return AQD_ReportingHeader == null &&
               AQD_Plan == null &&
               AQD_SourceApportionment == null &&
               AQD_EvaluationScenario == null &&
               AQD_Measures != null &&
               AQD_Attainment == null;
    }
    
    /**
     * Checking if an attainment has been imported
     * @return 
     */
    public boolean containsAttainment() {
        return AQD_ReportingHeader == null &&
               AQD_Plan == null &&
               AQD_SourceApportionment == null &&
               AQD_EvaluationScenario == null &&
               AQD_Measures == null &&
               AQD_Attainment != null;
    }

    /**
     * Checking if an evaluation scenario has been imported
     * @return 
     */
    public boolean containsScenario() {
        return AQD_ReportingHeader == null &&
               AQD_Plan == null &&
               AQD_SourceApportionment == null &&
               AQD_EvaluationScenario != null &&
               AQD_Measures == null &&
               AQD_Attainment == null;
    }

    /**
     * Checking if a source apportionment has been imported
     * @return 
     */
    public boolean containsSource() {
        return AQD_ReportingHeader == null &&
               AQD_Plan == null &&
               AQD_SourceApportionment != null &&
               AQD_EvaluationScenario == null &&
               AQD_Measures == null &&
               AQD_Attainment == null;
    }

    /**
     * Saving an attainment with the account of the given user
     * @param userEmail The user
     * @param context ActionBeanContext to which errors and messages can be
     * reported
     * @return 
     * @throws java.lang.Exception
     */
    public boolean saveAttainment(String userEmail, ActionBeanContext context) throws Exception {
        return AQD_Attainment.save(userEmail, context);
    }
}
