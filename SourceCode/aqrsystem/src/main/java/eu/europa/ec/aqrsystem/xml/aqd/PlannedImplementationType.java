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
package eu.europa.ec.aqrsystem.xml.aqd;

import eu.europa.ec.measures.plannedimplementation.PlannedimplementationBean;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.NillableString;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import eu.europa.ec.aqrsystem.xml.gml.TimeInstantPropertyType;
import eu.europa.ec.aqrsystem.xml.gml.TimePeriodPropertyType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Represents aqd:PlannedImplementationType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class PlannedImplementationType {

    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType status = new ReferenceType();
    @XmlElement(namespace = Namespaces.aqd)
    protected TimePeriodPropertyType implementationPlannedTimePeriod = new TimePeriodPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected TimePeriodPropertyType implementationActualTimePeriod;
    @XmlElement(namespace = Namespaces.aqd)
    protected TimeInstantPropertyType plannedFullEffectDate = new TimeInstantPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String otherDates;
    @XmlElement(namespace = Namespaces.aqd)
    protected NillableString monitoringProgressIndicators = new NillableString();
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;

    public void populate(PlannedimplementationBean p, String uuid) {
        status.setHref(p.getStatusplannedimplementation_uri(), false);
        implementationPlannedTimePeriod.populate("PLANNED_IMPLEMENTATION_TIME_PERIOD_" + uuid, p.getImplementationplannedtimeperiodBeginposition(), p.getImplementationplannedtimeperiodEndposition());
        if (BaseUtils.isDefined(p.getImplementationactualtimeperiodBeginposition()) || BaseUtils.isDefined(p.getImplementationactualtimeperiodEndposition())) {
            implementationActualTimePeriod = new TimePeriodPropertyType();
            implementationActualTimePeriod.populate("ACTUAL_IMPLEMENTATION_TIME_PERIOD_" + uuid, p.getImplementationactualtimeperiodBeginposition(), p.getImplementationactualtimeperiodEndposition());
        }
        plannedFullEffectDate.populate(p.getPlannedfulleffectdateTimeposition(), "PLANNED_FULL_EFFECT_DATE_" + uuid, p.isPlannedfulleffectdateTimeposition_nil(), p.getPlannedfulleffectdateTimeposition_nilreason());
        if (BaseUtils.isDefined(p.getOtherdate())) {
            otherDates = p.getOtherdate();
        }
        monitoringProgressIndicators.populate(p.getMonitoringprogressindicators(), p.isMonitoringprogressindicators_nil(), p.getMonitoringprogressindicators_nilreason());
        if (BaseUtils.isDefined(p.getComment())) {
            comment = p.getComment();
        }
    }

    public void setObject(PlannedimplementationBean p, ActionBeanContext context) {
        p.setStatusplannedimplementation_uri(status.getHref());
        if (implementationPlannedTimePeriod != null) {
            p.setImplementationplannedtimeperiodBeginposition(implementationPlannedTimePeriod.getBeginPosition());
            p.setImplementationplannedtimeperiodEndposition(implementationPlannedTimePeriod.getEndPosition());
        }
        if (implementationActualTimePeriod != null) {
            p.setImplementationactualtimeperiodBeginposition(implementationActualTimePeriod.getBeginPosition());
            p.setImplementationactualtimeperiodEndposition(implementationActualTimePeriod.getEndPosition());
        }
        if (!plannedFullEffectDate.isNilReason()) {
            p.setPlannedfulleffectdateTimeposition(plannedFullEffectDate.getValue());
        } else {
            p.setPlannedfulleffectdateTimeposition_nil(true);
            p.setPlannedfulleffectdateTimeposition_nilreason(plannedFullEffectDate.getNilReason());
        }
        p.setOtherdate(otherDates);
        if (!monitoringProgressIndicators.isNilReason()) {
            p.setMonitoringprogressindicators(monitoringProgressIndicators.getValue());
        } else {
            p.setMonitoringprogressindicators_nil(true);
            p.setMonitoringprogressindicators_nilreason(monitoringProgressIndicators.getNilReason());
        }
        p.setComment(comment);
    }
}
