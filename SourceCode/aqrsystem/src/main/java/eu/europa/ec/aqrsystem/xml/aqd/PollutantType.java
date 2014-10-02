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

import eu.europa.ec.plan.pollutant.PollutantBean;
import eu.europa.ec.plan.protectiontarget.ProtectiontargetBean;
import eu.europa.ec.plan.PlanManager;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Represents aqd:PollutantType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class PollutantType {

    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType pollutantCode = new ReferenceType();

    @XmlElement(namespace = Namespaces.aqd)
    protected final ReferenceType protectionTarget = new ReferenceType();

    public PollutantType populate(PollutantBean p, ProtectiontargetBean pt) {
        if (p != null) {
            pollutantCode.setHref(p.getUri(), false);
            protectionTarget.setHref(pt.getUri(), false);
        }
        return this;
    }

    public void save(String planId, ActionBeanContext context) throws Exception {
        PlanManager planManager = new PlanManager();

        if (pollutantCode.getHref() != null && protectionTarget.getHref() != null) {
            PollutantBean pol = planManager.getPollutantByUri(pollutantCode.getHref());
            ProtectiontargetBean ptb = planManager.getProtectiontargetBeanByProtectionTargetUri(protectionTarget.getHref());
            if (pol == null) {
                context.getValidationErrors().addGlobalError(new LocalizableError("import.error.plan.pollutanturi", HtmlUtil.encode(pollutantCode.getHref())));
            } else if (ptb == null) {
                context.getValidationErrors().addGlobalError(new LocalizableError("import.error.plan.targeturi", HtmlUtil.encode(protectionTarget.getHref()), HtmlUtil.encode(pollutantCode.getHref())));
            } else {
                ArrayList<String> list = new ArrayList<String>();
                for (ProtectiontargetBean item : planManager.getAllProtectiontargetForPlanAndPolutant(planId, pol.getUuid())) {
                    list.add(item.getUuid());
                }
                list.add(ptb.getUuid());
                planManager.saveProtectiontargetForPolutantAndPlan(planId, pol.getUuid(), list);
            }
        }
    }
}
