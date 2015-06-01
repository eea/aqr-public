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
package eu.europa.ec.plan;

import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.aqrmodel.Plan;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.user.UserWrapper;

public class PlanWrapper {

    public PlanWrapper() {
    }

    public static PlanBean convertPlanInPlanBean(Plan plan, Users user) {
        PlanBean planBean = new PlanBean();

        planBean.setUuid(plan.getUuid());

        planBean.setInspireidLocalid(plan.getInspireidLocalid());
        planBean.setInspireidNamespace(plan.getInspireidNamespace());
        planBean.setInspireidVersionid(plan.getInspireidVersionid());

        planBean.setCode(plan.getCode());
        planBean.setName(plan.getName());

        planBean.setFirstexceedanceyearId(plan.getFirstexceedanceyearId());
        planBean.setFirstexceedanceyearTimeposition(plan.getFirstexceedanceyearTimeposition());

        if (plan.getStatusplan() != null) {
            String preferredLabel = plan.getStatusplan().getPreferredLabel();
            String link = plan.getStatusplan().getLink();

            planBean.setPreferredLabel(preferredLabel);
            planBean.setLink(link);
        } else {
            planBean.setPreferredLabel(null);
            planBean.setLink(null);
        }

        planBean.setAdoptiondateId(plan.getAdoptiondateId());
        planBean.setAdoptiondateTimeposition(plan.getAdoptiondateTimeposition());

        planBean.setTimetable(plan.getTimetable());
        planBean.setReferenceaqplan(plan.getReferenceaqplan());
        planBean.setReferenceimplementation(plan.getReferenceimplementation());

        planBean.setComment(plan.getComment());
        planBean.setCompleted(plan.getCompleted());

        planBean.setChanges(plan.isChanges());
        planBean.setDescriptionofchanges(plan.getDescriptionofchanges());
        planBean.setReportingstartdate(plan.getReportingstartdate());
        planBean.setReportingenddate(plan.getReportingenddate());

        planBean.setDatecreation(plan.getDatecreation());
        planBean.setDatelastupdate(plan.getDatelastupdate());

        Relatedparty competentauthority = plan.getCompetentauthority();
        planBean.setRelatedpartyBean(RelatedpartyWrapper.convertRelatedpartyInRelatedpartyBean(competentauthority));

        Relatedparty provider = plan.getProvider();
        planBean.setProviderBean(RelatedpartyWrapper.convertRelatedpartyInRelatedpartyBean(provider));

        PlanManager planManager = new PlanManager();
        planBean.setAttainmentBeanList(planManager.getAttainmentByPlanID(plan));

        Users userPlan = plan.getUsers();

        if ("0".equals(user.getUserrole().getUuid())) {
            planBean.setEditable(false);
        } else if ("1".equals(user.getUserrole().getUuid())) {
            if (user.getCountry().equals(userPlan.getCountry())) {
                planBean.setEditable(true);
            } else {
                planBean.setEditable(false);
            }
        } else {
            if (user.equals(userPlan)) {
                planBean.setEditable(true);
            } else {
                planBean.setEditable(false);
            }
        }

        planBean.setUserBean(UserWrapper.convertUserInUserBean(plan.getUsers()));
        if (plan.getUserlastupdate()== null) {
            planBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(plan.getUsers()));
        } else {
            planBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(plan.getUserlastupdate()));
        }

        return planBean;
    }
    
    public static PlanBean convertPlanInPlanBeanTableView(Plan plan, Users user) {
        PlanBean planBean = new PlanBean();

        /**uri and inspire id*/
        planBean.setUuid(plan.getUuid());

        planBean.setInspireidLocalid(plan.getInspireidLocalid());

        /**dates*/
        planBean.setDatecreation(plan.getDatecreation());
        planBean.setDatelastupdate(plan.getDatelastupdate());
        /**completed*/
        planBean.setCompleted(plan.getCompleted());

        /**users*/
        Users userPlan = plan.getUsers();

        if ("0".equals(user.getUserrole().getUuid())) {
            planBean.setEditable(false);
        } else if ("1".equals(user.getUserrole().getUuid())) {
            if (user.getCountry().equals(userPlan.getCountry())) {
                planBean.setEditable(true);
            } else {
                planBean.setEditable(false);
            }
        } else {
            if (user.equals(userPlan)) {
                planBean.setEditable(true);
            } else {
                planBean.setEditable(false);
            }
        }

        planBean.setUserBean(UserWrapper.convertUserInUserBean(plan.getUsers()));
        if (plan.getUserlastupdate()== null) {
            planBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(plan.getUsers()));
        } else {
            planBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(plan.getUserlastupdate()));
        }

        return planBean;
    }
}
