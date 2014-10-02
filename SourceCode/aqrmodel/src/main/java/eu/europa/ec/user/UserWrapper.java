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
import eu.europa.ec.common.relatedparty.RelatedpartyWrapper;
import eu.europa.ec.common.SystemconfigurationBean;
import eu.europa.ec.aqrmodeluser.Country;
import eu.europa.ec.aqrmodeluser.Systemconfiguration;
import eu.europa.ec.aqrmodeluser.Userrole;
import eu.europa.ec.aqrmodeluser.Users;

public class UserWrapper {

    public static UserBean convertUserInUserBean(Users user) {
        UserBean userBean = new UserBean();

        userBean.setUuid(user.getUuid());
        userBean.setEmail(user.getEmail());

        userBean.setName(user.getName());
        userBean.setSurname(user.getSurname());

        userBean.setEnable(user.getEnable());

        userBean.setDatecreation(user.getDatecreation());
        userBean.setLastmodified(user.getLastmodified());

        userBean.setUserroleBean(convertUserroleInUserroleBean(user.getUserrole()));
        userBean.setProviderBean(RelatedpartyWrapper.convertRelatedpartyInRelatedpartyBean(user.getProvider()));
        userBean.setCountryBean(convertCountryInCountryBean(user.getCountry()));

        return userBean;
    }

    public static SystemconfigurationBean convertSystemconfigurationInSystemconfigurationBean(Systemconfiguration systemconfiguration) {
        SystemconfigurationBean systemconfigurationBean = new SystemconfigurationBean();

        systemconfigurationBean.setNamespace(systemconfiguration.getNamespace());
        systemconfigurationBean.setCountry(systemconfiguration.getCountry());

        return systemconfigurationBean;
    }

    public static UserroleBean convertUserroleInUserroleBean(Userrole userrole) {
        UserroleBean userroleBean = new UserroleBean();

        userroleBean.setUuid(userrole.getUuid());
        userroleBean.setRolename(userrole.getRolename());
        userroleBean.setLabel(userrole.getLabel());

        return userroleBean;
    }

    public static Userrole convertUserroleBeanInUserrole(UserroleBean userroleBean) {
        Userrole userrole = new Userrole();

        userrole.setUuid(userroleBean.getUuid());
        userrole.setRolename(userroleBean.getRolename());
        userrole.setLabel(userroleBean.getLabel());

        return userrole;
    }

    public static CountryBean convertCountryInCountryBean(Country country) {
        CountryBean countryBean = new CountryBean();

        countryBean.setUuid(country.getUuid());
        countryBean.setCountryname(country.getCountryname());

        return countryBean;
    }

    public static Country convertCountryBeanInCountry(CountryBean countryBean) {
        Country country = new Country();

        country.setUuid(countryBean.getUuid());
        country.setCountryname(countryBean.getCountryname());

        return country;
    }
}
