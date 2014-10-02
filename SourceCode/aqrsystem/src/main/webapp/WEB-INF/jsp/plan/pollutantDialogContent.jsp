<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="dialog" id="pollutantGUINewItemCont">

    <s:errors/>

    <s:link id="get-targets-link" beanclass="eu.europa.ec.aqrsystem.action.PollutantActionBean" event="formTargetsFragment" class="hidden">
        <s:param name="planId" value="${actionBean.planId}"/>
    </s:link>
    <s:form beanclass="eu.europa.ec.aqrsystem.action.PollutantActionBean">
        <s:hidden name="planId" value="actionBean.planId"/>
        <cust:label key="pollutant.uuid"/>
        <s:select name="pollutant.uuid"  id="pollutant-select">
            <s:options-collection collection="${actionBean.possiblePollutants}" value="uuid" label="label"/>
        </s:select>
        <c:if test="${actionBean.pollutant != null}"> <!-- As disabled fields are not passed by the form -->
            <s:hidden name="pollutant.uuid" value="pollutant.uuid"/>
        </c:if>
        <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/pollutant"/>

        <cust:label key="targetIds"/>
        <%@include file="targetsDialogFragment.jsp" %>
        <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/aq/protectiontarget"/>
        <br/>
        <cust:jqueryLink cssclass="ftm cancel btn btn_dialog_cancel" label="${res['common.cancel']}"/>
        <security:allowed event="save">
            <s:submit name="save" value="${res['common.save']}" class="ftm save btn btn_dialog_save"/>
        </security:allowed>
    </s:form>    
</div>	
