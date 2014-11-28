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
package eu.europa.ec.attainment;

import eu.europa.ec.common.HeaderInterface;
import eu.europa.ec.plan.pollutant.PollutantBean;
import eu.europa.ec.common.relatedparty.RelatedpartyBean;
import eu.europa.ec.user.UserBean;
import java.util.Date;

public class AttainmentBean implements HeaderInterface {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private String inspireidLocalid;
    private String inspireidNamespace;
    private String inspireidVersionid;
    private String comment;
    private boolean editable;
    private RelatedpartyBean providerBean;
    private String validityperiodId;
    private Date validityperiodBeginposition;
    private Date validityperiodEndposition;
    private Date datecreation;
    private Date datelastupdate;
    private boolean changes;
    private String descriptionofchanges;
    private String reportingstartdate;
    private String reportingenddate;
    private PollutantBean pollutantBean;
    private EnvironmentalobjectiveBean environmentalobjectBean;
    private UserBean userBean;

    public AttainmentBean() {
    }

    public AttainmentBean(String uuid) {
        this.uuid = uuid;
    }

    public AttainmentBean(String uuid, String inspireidLocalid, String inspireidNamespace, String inspireidVersionid, Date datecreation) {
        this.uuid = uuid;
        this.inspireidLocalid = inspireidLocalid;
        this.inspireidNamespace = inspireidNamespace;
        this.inspireidVersionid = inspireidVersionid;
        this.datecreation = datecreation;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getValidityperiodId() {
        return validityperiodId;
    }

    public void setValidityperiodId(String validityperiodId) {
        this.validityperiodId = validityperiodId;
    }

    public Date getValidityperiodBeginposition() {
        return validityperiodBeginposition;
    }

    public void setValidityperiodBeginposition(Date validityperiodBeginposition) {
        this.validityperiodBeginposition = validityperiodBeginposition;
    }

    public Date getValidityperiodEndposition() {
        return validityperiodEndposition;
    }

    public void setValidityperiodEndposition(Date validityperiodEndposition) {
        this.validityperiodEndposition = validityperiodEndposition;
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

    @Override
    public RelatedpartyBean getProviderBean() {
        return providerBean;
    }

    @Override
    public void setProviderBean(RelatedpartyBean provierBean) {
        this.providerBean = provierBean;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEnvironmentalobjectBean(EnvironmentalobjectiveBean environmentalobjectBean) {
        this.environmentalobjectBean = environmentalobjectBean;
    }

    public EnvironmentalobjectiveBean getEnvironmentalobjectBean() {
        return environmentalobjectBean;
    }

    public void setPollutantBean(PollutantBean pollutantBean) {
        this.pollutantBean = pollutantBean;
    }

    public PollutantBean getPollutantBean() {
        return pollutantBean;
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
