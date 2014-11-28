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
package eu.europa.ec.evaluationscenario.scenario;

import eu.europa.ec.aqrcrosstablesmodel.MeasuresScenario;
import eu.europa.ec.aqrmodel.Scenario;
import eu.europa.ec.util.EntityManagerCustom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ScenarioWrapper {

    public static ScenarioBean convertScenarioInScenarioBean(Scenario scenario) {
        ScenarioBean scenarioBean = new ScenarioBean();

        scenarioBean.setUuid(scenario.getUuid());

        scenarioBean.setInspireidLocalid(scenario.getInspireidLocalid());
        scenarioBean.setInspireidNamespace(scenario.getInspireidNamespace());
        scenarioBean.setInspireidVersionid(scenario.getInspireidVersionid());

        scenarioBean.setDescription(scenario.getDescription());

        scenarioBean.setExpectedconcentration(scenario.getExpectedconcentration());
        scenarioBean.setComment(scenario.getComment());

        scenarioBean.setTotalemissions(scenario.getTotalemissions());
        scenarioBean.setExpectedexceedence(scenario.getExpectedexceedence());

        scenarioBean.setMeasuresUuid(getAllMeasuresLinksByScenarioID(scenario.getUuid()));

        return scenarioBean;
    }

    /**
     *
     * @param scenarioID
     * @return a list of MeasuresBean
     */
    private static List<String> getAllMeasuresLinksByScenarioID(String scenarioID) {
        List<String> measuresBeanList = new ArrayList<String>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Scenario.findByUuid");
        q.setParameter("uuid", scenarioID);
        Scenario scenario = (Scenario) q.getSingleResult();

        q = em.createNamedQuery("MeasuresScenario.findByScenario");
        q.setParameter("scenario", scenario);
        List<MeasuresScenario> measuresScenarioList = q.getResultList();
        for (MeasuresScenario measuresScenario : measuresScenarioList) {
            measuresBeanList.add(measuresScenario.getMeasures().getUuid());
        }

        return measuresBeanList;
    }
}
