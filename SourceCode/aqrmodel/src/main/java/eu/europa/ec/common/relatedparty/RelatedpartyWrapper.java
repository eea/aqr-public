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
package eu.europa.ec.common.relatedparty;

import eu.europa.ec.aqrmodel.Relatedparty;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.util.StringUtils;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RelatedpartyWrapper {

    public RelatedpartyWrapper() {
    }

    public static RelatedpartyBean convertRelatedpartyInRelatedpartyBean(Relatedparty relatedparty) {
        RelatedpartyBean relatedpartyBean = new RelatedpartyBean();

        relatedpartyBean.setUuid(relatedparty.getUuid());

        relatedpartyBean.setIndividualname(relatedparty.getIndividualname());
        relatedpartyBean.setOrganisationname(relatedparty.getOrganisationname());

        relatedpartyBean.setWebsite(relatedparty.getWebsite());

        relatedpartyBean.setAddress(relatedparty.getAddress());

        relatedpartyBean.setElectronicmailaddress(relatedparty.getElectronicmailaddress());
        relatedpartyBean.setTelephonevoice(relatedparty.getTelephonevoice());

        return relatedpartyBean;
    }

    public Relatedparty createDraftRelatedParty(String uuid) throws Exception {
        Relatedparty relatedparty = new Relatedparty();
        String relatedPartyUuid = StringUtils.createUUID(uuid + new Timestamp(new Date().getTime()), Relatedparty.class);
        relatedparty.setUuid(relatedPartyUuid);
        relatedparty.setIndividualname("");
        relatedparty.setOrganisationname("");
        relatedparty.setAddress("");
        relatedparty.setElectronicmailaddress("");
        relatedparty.setTelephonevoice("");
        relatedparty.setWebsite("");
        return relatedparty;
    }

    public Relatedparty createDraftProviderFromUser(String uuid, Users user) throws Exception {
        Relatedparty relatedparty = new Relatedparty();
        Relatedparty userProvider = user.getProvider();
        String relatedPartyUuid = StringUtils.createUUID(uuid + user.getEmail() + new Timestamp(new Date().getTime()), Relatedparty.class);
        relatedparty.setUuid(relatedPartyUuid);

        if (userProvider.getIndividualname() != null) {
            relatedparty.setIndividualname(userProvider.getIndividualname());
        } else {
            relatedparty.setIndividualname(user.getName() + " " + user.getSurname());
        }

        if (userProvider.getOrganisationname() != null) {
            relatedparty.setOrganisationname(userProvider.getOrganisationname());
        } else {
            relatedparty.setOrganisationname("");
        }

        if (userProvider.getAddress() != null) {
            relatedparty.setAddress(userProvider.getAddress());
        } else {
            relatedparty.setAddress("");
        }

        if (userProvider.getElectronicmailaddress() != null) {
            relatedparty.setElectronicmailaddress(userProvider.getElectronicmailaddress());
        } else {
            relatedparty.setElectronicmailaddress(user.getEmail());
        }

        if (userProvider.getTelephonevoice() != null) {
            relatedparty.setTelephonevoice(userProvider.getTelephonevoice());
        } else {
            relatedparty.setTelephonevoice("");
        }

        if (userProvider.getWebsite() != null) {
            relatedparty.setWebsite(userProvider.getWebsite());
        } else {
            relatedparty.setWebsite("");
        }
        return relatedparty;
    }

    public Relatedparty saveProviderDraft(EntityManager em, String uuid, RelatedpartyBean providerBean, Users user) {
        Query q = em.createNamedQuery("Relatedparty.findByUuid");
        q.setParameter("uuid", uuid);
        Relatedparty provider = ((List<Relatedparty>) q.getResultList()).get(0);

        if (providerBean != null && providerBean.getIndividualname() != null) {
            provider.setIndividualname(providerBean.getIndividualname());
        } else {
            provider.setIndividualname("");
        }
        if (providerBean != null && providerBean.getOrganisationname() != null) {
            provider.setOrganisationname(providerBean.getOrganisationname());
        } else {
            provider.setOrganisationname("");
        }
        if (providerBean != null && providerBean.getWebsite() != null) {
            provider.setWebsite(providerBean.getWebsite());
        } else {
            provider.setWebsite("");
        }
        if (providerBean != null && providerBean.getAddress() != null) {
            provider.setAddress(providerBean.getAddress());
        } else {
            provider.setAddress("");
        }
        if (providerBean != null && providerBean.getTelephonevoice() != null) {
            provider.setTelephonevoice(providerBean.getTelephonevoice());
        } else {
            provider.setTelephonevoice("");
        }
        if (providerBean != null && providerBean.getElectronicmailaddress() != null) {
            provider.setElectronicmailaddress(providerBean.getElectronicmailaddress());
        } else {
            provider.setElectronicmailaddress("");
        }

        return provider;
    }
}
