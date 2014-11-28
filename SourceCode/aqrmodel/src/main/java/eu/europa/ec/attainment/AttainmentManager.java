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
package eu.europa.ec.attainment;

import eu.europa.ec.aqrmodel.Attainment;
import eu.europa.ec.aqrmodeluser.Country;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.util.EntityManagerCustom;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AttainmentManager {

    public AttainmentManager() {
    }

    /**
     * @param attainmenID
     * @return
     */
    public AttainmentBean getAttainmentBeanByAttaimentID(String attainmenID) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        try {
            Query q = em.createNamedQuery("Attainment.findByUuid");
            q.setParameter("uuid", attainmenID);
            Attainment attainment = (Attainment) q.getSingleResult();

            return AttainmentWrapper.convertAttainmentInAttainmentBean(attainment);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * @param attaimentInspireidLocalid
     * @param userEmail
     * @return AttainmentBean
     */
    public AttainmentBean getAttainmentBeanByINSPIRELocalID(String attaimentInspireidLocalid, String userEmail) {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users userEvaluationscenario = (Users) q.getSingleResult();
        Country country = userEvaluationscenario.getCountry();

        try {
            q = em.createNamedQuery("Attainment.findByInspireidLocalid");
            q.setParameter("inspireidLocalid", attaimentInspireidLocalid);

            List<Attainment> attainmentList = q.getResultList();

            Attainment attainmentExistingInThatCountry = null;
            for (Attainment attainment : attainmentList) {
                if (attainment.getUsers().getCountry().equals(country)) {
                    attainmentExistingInThatCountry = attainment;
                    break;
                }
            }

            if (attainmentExistingInThatCountry != null) {
                return AttainmentWrapper.convertAttainmentInAttainmentBean(attainmentExistingInThatCountry);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     *
     * @param userEmail
     * @param attainmentBean
     * @return the attainment ID in case the operation has been completed with
     * success, null in contrary
     * @throws java.lang.Exception
     */
    public String saveAttaimentByUserAndAttainmentBean(String userEmail, AttainmentBean attainmentBean) throws Exception {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        String query = "SELECT u FROM Users u WHERE UPPER(u.email) LIKE UPPER('" + userEmail + "')";
        Query q = em.createQuery(query);
        Users user = (Users) q.getSingleResult();
        Country country = user.getCountry();

        if (attainmentBean.getInspireidLocalid() != null) {
            q = em.createNamedQuery("Attainment.findByInspireidLocalid");
            q.setParameter("inspireidLocalid", attainmentBean.getInspireidLocalid());

            try {
                /**
                 * get all the attainment with the same INSPIRE Local ID
                 */
                List<Attainment> attainmentList = q.getResultList();
                int k = 0;
                for (Attainment existentAttainment : attainmentList) {
                    if (!existentAttainment.getUsers().getCountry().equals(country)) {
                        k++;
                    }
                }

                /**
                 * is doesn't exist any attainment for the country with inspire
                 * ID
                 */
                if (k == attainmentList.size()) {
                    Attainment newAttainment = AttainmentWrapper.convertAttainmentBeanInAttainment(attainmentBean, user, em);

                    int localID = attainmentBean.getInspireidLocalid().length();
                    if (!(localID < 100)) {
                        return null;
                    }
                    if (attainmentBean.getInspireidNamespace() != null && attainmentBean.getInspireidNamespace().length() > 100) {
                        return null;
                    }
                    if (attainmentBean.getInspireidVersionid() != null && attainmentBean.getInspireidVersionid().length() > 100) {
                        return null;
                    }

                    try {
                        emc.beginTransaction(em);
                        em.persist(newAttainment);
                        emc.commitAndCloseTransaction(em);
                    } catch (Exception e) {
                        return null;
                    }

                    return newAttainment.getUuid();
                } else {
                    return null;
                }
            } catch (Exception ex) {
                Attainment attainment = AttainmentWrapper.convertAttainmentBeanInAttainment(attainmentBean, user, em);
                try {

                    emc.beginTransaction(em);
                    em.persist(attainment);
                    emc.commitAndCloseTransaction(em);
                } catch (Exception e) {
                    return null;
                }
                return attainment.getUuid();
            }
        }
        return null;
    }

    /**
     * @param attainmenID
     * @throws eu.europa.ec.attainment.DeleteAttainmentException
     */
    public void deleteAttainmentByAttaimentID(String attainmenID) throws DeleteAttainmentException {
        EntityManagerCustom emc = new EntityManagerCustom();
        EntityManager em = emc.getEntityManager();

        Query q = em.createNamedQuery("Attainment.findByUuid");
        q.setParameter("uuid", attainmenID);
        Attainment attainment = (Attainment) q.getSingleResult();

        q = em.createNamedQuery("Attainment.deleteByUuid");
        q.setParameter("uuid", attainment.getUuid());

        emc.beginTransaction(em);

        try {
            q.executeUpdate();
        } catch (Exception ex) {
            throw new DeleteAttainmentException();
        }

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
