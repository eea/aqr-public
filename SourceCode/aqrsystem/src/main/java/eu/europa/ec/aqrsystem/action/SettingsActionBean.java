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
package eu.europa.ec.aqrsystem.action;

import eu.europa.ec.aqrsystem.utils.ValidationMasks;
import eu.europa.ec.aqrsystem.xml.FlowGXML;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.XMLSaveableObject;
import eu.europa.ec.attainment.AttainmentBean;
import eu.europa.ec.attainment.DeleteAttainmentException;
import eu.europa.ec.user.EmailAlreadyInTheDBException;
import eu.europa.ec.user.UserBean;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.security.RolesAllowed;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * The action bean for handling the Settings tab of the system.
 */
@RolesAllowed({"user", "administrator", "superuser"})
public class SettingsActionBean extends BaseSettingsActionBean {

    /**
     * Location of the tab view
     */
    private static final String MAIN_VIEW = "WEB-INF/jsp/settings/tab.jsp";

    /**
     * The default action of the controller, which displays the tab.
     *
     * @return Default Stripes object.
     */
    @DefaultHandler
    public Resolution showTab() {
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * Namespace of the system
     */
    @Validate(required = true, maxlength = 100, mask = ValidationMasks.namespace, on = "save")
    protected String namespace;

    /**
     * Getter.
     *
     * @return Namespace of the system
     */
    public String getNamespace() {
        if (namespace == null) {
            namespace = userManager.getNamespaceByUserEmail(email);
        }
        return namespace;
    }

    /**
     * Setter
     *
     * @param namespace Namespace of the system
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Saving the new namespace.
     *
     * @return Default Stripes object.
     * @throws java.lang.Exception
     */
    @RolesAllowed("administrator")
    public Resolution saveNamespace() throws Exception {
        userManager.setNamespaceByUserEmail(email, namespace);
        context.getMessages().add(new LocalizableMessage("namespace.save.message"));
        return new ForwardResolution(MAIN_VIEW);
    }

    /**
     * @return the UserBean for the current user
     */
    @ValidateNestedProperties({
        @Validate(field = "providerBean.organisationname", required = true, maxlength = 200, on = "saveProvider"),
        @Validate(field = "providerBean.website", required = true, maxlength = 250, mask = ValidationMasks.url, on = "saveProvider"),
        @Validate(field = "providerBean.individualname", required = true, maxlength = 250, on = "saveProvider"),
        @Validate(field = "providerBean.address", required = true, maxlength = 200, on = "saveProvider"),
        @Validate(field = "providerBean.telephonevoice", required = true, maxlength = 50, on = "saveProvider"),
        @Validate(field = "providerBean.electronicmailaddress", required = true, maxlength = 250, converter = EmailTypeConverter.class, on = "saveProvider")
    })
    @Override
    public UserBean getCurrentUser() {
        return super.getCurrentUser();
    }

    /**
     * Saving a new provider.
     *
     * @return Default Stripes object.
     * @throws eu.europa.ec.aqrexception.EmailAlreadyInTheDBException
     * @throws java.lang.Exception
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution saveProvider() throws EmailAlreadyInTheDBException, Exception {
        userManager.updateUserByUserID(currentUser);
        context.getMessages().add(new LocalizableMessage("currentUser.providerBean.save.message"));
        return new ForwardResolution(MAIN_VIEW);
    }

    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SettingsActionBean.class);
    /**
     * The action for importing a new plan
     *
     * @return Default Stripes object.
     * @throws javax.xml.bind.JAXBException
     */
    @RolesAllowed({"user", "administrator"})
    public Resolution importData() throws JAXBException, Exception {
        if (xmlFile == null) {
            context.getValidationErrors().addGlobalError(new LocalizableError("xmlFile.error.nofile"));
//        } else if (XMLManager.xmlIsValid(context, xmlFile)) {
        } else {
            try {
                InputStream inputStream = xmlFile.getInputStream();
                String inputStreamString = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();

                ArrayList<Integer> positionsStartAttainment = new ArrayList();
                String queryStartPatternAttainment = "<aqd:AQD_Attainment";
                Pattern patternStartAttainment = Pattern.compile(queryStartPatternAttainment);
                Matcher matcherStartAttainment = patternStartAttainment.matcher(inputStreamString);
                while (matcherStartAttainment.find()) {
                    positionsStartAttainment.add(matcherStartAttainment.start());
                }

                ArrayList<Integer> positionsEndAttainment = new ArrayList();
                String queryEndPatternAttainment = "</aqd:AQD_Attainment>";
                Pattern patternEndAttainment = Pattern.compile(queryEndPatternAttainment);
                Matcher matcherEndAttainment = patternEndAttainment.matcher(inputStreamString);
                while (matcherEndAttainment.find()) {
                    positionsEndAttainment.add(matcherEndAttainment.start());
                }

                ArrayList<String> attainmentarray = new ArrayList<String>();
                for (int i = 0; i < positionsStartAttainment.size(); i++) {
                    CharSequence charSequence = inputStreamString.subSequence(positionsStartAttainment.get(i), positionsEndAttainment.get(i) + queryEndPatternAttainment.length());
                    attainmentarray.add(charSequence.toString());
                }

                ArrayList<AttainmentBean> localIdArray = new ArrayList<AttainmentBean>();
                for (int i = 0; i < attainmentarray.size(); i++) {

                    ArrayList<Integer> positionsBaseStart = new ArrayList();
                    String queryBaseStartPattern = "<base:localId>";
                    Pattern patternBaseStart = Pattern.compile(queryBaseStartPattern);
                    Matcher matcherBaseStart = patternBaseStart.matcher(attainmentarray.get(i));
                    while (matcherBaseStart.find()) {
                        positionsBaseStart.add(positionsStartAttainment.get(i) + matcherBaseStart.start());
                        break;
                    }

                    ArrayList<Integer> positionsBaseEnd = new ArrayList();
                    String queryBaseEndPattern = "</base:localId>";
                    Pattern patternBaseEnd = Pattern.compile(queryBaseEndPattern);
                    Matcher matcherBaseEnd = patternBaseEnd.matcher(attainmentarray.get(i));
                    while (matcherBaseEnd.find()) {
                        positionsBaseEnd.add(positionsStartAttainment.get(i) + matcherBaseEnd.start());
                        break;
                    }

                    ArrayList<Integer> positionsNamespaceStart = new ArrayList();
                    String queryNamespaceStartPattern = "<base:namespace>";
                    Pattern patternNamespaceStart = Pattern.compile(queryNamespaceStartPattern);
                    Matcher matcherNamespaceStart = patternNamespaceStart.matcher(attainmentarray.get(i));
                    int m = 0;
                    while (matcherNamespaceStart.find()) {
                        positionsNamespaceStart.add(positionsStartAttainment.get(i) + matcherNamespaceStart.start());
                        m++;
                        break;
                    }
                    if (m == 0) {
                        positionsNamespaceStart.add(i, -1);
                    }

                    ArrayList<Integer> positionsNamespaceEnd = new ArrayList();
                    String queryNamespaceEndPattern = "</base:namespace>";
                    Pattern patternNamespaceEnd = Pattern.compile(queryNamespaceEndPattern);
                    Matcher matcherNamespaceEnd = patternNamespaceEnd.matcher(attainmentarray.get(i));
                    int n = 0;
                    while (matcherNamespaceEnd.find()) {
                        positionsNamespaceEnd.add(positionsStartAttainment.get(i) + matcherNamespaceEnd.start());
                        n++;
                        break;
                    }
                    if (n == 0) {
                        positionsNamespaceEnd.add(i, -1);
                    }

                    int positionsVersionIdStart = 0;
                    String queryVersionIdStartPattern = "<base:versionId>";
                    Pattern patternVersionIdStart = Pattern.compile(queryVersionIdStartPattern);
                    Matcher matcherVersionIdStart = patternVersionIdStart.matcher(attainmentarray.get(i));
                    int l = 0;
                    while (matcherVersionIdStart.find()) {
                        positionsVersionIdStart = positionsStartAttainment.get(i) + matcherVersionIdStart.start();
                        l++;
                        break;
                    }
                    if (l == 0) {
                        positionsVersionIdStart = -1;
                    }

                    int positionsVersionIdEnd = 0;
                    String queryVersionIdEndPattern = "</base:versionId>";
                    Pattern patternVersionIdEnd = Pattern.compile(queryVersionIdEndPattern);
                    Matcher matcherVersionIdEnd = patternVersionIdEnd.matcher(attainmentarray.get(i));
                    int k = 0;
                    while (matcherVersionIdEnd.find()) {
                        positionsVersionIdEnd = positionsStartAttainment.get(i) + matcherVersionIdEnd.start();
                        k++;
                        break;
                    }
                    if (k == 0) {
                        positionsVersionIdEnd =  -1;
                    }
                    
//                    for (int j = 0; j < positionsBaseStart.size(); j++) {
                        AttainmentBean attainmentBean = new AttainmentBean();

                        CharSequence charSequenceLocalID = inputStreamString.subSequence(positionsBaseStart.get(0) + queryBaseStartPattern.length(), positionsBaseEnd.get(0));
                        String localId = charSequenceLocalID.toString();
                        attainmentBean.setInspireidLocalid(localId);

                        if (positionsNamespaceStart.get(0) != null && positionsNamespaceEnd.get(0) != null) {
                            CharSequence charSequenceNamspace = inputStreamString.subSequence(positionsNamespaceStart.get(0) + queryNamespaceStartPattern.length(), positionsNamespaceEnd.get(0));
                            String namespace = charSequenceNamspace.toString();
                            attainmentBean.setInspireidNamespace(namespace);
                        }

                        if (positionsVersionIdStart != -1 && positionsVersionIdEnd != -1) {
                            CharSequence charSequenceVersionId = inputStreamString.subSequence(positionsVersionIdStart + queryVersionIdStartPattern.length(), positionsVersionIdEnd);
                            String versionId = charSequenceVersionId.toString();
                            attainmentBean.setInspireidVersionid(versionId);
                        }

                        localIdArray.add(attainmentBean);
//                    }
                }

                JAXBContext jc = JAXBContext.newInstance(FlowGXML.class);
                Unmarshaller u = jc.createUnmarshaller();
                XMLSaveableObject xml = (XMLSaveableObject) u.unmarshal(xmlFile.getInputStream());
                xml.save(email, context, res, localIdArray);
            } catch (IOException e) {
                context.getValidationErrors().addGlobalError(new LocalizableError("xmlFile.error.nofile"));
            }
        }

        return new RedirectResolution(getClass());
    }

    /**
     * This variable is here just to prevent Stripes warnings related to the
     * name of the select with attainments.
     */
    public final String attainments = null;

    /**
     * The location of the table content view.
     */
    private static final String TABLE_CONTENT = "WEB-INF/jsp/settings/settingsTableContent.jsp";

    /**
     * An event handler that renders the table.
     *
     * @return The table view.
     */
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT);
    }

    /**
     * The action for deleting a plan
     *
     * @return Default Stripes object.
     */
    @RolesAllowed({"user", "administrator", "superuser"})
    public Resolution delete() {
        try {
            attainmentManager.deleteAttainmentByAttaimentID(attainmentId);
        } catch (DeleteAttainmentException e) {
            return ajaxError("attainment.error.delete");
        }
        return ajaxSuccess();
    }

    /**
     * @return The current plan.
     */
    @ValidateNestedProperties({
        @Validate(field = "inspireidLocalid", required = true, maxlength = 100, mask = ValidationMasks.inspireId, on = "save")
    })
    @Override
    public AttainmentBean getAttainment() {
        return super.getAttainment();
    }

}
