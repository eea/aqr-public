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
package eu.europa.ec.measures.timescale;

import eu.europa.ec.aqrmodel.Timescale;

public class TimescaleWrapper {

    public static TimescaleBean convertTimescaleInTimescaleBean(Timescale timescale) {
        TimescaleBean timescaleBean = new TimescaleBean();

        timescaleBean.setUuid(timescale.getUuid());
        timescaleBean.setUri(timescale.getUri());
        timescaleBean.setLabel(timescale.getLabel());
        timescaleBean.setDefinition(timescale.getDefinition());
        timescaleBean.setNotation(timescale.getNotation());

        return timescaleBean;
    }
}
