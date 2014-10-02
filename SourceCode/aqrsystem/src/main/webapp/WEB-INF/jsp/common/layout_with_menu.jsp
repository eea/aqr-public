<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-definition>
    <s:layout-render name="/WEB-INF/jsp/common/layout.jsp">
        <s:layout-component name="content">
            <cust:include
                beanclass="eu.europa.ec.aqrsystem.action.MenuViewHelper"
                name1="currentSection"
                value1="${actionBean.currentSection}">
            </cust:include>
            <s:messages/>
            <s:errors/>
            ${content}
        </s:layout-component>
    </s:layout-render>
</s:layout-definition>