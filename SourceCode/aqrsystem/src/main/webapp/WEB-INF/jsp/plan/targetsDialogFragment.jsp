<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:form beanclass="eu.europa.ec.aqrsystem.action.PollutantActionBean" partial="true">
    <s:select class="multi" multiple="true" name="targetIds" id="target-select">
        <s:options-collection collection="${actionBean.possibleTargets}" value="uuid" label="label"/>
    </s:select>
</s:form>
