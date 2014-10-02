<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="dialog" id="usersGUINewItemCont">

    <s:errors/>

    <s:form beanclass="eu.europa.ec.aqrsystem.action.UserActionBean">
        <s:hidden name="user.uuid"/>
        <cust:label key="user.enable"/><s:checkbox name="user.enable"/><br/>
        <cust:field key="user.name"/>
        <cust:field key="user.surname"/>
        <cust:field key="user.email"/>

        <cust:label key="user.userroleBean.uuid"/>
        <s:select name="user.userroleBean.uuid">
            <s:options-collection collection="${actionBean.possibleRoles}" value="uuid" label="label"/>
        </s:select>
        <br/>

        <s:useActionBean var="settingsBean" beanclass="eu.europa.ec.aqrsystem.action.SettingsActionBean"/>
        <security:allowed bean="settingsBean" event="saveNamespace" negate="true">
            <cust:label key="user.countryBean.uuid"/>
            <s:select name="user.countryBean.uuid">
                <s:options-collection collection="${actionBean.possibleCountries}" value="uuid" label="countryname"/>
            </s:select>
        </security:allowed>
        <br/><br/><br/>
        <cust:jqueryLink cssclass="ftm cancel btn btn_dialog_cancel" label="${res['common.cancel']}"/>
        <s:submit name="save" value="${res['common.save']}" class="ftm save btn btn_dialog_save"/>
    </s:form>
</div>	
