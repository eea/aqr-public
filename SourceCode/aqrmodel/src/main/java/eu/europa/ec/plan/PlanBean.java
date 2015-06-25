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
package eu.europa.ec.plan;

import eu.europa.ec.common.relatedparty.RelatedpartyBean;
import eu.europa.ec.common.HeaderInterface;
import eu.europa.ec.user.UserBean;
import java.util.Date;
import java.util.List;

public class PlanBean implements HeaderInterface {

    private String uuid;
    private String inspireidLocalid;
    private String inspireidNamespace;
    private String inspireidVersionid;
    private String code;
    private String name;
    private String firstexceedanceyearId;
    private String firstexceedanceyearTimeposition;
    private String adoptiondateId;
    private String adoptiondateTimeposition;
    private String timetable;
    private String referenceaqplan;
    private String referenceimplementation;
    private String comment;
    private Date datecreation;
    private Date datelastupdate;
    private String preferredLabel;
    private String link;
    private boolean completed;
    private boolean editable;
    private RelatedpartyBean providerBean;
    private RelatedpartyBean relatedpartyBean;
    private boolean changes;
    private String descriptionofchanges;
    private String reportingstartdate;
    private String reportingenddate;
    private List<String> attainmentBeanList;
    private UserBean userBean;
    private UserBean userLastUpdateBean;

    public PlanBean() {
    }

    public PlanBean(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getInspireidLocalid() {
        return inspireidLocalid;
    }

    @Override
    public void setInspireidLocalid(String inspireidLocalid) {
        this.inspireidLocalid = inspireidLocalid;
    }

    @Override
    public String getInspireidNamespace() {
        return inspireidNamespace;
    }

    @Override
    public void setInspireidNamespace(String inspireidNamespace) {
        this.inspireidNamespace = inspireidNamespace;
    }

    @Override
    public String getInspireidVersionid() {
        return inspireidVersionid;
    }

    @Override
    public void setInspireidVersionid(String inspireidVersionid) {
        this.inspireidVersionid = inspireidVersionid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getFirstexceedanceyearId() {
        return firstexceedanceyearId;
    }

    public void setFirstexceedanceyearId(String firstexceedanceyearId) {
        this.firstexceedanceyearId = firstexceedanceyearId;
    }

    public String getFirstexceedanceyearTimeposition() {
        return firstexceedanceyearTimeposition;
    }

    public void setFirstexceedanceyearTimeposition(String firstexceedanceyearTimeposition) {
        this.firstexceedanceyearTimeposition = firstexceedanceyearTimeposition;
    }

    public String getAdoptiondateId() {
        return adoptiondateId;
    }

    public void setAdoptiondateId(String adoptiondateId) {
        this.adoptiondateId = adoptiondateId;
    }

    public String getAdoptiondateTimeposition() {
        return adoptiondateTimeposition;
    }

    public void setAdoptiondateTimeposition(String adoptiondateTimeposition) {
        this.adoptiondateTimeposition = adoptiondateTimeposition;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getReferenceaqplan() {
        return referenceaqplan;
    }

    public void setReferenceaqplan(String referenceaqplan) {
        this.referenceaqplan = referenceaqplan;
    }

    public String getReferenceimplementation() {
        return referenceimplementation;
    }

    public void setReferenceimplementation(String referenceimplementation) {
        this.referenceimplementation = referenceimplementation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDatelastupdate() {
        return datelastupdate;
    }

    public void setDatelastupdate(Date datelastupdate) {
        this.datelastupdate = datelastupdate;
    }

    public String getPreferredLabel() {
        return preferredLabel;
    }

    public void setPreferredLabel(String preferredLabel) {
        this.preferredLabel = preferredLabel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public RelatedpartyBean getProviderBean() {
        return providerBean;
    }

    @Override
    public void setProviderBean(RelatedpartyBean providerBean) {
        this.providerBean = providerBean;
    }

    public RelatedpartyBean getRelatedpartyBean() {
        return relatedpartyBean;
    }

    public void setRelatedpartyBean(RelatedpartyBean relatedpartyBean) {
        this.relatedpartyBean = relatedpartyBean;
    }

    @Override
    public boolean isChanges() {
        return changes;
    }

    @Override
    public void setChanges(boolean changes) {
        this.changes = changes;
    }

    @Override
    public String getDescriptionofchanges() {
        return descriptionofchanges;
    }

    @Override
    public void setDescriptionofchanges(String descriptionofchanges) {
        this.descriptionofchanges = descriptionofchanges;
    }

    @Override
    public String getReportingstartdate() {
        return reportingstartdate;
    }

    @Override
    public void setReportingstartdate(String reportingstartdate) {
        this.reportingstartdate = reportingstartdate;
    }

    @Override
    public String getReportingenddate() {
        return reportingenddate;
    }

    @Override
    public void setReportingenddate(String reportingenddate) {
        this.reportingenddate = reportingenddate;
    }

    public List<String> getAttainmentBeanList() {
        return attainmentBeanList;
    }

    public void setAttainmentBeanList(List<String> attainmentBeanList) {
        this.attainmentBeanList = attainmentBeanList;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserBean getUserLastUpdateBean() {
        return userLastUpdateBean;
    }

    public void setUserLastUpdateBean(UserBean userLastUpdateBean) {
        this.userLastUpdateBean = userLastUpdateBean;
    }
    
}