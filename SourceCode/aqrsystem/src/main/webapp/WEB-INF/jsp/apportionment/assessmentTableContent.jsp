<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="assessmentGUItable">
    <thead>
        <tr>
            <th>${res['table.assessment.type']}</th>
            <th>${res['common.command']}</th>
        </tr>
    </thead>
    <c:forEach var="assessment" items="${actionBean.assessments}">
        <tr>
            <td>
                <s:link beanclass="eu.europa.ec.aqrsystem.action.AssessmentActionBean" event="form" class="edit-item">
                    <s:param name="sourceId" value="${actionBean.sourceId}"/>
                    <s:param name="assessmentId" value="${assessment.uuid}"/>
                    ${fn:escapeXml(assessment.assesmenttypeBean.label)}
                </s:link>
            </td>
            <td>
                <s:link beanclass="eu.europa.ec.aqrsystem.action.AssessmentActionBean" event="form" class="ftm edit edit-item" title="${res['common.edit']}">
                    <s:param name="sourceId" value="${actionBean.sourceId}"/>
                    <s:param name="assessmentId" value="${assessment.uuid}"/>
                    <div class="hidden"></div>
                </s:link>
                <security:allowed event="save">
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.AssessmentActionBean" event="delete" class="ftm delete confirm ajax-operation" title="${res['common.delete']}">
                        <s:param name="sourceId" value="${actionBean.sourceId}"/>
                        <s:param name="assessmentId" value="${assessment.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                </security:allowed>
            </td>
        </tr>
    </c:forEach>
    <tbody>
    </tbody>
</table>