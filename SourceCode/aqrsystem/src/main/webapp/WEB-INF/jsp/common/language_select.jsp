<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div id="widget-lang_select">
    <s:form id="langChange" beanclass="eu.europa.ec.aqrsystem.action.LocalisationActionBean">
        <s:select name="currentLanguage" value="${actionBean.currentLanguage}">
            <s:options-collection collection="${actionBean.availableLanguages}" value="localeCode" label="localeLabel"/>
        </s:select>
    </s:form>
</div>
