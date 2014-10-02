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
import eu.europa.ec.common.publication.PublicationBean;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.attainment.AttainmentManager;
import eu.europa.ec.plan.PlanManager;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.base2.RelatedPartyPropertyType;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import eu.europa.ec.aqrsystem.xml.gml.TimeInstantPropertyType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Represents aqd:AQD_PlanType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AQD_PlanType {

    @XmlAttribute(namespace = Namespaces.gml)
    protected String id;

    @XmlElement(namespace = Namespaces.aqd)
    protected IdentifierPropertyType inspireId = new IdentifierPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String code;
    @XmlElement(namespace = Namespaces.aqd)
    protected String name;
    @XmlElement(namespace = Namespaces.aqd)
    protected RelatedPartyPropertyType competentAuthority = new RelatedPartyPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected TimeInstantPropertyType firstExceedanceYear = new TimeInstantPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected ReferenceType status = new ReferenceType();
    @XmlElement(namespace = Namespaces.aqd)
    protected List<PollutantPropertyType> pollutants = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected TimeInstantPropertyType adoptionDate = new TimeInstantPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String timeTable;
    @XmlElement(namespace = Namespaces.aqd)
    protected String referenceAQPlan;
    @XmlElement(namespace = Namespaces.aqd)
    protected String referenceImplementation;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<PublicationPropertyType> publication = new ArrayList();
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<AQD_AttainmentPropertyType> exceedanceSituation = new ArrayList();

    /**
     * Populating the object
     *
     * @param p Plan
     */
    public void populate(PlanBean p) {
        PlanManager planManager = new PlanManager();
        String planId = p.getUuid();
        String namespace = p.getInspireidNamespace();

        id = "ATTR_" + XMLManager.removeProblematicCharacters(p.getInspireidLocalid());
        inspireId.populate(p, p.getInspireidLocalid());
        code = p.getCode();
        name = p.getName();
        competentAuthority.populate(p.getRelatedpartyBean());
        firstExceedanceYear.populate(p.getFirstexceedanceyearTimeposition(), "FIRST_EXCEEDANCE_YEAR_" + p.getUuid(), false, null);
        status.setHref(p.getLink(), false);
        for (PollutantBean pol : planManager.getAllPollutantByPlan(planId)) {
            for (ProtectiontargetBean pt : planManager.getAllProtectiontargetForPlanAndPolutant(planId, pol.getUuid())) {
                pollutants.add(new PollutantPropertyType().populate(pol, pt));
            }
        }

        if (pollutants.isEmpty()) {
            pollutants.add(new PollutantPropertyType().populate(null, null));
        }

        adoptionDate.populate(p.getAdoptiondateTimeposition(), "ADOPTION_DATE_" + p.getUuid(), false, null);
        timeTable = p.getTimetable();
        referenceAQPlan = p.getReferenceaqplan();
        referenceImplementation = p.getReferenceimplementation();
        for (PublicationBean pub : planManager.getAllPublicationByPlan(planId)) {
            publication.add(new PublicationPropertyType().populate(pub));
        }

        if (publication.isEmpty()) {
            publication.add(new PublicationPropertyType().populate(null));
        }

        comment = p.getComment();
        for (String id : p.getAttainmentBeanList()) {
            exceedanceSituation.add(new AQD_AttainmentPropertyType().populate(id, namespace));
        }

        if (exceedanceSituation.isEmpty()) {
            exceedanceSituation.add(new AQD_AttainmentPropertyType().populate(null, null));
        }
    }

    /**
     * Populating the PlanBean with the data from the object
     *
     * @param p
     * @param context
     * @param email
     * @throws java.lang.Exception
     */
    public void setObject(PlanBean p, ActionBeanContext context, String email) throws Exception {
        p.setInspireidLocalid(inspireId.getLocalId());
        p.setCode(code);
        p.setName(name);
        competentAuthority.setObject(p.getRelatedpartyBean());
        p.setFirstexceedanceyearTimeposition(firstExceedanceYear.getValue());
        p.setLink(status.getHref());
        if (pollutants != null) {
            for (PollutantPropertyType pol : pollutants) {
                pol.save(p.getUuid(), context);
            }
        }
        p.setAdoptiondateTimeposition(adoptionDate.getValue());
        p.setTimetable(timeTable);
        p.setReferenceaqplan(referenceAQPlan);
        p.setReferenceimplementation(referenceImplementation);

        if (publication != null) {
            for (PublicationPropertyType pub : publication) {
                pub.saveToPlan(p.getUuid());
            }
        }

        p.setComment(comment);

        if (exceedanceSituation != null) {
            for (AQD_AttainmentPropertyType att : exceedanceSituation) {
                String href = att.getHref();
                if (href != null) {
                    AttainmentBean ab = new AttainmentManager().getAttainmentBeanByINSPIRELocalID(XMLManager.convertHrefToLocalId(href), email);
                    if (ab != null) {
                        p.getAttainmentBeanList().add(ab.getUuid());
                    } else {
                        context.getValidationErrors().addGlobalError(new LocalizableError("import.error.plan.noattainment", HtmlUtil.encode(href)));
                    }
                }
            }
        }
    }
}
