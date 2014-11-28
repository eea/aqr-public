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

import eu.europa.ec.plan.protectiontarget.ProtectiontargetBean;
import eu.europa.ec.plan.protectiontarget.ProtectiontargetWrapper;
import eu.europa.ec.plan.pollutant.PollutantWrapper;
import eu.europa.ec.plan.pollutant.PollutantBean;
import eu.europa.ec.common.publication.PublicationBean;
import eu.europa.ec.common.publication.Publicationwrapper;
import eu.europa.ec.common.relatedparty.RelatedpartyBean;
import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.aqrcrosstablesmodel.AttainmentPlan;
import eu.europa.ec.aqrcrosstablesmodel.PlanPollutantProtectiontarget;
import eu.europa.ec.aqrcrosstablesmodel.PlanPublication;
import eu.europa.ec.aqrcrosstablesmodel.PollutantProtectiontarget;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.attainment.AttainmentWrapper;
import eu.europa.ec.aqrmodel.Attainment;
import eu.europa.ec.aqrmodel.Plan;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.aqrmodel.Pollutant;
import eu.europa.ec.aqrmodel.Protectiontarget;
import eu.europa.ec.aqrmodel.Publication;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodel.Statusplan;
import eu.europa.ec.aqrmodeluser.Country;
import eu.europa.ec.aqrmodeluser.Systemconfiguration;
import eu.europa.ec.aqrmodeluser.Userrole;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.EntityManagerCustom;
import eu.europa.ec.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PlanManager {

    private final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public PlanManager() {
    }

    /**
     * create PLAN DRAFT for the specific user param: User user
     *
     * @param userEmail
     * @return Plan ID
     * @throws eu.europa.ec.aqrexception.CreatePlanException
     */
    public PlanBean createPlanDraftForUser(String userEmail) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        Plan plan = new Plan();
        String planUuid = StringUtils.createUUID(user.getEmail() + dateFormatUtil.getToday(), PlanManager.class);
        plan.setUuid(planUuid);

        plan.setInspireidLocalid("Draft_" + dateFormatUtil.getToday());
        plan.setInspireidNamespace(systemconfiguration.getNamespace());
        plan.setInspireidVersionid(dateFormatUtil.getToday());

        plan.setCode("");
        plan.setName("");

        RelatedpartyWrapper relatedpartyWrapper = new RelatedpartyWrapper();

        Relatedparty provider = relatedpartyWrapper.createDraftProviderFromUser(planUuid, user);
        Relatedparty relatedparty = relatedpartyWrapper.createDraftProviderFromUser(planUuid + new Date().toString(), user);

        plan.setUsers(user);
        plan.setProvider(provider);
        plan.setCompetentauthority(relatedparty);
        /**
         * commit the related party
         */
        emc.beginTransaction(em);
        em.persist(provider);
        em.persist(relatedparty);

        plan.setFirstexceedanceyearId("");
        plan.setFirstexceedanceyearTimeposition("");

        plan.setTimetable("");
        plan.setReferenceaqplan("");
        plan.setReferenceimplementation("");

        plan.setStatusplan(null);

        plan.setCompleted(false);

        plan.setChanges(true);
        plan.setDescriptionofchanges("");
        plan.setReportingstartdate("");
        plan.setReportingenddate("");

        plan.setDatecreation(new Date());
        plan.setDatelastupdate(new Date());

        em.persist(plan);
        emc.commitAndCloseTransaction(em);

        return PlanWrapper.convertPlanInPlanBean(plan, user);
    }

    /**
     * return a list of PlanBean
     *
     * @param userEmail
     * @return List of PlanBean
     */
    public List<PlanBean> getAllPlansByUser(String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userPlan = (Users) q.getSingleResult();
        Country country = userPlan.getCountry();
        Userrole userrole = userPlan.getUserrole();

        List<PlanBean> planBeanList = new ArrayList<PlanBean>();

        if (!userrole.getUuid().equals("0")) {
            q = em.createNamedQuery("Users.findByCountry");
            q.setParameter("country", country);

            List<Users> userList = q.getResultList();
            for (Users users : userList) {
                q = em.createNamedQuery("Plan.findAllByUser");
                q.setParameter("users", users);

                List<Plan> results = (List<Plan>) q.getResultList();
                for (Plan plan : results) {
                    planBeanList.add(PlanWrapper.convertPlanInPlanBean(plan, userPlan));
                }
            }
        } else {
            q = em.createNamedQuery("Plan.findAll");

            List<Plan> results = (List<Plan>) q.getResultList();
            for (Plan plan : results) {
                planBeanList.add(PlanWrapper.convertPlanInPlanBean(plan, userPlan));
            }
        }

        em.close();
        return planBeanList;
    }

    /**
     ** return the PlanBean with a specific ID
     *
     * @param planID
     * @param userEmail
     * @return PlanBean
     */
    public PlanBean getPlanByID(String planID, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userPlan = (Users) q.getSingleResult();

        q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = (Plan) q.getSingleResult();
        em.close();
        return PlanWrapper.convertPlanInPlanBean(plan, userPlan);
    }

    /**
     *
     * @param planINSPIRELocalID
     * @param userEmail
     * @return the PlanBean
     */
    public PlanBean getPlanByINSPIRELocalID(String planINSPIRELocalID, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userPlan = (Users) q.getSingleResult();
        Country country = userPlan.getCountry();

        try {
            q = em.createNamedQuery("Plan.findByInspireidLocalid");
            q.setParameter("inspireidLocalid", planINSPIRELocalID);
            List<Plan> planList = q.getResultList();

            Plan planExistingInThatCountry = null;
            for (Plan plan : planList) {
                if (plan.getUsers().getCountry().equals(country)) {
                    planExistingInThatCountry = plan;
                    break;
                }
            }

            if (planExistingInThatCountry != null) {
                return PlanWrapper.convertPlanInPlanBean(planExistingInThatCountry, userPlan);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Delete the Plan with a specific ID param: ID of the plan; return: void
     *
     * @param planID
     * @throws eu.europa.ec.aqrexception.DeletePlanException
     */
    public void deletePlanByID(String planID) throws DeletePlanException {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();
        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = ((List<Plan>) q.getResultList()).get(0);

        q = em.createNamedQuery("PlanPublication.findByPlan");
        q.setParameter("plan", plan);
        List<PlanPublication> publicationList = (List<PlanPublication>) q.getResultList();
        for (PlanPublication planPublication : publicationList) {
            q = em.createNamedQuery("PlanPublication.deleteByPlanAndPublication");
            q.setParameter("plan", planPublication.getPlan());
            q.setParameter("publication", planPublication.getPublication());
            q.executeUpdate();

            q = em.createNamedQuery("Publication.deleteByUuid");
            q.setParameter("uuid", planPublication.getPublication().getUuid());
            q.executeUpdate();
        }

        q = em.createNamedQuery("AttainmentPlan.deleteByPlan");
        q.setParameter("plan", plan);
        q.executeUpdate();

        q = em.createNamedQuery("PlanPollutantProtectiontarget.deleteByPlan");
        q.setParameter("plan", plan);
        q.executeUpdate();

        Relatedparty provider = plan.getProvider();
        Relatedparty competentauthority = plan.getCompetentauthority();

        q = em.createNamedQuery("Plan.deleteByUuid");
        q.setParameter("uuid", planID);
        try {
            q.executeUpdate();
        } catch (Exception ex) {
            throw new DeletePlanException();
        }

        q = em.createNamedQuery("Relatedparty.deleteByUuid");
        q.setParameter("uuid", competentauthority.getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Relatedparty.deleteByUuid");
        q.setParameter("uuid", provider.getUuid());
        q.executeUpdate();

        emc.commitAndCloseTransaction(em);

    }

    /**
     * clone a plan for a specific user
     *
     * @param planID
     * @param userEmail
     * @return the Id of the new cloned plan
     * @throws java.lang.Exception
     */
    public String clonePlanByPlanIDAndUser(String planID, String userEmail) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userWhichClone = (Users) q.getSingleResult();

        q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan planToBeCloned = ((List<Plan>) q.getResultList()).get(0);

        /**
         * clone plan and relatedparty
         */
        Plan clonePlan = clonePlan(planToBeCloned, userWhichClone);

        emc.beginTransaction(em);
        /**
         * clone publication and PlanPublication
         */
        q = em.createNamedQuery("PlanPublication.findByPlan");
        q.setParameter("plan", planToBeCloned);

        List<PlanPublication> planpublicationList = (List<PlanPublication>) q.getResultList();
        for (PlanPublication planPublication : planpublicationList) {
            Publication clonePublication = clonePublicationToPlan(clonePlan.getUuid(), planPublication.getPublication(), em);

            PlanPublication clonePlanPublication = new PlanPublication();
            String clonePlanPublicationUuid = StringUtils.createUUID(clonePlan.getUuid() + clonePublication.getUuid(), PlanPublication.class);
            clonePlanPublication.setUuid(clonePlanPublicationUuid);
            clonePlanPublication.setPlan(clonePlan);
            clonePlanPublication.setPublication(clonePublication);
            em.persist(clonePlanPublication);
        }

        /**
         * clone plan pollutant protectiontarget
         */
        q = em.createNamedQuery("PlanPollutantProtectiontarget.findByPlan");

        q.setParameter("plan", planToBeCloned);
        List<PlanPollutantProtectiontarget> planPollutantProtectiontargetList = q.getResultList();
        for (PlanPollutantProtectiontarget planPollutantProtectiontarget : planPollutantProtectiontargetList) {
            PlanPollutantProtectiontarget clonePlanPollutantProtectiontarget = new PlanPollutantProtectiontarget();

            String clonePlanPollutantProtectiontargetUuid = StringUtils.createUUID(clonePlan.getUuid() + planPollutantProtectiontarget.getPollutant().getUuid() + planPollutantProtectiontarget.getProtectiontarget().getUuid() + dateFormatUtil.getToday(), PlanPollutantProtectiontarget.class);
            clonePlanPollutantProtectiontarget.setUuid(clonePlanPollutantProtectiontargetUuid);
            clonePlanPollutantProtectiontarget.setPlan(clonePlan);
            clonePlanPollutantProtectiontarget.setPollutant(planPollutantProtectiontarget.getPollutant());
            clonePlanPollutantProtectiontarget.setProtectiontarget(planPollutantProtectiontarget.getProtectiontarget());

            em.persist(clonePlanPollutantProtectiontarget);
        }

        /**
         * clone plan attainment
         */
        q = em.createNamedQuery("AttainmentPlan.findByPlan");
        q.setParameter("plan", planToBeCloned);
        List<AttainmentPlan> attainmentPlanList = q.getResultList();
        for (AttainmentPlan attainmentPlan : attainmentPlanList) {
            AttainmentPlan cloneAttainmentPlan = new AttainmentPlan();

            String cloneAttainmentPlanUuid = StringUtils.createUUID(clonePlan.getUuid() + attainmentPlan.getAttainment().getUuid() + dateFormatUtil.getToday(), AttainmentPlan.class);
            cloneAttainmentPlan.setUuid(cloneAttainmentPlanUuid);
            cloneAttainmentPlan.setPlan(clonePlan);
            cloneAttainmentPlan.setAttainment(attainmentPlan.getAttainment());

            em.persist(cloneAttainmentPlan);
        }

        emc.commitAndCloseTransaction(em);

        return clonePlan.getUuid();

    }

    /**
     *
     * @param plan
     * @param userWhichClone
     * @param user
     * @return the clone plan
     * @throws java.lang.Exception
     */
    private Plan clonePlan(Plan plan, Users userWhichClone) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Country country = userWhichClone.getCountry();
        Query q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        Plan clonePlan = new Plan();
        String clonePlanUuid = StringUtils.createUUID(userWhichClone.getEmail() + new Date().getTime(), PlanManager.class);
        clonePlan.setUuid(clonePlanUuid);

        clonePlan.setInspireidLocalid("Clone_" + dateFormatUtil.getToday());
        clonePlan.setInspireidNamespace(systemconfiguration.getNamespace());
        clonePlan.setInspireidVersionid(dateFormatUtil.getToday());

        clonePlan.setCode(plan.getCode());
        clonePlan.setName(plan.getName());
        clonePlan.setCompleted(plan.getCompleted());

        clonePlan.setUsers(userWhichClone);

        clonePlan.setFirstexceedanceyearId(plan.getFirstexceedanceyearId());
        clonePlan.setFirstexceedanceyearTimeposition(plan.getFirstexceedanceyearTimeposition());

        if (plan.getStatusplan()
                != null) {
            String link = plan.getStatusplan().getLink();
            q = em.createNamedQuery("Statusplan.findByLink");
            q.setParameter("link", link);
            Statusplan statusplan = ((List<Statusplan>) q.getResultList()).get(0);
            clonePlan.setStatusplan(statusplan);
        } else {
            clonePlan.setStatusplan(null);
        }

        clonePlan.setAdoptiondateId(plan.getAdoptiondateId());
        clonePlan.setAdoptiondateTimeposition(plan.getAdoptiondateTimeposition());

        clonePlan.setTimetable(plan.getTimetable());
        clonePlan.setReferenceaqplan(plan.getReferenceaqplan());
        clonePlan.setReferenceimplementation(plan.getReferenceimplementation());

        clonePlan.setComment(plan.getComment());

        clonePlan.setChanges(plan.isChanges());
        clonePlan.setDescriptionofchanges(plan.getDescriptionofchanges());
        clonePlan.setReportingstartdate(plan.getReportingstartdate());
        clonePlan.setReportingenddate(plan.getReportingenddate());

        clonePlan.setDatecreation(new Date());
        clonePlan.setDatelastupdate(new Date());

        Relatedparty cloneRelatedparty = new Relatedparty();
        String relatedPartyUuid = StringUtils.createUUID(clonePlanUuid + dateFormatUtil.getToday(), Relatedparty.class);

        cloneRelatedparty.setUuid(relatedPartyUuid);
        Relatedparty competentauthority = plan.getCompetentauthority();

        cloneRelatedparty.setIndividualname(competentauthority.getIndividualname());
        cloneRelatedparty.setOrganisationname(competentauthority.getOrganisationname());
        cloneRelatedparty.setWebsite(competentauthority.getWebsite());
        cloneRelatedparty.setAddress(competentauthority.getAddress());
        cloneRelatedparty.setElectronicmailaddress(competentauthority.getElectronicmailaddress());
        cloneRelatedparty.setTelephonevoice(competentauthority.getTelephonevoice());
        clonePlan.setCompetentauthority(cloneRelatedparty);

        Relatedparty cloneProvider = new Relatedparty();
        String providerUuid = StringUtils.createUUID(clonePlanUuid + userWhichClone.getEmail() + dateFormatUtil.getToday(), Relatedparty.class);

        cloneProvider.setUuid(providerUuid);
        Relatedparty provider = plan.getProvider();

        cloneProvider.setIndividualname(provider.getIndividualname());
        cloneProvider.setOrganisationname(provider.getOrganisationname());
        cloneProvider.setWebsite(provider.getWebsite());
        cloneProvider.setAddress(provider.getAddress());
        cloneProvider.setElectronicmailaddress(provider.getElectronicmailaddress());
        cloneProvider.setTelephonevoice(provider.getTelephonevoice());
        clonePlan.setProvider(cloneProvider);

        /**
         * commit the related party
         */
        emc.beginTransaction(em);
        em.persist(cloneRelatedparty);
        em.persist(cloneProvider);
        em.persist(clonePlan);
        emc.commitAndCloseTransaction(em);

        return clonePlan;
    }

    /**
     *
     * @param clonePlanID
     * @param publication
     * @param em
     * @return the clone Publication
     * @throws java.lang.Exception
     */
    public Publication clonePublicationToPlan(String clonePlanID, Publication publication, EntityManager em) throws Exception {
        Publication clonePublication = new Publication();
        String clonePublicationUuid = StringUtils.createUUID(clonePlanID + dateFormatUtil.getToday(), Publication.class);
        clonePublication.setUuid(clonePublicationUuid);

        clonePublication.setTitle(publication.getTitle());
        clonePublication.setDescription(publication.getDescription());
        clonePublication.setAuthor(publication.getAuthor());
        clonePublication.setPublicationdateId(publication.getPublicationdateId());
        clonePublication.setPublicationdateTimeposition(publication.getPublicationdateTimeposition());
        clonePublication.setPublisher(publication.getPublisher());
        clonePublication.setWeblink(publication.getWeblink());

        em.persist(clonePublication);

        return clonePublication;
    }

    /**
     * save the plan
     *
     * @param planBean
     * @throws
     * eu.europa.ec.aqrexception.PlanINSPIRELocalIDAlreadyExistingException
     * @throws java.lang.Exception
     */
    public void savePlanDraft(PlanBean planBean) throws PlanINSPIRELocalIDAlreadyExistingException, Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planBean.getUuid());
        Plan plan = (Plan) q.getSingleResult();

        String userEmail = plan.getUsers().getEmail();
        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        boolean planINSPIRELocalIDAlreadyExisting = false;

        if (planBean.getInspireidLocalid() != null) {

            if (!plan.getInspireidLocalid().equals(planBean.getInspireidLocalid())) {
                q = em.createNamedQuery("Plan.findByInspireidLocalid");
                q.setParameter("inspireidLocalid", planBean.getInspireidLocalid());

                List<Plan> planList = q.getResultList();
                int k = 0;
                for (Plan existentPlan : planList) {
                    if (!existentPlan.getUsers().getCountry().equals(country)) {
                        k++;
                    }
                }

                if (k == planList.size()) {
                    plan.setInspireidLocalid(planBean.getInspireidLocalid());
                } else {
                    planINSPIRELocalIDAlreadyExisting = true;
                }
            }
        } else {
            plan.setInspireidLocalid("");
        }

        if (planBean.getInspireidNamespace() != null) {
            plan.setInspireidNamespace(planBean.getInspireidNamespace());
        } else {
            try {
                q = em.createNamedQuery("Systemconfiguration.findByCountry");
                q.setParameter("country", country);
                Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();
                plan.setInspireidNamespace(systemconfiguration.getNamespace());
            } catch (Exception e) {
                plan.setInspireidNamespace("");
            }
        }
        plan.setInspireidVersionid(dateFormatUtil.getToday());

        if (planBean.getCode() != null) {
            plan.setCode(planBean.getCode());
        } else {
            plan.setCode("");
        }
        if (planBean.getName() != null) {
            plan.setName(planBean.getName());
        } else {
            plan.setName("");
        }

        q = em.createNamedQuery("Relatedparty.findByUuid");
        q.setParameter("uuid", plan.getCompetentauthority().getUuid());
        Relatedparty relatedparty = ((List<Relatedparty>) q.getResultList()).get(0);

        RelatedpartyBean relatedpartyBean = planBean.getRelatedpartyBean();

        if (relatedpartyBean != null && relatedpartyBean.getIndividualname() != null) {
            relatedparty.setIndividualname(relatedpartyBean.getIndividualname());
        } else {
            relatedparty.setIndividualname("");
        }
        if (relatedpartyBean != null && relatedpartyBean.getOrganisationname() != null) {
            relatedparty.setOrganisationname(relatedpartyBean.getOrganisationname());
        } else {
            relatedparty.setOrganisationname("");
        }
        if (relatedpartyBean != null && relatedpartyBean.getWebsite() != null) {
            relatedparty.setWebsite(relatedpartyBean.getWebsite());
        } else {
            relatedparty.setWebsite("");
        }
        if (relatedpartyBean != null && relatedpartyBean.getAddress() != null) {
            relatedparty.setAddress(relatedpartyBean.getAddress());
        } else {
            relatedparty.setAddress("");
        }
        if (relatedpartyBean != null && relatedpartyBean.getTelephonevoice() != null) {
            relatedparty.setTelephonevoice(relatedpartyBean.getTelephonevoice());
        } else {
            relatedparty.setTelephonevoice("");
        }
        if (relatedpartyBean != null && relatedpartyBean.getElectronicmailaddress() != null) {
            relatedparty.setElectronicmailaddress(relatedpartyBean.getElectronicmailaddress());
        } else {
            relatedparty.setElectronicmailaddress("");
        }

        RelatedpartyWrapper relatedpartyWrapper = new RelatedpartyWrapper();
        Relatedparty provider = relatedpartyWrapper.saveProviderDraft(em, plan.getProvider().getUuid(), planBean.getProviderBean(), plan.getUsers());

        if (planBean.getFirstexceedanceyearId() != null) {
            plan.setFirstexceedanceyearId(planBean.getFirstexceedanceyearId());
        }
        if (planBean.getFirstexceedanceyearTimeposition() != null) {
            plan.setFirstexceedanceyearTimeposition(planBean.getFirstexceedanceyearTimeposition());
        } else {
            plan.setFirstexceedanceyearTimeposition("");
        }

        if (planBean.getAdoptiondateId() != null) {
            plan.setAdoptiondateId(planBean.getAdoptiondateId());
        } else {
            plan.setAdoptiondateId("");
        }
        if (planBean.getAdoptiondateTimeposition() != null) {
            plan.setAdoptiondateTimeposition(planBean.getAdoptiondateTimeposition());
        } else {
            plan.setAdoptiondateTimeposition("");
        }

        if (planBean.getTimetable() != null) {
            plan.setTimetable(planBean.getTimetable());
        } else {
            plan.setTimetable("");
        }
        if (planBean.getReferenceaqplan() != null) {
            plan.setReferenceaqplan(planBean.getReferenceaqplan());
        } else {
            plan.setReferenceaqplan("");
        }
        if (planBean.getReferenceimplementation() != null) {
            plan.setReferenceimplementation(planBean.getReferenceimplementation());
        } else {
            plan.setReferenceimplementation("");
        }

        if (planBean.getComment() != null) {
            plan.setComment(planBean.getComment());
        } else {
            plan.setComment("");
        }

        String link = planBean.getLink();
        if (link != null) {
            q = em.createNamedQuery("Statusplan.findByLink");
            q.setParameter("link", link);

            Statusplan statusplan = ((List<Statusplan>) q.getResultList()).get(0);
            plan.setStatusplan(statusplan);
        } else {
            plan.setStatusplan(null);
        }

        plan.setCompleted(planBean.isCompleted());

        plan.setChanges(planBean.isChanges());
        if (planBean.isChanges()) {
            if (planBean.getDescriptionofchanges() != null) {
                plan.setDescriptionofchanges(planBean.getDescriptionofchanges());
            } else {
                plan.setDescriptionofchanges("");
            }
        }

        if (planBean.getReportingstartdate() != null) {
            plan.setReportingstartdate(planBean.getReportingstartdate());
        } else {
            plan.setReportingstartdate("");
        }
        if (planBean.getReportingenddate() != null) {
            plan.setReportingenddate(planBean.getReportingenddate());
        } else {
            plan.setReportingenddate("");
        }

        plan.setDatelastupdate(new Date());

        emc.beginTransaction(em);

        List<String> attainmentBeanList = planBean.getAttainmentBeanList();
        if (attainmentBeanList != null) {
            /**
             * delete all the other links for that plan
             */
            q = em.createNamedQuery("AttainmentPlan.deleteByPlan");
            q.setParameter("plan", plan);
            q.executeUpdate();

            for (String attainmentUuid : attainmentBeanList) {
                q = em.createNamedQuery("Attainment.findByUuid");
                q.setParameter("uuid", attainmentUuid);

                Attainment attainment = (Attainment) q.getSingleResult();

                AttainmentPlan attainmentPlan = new AttainmentPlan();
                String attainmentPlanUuid = StringUtils.createUUID(attainment.getUuid() + plan.getUuid() + dateFormatUtil.getToday(), AttainmentPlan.class);
                attainmentPlan.setUuid(attainmentPlanUuid);
                attainmentPlan.setPlan(plan);
                attainmentPlan.setAttainment(attainment);
                em.persist(attainmentPlan);
            }
        } else {
            q = em.createNamedQuery("AttainmentPlan.deleteByPlan");
            q.setParameter("plan", plan);
            q.executeUpdate();
        }

        /**
         * commit the related party
         */
        em.merge(relatedparty);
        em.merge(provider);
        em.merge(plan);
        emc.commitAndCloseTransaction(em);

        if (planINSPIRELocalIDAlreadyExisting) {
            throw new PlanINSPIRELocalIDAlreadyExistingException();
        }
    }

    /**
     * return all the statusplan
     *
     * @return List of Statusplan
     * @throws java.lang.Exception
     */
    public List<Statusplan> getAllStatusPlan() throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Statusplan.findAll");
        List<Statusplan> results = q.getResultList();
        em.close();
        return results;
    }

    /**
     * return the statusplan for the specific plan *param: plan ID
     *
     * @param planID
     * @return
     */
    public Statusplan getSelectedStatusplanByPlan(String planID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query qp = em.createNamedQuery("Plan.findByUuid");
        qp.setParameter("uuid", planID);
        Plan plan = (Plan) qp.getSingleResult();

        Query q = em.createNamedQuery("Statusplan.findByPlan");
        q.setParameter("plan", plan);
        Statusplan result = (Statusplan) q.getSingleResult();
        em.close();
        return result;
    }

    /**
     * return all PollutantBean for a specific
     *
     * @param planID
     * @return List of PollutantBean
     */
    public List<PollutantBean> getAllPollutantByPlan(String planID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query qp = em.createNamedQuery("Plan.findByUuid");
        qp.setParameter("uuid", planID);
        Plan plan = (Plan) qp.getSingleResult();

        Query q = em.createNamedQuery("PlanPollutantProtectiontarget.findByPlan");
        q.setParameter("plan", plan);

        List<PollutantBean> pollutantList = new ArrayList<PollutantBean>();

        List<PlanPollutantProtectiontarget> ppptList = q.getResultList();
        HashMap<String, PollutantBean> hash = new HashMap<String, PollutantBean>();
        for (PlanPollutantProtectiontarget planPollutantProtectiontarget : ppptList) {
            Pollutant pollutant = planPollutantProtectiontarget.getPollutant();
            PollutantBean pollutantBean = PollutantWrapper.convertPollutantInPollutantBean(pollutant);

            if (!hash.containsKey(pollutantBean.getUuid())) {
                hash.put(pollutantBean.getUuid(), pollutantBean);
                pollutantList.add(pollutantBean);
            }
        }
        em.close();
        return pollutantList;
    }

    /**
     * Delete a Pollutant for a certain Plan *param: pollutant ID and plan ID
     *
     * @param pollutantID
     * @param planID
     */
    public void deletePollutantByPlan(String pollutantID, String planID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();
        emc.beginTransaction(em);

        Query qp = em.createNamedQuery("Plan.findByUuid");
        qp.setParameter("uuid", planID);
        Plan plan = (Plan) qp.getSingleResult();

        Query qpo = em.createNamedQuery("Pollutant.findByUuid");
        qpo.setParameter("uuid", pollutantID);
        Pollutant pollutant = (Pollutant) qpo.getSingleResult();

        Query q = em.createNamedQuery("PlanPollutantProtectiontarget.deleteByPlanAndPollutant");
        q.setParameter("plan", plan);
        q.setParameter("pollutant", pollutant);

        emc.beginTransaction(em);
        q.executeUpdate();
        emc.commitAndCloseTransaction(em);
    }

    /**
     * @return List of PollutantBean
     */
    public List<PollutantBean> getAllPollutantOrderByID() {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Pollutant.findAllOrderByID");
        List<Pollutant> results = q.getResultList();
        LinkedHashMap<Integer, Pollutant> hashUnorderedPollutant = new LinkedHashMap<Integer, Pollutant>();
        for (Pollutant pollutant : results) {
            String pollutantUuid = pollutant.getUuid();
            hashUnorderedPollutant.put(Integer.parseInt(pollutantUuid), pollutant);
        }

        Map<Integer, Pollutant> orderMap = new TreeMap<Integer, Pollutant>(hashUnorderedPollutant);
        List<PollutantBean> pollutantBeanList = new ArrayList<PollutantBean>();
        for (Map.Entry<Integer, Pollutant> entry : orderMap.entrySet()) {
            Pollutant pollutant = entry.getValue();
            pollutantBeanList.add(PollutantWrapper.convertPollutantInPollutantBean(pollutant));
        }

        em.close();
        return pollutantBeanList;
    }

    /**
     * get the pollutantBean by the ID
     *
     * @param pollutantID
     * @return
     */
    public PollutantBean getPollutantByID(String pollutantID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Pollutant.findByUuid");
        q.setParameter("uuid", pollutantID);
        Pollutant result = (Pollutant) q.getSingleResult();
        em.close();
        return PollutantWrapper.convertPollutantInPollutantBean(result);
    }

    /**
     * get the pollutantBean by the Uri
     *
     * @param pollutantUri
     * @return
     */
    public PollutantBean getPollutantByUri(String pollutantUri) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        try {
            Query q = em.createNamedQuery("Pollutant.findByUri");
            q.setParameter("uri", pollutantUri);
            Pollutant result = (Pollutant) q.getSingleResult();
            em.close();
            return PollutantWrapper.convertPollutantInPollutantBean(result);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * return the list of ProtectiontargetBean for a specific plan ID and
     * pollutant ID
     *
     * @param planID
     * @param pollutantID
     * @return
     */
    public List<ProtectiontargetBean> getAllProtectiontargetForPlanAndPolutant(String planID, String pollutantID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query qp = em.createNamedQuery("Plan.findByUuid");
        qp.setParameter("uuid", planID);
        Plan plan = (Plan) qp.getSingleResult();

        Query qpo = em.createNamedQuery("Pollutant.findByUuid");
        qpo.setParameter("uuid", pollutantID);
        Pollutant pollutant = (Pollutant) qpo.getSingleResult();

        Query q = em.createNamedQuery("PlanPollutantProtectiontarget.findByPlanAndPollutant");
        q.setParameter("plan", plan);
        q.setParameter("pollutant", pollutant);
        List<PlanPollutantProtectiontarget> results = q.getResultList();

        List<ProtectiontargetBean> protectiontargetList = new ArrayList<ProtectiontargetBean>();
        for (PlanPollutantProtectiontarget planPollutantProtectiontarget : results) {
            Query qpt = em.createNamedQuery("Protectiontarget.findByUuid");
            qpt.setParameter("uuid", planPollutantProtectiontarget.getProtectiontarget().getUuid());
            Protectiontarget protectiontarget = (Protectiontarget) qpt.getSingleResult();

            protectiontargetList.add(ProtectiontargetWrapper.convertProtectiontargetInProtectiontargetBean(protectiontarget));
        }
        em.close();
        return protectiontargetList;
    }

    /**
     * save the connection between plan, pollutant and the list of
     * protectiontarget
     *
     * @param planID
     * @param pollutantID
     * @param protectiontargetListID
     * @throws java.lang.Exception
     */
    public void saveProtectiontargetForPolutantAndPlan(String planID, String pollutantID, List<String> protectiontargetListID) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = (Plan) q.getSingleResult();

        q = em.createNamedQuery("Pollutant.findByUuid");
        q.setParameter("uuid", pollutantID);
        Pollutant pollutant = (Pollutant) q.getSingleResult();
        emc.beginTransaction(em);

        deleteExistingPlanPollutant(em, plan, pollutant);

        for (String protectiontargetUuid : protectiontargetListID) {
            q = em.createNamedQuery("Protectiontarget.findByUuid");
            q.setParameter("uuid", protectiontargetUuid);
            Protectiontarget protectiontarget = (Protectiontarget) q.getSingleResult();

            PlanPollutantProtectiontarget planPollutantProtectiontarget = new PlanPollutantProtectiontarget();
            String planPollutantProtectiontargetUuid = StringUtils.createUUID(planID + pollutantID + protectiontarget.getUuid() + dateFormatUtil.getToday(), PlanPollutantProtectiontarget.class);
            planPollutantProtectiontarget.setUuid(planPollutantProtectiontargetUuid);
            planPollutantProtectiontarget.setPlan(plan);
            planPollutantProtectiontarget.setPollutant(pollutant);
            planPollutantProtectiontarget.setProtectiontarget(protectiontarget);

            em.persist(planPollutantProtectiontarget);

        }
        emc.commitAndCloseTransaction(em);
    }

    /**
     * delete the pollutant for a specific plan
     *
     * * @param plan
     * * @param pollutant
     * * @param em
     */
    private void deleteExistingPlanPollutant(EntityManager em, Plan plan, Pollutant pollutant) {
        Query q = em.createNamedQuery("PlanPollutantProtectiontarget.findByPlanAndPollutant");
        q.setParameter("plan", plan);
        q.setParameter("pollutant", pollutant);
        List<PlanPollutantProtectiontarget> resultsPlanPollutantProtectiontarget = q.getResultList();
        if (resultsPlanPollutantProtectiontarget != null && !resultsPlanPollutantProtectiontarget.isEmpty()) {
            q = em.createNamedQuery("PlanPollutantProtectiontarget.deleteByPlanAndPollutant");
            q.setParameter("plan", plan);
            q.setParameter("pollutant", pollutant);

            q.executeUpdate();
        }
    }

    /**
     *
     * @param pollutantID
     * @return the List of ProtectiontargetBean
     */
    public List<ProtectiontargetBean> getAllProtectiontargetByPollutantID(String pollutantID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Pollutant.findByUuid");
        q.setParameter("uuid", pollutantID);
        Pollutant pollutant = (Pollutant) q.getSingleResult();

        q = em.createNamedQuery("PollutantProtectiontarget.findByPollutant");
        q.setParameter("pollutant", pollutant);
        List<PollutantProtectiontarget> results = q.getResultList();

        List<ProtectiontargetBean> protectiontargetBeanList = new ArrayList<ProtectiontargetBean>();
        if (results != null && results.size() > 0) {
            for (PollutantProtectiontarget pollutantProtectiontarget : results) {
                protectiontargetBeanList.add(ProtectiontargetWrapper.convertProtectiontargetInProtectiontargetBean(pollutantProtectiontarget.getProtectiontarget()));
            }
        } else {
            protectiontargetBeanList = getAllProtectiontarget();
        }
        em.close();
        return protectiontargetBeanList;
    }

    /**
     *
     * @param protectiontargetUri
     * @return ProtectiontargetBean by protectiontargetUri
     */
    public ProtectiontargetBean getProtectiontargetBeanByProtectionTargetUri(String protectiontargetUri) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        try {
            Query q = em.createNamedQuery("Protectiontarget.findByUri");
            q.setParameter("uri", protectiontargetUri);
            Protectiontarget protectiontarget = (Protectiontarget) q.getSingleResult();

            return ProtectiontargetWrapper.convertProtectiontargetInProtectiontargetBean(protectiontarget);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<ProtectiontargetBean> getAllProtectiontarget() {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        List<ProtectiontargetBean> protectiontargetBeanList = new ArrayList<ProtectiontargetBean>();
        Query q = em.createNamedQuery("Protectiontarget.findAll");
        List<Protectiontarget> results = q.getResultList();
        for (Protectiontarget protectiontarget : results) {
            protectiontargetBeanList.add(ProtectiontargetWrapper.convertProtectiontargetInProtectiontargetBean(protectiontarget));
        }
        em.close();
        return protectiontargetBeanList;
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

        q = em.createNamedQuery("PlanPublication.deleteByPublication");
        q.setParameter("publication", publication);

        emc.beginTransaction(em);
        q.executeUpdate();

        q = em.createNamedQuery("Publication.deleteByUuid");
        q.setParameter("uuid", publicationID);
        q.executeUpdate();

        emc.commitAndCloseTransaction(em);
    }

    /**
     * save the PublicationBean to the given Plan
     *
     * @param planID
     * @param publicationBean
     * @throws java.lang.Exception
     */
    public void savePublicationToPlan(String planID, PublicationBean publicationBean) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = ((List<Plan>) q.getResultList()).get(0);

        Publication publication;

        if (publicationBean.getUuid() == null) {
            String publicationUuid = StringUtils.createUUID(planID + dateFormatUtil.getToday(), Publication.class);
            publication = new Publication();

            publication.setUuid(publicationUuid);
        } else {
            q = em.createNamedQuery("Publication.findByUuid");
            q.setParameter("uuid", publicationBean.getUuid());
            publication = (Publication) q.getSingleResult();
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

            PlanPublication planPublication = new PlanPublication();
            String planPublicationUuid = StringUtils.createUUID(planID + publication.getUuid() + dateFormatUtil.getToday(), PlanPublication.class);
            planPublication.setUuid(planPublicationUuid);
            planPublication.setPlan(plan);
            planPublication.setPublication(publication);
            em.persist(planPublication);
        } else {
            em.merge(publication);
        }
        emc.commitAndCloseTransaction(em);

    }

    /**
     *
     * @param planID
     * @return a list of PublicationBean by Plan ID
     */
    public List<PublicationBean> getAllPublicationByPlan(String planID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = (Plan) q.getSingleResult();

        q = em.createNamedQuery("PlanPublication.findByPlan");
        q.setParameter("plan", plan);
        List<PlanPublication> results = q.getResultList();

        List<PublicationBean> publicationList = new ArrayList<PublicationBean>();
        for (PlanPublication planPublication : results) {
            publicationList.add(Publicationwrapper.convertPublicationInPublicationBean(planPublication.getPublication()));
        }
        em.close();
        return publicationList;
    }

    /**
     * Delete the planPollutantProtectiontarget by ID
     *
     * @param planID
     * @param pollutantID
     */
    public void deletePollutant(String planID, String pollutantID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = (Plan) q.getSingleResult();

        q = em.createNamedQuery("Pollutant.findByUuid");
        q.setParameter("uuid", pollutantID);
        Pollutant pollutant = (Pollutant) q.getSingleResult();

        q = em.createNamedQuery("PlanPollutantProtectiontarget.deleteByPlanAndPollutant");
        q.setParameter("plan", plan);
        q.setParameter("pollutant", pollutant);

        emc.beginTransaction(em);
        q.executeUpdate();
        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param publicationID
     * @return the ID of the Plan given the ID of the publication
     */
    public String getPlanIDByPublicationID(String publicationID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Publication.findByUuid");
        q.setParameter("uuid", publicationID);
        Publication publication = (Publication) q.getSingleResult();

        q = em.createNamedQuery("PlanPublication.findByPublication");
        q.setParameter("publication", publication);

        PlanPublication planPublication = (PlanPublication) q.getSingleResult();
        String planPublicationUuuid = planPublication.getPlan().getUuid();
        em.close();
        return planPublicationUuuid;
    }

    /**
     *
     * @param planPollutantProtectiontargetID
     * @return the ID of the plan given the planPollutantProtectiontargetID
     */
    public String getPlanIDByPlanPollutantProtectiontargetID(String planPollutantProtectiontargetID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("PlanPollutantProtectiontarget.findByUuid");
        q.setParameter("uuid", planPollutantProtectiontargetID);

        PlanPollutantProtectiontarget planPollutantProtectiontarget = (PlanPollutantProtectiontarget) q.getSingleResult();
        String planPollutantProtectiontargetUuid = planPollutantProtectiontarget.getPlan().getUuid();
        em.close();
        return planPollutantProtectiontargetUuid;
    }

    /**
     * Change the status of the Plan
     *
     * @param planID
     * @param completed
     */
    public void setCompletnessByPlanID(String planID, boolean completed) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = (Plan) q.getSingleResult();

        emc.beginTransaction(em);
        if (completed) {
            plan.setCompleted(Boolean.TRUE);
        } else {
            plan.setCompleted(Boolean.FALSE);
        }

        em.merge(plan);

        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param plan
     * @return a list of all the uploaded AttainmentBean
     */
    public List<String> getAttainmentByPlanID(Plan plan) {
        List<String> attainmentBeanList = new ArrayList<String>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("AttainmentPlan.findByPlan");
        q.setParameter("plan", plan);
        List<AttainmentPlan> attainmentPlanList = q.getResultList();

        for (AttainmentPlan attainmentPlan : attainmentPlanList) {
            attainmentBeanList.add(attainmentPlan.getAttainment().getUuid());
        }

        em.close();
        return attainmentBeanList;
    }

    /**
     *
     * @param userEmail
     * @return a list of all the uploaded AttainmentBean
     */
    public List<String> getAllAttainmentByUser(String userEmail) {
        List<String> attainmentBeanList = new ArrayList<String>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userAttainment = (Users) q.getSingleResult();
        Country country = userAttainment.getCountry();

        q = em.createNamedQuery("Users.findByCountry");
        q.setParameter("country", country);

        List<Users> userList = q.getResultList();

        for (Users users : userList) {
            q = em.createNamedQuery("Attainment.findAllByUser");
            q.setParameter("users", users);

            List<Attainment> attainmentList = (List<Attainment>) q.getResultList();
            for (Attainment attainment : attainmentList) {
                attainmentBeanList.add(attainment.getUuid());
            }
        }

        em.close();
        return attainmentBeanList;
    }

    /**
     *
     * @param planID
     * @return a list of all the uploaded AttainmentBean
     */
    public List<AttainmentBean> getAllAttainmentBeanByUser(String planID) {
        List<AttainmentBean> attainmentBeanList = new ArrayList<AttainmentBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = (Plan) q.getSingleResult();

        String userEmail = plan.getUsers().getEmail();
        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        q = em.createNamedQuery("Users.findByCountry");
        q.setParameter("country", country);

        List<Users> userList = q.getResultList();

        for (Users users : userList) {
            q = em.createNamedQuery("Attainment.findAllByUser");
            q.setParameter("users", users);

            List<Attainment> attainmentList = (List<Attainment>) q.getResultList();
            for (Attainment attainment : attainmentList) {
                attainmentBeanList.add(AttainmentWrapper.convertAttainmentInAttainmentBean(attainment));
            }
        }

        em.close();
        return attainmentBeanList;
    }

    /**
     *
     * @param userEmail
     * @return a list of all the completed plans for the user's country
     */
    public List<PlanBean> getAllCompletedPlansByUser(String userEmail, boolean completed, Date fromDate, Date toDate) {
        List<PlanBean> planBeanList = new ArrayList<PlanBean>();

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
            q = em.createNamedQuery("Plan.findAllByUserInInterval");
            q.setParameter("users", user);
            q.setParameter("fromDate", new java.sql.Date(fromDate.getTime()));
            q.setParameter("toDate", new java.sql.Date(toDate.getTime()));
            List<Plan> planList = (List<Plan>) q.getResultList();

            for (Plan plan : planList) {
                if (completed && plan.getCompleted()) {
                    planBeanList.add(PlanWrapper.convertPlanInPlanBean(plan, user));
                } else if (!completed) {
                    planBeanList.add(PlanWrapper.convertPlanInPlanBean(plan, user));
                }
            }
        }

        em.close();
        return planBeanList;
    }
    
    public List<PlanBean> getAllCompletedPlansByUser(String userEmail, boolean completed) {
        List<PlanBean> planBeanList = new ArrayList<PlanBean>();

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
            q = em.createNamedQuery("Plan.findAllByUser");
            q.setParameter("users", user);
            List<Plan> planList = (List<Plan>) q.getResultList();

            for (Plan plan : planList) {
//                if (completed && plan.getCompleted()) {
//                    planBeanList.add(PlanWrapper.convertPlanInPlanBean(plan, user));
//                } else if (!completed) {
                    planBeanList.add(PlanWrapper.convertPlanInPlanBean(plan, user));
//                }
            }
        }

        em.close();
        return planBeanList;
    }
}
