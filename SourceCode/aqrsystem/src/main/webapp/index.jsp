<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<s:url var="url" beanclass="eu.europa.ec.aqrsystem.action.LoginActionBean" prependContext="false"/>
<jsp:forward page="${url}"/>