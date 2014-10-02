<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="attainmentGUItable">
    <thead>
        <tr>
            <th>${res['attainments.localid']}</th>
            <th>${res['attainments.namespace']}</th>
            <th>${res['attainments.versionid']}</th>
            <th>${res['attainments.name']}</th>
            <th>${res['common.command']}</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="attainment" items="${actionBean.existingAttainments}">
            <tr>
                <td>${fn:escapeXml(attainment.inspireidLocalid)}</td>
                <td>${fn:escapeXml(attainment.inspireidNamespace)}</td>
                <td>${fn:escapeXml(attainment.inspireidVersionid)}</td>
                <td>${fn:escapeXml(attainment.userBean.name)} ${fn:escapeXml(attainment.userBean.surname)}</td>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.SettingsActionBean" event="delete" class="ftm delete confirm ajax-operation" title="${res['common.delete']}">
                        <s:param name="attainmentId" value="${attainment.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
