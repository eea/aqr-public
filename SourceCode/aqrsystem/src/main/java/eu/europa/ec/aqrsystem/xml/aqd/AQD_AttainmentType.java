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
 *  Date: Expected method. date?string evaluated instead to freemarker.template.SimpleScalar on line 17, column 26 in Templates/Licenses/license-aq-license.txt.
 *  Authors: European Commission, Joint Research Centre
*  Lucasz Cyra, Emanuela Epure, Daniele Francioli
 *  aq-dev@jrc.ec.europa.eu
 */

package eu.europa.ec.aqrsystem.xml.aqd;


import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.attainment.AttainmentManager;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * Represents aqd:AQD_AttainmentType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AQD_AttainmentType {
    @XmlElement(namespace=Namespaces.aqd)
    protected IdentifierPropertyType inspireId = new IdentifierPropertyType();

    /**
     * Saving an attainment
     * @param userEmail The email of the user who imports the attainment
     * @param context ActionBeanContext to which errors and messages can be
     * reported
     * @param inspireLocalID
     * @return imported/not imported due to a problem
     * @throws java.lang.Exception
     */
    public boolean save(String userEmail, ActionBeanContext context) throws Exception {
//        if(inspireId.getLocalId() == null) return false; // as it means that this object is not an attainment
        AttainmentBean ab = new AttainmentBean();

        ab.setInspireidLocalid(inspireId.getLocalId());
//        ab.setInspireidNamespace(inspireId.getNamespace());
//        ab.setInspireidVersionid(inspireId.getVersionId());

        if(new AttainmentManager().saveAttaimentByUserAndAttainmentBean(userEmail, ab) == null) {
            context.getValidationErrors().addGlobalError(new LocalizableError("import.error.attainment.duplicate", HtmlUtil.encode(inspireId.getLocalId())));
            return false;
        }

        return true;
    }
}
