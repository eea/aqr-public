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
import eu.europa.ec.aqrmodeluser.Systemconfiguration;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.user.UserBean;
import eu.europa.ec.user.UserWrapper;
import eu.europa.ec.util.StringUtils;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AttainmentWrapper {

    public static AttainmentBean convertAttainmentInAttainmentBean(Attainment attainment) {
        return convertAttainmentInAttainmentBean(attainment, null);
    }

    public static AttainmentBean convertAttainmentInAttainmentBean(Attainment attainment, Users user) {
        AttainmentBean attainmentBean = new AttainmentBean();

        attainmentBean.setUuid(attainment.getUuid());

        attainmentBean.setInspireidLocalid(attainment.getInspireidLocalid());
        attainmentBean.setInspireidNamespace(attainment.getInspireidNamespace());
        attainmentBean.setInspireidVersionid(attainment.getInspireidVersionid());

        attainmentBean.setComment(attainment.getComment());

        attainmentBean.setValidityperiodId(attainment.getValidityperiodId());
        attainmentBean.setValidityperiodBeginposition(attainment.getValidityperiodBeginposition());
        attainmentBean.setValidityperiodEndposition(attainment.getValidityperiodEndposition());

        attainmentBean.setDatecreation(attainment.getDatecreation());
        attainmentBean.setDatelastupdate(attainment.getDatelastupdate());
        
        attainmentBean.setUserBean(UserWrapper.convertUserInUserBean(attainment.getUsers()));

        if (user != null) {
            Users userAttainment = attainment.getUsers();
            if (userAttainment.equals(user)) {
                attainmentBean.setEditable(true);
            } else {
                attainmentBean.setEditable(false);
            }
        }

        return attainmentBean;
    }

    public static Attainment convertAttainmentBeanInAttainment(AttainmentBean attainmentBean, Users user, EntityManager em) throws Exception {
        Attainment attainment = new Attainment();

        Country country = user.getCountry();

        Query q = em.createNamedQuery("Systemconfiguration.findByCountry");
        q.setParameter("country", country);
        Systemconfiguration systemconfiguration = (Systemconfiguration) q.getSingleResult();

        String attainmentUuid = StringUtils.createUUID(attainmentBean.getInspireidLocalid() + new Timestamp(new Date().getTime()), Attainment.class);
        attainment.setUuid(attainmentUuid);

        if (attainmentBean.getInspireidLocalid() != null) {
            attainment.setInspireidLocalid(attainmentBean.getInspireidLocalid());
        } else {
            attainment.setInspireidLocalid("Draft " + new Timestamp(new Date().getTime()));
        }

        if (attainmentBean.getInspireidNamespace() != null) {
            attainment.setInspireidNamespace(attainmentBean.getInspireidNamespace());
        } else {
            attainment.setInspireidNamespace(systemconfiguration.getNamespace());
        }

        if (attainmentBean.getInspireidVersionid() != null) {
            attainment.setInspireidVersionid(attainmentBean.getInspireidVersionid());
        } else {
            attainment.setInspireidVersionid(new Date() + "");
        }

        if (attainmentBean.getComment() != null) {
            attainment.setComment(attainmentBean.getComment());
        }
        if (attainmentBean.getValidityperiodId() != null) {
            attainment.setValidityperiodId(attainmentBean.getValidityperiodId());
        }

        if (attainmentBean.getValidityperiodBeginposition() != null) {
            attainment.setValidityperiodBeginposition(attainmentBean.getValidityperiodBeginposition());
        }

        if (attainmentBean.getValidityperiodEndposition() != null) {
            attainment.setValidityperiodEndposition(attainmentBean.getValidityperiodEndposition());
        }

        attainment.setDatecreation(new Date());
        attainment.setDatelastupdate(new Date());

        attainment.setUsers(user);

        //pollutant
        //EnvironmentalobjectiveBean
        return attainment;
    }

}
