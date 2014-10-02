<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div class="mainmenu-cnt">
    <ul class="mainmenu">
        <c:forEach var="section" items="${actionBean.sections}">
            <li><s:link class="${section eq actionBean.currentSection ? 'sel' : ''}" beanclass="${section.beanclass}">${res[section.textKey]}</s:link></li>
            </c:forEach>
    </ul>
</div>
