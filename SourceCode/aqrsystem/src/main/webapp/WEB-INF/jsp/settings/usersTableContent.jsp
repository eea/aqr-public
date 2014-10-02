<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="usersGUItable">
    <thead>
        <tr>
            <th>${res['settings.user.name']}</th>
            <th>${res['settings.user.affiliation']}</th>
            <th>${res['settings.user.role']}</th>
            <th>${res['settings.user.email']}</th>
            <th>${res['settings.user.active']}</th>
            <th>${res['common.command']}</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${actionBean.existingUsers}">
            <tr>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.UserActionBean" event="form" class="edit-item">
                        <s:param name="userId" value="${user.uuid}"/>
                        ${fn:escapeXml(user.surname)} ${fn:escapeXml(user.name)}
                    </s:link>
                </td>
                <td>${user.countryBean.countryname}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.userroleBean.rolename == 'superuser'}">${res['settings.user.superuser']}</c:when>
                        <c:when test="${user.userroleBean.rolename == 'administrator'}">${res['settings.user.administrator']}</c:when>
                        <c:otherwise>${res['settings.user.user']}</c:otherwise>
                    </c:choose>
                </td>
                <td><a href="mailto:${fn:escapeXml(user.email)}">${fn:escapeXml(user.email)}</a></td>
                <td class="center"><img src="${user.enable ? 'img/tableicons/tick.png' : 'img/tableicons/cross.png'}"/></td>
                <td>                    
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.UserActionBean" event="form" class="ftm edit edit-item" title="${res['common.edit']}">
                        <s:param name="userId" value="${user.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.UserActionBean" event="enable" class="ftm ${user.enable ? 'disable' : 'enable'} ajax-operation confirm" title="${res[user.enable ? 'common.disable' : 'common.enable']}">
                        <s:param name="userId" value="${user.uuid}"/>
                        <s:param name="enable" value="${!user.enable}"/>
                        <div class="hidden"></div>
                    </s:link>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
