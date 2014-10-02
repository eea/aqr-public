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
 *  Date: 14/05/2014
 *  Authors: European Commission, Joint Research Centre
 *  Lucasz Cyra, Emanuela Epure, Daniele Francioli
 *  aq-dev@jrc.ec.europa.eu
 */
package eu.europa.ec.aqrsystem.xml;

import eu.europa.ec.aqrmodel.Attainment;
import eu.europa.ec.aqrsystem.xml.gml.FeatureMember;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.attainment.AttainmentManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * The root of the attainment XML file
 */
@XmlRootElement(name = "FeatureCollection", namespace = Namespaces.gml)
@XmlAccessorType(XmlAccessType.NONE)
public class FlowGXML implements XMLSaveableObject {

    @XmlElement(namespace = Namespaces.gml)
    protected List<FeatureMember> featureMember = new ArrayList<FeatureMember>();

    /**
     * Saving all the attainments for the given user
     *
     * @param userEmail The user
     * @param context ActionBeanContext to which errors and messages can be
     * reported
     * @throws java.lang.Exception
     */
    @Override
    public void save(String userEmail, ActionBeanContext context, ResourceBundle res) throws Exception {
        int counter = 0;
        for (FeatureMember f : featureMember) {
            if (f.containsAttainment() && f.saveAttainment(userEmail, context)) {
                counter++;
            }
        }
        context.getMessages().add(new LocalizableMessage("import.message.attainments", counter));
    }

    @Override
    public void save(String userEmail, ActionBeanContext context, ResourceBundle res, ArrayList<AttainmentBean> attainmentBean) throws Exception {
        int counter = 0;

        AttainmentManager attainmentManager = new AttainmentManager();
        for (AttainmentBean ab : attainmentBean) {

            if (attainmentManager.saveAttaimentByUserAndAttainmentBean(userEmail, ab) != null) {
                counter++;
            } else {
                context.getValidationErrors().addGlobalError(new LocalizableError("import.error.attainment.duplicate", HtmlUtil.encode(ab.getInspireidLocalid())));
            }
        }
        context.getMessages().add(new LocalizableMessage("import.message.attainments", counter));

    }

}
