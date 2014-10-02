<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout.jsp">
    <s:layout-component name="content">
        <s:messages/>
        <s:errors/>

        <p>${res['loginpage.explanation']}</p>
        <br/><br/>
        <s:link class="ftm insert btn login" beanclass="eu.europa.ec.aqrsystem.action.HomeActionBean">${res['loginpage.button']}</s:link>

    </s:layout-component>
</s:layout-render>