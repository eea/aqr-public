<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="dialog" id="costNewItemCont">

    <s:errors/>

    <s:form beanclass="eu.europa.ec.aqrsystem.action.CostActionBean">
        <s:hidden name="measureId" value="actionBean.measureId"/>
        <cust:nilField key="cost.extimatedimplementationcosts" linkedField2="cost.finalimplementationcosts" linkedField="cost.currency_uri" linkedFieldCollection="${actionBean.possibleCurrencies}" linkedFieldCollectionURL="http://dd.eionet.europa.eu/vocabulary/common/currencies"/>
        <cust:textArea key="cost.comment" optional="true"/>
        <br/><br/><br/>
        <cust:jqueryLink cssclass="ftm cancel btn btn_dialog_cancel" label="${res['common.cancel']}"/>
        <s:submit name="save" value="${res['common.save']}" class="ftm save btn btn_dialog_save"/>
    </s:form>    
</div>	