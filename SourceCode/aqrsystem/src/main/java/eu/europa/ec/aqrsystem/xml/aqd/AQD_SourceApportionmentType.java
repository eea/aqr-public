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

import eu.europa.ec.sourceapprotionment.localincrement.LocalincrementBean;
import eu.europa.ec.sourceapprotionment.regionalbackground.RegionalbackgroundBean;
import eu.europa.ec.sourceapprotionment.urbanbackground.UrbanbackgroundBean;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.ExceedancedescriptionBean;
import eu.europa.ec.plan.PlanBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.attainment.AttainmentManager;
import eu.europa.ec.plan.PlanManager;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.gml.TimeInstantPropertyType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Represents aqd:AQD_SourceApportionmentType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AQD_SourceApportionmentType {

    @XmlAttribute(namespace = Namespaces.gml)
    protected String id;

    @XmlElement(namespace = Namespaces.aqd)
    protected IdentifierPropertyType inspireId = new IdentifierPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected TimeInstantPropertyType referenceYear;
    @XmlElement(namespace = Namespaces.aqd)
    protected RegionalBackgroundPropertyType regionalBackground = new RegionalBackgroundPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected UrbanBackgroundPropertyType urbanBackground = new UrbanBackgroundPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected LocalIncrementPropertyType localIncrement = new LocalIncrementPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected ExceedanceDescriptionPropertyType macroExceedanceSituation = new ExceedanceDescriptionPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String comment;
    @XmlElement(namespace = Namespaces.aqd)
    protected AQD_AttainmentPropertyType parentExceedanceSituation = new AQD_AttainmentPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected AQD_PlanPropertyType usedInPlan = new AQD_PlanPropertyType();

    /**
     * Populating the object
     *
     * @param p SourceapportionmentBean
     * @param userEmail
     */
    public void populate(SourceapportionmentBean p, String userEmail) {
        String namespace = p.getInspireidNamespace();

        id = "ATTR_" + XMLManager.removeProblematicCharacters(p.getInspireidLocalid());
        inspireId.populate(p, p.getInspireidLocalid());
        if (BaseUtils.isDefined(p.getReferenceyearTimeperiod())) {
            referenceYear = new TimeInstantPropertyType();
            referenceYear.populate(p.getReferenceyearTimeperiod(), "REFERENCE_YEAR_" + p.getUuid(), false, null);
        }
        regionalBackground.populate(p.getRegionalbackgroundBean());
        urbanBackground.populate(p.getUrbanbackgroundBean());
        localIncrement.populate(p.getLocalincrementBean());
        macroExceedanceSituation.populate(p.getExceedancedescriptionBean(), p.getInspireidNamespace());
        comment = p.getComment();

        if (p.getAttainmentBean() != null) {
            parentExceedanceSituation.populate(p.getAttainmentBean().getUuid(), namespace);
        } else {
            parentExceedanceSituation.populate(null, null);
        }

        if (p.getPlanBean() != null) {
            usedInPlan.populate(p.getPlanBean().getUuid(), namespace, userEmail);
        } else {
            usedInPlan.populate(null, null, userEmail);
        }
    }

    /**
     * Populating the SourceapportionmentBean with the data from the object
     *
     * @param p
     * @param context
     * @param email
     * @throws java.lang.Exception
     */
    public void setObject(SourceapportionmentBean p, ActionBeanContext context, String email) throws Exception {
        p.setInspireidLocalid(inspireId.getLocalId());
        if (referenceYear != null) {
            p.setReferenceyearTimeperiod(referenceYear.getValue());
        }

        RegionalbackgroundBean rbb = new RegionalbackgroundBean();
        regionalBackground.setObject(rbb);
        p.setRegionalbackgroundBean(rbb);

        UrbanbackgroundBean ubb = new UrbanbackgroundBean();
        urbanBackground.setObject(ubb);
        p.setUrbanbackgroundBean(ubb);

        LocalincrementBean lib = new LocalincrementBean();
        localIncrement.setObject(lib);
        p.setLocalincrementBean(lib);

        ExceedancedescriptionBean edb = new ExceedancedescriptionBean();
        macroExceedanceSituation.setObject(edb, p.getUuid());
        p.setExceedancedescriptionBean(edb);

        p.setComment(comment);

        if (parentExceedanceSituation != null) {
            String href = parentExceedanceSituation.getHref();
            if (href != null) {
                AttainmentBean ab = new AttainmentManager().getAttainmentBeanByINSPIRELocalID(XMLManager.convertHrefToLocalId(href), email);
                if (ab != null) {
                    p.setAttainmentBean(ab);
                } else {
                    context.getValidationErrors().addGlobalError(new LocalizableError("import.error.source.noattainment", HtmlUtil.encode(href)));
                }
            }
        }
        if (usedInPlan != null) {
            String href = usedInPlan.getHref();
            if (href != null) {
                PlanBean pb = new PlanManager().getPlanByINSPIRELocalID(XMLManager.convertHrefToLocalId(href), email);
                if (pb != null) {
                    p.setPlanBean(pb);
                } else {
                    context.getValidationErrors().addGlobalError(new LocalizableError("import.error.source.noplan", HtmlUtil.encode(href)));
                }
            }
        }
    }
}
