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
package eu.europa.ec.sourceapprotionment;

import eu.europa.ec.sourceapprotionment.localincrement.LocalincrementWrapper;
import eu.europa.ec.sourceapprotionment.regionalbackground.RegionalbackgroundWrapper;
import eu.europa.ec.sourceapprotionment.urbanbackground.UrbanbackgroundWrapper;
import eu.europa.ec.plan.PlanWrapper;
import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.attainment.AttainmentWrapper;
import eu.europa.ec.sourceapprotionment.exceedancedescription.ExceedancedescriptionWrapper;
import eu.europa.ec.aqrmodel.Sourceapportionment;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.user.UserWrapper;

public class SourceapportionmentWrapper {

    public static SourceapportionmentBean convertSourceapportionmentInSourceapportionmentBean(Sourceapportionment sourceapportionment, Users user) {
        SourceapportionmentBean sourceapportionmentBean = new SourceapportionmentBean();

        sourceapportionmentBean.setUuid(sourceapportionment.getUuid());

        sourceapportionmentBean.setInspireidLocalid(sourceapportionment.getInspireidLocalid());
        sourceapportionmentBean.setInspireidNamespace(sourceapportionment.getInspireidNamespace());
        sourceapportionmentBean.setInspireidVersionid(sourceapportionment.getInspireidVersionid());

        sourceapportionmentBean.setReferenceyearId(sourceapportionment.getReferenceyearId());
        sourceapportionmentBean.setReferenceyearTimeperiod(sourceapportionment.getReferenceyearTimeperiod());

        sourceapportionmentBean.setChanges(sourceapportionment.isChanges());
        sourceapportionmentBean.setDescriptionofchanges(sourceapportionment.getDescriptionofchanges());
        sourceapportionmentBean.setReportingstartdate(sourceapportionment.getReportingstartdate());
        sourceapportionmentBean.setReportingenddate(sourceapportionment.getReportingenddate());

        sourceapportionmentBean.setComment(sourceapportionment.getComment());
        sourceapportionmentBean.setCompleted(sourceapportionment.getCompleted());


        sourceapportionmentBean.setProviderBean(RelatedpartyWrapper.convertRelatedpartyInRelatedpartyBean(sourceapportionment.getProvider()));

        sourceapportionmentBean.setDatecreation(sourceapportionment.getDatecreation());
        sourceapportionmentBean.setDatelastupdate(sourceapportionment.getDatelastupdate());

        sourceapportionmentBean.setUrbanbackgroundBean(UrbanbackgroundWrapper.convertUrbanbackgroundInUrbanbackgroundBean(sourceapportionment.getUrbanbackground()));
        sourceapportionmentBean.setRegionalbackgroundBean(RegionalbackgroundWrapper.convertRegionalbackgroundInRegionalbackgroundBean(sourceapportionment.getRegionalbackground()));
        sourceapportionmentBean.setLocalincrementBean(LocalincrementWrapper.convertUrbanbackgroundInUrbanbackgroundBean(sourceapportionment.getLocalincrement()));

        sourceapportionmentBean.setExceedancedescriptionBean(ExceedancedescriptionWrapper.convertExceedancedescriptionInExceedancedescriptionBean(sourceapportionment.getExceedancedescription(), sourceapportionment.getUsers()));

        if (sourceapportionment.getPlan() != null) {
            sourceapportionmentBean.setPlanBean(PlanWrapper.convertPlanInPlanBean(sourceapportionment.getPlan(), sourceapportionment.getUsers()));
        } else {
            sourceapportionmentBean.setPlanBean(null);
        }

        if (sourceapportionment.getAttainment() != null) {
            sourceapportionmentBean.setAttainmentBean(AttainmentWrapper.convertAttainmentInAttainmentBean(sourceapportionment.getAttainment()));
        } else {
            sourceapportionmentBean.setAttainmentBean(null);
        }
        
        Users userSource = sourceapportionment.getUsers();
        if ("0".equals(user.getUserrole().getUuid())) {
            sourceapportionmentBean.setEditable(false);
        } else if ("1".equals(user.getUserrole().getUuid())) {
            if (user.getCountry().equals(userSource.getCountry())) {
                sourceapportionmentBean.setEditable(true);
            } else {
                sourceapportionmentBean.setEditable(false);
            }
        } else {
            if (user.equals(userSource)) {
                sourceapportionmentBean.setEditable(true);
            } else {
                sourceapportionmentBean.setEditable(false);
            }
        }
        
        sourceapportionmentBean.setUserBean(UserWrapper.convertUserInUserBean(sourceapportionment.getUsers()));
        if (sourceapportionment.getUserlastupdate() == null) {
            sourceapportionmentBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(sourceapportionment.getUsers()));
        } else {
            sourceapportionmentBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(sourceapportionment.getUserlastupdate()));
        }

        return sourceapportionmentBean;

    }

    public static SourceapportionmentBean convertSourceapportionmentInSourceapportionmentBeanTableView(Sourceapportionment sourceapportionment, Users user) {
        SourceapportionmentBean sourceapportionmentBean = new SourceapportionmentBean();

        sourceapportionmentBean.setUuid(sourceapportionment.getUuid());

        sourceapportionmentBean.setInspireidLocalid(sourceapportionment.getInspireidLocalid());
        sourceapportionmentBean.setCompleted(sourceapportionment.getCompleted());
        
        sourceapportionmentBean.setDatecreation(sourceapportionment.getDatecreation());
        sourceapportionmentBean.setDatelastupdate(sourceapportionment.getDatelastupdate());

        Users userEvaluationscenario = sourceapportionment.getUsers();
        if ("0".equals(user.getUserrole().getUuid())) {
            sourceapportionmentBean.setEditable(false);
        } else if ("1".equals(user.getUserrole().getUuid())) {
            if (user.getCountry().equals(userEvaluationscenario.getCountry())) {
                sourceapportionmentBean.setEditable(true);
            } else {
                sourceapportionmentBean.setEditable(false);
            }
        } else {
            if (user.equals(userEvaluationscenario)) {
                sourceapportionmentBean.setEditable(true);
            } else {
                sourceapportionmentBean.setEditable(false);
            }
        }

        sourceapportionmentBean.setUserBean(UserWrapper.convertUserInUserBean(sourceapportionment.getUsers()));
        if (sourceapportionment.getUserlastupdate() == null) {
            sourceapportionmentBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(sourceapportionment.getUsers()));
        } else {
            sourceapportionmentBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(sourceapportionment.getUserlastupdate()));
        }


        return sourceapportionmentBean;

    }
}
