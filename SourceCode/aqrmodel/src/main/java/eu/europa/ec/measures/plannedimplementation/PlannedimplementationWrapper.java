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
package eu.europa.ec.measures.plannedimplementation;

import eu.europa.ec.aqrmodel.Plannedimplementation;
import eu.europa.ec.aqrmodel.Statusplannedimplementation;

public class PlannedimplementationWrapper {

    public static PlannedimplementationBean convertPlannedimplementationInPlannedimplementationBean(Plannedimplementation plannedimplementation) {
        PlannedimplementationBean plannedimplementationBean = new PlannedimplementationBean();

        plannedimplementation.setUuid(plannedimplementation.getUuid());

        plannedimplementationBean.setImplementationplannedtimeperiodId(plannedimplementation.getImplementationplannedtimeperiodId());
        plannedimplementationBean.setImplementationplannedtimeperiodBeginposition(plannedimplementation.getImplementationplannedtimeperiodBeginposition());
        plannedimplementationBean.setImplementationplannedtimeperiodEndposition(plannedimplementation.getImplementationplannedtimeperiodEndposition());

        plannedimplementationBean.setImplementationactualtimeperiodId(plannedimplementation.getImplementationactualtimeperiodId());
        plannedimplementationBean.setImplementationactualtimeperiodBeginposition(plannedimplementation.getImplementationactualtimeperiodBeginposition());
        plannedimplementationBean.setImplementationactualtimeperiodEndposition(plannedimplementation.getImplementationactualtimeperiodEndposition());

        plannedimplementationBean.setPlannedfulleffectdateId(plannedimplementation.getPlannedfulleffectdateId());
        plannedimplementationBean.setPlannedfulleffectdateTimeposition(plannedimplementation.getPlannedfulleffectdateTimeposition());
        plannedimplementationBean.setPlannedfulleffectdateTimeposition_nil(plannedimplementation.getPlannedfulleffectdateTimepositionNil());
        plannedimplementationBean.setPlannedfulleffectdateTimeposition_nilreason(plannedimplementation.getPlannedfulleffectdateTimepositionNilreason());

        plannedimplementationBean.setOtherdate(plannedimplementation.getOtherdate());

        plannedimplementationBean.setMonitoringprogressindicators(plannedimplementation.getMonitoringprogressindicators());
        plannedimplementationBean.setMonitoringprogressindicators_nil(plannedimplementation.getMonitoringprogressindicatorsNil());
        plannedimplementationBean.setMonitoringprogressindicators_nilreason(plannedimplementation.getMonitoringprogressindicatorsNilreason());

        plannedimplementationBean.setComment(plannedimplementation.getComment());

        Statusplannedimplementation statusplannedimplementation = plannedimplementation.getStatusplannedimplementation();
        if (statusplannedimplementation != null) {
            plannedimplementationBean.setStatusplannedimplementation_uri(statusplannedimplementation.getUri());
            plannedimplementationBean.setStatusplannedimplementation_label(statusplannedimplementation.getLabel());
        } else {
            plannedimplementationBean.setStatusplannedimplementation_uri(null);
            plannedimplementationBean.setStatusplannedimplementation_label(null);
        }

        return plannedimplementationBean;
    }
}
