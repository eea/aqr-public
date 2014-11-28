<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="evaluationsGUItable">
    <thead>
        <tr>
            <th>${res['common.name']}</th>
            <th>${res['common.user']}</th>
            <th>${res['common.created']}</th>
            <th>${res['common.lastmodified']}</th>
            <th>${res['common.status']}</th>
            <th>${res['common.command']}</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="evaluation" items="${actionBean.existingEvaluationScenarios}">
            <tr>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.EditEvaluationActionBean" title="${res['common.viewdetail']}">
                        <s:param name="evaluationId" value="${evaluation.uuid}"/>
                        ${fn:escapeXml(evaluation.inspireidLocalid)}
                    </s:link>
                </td>
                <td>${fn:escapeXml(evaluation.userBean.name)} ${fn:escapeXml(evaluation.userBean.surname)}</td>
                <td><fmt:formatDate value="${evaluation.datecreation}" pattern="yy/MM/dd HH:mm z"/></td>
                <td><fmt:formatDate value="${evaluation.datelastupdate}" pattern="yy/MM/dd HH:mm z"/></td>
                <td>${evaluation.completed ? res['common.complete'] : res['common.draft'] }</td>
                <td>
                    <c:choose>
                        <c:when test="${evaluation.editable}">
                            <cust:emptyLink cssclass="ftm edit" beanclass="eu.europa.ec.aqrsystem.action.EditEvaluationActionBean" event="edit" title="${res['common.editdetail']}" idName="evaluationId" idValue="${evaluation.uuid}"/>
                        </c:when>

                        <c:otherwise>
                            <cust:emptyLink cssclass="ftm view" beanclass="eu.europa.ec.aqrsystem.action.EditEvaluationActionBean" event="edit" title="${res['common.viewdetail']}" idName="evaluationId" idValue="${evaluation.uuid}"/>
                        </c:otherwise>
                    </c:choose>

                    <security:allowed event="cloneItem">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean" event="cloneItem" class="ftm clone ajax-operation" title="${res['common.clone']}">
                            <s:param name="evaluationId" value="${evaluation.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </security:allowed>
                    <c:if test="${evaluation.editable}">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean" event="delete" class="ftm delete confirm ajax-operation" title="${res['common.delete']}">
                            <s:param name="evaluationId" value="${evaluation.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </c:if>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean" event="export" class="ftm generatexml" title="${res['common.export']}">
                        <s:param name="evaluationId" value="${evaluation.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
