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
package eu.europa.ec.measures;

import eu.europa.ec.measures.plannedimplementation.PlannedimplementationBean;
import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresAdministrationlevel;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresClassification;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresEvaluationscenario;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresSourcesector;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresSpatialscale;
import eu.europa.ec.aqrcrosstablesmodel.SourceapportionmentMeasures;
import eu.europa.ec.aqrmodel.Administrationlevel;
import eu.europa.ec.aqrmodel.Classification;
import eu.europa.ec.aqrmodel.Costs;
import eu.europa.ec.aqrmodel.Evaluationscenario;
import eu.europa.ec.aqrmodel.Expectedimpact;
import java.util.List;
import eu.europa.ec.aqrmodel.Measures;
import eu.europa.ec.aqrmodel.Measuretype;
import eu.europa.ec.aqrmodel.Plannedimplementation;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodel.Sourceapportionment;
import eu.europa.ec.aqrmodel.Sourcesector;
import eu.europa.ec.aqrmodel.Spatialscale;
import eu.europa.ec.aqrmodel.Timescale;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.measures.cost.CostWrapper;
import eu.europa.ec.measures.expectedimpact.ExpectedimpactWrapper;
import eu.europa.ec.measures.plannedimplementation.PlannedimplementationWrapper;
import eu.europa.ec.user.UserWrapper;
import eu.europa.ec.util.EntityManagerCustom;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MeasuresWrapper {

    public MeasuresWrapper() {
    }

    public static MeasuresBean convertMeasuresInMeasuresBean(Measures measures, Users user) {
        MeasuresBean measuresBean = new MeasuresBean();

        measuresBean.setUuid(measures.getUuid());
        measuresBean.setInspireidLocalid(measures.getInspireidLocalid());
        measuresBean.setInspireidNamespace(measures.getInspireidNamespace());
        measuresBean.setInspireidVersionid(measures.getInspireidVersionid());

        measuresBean.setCode(measures.getCode());
        measuresBean.setName(measures.getName());
        measuresBean.setDescription(measures.getDescription());
        measuresBean.setCommentForClarification(measures.getCommentforclarification());
        measuresBean.setCompleted(measures.getCompleted());

        measuresBean.setChanges(measures.isChanges());
        measuresBean.setDescriptionofchanges(measures.getDescriptionofchanges());
        measuresBean.setReportingstartdate(measures.getReportingstartdate());
        measuresBean.setReportingenddate(measures.getReportingenddate());

        measuresBean.setDatecreation(measures.getDatecreation());
        measuresBean.setDatelastupdate(measures.getDatelastupdate());

        /**
         * MeasuresClassification
         */
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Relatedparty provider = measures.getProvider();
        measuresBean.setProviderBean(RelatedpartyWrapper.convertRelatedpartyInRelatedpartyBean(provider));

        Query q = em.createNamedQuery("MeasuresClassification.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresClassification> measuresClassificationList = q.getResultList();
        List<String> classificationURI = new ArrayList<String>();
        for (MeasuresClassification measuresClassification : measuresClassificationList) {
            Classification classification = measuresClassification.getClassification();
            classificationURI.add(classification.getUri());
        }
        measuresBean.setClassification_uri(classificationURI);

        /**
         * Measuretype
         */
        Measuretype measuretype = measures.getMeasuretype();
        if (measuretype != null) {
            measuresBean.setMeasuretype_uri(measuretype.getUri());
        } else {
            measuresBean.setMeasuretype_uri(null);
        }

        /**
         * Administrationlevel
         */
        q = em.createNamedQuery("MeasuresAdministrationlevel.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresAdministrationlevel> measuresAdministrationlevelList = q.getResultList();
        List<String> measuresAdministrationlevelListURI = new ArrayList<String>();
        for (MeasuresAdministrationlevel measuresAdministrationlevel : measuresAdministrationlevelList) {
            Administrationlevel administrationlevel = measuresAdministrationlevel.getAdministractionlevel();
            measuresAdministrationlevelListURI.add(administrationlevel.getUri());
        }
        measuresBean.setAdministrationlevel_uri(measuresAdministrationlevelListURI);

        /**
         * Time scale
         */
        Timescale timescale = measures.getTimescale();
        if (timescale != null) {
            measuresBean.setTimescale_uri(timescale.getUri());
        } else {
            measuresBean.setTimescale_uri(null);
        }

        /**
         * Costs
         */
        Costs costs = measures.getCosts();
        if (costs != null) {
            measuresBean.setCostsBean(CostWrapper.convertCostsInCostsBean(costs));
        }

        /**
         * Sourcesector
         */
        q = em.createNamedQuery("MeasuresSourcesector.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresSourcesector> measuresSourcesectorList = q.getResultList();
        List<String> measuresSourcesectorListURI = new ArrayList<String>();
        for (MeasuresSourcesector measuresSourcesector : measuresSourcesectorList) {
            Sourcesector sourcesector = measuresSourcesector.getSourcesector();
            measuresSourcesectorListURI.add(sourcesector.getUri());
        }
        measuresBean.setSourcesector_uri(measuresSourcesectorListURI);

        /**
         * Spatialscale
         */
        q = em.createNamedQuery("MeasuresSpatialscale.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresSpatialscale> measuresSpatialscaleList = q.getResultList();
        List<String> measuresSpatialscaleListURI = new ArrayList<String>();
        for (MeasuresSpatialscale measuresSpatialscale : measuresSpatialscaleList) {
            Spatialscale sourcesector = measuresSpatialscale.getSpatialscale();
            measuresSpatialscaleListURI.add(sourcesector.getUri());
        }
        measuresBean.setSpatialscale_uri(measuresSpatialscaleListURI);

        /**
         * Plannedimplementation
         */
        Plannedimplementation plannedimplementation = measures.getPlannedimplementation();
        PlannedimplementationBean plannedimplementationBean = PlannedimplementationWrapper.convertPlannedimplementationInPlannedimplementationBean(plannedimplementation);
        measuresBean.setPlannedimplementationBean(plannedimplementationBean);

        /**
         * Reductionofemission
         */
        measuresBean.setReductionofemission(measures.getReductionofemission());
        measuresBean.setReductionofemission_nil(measures.getReductionofemissionNil());
        if (measures.getReductionofemissionNilreason() != null) {
            measuresBean.setReductionofemission_nilreason(measures.getReductionofemissionNilreason());
        } else {
            measuresBean.setReductionofemission_nilreason(null);
        }

        if (measures.getQuantificationnumerical() != null) {
            measuresBean.setQuantificationnumerical_uri(measures.getQuantificationnumerical().getUri());
        } else {
            measuresBean.setQuantificationnumerical_uri(null);
        }

        measuresBean.setComment(measures.getComment());

        /**
         * Sourceapportionment
         */
        q = em.createNamedQuery("SourceapportionmentMeasures.findByMeasures");
        q.setParameter("measures", measures);
        List<SourceapportionmentMeasures> sourceapportionmentMeasuresList = q.getResultList();
        if (sourceapportionmentMeasuresList != null && sourceapportionmentMeasuresList.size() > 0) {
            List<String> sourceapportionmentBeanList = new ArrayList<String>();
            for (SourceapportionmentMeasures sourceapportionmentMeasures : sourceapportionmentMeasuresList) {
                Sourceapportionment sourceapportionment = sourceapportionmentMeasures.getSourceapportionment();
                sourceapportionmentBeanList.add(sourceapportionment.getUuid());
            }
            measuresBean.setSourceapportionmentBeanList(sourceapportionmentBeanList);
        }

        /**
         * Evaluationscenario
         */
        q = em.createNamedQuery("MeasuresEvaluationscenario.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresEvaluationscenario> measuresEvaluationscenarioList = q.getResultList();
        if (measuresEvaluationscenarioList != null && measuresEvaluationscenarioList.size() > 0) {
            List<String> evaluationscenarioBeanList = new ArrayList<String>();
            for (MeasuresEvaluationscenario measuresEvaluationscenario : measuresEvaluationscenarioList) {
                Evaluationscenario evaluationscenario = measuresEvaluationscenario.getEvaluationscenario();
                evaluationscenarioBeanList.add(evaluationscenario.getUuid());
            }
            measuresBean.setEvaluationscenarioBeanList(evaluationscenarioBeanList);
        }

        /**
         * Expectedimpact
         */
        Expectedimpact expectedimpact = measures.getExpectedimpact();
        if (expectedimpact != null) {
            measuresBean.setExpectedimpactBean(ExpectedimpactWrapper.convertExpectedimpactInExpectedimpactBean(expectedimpact));
        }

        Users userMeasures = measures.getUsers();
        if ("0".equals(user.getUserrole().getUuid())) {
            measuresBean.setEditable(false);
        } else if ("1".equals(user.getUserrole().getUuid())) {
            if (user.getCountry().equals(userMeasures.getCountry())) {
                measuresBean.setEditable(true);
            } else {
                measuresBean.setEditable(false);
            }
        } else {
            if (user.equals(userMeasures)) {
                measuresBean.setEditable(true);
            } else {
                measuresBean.setEditable(false);
            }
        }

        measuresBean.setUserBean(UserWrapper.convertUserInUserBean(measures.getUsers()));
        if (measures.getUserlastupdate() == null) {
            measuresBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(measures.getUsers()));
        } else {
            measuresBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(measures.getUserlastupdate()));
        }


        return measuresBean;
    }
    
    public static MeasuresBean convertMeasuresInMeasuresBeanTableView(Measures measures, Users user) {
        MeasuresBean measuresBean = new MeasuresBean();

        measuresBean.setUuid(measures.getUuid());
        measuresBean.setInspireidLocalid(measures.getInspireidLocalid());
        measuresBean.setCompleted(measures.getCompleted());

        measuresBean.setDatecreation(measures.getDatecreation());
        measuresBean.setDatelastupdate(measures.getDatelastupdate());


        Users userMeasures = measures.getUsers();
        if ("0".equals(user.getUserrole().getUuid())) {
            measuresBean.setEditable(false);
        } else if ("1".equals(user.getUserrole().getUuid())) {
            if (user.getCountry().equals(userMeasures.getCountry())) {
                measuresBean.setEditable(true);
            } else {
                measuresBean.setEditable(false);
            }
        } else {
            if (user.equals(userMeasures)) {
                measuresBean.setEditable(true);
            } else {
                measuresBean.setEditable(false);
            }
        }

        measuresBean.setUserBean(UserWrapper.convertUserInUserBean(measures.getUsers()));
        if (measures.getUserlastupdate() == null) {
            measuresBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(measures.getUsers()));
        } else {
            measuresBean.setUserLastUpdateBean(UserWrapper.convertUserInUserBean(measures.getUserlastupdate()));
        }


        return measuresBean;
    }
}
