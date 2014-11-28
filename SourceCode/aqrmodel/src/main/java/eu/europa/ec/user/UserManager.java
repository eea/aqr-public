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
package eu.europa.ec.user;

import eu.europa.ec.common.CountryBean;
import eu.europa.ec.common.relatedparty.RelatedpartyBean;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.attainment.AttainmentWrapper;
import eu.europa.ec.aqrmodel.Attainment;
import eu.europa.ec.aqrmodel.Plan;
import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodeluser.Country;
import eu.europa.ec.aqrmodeluser.Systemconfiguration;
import eu.europa.ec.aqrmodeluser.Userrole;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.EntityManagerCustom;
import eu.europa.ec.util.StringUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserManager {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserManager.class);
    private final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public UserManager() {
    }

    /**
     *
     * @param userEmail
     * @param planID
     * @return a boolean if the user can or not save the plan
     */
    public boolean canUserSavePlan(String userEmail, String planID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Plan.findByUuid");
        q.setParameter("uuid", planID);
        Plan plan = ((List<Plan>) q.getResultList()).get(0);
        Users userPlan = plan.getUsers();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();

        return userPlan.equals(user);
    }

    /**
     *
     * @param userEmail
     * @return the UserBean
     */
    public UserBean getUserByEmail(String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        try {
            String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
            Query q = em.createQuery(query);
            Users user = (Users) q.getSingleResult();

            return UserWrapper.convertUserInUserBean(user);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param uuid
     * @return the UserBean
     */
    public UserBean getUserByUuid(String uuid) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        try {
            Query q = em.createNamedQuery("Users.findByUuid");
            q.setParameter("uuid", uuid);
            Users user = (Users) q.getSingleResult();

            return UserWrapper.convertUserInUserBean(user);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param userBean
     * @throws eu.europa.ec.aqrexception.EmailAlreadyInTheDBException
     */
    public void updateUserByUserID(UserBean userBean) throws EmailAlreadyInTheDBException, Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();
        /**
         * check if the userEmail exist
         */
        String userEmail = userBean.getEmail();

        Users user = new Users();
        if (userBean.getUuid() == null) {
            String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
            Query q = em.createQuery(query);
            Users result = null;
            try {
                result = (Users) q.getSingleResult();
                if (result != null) {
                    throw new EmailAlreadyInTheDBException();
                }
            } catch (Exception ex) {
                if (result != null) {
                    throw new EmailAlreadyInTheDBException();
                } else {
                    user = new Users();
                    String userUuid = StringUtils.createUUID(userEmail + dateFormatUtil.getToday(), Users.class);
                    user.setUuid(userUuid);
                    user.setDatecreation(new Date());
                }
            }
        } else {
            Query q = em.createNamedQuery("Users.findByUuid");
            q.setParameter("uuid", userBean.getUuid());
            user = (Users) q.getSingleResult();

            String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
            q = em.createQuery(query);
            List<Users> usersWithEmail = q.getResultList();
            for (Users users : usersWithEmail) {
                try {
                    if (!user.equals(users)) {
                        throw new EmailAlreadyInTheDBException();
                    }
                } catch (EmailAlreadyInTheDBException ex) {
                    throw new EmailAlreadyInTheDBException();
                }
            }
        }

        if (userBean.isEnable()) {
            user.setEnable(Boolean.TRUE);
        } else {
            user.setEnable(Boolean.FALSE);
        }

        if (userEmail != null) {
            user.setEmail(userEmail);
        } else {
            user.setEmail("");
        }

        if (userBean.getName() != null) {
            user.setName(userBean.getName());
        } else {
            user.setName("");
        }

        if (userBean.getName() != null) {
            user.setSurname(userBean.getSurname());
        } else {
            user.setSurname("");
        }

        String userroleUuid = userBean.getCountryBean().getUuid();
        String rolenameUuid = userBean.getUserroleBean().getUuid();

        Query q = em.createNamedQuery("Userrole.findByUuid");
        q.setParameter("uuid", rolenameUuid);
        Userrole userrole = (Userrole) q.getSingleResult();

        q = em.createNamedQuery("Country.findByUuid");
        q.setParameter("uuid", userroleUuid);
        Country country = (Country) q.getSingleResult();

        user.setUserrole(userrole);
        user.setCountry(country);

        user.setLastmodified(new Date());

        RelatedpartyBean providerBean = userBean.getProviderBean();
        Relatedparty relatedparty;

        if (user.getProvider() != null) {
            relatedparty = user.getProvider();
        } else {
            relatedparty = new Relatedparty();
            String relatedPartyUuid = StringUtils.createUUID(user.getEmail() + new Timestamp(new Date().getTime()), Relatedparty.class);
            relatedparty.setUuid(relatedPartyUuid);
        }

        if (providerBean != null) {
            if (providerBean.getIndividualname() != null) {
                relatedparty.setIndividualname(providerBean.getIndividualname());
            } else {
                relatedparty.setIndividualname(user.getName() + " " + user.getSurname());
            }

            if (providerBean.getOrganisationname() != null) {
                relatedparty.setOrganisationname(providerBean.getOrganisationname());
            } else {
                relatedparty.setOrganisationname("");
            }

            if (providerBean.getAddress() != null) {
                relatedparty.setAddress(providerBean.getAddress());
            } else {
                relatedparty.setAddress("");
            }

            if (providerBean.getAddress() != null) {
                relatedparty.setElectronicmailaddress(providerBean.getElectronicmailaddress());
            } else {
                relatedparty.setElectronicmailaddress(user.getEmail());
            }

            if (providerBean.getTelephonevoice() != null) {
                relatedparty.setTelephonevoice(providerBean.getTelephonevoice());
            } else {
                relatedparty.setTelephonevoice(user.getEmail());
            }

            if (providerBean.getWebsite() != null) {
                relatedparty.setWebsite(providerBean.getWebsite());
            } else {
                relatedparty.setWebsite(user.getEmail());
            }
        } else {
            relatedparty.setIndividualname(user.getName() + " " + user.getSurname());
            relatedparty.setOrganisationname("");
            relatedparty.setAddress("");
            relatedparty.setElectronicmailaddress(user.getEmail());
            relatedparty.setTelephonevoice(user.getEmail());
            relatedparty.setWebsite(user.getEmail());
        }
        user.setProvider(relatedparty);

        emc.beginTransaction(em);
        if (userBean.getUuid() != null) {
            em.merge(relatedparty);
            em.merge(user);
        } else {
            em.persist(relatedparty);
            em.persist(user);
        }
        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param userId
     * @param enable
     */
    public void enableUser(String userId, boolean enable) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Users.findByUuid");
        q.setParameter("uuid", userId);
        Users user = (Users) q.getSingleResult();

        emc.beginTransaction(em);
        user.setEnable(enable);
        em.merge(user);
        emc.commitAndCloseTransaction(em);

    }

    /**
     *
     * @return all the Administrator user
     */
    public List<UserBean> getAllAdminUser() {
        List<UserBean> userBeanList = new ArrayList<UserBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Userrole.findByRolename");
        q.setParameter("rolename", "administrator");
        Userrole userrole = (Userrole) q.getSingleResult();

        q = em.createNamedQuery("Users.findByUserrole");
        q.setParameter("userrole", userrole);
        List<Users> usersList = q.getResultList();
        for (Users users : usersList) {
            userBeanList.add(UserWrapper.convertUserInUserBean(users));
        }

        return userBeanList;
    }

    /**
     *
     * @param countryname
     * @return all the user for a country
     */
    public List<UserBean> getAlUserByCountryname(String countryname) {
        List<UserBean> userBeanList = new ArrayList<UserBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Country.findByCountryname");
        q.setParameter("countryname", countryname);
        Country country = (Country) q.getSingleResult();

        q = em.createNamedQuery("Users.findByCountry");
        q.setParameter("country", country);

        List<Users> usersList = q.getResultList();
        for (Users user : usersList) {
            userBeanList.add(UserWrapper.convertUserInUserBean(user));
        }

        return userBeanList;
    }

    /**
     *
     * @return all the Countries
     */
    public List<CountryBean> getAllCountries() {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Country.findAll");
        List<Country> countryList = q.getResultList();

        HashMap<String, Country> hash = new HashMap<String, Country>();
        String[] countryName = new String[countryList.size()];
        int k = 0;
        for (Country country : countryList) {
            hash.put(country.getCountryname(), country);
            countryName[k] = country.getCountryname();
            k++;
        }

        Arrays.sort(countryName);

        List<CountryBean> countryBeanList = new ArrayList<CountryBean>();
        for (String country : countryName) {
            countryBeanList.add(UserWrapper.convertCountryInCountryBean(hash.get(country)));
        }

        return countryBeanList;
    }

    /**
     *
     * @return all the Roles
     */
    public List<UserroleBean> getAllRoles() {
        List<UserroleBean> result = new ArrayList<UserroleBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Userrole.findAll");
        List<Userrole> roleList = q.getResultList();
        for (Userrole role : roleList) {
            result.add(UserWrapper.convertUserroleInUserroleBean(role));
        }

        return result;
    }

    /**
     * Getting a list of users. The superuser will get all the users in the
     * system, the administrator will get users of his country.
     *
     * @param userEmail Email of the user used to prepare the right list for the
     * user
     * @return A list of users
     */
    public List<UserBean> getAllUsers(String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();

        List<UserBean> userBeanList = new ArrayList<UserBean>();
        if (user.getUserrole().getUuid().equals("0")) {
            /**
             * superusers
             */
            q = em.createNamedQuery("Users.findAll");
            List<Users> users = q.getResultList();
            for (Users usersInTheCountry : users) {
                userBeanList.add(UserWrapper.convertUserInUserBean(usersInTheCountry));
            }
        } else {
            Country country = user.getCountry();
            q = em.createNamedQuery("Users.findByCountry");
            q.setParameter("country", country);

            List<Users> usersList = q.getResultList();
            for (Users userFromCountry : usersList) {
                userBeanList.add(UserWrapper.convertUserInUserBean(userFromCountry));
            }
        }

        return userBeanList;
    }

    /**
     *
     * @param userEmail
     * @return the namespace corresponding to the user
     */
    public String getNamespaceByUserEmail(String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        return systemconfiguration.getNamespace();
    }

    /**
     *
     * @param userEmail
     * @param namespace
     */
    public void setNamespaceByUserEmail(String userEmail, String namespace) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        systemconfiguration.setNamespace(namespace);

        emc.beginTransaction(em);
        em.merge(systemconfiguration);
        emc.commitAndCloseTransaction(em);
    }

    /**
     *
     * @param userEmail
     * @return a list of all the uploaded AttainmentBean
     */
    public List<AttainmentBean> getAllAttainmentBeanByUser(String userEmail) {
        List<AttainmentBean> attainmentBeanList = new ArrayList<AttainmentBean>();

        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
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
