<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="sourcesGUItable">
    <thead>
        <tr>
            <th>${res['common.name']}</th>
            <th>${res['common.created']}</th>
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
                <td><fmt:formatDate value="${source.datecreation}" pattern="yy/MM/dd HH:mm z"/></td>
                <td><fmt:formatDate value="${source.datelastupdate}" pattern="yy/MM/dd HH:mm z"/></td>
                <td>${source.completed ? res['common.complete'] : res['common.draft'] }</td>
                <td>
                    <cust:emptyLink cssclass="ftm edit" beanclass="eu.europa.ec.aqrsystem.action.EditApportionmentActionBean" event="edit" title="${res['common.edit']}" idName="sourceId" idValue="${source.uuid}"/>
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
