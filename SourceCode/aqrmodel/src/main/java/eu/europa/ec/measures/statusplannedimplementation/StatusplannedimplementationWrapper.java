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
 *  Daniele Francioli, Emanuela Epure
 *  aq-dev@jrc.ec.europa.eu
 */
package eu.europa.ec.measures.statusplannedimplementation;

import eu.europa.ec.aqrmodel.Statusplannedimplementation;

public class StatusplannedimplementationWrapper {

    public static StatusplannedimplementationBean convertStatusplannedimplementationInStatusplannedimplementationBean(Statusplannedimplementation statusplannedimplementation) {
        StatusplannedimplementationBean statusplannedimplementationBean = new StatusplannedimplementationBean();

        statusplannedimplementationBean.setUuid(statusplannedimplementation.getUuid());
        statusplannedimplementationBean.setUri(statusplannedimplementation.getUri());
        statusplannedimplementationBean.setLabel(statusplannedimplementation.getLabel());
        statusplannedimplementationBean.setDefinition(statusplannedimplementation.getDefinition());
        statusplannedimplementationBean.setNotation(statusplannedimplementation.getNotation());

        return statusplannedimplementationBean;
    }
}
