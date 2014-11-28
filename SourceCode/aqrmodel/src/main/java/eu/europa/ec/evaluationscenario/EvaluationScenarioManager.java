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

import eu.europa.ec.common.publication.PublicationBean;
import eu.europa.ec.common.publication.Publicationwrapper;
import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.aqrcrosstablesmodel.EvaluationscenarioPublication;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresScenario;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.evaluationscenario.scenario.ScenarioBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.aqrmodel.Evaluationscenario;
import eu.europa.ec.aqrmodel.Measures;
import eu.europa.ec.aqrmodel.Plan;
import eu.europa.ec.aqrmodel.Publication;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodel.Scenario;
import eu.europa.ec.aqrmodel.Sourceapportionment;
import eu.europa.ec.aqrmodeluser.Country;
import eu.europa.ec.aqrmodeluser.Systemconfiguration;
import eu.europa.ec.aqrmodeluser.Userrole;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.EntityManagerCustom;
import eu.europa.ec.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EvaluationScenarioManager {

    private final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public EvaluationScenarioManager() {
    }

    /**
     *
     * @param userEmail
     * @return the Id of the draft EvaluationScenario
     * @throws java.lang.Exception
     */
    public String createEvaluationScenarioDraftForUser(String userEmail) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        Evaluationscenario evaluationscenario = new Evaluationscenario();
        String evaluationScenarioUuid = StringUtils.createUUID(user.getEmail() + dateFormatUtil.getToday(), Evaluationscenario.class);
        evaluationscenario.setUuid(evaluationScenarioUuid);

        evaluationscenario.setInspireidLocalid("Draft_" + dateFormatUtil.getToday());
        evaluationscenario.setInspireidNamespace(systemconfiguration.getNamespace());
        evaluationscenario.setInspireidVersionid(dateFormatUtil.getToday());

        evaluationscenario.setCodeofscenario("");

        evaluationscenario.setAttainmentyearId("");
        evaluationscenario.setAttainmentyearPeriodtime("");

        evaluationscenario.setStartyearId("");
        evaluationscenario.setStartyearPeriodtime("");

        evaluationscenario.setChanges(true);
        evaluationscenario.setDescriptionofchanges("");
        evaluationscenario.setReportingstartdate("");
        evaluationscenario.setReportingenddate("");

        evaluationscenario.setCompleted(false);
        evaluationscenario.setUsers(user);

        RelatedpartyWrapper relatedpartyWrapper = new RelatedpartyWrapper();
        Relatedparty provider = relatedpartyWrapper.createDraftProviderFromUser(evaluationScenarioUuid, user);
        evaluationscenario.setProvider(provider);

        evaluationscenario.setDatecreation(new Date());
        evaluationscenario.setDatelastupdate(new Date());

        Scenario baselinescenario = new Scenario();
        String baselinescenarioUuid = StringUtils.createUUID(user.getEmail() + dateFormatUtil.getToday(), Scenario.class);
        baselinescenario.setUuid(baselinescenarioUuid);
        baselinescenario.setInspireidLocalid("Draft " + dateFormatUtil.getToday());
        baselinescenario.setInspireidNamespace(systemconfiguration.getNamespace());
        baselinescenario.setInspireidVersionid(new Date() + "");
        baselinescenario.setDescription("");
        baselinescenario.setComment("");
        baselinescenario.setTotalemissions("");
        evaluationscenario.setBaselinescenario(baselinescenario);

        Scenario projectionscenario = new Scenario();
        String projectionscenarioUuid = StringUtils.createUUID(evaluationScenarioUuid + user.getEmail() + dateFormatUtil.getToday(), Scenario.class);
        projectionscenario.setUuid(projectionscenarioUuid);
        projectionscenario.setInspireidLocalid("Draft " + dateFormatUtil.getToday());
        projectionscenario.setInspireidNamespace(systemconfiguration.getNamespace());
        projectionscenario.setInspireidVersionid(new Date() + "");
        projectionscenario.setDescription("");
        projectionscenario.setComment("");
        projectionscenario.setTotalemissions("");
        evaluationscenario.setProjectionscenario(projectionscenario);

        emc.beginTransaction(em);
        em.persist(provider);
        em.persist(baselinescenario);
        em.persist(projectionscenario);
        em.persist(evaluationscenario);
        emc.commitAndCloseTransaction(em);

        return evaluationscenario.getUuid();
    }

    /**
     *
     * @param userEmail
     * @return all the EvaluationscenarioBean
     */
    public List<EvaluationscenarioBean> getAllEvaluationscenarioByUser(String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userEvaluationscenario = (Users) q.getSingleResult();
        Country country = userEvaluationscenario.getCountry();
        Userrole userrole = userEvaluationscenario.getUserrole();

        List<EvaluationscenarioBean> evaluationscenarioBeanList = new ArrayList<EvaluationscenarioBean>();

        if (!userrole.getUuid().equals("0")) {
            q = em.createNamedQuery("Users.findByCountry");
            q.setParameter("country", country);

            List<Users> userList = q.getResultList();
            for (Users users : userList) {
                q = em.createNamedQuery("Evaluationscenario.findAllByUser");
                q.setParameter("users", users);

                List<Evaluationscenario> results = (List<Evaluationscenario>) q.getResultList();
                for (Evaluationscenario evaluationscenario : results) {
                    evaluationscenarioBeanList.add(EvaluationscenarioWrapper.convertEvaluationscenarioInEvaluationscenarioBean(evaluationscenario, userEvaluationscenario));
                }
            }
        } else {
            q = em.createNamedQuery("Evaluationscenario.findAll");

            List<Evaluationscenario> results = (List<Evaluationscenario>) q.getResultList();
            for (Evaluationscenario evaluationscenario : results) {
                evaluationscenarioBeanList.add(EvaluationscenarioWrapper.convertEvaluationscenarioInEvaluationscenarioBean(evaluationscenario, userEvaluationscenario));
            }
        }

        em.close();
        return evaluationscenarioBeanList;
    }

    /**
     *
     * @param evaluationScenarioID
     * @param userEmail
     * @return the ID of the cloned Evaluationscenario
     * @throws java.lang.Exception
     */
    public String cloneEvaluationscenarioByEvaluationscenarioIDAndUser(String evaluationScenarioID, String userEmail) throws Exception {
        Evaluationscenario cloneEvaluationscenario = new Evaluationscenario();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userWhichClone = (Users) q.getSingleResult();

        q = em.createNamedQuery("Evaluationscenario.findByUuid");
        q.setParameter("uuid", evaluationScenarioID);
        Evaluationscenario evaluationScenario = (Evaluationscenario) q.getSingleResult();

        String cloneEvaluationScenarioUuid = StringUtils.createUUID(evaluationScenarioID + dateFormatUtil.getToday(), Evaluationscenario.class);
        cloneEvaluationscenario.setUuid(cloneEvaluationScenarioUuid);

        Country country = userWhichClone.getCountry();
        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        cloneEvaluationscenario.setInspireidLocalid("Clone_" + dateFormatUtil.getToday());
        cloneEvaluationscenario.setInspireidNamespace(systemconfiguration.getNamespace());
        cloneEvaluationscenario.setInspireidVersionid(dateFormatUtil.getToday());

        cloneEvaluationscenario.setCodeofscenario(evaluationScenario.getCodeofscenario());

        cloneEvaluationscenario.setAttainmentyearId(evaluationScenario.getAttainmentyearId());
        cloneEvaluationscenario.setAttainmentyearPeriodtime(evaluationScenario.getAttainmentyearPeriodtime());

        cloneEvaluationscenario.setStartyearId(evaluationScenario.getStartyearId());
        cloneEvaluationscenario.setStartyearPeriodtime(evaluationScenario.getStartyearPeriodtime());

        cloneEvaluationscenario.setCompleted(evaluationScenario.getCompleted());
        cloneEvaluationscenario.setUsers(userWhichClone);

        cloneEvaluationscenario.setDatecreation(new Date());
        cloneEvaluationscenario.setDatelastupdate(new Date());

        cloneEvaluationscenario.setChanges(evaluationScenario.isChanges());
        cloneEvaluationscenario.setDescriptionofchanges(evaluationScenario.getDescriptionofchanges());
        cloneEvaluationscenario.setReportingstartdate(evaluationScenario.getReportingstartdate());
        cloneEvaluationscenario.setReportingenddate(evaluationScenario.getReportingenddate());

        cloneEvaluationscenario.setPlan(evaluationScenario.getPlan());
        cloneEvaluationscenario.setSourceapportionment(evaluationScenario.getSourceapportionment());

        emc.beginTransaction(em);

        Relatedparty provider = evaluationScenario.getProvider();
        Relatedparty cloneProvider = new Relatedparty();
        String cloneProviderUuid = StringUtils.createUUID(cloneEvaluationscenario + userWhichClone.getEmail() + dateFormatUtil.getToday(), Relatedparty.class);
        cloneProvider.setUuid(cloneProviderUuid);
        cloneProvider.setIndividualname(provider.getIndividualname());
        cloneProvider.setOrganisationname(provider.getOrganisationname());
        cloneProvider.setWebsite(provider.getWebsite());
        cloneProvider.setAddress(provider.getAddress());
        cloneProvider.setElectronicmailaddress(provider.getElectronicmailaddress());
        cloneProvider.setTelephonevoice(provider.getTelephonevoice());
        em.persist(cloneProvider);
        cloneEvaluationscenario.setProvider(cloneProvider);

        Scenario baselinescenario = evaluationScenario.getBaselinescenario();
        Scenario cloneBaselinescenario = cloneScenario(baselinescenario);
        cloneEvaluationscenario.setBaselinescenario(cloneBaselinescenario);

        Scenario projectionscenario = evaluationScenario.getProjectionscenario();
        Scenario cloneProjectionscenario = cloneScenario(projectionscenario);
        cloneEvaluationscenario.setProjectionscenario(cloneProjectionscenario);

        em.persist(cloneEvaluationscenario);

        emc.commitAndCloseTransaction(em);

        List<PublicationBean> publicationBeanList = getAllPublicationBeanByEvaluationscenario(evaluationScenarioID);
        for (PublicationBean publicationBean : publicationBeanList) {
            publicationBean.setUuid(null);
            savePublicationToEvaluationscenario(cloneEvaluationscenario.getUuid(), publicationBean);
        }

        return cloneEvaluationscenario.getUuid();
    }

    /**
     * delete the EvaluationScenario given the ID
     *
     * @param evaluationScenarioID
     * @throws eu.europa.ec.aqrexception.DeleteEvaluationscenarioException
     */
    public void deleteEvaluationscenarioByID(String evaluationScenarioID) throws DeleteEvaluationscenarioException {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Evaluationscenario.findByUuid");
        q.setParameter("uuid", evaluationScenarioID);
        Evaluationscenario evaluationscenario = (Evaluationscenario) q.getSingleResult();

        q = em.createNamedQuery("EvaluationscenarioPublication.findByEvaluationscenario");
        q.setParameter("evaluationscenario", evaluationscenario);
        List<EvaluationscenarioPublication> publicationList = (List<EvaluationscenarioPublication>) q.getResultList();
        for (EvaluationscenarioPublication evaluationScenarioPublication : publicationList) {
            q = em.createNamedQuery("EvaluationScenarioPublication.deleteByEvaluationscenarioAndPublication");
            q.setParameter("evaluationscenario", evaluationScenarioPublication.getEvaluationscenario());
            q.setParameter("publication", evaluationScenarioPublication.getPublication());
            q.executeUpdate();

            q = em.createNamedQuery("Publication.deleteByUuid");
            q.setParameter("uuid", evaluationScenarioPublication.getPublication().getUuid());
            q.executeUpdate();
        }

        Scenario baselinescenario = evaluationscenario.getBaselinescenario();
        Scenario projectionscenario = evaluationscenario.getProjectionscenario();
        Relatedparty provider = evaluationscenario.getProvider();

        q = em.createNamedQuery("Evaluationscenario.deleteByUuid");
        q.setParameter("uuid", evaluationscenario.getUuid());
        try {
            q.executeUpdate();
        } catch (Exception ex) {
            throw new DeleteEvaluationscenarioException();
        }

        q = em.createNamedQuery("MeasuresScenario.deleteByScenario");
        q.setParameter("scenario", baselinescenario);
        q.executeUpdate();

        q = em.createNamedQuery("MeasuresScenario.deleteByScenario");
        q.setParameter("scenario", projectionscenario);
        q.executeUpdate();

        q = em.createNamedQuery("Scenario.deleteByUuid");
        q.setParameter("uuid", baselinescenario.getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Scenario.deleteByUuid");
        q.setParameter("uuid", projectionscenario.getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Relatedparty.deleteByUuid");
        q.setParameter("uuid", provider.getUuid());
        q.executeUpdate();

        emc.commitAndCloseTransaction(em);
    }

    /**
     * delete a publication by ID
     *
     * @param publicationID
     */
    public void deletePublicationByPublicationID(String publicationID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Publication.findByUuid");
        q.setParameter("uuid", publicationID);
        Publication publication = (Publication) q.getSingleResult();

        q = em.createNamedQuery("EvaluationscenarioPublication.deleteByPublication");
        q.setParameter("publication", publication);

        emc.beginTransaction(em);
        q.executeUpdate();

        q = em.createNamedQuery("Publication.deleteByUuid");
        q.setParameter("uuid", publicationID);
        q.executeUpdate();

        emc.commitAndCloseTransaction(em);
    }

    /**
     * Save/Edit the EvaluationScenario
     *
     * @param evaluationscenarioBean
     * @throws java.lang.Exception
     * @throws
     * eu.europa.ec.aqrexception.EvaluationscenarioINSPIRELocalIDAlreadyExistingException
     */
    public void saveEvaluationScenarioDraft(EvaluationscenarioBean evaluationscenarioBean) throws Exception, EvaluationscenarioINSPIRELocalIDAlreadyExistingException {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Evaluationscenario.findByUuid");
        q.setParameter("uuid", evaluationscenarioBean.getUuid());
        Evaluationscenario evaluationscenario = ((List<Evaluationscenario>) q.getResultList()).get(0);

        String userEmail = evaluationscenario.getUsers().getEmail();
        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        boolean evaluationscenarioINSPIRELocalIDAlreadyExisting = false;

        if (evaluationscenarioBean.getInspireidLocalid() != null) {

            if (!evaluationscenario.getInspireidLocalid().equals(evaluationscenarioBean.getInspireidLocalid())) {
                q = em.createNamedQuery("Evaluationscenario.findByInspireidLocalid");
                q.setParameter("inspireidLocalid", evaluationscenarioBean.getInspireidLocalid());

                List<Evaluationscenario> evaluationscenarioList = q.getResultList();
                int k = 0;
                for (Evaluationscenario existentEvaluationscenario : evaluationscenarioList) {
                    if (!existentEvaluationscenario.getUsers().getCountry().equals(country)) {
                        k++;
                    }
                }

                if (k == evaluationscenarioList.size()) {
                    evaluationscenario.setInspireidLocalid(evaluationscenarioBean.getInspireidLocalid());
                } else {
                    evaluationscenarioINSPIRELocalIDAlreadyExisting = true;
                }
            }
        } else {
            evaluationscenario.setInspireidLocalid("");
        }

        if (evaluationscenarioBean.getInspireidNamespace() != null) {
            evaluationscenario.setInspireidNamespace(evaluationscenarioBean.getInspireidNamespace());
        } else {
            try {
                q = em.createNamedQuery("Systemconfiguration.findByCountry");
                q.setParameter("country", user.getCountry());
                Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();
                evaluationscenario.setInspireidNamespace(systemconfiguration.getNamespace());
            } catch (Exception e) {
                evaluationscenario.setInspireidNamespace("");
            }
        }
        evaluationscenario.setInspireidVersionid(dateFormatUtil.getToday());

        if (evaluationscenarioBean.getCodeofscenario() != null) {
            evaluationscenario.setCodeofscenario(evaluationscenarioBean.getCodeofscenario());
        } else {
            evaluationscenario.setCodeofscenario("");
        }

        if (evaluationscenarioBean.getAttainmentyearId() != null) {
            evaluationscenario.setAttainmentyearId(evaluationscenarioBean.getAttainmentyearId());
        } else {
            evaluationscenario.setAttainmentyearId("");
        }
        if (evaluationscenarioBean.getAttainmentyearPeriodtime() != null) {
            evaluationscenario.setAttainmentyearPeriodtime(evaluationscenarioBean.getAttainmentyearPeriodtime());
        } else {
            evaluationscenario.setAttainmentyearPeriodtime("");
        }

        if (evaluationscenarioBean.getStartyearId() != null) {
            evaluationscenario.setStartyearId(evaluationscenarioBean.getStartyearId());
        } else {
            evaluationscenario.setStartyearId("");
        }
        if (evaluationscenarioBean.getStartyearPeriodtime() != null) {
            evaluationscenario.setStartyearPeriodtime(evaluationscenarioBean.getStartyearPeriodtime());
        } else {
            evaluationscenario.setStartyearPeriodtime("");
        }

        evaluationscenario.setCompleted(evaluationscenarioBean.isCompleted());
        evaluationscenario.setDatelastupdate(new Date());

        evaluationscenario.setChanges(evaluationscenarioBean.isChanges());
        if (evaluationscenarioBean.isChanges()) {
            if (evaluationscenarioBean.getDescriptionofchanges() != null) {
                evaluationscenario.setDescriptionofchanges(evaluationscenarioBean.getDescriptionofchanges());
            } else {
                evaluationscenario.setDescriptionofchanges("");
            }
        }

        if (evaluationscenarioBean.getReportingstartdate() != null) {
            evaluationscenario.setReportingstartdate(evaluationscenarioBean.getReportingstartdate());
        } else {
            evaluationscenario.setReportingstartdate("");
        }
        if (evaluationscenarioBean.getReportingenddate() != null) {
            evaluationscenario.setReportingenddate(evaluationscenarioBean.getReportingenddate());
        } else {
            evaluationscenario.setReportingenddate("");
        }

        emc.beginTransaction(em);

        RelatedpartyWrapper relatedpartyWrapper = new RelatedpartyWrapper();
        Relatedparty provider = relatedpartyWrapper.saveProviderDraft(em, evaluationscenario.getProvider().getUuid(), evaluationscenarioBean.getProviderBean(), evaluationscenario.getUsers());

        em.merge(provider);

        ScenarioBean baselinescenarionBean = evaluationscenarioBean.getBaselinescenario();
        if (baselinescenarionBean != null) {
            Scenario baselinescenario = evaluationscenario.getBaselinescenario();
            updateScenario(baselinescenarionBean, baselinescenario, em);
            em.merge(baselinescenario);
        }

        ScenarioBean projectionscenarioBean = evaluationscenarioBean.getProjectionscenario();
        if (projectionscenarioBean != null) {
            Scenario projectionscenario = evaluationscenario.getProjectionscenario();
            updateScenario(projectionscenarioBean, projectionscenario, em);
            em.merge(projectionscenario);
        }

        PlanBean planBean = evaluationscenarioBean.getPlan();
        if (planBean != null && planBean.getUuid() != null) {
            String planUuid = planBean.getUuid();

            q = em.createNamedQuery("Plan.findByUuid");
            q.setParameter("uuid", planUuid);
            Plan plan = (Plan) q.getSingleResult();

            evaluationscenario.setPlan(plan);
        } else {
            evaluationscenario.setPlan(null);
        }

        SourceapportionmentBean sourceapportionmentBean = evaluationscenarioBean.getSourceapportionment();
        if (sourceapportionmentBean != null && sourceapportionmentBean.getUuid() != null) {
            String sourceapportionmentBeanUuid = sourceapportionmentBean.getUuid();

            q = em.createNamedQuery("Sourceapportionment.findByUuid");
            q.setParameter("uuid", sourceapportionmentBeanUuid);
            Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();

            evaluationscenario.setSourceapportionment(sourceapportionment);
        } else {
            evaluationscenario.setSourceapportionment(null);
        }

        em.merge(evaluationscenario);
        emc.commitAndCloseTransaction(em);

        if (evaluationscenarioINSPIRELocalIDAlreadyExisting) {
            throw new EvaluationscenarioINSPIRELocalIDAlreadyExistingException();
        }
    }

    private void updateScenario(ScenarioBean scenarioBean, Scenario scenario, EntityManager em) throws Exception {
        if (scenarioBean.getInspireidLocalid() != null) {
            scenario.setInspireidLocalid(scenarioBean.getInspireidLocalid());
        } else {
            scenario.setInspireidLocalid("");
        }
        if (scenarioBean.getInspireidNamespace() != null) {
            scenario.setInspireidNamespace(scenarioBean.getInspireidNamespace());
        } else {
            scenario.setInspireidNamespace("");
        }
        if (scenarioBean.getInspireidVersionid() != null) {
            scenario.setInspireidVersionid(scenarioBean.getInspireidVersionid());
        } else {
            scenario.setInspireidVersionid(new Date() + "");
        }

        if (scenarioBean.getDescription() != null) {
            scenario.setDescription(scenarioBean.getDescription());
        } else {
            scenario.setDescription("");
        }

        if (scenarioBean.getExpectedconcentration() != null) {
            scenario.setExpectedconcentration(scenarioBean.getExpectedconcentration());
        } else {
            scenario.setExpectedconcentration("");
        }

        if (scenarioBean.getComment() != null) {
            scenario.setComment(scenarioBean.getComment());
        } else {
            scenario.setComment("");
        }

        if (scenarioBean.getTotalemissions() != null) {
            scenario.setTotalemissions(scenarioBean.getTotalemissions());
        } else {
            scenario.setTotalemissions("");
        }
        if (scenarioBean.getExpectedexceedence() != null) {
            scenario.setExpectedexceedence(scenarioBean.getExpectedexceedence());
        } else {
            scenario.setExpectedexceedence("");
        }

        if (scenarioBean.getMeasuresUuid() != null && !scenarioBean.getMeasuresUuid().isEmpty()) {
            List<String> measuresBeanList = scenarioBean.getMeasuresUuid();
            Query q = em.createNamedQuery("MeasuresScenario.deleteByScenario");
            q.setParameter("scenario", scenario);
            q.executeUpdate();

            for (String measuresUuid : measuresBeanList) {
                q = em.createNamedQuery("Measures.findByUuid");
                q.setParameter("uuid", measuresUuid);
                Measures measures = (Measures) q.getSingleResult();

                MeasuresScenario measuresScenario = new MeasuresScenario();
                String measuresScenarioUuid = StringUtils.createUUID(measures.getUuid() + scenario.getUuid() + dateFormatUtil.getToday(), MeasuresScenario.class);
                measuresScenario.setUuid(measuresScenarioUuid);
                measuresScenario.setMeasures(measures);
                measuresScenario.setScenario(scenario);

                em.persist(measuresScenario);
            }
        } else {
            Query q = em.createNamedQuery("MeasuresScenario.deleteByScenario");
            q.setParameter("scenario", scenario);
            q.executeUpdate();
        }
    }

    /**
     *
     * @param evaluationscenarioID
     * @return a list of PublicationBean by Plan ID
     */
    public List<PublicationBean> getAllPublicationBeanByEvaluationscenario(String evaluationscenarioID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Evaluationscenario.findByUuid");
        q.setParameter("uuid", evaluationscenarioID);
        Evaluationscenario evaluationscenario = (Evaluationscenario) q.getSingleResult();

        q = em.createNamedQuery("EvaluationscenarioPublication.findByEvaluationscenario");
        q.setParameter("evaluationscenario", evaluationscenario);
        List<EvaluationscenarioPublication> results = q.getResultList();

        List<PublicationBean> publicationList = new ArrayList<PublicationBean>();
        for (EvaluationscenarioPublication evaluationscenarioPublication : results) {
            publicationList.add(Publicationwrapper.convertPublicationInPublicationBean(evaluationscenarioPublication.getPublication()));
        }
        em.close();
        return publicationList;
    }

    /**
     *
     * @param evaluationscenarioID
     * @param userEmail
     * @return the EvaluationscenarioBean
     */
    public EvaluationscenarioBean getEvaluationscenarioByID(String evaluationscenarioID, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userEvaluationscenario = (Users) q.getSingleResult();

        q = em.createNamedQuery("Evaluationscenario.findByUuid");
        q.setParameter("uuid", evaluationscenarioID);
        Evaluationscenario evaluationscenario = (Evaluationscenario) q.getSingleResult();
        EvaluationscenarioBean evaluationscenarioBean = EvaluationscenarioWrapper.convertEvaluationscenarioInEvaluationscenarioBean(evaluationscenario, userEvaluationscenario);
        em.close();
        return evaluationscenarioBean;
    }

    /**
     *
     * @param evaluationscenarioINSPIRELocalID
     * @param userEmail
     * @return the EvaluationscenarioBean for the measureINSPIRELocalID
     */
    public EvaluationscenarioBean getEvaluationscenarioByINSPIRELocalID(String evaluationscenarioINSPIRELocalID, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userEvaluationscenario = (Users) q.getSingleResult();
        Country country = userEvaluationscenario.getCountry();

        try {
            q = em.createNamedQuery("Evaluationscenario.findByInspireidLocalid");
            q.setParameter("inspireidLocalid", evaluationscenarioINSPIRELocalID);
            List<Evaluationscenario> evaluationscenarioList = q.getResultList();

            Evaluationscenario evaluationscenarioExistingInThatCountry = null;
            for (Evaluationscenario evaluationscenario : evaluationscenarioList) {
                if (evaluationscenario.getUsers().getCountry().equals(country)) {
                    evaluationscenarioExistingInThatCountry = evaluationscenario;
                    break;
                }
            }

            if (evaluationscenarioExistingInThatCountry != null) {
                return EvaluationscenarioWrapper.convertEvaluationscenarioInEvaluationscenarioBean(evaluationscenarioExistingInThatCountry, userEvaluationscenario);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Return PublicationBean by publication ID
     *
     * @param publicationID
     * @return PublicationBean
     */
    public PublicationBean getPublicationByID(String publicationID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Publication.findByUuid");
        q.setParameter("uuid", publicationID);
        Publication publication = (Publication) q.getSingleResult();
        em.close();
        return Publicationwrapper.convertPublicationInPublicationBean(publication);
    }

    /**
     * save the PublicationBean to the given Plan
     *
     * @param evaluationscenarioID
     * @param publicationBean
     * @throws java.lang.Exception
     */
    public void savePublicationToEvaluationscenario(String evaluationscenarioID, PublicationBean publicationBean) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Evaluationscenario.findByUuid");
        q.setParameter("uuid", evaluationscenarioID);
        Evaluationscenario evaluationscenario = (Evaluationscenario) q.getSingleResult();

        Publication publication;

        if (publicationBean.getUuid() == null) {
            String publicationUuid = StringUtils.createUUID(evaluationscenarioID + dateFormatUtil.getToday(), Publication.class);
            publication = new Publication();

            publication.setUuid(publicationUuid);
        } else {
            Query qp = em.createNamedQuery("Publication.findByUuid");
            qp.setParameter("uuid", publicationBean.getUuid());
            publication = ((List<Publication>) qp.getResultList()).get(0);
        }

        if (publicationBean.getTitle() != null) {
            publication.setTitle(publicationBean.getTitle());
        } else {
            publication.setTitle("");
        }

        if (publicationBean.getDescription() != null) {
            publication.setDescription(publicationBean.getDescription());
        } else {
            publication.setDescription("");
        }
        if (publicationBean.getAuthor() != null) {
            publication.setAuthor(publicationBean.getAuthor());
        } else {
            publication.setAuthor("");
        }
        if (publicationBean.getPublicationdateId() != null) {
            publication.setPublicationdateId(publicationBean.getPublicationdateId());
        } else {
            publication.setPublicationdateId("");
        }
        if (publicationBean.getPublicationdateTimeposition() != null) {
            publication.setPublicationdateTimeposition(publicationBean.getPublicationdateTimeposition());
        } else {
            publication.setPublicationdateTimeposition("");
        }
        if (publicationBean.getPublisher() != null) {
            publication.setPublisher(publicationBean.getPublisher());
        } else {
            publication.setPublisher("");
        }
        if (publicationBean.getWeblink() != null) {
            publication.setWeblink(publicationBean.getWeblink());
        } else {
            publication.setWeblink("");
        }

        emc.beginTransaction(em);
        if (publicationBean.getUuid() == null) {
            em.persist(publication);

            EvaluationscenarioPublication evaluationscenarioPublication = new EvaluationscenarioPublication();
            String planPublicationUuid = StringUtils.createUUID(evaluationscenario.getUuid() + publication.getUuid() + dateFormatUtil.getToday(), EvaluationscenarioPublication.class);
            evaluationscenarioPublication.setUuid(planPublicationUuid);

            evaluationscenarioPublication.setEvaluationscenario(evaluationscenario);

            evaluationscenarioPublication.setPublication(publication);

            em.persist(evaluationscenarioPublication);
        } else {
            em.merge(publication);
        }
        emc.commitAndCloseTransaction(em);
    }

    /**
     * Change the status of the Plan
     *
     * @param evaluationScenarioID
     * @param completed
     */
    public void setCompletnessByEvaluationScenarioID(String evaluationScenarioID, boolean completed) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Evaluationscenario.findByUuid");
        q.setParameter("uuid", evaluationScenarioID);
        Evaluationscenario evaluationScenario = (Evaluationscenario) q.getSingleResult();

        emc.beginTransaction(em);
        if (completed) {
            evaluationScenario.setCompleted(Boolean.TRUE);
        } else {
            evaluationScenario.setCompleted(Boolean.FALSE);
        }

        em.merge(evaluationScenario);

        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     */
    private Scenario cloneScenario(Scenario scenario) throws Exception {
        Scenario cloneScenario = new Scenario();

        String cloneBaselinescenarioUuid = StringUtils.createUUID(scenario.getUuid() + dateFormatUtil.getToday(), Scenario.class);
        cloneScenario.setUuid(cloneBaselinescenarioUuid);

        cloneScenario.setInspireidLocalid(scenario.getInspireidLocalid());
        cloneScenario.setInspireidNamespace(scenario.getInspireidNamespace());
        cloneScenario.setInspireidVersionid(scenario.getInspireidVersionid());

        cloneScenario.setDescription(scenario.getDescription());

        cloneScenario.setExpectedexceedence(scenario.getExpectedexceedence());
        cloneScenario.setComment(scenario.getComment());

        cloneScenario.setTotalemissions(scenario.getTotalemissions());
        cloneScenario.setExpectedconcentration(scenario.getExpectedconcentration());

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        em.persist(cloneScenario);

        Query q = em.createNamedQuery("MeasuresScenario.findByScenario");

        q.setParameter(
                "scenario", scenario);
        List<MeasuresScenario> measuresScenarioList = q.getResultList();
        List<String> measureIDList = new ArrayList<String>();
        for (MeasuresScenario measuresScenario : measuresScenarioList) {
            measureIDList.add(measuresScenario.getMeasures().getUuid());
        }

        saveMeasuresLinkForScenarioID(cloneBaselinescenarioUuid, measureIDList, em);

        emc.commitAndCloseTransaction(em);
        return cloneScenario;
    }

    /**
     *
     * @param scenarioID
     * @param measuresListID
     */
    private void saveMeasuresLinkForScenarioID(String scenarioID, List<String> measuresListID, EntityManager em) throws Exception {
        Query q = em.createNamedQuery("Scenario.findByUuid");
        q.setParameter("uuid", scenarioID);
        Scenario scenario = (Scenario) q.getSingleResult();

        for (String measuresID : measuresListID) {
            q = em.createNamedQuery("Measures.findByUuid");
            q.setParameter("uuid", measuresID);

            Measures measures = (Measures) q.getSingleResult();

            MeasuresScenario measuresScenario = new MeasuresScenario();
            String measuresScenarioUuid = StringUtils.createUUID(measuresID + scenarioID, MeasuresScenario.class);
            measuresScenario.setUuid(measuresScenarioUuid);

            measuresScenario.setMeasures(measures);

            measuresScenario.setScenario(scenario);

            em.persist(measuresScenario);
        }
    }

    /**
     *
     * @param userEmail
     * @return a list of all the completed Evaluationscenario for the user's
     * country
     */
    public List<EvaluationscenarioBean> getAllCompletedEvaluationscenarioByUser(String userEmail, boolean completed, Date fromDate, Date toDate) {
        List<EvaluationscenarioBean> evaluationscenarioBeanList = new ArrayList<EvaluationscenarioBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userAttainment = (Users) q.getSingleResult();
        Country country = userAttainment.getCountry();

        q = em.createNamedQuery("Users.findByCountry");
        q.setParameter("country", country);

        List<Users> userList = q.getResultList();

        for (Iterator<Users> it = userList.iterator(); it.hasNext();) {
            Users user = it.next();
            q = em.createNamedQuery("Evaluationscenario.findAllByUserInInterval");
            q.setParameter("users", user);
            q.setParameter("fromDate", new java.sql.Date(fromDate.getTime()));
            q.setParameter("toDate", new java.sql.Date(toDate.getTime()));
            List<Evaluationscenario> evaluationscenarioList = (List<Evaluationscenario>) q.getResultList();

            for (Evaluationscenario evaluationscenario : evaluationscenarioList) {
                if (completed && evaluationscenario.getCompleted()) {
                    evaluationscenarioBeanList.add(EvaluationscenarioWrapper.convertEvaluationscenarioInEvaluationscenarioBean(evaluationscenario, user));
                } else if (!completed) {
                    evaluationscenarioBeanList.add(EvaluationscenarioWrapper.convertEvaluationscenarioInEvaluationscenarioBean(evaluationscenario, user));
                }
            }
        }

        em.close();
        return evaluationscenarioBeanList;
    }
}
