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

import eu.europa.ec.sourceapprotionment.areaclassification.AreaclassificationBean;
import eu.europa.ec.sourceapprotionment.adjustmenttype.AdjustmenttypeBean;
import eu.europa.ec.sourceapprotionment.adjustmentsource.AdjustmentsourceBean;
import eu.europa.ec.sourceapprotionment.assesmentmethods.AssesmentmethodsWrapper;
import eu.europa.ec.sourceapprotionment.assesmentmethods.AssesmentmethodsBean;
import eu.europa.ec.sourceapprotionment.assesmenttype.AssesmenttypeWrapper;
import eu.europa.ec.sourceapprotionment.assesmenttype.AssesmenttypeBean;
import eu.europa.ec.sourceapprotionment.localincrement.LocalincrementBean;
import eu.europa.ec.sourceapprotionment.localincrement.LocalincrementWrapper;
import eu.europa.ec.sourceapprotionment.reasonvalue.ReasonvalueBean;
import eu.europa.ec.sourceapprotionment.regionalbackground.RegionalbackgroundBean;
import eu.europa.ec.sourceapprotionment.regionalbackground.RegionalbackgroundWrapper;
import eu.europa.ec.sourceapprotionment.urbanbackground.UrbanbackgroundWrapper;
import eu.europa.ec.sourceapprotionment.urbanbackground.UrbanbackgroundBean;
import eu.europa.ec.plan.PlanManager;
import eu.europa.ec.sourceapprotionment.exceedancedescription.deductionassessmentmethod.DeductionassessmentmethodBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedancearea.ExceedanceareaBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedanceexposure.ExceedanceexposureBean;
import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.aqrcrosstablesmodel.DeductionassessmentmethodAdjustmentsource;
import eu.europa.ec.aqrcrosstablesmodel.DeductionassessmentmethodAssesmentmethods;
import eu.europa.ec.aqrcrosstablesmodel.ExceedancedescriptionReasonvalue;
import eu.europa.ec.aqrcrosstablesmodel.ExceedenceareaAreaclassification;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.attainment.AttainmentWrapper;
import eu.europa.ec.sourceapprotionment.exceedancedescription.ExceedancedescriptionBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.ExceedancedescriptionWrapper;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.aqrmodel.Adjustmentsource;
import eu.europa.ec.aqrmodel.Adjustmenttype;
import eu.europa.ec.aqrmodel.Areaclassification;
import eu.europa.ec.aqrmodel.Assesmentmethods;
import eu.europa.ec.aqrmodel.Assesmenttype;
import eu.europa.ec.aqrmodel.Attainment;
import eu.europa.ec.aqrmodel.Deductionassessmentmethod;
import eu.europa.ec.aqrmodel.Exceedancearea;
import eu.europa.ec.aqrmodel.Exceedancedescription;
import eu.europa.ec.aqrmodel.Exceedanceexposure;
import eu.europa.ec.aqrmodel.Localincrement;
import eu.europa.ec.aqrmodel.Plan;
import eu.europa.ec.aqrmodel.Reasonvalue;
import eu.europa.ec.aqrmodel.Regionalbackground;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodel.Sourceapportionment;
import eu.europa.ec.aqrmodel.Urbanbackground;
import eu.europa.ec.aqrmodeluser.Country;
import eu.europa.ec.aqrmodeluser.Systemconfiguration;
import eu.europa.ec.aqrmodeluser.Userrole;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.sourceapprotionment.adjustmentsource.AdjustmentsourceWrapper;
import eu.europa.ec.sourceapprotionment.adjustmenttype.AdjustmenttypeWrapper;
import eu.europa.ec.sourceapprotionment.areaclassification.AreaclassificationWrapper;
import eu.europa.ec.sourceapprotionment.reasonvalue.ReasonvalueWrapper;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.EntityManagerCustom;
import eu.europa.ec.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SourceapportionmentManager {

    private final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public SourceapportionmentManager() {
    }

    /**
     * create Sourceapportionment DRAFT for the specific user param: User user
     *
     * @param userEmail
     * @return Sourceapportionment ID
     * @throws eu.europa.ec.aqrexception.CreatePlanException
     */
    public SourceapportionmentBean createSourceapportionmentDraftForUser(String userEmail) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Users.findByEmail");
        q.setParameter("email", userEmail);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        Sourceapportionment sourceapportionment = new Sourceapportionment();
        String sourceapportionmentUuid = StringUtils.createUUID(user.getEmail() + dateFormatUtil.getToday(), PlanManager.class);
        sourceapportionment.setUuid(sourceapportionmentUuid);

        sourceapportionment.setInspireidLocalid("Draft_" + dateFormatUtil.getToday());
        sourceapportionment.setInspireidNamespace(systemconfiguration.getNamespace());
        sourceapportionment.setInspireidVersionid(dateFormatUtil.getToday());

        RelatedpartyWrapper relatedpartyWrapper = new RelatedpartyWrapper();
        Relatedparty provider = relatedpartyWrapper.createDraftProviderFromUser(sourceapportionmentUuid, user);
        sourceapportionment.setProvider(provider);

        sourceapportionment.setReferenceyearId("");
        sourceapportionment.setReferenceyearTimeperiod("");

        sourceapportionment.setComment("");
        sourceapportionment.setUsers(user);
        sourceapportionment.setCompleted(Boolean.FALSE);

        sourceapportionment.setDatecreation(new Date());
        sourceapportionment.setDatelastupdate(new Date());

        sourceapportionment.setChanges(false);
        sourceapportionment.setReportingstartdate("");
        sourceapportionment.setReportingenddate("");

        Urbanbackground urbanbackground = UrbanbackgroundWrapper.createDraftUrbanbackgroundForSourceapportionment(sourceapportionmentUuid);
        sourceapportionment.setUrbanbackground(urbanbackground);

        Regionalbackground regionalbackground = RegionalbackgroundWrapper.createDraftRegionalbackgroundForSourceapportionment(sourceapportionmentUuid);
        sourceapportionment.setRegionalbackground(regionalbackground);

        Localincrement localincrement = LocalincrementWrapper.createDraftLocalincrementForSourceapportionment(sourceapportionmentUuid);
        sourceapportionment.setLocalincrement(localincrement);

        Exceedancedescription exceedancedescription = ExceedancedescriptionWrapper.createDraftExceedancedescriptionForSourceapportionment(sourceapportionment);
        sourceapportionment.setExceedancedescription(exceedancedescription);

        sourceapportionment.setPlan(null);
        sourceapportionment.setAttainment(null);

        emc.beginTransaction(em);
        em.persist(provider);
        em.persist(urbanbackground);
        em.persist(regionalbackground);
        em.persist(localincrement);

        em.persist(exceedancedescription.getExceedanceexposure());
        em.persist(exceedancedescription.getDeductionassessmentmethod());
        em.persist(exceedancedescription.getExceedancearea());
        em.persist(exceedancedescription);

        em.persist(sourceapportionment);
        emc.commitAndCloseTransaction(em);

        return SourceapportionmentWrapper.convertSourceapportionmentInSourceapportionmentBean(sourceapportionment, sourceapportionment.getUsers());
    }

    /**
     * return a list of SourceapportionmentBean
     *
     * @param userEmail
     * @return List of SourceapportionmentBean
     */
    public List<SourceapportionmentBean> getAllSourceapportionmentByUser(String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Users.findByEmail");
        q.setParameter("email", userEmail);
        Users userSourceapportionment = (Users) q.getSingleResult();
        Country country = userSourceapportionment.getCountry();
        Userrole userrole = userSourceapportionment.getUserrole();

        List<SourceapportionmentBean> sourceapportionmentBeanList = new ArrayList<SourceapportionmentBean>();

        if (!userrole.getUuid().equals("0")) {
            q = em.createNamedQuery("Users.findByCountry");
            q.setParameter("country", country);

            List<Users> userList = q.getResultList();
            for (Users users : userList) {
                q = em.createNamedQuery("Sourceapportionment.findAllByUser");
                q.setParameter("users", users);

                List<Sourceapportionment> results = (List<Sourceapportionment>) q.getResultList();
                for (Sourceapportionment sourceapportionment : results) {
                    sourceapportionmentBeanList.add(SourceapportionmentWrapper.convertSourceapportionmentInSourceapportionmentBean(sourceapportionment, userSourceapportionment));
                }
            }
        } else {
            q = em.createNamedQuery("Sourceapportionment.findAll");

            List<Sourceapportionment> results = (List<Sourceapportionment>) q.getResultList();
            for (Sourceapportionment sourceapportionment : results) {
                sourceapportionmentBeanList.add(SourceapportionmentWrapper.convertSourceapportionmentInSourceapportionmentBean(sourceapportionment, userSourceapportionment));
            }
        }

        em.close();
        return sourceapportionmentBeanList;
    }

    /**
     ** return the SourceapportionmentBean with a specific ID
     *
     * @param sourceapportionmentID
     * @param userEmail
     * @return SourceapportionmentBean
     */
    public SourceapportionmentBean getSourceapportionmentByID(String sourceapportionmentID, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Users.findByEmail");
        q.setParameter("email", userEmail);
        Users userSourceapportionment = (Users) q.getSingleResult();

        q = em.createNamedQuery("Sourceapportionment.findByUuid");
        q.setParameter("uuid", sourceapportionmentID);
        Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();
        em.close();
        return SourceapportionmentWrapper.convertSourceapportionmentInSourceapportionmentBean(sourceapportionment, userSourceapportionment);
    }

    /**
     * Delete the Sourceapportionment with a specific ID param: ID of the
     * Sourceapportionment; return: void
     *
     * @param sourceapportionmentID
     * @throws eu.europa.ec.aqrexception.DeleteSourceapportionmentException
     */
    public void deleteSourceapportionmentByID(String sourceapportionmentID) throws DeleteSourceapportionmentException {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Sourceapportionment.findByUuid");
        q.setParameter("uuid", sourceapportionmentID);
        Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();

        q = em.createNamedQuery("DeductionassessmentmethodAssesmentmethods.deleteByDeductionassessmentmethod");
        q.setParameter("deductionassessmentmethod", sourceapportionment.getExceedancedescription().getDeductionassessmentmethod());
        q.executeUpdate();

        q = em.createNamedQuery("DeductionassessmentmethodAssesmentmethods.findByDeductionassessmentmethod");
        q.setParameter("deductionassessmentmethod", sourceapportionment.getExceedancedescription().getDeductionassessmentmethod());
        List<DeductionassessmentmethodAssesmentmethods> deductionassessmentmethodAssesmentmethodsList = q.getResultList();
        for (DeductionassessmentmethodAssesmentmethods deductionassessmentmethodAssesmentmethods : deductionassessmentmethodAssesmentmethodsList) {
            q = em.createNamedQuery("Assesmentmethods.deleteByUuid");
            q.setParameter("uuid", deductionassessmentmethodAssesmentmethods.getAssesmentmethods().getUuid());
            q.executeUpdate();

        }

        q = em.createNamedQuery("DeductionassessmentmethodAdjustmentsource.deleteByDeductionassessmentmethod");
        q.setParameter("deductionassessmentmethod", sourceapportionment.getExceedancedescription().getDeductionassessmentmethod());
        q.executeUpdate();

        q = em.createNamedQuery("Sourceapportionment.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getUuid());
        try {
            q.executeUpdate();
        } catch (Exception ex) {
            throw new DeleteSourceapportionmentException();
        }

        q = em.createNamedQuery("ExceedancedescriptionReasonvalue.deleteByExceedancedescription");
        q.setParameter("exceedancedescription", sourceapportionment.getExceedancedescription());
        q.executeUpdate();

        q = em.createNamedQuery("Exceedancedescription.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getExceedancedescription().getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("ExceedenceareaAreaclassification.deleteByExceedancearea");
        q.setParameter("exceedancearea", sourceapportionment.getExceedancedescription().getExceedancearea());
        q.executeUpdate();

        q = em.createNamedQuery("Exceedancearea.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getExceedancedescription().getExceedancearea().getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Deductionassessmentmethod.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getExceedancedescription().getDeductionassessmentmethod().getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Exceedanceexposure.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getExceedancedescription().getExceedanceexposure().getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Relatedparty.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getProvider().getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Localincrement.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getLocalincrement().getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Regionalbackground.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getRegionalbackground().getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Urbanbackground.deleteByUuid");
        q.setParameter("uuid", sourceapportionment.getUrbanbackground().getUuid());
        q.executeUpdate();

        emc.commitAndCloseTransaction(em);
    }

    /**
     * clone a Sourceapportionment for a specific user
     *
     * @param sourceapportionmentID
     * @param userEmail
     * @return the Id of the new cloned Sourceapportionment
     * @throws java.lang.Exception
     */
    public String cloneSourceapportionmentByPlanIDAndUser(String sourceapportionmentID, String userEmail) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Sourceapportionment cloneSourceapportionment = new Sourceapportionment();
        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Users.findByEmail");
        q.setParameter("email", userEmail);
        Users user = (Users) q.getSingleResult();

        q = em.createNamedQuery("Sourceapportionment.findByUuid");
        q.setParameter("uuid", sourceapportionmentID);
        Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();

        String cloneSourceapportionmentUuid = StringUtils.createUUID(sourceapportionment.getInspireidNamespace() + dateFormatUtil.getToday(), Sourceapportionment.class);
        cloneSourceapportionment.setUuid(cloneSourceapportionmentUuid);

        Country country = user.getCountry();
        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        cloneSourceapportionment.setInspireidLocalid("Clone_" + dateFormatUtil.getToday());
        cloneSourceapportionment.setInspireidNamespace(systemconfiguration.getNamespace());
        cloneSourceapportionment.setInspireidVersionid(dateFormatUtil.getToday());

        Relatedparty cloneProvider = new Relatedparty();
        Relatedparty measureProvider = sourceapportionment.getProvider();
        String cloneProviderUuid = StringUtils.createUUID(cloneSourceapportionment.getUuid() + user.getEmail() + dateFormatUtil.getToday(), Relatedparty.class);
        cloneProvider.setUuid(cloneProviderUuid);
        cloneProvider.setIndividualname(measureProvider.getIndividualname());
        cloneProvider.setOrganisationname(measureProvider.getOrganisationname());
        cloneProvider.setAddress(measureProvider.getAddress());
        cloneProvider.setElectronicmailaddress(measureProvider.getElectronicmailaddress());
        cloneProvider.setTelephonevoice(measureProvider.getTelephonevoice());
        cloneProvider.setWebsite(measureProvider.getWebsite());
        cloneSourceapportionment.setProvider(cloneProvider);

        em.persist(cloneProvider);
        cloneSourceapportionment.setUsers(user);

        cloneSourceapportionment.setChanges(sourceapportionment.isChanges());
        cloneSourceapportionment.setDescriptionofchanges(sourceapportionment.getDescriptionofchanges());
        cloneSourceapportionment.setReportingstartdate(sourceapportionment.getReportingstartdate());
        cloneSourceapportionment.setReportingenddate(sourceapportionment.getReportingenddate());

        cloneSourceapportionment.setReferenceyearId(sourceapportionment.getReferenceyearId());
        cloneSourceapportionment.setReferenceyearTimeperiod(sourceapportionment.getReferenceyearTimeperiod());

        cloneRegionalBackground(cloneSourceapportionment, sourceapportionment.getRegionalbackground(), em);
        cloneUrbanBackground(cloneSourceapportionment, sourceapportionment.getUrbanbackground(), em);
        cloneLocalIncrement(cloneSourceapportionment, sourceapportionment.getLocalincrement(), em);
        cloneExceedancedescription(cloneSourceapportionment, sourceapportionment.getExceedancedescription(), em);

        cloneSourceapportionment.setCompleted(sourceapportionment.getCompleted());
        cloneSourceapportionment.setComment(sourceapportionment.getComment());

        cloneSourceapportionment.setPlan(sourceapportionment.getPlan());
        cloneSourceapportionment.setAttainment(sourceapportionment.getAttainment());

        cloneSourceapportionment.setDatecreation(new Date());
        cloneSourceapportionment.setDatelastupdate(new Date());

        em.persist(cloneSourceapportionment);
        emc.commitAndCloseTransaction(em);

        return cloneSourceapportionment.getUuid();
    }

    /**
     * @param cloneSourceapportionment
     * @param regionalbackground
     * @param em clone the RegionalBackground
     */
    private void cloneRegionalBackground(Sourceapportionment cloneSourceapportionment, Regionalbackground regionalbackground, EntityManager em) throws Exception {
        Regionalbackground cloneRegionalbackground = new Regionalbackground();
        String cloneRegionalbackgroundUuid = StringUtils.createUUID(cloneSourceapportionment.getUuid() + regionalbackground.getUuid() + dateFormatUtil.getToday(), Regionalbackground.class);

        cloneRegionalbackground.setUuid(cloneRegionalbackgroundUuid);

        cloneRegionalbackground.setTotal(regionalbackground.getTotal());
        cloneRegionalbackground.setTotalcomment(regionalbackground.getTotalcomment());
        cloneRegionalbackground.setTotalNil(regionalbackground.getTotalNil());
        cloneRegionalbackground.setTotalNilreason(regionalbackground.getTotalNilreason());

        cloneRegionalbackground.setFromwithinms(regionalbackground.getFromwithinms());
        cloneRegionalbackground.setFromwithinmscomment(regionalbackground.getFromwithinmscomment());
        cloneRegionalbackground.setFromwithinmsNil(regionalbackground.getFromwithinmsNil());
        cloneRegionalbackground.setFromwithinmsNilreason(regionalbackground.getFromwithinmsNilreason());

        cloneRegionalbackground.setNaturalregionalbackground(regionalbackground.getNaturalregionalbackground());
        cloneRegionalbackground.setNaturalregionalbackgroundcomment(regionalbackground.getNaturalregionalbackgroundcomment());
        cloneRegionalbackground.setNaturalregionalbackgroundNil(regionalbackground.getNaturalregionalbackgroundNil());
        cloneRegionalbackground.setNaturalregionalbackgroundNilreason(regionalbackground.getNaturalregionalbackgroundNilreason());

        cloneRegionalbackground.setTransboundary(regionalbackground.getTransboundary());
        cloneRegionalbackground.setTransboundarycomment(regionalbackground.getTransboundarycomment());
        cloneRegionalbackground.setTransboundaryNil(regionalbackground.getTransboundaryNil());
        cloneRegionalbackground.setTransboundaryNilreason(regionalbackground.getTransboundaryNilreason());

        cloneRegionalbackground.setOther(regionalbackground.getOther());
        cloneRegionalbackground.setOthercomment(regionalbackground.getOthercomment());
        cloneRegionalbackground.setOtherNil(regionalbackground.getOtherNil());
        cloneRegionalbackground.setOtherNilreason(regionalbackground.getOtherNilreason());

        cloneRegionalbackground.setUnitmisure(regionalbackground.getUnitmisure());
        cloneRegionalbackground.setUnitmisurecomment(regionalbackground.getUnitmisurecomment());
        cloneRegionalbackground.setUnitmisureNil(regionalbackground.getUnitmisureNil());
        cloneRegionalbackground.setUnitmisureNilreason(regionalbackground.getUnitmisureNilreason());

        cloneSourceapportionment.setRegionalbackground(cloneRegionalbackground);
        em.persist(cloneRegionalbackground);
    }

    /**
     * @param cloneSourceapportionment
     * @param urbanbackground
     * @param em clone the Urbanbackground
     */
    private void cloneUrbanBackground(Sourceapportionment cloneSourceapportionment, Urbanbackground urbanbackground, EntityManager em) throws Exception {
        Urbanbackground cloneUrbanbackground = new Urbanbackground();
        String cloneUrbanbackgroundUuid = StringUtils.createUUID(cloneSourceapportionment.getUuid() + urbanbackground.getUuid() + dateFormatUtil.getToday(), Urbanbackground.class);

        cloneUrbanbackground.setUuid(cloneUrbanbackgroundUuid);

        cloneUrbanbackground.setTotal(urbanbackground.getTotal());
        cloneUrbanbackground.setTotalcomment(urbanbackground.getTotalcomment());
        cloneUrbanbackground.setTotalNil(urbanbackground.getTotalNil());
        cloneUrbanbackground.setTotalNilreason(urbanbackground.getTotalNilreason());

        cloneUrbanbackground.setTraffic(urbanbackground.getTraffic());
        cloneUrbanbackground.setTrafficcomment(urbanbackground.getTrafficcomment());
        cloneUrbanbackground.setTrafficNil(urbanbackground.getTrafficNil());
        cloneUrbanbackground.setTrafficNilreason(urbanbackground.getTrafficNilreason());

        cloneUrbanbackground.setHeatandpowerproduction(urbanbackground.getHeatandpowerproduction());
        cloneUrbanbackground.setHeatandpowerproductioncomment(urbanbackground.getHeatandpowerproductioncomment());
        cloneUrbanbackground.setHeatandpowerproductionNil(urbanbackground.getHeatandpowerproductionNil());
        cloneUrbanbackground.setHeatandpowerproductionNilreason(urbanbackground.getHeatandpowerproductionNilreason());

        cloneUrbanbackground.setAgriculture(urbanbackground.getAgriculture());
        cloneUrbanbackground.setAgriculturecomment(urbanbackground.getAgriculturecomment());
        cloneUrbanbackground.setAgricultureNil(urbanbackground.getAgricultureNil());
        cloneUrbanbackground.setAgricultureNilreason(urbanbackground.getAgricultureNilreason());

        cloneUrbanbackground.setCommercialandresidential(urbanbackground.getCommercialandresidential());
        cloneUrbanbackground.setCommercialandresidentialcomment(urbanbackground.getCommercialandresidentialcomment());
        cloneUrbanbackground.setCommercialandresidentialNil(urbanbackground.getCommercialandresidentialNil());
        cloneUrbanbackground.setCommercialandresidentialNilreason(urbanbackground.getCommercialandresidentialNilreason());

        cloneUrbanbackground.setShipping(urbanbackground.getShipping());
        cloneUrbanbackground.setShippingcomment(urbanbackground.getShippingcomment());
        cloneUrbanbackground.setShippingNil(urbanbackground.getShippingNil());
        cloneUrbanbackground.setShippingNilreason(urbanbackground.getShippingNilreason());

        cloneUrbanbackground.setOffroadmobilemachinery(urbanbackground.getOffroadmobilemachinery());
        cloneUrbanbackground.setOffroadmobilemachinerycomment(urbanbackground.getOffroadmobilemachinerycomment());
        cloneUrbanbackground.setOffroadmobilemachineryNil(urbanbackground.getOffroadmobilemachineryNil());
        cloneUrbanbackground.setOffroadmobilemachineryNilreason(urbanbackground.getOffroadmobilemachineryNilreason());

        cloneUrbanbackground.setNaturalurbanbackground(urbanbackground.getNaturalurbanbackground());
        cloneUrbanbackground.setNaturalurbanbackgroundcomment(urbanbackground.getNaturalurbanbackgroundcomment());
        cloneUrbanbackground.setNaturalurbanbackgroundNil(urbanbackground.getNaturalurbanbackgroundNil());
        cloneUrbanbackground.setNaturalurbanbackgroundNilreason(urbanbackground.getNaturalurbanbackgroundNilreason());

        cloneUrbanbackground.setTransboundary(urbanbackground.getTransboundary());
        cloneUrbanbackground.setTransboundarycomment(urbanbackground.getTransboundarycomment());
        cloneUrbanbackground.setTransboundaryNil(urbanbackground.getTransboundaryNil());
        cloneUrbanbackground.setTransboundaryNilreason(urbanbackground.getTransboundaryNilreason());

        cloneUrbanbackground.setOther(urbanbackground.getOther());
        cloneUrbanbackground.setOthercomment(urbanbackground.getOthercomment());
        cloneUrbanbackground.setOtherNil(urbanbackground.getOtherNil());
        cloneUrbanbackground.setOtherNilreason(urbanbackground.getOtherNilreason());

        cloneUrbanbackground.setUnitmisure(urbanbackground.getUnitmisure());
        cloneUrbanbackground.setUnitmisurecomment(urbanbackground.getUnitmisurecomment());
        cloneUrbanbackground.setUnitmisureNil(urbanbackground.getUnitmisureNil());
        cloneUrbanbackground.setUnitmisureNilreason(urbanbackground.getUnitmisureNilreason());

        cloneSourceapportionment.setUrbanbackground(cloneUrbanbackground);
        em.persist(cloneUrbanbackground);
    }

    /**
     * @param cloneSourceapportionment
     * @param localincrement
     * @param em clone the Localincrement
     */
    private void cloneLocalIncrement(Sourceapportionment cloneSourceapportionment, Localincrement localincrement, EntityManager em) throws Exception {
        Localincrement clonelocalincrement = new Localincrement();
        String cloneLocalincrementUuid = StringUtils.createUUID(cloneSourceapportionment.getUuid() + localincrement.getUuid() + dateFormatUtil.getToday(), Localincrement.class);

        clonelocalincrement.setUuid(cloneLocalincrementUuid);

        clonelocalincrement.setTotal(localincrement.getTotal());
        clonelocalincrement.setTotalcomment(localincrement.getTotalcomment());
        clonelocalincrement.setTotalNil(localincrement.getTotalNil());
        clonelocalincrement.setTotalNilreason(localincrement.getTotalNilreason());

        clonelocalincrement.setTraffic(localincrement.getTraffic());
        clonelocalincrement.setTrafficcomment(localincrement.getTrafficcomment());
        clonelocalincrement.setTrafficNil(localincrement.getTrafficNil());
        clonelocalincrement.setTrafficNilreason(localincrement.getTrafficNilreason());

        clonelocalincrement.setHeatandpowerproduction(localincrement.getHeatandpowerproduction());
        clonelocalincrement.setHeatandpowerproductioncomment(localincrement.getHeatandpowerproductioncomment());
        clonelocalincrement.setHeatandpowerproductionNil(localincrement.getHeatandpowerproductionNil());
        clonelocalincrement.setHeatandpowerproductionNilreason(localincrement.getHeatandpowerproductionNilreason());

        clonelocalincrement.setAgriculture(localincrement.getAgriculture());
        clonelocalincrement.setAgriculturecomment(localincrement.getAgriculturecomment());
        clonelocalincrement.setAgricultureNil(localincrement.getAgricultureNil());
        clonelocalincrement.setAgricultureNilreason(localincrement.getAgricultureNilreason());

        clonelocalincrement.setCommercialandresidential(localincrement.getCommercialandresidential());
        clonelocalincrement.setCommercialandresidentialcomment(localincrement.getCommercialandresidentialcomment());
        clonelocalincrement.setCommercialandresidentialNil(localincrement.getCommercialandresidentialNil());
        clonelocalincrement.setCommercialandresidentialNilreason(localincrement.getCommercialandresidentialNilreason());

        clonelocalincrement.setShipping(localincrement.getShipping());
        clonelocalincrement.setShippingcomment(localincrement.getShippingcomment());
        clonelocalincrement.setShippingNil(localincrement.getShippingNil());
        clonelocalincrement.setShippingNilreason(localincrement.getShippingNilreason());

        clonelocalincrement.setOffroadmobilemachinery(localincrement.getOffroadmobilemachinery());
        clonelocalincrement.setOffroadmobilemachinerycomment(localincrement.getOffroadmobilemachinerycomment());
        clonelocalincrement.setOffroadmobilemachineryNil(localincrement.getOffroadmobilemachineryNil());
        clonelocalincrement.setOffroadmobilemachineryNilreason(localincrement.getOffroadmobilemachineryNilreason());

        clonelocalincrement.setNaturallocalincrement(localincrement.getNaturallocalincrement());
        clonelocalincrement.setNaturallocalincrementcomment(localincrement.getNaturallocalincrementcomment());
        clonelocalincrement.setNaturallocalincrementNil(localincrement.getNaturallocalincrementNil());
        clonelocalincrement.setNaturallocalincrementNilreason(localincrement.getNaturallocalincrementNilreason());

        clonelocalincrement.setTransboundary(localincrement.getTransboundary());
        clonelocalincrement.setTransboundarycomment(localincrement.getTransboundarycomment());
        clonelocalincrement.setTransboundaryNil(localincrement.getTransboundaryNil());
        clonelocalincrement.setTransboundaryNilreason(localincrement.getTransboundaryNilreason());

        clonelocalincrement.setOther(localincrement.getOther());
        clonelocalincrement.setOthercomment(localincrement.getOthercomment());
        clonelocalincrement.setOtherNil(localincrement.getOtherNil());
        clonelocalincrement.setOtherNilreason(localincrement.getOtherNilreason());

        clonelocalincrement.setUnitmisure(localincrement.getUnitmisure());
        clonelocalincrement.setUnitmisurecomment(localincrement.getUnitmisurecomment());
        clonelocalincrement.setUnitmisureNil(localincrement.getUnitmisureNil());
        clonelocalincrement.setUnitmisureNilreason(localincrement.getUnitmisureNilreason());

        cloneSourceapportionment.setLocalincrement(clonelocalincrement);
        em.persist(clonelocalincrement);
    }

    /**
     * @param cloneSourceapportionment
     * @param exceedancedescription
     * @param em clone the Exceedancedescription
     */
    private void cloneExceedancedescription(Sourceapportionment cloneSourceapportionment, Exceedancedescription exceedancedescription, EntityManager em) throws Exception {
        Exceedancedescription cloneExceedancedescription = new Exceedancedescription();
        String cloneExceedancedescriptionUuid = StringUtils.createUUID(cloneSourceapportionment.getUuid() + exceedancedescription.getUuid() + dateFormatUtil.getToday(), Exceedancedescription.class);
        cloneExceedancedescription.setUuid(cloneExceedancedescriptionUuid);

        cloneExceedancedescription.setExceedance(exceedancedescription.getExceedance());
        cloneExceedancedescription.setNumericalexceedance(exceedancedescription.getNumericalexceedance());
        cloneExceedancedescription.setNumberexceedances(exceedancedescription.getNumberexceedances());

        cloneDeductionAssessmentMethod(cloneExceedancedescription, exceedancedescription.getDeductionassessmentmethod(), em);
        cloneExceedanceArea(cloneExceedancedescription, exceedancedescription.getExceedancearea(), em);
        cloneExceedanceexposure(cloneExceedancedescription, exceedancedescription.getExceedanceexposure(), em);

        cloneExceedancedescription.setOtherreason(exceedancedescription.getOtherreason());
        cloneExceedancedescription.setComment(exceedancedescription.getComment());

        Query q = em.createNamedQuery("ExceedancedescriptionReasonvalue.findByExceedancedescription");
        q.setParameter("exceedancedescription", exceedancedescription);

        List<ExceedancedescriptionReasonvalue> exceedancedescriptionReasonvalueList = (List<ExceedancedescriptionReasonvalue>) q.getResultList();
        for (ExceedancedescriptionReasonvalue exceedancedescriptionReasonvalue : exceedancedescriptionReasonvalueList) {
            Reasonvalue reasonvalue = exceedancedescriptionReasonvalue.getReasonvalue();

            ExceedancedescriptionReasonvalue cloneExceedancedescriptionReasonvalue = new ExceedancedescriptionReasonvalue();
            String cloneExceedancedescriptionReasonvalueUuid = StringUtils.createUUID(cloneExceedancedescriptionUuid + reasonvalue.getUuid(), ExceedancedescriptionReasonvalue.class);
            cloneExceedancedescriptionReasonvalue.setUuid(cloneExceedancedescriptionReasonvalueUuid);
            cloneExceedancedescriptionReasonvalue.setReasonvalue(reasonvalue);
            cloneExceedancedescriptionReasonvalue.setExceedancedescription(cloneExceedancedescription);
            em.persist(cloneExceedancedescriptionReasonvalue);
        }

        cloneExceedancedescription.setDatecreation(new Date());
        cloneExceedancedescription.setDatelastupdate(new Date());

        cloneSourceapportionment.setExceedancedescription(cloneExceedancedescription);
        em.persist(cloneExceedancedescription);
    }

    /**
     * @param cloneExceedancedescription
     * @param deductionassessmentmethod
     * @param em clone the Deductionassessmentmethod
     */
    private void cloneDeductionAssessmentMethod(Exceedancedescription cloneExceedancedescription, Deductionassessmentmethod deductionassessmentmethod, EntityManager em) throws Exception {
        Deductionassessmentmethod cloneDeductionassessmentmethod = new Deductionassessmentmethod();
        String cloneDeductionassessmentmethodUuid = StringUtils.createUUID(cloneExceedancedescription.getUuid() + deductionassessmentmethod.getUuid() + dateFormatUtil.getToday(), Deductionassessmentmethod.class);

        cloneDeductionassessmentmethod.setUuid(cloneDeductionassessmentmethodUuid);
        cloneDeductionassessmentmethod.setAdjustmenttype(deductionassessmentmethod.getAdjustmenttype());

        Query q = em.createNamedQuery("DeductionassessmentmethodAdjustmentsource.findByDeductionassessmentmethod");
        q.setParameter("deductionassessmentmethod", deductionassessmentmethod);

        List<DeductionassessmentmethodAdjustmentsource> deductionassessmentmethodAdjustmentsourceList = (List<DeductionassessmentmethodAdjustmentsource>) q.getResultList();
        for (DeductionassessmentmethodAdjustmentsource deductionassessmentmethodAdjustmentsource : deductionassessmentmethodAdjustmentsourceList) {
            Adjustmentsource adjustmentsource = deductionassessmentmethodAdjustmentsource.getAdjustmentsource();

            DeductionassessmentmethodAdjustmentsource cloneDeductionassessmentmethodAdjustmentsource = new DeductionassessmentmethodAdjustmentsource();
            String cloneDeductionassessmentmethodAdjustmentsourceUuid = StringUtils.createUUID(cloneDeductionassessmentmethod.getUuid() + adjustmentsource.getUuid(), DeductionassessmentmethodAdjustmentsource.class);
            cloneDeductionassessmentmethodAdjustmentsource.setUuid(cloneDeductionassessmentmethodAdjustmentsourceUuid);
            cloneDeductionassessmentmethodAdjustmentsource.setAdjustmentsource(adjustmentsource);
            cloneDeductionassessmentmethodAdjustmentsource.setDeductionassessmentmethod(cloneDeductionassessmentmethod);
            em.persist(cloneDeductionassessmentmethodAdjustmentsource);
        }

        q = em.createNamedQuery("DeductionassessmentmethodAssesmentmethods.findByDeductionassessmentmethod");
        q.setParameter("deductionassessmentmethod", deductionassessmentmethod);

        List<DeductionassessmentmethodAssesmentmethods> deductionassessmentmethodAssesmentmethodsList = (List<DeductionassessmentmethodAssesmentmethods>) q.getResultList();
        for (DeductionassessmentmethodAssesmentmethods deductionassessmentmethodAssesmentmethods : deductionassessmentmethodAssesmentmethodsList) {
            Assesmentmethods assesmentmethods = deductionassessmentmethodAssesmentmethods.getAssesmentmethods();

            Assesmentmethods cloneAssesmentmethods = new Assesmentmethods();
            String cloneAssesmentmethodsUuid = StringUtils.createUUID(cloneDeductionassessmentmethodUuid + assesmentmethods.getUuid() + dateFormatUtil.getToday(), Assesmentmethods.class);
            cloneAssesmentmethods.setUuid(cloneAssesmentmethodsUuid);
            cloneAssesmentmethods.setAssesmenttype(assesmentmethods.getAssesmenttype());
            cloneAssesmentmethods.setAssesmenttypedescription(assesmentmethods.getAssesmenttypedescription());
            cloneAssesmentmethods.setMetadata(assesmentmethods.getMetadata());
            em.persist(cloneAssesmentmethods);

            DeductionassessmentmethodAssesmentmethods cloneDeductionassessmentmethodAssesmentmethods = new DeductionassessmentmethodAssesmentmethods();
            String cloneDeductionassessmentmethodAssesmentmethodsUuid = StringUtils.createUUID(cloneDeductionassessmentmethod.getUuid() + cloneAssesmentmethodsUuid, DeductionassessmentmethodAssesmentmethods.class);
            cloneDeductionassessmentmethodAssesmentmethods.setUuid(cloneDeductionassessmentmethodAssesmentmethodsUuid);
            cloneDeductionassessmentmethodAssesmentmethods.setAssesmentmethods(cloneAssesmentmethods);
            cloneDeductionassessmentmethodAssesmentmethods.setDeductionassessmentmethod(cloneDeductionassessmentmethod);
            em.persist(cloneDeductionassessmentmethodAssesmentmethods);
        }

        cloneExceedancedescription.setDeductionassessmentmethod(cloneDeductionassessmentmethod);
        em.persist(cloneDeductionassessmentmethod);
    }

    /**
     * @param cloneExceedancedescription
     * @param exceedancearea
     * @param em clone the ExceedanceArea
     */
    private void cloneExceedanceArea(Exceedancedescription cloneExceedancedescription, Exceedancearea exceedancearea, EntityManager em) throws Exception {
        Exceedancearea cloneExceedancearea = new Exceedancearea();
        String cloneExceedanceareaUuid = StringUtils.createUUID(cloneExceedancedescription.getUuid() + exceedancearea.getUuid() + dateFormatUtil.getToday(), Exceedancearea.class);

        cloneExceedancearea.setUuid(cloneExceedanceareaUuid);
        cloneExceedancearea.setArea(exceedancearea.getArea());
        cloneExceedancearea.setAreaestimate(exceedancearea.getAreaestimate());
        cloneExceedancearea.setRoadlenghtestimate(exceedancearea.getRoadlenghtestimate());
        cloneExceedancearea.setAdministrativeunits(exceedancearea.getAdministrativeunits());
        cloneExceedancearea.setModelused(exceedancearea.getModelused());
        cloneExceedancearea.setStationused(exceedancearea.getStationused());

        Query q = em.createNamedQuery("ExceedenceareaAreaclassification.findByExceedancearea");
        q.setParameter("exceedancearea", exceedancearea);

        List<ExceedenceareaAreaclassification> exceedenceareaAreaclassificationList = (List<ExceedenceareaAreaclassification>) q.getResultList();
        for (ExceedenceareaAreaclassification exceedenceareaAreaclassification : exceedenceareaAreaclassificationList) {
            Areaclassification areaclassification = exceedenceareaAreaclassification.getAreaclassification();

            ExceedenceareaAreaclassification cloneExceedenceareaAreaclassification = new ExceedenceareaAreaclassification();
            String cloneExceedenceareaAreaclassificationUuid = StringUtils.createUUID(cloneExceedancedescription.getUuid() + areaclassification.getUuid(), ExceedenceareaAreaclassification.class);
            cloneExceedenceareaAreaclassification.setUuid(cloneExceedenceareaAreaclassificationUuid);
            cloneExceedenceareaAreaclassification.setAreaclassification(areaclassification);
            cloneExceedenceareaAreaclassification.setExceedancearea(cloneExceedancearea);
            em.persist(cloneExceedenceareaAreaclassification);
        }

        cloneExceedancedescription.setExceedancearea(cloneExceedancearea);
        em.persist(cloneExceedancearea);
    }

    /**
     * @param cloneExceedancedescription
     * @param exceedanceexposure
     * @param em clone the Exceedanceexposure
     */
    private void cloneExceedanceexposure(Exceedancedescription cloneExceedancedescription, Exceedanceexposure exceedanceexposure, EntityManager em) throws Exception {
        Exceedanceexposure cloneExceedanceexposure = new Exceedanceexposure();
        String cloneExceedanceexposureUuid = StringUtils.createUUID(cloneExceedancedescription.getUuid() + exceedanceexposure.getUuid() + dateFormatUtil.getToday(), Exceedanceexposure.class);

        cloneExceedanceexposure.setUuid(cloneExceedanceexposureUuid);
        cloneExceedanceexposure.setExposedpopulation(exceedanceexposure.getExposedpopulation());
        cloneExceedanceexposure.setExposedarea(exceedanceexposure.getExposedarea());
        cloneExceedanceexposure.setSensitiveresidentpopulation(exceedanceexposure.getSensitiveresidentpopulation());
        cloneExceedanceexposure.setRelevantinfrastructure(exceedanceexposure.getRelevantinfrastructure());
        cloneExceedanceexposure.setReferenceyear(exceedanceexposure.getReferenceyear());

        cloneExceedancedescription.setExceedanceexposure(cloneExceedanceexposure);
        em.persist(cloneExceedanceexposure);
    }

    /**
     *
     * @param sourceapportionmentID
     * @param assesmentmethodsBean
     * @throws java.lang.Exception
     */
    public void saveAssesmentmethodsToSourceapportionmentID(String sourceapportionmentID, AssesmentmethodsBean assesmentmethodsBean) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Sourceapportionment.findByUuid");
        q.setParameter("uuid", sourceapportionmentID);
        Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();

        Assesmentmethods assesmentmethods;
        if (assesmentmethodsBean.getUuid() == null) {
            assesmentmethods = new Assesmentmethods();
            String assesmentmethodsUuid = StringUtils.createUUID(sourceapportionmentID + dateFormatUtil.getToday(), Assesmentmethods.class);
            assesmentmethods.setUuid(assesmentmethodsUuid);
        } else {
            q = em.createNamedQuery("Assesmentmethods.findByUuid");
            q.setParameter("uuid", assesmentmethodsBean.getUuid());
            assesmentmethods = (Assesmentmethods) q.getSingleResult();
        }

        if (assesmentmethodsBean.getAssesmenttypedescription() != null) {
            assesmentmethods.setAssesmenttypedescription(assesmentmethodsBean.getAssesmenttypedescription());
        } else {
            assesmentmethods.setAssesmenttypedescription("");
        }

        if (assesmentmethodsBean.getMetadata() != null) {
            assesmentmethods.setMetadata(assesmentmethodsBean.getMetadata());
        } else {
            assesmentmethods.setMetadata("");
        }

        AssesmenttypeBean assesmenttypeBean = assesmentmethodsBean.getAssesmenttypeBean();
        if (assesmenttypeBean != null) {
            String assesmenttypeUri = assesmenttypeBean.getUri();
            q = em.createNamedQuery("Assesmenttype.findByUri");
            q.setParameter("uri", assesmenttypeUri);
            Assesmenttype assesmenttype = (Assesmenttype) q.getSingleResult();
            assesmentmethods.setAssesmenttype(assesmenttype);
        } else {
            assesmentmethods.setAssesmenttype(null);
        }

        emc.beginTransaction(em);
        if (assesmentmethodsBean.getUuid() == null) {
            em.persist(assesmentmethods);

            DeductionassessmentmethodAssesmentmethods deductionassessmentmethodAssesmentmethods = new DeductionassessmentmethodAssesmentmethods();
            String deductionassessmentmethodAssesmentmethodsUuid = StringUtils.createUUID(sourceapportionmentID + assesmentmethods.getUuid() + dateFormatUtil.getToday(), DeductionassessmentmethodAssesmentmethods.class);
            deductionassessmentmethodAssesmentmethods.setUuid(deductionassessmentmethodAssesmentmethodsUuid);
            deductionassessmentmethodAssesmentmethods.setAssesmentmethods(assesmentmethods);
            deductionassessmentmethodAssesmentmethods.setDeductionassessmentmethod(sourceapportionment.getExceedancedescription().getDeductionassessmentmethod());
            em.persist(deductionassessmentmethodAssesmentmethods);
        } else {
            em.merge(assesmentmethods);
        }
        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param assesmentmethodsID
     */
    public void deleteAssesmentmethodsByAssesmentmethodsID(String assesmentmethodsID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Assesmentmethods.findByUuid");
        q.setParameter("uuid", assesmentmethodsID);
        Assesmentmethods assesmentmethods = (Assesmentmethods) q.getSingleResult();

        q = em.createNamedQuery("DeductionassessmentmethodAssesmentmethods.deleteByAssesmentmethods");
        q.setParameter("assesmentmethods", assesmentmethods);
        q.executeUpdate();

        q = em.createNamedQuery("Assesmentmethods.deleteByUuid");
        q.setParameter("uuid", assesmentmethodsID);
        q.executeUpdate();

        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param sourceapportionmentID
     * @return
     */
    public List<AssesmentmethodsBean> getAllAssesmentmethodsBySourceapportionmentID(String sourceapportionmentID) {
        List<AssesmentmethodsBean> assesmentmethodsBeanList = new ArrayList<AssesmentmethodsBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Sourceapportionment.findByUuid");
        q.setParameter("uuid", sourceapportionmentID);
        Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();
        Deductionassessmentmethod deductionassessmentmethod = sourceapportionment.getExceedancedescription().getDeductionassessmentmethod();

        q = em.createNamedQuery("DeductionassessmentmethodAssesmentmethods.findByDeductionassessmentmethod");
        q.setParameter("deductionassessmentmethod", deductionassessmentmethod);
        List<DeductionassessmentmethodAssesmentmethods> results = q.getResultList();

        for (DeductionassessmentmethodAssesmentmethods deductionassessmentmethodAssesmentmethods : results) {
            assesmentmethodsBeanList.add(AssesmentmethodsWrapper.convertAssesmentmethodsInAssesmentmethodsBean(deductionassessmentmethodAssesmentmethods.getAssesmentmethods()));
        }
        em.close();

        return assesmentmethodsBeanList;
    }

    /**
     *
     * @param assesmentmethodsID
     * @return
     */
    public AssesmentmethodsBean getAssesmentmethodsByAssesmentmethodsID(String assesmentmethodsID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Assesmentmethods.findByUuid");
        q.setParameter("uuid", assesmentmethodsID);
        Assesmentmethods assesmentmethods = (Assesmentmethods) q.getSingleResult();

        return AssesmentmethodsWrapper.convertAssesmentmethodsInAssesmentmethodsBean(assesmentmethods);
    }

    /**
     * save the Sourceapportionment
     *
     * @param sourceapportionmentBean
     * @throws java.lang.Exception
     * @throws
     * eu.europa.ec.aqrexception.SourceapportionmentINSPIRELocalIDAlreadyExistingException
     */
    public void saveSourceapportionmentDraft(SourceapportionmentBean sourceapportionmentBean) throws Exception, SourceapportionmentINSPIRELocalIDAlreadyExistingException {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Sourceapportionment.findByUuid");
        q.setParameter("uuid", sourceapportionmentBean.getUuid());
        Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();

        q = em.createNamedQuery("Users.findByEmail");
        q.setParameter("email", sourceapportionment.getUsers().getEmail());
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        boolean sourceapportionmentINSPIRELocalIDAlreadyExisting = false;

        if (sourceapportionmentBean.getInspireidLocalid() != null) {

            if (!sourceapportionment.getInspireidLocalid().equals(sourceapportionmentBean.getInspireidLocalid())) {
                q = em.createNamedQuery("Sourceapportionment.findByInspireidLocalid");
                q.setParameter("inspireidLocalid", sourceapportionmentBean.getInspireidLocalid());

                List<Sourceapportionment> sourceapportionmentList = q.getResultList();
                int k = 0;
                for (Sourceapportionment existentSourceapportionment : sourceapportionmentList) {
                    if (!existentSourceapportionment.getUsers().getCountry().equals(country)) {
                        k++;
                    }
                }

                if (k == sourceapportionmentList.size()) {
                    sourceapportionment.setInspireidLocalid(sourceapportionmentBean.getInspireidLocalid());
                } else {
                    sourceapportionmentINSPIRELocalIDAlreadyExisting = true;
                }
            }
        } else {
            sourceapportionment.setInspireidLocalid("");
        }

        if (sourceapportionmentBean.getInspireidNamespace() != null) {
            sourceapportionment.setInspireidNamespace(sourceapportionmentBean.getInspireidNamespace());
        } else {
            try {
                q = em.createNamedQuery("Systemconfiguration.findByCountry");
                q.setParameter("country", user.getCountry());
                Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();
                sourceapportionment.setInspireidNamespace(systemconfiguration.getNamespace());
            } catch (Exception e) {
                sourceapportionment.setInspireidNamespace("");
            }
        }
        sourceapportionment.setInspireidVersionid(dateFormatUtil.getToday());

        if (sourceapportionmentBean.getReferenceyearId() != null) {
            sourceapportionment.setReferenceyearId(sourceapportionmentBean.getReferenceyearId());
        } else {
            sourceapportionment.setReferenceyearId("");
        }

        if (sourceapportionmentBean.getReferenceyearTimeperiod() != null) {
            sourceapportionment.setReferenceyearTimeperiod(sourceapportionmentBean.getReferenceyearTimeperiod());
        } else {
            sourceapportionment.setReferenceyearTimeperiod("");
        }

        if (sourceapportionmentBean.getComment() != null) {
            sourceapportionment.setComment(sourceapportionmentBean.getComment());
        } else {
            sourceapportionment.setComment("");
        }

        sourceapportionment.setCompleted(sourceapportionmentBean.isCompleted());

        RelatedpartyWrapper relatedpartyWrapper = new RelatedpartyWrapper();
        Relatedparty provider = relatedpartyWrapper.saveProviderDraft(em, sourceapportionment.getProvider().getUuid(), sourceapportionmentBean.getProviderBean(), sourceapportionment.getUsers());

        sourceapportionment.setDatelastupdate(new Date());

        sourceapportionment.setChanges(sourceapportionmentBean.isChanges());
        if (sourceapportionmentBean.isChanges()) {
            if (sourceapportionmentBean.getDescriptionofchanges() != null) {
                sourceapportionment.setDescriptionofchanges(sourceapportionmentBean.getDescriptionofchanges());
            } else {
                sourceapportionment.setDescriptionofchanges("");
            }
        }

        if (sourceapportionmentBean.getReportingstartdate() != null) {
            sourceapportionment.setReportingstartdate(sourceapportionmentBean.getReportingstartdate());
        } else {
            sourceapportionment.setReportingstartdate("");
        }
        if (sourceapportionmentBean.getReportingenddate() != null) {
            sourceapportionment.setReportingenddate(sourceapportionmentBean.getReportingenddate());
        } else {
            sourceapportionment.setReportingenddate("");
        }

        emc.beginTransaction(em);

        saveUrbanbackground(sourceapportionment.getUrbanbackground(), sourceapportionmentBean, em);
        saveRegionalbackground(sourceapportionment.getRegionalbackground(), sourceapportionmentBean, em);
        saveLocalincrement(sourceapportionment.getLocalincrement(), sourceapportionmentBean, em);
        saveExceedancedescription(sourceapportionment.getExceedancedescription(), sourceapportionmentBean, em);

        PlanBean planBean = sourceapportionmentBean.getPlanBean();
        if (planBean != null && planBean.getUuid() != null) {
            String planUuid = planBean.getUuid();

            q = em.createNamedQuery("Plan.findByUuid");
            q.setParameter("uuid", planUuid);
            Plan plan = (Plan) q.getSingleResult();

            sourceapportionment.setPlan(plan);
        } else {
            sourceapportionment.setPlan(null);
        }

        AttainmentBean attainmentBean = sourceapportionmentBean.getAttainmentBean();
        if (attainmentBean != null && attainmentBean.getUuid() != null) {
            String attainmentUuid = attainmentBean.getUuid();

            q = em.createNamedQuery("Attainment.findByUuid");
            q.setParameter("uuid", attainmentUuid);
            Attainment attainment = (Attainment) q.getSingleResult();

            sourceapportionment.setAttainment(attainment);
        } else {
            sourceapportionment.setAttainment(null);
        }

        em.merge(provider);
        em.merge(sourceapportionment);
        emc.commitAndCloseTransaction(em);

        if (sourceapportionmentINSPIRELocalIDAlreadyExisting) {
            throw new SourceapportionmentINSPIRELocalIDAlreadyExistingException();
        }
    }

    private void saveUrbanbackground(Urbanbackground urbanbackground, SourceapportionmentBean sourceapportionmentBean, EntityManager em) {
        UrbanbackgroundBean urbanbackgroundBean = sourceapportionmentBean.getUrbanbackgroundBean();

        if (urbanbackgroundBean != null && urbanbackgroundBean.getTotal() != null) {
            urbanbackground.setTotal(urbanbackgroundBean.getTotal());
        } else {
            urbanbackground.setTotal("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getTotalcomment() != null) {
            urbanbackground.setTotalcomment(urbanbackgroundBean.getTotalcomment());
        } else {
            urbanbackground.setTotalcomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setTotalNil(Boolean.FALSE);
        } else {
            urbanbackground.setTotalNil(urbanbackgroundBean.isTotal_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getTotal_nilreason() != null) {
            urbanbackground.setTotalNilreason(urbanbackgroundBean.getTotal_nilreason());
        } else {
            urbanbackground.setTotalNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getTraffic() != null) {
            urbanbackground.setTraffic(urbanbackgroundBean.getTraffic());
        } else {
            urbanbackground.setTraffic("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getTrafficcomment() != null) {
            urbanbackground.setTrafficcomment(urbanbackgroundBean.getTrafficcomment());
        } else {
            urbanbackground.setTrafficcomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setTrafficNil(Boolean.FALSE);
        } else {
            urbanbackground.setTrafficNil(urbanbackgroundBean.isTraffic_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getTraffic_nilreason() != null) {
            urbanbackground.setTrafficNilreason(urbanbackgroundBean.getTraffic_nilreason());
        } else {
            urbanbackground.setTrafficNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getHeatandpowerproduction() != null) {
            urbanbackground.setHeatandpowerproduction(urbanbackgroundBean.getHeatandpowerproduction());
        } else {
            urbanbackground.setHeatandpowerproduction("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getHeatandpowerproductioncomment() != null) {
            urbanbackground.setHeatandpowerproductioncomment(urbanbackgroundBean.getHeatandpowerproductioncomment());
        } else {
            urbanbackground.setHeatandpowerproductioncomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setHeatandpowerproductionNil(Boolean.FALSE);
        } else {
            urbanbackground.setHeatandpowerproductionNil(urbanbackgroundBean.isHeatandpowerproduction_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getHeatandpowerproduction_nilreason() != null) {
            urbanbackground.setHeatandpowerproductionNilreason(urbanbackgroundBean.getHeatandpowerproduction_nilreason());
        } else {
            urbanbackground.setHeatandpowerproductionNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getAgriculture() != null) {
            urbanbackground.setAgriculture(urbanbackgroundBean.getAgriculture());
        } else {
            urbanbackground.setAgriculture("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getAgriculturecomment() != null) {
            urbanbackground.setAgriculturecomment(urbanbackgroundBean.getAgriculturecomment());
        } else {
            urbanbackground.setAgriculturecomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setAgricultureNil(Boolean.FALSE);
        } else {
            urbanbackground.setAgricultureNil(urbanbackgroundBean.isAgriculture_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getAgriculture_nilreason() != null) {
            urbanbackground.setAgricultureNilreason(urbanbackgroundBean.getAgriculture_nilreason());
        } else {
            urbanbackground.setAgricultureNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getCommercialandresidential() != null) {
            urbanbackground.setCommercialandresidential(urbanbackgroundBean.getCommercialandresidential());
        } else {
            urbanbackground.setCommercialandresidential("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getCommercialandresidentialcomment() != null) {
            urbanbackground.setCommercialandresidentialcomment(urbanbackgroundBean.getCommercialandresidentialcomment());
        } else {
            urbanbackground.setCommercialandresidentialcomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setCommercialandresidentialNil(Boolean.FALSE);
        } else {
            urbanbackground.setCommercialandresidentialNil(urbanbackgroundBean.isCommercialandresidential_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getCommercialandresidential_nilreason() != null) {
            urbanbackground.setCommercialandresidentialNilreason(urbanbackgroundBean.getCommercialandresidential_nilreason());
        } else {
            urbanbackground.setCommercialandresidentialNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getShipping() != null) {
            urbanbackground.setShipping(urbanbackgroundBean.getShipping());
        } else {
            urbanbackground.setShipping("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getShippingcomment() != null) {
            urbanbackground.setShippingcomment(urbanbackgroundBean.getShippingcomment());
        } else {
            urbanbackground.setShippingcomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setShippingNil(Boolean.FALSE);
        } else {
            urbanbackground.setShippingNil(urbanbackgroundBean.isShipping_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getShipping_nilreason() != null) {
            urbanbackground.setShippingNilreason(urbanbackgroundBean.getShipping_nilreason());
        } else {
            urbanbackground.setShippingNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getOffroadmobilemachinery() != null) {
            urbanbackground.setOffroadmobilemachinery(urbanbackgroundBean.getOffroadmobilemachinery());
        } else {
            urbanbackground.setOffroadmobilemachinery("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getOffroadmobilemachinerycomment() != null) {
            urbanbackground.setOffroadmobilemachinerycomment(urbanbackgroundBean.getOffroadmobilemachinerycomment());
        } else {
            urbanbackground.setOffroadmobilemachinerycomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setOffroadmobilemachineryNil(Boolean.FALSE);
        } else {
            urbanbackground.setOffroadmobilemachineryNil(urbanbackgroundBean.isOffroadmobilemachinery_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getOffroadmobilemachinery_nilreason() != null) {
            urbanbackground.setOffroadmobilemachineryNilreason(urbanbackgroundBean.getOffroadmobilemachinery_nilreason());
        } else {
            urbanbackground.setOffroadmobilemachineryNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getNaturalurbanbackground() != null) {
            urbanbackground.setNaturalurbanbackground(urbanbackgroundBean.getNaturalurbanbackground());
        } else {
            urbanbackground.setNaturalurbanbackground("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getNaturalurbanbackgroundcomment() != null) {
            urbanbackground.setNaturalurbanbackgroundcomment(urbanbackgroundBean.getNaturalurbanbackgroundcomment());
        } else {
            urbanbackground.setNaturalurbanbackgroundcomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setNaturalurbanbackgroundNil(Boolean.FALSE);
        } else {
            urbanbackground.setNaturalurbanbackgroundNil(urbanbackgroundBean.isNaturalurbanbackground_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getNaturalurbanbackground_nilreason() != null) {
            urbanbackground.setNaturalurbanbackgroundNilreason(urbanbackgroundBean.getNaturalurbanbackground_nilreason());
        } else {
            urbanbackground.setNaturalurbanbackgroundNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getTransboundary() != null) {
            urbanbackground.setTransboundary(urbanbackgroundBean.getTransboundary());
        } else {
            urbanbackground.setTransboundary("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getTransboundarycomment() != null) {
            urbanbackground.setTransboundarycomment(urbanbackgroundBean.getTransboundarycomment());
        } else {
            urbanbackground.setTransboundarycomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setTransboundaryNil(Boolean.FALSE);
        } else {
            urbanbackground.setTransboundaryNil(urbanbackgroundBean.isTransboundary_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getTransboundary_nilreason() != null) {
            urbanbackground.setTransboundaryNilreason(urbanbackgroundBean.getTransboundary_nilreason());
        } else {
            urbanbackground.setTransboundaryNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getOther() != null) {
            urbanbackground.setOther(urbanbackgroundBean.getOther());
        } else {
            urbanbackground.setOther("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getOthercomment() != null) {
            urbanbackground.setOthercomment(urbanbackgroundBean.getOthercomment());
        } else {
            urbanbackground.setOthercomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setOtherNil(Boolean.FALSE);
        } else {
            urbanbackground.setOtherNil(urbanbackgroundBean.isOther_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getOther_nilreason() != null) {
            urbanbackground.setOtherNilreason(urbanbackgroundBean.getOther_nilreason());
        } else {
            urbanbackground.setOtherNilreason("");
        }

        if (urbanbackgroundBean != null && urbanbackgroundBean.getUnitmisure() != null) {
            urbanbackground.setUnitmisure(urbanbackgroundBean.getUnitmisure());
        } else {
            urbanbackground.setUnitmisure("");
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getUnitmisurecomment() != null) {
            urbanbackground.setUnitmisurecomment(urbanbackgroundBean.getUnitmisurecomment());
        } else {
            urbanbackground.setUnitmisurecomment("");
        }
        if (urbanbackgroundBean == null) {
            urbanbackground.setUnitmisureNil(Boolean.FALSE);
        } else {
            urbanbackground.setUnitmisureNil(urbanbackgroundBean.isUnitmisure_nil());
        }
        if (urbanbackgroundBean != null && urbanbackgroundBean.getUnitmisure_nilreason() != null) {
            urbanbackground.setUnitmisureNilreason(urbanbackgroundBean.getUnitmisure_nilreason());
        } else {
            urbanbackground.setUnitmisureNilreason("");
        }

        em.merge(urbanbackground);
    }

    private void saveRegionalbackground(Regionalbackground regionalbackground, SourceapportionmentBean sourceapportionmentBean, EntityManager em) {
        RegionalbackgroundBean regionalbackgroundBean = sourceapportionmentBean.getRegionalbackgroundBean();

        if (regionalbackgroundBean != null && regionalbackgroundBean.getTotal() != null) {
            regionalbackground.setTotal(regionalbackgroundBean.getTotal());
        } else {
            regionalbackground.setTotal("");
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getTotalcomment() != null) {
            regionalbackground.setTotalcomment(regionalbackgroundBean.getTotalcomment());
        } else {
            regionalbackground.setTotalcomment("");
        }

        if (regionalbackgroundBean == null) {
            regionalbackground.setTotalNil(Boolean.FALSE);
        } else {
            regionalbackground.setTotalNil(regionalbackgroundBean.isTotal_nil());
        }

        if (regionalbackgroundBean != null && regionalbackgroundBean.getTotal_nilreason() != null) {
            regionalbackground.setTotalNilreason(regionalbackgroundBean.getTotal_nilreason());
        } else {
            regionalbackground.setTotalNilreason("");
        }

        if (regionalbackgroundBean != null && regionalbackgroundBean.getFromwithinms() != null) {
            regionalbackground.setFromwithinms(regionalbackgroundBean.getFromwithinms());
        } else {
            regionalbackground.setFromwithinms("");
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getFromwithinmscomment() != null) {
            regionalbackground.setFromwithinmscomment(regionalbackgroundBean.getFromwithinmscomment());
        } else {
            regionalbackground.setFromwithinmscomment("");
        }
        if (regionalbackgroundBean == null) {
            regionalbackground.setFromwithinmsNil(Boolean.FALSE);
        } else {
            regionalbackground.setFromwithinmsNil(regionalbackgroundBean.isFromwithinms_nil());
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getFromwithinms_nilreason() != null) {
            regionalbackground.setFromwithinmsNilreason(regionalbackgroundBean.getFromwithinms_nilreason());
        } else {
            regionalbackground.setFromwithinmsNilreason("");
        }

        if (regionalbackgroundBean != null && regionalbackgroundBean.getNaturalregionalbackground() != null) {
            regionalbackground.setNaturalregionalbackground(regionalbackgroundBean.getNaturalregionalbackground());
        } else {
            regionalbackground.setNaturalregionalbackground("");
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getNaturalregionalbackgroundcomment() != null) {
            regionalbackground.setNaturalregionalbackgroundcomment(regionalbackgroundBean.getNaturalregionalbackgroundcomment());
        } else {
            regionalbackground.setNaturalregionalbackgroundcomment("");
        }
        if (regionalbackgroundBean == null) {
            regionalbackground.setNaturalregionalbackgroundNil(Boolean.FALSE);
        } else {
            regionalbackground.setNaturalregionalbackgroundNil(regionalbackgroundBean.isNaturalregionalbackground_nil());
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getNaturalregionalbackground_nilreason() != null) {
            regionalbackground.setNaturalregionalbackgroundNilreason(regionalbackgroundBean.getNaturalregionalbackground_nilreason());
        } else {
            regionalbackground.setNaturalregionalbackgroundNilreason("");
        }

        if (regionalbackgroundBean != null && regionalbackgroundBean.getTransboundary() != null) {
            regionalbackground.setTransboundary(regionalbackgroundBean.getTransboundary());
        } else {
            regionalbackground.setTransboundary("");
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getTransboundarycomment() != null) {
            regionalbackground.setTransboundarycomment(regionalbackgroundBean.getTransboundarycomment());
        } else {
            regionalbackground.setTransboundarycomment("");
        }
        if (regionalbackgroundBean == null) {
            regionalbackground.setTransboundaryNil(Boolean.FALSE);
        } else {
            regionalbackground.setTransboundaryNil(regionalbackgroundBean.isTransboundary_nil());
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getTransboundary_nilreason() != null) {
            regionalbackground.setTransboundaryNilreason(regionalbackgroundBean.getTransboundary_nilreason());
        } else {
            regionalbackground.setTransboundaryNilreason("");
        }

        if (regionalbackgroundBean != null && regionalbackgroundBean.getOther() != null) {
            regionalbackground.setOther(regionalbackgroundBean.getOther());
        } else {
            regionalbackground.setOther("");
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getOthercomment() != null) {
            regionalbackground.setOthercomment(regionalbackgroundBean.getOthercomment());
        } else {
            regionalbackground.setOthercomment("");
        }
        if (regionalbackgroundBean == null) {
            regionalbackground.setOtherNil(Boolean.FALSE);
        } else {
            regionalbackground.setOtherNil(regionalbackgroundBean.isOther_nil());
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getOther_nilreason() != null) {
            regionalbackground.setOtherNilreason(regionalbackgroundBean.getOther_nilreason());
        } else {
            regionalbackground.setOtherNilreason("");
        }

        if (regionalbackgroundBean != null && regionalbackgroundBean.getUnitmisure() != null) {
            regionalbackground.setUnitmisure(regionalbackgroundBean.getUnitmisure());
        } else {
            regionalbackground.setUnitmisure("");
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getUnitmisurecomment() != null) {
            regionalbackground.setUnitmisurecomment(regionalbackgroundBean.getUnitmisurecomment());
        } else {
            regionalbackground.setUnitmisurecomment("");
        }
        if (regionalbackgroundBean == null) {
            regionalbackground.setUnitmisureNil(Boolean.FALSE);
        } else {
            regionalbackground.setUnitmisureNil(regionalbackgroundBean.isUnitmisure_nil());
        }
        if (regionalbackgroundBean != null && regionalbackgroundBean.getUnitmisure_nilreason() != null) {
            regionalbackground.setUnitmisureNilreason(regionalbackgroundBean.getUnitmisure_nilreason());
        } else {
            regionalbackground.setUnitmisureNilreason("");
        }

        em.merge(regionalbackground);
    }

    private void saveLocalincrement(Localincrement localincrement, SourceapportionmentBean sourceapportionmentBean, EntityManager em) {
        LocalincrementBean localincrementBean = sourceapportionmentBean.getLocalincrementBean();

        if (localincrementBean != null && localincrementBean.getTotal() != null) {
            localincrement.setTotal(localincrementBean.getTotal());
        } else {
            localincrement.setTotal("");
        }
        if (localincrementBean != null && localincrementBean.getTotalcomment() != null) {
            localincrement.setTotalcomment(localincrementBean.getTotalcomment());
        } else {
            localincrement.setTotalcomment("");
        }
        if (localincrementBean == null) {
            localincrement.setTotalNil(Boolean.FALSE);
        } else {
            localincrement.setTotalNil(localincrementBean.isTotal_nil());
        }
        if (localincrementBean != null && localincrementBean.getTotal_nilreason() != null) {
            localincrement.setTotalNilreason(localincrementBean.getTotal_nilreason());
        } else {
            localincrement.setTotalNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getTraffic() != null) {
            localincrement.setTraffic(localincrementBean.getTraffic());
        } else {
            localincrement.setTraffic("");
        }
        if (localincrementBean != null && localincrementBean.getTrafficcomment() != null) {
            localincrement.setTrafficcomment(localincrementBean.getTrafficcomment());
        } else {
            localincrement.setTrafficcomment("");
        }
        if (localincrementBean == null) {
            localincrement.setTrafficNil(Boolean.FALSE);
        } else {
            localincrement.setTrafficNil(localincrementBean.isTraffic_nil());
        }
        if (localincrementBean != null && localincrementBean.getTraffic_nilreason() != null) {
            localincrement.setTrafficNilreason(localincrementBean.getTraffic_nilreason());
        } else {
            localincrement.setTrafficNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getHeatandpowerproduction() != null) {
            localincrement.setHeatandpowerproduction(localincrementBean.getHeatandpowerproduction());
        } else {
            localincrement.setHeatandpowerproduction("");
        }
        if (localincrementBean != null && localincrementBean.getHeatandpowerproductioncomment() != null) {
            localincrement.setHeatandpowerproductioncomment(localincrementBean.getHeatandpowerproductioncomment());
        } else {
            localincrement.setHeatandpowerproductioncomment("");
        }
        if (localincrementBean == null) {
            localincrement.setHeatandpowerproductionNil(Boolean.FALSE);
        } else {
            localincrement.setHeatandpowerproductionNil(localincrementBean.isHeatandpowerproduction_nil());
        }
        if (localincrementBean != null && localincrementBean.getHeatandpowerproduction_nilreason() != null) {
            localincrement.setHeatandpowerproductionNilreason(localincrementBean.getHeatandpowerproduction_nilreason());
        } else {
            localincrement.setHeatandpowerproductionNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getAgriculture() != null) {
            localincrement.setAgriculture(localincrementBean.getAgriculture());
        } else {
            localincrement.setAgriculture("");
        }
        if (localincrementBean != null && localincrementBean.getAgriculturecomment() != null) {
            localincrement.setAgriculturecomment(localincrementBean.getAgriculturecomment());
        } else {
            localincrement.setAgriculturecomment("");
        }
        if (localincrementBean == null) {
            localincrement.setAgricultureNil(Boolean.FALSE);
        } else {
            localincrement.setAgricultureNil(localincrementBean.isAgriculture_nil());
        }
        if (localincrementBean != null && localincrementBean.getAgriculture_nilreason() != null) {
            localincrement.setAgricultureNilreason(localincrementBean.getAgriculture_nilreason());
        } else {
            localincrement.setAgricultureNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getCommercialandresidential() != null) {
            localincrement.setCommercialandresidential(localincrementBean.getCommercialandresidential());
        } else {
            localincrement.setCommercialandresidential("");
        }
        if (localincrementBean != null && localincrementBean.getCommercialandresidentialcomment() != null) {
            localincrement.setCommercialandresidentialcomment(localincrementBean.getCommercialandresidentialcomment());
        } else {
            localincrement.setCommercialandresidentialcomment("");
        }
        if (localincrementBean == null) {
            localincrement.setCommercialandresidentialNil(Boolean.FALSE);
        } else {
            localincrement.setCommercialandresidentialNil(localincrementBean.isCommercialandresidential_nil());
        }
        if (localincrementBean != null && localincrementBean.getCommercialandresidential_nilreason() != null) {
            localincrement.setCommercialandresidentialNilreason(localincrementBean.getCommercialandresidential_nilreason());
        } else {
            localincrement.setCommercialandresidentialNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getShipping() != null) {
            localincrement.setShipping(localincrementBean.getShipping());
        } else {
            localincrement.setShipping("");
        }
        if (localincrementBean != null && localincrementBean.getShippingcomment() != null) {
            localincrement.setShippingcomment(localincrementBean.getShippingcomment());
        } else {
            localincrement.setShippingcomment("");
        }
        if (localincrementBean == null) {
            localincrement.setShippingNil(Boolean.FALSE);
        } else {
            localincrement.setShippingNil(localincrementBean.isShipping_nil());
        }
        if (localincrementBean != null && localincrementBean.getShipping_nilreason() != null) {
            localincrement.setShippingNilreason(localincrementBean.getShipping_nilreason());
        } else {
            localincrement.setShippingNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getOffroadmobilemachinery() != null) {
            localincrement.setOffroadmobilemachinery(localincrementBean.getOffroadmobilemachinery());
        } else {
            localincrement.setOffroadmobilemachinery("");
        }
        if (localincrementBean != null && localincrementBean.getOffroadmobilemachinerycomment() != null) {
            localincrement.setOffroadmobilemachinerycomment(localincrementBean.getOffroadmobilemachinerycomment());
        } else {
            localincrement.setOffroadmobilemachinerycomment("");
        }
        if (localincrementBean == null) {
            localincrement.setOffroadmobilemachineryNil(Boolean.FALSE);
        } else {
            localincrement.setOffroadmobilemachineryNil(localincrementBean.isOffroadmobilemachinery_nil());
        }
        if (localincrementBean != null && localincrementBean.getOffroadmobilemachinery_nilreason() != null) {
            localincrement.setOffroadmobilemachineryNilreason(localincrementBean.getOffroadmobilemachinery_nilreason());
        } else {
            localincrement.setOffroadmobilemachineryNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getNaturallocalincrement() != null) {
            localincrement.setNaturallocalincrement(localincrementBean.getNaturallocalincrement());
        } else {
            localincrement.setNaturallocalincrement("");
        }
        if (localincrementBean != null && localincrementBean.getNaturallocalincrementcomment() != null) {
            localincrement.setNaturallocalincrementcomment(localincrementBean.getNaturallocalincrementcomment());
        } else {
            localincrement.setNaturallocalincrementcomment("");
        }
        if (localincrementBean == null) {
            localincrement.setNaturallocalincrementNil(Boolean.FALSE);
        } else {
            localincrement.setNaturallocalincrementNil(localincrementBean.isNaturallocalincrement_nil());
        }
        if (localincrementBean != null && localincrementBean.getNaturallocalincrement_nilreason() != null) {
            localincrement.setNaturallocalincrementNilreason(localincrementBean.getNaturallocalincrement_nilreason());
        } else {
            localincrement.setNaturallocalincrementNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getTransboundary() != null) {
            localincrement.setTransboundary(localincrementBean.getTransboundary());
        } else {
            localincrement.setTransboundary("");
        }
        if (localincrementBean != null && localincrementBean.getTransboundarycomment() != null) {
            localincrement.setTransboundarycomment(localincrementBean.getTransboundarycomment());
        } else {
            localincrement.setTransboundarycomment("");
        }
        if (localincrementBean == null) {
            localincrement.setTransboundaryNil(Boolean.FALSE);
        } else {
            localincrement.setTransboundaryNil(localincrementBean.isTransboundary_nil());
        }
        if (localincrementBean != null && localincrementBean.getTransboundary_nilreason() != null) {
            localincrement.setTransboundaryNilreason(localincrementBean.getTransboundary_nilreason());
        } else {
            localincrement.setTransboundaryNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getOther() != null) {
            localincrement.setOther(localincrementBean.getOther());
        } else {
            localincrement.setOther("");
        }
        if (localincrementBean != null && localincrementBean.getOthercomment() != null) {
            localincrement.setOthercomment(localincrementBean.getOthercomment());
        } else {
            localincrement.setOthercomment("");
        }
        if (localincrementBean == null) {
            localincrement.setOtherNil(Boolean.FALSE);
        } else {
            localincrement.setOtherNil(localincrementBean.isOther_nil());
        }
        if (localincrementBean != null && localincrementBean.getOther_nilreason() != null) {
            localincrement.setOtherNilreason(localincrementBean.getOther_nilreason());
        } else {
            localincrement.setOtherNilreason("");
        }

        if (localincrementBean != null && localincrementBean.getUnitmisure() != null) {
            localincrement.setUnitmisure(localincrementBean.getUnitmisure());
        } else {
            localincrement.setUnitmisure("");
        }
        if (localincrementBean != null && localincrementBean.getUnitmisurecomment() != null) {
            localincrement.setUnitmisurecomment(localincrementBean.getUnitmisurecomment());
        } else {
            localincrement.setUnitmisurecomment("");
        }
        if (localincrementBean == null) {
            localincrement.setUnitmisureNil(Boolean.FALSE);
        } else {
            localincrement.setUnitmisureNil(localincrementBean.isUnitmisure_nil());
        }
        if (localincrementBean != null && localincrementBean.getUnitmisure_nilreason() != null) {
            localincrement.setUnitmisureNilreason(localincrementBean.getUnitmisure_nilreason());
        } else {
            localincrement.setUnitmisureNilreason("");
        }

        em.merge(localincrement);
    }

    private void saveExceedancedescription(Exceedancedescription exceedancedescription, SourceapportionmentBean sourceapportionmentBean, EntityManager em) throws Exception {
        ExceedancedescriptionBean exceedancedescriptionBean = sourceapportionmentBean.getExceedancedescriptionBean();

        if (exceedancedescriptionBean == null || exceedancedescriptionBean.getExceedance() == null) {
            exceedancedescription.setExceedance(Boolean.FALSE);
        } else {
            exceedancedescription.setExceedance(exceedancedescriptionBean.getExceedance());
        }

        if (exceedancedescriptionBean != null && exceedancedescriptionBean.getNumericalexceedance() != null) {
            exceedancedescription.setNumericalexceedance(exceedancedescriptionBean.getNumericalexceedance());
        } else {
            exceedancedescription.setNumericalexceedance("");
        }
        if (exceedancedescriptionBean != null && exceedancedescriptionBean.getNumberexceedances() != null) {
            exceedancedescription.setNumberexceedances(exceedancedescriptionBean.getNumberexceedances());
        } else {
            exceedancedescription.setNumberexceedances("");
        }

        if (exceedancedescriptionBean != null && exceedancedescriptionBean.getOtherreason() != null) {
            exceedancedescription.setOtherreason(exceedancedescriptionBean.getOtherreason());
        } else {
            exceedancedescription.setOtherreason("");
        }

        if (exceedancedescriptionBean != null && exceedancedescriptionBean.getComment() != null) {
            exceedancedescription.setComment(exceedancedescriptionBean.getComment());
        } else {
            exceedancedescription.setComment("");
        }

        exceedancedescription.setDatelastupdate(new Date());

        em.merge(exceedancedescription);

        Query q = em.createNamedQuery("ExceedancedescriptionReasonvalue.findByExceedancedescription");
        q.setParameter("exceedancedescription", exceedancedescription);
        List<ExceedancedescriptionReasonvalue> exceedancedescriptionReasonvalueListToDelete = q.getResultList();
        if (exceedancedescriptionReasonvalueListToDelete.size() > 0) {
            for (ExceedancedescriptionReasonvalue exceedancedescriptionReasonvalue : exceedancedescriptionReasonvalueListToDelete) {
                q = em.createNamedQuery("ExceedancedescriptionReasonvalue.deleteByUuid");
                q.setParameter("uuid", exceedancedescriptionReasonvalue.getUuid());
                q.executeUpdate();
            }
        }

        if (exceedancedescriptionBean != null) {
            List<String> reasonvalueList_uriList = exceedancedescriptionBean.getReasonvalueList_uri();
            if (reasonvalueList_uriList != null) {
                /**
                 * delete all the other links for that plan
                 */

                for (String reasonvalueUri : reasonvalueList_uriList) {
                    q = em.createNamedQuery("Reasonvalue.findByUri");
                    q.setParameter("uri", reasonvalueUri);

                    Reasonvalue reasonvalue = (Reasonvalue) q.getSingleResult();

                    ExceedancedescriptionReasonvalue exceedancedescriptionReasonvalue = new ExceedancedescriptionReasonvalue();
                    String exceedancedescriptionReasonvalueUuid = StringUtils.createUUID(reasonvalue.getUuid() + exceedancedescription.getUuid() + dateFormatUtil.getToday(), ExceedancedescriptionReasonvalue.class
                    );
                    exceedancedescriptionReasonvalue.setUuid(exceedancedescriptionReasonvalueUuid);
                    exceedancedescriptionReasonvalue.setExceedancedescription(exceedancedescription);
                    exceedancedescriptionReasonvalue.setReasonvalue(reasonvalue);
                    em.persist(exceedancedescriptionReasonvalue);
                }
            }
        }

        saveDeductionassessmentmethod(exceedancedescription, exceedancedescriptionBean, em);
        saveExceedancearea(exceedancedescription, exceedancedescriptionBean, em);
        saveExceedanceexposure(exceedancedescription, exceedancedescriptionBean, em);

    }

    private void saveDeductionassessmentmethod(Exceedancedescription exceedancedescription, ExceedancedescriptionBean exceedancedescriptionBean, EntityManager em) throws Exception {
        Deductionassessmentmethod deductionassessmentmethod = exceedancedescription.getDeductionassessmentmethod();
        if (exceedancedescriptionBean != null) {

            DeductionassessmentmethodBean deductionassessmentmethodBean = exceedancedescriptionBean.getDeductionassessmentmethodBean();

            if (deductionassessmentmethodBean != null) {
                String adjustmenttype_uri = deductionassessmentmethodBean.getAdjustmenttype_uri();
                if (adjustmenttype_uri != null) {
                    Query q = em.createNamedQuery("Adjustmenttype.findByUri");
                    q.setParameter("uri", adjustmenttype_uri);
                    Adjustmenttype adjustmenttype = (Adjustmenttype) q.getSingleResult();

                    deductionassessmentmethod.setAdjustmenttype(adjustmenttype);
                } else {
                    deductionassessmentmethod.setAdjustmenttype(null);
                }

                Query q = em.createNamedQuery("DeductionassessmentmethodAdjustmentsource.deleteByDeductionassessmentmethod");
                q.setParameter("deductionassessmentmethod", deductionassessmentmethod);
                q.executeUpdate();

                List<String> adjustmentsourceList_uri = deductionassessmentmethodBean.getAdjustmentsourceList_uri();
                if (adjustmentsourceList_uri != null) {
                    /**
                     * delete all the other links for that plan
                     */

                    for (String adjustmentsourceUri : adjustmentsourceList_uri) {
                        q = em.createNamedQuery("Adjustmentsource.findByUri");
                        q.setParameter("uri", adjustmentsourceUri);

                        Adjustmentsource adjustmentsource = (Adjustmentsource) q.getSingleResult();

                        DeductionassessmentmethodAdjustmentsource deductionassessmentmethodAdjustmentsource = new DeductionassessmentmethodAdjustmentsource();
                        String deductionassessmentmethodAdjustmentsourceUuid = StringUtils.createUUID(adjustmentsource.getUuid() + deductionassessmentmethod.getUuid() + dateFormatUtil.getToday(), DeductionassessmentmethodAdjustmentsource.class
                        );
                        deductionassessmentmethodAdjustmentsource.setUuid(deductionassessmentmethodAdjustmentsourceUuid);
                        deductionassessmentmethodAdjustmentsource.setAdjustmentsource(adjustmentsource);
                        deductionassessmentmethodAdjustmentsource.setDeductionassessmentmethod(deductionassessmentmethod);
                        em.persist(deductionassessmentmethodAdjustmentsource);
                    }
                }

                em.merge(deductionassessmentmethod);
            }
        } else {
            deductionassessmentmethod.setAdjustmenttype(null);

            Query q = em.createNamedQuery("DeductionassessmentmethodAdjustmentsource.deleteByDeductionassessmentmethod");
            q.setParameter("deductionassessmentmethod", deductionassessmentmethod);
            q.executeUpdate();

            em.merge(deductionassessmentmethod);
        }
    }

    private void saveExceedancearea(Exceedancedescription exceedancedescription, ExceedancedescriptionBean exceedancedescriptionBean, EntityManager em) throws Exception {
        Exceedancearea exceedancearea = exceedancedescription.getExceedancearea();
        if (exceedancedescriptionBean != null) {
            ExceedanceareaBean exceedanceareaBean = exceedancedescriptionBean.getExceedanceareaBean();

            if (exceedanceareaBean != null) {
                if (exceedanceareaBean.getArea() != null) {
                    exceedancearea.setArea(exceedanceareaBean.getArea());
                } else {
                    exceedancearea.setArea("");
                }

                if (exceedanceareaBean.getAreaestimate() != null) {
                    exceedancearea.setAreaestimate(exceedanceareaBean.getAreaestimate());
                } else {
                    exceedancearea.setAreaestimate("");
                }

                if (exceedanceareaBean.getRoadlenghtestimate() != null) {
                    exceedancearea.setRoadlenghtestimate(exceedanceareaBean.getRoadlenghtestimate());
                } else {
                    exceedancearea.setRoadlenghtestimate("");
                }

                if (exceedanceareaBean.getAdministrativeunits() != null) {
                    exceedancearea.setAdministrativeunits(exceedanceareaBean.getAdministrativeunits());
                } else {
                    exceedancearea.setAdministrativeunits("");
                }

                if (exceedanceareaBean.getModelused() != null) {
                    exceedancearea.setModelused(exceedanceareaBean.getModelused());
                } else {
                    exceedancearea.setModelused("");
                }

                if (exceedanceareaBean.getStationused() != null) {
                    exceedancearea.setStationused(exceedanceareaBean.getStationused());
                } else {
                    exceedancearea.setStationused("");
                }

                List<String> areaclassificationList_uri = exceedanceareaBean.getAreaclassificationList_uri();
                if (areaclassificationList_uri != null) {
                    /**
                     * delete all the other links for that plan
                     */
                    Query q = em.createNamedQuery("ExceedenceareaAreaclassification.deleteByExceedancearea");
                    q.setParameter("exceedancearea", exceedancearea);
                    q.executeUpdate();

                    for (String areaclassificationUri : areaclassificationList_uri) {
                        q = em.createNamedQuery("Areaclassification.findByUri");
                        q.setParameter("uri", areaclassificationUri);

                        Areaclassification areaclassification = (Areaclassification) q.getSingleResult();

                        ExceedenceareaAreaclassification exceedenceareaAreaclassification = new ExceedenceareaAreaclassification();
                        String exceedenceareaAreaclassificationUuid = StringUtils.createUUID(areaclassification.getUuid() + exceedancearea.getUuid() + dateFormatUtil.getToday(), ExceedenceareaAreaclassification.class
                        );
                        exceedenceareaAreaclassification.setUuid(exceedenceareaAreaclassificationUuid);
                        exceedenceareaAreaclassification.setExceedancearea(exceedancearea);
                        exceedenceareaAreaclassification.setAreaclassification(areaclassification);
                        em.persist(exceedenceareaAreaclassification);
                    }
                } else {
                    Query q = em.createNamedQuery("ExceedenceareaAreaclassification.deleteByExceedancearea");
                    q.setParameter("exceedancearea", exceedancearea);
                    q.executeUpdate();
                }

                em.merge(exceedancearea);
            } else {
                exceedancearea.setArea("");
                exceedancearea.setAreaestimate("");
                exceedancearea.setRoadlenghtestimate("");
                exceedancearea.setAdministrativeunits("");
                exceedancearea.setModelused("");
                exceedancearea.setStationused("");

                Query q = em.createNamedQuery("ExceedenceareaAreaclassification.deleteByExceedancearea");
                q.setParameter("exceedancearea", exceedancearea);
                q.executeUpdate();

                em.merge(exceedancearea);
            }
        } else {
            exceedancearea.setArea("");
            exceedancearea.setAreaestimate("");
            exceedancearea.setRoadlenghtestimate("");
            exceedancearea.setAdministrativeunits("");
            exceedancearea.setModelused("");
            exceedancearea.setStationused("");

            Query q = em.createNamedQuery("ExceedenceareaAreaclassification.deleteByExceedancearea");
            q.setParameter("exceedancearea", exceedancearea);
            q.executeUpdate();

            em.merge(exceedancearea);
        }
    }

    private void saveExceedanceexposure(Exceedancedescription exceedancedescription, ExceedancedescriptionBean exceedancedescriptionBean, EntityManager em) {
        Exceedanceexposure exceedanceexposure = exceedancedescription.getExceedanceexposure();
        if (exceedancedescriptionBean != null) {
            ExceedanceexposureBean exceedenceexposureBean = exceedancedescriptionBean.getExceedenceexposureBean();

            if (exceedenceexposureBean != null && exceedenceexposureBean.getExposedpopulation() != null) {
                exceedanceexposure.setExposedpopulation(exceedenceexposureBean.getExposedpopulation());
            } else {
                exceedanceexposure.setExposedpopulation("");
            }

            if (exceedenceexposureBean != null && exceedenceexposureBean.getExposedarea() != null) {
                exceedanceexposure.setExposedarea(exceedenceexposureBean.getExposedarea());
            } else {
                exceedanceexposure.setExposedarea("");
            }

            if (exceedenceexposureBean != null && exceedenceexposureBean.getSensitiveresidentpopulation() != null) {
                exceedanceexposure.setSensitiveresidentpopulation(exceedenceexposureBean.getSensitiveresidentpopulation());
            } else {
                exceedanceexposure.setSensitiveresidentpopulation("");
            }

            if (exceedenceexposureBean != null && exceedenceexposureBean.getRelevantinfrastructure() != null) {
                exceedanceexposure.setRelevantinfrastructure(exceedenceexposureBean.getRelevantinfrastructure());
            } else {
                exceedanceexposure.setRelevantinfrastructure("");
            }

            if (exceedenceexposureBean != null && exceedenceexposureBean.getReferenceyear() != null) {
                exceedanceexposure.setReferenceyear(exceedenceexposureBean.getReferenceyear());
            } else {
                exceedanceexposure.setReferenceyear("");
            }

            em.merge(exceedanceexposure);
        } else {
            exceedanceexposure.setExposedpopulation("");
            exceedanceexposure.setExposedarea("");
            exceedanceexposure.setSensitiveresidentpopulation("");
            exceedanceexposure.setRelevantinfrastructure("");
            exceedanceexposure.setReferenceyear("");

            em.merge(exceedanceexposure);
        }
    }

    /**
     *
     * @param sourceapportionmentINSPIRELocalID
     * @param userEmail
     * @return the SourceapportionmentBean
     */
    public SourceapportionmentBean getSourceapportionmentByINSPIRELocalID(String sourceapportionmentINSPIRELocalID, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Users.findByEmail");
        q.setParameter("email", userEmail);
        Users userSourceapportionment = (Users) q.getSingleResult();
        Country country = userSourceapportionment.getCountry();

        try {
            q = em.createNamedQuery("Sourceapportionment.findByInspireidLocalid");
            q.setParameter("inspireidLocalid", sourceapportionmentINSPIRELocalID);
            List<Sourceapportionment> sourceapportionmentList = q.getResultList();

            Sourceapportionment sourceapportionmentExistingInThatCountry = null;
            for (Sourceapportionment sourceapportionment : sourceapportionmentList) {
                if (sourceapportionment.getUsers().getCountry().equals(country)) {
                    sourceapportionmentExistingInThatCountry = sourceapportionment;
                    break;
                }
            }

            if (sourceapportionmentExistingInThatCountry != null) {
                return SourceapportionmentWrapper.convertSourceapportionmentInSourceapportionmentBean(sourceapportionmentExistingInThatCountry, userSourceapportionment);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Change the status of the Plan
     *
     * @param sourceapportionmentID
     * @param completed
     */
    public void setCompletnessBySourceapportionmentID(String sourceapportionmentID, boolean completed) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Sourceapportionment.findByUuid");
        q.setParameter("uuid", sourceapportionmentID);
        Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();

        emc.beginTransaction(em);
        if (completed) {
            sourceapportionment.setCompleted(Boolean.TRUE);
        } else {
            sourceapportionment.setCompleted(Boolean.FALSE);
        }

        em.merge(sourceapportionment);

        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @return all the Reasonvalue
     */
    public List<ReasonvalueBean> getAllExceedancereason() {
        List<ReasonvalueBean> reasonvalueBeanList = new ArrayList<ReasonvalueBean>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Reasonvalue.findAll");
        List<Reasonvalue> reasonvalueList = q.getResultList();

        for (Reasonvalue reasonvalue : reasonvalueList) {
            reasonvalueBeanList.add(ReasonvalueWrapper.convertReasonvalueInReasonvalueBean(reasonvalue));
        }
        return reasonvalueBeanList;
    }

    /**
     *
     * @return all the Areaclassification
     */
    public List<AreaclassificationBean> getAllAreaclassification() {
        List<AreaclassificationBean> areaclassificationBeanList = new ArrayList<AreaclassificationBean>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Areaclassification.findAll");
        List<Areaclassification> adjustmentsourceList = q.getResultList();

        for (Areaclassification areaclassification : adjustmentsourceList) {
            areaclassificationBeanList.add(AreaclassificationWrapper.convertAreaclassificationInAreaclassificationBean(areaclassification));
        }
        return areaclassificationBeanList;
    }

    /**
     *
     * @return all the Adjustmentsource
     */
    public List<AdjustmentsourceBean> getAllAdjustmentSource() {
        List<AdjustmentsourceBean> adjustmentsourceBeanList = new ArrayList<AdjustmentsourceBean>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Adjustmentsource.findAll");
        List<Adjustmentsource> adjustmentsourceList = q.getResultList();

        for (Adjustmentsource adjustmentsource : adjustmentsourceList) {
            adjustmentsourceBeanList.add(AdjustmentsourceWrapper.convertAdjustmentsourceInAdjustmentsourceBean(adjustmentsource));
        }
        return adjustmentsourceBeanList;
    }

    /**
     *
     * @return all the Adjustmenttype
     */
    public List<AdjustmenttypeBean> getAllAdjustmentType() {
        List<AdjustmenttypeBean> adjustmenttypeBeanList = new ArrayList<AdjustmenttypeBean>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Adjustmenttype.findAll");
        List<Adjustmenttype> adjustmenttypeList = q.getResultList();

        for (Adjustmenttype adjustmenttype : adjustmenttypeList) {
            adjustmenttypeBeanList.add(AdjustmenttypeWrapper.convertAdjustmenttypeInAdjustmenttypeBean(adjustmenttype));
        }
        return adjustmenttypeBeanList;
    }

    /**
     *
     * @return List<AssesmenttypeBean>
     */
    public List<AssesmenttypeBean> getAllAssesmenttype() {
        List<AssesmenttypeBean> assesmenttypeBeanBeanList = new ArrayList<AssesmenttypeBean>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Assesmenttype.findAll");
        List<Assesmenttype> adjustmenttypeList = q.getResultList();

        for (Assesmenttype adjustmenttype : adjustmenttypeList) {
            assesmenttypeBeanBeanList.add(AssesmenttypeWrapper.convertAdjustmenttypeInAdjustmenttypeBean(adjustmenttype));
        }
        return assesmenttypeBeanBeanList;
    }

    /**
     * return all the DeductionassessmentmethodAdjustmentsource
     *
     * @param deductionassessmentmethod
     * @return a list of Adjustmentsource
     */
    public List<Adjustmentsource> getAdjustmentsourceListByDeductionassessmentmethod(Deductionassessmentmethod deductionassessmentmethod) {
        List<Adjustmentsource> adjustmentsourceList = new ArrayList<Adjustmentsource>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("DeductionassessmentmethodAdjustmentsource.findByDeductionassessmentmethod");
        q.setParameter("deductionassessmentmethod", deductionassessmentmethod);
        List<DeductionassessmentmethodAdjustmentsource> deductionassessmentmethodAdjustmentsourceList = q.getResultList();

        for (DeductionassessmentmethodAdjustmentsource deductionassessmentmethodAdjustmentsource : deductionassessmentmethodAdjustmentsourceList) {
            adjustmentsourceList.add(deductionassessmentmethodAdjustmentsource.getAdjustmentsource());
        }
        return adjustmentsourceList;
    }

    /**
     *
     * @param deductionassessmentmethod
     * @return a list of Assesmentmethods
     */
    public List<Assesmentmethods> getAssesmentmethodsListByDeductionassessmentmethod(Deductionassessmentmethod deductionassessmentmethod) {
        List<Assesmentmethods> assesmentmethodsList = new ArrayList<Assesmentmethods>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("DeductionassessmentmethodAssesmentmethods.findByDeductionassessmentmethod");
        q.setParameter("deductionassessmentmethod", deductionassessmentmethod);
        List<DeductionassessmentmethodAssesmentmethods> deductionassessmentmethodAssesmentmethodsList = q.getResultList();

        for (DeductionassessmentmethodAssesmentmethods deductionassessmentmethodAssesmentmethods : deductionassessmentmethodAssesmentmethodsList) {
            assesmentmethodsList.add(deductionassessmentmethodAssesmentmethods.getAssesmentmethods());
        }
        return assesmentmethodsList;
    }

    /**
     *
     * @param exceedancearea
     * @return list of Areaclassification
     */
    public List<Areaclassification> getAreaclassificationListByExceedancearea(Exceedancearea exceedancearea) {
        List<Areaclassification> areaclassificationList = new ArrayList<Areaclassification>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("ExceedenceareaAreaclassification.findByExceedancearea");
        q.setParameter("exceedancearea", exceedancearea);
        List<ExceedenceareaAreaclassification> exceedenceareaAreaclassificationList = q.getResultList();

        for (ExceedenceareaAreaclassification exceedenceareaAreaclassification : exceedenceareaAreaclassificationList) {
            areaclassificationList.add(exceedenceareaAreaclassification.getAreaclassification());
        }
        return areaclassificationList;

    }

    /**
     *
     * @param exceedancedescription
     * @return list of Reasonvalue
     */
    public List<Reasonvalue> getReasonvalueListByExceedancedescription(Exceedancedescription exceedancedescription) {
        List<Reasonvalue> reasonvalueList = new ArrayList<Reasonvalue>();
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("ExceedancedescriptionReasonvalue.findByExceedancedescription");
        q.setParameter("exceedancedescription", exceedancedescription);
        List<ExceedancedescriptionReasonvalue> exceedancedescriptionReasonvalueList = q.getResultList();

        for (ExceedancedescriptionReasonvalue exceedancedescriptionReasonvalue : exceedancedescriptionReasonvalueList) {
            reasonvalueList.add(exceedancedescriptionReasonvalue.getReasonvalue());
        }
        return reasonvalueList;

    }

    /**
     *
     * @param sourceapportionmentID
     * @return a list of all the uploaded AttainmentBean
     */
    public List<AttainmentBean> getAllAttainmentBeanByUser(String sourceapportionmentID) {
        List<AttainmentBean> attainmentBeanList = new ArrayList<AttainmentBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Sourceapportionment.findByUuid");
        q.setParameter("uuid", sourceapportionmentID);
        Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();

        q = em.createNamedQuery("Users.findByEmail");
        q.setParameter("email", sourceapportionment.getUsers().getEmail());
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

}
