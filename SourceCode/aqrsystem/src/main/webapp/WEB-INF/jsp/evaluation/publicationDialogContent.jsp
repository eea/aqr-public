<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="dialog" id="publicationGUINewItemCont">

    <s:errors/>

    <s:form beanclass="eu.europa.ec.aqrsystem.action.PublicationForEvaluationActionBean">
        <s:hidden name="evaluationId" value="actionBean.evaluationId"/>
        <s:hidden name="publication.uuid"/>
        <cust:field key="publication.description"/>
        <cust:field key="publication.title"/>
        <cust:field key="publication.author" optional="true"/>
        <cust:field key="publication.publicationdateTimeposition"/>
        <cust:field key="publication.publisher"/>
        <cust:field key="publication.weblink" optional="true"/>
        <br/><br/><br/>
        <cust:jqueryLink cssclass="ftm cancel btn btn_dialog_cancel" label="${res['common.cancel']}"/>
        <security:allowed event="save">
            <s:submit name="save" value="${res['common.save']}" class="ftm save btn btn_dialog_save"/>
        </security:allowed>
    </s:form>    
</div>	
