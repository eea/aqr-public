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

import eu.europa.ec.measures.timescale.TimescaleBean;
import eu.europa.ec.measures.statusplannedimplementation.StatusplannedimplementationBean;
import eu.europa.ec.measures.specificationofhours.SpecificationofhoursBean;
import eu.europa.ec.measures.sourcesectors.SourcesectorsBean;
import eu.europa.ec.measures.spatialscale.SpatialscaleBean;
import eu.europa.ec.measures.quantificationnumerical.QuantificationnumericalBean;
import eu.europa.ec.measures.measuretype.MeasuretypeBean;
import eu.europa.ec.measures.plannedimplementation.PlannedimplementationBean;
import eu.europa.ec.measures.expectedimpact.ExpectedimpactBean;
import eu.europa.ec.measures.currencies.CurrenciesBean;
import eu.europa.ec.measures.cost.CostsBean;
import eu.europa.ec.measures.administrativelevel.AdministrativelevelBean;
import eu.europa.ec.measures.classification.ClassificationBean;
import eu.europa.ec.measures.classification.ClassificationWrapper;
import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresAdministrationlevel;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresClassification;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresEvaluationscenario;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresSourcesector;
import eu.europa.ec.aqrcrosstablesmodel.MeasuresSpatialscale;
import eu.europa.ec.aqrcrosstablesmodel.SourceapportionmentMeasures;
import eu.europa.ec.evaluationscenario.EvaluationscenarioBean;
import eu.europa.ec.evaluationscenario.EvaluationscenarioWrapper;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentWrapper;
import eu.europa.ec.aqrmodel.Administrationlevel;
import eu.europa.ec.aqrmodel.Classification;
import eu.europa.ec.aqrmodel.Costs;
import eu.europa.ec.aqrmodel.Currency;
import eu.europa.ec.aqrmodel.Evaluationscenario;
import eu.europa.ec.aqrmodel.Expectedimpact;
import eu.europa.ec.aqrmodel.Measures;
import eu.europa.ec.aqrmodel.Measuretype;
import eu.europa.ec.aqrmodel.Plannedimplementation;
import eu.europa.ec.aqrmodel.Quantificationnumerical;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodel.Sourceapportionment;
import eu.europa.ec.aqrmodel.Sourcesector;
import eu.europa.ec.aqrmodel.Spatialscale;
import eu.europa.ec.aqrmodel.Specificationofhours;
import eu.europa.ec.aqrmodel.Statusplannedimplementation;
import eu.europa.ec.aqrmodel.Timescale;
import eu.europa.ec.aqrmodeluser.Country;
import eu.europa.ec.aqrmodeluser.Systemconfiguration;
import eu.europa.ec.aqrmodeluser.Userrole;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.measures.administrativelevel.AdministrativelevelWrapper;
import eu.europa.ec.measures.currencies.CurrenciesWrapper;
import eu.europa.ec.measures.measuretype.MeasuretypeWrapper;
import eu.europa.ec.measures.quantificationnumerical.QuantificationnumericalWrapper;
import eu.europa.ec.measures.sourcesectors.SourcesectorsWrapper;
import eu.europa.ec.measures.spatialscale.SpatialscaleWrapper;
import eu.europa.ec.measures.specificationofhours.SpecificationofhoursWrapper;
import eu.europa.ec.measures.statusplannedimplementation.StatusplannedimplementationWrapper;
import eu.europa.ec.measures.timescale.TimescaleWrapper;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.EntityManagerCustom;
import eu.europa.ec.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MeasuresManager {

    private final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public MeasuresManager() {
    }

    /**
     *
     * @return the complete list of ClassificationBean
     */
    public List<ClassificationBean> getAllClassification() {
        List<ClassificationBean> classificationBeanList = new ArrayList<ClassificationBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Classification.findAll");
        List<Classification> results = q.getResultList();
        for (Classification classification : results) {
            classificationBeanList.add(ClassificationWrapper.convertClassificationInClassificationBean(classification));
        }
        em.close();
        return classificationBeanList;
    }

    /**
     *
     * @param measuresID
     * @return the list of selected ClassificationBean for the measures
     */
    public List<ClassificationBean> getSelectedClassificationByMeasuresID(String measuresID) {
        List<ClassificationBean> classificationBeanList = new ArrayList<ClassificationBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresClassification.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresClassification> results = q.getResultList();
        for (MeasuresClassification measuresClassification : results) {
            classificationBeanList.add(ClassificationWrapper.convertClassificationInClassificationBean(measuresClassification.getClassification()));
        }
        em.close();
        return classificationBeanList;
    }

    private List<MeasuresClassification> getMeasuresClassificationByMeasuresID(String measuresID) {
        List<MeasuresClassification> measuresClassificationList = new ArrayList<MeasuresClassification>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresClassification.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresClassification> results = q.getResultList();
        for (MeasuresClassification measuresClassification : results) {
            measuresClassificationList.add(measuresClassification);
        }
        em.close();
        return measuresClassificationList;
    }

    /**
     *
     * @return all the MeasuretypeBean
     */
    public List<MeasuretypeBean> getAllMeasuretype() {
        List<MeasuretypeBean> measuretypeBeanList = new ArrayList<MeasuretypeBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measuretype.findAll");
        List<Measuretype> results = q.getResultList();
        for (Measuretype measuretype : results) {
            measuretypeBeanList.add(MeasuretypeWrapper.convertMeasuretypeInMeasuretypeBean(measuretype));
        }
        em.close();
        return measuretypeBeanList;
    }

    /**
     *
     * @param measuresID
     * @return the selected MeasuretypeBean for the measures
     */
    public MeasuretypeBean getSelectedMeasuretypeByMeasuresID(String measuresID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        Measuretype measuretype = measures.getMeasuretype();
        em.close();
        return MeasuretypeWrapper.convertMeasuretypeInMeasuretypeBean(measuretype);
    }

    /**
     *
     * @return all the AdministrativelevelBean
     */
    public List<AdministrativelevelBean> getAllAdministrativelevel() {
        List<AdministrativelevelBean> administrativelevelBeanList = new ArrayList<AdministrativelevelBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Administrationlevel.findAll");
        List<Administrationlevel> results = q.getResultList();
        for (Administrationlevel administrativelevel : results) {
            administrativelevelBeanList.add(AdministrativelevelWrapper.convertAdministrativelevelInAdministrativelevelBean(administrativelevel));
        }
        em.close();
        return administrativelevelBeanList;
    }

    private List<MeasuresAdministrationlevel> getMeasuresAdministrationlevelByMeasureID(String measuresID) {
        List<MeasuresAdministrationlevel> measuresAdministrationlevelList = new ArrayList<MeasuresAdministrationlevel>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresAdministrationlevel.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresAdministrationlevel> results = q.getResultList();
        for (MeasuresAdministrationlevel measuresAdministrationlevel : results) {
            measuresAdministrationlevelList.add(measuresAdministrationlevel);
        }
        em.close();
        return measuresAdministrationlevelList;
    }

    /**
     *
     * @param measuresID
     * @return the selected AdministrativelevelBean for measures
     */
    public List<AdministrativelevelBean> getSelectedAdministrativelevelByMeasuresID(String measuresID) {
        List<AdministrativelevelBean> administrativelevelBeanList = new ArrayList<AdministrativelevelBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresAdministrationlevel.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresAdministrationlevel> results = q.getResultList();
        for (MeasuresAdministrationlevel measuresAdministrationlevel : results) {
            administrativelevelBeanList.add(AdministrativelevelWrapper.convertAdministrativelevelInAdministrativelevelBean(measuresAdministrationlevel.getAdministractionlevel()));
        }
        em.close();
        return administrativelevelBeanList;
    }

    /**
     *
     * @return all the Timescale
     */
    public List<TimescaleBean> getAllTimescale() {
        List<TimescaleBean> timescaleBeanList = new ArrayList<TimescaleBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Timescale.findAll");
        List<Timescale> results = q.getResultList();
        for (Timescale timescale : results) {
            timescaleBeanList.add(TimescaleWrapper.convertTimescaleInTimescaleBean(timescale));
        }
        em.close();
        return timescaleBeanList;
    }

    /**
     *
     * @param userEmail
     * @return all the SourceapportionmentBean
     */
    public List<SourceapportionmentBean> getAllSourceapportionmentByUser(String userEmail) {
        List<SourceapportionmentBean> sourceapportionmentBeanList = new ArrayList<SourceapportionmentBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userSourceapportionment = (Users) q.getSingleResult();
        Country country = userSourceapportionment.getCountry();
        Userrole userrole = userSourceapportionment.getUserrole();

        q = em.createNamedQuery("Users.findByCountryAndUserrole");
        q.setParameter("country", country);
        q.setParameter("userrole", userrole);

        List<Users> userList = q.getResultList();
        for (Users users : userList) {
            q = em.createNamedQuery("Sourceapportionment.findAllByUser");
            q.setParameter("users", users);

            List<Sourceapportionment> results = q.getResultList();
            for (Sourceapportionment sourceapportionment : results) {
                sourceapportionmentBeanList.add(SourceapportionmentWrapper.convertSourceapportionmentInSourceapportionmentBean(sourceapportionment, userSourceapportionment));
            }
        }
        em.close();
        return sourceapportionmentBeanList;
    }

    /**
     *
     * @return all the Quantificationnumerical
     */
    public List<QuantificationnumericalBean> getAllQuantificationnumerical() {
        List<QuantificationnumericalBean> quantificationnumericalBeanList = new ArrayList<QuantificationnumericalBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Quantificationnumerical.findAll");
        List<Quantificationnumerical> results = q.getResultList();
        for (Quantificationnumerical quantificationnumerical : results) {
            quantificationnumericalBeanList.add(QuantificationnumericalWrapper.convertQuantificationnumericalInQuantificationnumericalBean(quantificationnumerical));
        }
        em.close();
        return quantificationnumericalBeanList;
    }

    /**
     *
     * @param measuresID
     * @return the selected TimescaleBean for the measures
     */
    public TimescaleBean getSelectedTimescaleBeanByMeasuresID(String measuresID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        Timescale timescale = measures.getTimescale();
        em.close();
        return TimescaleWrapper.convertTimescaleInTimescaleBean(timescale);
    }

    /**
     *
     * @return all the CurrenciesBean
     */
    public List<CurrenciesBean> getAllCurrencies() {
        List<CurrenciesBean> currenciesBeanList = new ArrayList<CurrenciesBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Currency.findAll");
        List<Currency> results = q.getResultList();
        for (Currency currency : results) {
            currenciesBeanList.add(CurrenciesWrapper.convertCurrenciesInCurrenciesBean(currency));
        }
        em.close();
        return currenciesBeanList;
    }

    /**
     *
     * @return all the SourcesectorsBean
     */
    public List<SourcesectorsBean> getAllSourcesectors() {
        List<SourcesectorsBean> sourcesectorsBeanList = new ArrayList<SourcesectorsBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Sourcesector.findAll");
        List<Sourcesector> results = q.getResultList();
        for (Sourcesector sourcesector : results) {
            sourcesectorsBeanList.add(SourcesectorsWrapper.convertSourcesectorsInSourcesectorsBean(sourcesector));
        }
        em.close();
        return sourcesectorsBeanList;
    }

    /**
     *
     * @param measuresID
     * @return the selected SourcesectorsBean for measures
     */
    public List<SourcesectorsBean> getSelectedSourcesectorsByMeasuresID(String measuresID) {
        List<SourcesectorsBean> sourcesectorsBeanList = new ArrayList<SourcesectorsBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresSourcesector.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresSourcesector> results = q.getResultList();
        for (MeasuresSourcesector measuresSourcesector : results) {
            sourcesectorsBeanList.add(SourcesectorsWrapper.convertSourcesectorsInSourcesectorsBean(measuresSourcesector.getSourcesector()));
        }
        em.close();
        return sourcesectorsBeanList;
    }

    private List<MeasuresSourcesector> getMeasuresSourcesectorByMeasuresID(String measuresID) {
        List<MeasuresSourcesector> measuresSourcesectorList = new ArrayList<MeasuresSourcesector>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresSourcesector.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresSourcesector> results = q.getResultList();
        for (MeasuresSourcesector measuresSourcesector : results) {
            measuresSourcesectorList.add(measuresSourcesector);
        }
        em.close();
        return measuresSourcesectorList;
    }

    /**
     *
     * @return all the SpatialscaleBean
     */
    public List<SpatialscaleBean> getAllSpatialscale() {
        List<SpatialscaleBean> spatialscaleBeanList = new ArrayList<SpatialscaleBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Spatialscale.findAll");
        List<Spatialscale> results = q.getResultList();
        for (Spatialscale spatialscale : results) {
            spatialscaleBeanList.add(SpatialscaleWrapper.convertSpatialscaleInSpatialscaleBean(spatialscale));
        }
        em.close();
        return spatialscaleBeanList;
    }

    /**
     *
     * @param measuresID
     * @return the selected SpatialscaleBean
     */
    public List<SpatialscaleBean> getSelectedSpatialscaleByMeasuresID(String measuresID) {
        List<SpatialscaleBean> spatialscaleBeanList = new ArrayList<SpatialscaleBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresSpatialscale.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresSpatialscale> results = q.getResultList();
        for (MeasuresSpatialscale measuresSpatialscale : results) {
            spatialscaleBeanList.add(SpatialscaleWrapper.convertSpatialscaleInSpatialscaleBean(measuresSpatialscale.getSpatialscale()));
        }
        em.close();
        return spatialscaleBeanList;
    }

    private List<MeasuresSpatialscale> getMeasuresSpatialscaleByMeasuresID(String measuresID) {
        List<MeasuresSpatialscale> measuresSpatialscaleList = new ArrayList<MeasuresSpatialscale>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresSpatialscale.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresSpatialscale> results = q.getResultList();
        for (MeasuresSpatialscale measuresSpatialscale : results) {
            measuresSpatialscaleList.add(measuresSpatialscale);
        }
        em.close();
        return measuresSpatialscaleList;
    }

    private List<SourceapportionmentMeasures> getSourceapportionmentByMeasuresID(String measuresID) {
        List<SourceapportionmentMeasures> sourceapportionmentMeasuresList = new ArrayList<SourceapportionmentMeasures>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("SourceapportionmentMeasures.findByMeasures");
        q.setParameter("measures", measures);
        List<SourceapportionmentMeasures> results = q.getResultList();
        for (SourceapportionmentMeasures sourceapportionmentMeasures : results) {
            sourceapportionmentMeasuresList.add(sourceapportionmentMeasures);
        }
        em.close();
        return sourceapportionmentMeasuresList;
    }

    private List<MeasuresEvaluationscenario> getMeasuresEvaluationscenarioByMeasuresID(String measuresID) {
        List<MeasuresEvaluationscenario> measuresEvaluationscenarioList = new ArrayList<MeasuresEvaluationscenario>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresEvaluationscenario.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresEvaluationscenario> results = q.getResultList();
        for (MeasuresEvaluationscenario measuresSpatialscale : results) {
            measuresEvaluationscenarioList.add(measuresSpatialscale);
        }
        em.close();
        return measuresEvaluationscenarioList;
    }

    /**
     *
     * @return all the StatuscloneplannedimplementationBean
     */
    public List<StatusplannedimplementationBean> getAllMeasureimplementationstatus() {
        List<StatusplannedimplementationBean> statusplannedimplementationBeanList = new ArrayList<StatusplannedimplementationBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Statusplannedimplementation.findAll");
        List<Statusplannedimplementation> results = q.getResultList();
        for (Statusplannedimplementation statusplannedimplementation : results) {
            statusplannedimplementationBeanList.add(StatusplannedimplementationWrapper.convertStatusplannedimplementationInStatusplannedimplementationBean(statusplannedimplementation));
        }
        em.close();
        return statusplannedimplementationBeanList;
    }

    /**
     *
     * @return all the SpecificationofhoursBean
     */
    public List<SpecificationofhoursBean> getAllSpecificationofhoursBean() {
        List<SpecificationofhoursBean> specificationofhoursBeanList = new ArrayList<SpecificationofhoursBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Specificationofhours.findAll");
        List<Specificationofhours> results = q.getResultList();
        for (Specificationofhours specificationofhours : results) {
            specificationofhoursBeanList.add(SpecificationofhoursWrapper.convertSpecificationofhoursInSpecificationofhoursBean(specificationofhours));
        }
        em.close();
        return specificationofhoursBeanList;
    }

    /**
     *
     * @param userEmail
     * @return the ID of the draft Measures
     * @throws java.lang.Exception
     */
    public String createMeasuresDraftForUser(String userEmail) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        Measures measures = new Measures();
        String measuresUuid = StringUtils.createUUID(user.getEmail() + dateFormatUtil.getToday(), Measures.class);

        Plannedimplementation plannedimplementation = new Plannedimplementation();
        String plannedimplementationUuid = StringUtils.createUUID(measuresUuid + dateFormatUtil.getToday(), Plannedimplementation.class);
        plannedimplementation.setUuid(plannedimplementationUuid);

        /**
         * set default Statusplannedimplementation
         */
        plannedimplementation.setStatusplannedimplementation(null);

        plannedimplementation.setImplementationplannedtimeperiodId("");
        plannedimplementation.setImplementationplannedtimeperiodBeginposition("9999-01-01");
        plannedimplementation.setImplementationplannedtimeperiodEndposition("9999-01-01");
        plannedimplementation.setPlannedfulleffectdateTimepositionNil(Boolean.FALSE);

        plannedimplementation.setPlannedfulleffectdateId("");
        plannedimplementation.setPlannedfulleffectdateTimeposition("");

        plannedimplementation.setMonitoringprogressindicators("");
        plannedimplementation.setMonitoringprogressindicatorsNil(Boolean.FALSE);

        /**
         * expectedimpact
         */
        measures.setExpectedimpact(null);

        measures.setUuid(measuresUuid);

        measures.setInspireidLocalid("Draft_" + dateFormatUtil.getToday());
        measures.setInspireidNamespace(systemconfiguration.getNamespace());
        measures.setInspireidVersionid(dateFormatUtil.getToday());

        measures.setCode("");
        measures.setName("");
        measures.setDescription("");
        measures.setUsers(user);
        measures.setUserlastupdate(user);

        RelatedpartyWrapper relatedpartyWrapper = new RelatedpartyWrapper();
        Relatedparty provider = relatedpartyWrapper.createDraftProviderFromUser(measuresUuid, user);
        measures.setProvider(provider);

        measures.setChanges(true);
        measures.setDescriptionofchanges("");
        measures.setReportingstartdate("");
        measures.setReportingenddate("");

        measures.setDatecreation(new Date());
        measures.setDatelastupdate(new Date());
        measures.setCompleted(false);

        measures.setMeasuretype(null);
        measures.setTimescale(null);

        measures.setPlannedimplementation(plannedimplementation);

        measures.setReductionofemissionNil(Boolean.FALSE);
        measures.setQuantificationnumerical(null);

        emc.beginTransaction(em);
        em.persist(provider);
        em.persist(plannedimplementation);
        em.persist(measures);
        emc.commitAndCloseTransaction(em);
        return measures.getUuid();
    }

    /**
     * Delete the measures with the given ID
     *
     * @param measuresID
     * @throws eu.europa.ec.aqrexception.DeleteMeasuresException
     */
    public void deleteMeasuresByID(String measuresID) throws DeleteMeasuresException {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("MeasuresClassification.deleteByMeasures");
        q.setParameter("measures", measures);
        q.executeUpdate();

        q = em.createNamedQuery("MeasuresAdministrationlevel.deleteByMeasures");
        q.setParameter("measures", measures);
        q.executeUpdate();

        q = em.createNamedQuery("MeasuresSourcesector.deleteByMeasures");
        q.setParameter("measures", measures);
        q.executeUpdate();

        q = em.createNamedQuery("MeasuresSpatialscale.deleteByMeasures");
        q.setParameter("measures", measures);
        q.executeUpdate();

        q = em.createNamedQuery("MeasuresEvaluationscenario.deleteByMeasures");
        q.setParameter("measures", measures);
        q.executeUpdate();

        q = em.createNamedQuery("SourceapportionmentMeasures.deleteByMeasures");
        q.setParameter("measures", measures);
        q.executeUpdate();

        Plannedimplementation plannedimplementation = measures.getPlannedimplementation();
        Costs costs = measures.getCosts();
        Expectedimpact expectedimpact = measures.getExpectedimpact();
        Relatedparty provider = measures.getProvider();

        q = em.createNamedQuery("Measures.deleteByUuid");
        q.setParameter("uuid", measuresID);
        try {
            q.executeUpdate();
        } catch (Exception ex) {
            throw new DeleteMeasuresException();
        }

        if (costs != null) {
            q = em.createNamedQuery("Costs.deleteByUuid");
            q.setParameter("uuid", costs.getUuid());
            q.executeUpdate();
        }

        if (expectedimpact != null) {
            q = em.createNamedQuery("Expectedimpact.deleteByUuid");
            q.setParameter("uuid", expectedimpact.getUuid());
            q.executeUpdate();
        }

        q = em.createNamedQuery("Relatedparty.deleteByUuid");
        q.setParameter("uuid", provider.getUuid());
        q.executeUpdate();

        q = em.createNamedQuery("Plannedimplementation.deleteByUuid");
        q.setParameter("uuid", plannedimplementation.getUuid());
        q.executeUpdate();
        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param measuresID
     * @param userEmail
     * @return the ID of the cloned Measures
     * @throws java.lang.Exception
     */
    public String cloneMeasuresByMeasuresIDAndUser(String measuresID, String userEmail) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Measures cloneMeasures = new Measures();
        emc.beginTransaction(em);

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();

        q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        String measuresUuid = StringUtils.createUUID(measures.getInspireidNamespace() + dateFormatUtil.getToday(), Measures.class);
        cloneMeasures.setUuid(measuresUuid);

        Country country = user.getCountry();
        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        cloneMeasures.setInspireidLocalid("Clone_" + dateFormatUtil.getToday());
        cloneMeasures.setInspireidNamespace(systemconfiguration.getNamespace());
        cloneMeasures.setInspireidVersionid(dateFormatUtil.getToday());

        cloneMeasures.setCode(measures.getCode());
        cloneMeasures.setName(measures.getName());
        cloneMeasures.setCompleted(measures.getCompleted());
        cloneMeasures.setDescription(measures.getDescription());
        cloneMeasures.setCommentforclarification(measures.getCommentforclarification());

        cloneMeasures.setReductionofemission(measures.getReductionofemission());
        cloneMeasures.setReductionofemissionNil(measures.getReductionofemissionNil());
        cloneMeasures.setReductionofemissionNilreason(measures.getReductionofemissionNilreason());

        cloneMeasures.setQuantificationnumerical(measures.getQuantificationnumerical());
        cloneMeasures.setComment(measures.getComment());

        cloneMeasures.setChanges(measures.isChanges());
        cloneMeasures.setDescriptionofchanges(measures.getDescriptionofchanges());
        cloneMeasures.setReportingstartdate(measures.getReportingstartdate());
        cloneMeasures.setReportingenddate(measures.getReportingenddate());

        cloneMeasures.setDatecreation(new Date());
        cloneMeasures.setDatelastupdate(new Date());

        Relatedparty cloneProvider = new Relatedparty();
        Relatedparty measureProvider = measures.getProvider();
        String cloneProviderUuid = StringUtils.createUUID(cloneMeasures.getUuid() + user.getEmail() + dateFormatUtil.getToday(), Relatedparty.class);
        cloneProvider.setUuid(cloneProviderUuid);
        cloneProvider.setIndividualname(measureProvider.getIndividualname());
        cloneProvider.setOrganisationname(measureProvider.getOrganisationname());
        cloneProvider.setAddress(measureProvider.getAddress());
        cloneProvider.setElectronicmailaddress(measureProvider.getElectronicmailaddress());
        cloneProvider.setTelephonevoice(measureProvider.getTelephonevoice());
        cloneProvider.setWebsite(measureProvider.getWebsite());
        cloneMeasures.setProvider(cloneProvider);
        em.persist(cloneProvider);

        cloneMeasures.setUsers(user);
        cloneMeasures.setUserlastupdate(user);

        /**
         * Measuretype
         */
        Measuretype measuretype = measures.getMeasuretype();
        cloneMeasures.setMeasuretype(measuretype);

        /**
         * Timescale
         */
        Timescale timescale = measures.getTimescale();
        cloneMeasures.setTimescale(timescale);

        /**
         * Plannedimplementation
         */
        Plannedimplementation cloneplannedimplementation = new Plannedimplementation();
        Plannedimplementation plannedimplementation = measures.getPlannedimplementation();
        String cloneplannedimplementationUuid = StringUtils.createUUID(cloneMeasures.getUuid() + dateFormatUtil.getToday(), Plannedimplementation.class);
        cloneplannedimplementation.setUuid(cloneplannedimplementationUuid);

        /**
         * set default Statusplannedimplementation
         */
        Statusplannedimplementation statusplannedimplementation = plannedimplementation.getStatusplannedimplementation();
        cloneplannedimplementation.setStatusplannedimplementation(statusplannedimplementation);

        cloneplannedimplementation.setImplementationplannedtimeperiodId(plannedimplementation.getImplementationplannedtimeperiodId());
        cloneplannedimplementation.setImplementationplannedtimeperiodBeginposition(plannedimplementation.getImplementationplannedtimeperiodBeginposition());
        cloneplannedimplementation.setImplementationplannedtimeperiodEndposition(plannedimplementation.getImplementationplannedtimeperiodEndposition());

        cloneplannedimplementation.setImplementationactualtimeperiodId(plannedimplementation.getImplementationactualtimeperiodId());
        cloneplannedimplementation.setImplementationactualtimeperiodBeginposition(plannedimplementation.getImplementationactualtimeperiodBeginposition());
        cloneplannedimplementation.setImplementationactualtimeperiodEndposition(plannedimplementation.getImplementationactualtimeperiodEndposition());

        cloneplannedimplementation.setPlannedfulleffectdateId(plannedimplementation.getPlannedfulleffectdateId());
        cloneplannedimplementation.setPlannedfulleffectdateTimeposition(plannedimplementation.getPlannedfulleffectdateTimeposition());
        cloneplannedimplementation.setPlannedfulleffectdateTimepositionNil(plannedimplementation.getPlannedfulleffectdateTimepositionNil());
        cloneplannedimplementation.setPlannedfulleffectdateTimepositionNilreason(plannedimplementation.getPlannedfulleffectdateTimepositionNilreason());

        cloneplannedimplementation.setOtherdate(plannedimplementation.getOtherdate());
        cloneplannedimplementation.setMonitoringprogressindicators(plannedimplementation.getMonitoringprogressindicators());
        cloneplannedimplementation.setMonitoringprogressindicatorsNil(plannedimplementation.getMonitoringprogressindicatorsNil());
        cloneplannedimplementation.setMonitoringprogressindicatorsNilreason(plannedimplementation.getMonitoringprogressindicatorsNilreason());
        cloneplannedimplementation.setComment(plannedimplementation.getComment());

        em.persist(cloneplannedimplementation);
        cloneMeasures.setPlannedimplementation(cloneplannedimplementation);

        Costs costs = measures.getCosts();
        if (costs != null) {
            Costs cloneCosts = new Costs();

            String cloneCostsUuid = StringUtils.createUUID(cloneMeasures.getUuid() + dateFormatUtil.getToday(), Costs.class);
            cloneCosts.setUuid(cloneCostsUuid);

            cloneCosts.setExtimatedimplementationcosts(costs.getExtimatedimplementationcosts());
            cloneCosts.setExtimatedimplementationcostsNil(costs.getExtimatedimplementationcostsNil());
            cloneCosts.setExtimatedimplementationcostsNilreason(costs.getExtimatedimplementationcostsNilreason());

            cloneCosts.setFinalimplementationcosts(costs.getFinalimplementationcosts());
            cloneCosts.setComment(costs.getComment());

            Currency currency = costs.getCurrency();
            cloneCosts.setCurrency(currency);

            em.persist(cloneCosts);
            cloneMeasures.setCosts(cloneCosts);
        }

        Expectedimpact expectedimpact = measures.getExpectedimpact();
        if (expectedimpact != null) {
            Expectedimpact cloneExpectedimpact = new Expectedimpact();

            String cloneExpectedimpactUuid = StringUtils.createUUID(cloneMeasures.getUuid() + dateFormatUtil.getToday(), Expectedimpact.class);
            cloneExpectedimpact.setUuid(cloneExpectedimpactUuid);

            cloneExpectedimpact.setLevelofconcentration(expectedimpact.getLevelofconcentration());
            cloneExpectedimpact.setNumberofexceedence(expectedimpact.getNumberofexceedence());
            cloneExpectedimpact.setComment(expectedimpact.getComment());

            Specificationofhours specificationofhours = expectedimpact.getSpecificationofhours();
            cloneExpectedimpact.setSpecificationofhours(specificationofhours);

            em.persist(cloneExpectedimpact);
            cloneMeasures.setExpectedimpact(cloneExpectedimpact);
        }

        em.persist(cloneMeasures);

        List<MeasuresClassification> measuresClassificationList = getMeasuresClassificationByMeasuresID(measuresID);
        for (MeasuresClassification measuresClassification : measuresClassificationList) {
            MeasuresClassification cloneMeasuresClassification = new MeasuresClassification();
            String cloneMeasuresClassificationUuid = StringUtils.createUUID(cloneMeasures.getUuid() + measuresClassification.getClassification().getUuid() + dateFormatUtil.getToday(), MeasuresClassification.class);
            cloneMeasuresClassification.setUuid(cloneMeasuresClassificationUuid);
            cloneMeasuresClassification.setMeasures(cloneMeasures);
            cloneMeasuresClassification.setClassification(measuresClassification.getClassification());

            em.persist(cloneMeasuresClassification);
        }

        List<MeasuresAdministrationlevel> measuresAdministrationlevelList = getMeasuresAdministrationlevelByMeasureID(measuresID);
        for (MeasuresAdministrationlevel measuresAdministrationlevel : measuresAdministrationlevelList) {
            MeasuresAdministrationlevel cloneMeasuresAdministrationlevel = new MeasuresAdministrationlevel();
            String cloneMeasuresClassificationUuid = StringUtils.createUUID(cloneMeasures.getUuid() + measuresAdministrationlevel.getAdministractionlevel().getUuid() + dateFormatUtil.getToday(), MeasuresClassification.class);
            cloneMeasuresAdministrationlevel.setUuid(cloneMeasuresClassificationUuid);
            cloneMeasuresAdministrationlevel.setMeasures(cloneMeasures);
            cloneMeasuresAdministrationlevel.setAdministractionlevel(measuresAdministrationlevel.getAdministractionlevel());

            em.persist(cloneMeasuresAdministrationlevel);
        }

        List<MeasuresSourcesector> measuresSourcesectorList = getMeasuresSourcesectorByMeasuresID(measuresID);
        for (MeasuresSourcesector measuresSourcesector : measuresSourcesectorList) {
            MeasuresSourcesector cloneMeasuresSourcesector = new MeasuresSourcesector();
            String cloneMeasuresClassificationUuid = StringUtils.createUUID(cloneMeasures.getUuid() + measuresSourcesector.getSourcesector().getUuid() + dateFormatUtil.getToday(), MeasuresClassification.class);

            cloneMeasuresSourcesector.setUuid(cloneMeasuresClassificationUuid);
            cloneMeasuresSourcesector.setMeasures(cloneMeasures);
            cloneMeasuresSourcesector.setSourcesector(measuresSourcesector.getSourcesector());

            em.persist(cloneMeasuresSourcesector);
        }

        List<MeasuresSpatialscale> measuresSpatialscaleList = getMeasuresSpatialscaleByMeasuresID(measuresID);
        for (MeasuresSpatialscale measuresSpatialscale : measuresSpatialscaleList) {
            MeasuresSpatialscale cloneMeasuresSpatialscale = new MeasuresSpatialscale();
            String cloneMeasuresClassificationUuid = StringUtils.createUUID(cloneMeasures.getUuid() + measuresSpatialscale.getSpatialscale().getUuid() + dateFormatUtil.getToday(), MeasuresClassification.class);

            cloneMeasuresSpatialscale.setUuid(cloneMeasuresClassificationUuid);
            cloneMeasuresSpatialscale.setMeasures(cloneMeasures);
            cloneMeasuresSpatialscale.setSpatialscale(measuresSpatialscale.getSpatialscale());

            em.persist(cloneMeasuresSpatialscale);
        }

        List<SourceapportionmentMeasures> sourceapportionmentMeasuresList = getSourceapportionmentByMeasuresID(measuresID);
        for (SourceapportionmentMeasures sourceapportionmentMeasures : sourceapportionmentMeasuresList) {
            SourceapportionmentMeasures cloneSourceapportionmentMeasures = new SourceapportionmentMeasures();
            String cloneSourceapportionmentMeasuresUuid = StringUtils.createUUID(cloneMeasures.getUuid() + sourceapportionmentMeasures.getSourceapportionment().getUuid() + dateFormatUtil.getToday(), SourceapportionmentMeasures.class);

            cloneSourceapportionmentMeasures.setUuid(cloneSourceapportionmentMeasuresUuid);
            cloneSourceapportionmentMeasures.setMeasures(cloneMeasures);
            cloneSourceapportionmentMeasures.setSourceapportionment(sourceapportionmentMeasures.getSourceapportionment());

            em.persist(cloneSourceapportionmentMeasures);
        }

        List<MeasuresEvaluationscenario> measuresEvaluationscenarioList = getMeasuresEvaluationscenarioByMeasuresID(measuresID);
        for (MeasuresEvaluationscenario measuresEvaluationscenario : measuresEvaluationscenarioList) {
            MeasuresEvaluationscenario cloneMeasuresEvaluationscenario = new MeasuresEvaluationscenario();
            String cloneMeasuresEvaluationscenarioUuid = StringUtils.createUUID(cloneMeasures.getUuid() + measuresEvaluationscenario.getEvaluationscenario().getUuid() + dateFormatUtil.getToday(), MeasuresEvaluationscenario.class);

            cloneMeasuresEvaluationscenario.setUuid(cloneMeasuresEvaluationscenarioUuid);
            cloneMeasuresEvaluationscenario.setMeasures(cloneMeasures);
            cloneMeasuresEvaluationscenario.setEvaluationscenario(measuresEvaluationscenario.getEvaluationscenario());

            em.persist(cloneMeasuresEvaluationscenario);
        }
        emc.commitAndCloseTransaction(em);

        return cloneMeasures.getUuid();
    }

    /**
     *
     * @param userEmail
     * @return the list of MeasuresBean for the user
     */
    public List<MeasuresBean> getAllMeasuresByUser(String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userMeasures = (Users) q.getSingleResult();
        Country country = userMeasures.getCountry();
        Userrole userrole = userMeasures.getUserrole();

        List<MeasuresBean> measuresBeanList = new ArrayList<MeasuresBean>();

        if (!userrole.getUuid().equals("0")) {
            q = em.createNamedQuery("Users.findByCountry");
            q.setParameter("country", country);

            List<Users> userList = q.getResultList();
            for (Users users : userList) {
                q = em.createNamedQuery("Measures.findAllByUser");
                q.setParameter("users", users);

                List<Measures> results = (List<Measures>) q.getResultList();
                for (Measures measures : results) {
                    measuresBeanList.add(MeasuresWrapper.convertMeasuresInMeasuresBeanTableView(measures, userMeasures));
                }
            }
        } else {
            q = em.createNamedQuery("Measures.findAll");

            List<Measures> results = (List<Measures>) q.getResultList();
            for (Measures measures : results) {
                measuresBeanList.add(MeasuresWrapper.convertMeasuresInMeasuresBeanTableView(measures, userMeasures));
            }
        }

        em.close();
        return measuresBeanList;
    }

    /**
     *
     * @param measureID
     * @param userEmail
     * @return the MeasuredBean for the measureID and email
     */
    public MeasuresBean getMeasureByID(String measureID, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userMeasure = (Users) q.getSingleResult();

        q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measureID);
        Measures measures = (Measures) q.getSingleResult();
        em.close();
        return MeasuresWrapper.convertMeasuresInMeasuresBean(measures, userMeasure);
    }

    /**
     *
     * @param measureINSPIRELocalID
     * @param userEmail
     * @return the MeasuredBean for the measureID
     */
    public MeasuresBean getMeasureByINSPIRELocalID(String measureINSPIRELocalID, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userMeasure = (Users) q.getSingleResult();
        Country country = userMeasure.getCountry();

        try {
            q = em.createNamedQuery("Measures.findByInspireidLocalid");
            q.setParameter("inspireidLocalid", measureINSPIRELocalID);
            List<Measures> measuresList = q.getResultList();

            Measures measuresExistingInThatCountry = null;
            for (Measures measures : measuresList) {
                if (measures.getUsers().getCountry().equals(country)) {
                    measuresExistingInThatCountry = measures;
                    break;
                }
            }

            if (measuresExistingInThatCountry != null) {
                return MeasuresWrapper.convertMeasuresInMeasuresBean(measuresExistingInThatCountry, userMeasure);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Save/Edit the Measures
     *
     * @param measuresBean
     * @throws java.lang.Exception
     * @throws
     * eu.europa.ec.aqrexception.MeasuresINSPIRELocalIDAlreadyExistingException
     */
    public void saveMeasuresDraft(MeasuresBean measuresBean, String emailUserEdit) throws Exception, MeasuresINSPIRELocalIDAlreadyExistingException {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresBean.getUuid());
        Measures measures = (Measures) q.getSingleResult();

        String userEmail = measures.getUsers().getEmail();
        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        boolean measuresINSPIRELocalIDAlreadyExisting = false;

        if (measuresBean.getInspireidLocalid() != null) {

            if (!measures.getInspireidLocalid().equals(measuresBean.getInspireidLocalid())) {
                q = em.createNamedQuery("Measures.findByInspireidLocalid");
                q.setParameter("inspireidLocalid", measuresBean.getInspireidLocalid());

                List<Measures> measuresList = q.getResultList();
                int k = 0;
                for (Measures existentMeasures : measuresList) {
                    if (!existentMeasures.getUsers().getCountry().equals(country)) {
                        k++;
                    }
                }

                if (k == measuresList.size()) {
                    measures.setInspireidLocalid(measuresBean.getInspireidLocalid());
                } else {
                    measuresINSPIRELocalIDAlreadyExisting = true;
                }
            }
        } else {
            measures.setInspireidLocalid("");
        }

        if (measuresINSPIRELocalIDAlreadyExisting) {
            throw new MeasuresINSPIRELocalIDAlreadyExistingException();
        }

        if (measuresBean.getInspireidNamespace() != null) {
            measures.setInspireidNamespace(measuresBean.getInspireidNamespace());
        } else {
            try {
                q = em.createNamedQuery("Systemconfiguration.findByCountry");
                q.setParameter("country", user.getCountry());
                Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();
                measures.setInspireidNamespace(systemconfiguration.getNamespace());
            } catch (Exception e) {
                measures.setInspireidNamespace("");
            }
        }
        measures.setInspireidVersionid(dateFormatUtil.getToday());

        if (measuresBean.getCode() != null) {
            measures.setCode(measuresBean.getCode());
        } else {
            measures.setCode("");
        }
        if (measuresBean.getName() != null) {
            measures.setName(measuresBean.getName());
        } else {
            measures.setName("");
        }
        measures.setCompleted(measuresBean.isCompleted());

        if (measuresBean.getDescription() != null) {
            measures.setDescription(measuresBean.getDescription());
        } else {
            measures.setDescription("");
        }

        RelatedpartyWrapper relatedpartyWrapper = new RelatedpartyWrapper();
        Relatedparty provider = relatedpartyWrapper.saveProviderDraft(em, measures.getProvider().getUuid(), measuresBean.getProviderBean(), measures.getUsers());
        em.merge(provider);

        measures.setChanges(measuresBean.isChanges());
        if (measuresBean.isChanges()) {
            if (measuresBean.getDescriptionofchanges() != null) {
                measures.setDescriptionofchanges(measuresBean.getDescriptionofchanges());
            } else {
                measures.setDescriptionofchanges("");
            }
        }

        if (measuresBean.getReportingstartdate() != null) {
            measures.setReportingstartdate(measuresBean.getReportingstartdate());
        } else {
            measures.setReportingstartdate("");
        }
        if (measuresBean.getReportingenddate() != null) {
            measures.setReportingenddate(measuresBean.getReportingenddate());
        } else {
            measures.setReportingenddate("");
        }

        measures.setDatelastupdate(new Date());
        /**
         * Classification
         */
        q = em.createNamedQuery("MeasuresClassification.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresClassification> measuresClassificationListToDelete = q.getResultList();
        if (measuresClassificationListToDelete.size() > 0) {
            for (MeasuresClassification measuresClassification : measuresClassificationListToDelete) {
                q = em.createNamedQuery("MeasuresClassification.deleteByUuid");
                q.setParameter("uuid", measuresClassification.getUuid());
                q.executeUpdate();
            }
        }

        List<String> classificationUriList = measuresBean.getClassification_uri();
        if (classificationUriList != null && classificationUriList.size() > 0) {
            for (String classificationUri : classificationUriList) {
                q = em.createNamedQuery("Classification.findByUri");
                q.setParameter("uri", classificationUri);
                Classification classification = (Classification) q.getSingleResult();

                MeasuresClassification measuresClassification = new MeasuresClassification();
                String measuresClassificationUuid = StringUtils.createUUID(measures.getUuid() + classification.getUuid() + dateFormatUtil.getToday(), MeasuresClassification.class);
                measuresClassification.setUuid(measuresClassificationUuid);
                measuresClassification.setMeasures(measures);
                measuresClassification.setClassification(classification);
                em.persist(measuresClassification);
            }
        }

        /**
         * create default Measuretype
         */
        String measuretypeUri = measuresBean.getMeasuretype_uri();
        if (measuretypeUri != null) {
            q = em.createNamedQuery("Measuretype.findByUri");
            q.setParameter("uri", measuretypeUri);
            Measuretype measuretype = (Measuretype) q.getSingleResult();
            measures.setMeasuretype(measuretype);
        } else {
            measures.setMeasuretype(null);
        }

        /**
         * Administrative level
         */
        q = em.createNamedQuery("MeasuresAdministrationlevel.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresAdministrationlevel> measuresAdministrationlevelListToDelete = q.getResultList();
        if (measuresAdministrationlevelListToDelete.size() > 0) {
            for (MeasuresAdministrationlevel measuresAdministrationlevel : measuresAdministrationlevelListToDelete) {
                q = em.createNamedQuery("MeasuresAdministrationlevel.deleteByUuid");
                q.setParameter("uuid", measuresAdministrationlevel.getUuid());
                q.executeUpdate();
            }
        }
        List<String> administrationlevelUriList = measuresBean.getAdministrationlevel_uri();
        if (administrationlevelUriList != null && administrationlevelUriList.size() > 0) {
            for (String administrationlevelUri : administrationlevelUriList) {
                q = em.createNamedQuery("Administrationlevel.findByUri");
                q.setParameter("uri", administrationlevelUri);
                Administrationlevel administrationlevel = (Administrationlevel) q.getSingleResult();

                MeasuresAdministrationlevel measuresAdministrationlevel = new MeasuresAdministrationlevel();
                String measuresClassificationUuid = StringUtils.createUUID(measures.getUuid() + administrationlevel.getUuid() + dateFormatUtil.getToday(), MeasuresAdministrationlevel.class);
                measuresAdministrationlevel.setUuid(measuresClassificationUuid);
                measuresAdministrationlevel.setMeasures(measures);
                measuresAdministrationlevel.setAdministractionlevel(administrationlevel);
                em.persist(measuresAdministrationlevel);
            }
        }

        /**
         * Time scale
         */
        String timescaleUri = measuresBean.getTimescale_uri();
        if (timescaleUri != null) {
            q = em.createNamedQuery("Timescale.findByUri");
            q.setParameter("uri", timescaleUri);
            Timescale timescale = (Timescale) q.getSingleResult();
            measures.setTimescale(timescale);
        } else {
            measures.setTimescale(null);
        }

        /**
         * Source sector
         */
        q = em.createNamedQuery("MeasuresSourcesector.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresSourcesector> measuresSourcesectorListToDelete = q.getResultList();
        if (measuresSourcesectorListToDelete.size() > 0) {
            for (MeasuresSourcesector measuresSourcesector : measuresSourcesectorListToDelete) {
                q = em.createNamedQuery("MeasuresSourcesector.deleteByUuid");
                q.setParameter("uuid", measuresSourcesector.getUuid());
                q.executeUpdate();
            }
        }
        List<String> sourcesectorUriList = measuresBean.getSourcesector_uri();
        if (sourcesectorUriList != null && sourcesectorUriList.size() > 0) {
            for (String sourcesectorUri : sourcesectorUriList) {
                q = em.createNamedQuery("Sourcesector.findByUri");
                q.setParameter("uri", sourcesectorUri);
                Sourcesector sourcesector = (Sourcesector) q.getSingleResult();

                MeasuresSourcesector measuresSourcesector = new MeasuresSourcesector();
                String measuresClassificationUuid = StringUtils.createUUID(measures.getUuid() + sourcesector.getUuid() + dateFormatUtil.getToday(), MeasuresAdministrationlevel.class);
                measuresSourcesector.setUuid(measuresClassificationUuid);
                measuresSourcesector.setMeasures(measures);
                measuresSourcesector.setSourcesector(sourcesector);
                em.persist(measuresSourcesector);
            }
        }

        /**
         * Spatial scale
         */
        q = em.createNamedQuery("MeasuresSpatialscale.findByMeasures");
        q.setParameter("measures", measures);
        List<MeasuresSpatialscale> measuresSpatialscaleListToDelete = q.getResultList();
        if (measuresSpatialscaleListToDelete.size() > 0) {
            for (MeasuresSpatialscale measuresSpatialscale : measuresSpatialscaleListToDelete) {
                q = em.createNamedQuery("MeasuresSpatialscale.deleteByUuid");
                q.setParameter("uuid", measuresSpatialscale.getUuid());
                q.executeUpdate();
            }
        }
        List<String> spatialscaleUriList = measuresBean.getSpatialscale_uri();
        if (spatialscaleUriList != null && spatialscaleUriList.size() > 0) {
            for (String spatialscaleUri : spatialscaleUriList) {
                q = em.createNamedQuery("Spatialscale.findByUri");
                q.setParameter("uri", spatialscaleUri);
                Spatialscale spatialscale = (Spatialscale) q.getSingleResult();

                MeasuresSpatialscale measuresSpatialscale = new MeasuresSpatialscale();
                String measuresClassificationUuid = StringUtils.createUUID(measures.getUuid() + spatialscale.getUuid() + dateFormatUtil.getToday(), MeasuresSpatialscale.class);
                measuresSpatialscale.setUuid(measuresClassificationUuid);
                measuresSpatialscale.setMeasures(measures);
                measuresSpatialscale.setSpatialscale(spatialscale);
                em.persist(measuresSpatialscale);
            }
        }

        /**
         * Planned Implementation
         */
        Plannedimplementation plannedimplementation = measures.getPlannedimplementation();
        PlannedimplementationBean plannedimplementationBean = measuresBean.getPlannedimplementationBean();
        if (plannedimplementationBean != null) {

            String statusplannedimplementationUri = plannedimplementationBean.getStatusplannedimplementation_uri();
            if (statusplannedimplementationUri != null) {
                q = em.createNamedQuery("Statusplannedimplementation.findByUri");
                q.setParameter("uri", statusplannedimplementationUri);
                Statusplannedimplementation statusplannedimplementation = (Statusplannedimplementation) q.getSingleResult();
                plannedimplementation.setStatusplannedimplementation(statusplannedimplementation);
            } else {
                plannedimplementation.setStatusplannedimplementation(null);
            }

            if (plannedimplementationBean.getImplementationplannedtimeperiodId() != null) {
                plannedimplementation.setImplementationplannedtimeperiodId(plannedimplementationBean.getImplementationplannedtimeperiodId());
            } else {
                plannedimplementation.setImplementationplannedtimeperiodId("");
            }
            if (plannedimplementationBean.getImplementationplannedtimeperiodBeginposition() != null) {
                plannedimplementation.setImplementationplannedtimeperiodBeginposition(plannedimplementationBean.getImplementationplannedtimeperiodBeginposition());
            } else {
                plannedimplementation.setImplementationplannedtimeperiodBeginposition("");
            }
            if (plannedimplementationBean.getImplementationplannedtimeperiodEndposition() != null) {
                plannedimplementation.setImplementationplannedtimeperiodEndposition(plannedimplementationBean.getImplementationplannedtimeperiodEndposition());
            } else {
                plannedimplementation.setImplementationplannedtimeperiodEndposition("");
            }

            if (plannedimplementationBean.getImplementationactualtimeperiodId() != null) {
                plannedimplementation.setImplementationactualtimeperiodId(plannedimplementationBean.getImplementationactualtimeperiodId());
            } else {
                plannedimplementation.setImplementationactualtimeperiodId("");
            }
            if (plannedimplementationBean.getImplementationactualtimeperiodBeginposition() != null) {
                plannedimplementation.setImplementationactualtimeperiodBeginposition(plannedimplementationBean.getImplementationactualtimeperiodBeginposition());
            } else {
                plannedimplementation.setImplementationactualtimeperiodBeginposition("");
            }
            if (plannedimplementationBean.getImplementationactualtimeperiodEndposition() != null) {
                plannedimplementation.setImplementationactualtimeperiodEndposition(plannedimplementationBean.getImplementationactualtimeperiodEndposition());
            } else {
                plannedimplementation.setImplementationactualtimeperiodEndposition("");
            }

            if (plannedimplementationBean.getPlannedfulleffectdateId() != null) {
                plannedimplementation.setPlannedfulleffectdateId(plannedimplementationBean.getPlannedfulleffectdateId());
            } else {
                plannedimplementation.setPlannedfulleffectdateId("");
            }
            if (plannedimplementationBean.getPlannedfulleffectdateTimeposition() != null) {
                plannedimplementation.setPlannedfulleffectdateTimeposition(plannedimplementationBean.getPlannedfulleffectdateTimeposition());
            } else {
                plannedimplementation.setPlannedfulleffectdateTimeposition("");
            }
            plannedimplementation.setPlannedfulleffectdateTimepositionNil(plannedimplementationBean.isPlannedfulleffectdateTimeposition_nil());
            if (plannedimplementationBean.getPlannedfulleffectdateTimeposition_nilreason() != null) {
                plannedimplementation.setPlannedfulleffectdateTimepositionNilreason(plannedimplementationBean.getPlannedfulleffectdateTimeposition_nilreason());
            } else {
                plannedimplementation.setPlannedfulleffectdateTimepositionNilreason("");
            }

            if (plannedimplementationBean.getOtherdate() != null) {
                plannedimplementation.setOtherdate(plannedimplementationBean.getOtherdate());
            } else {
                plannedimplementation.setOtherdate("");
            }

            if (plannedimplementationBean.getMonitoringprogressindicators() != null) {
                plannedimplementation.setMonitoringprogressindicators(plannedimplementationBean.getMonitoringprogressindicators());
            } else {
                plannedimplementation.setMonitoringprogressindicators("");
            }
            plannedimplementation.setMonitoringprogressindicatorsNil(plannedimplementationBean.isPlannedfulleffectdateTimeposition_nil());
            if (plannedimplementationBean.getMonitoringprogressindicators_nilreason() != null) {
                plannedimplementation.setMonitoringprogressindicatorsNilreason(plannedimplementationBean.getMonitoringprogressindicators_nilreason());
            } else {
                plannedimplementation.setMonitoringprogressindicatorsNilreason("");
            }

            if (plannedimplementationBean.getComment() != null) {
                plannedimplementation.setComment(plannedimplementationBean.getComment());
            } else {
                plannedimplementation.setComment("");
            }

            em.merge(plannedimplementation);
        }

        /**
         * Expectedimpact
         */
        ExpectedimpactBean expectedimpactBean = measuresBean.getExpectedimpactBean();
        if (expectedimpactBean != null) {
            Expectedimpact expectedimpact = measures.getExpectedimpact();
            if (expectedimpact == null) {
                expectedimpact = new Expectedimpact();
                String expectedimpactUuid = StringUtils.createUUID(measures.getUuid() + dateFormatUtil.getToday(), Expectedimpact.class);
                expectedimpact.setUuid(expectedimpactUuid);
            }

            if (expectedimpactBean.getLevelofconcentration() != null) {
                expectedimpact.setLevelofconcentration(expectedimpactBean.getLevelofconcentration());
            } else {
                expectedimpact.setLevelofconcentration("");
            }
            if (expectedimpactBean.getNumberofexceedence() != null) {
                expectedimpact.setNumberofexceedence(expectedimpactBean.getNumberofexceedence());
            } else {
                expectedimpact.setNumberofexceedence("");
            }
            if (expectedimpactBean.getComment() != null) {
                expectedimpact.setComment(expectedimpactBean.getComment());
            } else {
                expectedimpact.setComment("");
            }

            String specificationofhoursUri = expectedimpactBean.getSpecificationofhours_uri();
            if (specificationofhoursUri != null) {
                q = em.createNamedQuery("Specificationofhours.findByUri");
                q.setParameter("uri", specificationofhoursUri);
                Specificationofhours specificationofhours = (Specificationofhours) q.getSingleResult();
                expectedimpact.setSpecificationofhours(specificationofhours);
            } else {
                expectedimpact.setSpecificationofhours(null);
            }

            if (measures.getExpectedimpact() != null) {
                em.merge(expectedimpact);
            } else {
                em.persist(expectedimpact);
                measures.setExpectedimpact(expectedimpact);
            }
        } else {
            measures.setExpectedimpact(null);
        }

        /**
         * Reduction of emissions
         */
        if (measuresBean.getReductionofemission() != null) {
            measures.setReductionofemission(measuresBean.getReductionofemission());
        } else {
            measures.setReductionofemission("");
        }
        measures.setReductionofemissionNil(measuresBean.isReductionofemission_nil());
        if (measuresBean.getReductionofemission_nilreason() != null) {
            measures.setReductionofemissionNilreason(measuresBean.getReductionofemission_nilreason());
        } else {
            measures.setReductionofemissionNilreason("");
        }

        if (measuresBean.getQuantificationnumerical_uri() != null) {
            String quantificationnumericalUri = measuresBean.getQuantificationnumerical_uri();
            q = em.createNamedQuery("Quantificationnumerical.findByUri");
            q.setParameter("uri", quantificationnumericalUri);
            Quantificationnumerical quantificationnumerical = (Quantificationnumerical) q.getSingleResult();
            measures.setQuantificationnumerical(quantificationnumerical);
        } else {
            measures.setQuantificationnumerical(null);
        }

        if (measuresBean.getCommentForClarification() != null) {
            measures.setCommentforclarification(measuresBean.getCommentForClarification());
        } else {
            measures.setCommentforclarification("");
        }

        /**
         * Expect Impact
         */
        /**
         * Comment
         */
        if (measuresBean.getComment() != null) {
            measures.setComment(measuresBean.getComment());
        } else {
            measures.setComment("");
        }
        /**
         * Sourceapportionment
         */
        List<String> sourceapportionmentBeanList = measuresBean.getSourceapportionmentBeanList();
        if (sourceapportionmentBeanList != null) {
            /**
             * delete all the other links for that measures
             */
            q = em.createNamedQuery("SourceapportionmentMeasures.deleteByMeasures");
            q.setParameter("measures", measures);
            q.executeUpdate();

            for (String sourceapportionmentBeanUuid : sourceapportionmentBeanList) {
                q = em.createNamedQuery("Sourceapportionment.findByUuid");
                q.setParameter("uuid", sourceapportionmentBeanUuid);
                Sourceapportionment sourceapportionment = (Sourceapportionment) q.getSingleResult();

                SourceapportionmentMeasures sourceapportionmentMeasures = new SourceapportionmentMeasures();
                String mourceapportionmentMeasuresUuid = StringUtils.createUUID(sourceapportionment.getUuid() + measures.getUuid() + dateFormatUtil.getToday(), SourceapportionmentMeasures.class);
                sourceapportionmentMeasures.setUuid(mourceapportionmentMeasuresUuid);
                sourceapportionmentMeasures.setMeasures(measures);
                sourceapportionmentMeasures.setSourceapportionment(sourceapportionment);
                em.persist(sourceapportionmentMeasures);
            }
        } else {
            q = em.createNamedQuery("SourceapportionmentMeasures.deleteByMeasures");
            q.setParameter("measures", measures);
            q.executeUpdate();
        }

        /**
         * Evaluationscenario
         */
        List<String> evaluationscenarioBeanList = measuresBean.getEvaluationscenarioBeanList();
        if (evaluationscenarioBeanList != null) {
            /**
             * delete all the other links for that measures
             */
            q = em.createNamedQuery("MeasuresEvaluationscenario.deleteByMeasures");
            q.setParameter("measures", measures);
            q.executeUpdate();

            for (String evaluationscenarioBeanUuid : evaluationscenarioBeanList) {
                q = em.createNamedQuery("Evaluationscenario.findByUuid");
                q.setParameter("uuid", evaluationscenarioBeanUuid);
                Evaluationscenario evaluationscenario = (Evaluationscenario) q.getSingleResult();

                MeasuresEvaluationscenario measuresEvaluationscenario = new MeasuresEvaluationscenario();
                String measuresEvaluationscenarioUuid = StringUtils.createUUID(evaluationscenario.getUuid() + measures.getUuid() + dateFormatUtil.getToday(), MeasuresEvaluationscenario.class);
                measuresEvaluationscenario.setUuid(measuresEvaluationscenarioUuid);
                measuresEvaluationscenario.setMeasures(measures);
                measuresEvaluationscenario.setEvaluationscenario(evaluationscenario);

                em.persist(measuresEvaluationscenario);
            }
        } else {
            q = em.createNamedQuery("MeasuresEvaluationscenario.deleteByMeasures");
            q.setParameter("measures", measures);
            q.executeUpdate();
        }

        query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + emailUserEdit + "')";
        q = em.createQuery(query);
        Users useredit = (Users) q.getSingleResult();
        measures.setUserlastupdate(useredit);

        em.merge(measures);
        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param measuresID
     * @param costBean
     * @throws java.lang.Exception
     */
    public void saveCostByMeasueresID(String measuresID, CostsBean costBean) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();
        /**
         * Cost
         */
        Costs costs = measures.getCosts();
        if (costs == null) {
            costs = new Costs();
            String costsUuid = StringUtils.createUUID(measures.getUuid() + dateFormatUtil.getToday(), Costs.class);
            costs.setUuid(costsUuid);
        }

        if (costBean.getExtimatedimplementationcosts() != null) {
            costs.setExtimatedimplementationcosts(costBean.getExtimatedimplementationcosts());
        } else {
            costs.setExtimatedimplementationcosts("");
        }
        costs.setExtimatedimplementationcostsNil(costBean.isExtimatedimplementationcosts_nil());
        if (costBean.getExtimatedimplementationcosts_nilreason() != null) {
            costs.setExtimatedimplementationcostsNilreason(costBean.getExtimatedimplementationcosts_nilreason());
        } else {
            costs.setExtimatedimplementationcostsNilreason("");
        }

        if (costBean.getFinalimplementationcosts() != null) {
            costs.setFinalimplementationcosts(costBean.getFinalimplementationcosts());
        } else {
            costs.setFinalimplementationcosts("");
        }
        if (costBean.getComment() != null) {
            costs.setComment(costBean.getComment());
        } else {
            costs.setComment("");
        }

        String currencyUri = costBean.getCurrency_uri();
        q = em.createNamedQuery("Currency.findByUri");
        q.setParameter("uri", currencyUri);
        try {
            Currency currency = (Currency) q.getSingleResult();
            costs.setCurrency(currency);
        } catch (Exception ex) {
            costs.setCurrency(null);
        }

        if (measures.getCosts() == null) {
            em.persist(costs);
            measures.setCosts(costs);
            em.persist(measures);
        } else {
            em.merge(costs);
            em.merge(measures);
        }
        emc.commitAndCloseTransaction(em);
    }

    public void deleteCostByMeasureID(String measuresID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        Costs costs = measures.getCosts();

        measures.setCosts(null);
        em.merge(measures);

        q = em.createNamedQuery("Costs.deleteByUuid");
        q.setParameter("uuid", costs.getUuid());
        q.executeUpdate();

        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param measuresID
     * @param expectedimpactBean
     * @throws java.lang.Exception
     */
    public void savExpectedimpactByMeasueresID(String measuresID, ExpectedimpactBean expectedimpactBean) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        emc.beginTransaction(em);

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();
        /**
         * Expectedimpact
         */
        Expectedimpact expectedimpact = measures.getExpectedimpact();
        if (expectedimpact == null) {
            expectedimpact = new Expectedimpact();
            String costsUuid = StringUtils.createUUID(measures.getUuid() + dateFormatUtil.getToday(), Expectedimpact.class);
            expectedimpact.setUuid(costsUuid);
        }

        if (expectedimpactBean.getLevelofconcentration() != null) {
            expectedimpact.setLevelofconcentration(expectedimpactBean.getLevelofconcentration());
        }
        if (expectedimpactBean.getNumberofexceedence() != null) {
            expectedimpact.setNumberofexceedence(expectedimpactBean.getNumberofexceedence());
        }
        if (expectedimpactBean.getComment() != null) {
            expectedimpact.setComment(expectedimpactBean.getComment());
        }

        String specificationofhoursUri = expectedimpactBean.getSpecificationofhours_uri();
        q = em.createNamedQuery("Specificationofhours.findByUri");
        q.setParameter("uri", specificationofhoursUri);
        Specificationofhours specificationofhours = (Specificationofhours) q.getSingleResult();
        expectedimpact.setSpecificationofhours(specificationofhours);

        if (measures.getCosts() == null) {
            em.persist(expectedimpact);
            measures.setExpectedimpact(expectedimpact);
            em.persist(measures);
        } else {
            em.merge(expectedimpact);
            em.merge(measures);
        }
        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param measuresID
     */
    public void deleteExpectedimpactByMeasuresID(String measuresID) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measuresID);
        Measures measures = (Measures) q.getSingleResult();

        q = em.createNamedQuery("Expectedimpact.deleteByUuid");
        q.setParameter("uuid", measures.getExpectedimpact().getUuid());

        emc.beginTransaction(em);
        q.executeUpdate();
        emc.commitAndCloseTransaction(em);
    }

    /**
     * Change the status of the Measures
     *
     * @param measureID
     * @param completed
     */
    public void setCompletnessByMeasureID(String measureID, boolean completed) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Measures.findByUuid");
        q.setParameter("uuid", measureID);
        Measures measures = (Measures) q.getSingleResult();

        emc.beginTransaction(em);
        if (completed) {
            measures.setCompleted(Boolean.TRUE);
        } else {
            measures.setCompleted(Boolean.FALSE);
        }
        em.merge(measures);

        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param userEmail
     * @return a list of completed SourceapportionmentBean
     */
    public List<SourceapportionmentBean> getAllCompletedSourceapportionmentByUser(String userEmail) {
        List<SourceapportionmentBean> sourceapportionmentBeanList = new ArrayList<SourceapportionmentBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userSourceapportionment = (Users) q.getSingleResult();

        q = em.createNamedQuery("Sourceapportionment.findByCompletedAndUser");
        q.setParameter("completed", Boolean.TRUE);
        q.setParameter("users", userSourceapportionment);
        List<Sourceapportionment> sourceapportionmentList = (List<Sourceapportionment>) q.getResultList();
        for (Sourceapportionment sourceapportionment : sourceapportionmentList) {
            sourceapportionmentBeanList.add(SourceapportionmentWrapper.convertSourceapportionmentInSourceapportionmentBean(sourceapportionment, userSourceapportionment));
        }

        em.close();
        return sourceapportionmentBeanList;
    }

    /**
     *
     * @param userEmail
     * @return a list of completed EvaluationscenarioBean
     */
    public List<EvaluationscenarioBean> getAllCompletedEvaluationscenarioByUser(String userEmail) {
        List<EvaluationscenarioBean> evaluationscenarioBeanList = new ArrayList<EvaluationscenarioBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userEvaluationscenario = (Users) q.getSingleResult();

        q = em.createNamedQuery("Evaluationscenario.findByCompletedAndUser");
        q.setParameter("completed", Boolean.TRUE);
        q.setParameter("users", userEvaluationscenario);

        List<Evaluationscenario> evaluationscenarioList = (List<Evaluationscenario>) q.getResultList();
        for (Evaluationscenario evaluationscenario : evaluationscenarioList) {
            evaluationscenarioBeanList.add(EvaluationscenarioWrapper.convertEvaluationscenarioInEvaluationscenarioBean(evaluationscenario, userEvaluationscenario));
        }

        em.close();
        return evaluationscenarioBeanList;
    }

    /**
     *
     * @param userEmail
     * @return a list of all the completed measures for the user's country
     */
    public List<MeasuresBean> getAllCompletedMeasuresByCountryOfTheActualUser(String userEmail, boolean completed, Date fromDate, Date toDate) {
        List<MeasuresBean> measuresBeanList = new ArrayList<MeasuresBean>();

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
            q = em.createNamedQuery("Measures.findAllByUserInInterval");
            q.setParameter("users", user);
            q.setParameter("fromDate", new java.sql.Date(fromDate.getTime()));
            q.setParameter("toDate", new java.sql.Date(toDate.getTime()));
            List<Measures> measuresList = (List<Measures>) q.getResultList();

            for (Measures measures : measuresList) {
                if (completed && measures.getCompleted()) {
                    measuresBeanList.add(MeasuresWrapper.convertMeasuresInMeasuresBean(measures, user));
                } else if (!completed) {
                    measuresBeanList.add(MeasuresWrapper.convertMeasuresInMeasuresBean(measures, user));
                }
            }
        }

        em.close();
        return measuresBeanList;
    }
}
