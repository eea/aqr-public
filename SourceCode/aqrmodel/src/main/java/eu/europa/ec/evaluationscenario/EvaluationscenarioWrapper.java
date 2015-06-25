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
package eu.europa.ec.evaluationscenario;

import eu.europa.ec.plan.PlanWrapper;
import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.evaluationscenario.scenario.ScenarioWrapper;
import eu.europa.ec.sourceapprotionment.SourceapportionmentWrapper;
import eu.europa.ec.aqrmodel.Evaluationscenario;
import eu.europa.ec.aqrmodel.Plan;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodel.Scenario;
import eu.europa.ec.aqrmodel.Sourceapportionment;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.user.UserWrapper;

public class EvaluationscenarioWrapper {

    public static EvaluationscenarioBean convertEvaluationscenarioInEvaluationscenarioBean(Evaluationscenario evaluationscenario, Users user) {
        EvaluationscenarioBean evaluationscenarioBean = new EvaluationscenarioBean();

        evaluationscenarioBean.setUuid(evaluationscenario.getUuid());

        evaluationscenarioBean.setInspireidLocalid(evaluationscenario.getInspireidLocalid());
        evaluationscenarioBean.setInspireidNamespace(evaluationscenario.getInspireidNamespace());
        evaluationscenarioBean.setInspireidVersionid(evaluationscenario.getInspireidVersionid());

        evaluationscenarioBean.setCodeofscenario(evaluationscenario.getCodeofscenario());

        evaluationscenarioBean.setAttainmentyearId(evaluationscenario.getAttainmentyearId());
        evaluationscenarioBean.setAttainmentyearPeriodtime(evaluationscenario.getAttainmentyearPeriodtime());

        evaluationscenarioBean.setStartyearId(evaluationscenario.getStartyearId());
        evaluationscenarioBean.setStartyearPeriodtime(evaluationscenario.getStartyearPeriodtime());

        evaluationscenarioBean.setChanges(evaluationscenario.isChanges());
        evaluationscenarioBean.setDescriptionofchanges(evaluationscenario.getDescriptionofchanges());
        evaluationscenarioBean.setReportingstartdate(evaluationscenario.getReportingstartdate());
        evaluationscenarioBean.setReportingenddate(evaluationscenario.getReportingenddate());

        evaluationscenarioBean.setDatecreation(evaluationscenario.getDatecreation());
        evaluationscenarioBean.setDatelastupdate(evaluationscenario.getDatelastupdate());

        evaluationscenarioBean.setCompleted(evaluationscenario.getCompleted());

        Relatedparty provider = evaluationscenario.getProvider();
        evaluationscenarioBean.setProviderBean(RelatedpartyWrapper.convertRelatedpartyInRelatedpartyBean(provider));

        Scenario baselinescenario = evaluationscenario.getBaselinescenario();
        evaluationscenarioBean.setBaselinescenario(ScenarioWrapper.convertScenarioInScenarioBean(baselinescenario));

        Scenario projectionscenario = evaluationscenario.getProjectionscenario();
        evaluationscenarioBean.setProjectionscenario(ScenarioWrapper.convertScenarioInScenarioBean(projectionscenario));

        Plan plan = evaluationscenario.getPlan();
        if (plan != null) {
            evaluationscenarioBean.setPlan(PlanWrapper.convertPlanInPlanBean(plan, user));
        } else {
            evaluationscenarioBean.setPlan(null);
        }

        Sourceapportionment sourceapportionment = evaluationscenario.getSourceapportionment();
        if (sourceapportionment != null) {
            evaluationscenarioBean.setSourceapportionment(SourceapportionmentWrapper.convertSourceapportionmentInSourceapportionmentBean(sourceapportionment, sourceapportionment.getUsers()));
        } else {
            evaluationscenarioBean.setSourceapportionment(null);
        }

        Users userEvaluationscenario = evaluationscenario.getUsers();
        if ("0".equals(user.getUserrole().getUuid())) {
            evaluationscenarioBean.setEditable(false);
        } else if ("1".equals(user.getUserrole().getUuid())) {
            if (user.getCountry().equals(userEvaluationscenario.getCountry())) {
                evaluationscenarioBean.setEditable(true);
            } else {
                evaluationscenarioBean.setEditable(false);
            }
        } else {
            if (user.equals(userEvaluationscenario)) {
                evaluationscenarioBean.setEditable(true);
            } else {
                evaluationscenarioBean.setEditable(false);
            }
        }

        evaluationscenarioBean.setUserBean(UserWrapper.convertUserInUserBean(evaluationscenario.getUsers()));
        if (evaluationscenario.getUserlastupdate() == null) {
            evaluationscenarioBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(evaluationscenario.getUsers()));
        } else {
            evaluationscenarioBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(evaluationscenario.getUserlastupdate()));
        }

        return evaluationscenarioBean;
    }
    
    public static EvaluationscenarioBean convertEvaluationscenarioInEvaluationscenarioBeanTableView(Evaluationscenario evaluationscenario, Users user) {
        EvaluationscenarioBean evaluationscenarioBean = new EvaluationscenarioBean();

        evaluationscenarioBean.setUuid(evaluationscenario.getUuid());

        evaluationscenarioBean.setInspireidLocalid(evaluationscenario.getInspireidLocalid());
        evaluationscenarioBean.setCompleted(evaluationscenario.getCompleted());

        evaluationscenarioBean.setDatecreation(evaluationscenario.getDatecreation());
        evaluationscenarioBean.setDatelastupdate(evaluationscenario.getDatelastupdate());


        Users userEvaluationscenario = evaluationscenario.getUsers();
        if ("0".equals(user.getUserrole().getUuid())) {
            evaluationscenarioBean.setEditable(false);
        } else if ("1".equals(user.getUserrole().getUuid())) {
            if (user.getCountry().equals(userEvaluationscenario.getCountry())) {
                evaluationscenarioBean.setEditable(true);
            } else {
                evaluationscenarioBean.setEditable(false);
            }
        } else {
            if (user.equals(userEvaluationscenario)) {
                evaluationscenarioBean.setEditable(true);
            } else {
                evaluationscenarioBean.setEditable(false);
            }
        }

        evaluationscenarioBean.setUserBean(UserWrapper.convertUserInUserBean(evaluationscenario.getUsers()));
        if (evaluationscenario.getUserlastupdate() == null) {
            evaluationscenarioBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(evaluationscenario.getUsers()));
        } else {
            evaluationscenarioBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(evaluationscenario.getUserlastupdate()));
        }

        return evaluationscenarioBean;
    }
}
