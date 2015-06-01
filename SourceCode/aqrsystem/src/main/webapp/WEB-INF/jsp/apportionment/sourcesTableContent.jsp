<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="sourcesGUItable">
    <thead>
        <tr>
            <th>${res['common.name']}</th>
            <th>${res['common.user']}</th>
            <th>${res['common.created']}</th>
            <th>${res['common.userLastUpdate']}</th>
            <th>${res['common.lastmodified']}</th>
            <th>${res['common.status']}</th>
            <th>${res['common.command']}</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="source" items="${actionBean.existingSourceApportionments}">
            <tr>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.EditApportionmentActionBean" title="${res['common.viewdetail']}">
                        <s:param name="sourceId" value="${source.uuid}"/>
                        ${fn:escapeXml(source.inspireidLocalid)}
                    </s:link>
                </td>
                <td>${fn:escapeXml(source.userBean.name)} ${fn:escapeXml(source.userBean.surname)}</td>
                <td><fmt:formatDate value="${source.datecreation}" pattern="dd/MM/yyyy HH:mm z"/></td>
                <td>${fn:escapeXml(source.userLastUpdateBean.name)} ${fn:escapeXml(source.userLastUpdateBean.surname)}</td>
                <td><fmt:formatDate value="${source.datelastupdate}" pattern="dd/MM/yyyy HH:mm z"/></td>
                <td>${source.completed ? res['common.complete'] : res['common.draft'] }</td>
                <td>
                    <c:choose>
                        <c:when test="${source.editable}">
                            <cust:emptyLink cssclass="ftm edit" beanclass="eu.europa.ec.aqrsystem.action.EditApportionmentActionBean" event="edit" title="${res['common.editdetail']}" idName="sourceId" idValue="${source.uuid}"/>
                        </c:when>

                        <c:otherwise>
                            <cust:emptyLink cssclass="ftm view" beanclass="eu.europa.ec.aqrsystem.action.EditApportionmentActionBean" event="edit" title="${res['common.viewdetail']}" idName="sourceId" idValue="${source.uuid}"/>
                        </c:otherwise>
                    </c:choose>

                    <security:allowed event="cloneItem">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.ApportionmentActionBean" event="cloneItem" class="ftm clone ajax-operation" title="${res['common.clone']}">
                            <s:param name="sourceId" value="${source.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </security:allowed>
                    <c:if test="${source.editable}">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.ApportionmentActionBean" event="delete" class="ftm delete confirm ajax-operation" title="${res['common.delete']}">
                            <s:param name="sourceId" value="${source.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </c:if>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.ApportionmentActionBean" event="export" class="ftm generatexml" title="${res['common.export']}">
                        <s:param name="sourceId" value="${source.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
