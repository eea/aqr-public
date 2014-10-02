<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="dialog" id="assessmentGUINewItemCont">

    <s:errors/>

    <s:form beanclass="eu.europa.ec.aqrsystem.action.AssessmentActionBean">
        <s:hidden name="sourceId" value="actionBean.sourceId"/>
        <s:hidden name="assessment.uuid"/>
        <cust:label key="assessment.assesmenttypeBean.uri"/>
        <s:select name="assessment.assesmenttypeBean.uri">
            <s:options-collection collection="${actionBean.possibleAssessmentTypes}" value="uri" label="label"/>
        </s:select>
        <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/assessmenttype"/>
        <cust:field key="assessment.assesmenttypedescription"/>
        <cust:tagItField key="assessment.metadata" optional="true"/>
        <br/><br/><br/>
        <cust:jqueryLink cssclass="ftm cancel btn btn_dialog_cancel" label="${res['common.cancel']}"/>
        <security:allowed event="save">
            <s:submit name="save" value="${res['common.save']}" class="ftm save btn btn_dialog_save"/>
        </security:allowed>
    </s:form>    
</div>	
