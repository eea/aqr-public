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

import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedanceexposure.ExceedanceexposureWrapper;
import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedancearea.ExceedanceareaWrapper;
import eu.europa.ec.sourceapprotionment.exceedancedescription.deductionassessmentmethod.DeductionassessmentmethodWrapper;
import eu.europa.ec.sourceapprotionment.SourceapportionmentManager;
import eu.europa.ec.aqrmodel.Deductionassessmentmethod;
import eu.europa.ec.aqrmodel.Exceedancearea;
import eu.europa.ec.aqrmodel.Exceedancedescription;
import eu.europa.ec.aqrmodel.Exceedanceexposure;
import eu.europa.ec.aqrmodel.Reasonvalue;
import eu.europa.ec.aqrmodel.Sourceapportionment;
import eu.europa.ec.aqrmodeluser.Users;
import eu.europa.ec.util.DateFormatUtil;
import eu.europa.ec.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExceedancedescriptionWrapper {

    private static final DateFormatUtil dateFormatUtil = new DateFormatUtil();

    public ExceedancedescriptionWrapper() {
    }

    public static ExceedancedescriptionBean convertExceedancedescriptionInExceedancedescriptionBean(Exceedancedescription exceedancedescription, Users user) {
        ExceedancedescriptionBean exceedancedescriptionBean = new ExceedancedescriptionBean();

        exceedancedescriptionBean.setUuid(exceedancedescription.getUuid());

        exceedancedescriptionBean.setExceedance(exceedancedescription.getExceedance());
        exceedancedescriptionBean.setNumericalexceedance(exceedancedescription.getNumericalexceedance());
        exceedancedescriptionBean.setNumberexceedances(exceedancedescription.getNumberexceedances());

        SourceapportionmentManager sourceapportionmentManager = new SourceapportionmentManager();
        ArrayList<String> reasonUriList = new ArrayList<String>();
        List<Reasonvalue> reasonvalueList = sourceapportionmentManager.getReasonvalueListByExceedancedescription(exceedancedescription);
        for (Reasonvalue reasonvalue : reasonvalueList) {
            reasonUriList.add(reasonvalue.getUri());
        }
        exceedancedescriptionBean.setReasonvalueList_uri(reasonUriList);
        exceedancedescriptionBean.setOtherreason(exceedancedescription.getOtherreason());
        exceedancedescriptionBean.setComment(exceedancedescription.getComment());

        exceedancedescriptionBean.setDatecreation(exceedancedescription.getDatecreation());
        exceedancedescriptionBean.setDatelastupdate(exceedancedescription.getDatelastupdate());

        exceedancedescriptionBean.setDeductionassessmentmethodBean(DeductionassessmentmethodWrapper.convertDeductionassessmentmethodBeanInDeductionassessmentmethodBeanBean(exceedancedescription.getDeductionassessmentmethod()));
        exceedancedescriptionBean.setExceedanceareaBean(ExceedanceareaWrapper.convertExceedanceareaInExceedanceareaBean(exceedancedescription.getExceedancearea()));
        exceedancedescriptionBean.setExceedenceexposureBean(ExceedanceexposureWrapper.convertExceedanceexposureInExceedanceexposureBean(exceedancedescription.getExceedanceexposure()));

        return exceedancedescriptionBean;
    }

    public static Exceedancedescription createDraftExceedancedescriptionForSourceapportionment(Sourceapportionment sourceapportionment) throws Exception {
        Exceedancedescription exceedancedescription = new Exceedancedescription();

        String exceedancedescriptionUuid = StringUtils.createUUID(sourceapportionment + dateFormatUtil.getToday(), Exceedancedescription.class);
        exceedancedescription.setUuid(exceedancedescriptionUuid);
        exceedancedescription.setExceedance(Boolean.FALSE);

        Exceedanceexposure exceedanceexposure = new Exceedanceexposure();
        String exceedanceexposureUuid = StringUtils.createUUID(exceedancedescriptionUuid + dateFormatUtil.getToday(), Exceedanceexposure.class);
        exceedanceexposure.setUuid(exceedanceexposureUuid);
        exceedancedescription.setExceedanceexposure(exceedanceexposure);

        Deductionassessmentmethod deductionassessmentmethod = new Deductionassessmentmethod();
        String deductionassessmentmethodUuid = StringUtils.createUUID(exceedancedescriptionUuid + dateFormatUtil.getToday(), Deductionassessmentmethod.class);
        deductionassessmentmethod.setUuid(deductionassessmentmethodUuid);
        deductionassessmentmethod.setAdjustmenttype(null);
        exceedancedescription.setDeductionassessmentmethod(deductionassessmentmethod);

        Exceedancearea exceedancearea = new Exceedancearea();
        String exceedanceareaUuid = StringUtils.createUUID(exceedanceexposureUuid + dateFormatUtil.getToday(), Exceedancearea.class);
        exceedancearea.setUuid(exceedanceareaUuid);
        exceedancedescription.setExceedancearea(exceedancearea);

        exceedancedescription.setDatecreation(new Date());
        exceedancedescription.setDatelastupdate(new Date());

        return exceedancedescription;
    }
}
