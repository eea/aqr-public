<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout.jsp">
    <s:layout-component name="content">
        <h2>${res['error.standardmessage']}</h2>
        <h2 class="error_link">
            <s:link href="/">${res['error.gotohomepage']}</s:link>
            </h2>
    </s:layout-component>
</s:layout-render>