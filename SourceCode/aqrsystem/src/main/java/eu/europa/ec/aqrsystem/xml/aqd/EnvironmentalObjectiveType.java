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

import eu.europa.ec.attainment.EnvironmentalobjectiveBean;
import eu.europa.ec.plan.pollutant.PollutantBean;
import eu.europa.ec.plan.protectiontarget.ProtectiontargetBean;
import eu.europa.ec.plan.PlanManager;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Represents aqd:EnvironmentalObjectivePropertyType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class EnvironmentalObjectiveType {

    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType objectiveType;
    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType reportingMetric;
    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType protectionTarget;

    /**
     * Saving the object to eo
     *
     * @param eo Environmentalobjective
     * @param pollutant The pollutant for which is the target
     * @param context ActionBeanContext to which errors and messages can be
     * reported
     * @return true on success and false on error
     */
    public boolean save(EnvironmentalobjectiveBean eo, PollutantBean pollutant, ActionBeanContext context) {
        eo.setObjectivetypevalue(objectiveType.getHref());
        eo.setReportingmetricvalue(reportingMetric.getHref());
        ProtectiontargetBean pt = new PlanManager().getProtectiontargetBeanByProtectionTargetUri(protectionTarget.getHref());
        if (pt == null) {
            BaseUtils.logWarn("Unknown target URI: " + protectionTarget.getHref() + "\nfor pollutant: " + pollutant.getLabel());
            context.getValidationErrors().addGlobalError(new LocalizableError("import.error.attainment.targeturi", HtmlUtil.encode(protectionTarget.getHref()), HtmlUtil.encode(pollutant.getLabel())));
            return false;
        }
        eo.setProtectiontargetBean(pt);
        return true;
    }
}
