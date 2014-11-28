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
package eu.europa.ec.sourceapprotionment.exceedancedescription;

import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedanceexposure.ExceedanceexposureBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedancearea.ExceedanceareaBean;
import eu.europa.ec.sourceapprotionment.exceedancedescription.deductionassessmentmethod.DeductionassessmentmethodBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ExceedancedescriptionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private Boolean exceedance;
    private String numericalexceedance;
    private String numberexceedances;
    private String otherreason;
    private String comment;
    private Date datecreation;
    private Date datelastupdate;
    private List<String> reasonvalueList_uri;
    private DeductionassessmentmethodBean deductionassessmentmethodBean;
    private ExceedanceareaBean exceedanceareaBean;
    private ExceedanceexposureBean exceedenceexposureBean;

    public ExceedancedescriptionBean() {
    }

    public ExceedancedescriptionBean(String uuid) {
        this.uuid = uuid;
    }

    public ExceedancedescriptionBean(String uuid, Date datecreation) {
        this.uuid = uuid;
        this.datecreation = datecreation;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getExceedance() {
        return exceedance;
    }

    public void setExceedance(Boolean exceedance) {
        this.exceedance = exceedance;
    }

    public String getNumberexceedances() {
        return numberexceedances;
    }

    public void setNumberexceedances(String numberexceedances) {
        this.numberexceedances = numberexceedances;
    }

    public String getNumericalexceedance() {
        return numericalexceedance;
    }

    public void setNumericalexceedance(String numericalexceedance) {
        this.numericalexceedance = numericalexceedance;
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

    public String getOtherreason() {
        return otherreason;
    }

    public void setOtherreason(String otherreason) {
        this.otherreason = otherreason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ExceedanceexposureBean getExceedenceexposureBean() {
        return exceedenceexposureBean;
    }

    public void setExceedenceexposureBean(ExceedanceexposureBean exceedenceexposureBean) {
        this.exceedenceexposureBean = exceedenceexposureBean;
    }

    public ExceedanceareaBean getExceedanceareaBean() {
        return exceedanceareaBean;
    }

    public void setExceedanceareaBean(ExceedanceareaBean exceedanceareaBean) {
        this.exceedanceareaBean = exceedanceareaBean;
    }

    public DeductionassessmentmethodBean getDeductionassessmentmethodBean() {
        return deductionassessmentmethodBean;
    }

    public void setDeductionassessmentmethodBean(DeductionassessmentmethodBean deductionassessmentmethodBean) {
        this.deductionassessmentmethodBean = deductionassessmentmethodBean;
    }

    public List<String> getReasonvalueList_uri() {
        return reasonvalueList_uri;
    }

    public void setReasonvalueList_uri(List<String> reasonvalueList_uri) {
        this.reasonvalueList_uri = reasonvalueList_uri;
    }
}
