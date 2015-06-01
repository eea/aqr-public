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
package eu.europa.ec.aqrsystem.xml;

import eu.europa.ec.sourceapprotionment.SourceapportionmentINSPIRELocalIDAlreadyExistingException;
import eu.europa.ec.sourceapprotionment.SourceapportionmentBean;
import eu.europa.ec.sourceapprotionment.SourceapportionmentManager;
import eu.europa.ec.aqrsystem.action.EditApportionmentActionBean;
import eu.europa.ec.aqrsystem.xml.gml.FeatureMember;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.common.HeaderInterface;
import eu.europa.ec.common.relatedparty.RelatedpartyBean;
import eu.europa.ec.user.UserBean;
import eu.europa.ec.user.UserManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.util.HtmlUtil;
import net.sourceforge.stripes.validation.LocalizableError;

/**
 * The main object responsible for defining the structure of the source
 * apportionment XML files when they are exported. In XML it represents
 * gml:FeatureCollection
 */
@XmlRootElement(name = "FeatureCollection", namespace = Namespaces.gml)
@XmlAccessorType(XmlAccessType.NONE)
public class SourceXML implements XMLSaveableObject {

    @XmlAttribute(namespace = Namespaces.gml)
    protected String id;
    @XmlElement(namespace = Namespaces.gml)
    protected List<FeatureMember> featureMember = new ArrayList<FeatureMember>();

    /**
     * Populating the object using the data from the SourceapportionmentBean
     *
     * @param s
     * @param userEmail
     * @return
     */
    public SourceXML populate(SourceapportionmentBean s, String userEmail) {
        id = "Source_Apportionment";

        FeatureMember header = new FeatureMember();
        FeatureMember source = new FeatureMember();

        header.populateHeader(s);
        source.populate(s, userEmail);

        featureMember.add(header);
        featureMember.add(source);

        return this;
    }

    public SourceXML populateMultiple(final List<SourceapportionmentBean> sourceapportionmentBeans, final String fromDate, final String toDate, final String userEmail) {
        id = "Source_Apportionment";

        final UserManager userManager = new UserManager();
        final UserBean user = userManager.getUserByEmail(userEmail);

        HeaderInterface defaultHeader = new HeaderInterface() {
            @Override
            public String getUuid() {
                return user.getUuid();
            }

            @Override
            public void setUuid(String uuid) {
            }

            @Override
            public String getInspireidLocalid() {
                return new Date().toString();
            }

            @Override
            public void setInspireidLocalid(String inspireidLocalid) {
            }

            @Override
            public String getInspireidNamespace() {
                return userManager.getNamespaceByUserEmail(userEmail);
            }

            @Override
            public void setInspireidNamespace(String inspireidNamespace) {
            }

            @Override
            public String getInspireidVersionid() {
                return new Date().toString();
            }

            @Override
            public void setInspireidVersionid(String inspireidVersionid) {
            }

            @Override
            public boolean isChanges() {
                return true;
            }

            @Override
            public void setChanges(boolean changes) {
            }

            @Override
            public String getDescriptionofchanges() {
                if (!sourceapportionmentBeans.isEmpty()) {
                    return sourceapportionmentBeans.get(0).getDescriptionofchanges();
                } else {
                    return "";
                }
            }

            @Override
            public void setDescriptionofchanges(String descriptionofchanges) {
            }

            @Override
            public String getReportingstartdate() {
                return fromDate;
            }

            @Override
            public void setReportingstartdate(String reportingstartdate) {
            }

            @Override
            public String getReportingenddate() {
                return toDate;
            }

            @Override
            public void setReportingenddate(String reportingenddate) {
            }

            @Override
            public RelatedpartyBean getProviderBean() {
                return user.getProviderBean();
            }

            @Override
            public void setProviderBean(RelatedpartyBean providerBean) {
            }
        };

        FeatureMember header = new FeatureMember();
        header.populateHeader(defaultHeader);
        featureMember.add(header);

        for (SourceapportionmentBean s : sourceapportionmentBeans) {
            FeatureMember source = new FeatureMember();
            source.populate(s, userEmail);
            featureMember.add(source);
        }

        return this;
    }

    /**
     * Saving the object
     *
     * @param userEmail
     * @param context of the ActionBean
     * @throws java.lang.Exception
     */
    @Override
    public void save(String userEmail, ActionBeanContext context, ResourceBundle res) throws Exception {
        if (featureMember != null && featureMember.size() == 2 && featureMember.get(0).containsHeader() && featureMember.get(1).containsSource()) {
            SourceapportionmentManager sourceManager = new SourceapportionmentManager();
            String sourceId = sourceManager.createSourceapportionmentDraftForUser(userEmail).getUuid();
            SourceapportionmentBean source = sourceManager.getSourceapportionmentByID(sourceId, userEmail);

            featureMember.get(0).setObjectHeader(source);
            featureMember.get(1).setObject(source, context, userEmail);

            String newLocalId = source.getInspireidLocalid();
            try {
                sourceManager.saveSourceapportionmentDraft(source, userEmail);
            } catch (SourceapportionmentINSPIRELocalIDAlreadyExistingException e) {
                String oldLocalId = sourceManager.getSourceapportionmentByID(source.getUuid(), userEmail).getInspireidLocalid();
                context.getValidationErrors().addGlobalError(new LocalizableError("source.error.duplicatelocalid", HtmlUtil.encode(newLocalId), HtmlUtil.encode(oldLocalId)));
            }
            EditApportionmentActionBean.updateCompleteness(source, res);

            context.getMessages().add(new LocalizableMessage("import.message.source", HtmlUtil.encode(source.getInspireidLocalid())));
        } else {
            context.getValidationErrors().addGlobalError(new LocalizableError("import.error.plan.nosource"));
        }
    }

    @Override
    public void save(String userEmail, ActionBeanContext context, ResourceBundle res, ArrayList<AttainmentBean> localId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
