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
package eu.europa.ec.plan.protectiontarget;

import eu.europa.ec.aqrmodel.Protectiontarget;

public class ProtectiontargetWrapper {

    public ProtectiontargetWrapper() {
    }

    public static ProtectiontargetBean convertProtectiontargetInProtectiontargetBean(Protectiontarget protectiontarget) {
        ProtectiontargetBean protectiontargetBean = new ProtectiontargetBean();

        protectiontargetBean.setUuid(protectiontarget.getUuid());
        protectiontargetBean.setUri(protectiontarget.getUri());
        protectiontargetBean.setDefinition(protectiontarget.getDefinition());
        protectiontargetBean.setLabel(protectiontarget.getLabel());
        protectiontargetBean.setNotation(protectiontarget.getNotation());

        return protectiontargetBean;
    }

    public static Protectiontarget convertProtectiontargetBeanInProtectiontarget(ProtectiontargetBean protectiontargetBean) {
        Protectiontarget protectiontarget = new Protectiontarget();

        protectiontarget.setUuid(protectiontargetBean.getUuid());
        protectiontarget.setUri(protectiontargetBean.getUri());
        protectiontarget.setDefinition(protectiontargetBean.getDefinition());
        protectiontarget.setLabel(protectiontargetBean.getLabel());
        protectiontarget.setNotation(protectiontargetBean.getNotation());

        return protectiontarget;
    }
}
