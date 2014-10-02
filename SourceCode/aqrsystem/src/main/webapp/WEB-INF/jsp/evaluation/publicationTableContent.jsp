<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="publicationGUItable">
    <thead>
        <tr>
            <th>${res['table.publication.title']}</th>
            <th>${res['table.publication.author']}</th>
            <th>${res['table.publication.date']}</th>
            <th>${res['table.publication.publisher']}</th>
            <th>${res['common.command']}</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="publication" items="${actionBean.publications}">
            <tr>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.PublicationForEvaluationActionBean" event="form" class="edit-item">
                        <s:param name="evaluationId" value="${actionBean.evaluationId}"/>
                        <s:param name="publicationId" value="${publication.uuid}"/>
                        ${fn:escapeXml(publication.title)}
                    </s:link>
                </td>
                <td>${fn:escapeXml(publication.author)}</td>
                <td>${fn:escapeXml(publication.publicationdateTimeposition)}</td>
                <td>${fn:escapeXml(publication.publisher)}</td>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.PublicationForEvaluationActionBean" event="form" class="ftm edit edit-item" title="${res['common.edit']}">
                        <s:param name="evaluationId" value="${actionBean.evaluationId}"/>
                        <s:param name="publicationId" value="${publication.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                    <security:allowed event="save">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.PublicationForEvaluationActionBean" event="delete" class="ftm delete confirm ajax-operation" title="${res['common.delete']}">
                            <s:param name="evaluationId" value="${actionBean.evaluationId}"/>
                            <s:param name="publicationId" value="${publication.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </security:allowed>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>