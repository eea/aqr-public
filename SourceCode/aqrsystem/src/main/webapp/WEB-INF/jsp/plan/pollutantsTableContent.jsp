<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="pollutantGUItable">
    <thead>
        <tr>
            <th>${res['pollutant.table.code']}</th>
            <th>${res['common.command']}</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="pollutant" items="${actionBean.pollutants}">
            <tr>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.PollutantActionBean" event="form" class="edit-item">
                        <s:param name="planId" value="${actionBean.planId}"/>
                        <s:param name="pollutantId" value="${pollutant.uuid}"/>
                        ${pollutant.label}
                    </s:link>
                </td>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.PollutantActionBean" event="form" class="ftm edit edit-item" title="${res['common.edit']}">
                        <s:param name="planId" value="${actionBean.planId}"/>
                        <s:param name="pollutantId" value="${pollutant.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                    <security:allowed event="save">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.PollutantActionBean" event="delete" class="ftm delete confirm ajax-operation" title="${res['common.delete']}">
                            <s:param name="planId" value="${actionBean.planId}"/>
                            <s:param name="pollutantId" value="${pollutant.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </security:allowed>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>