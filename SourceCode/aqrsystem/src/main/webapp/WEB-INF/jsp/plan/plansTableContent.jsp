<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="plansGUItable">
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
        <c:forEach var="plan" items="${actionBean.existingPlans}">
            <tr>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.EditPlanActionBean" title="${res['common.viewdetail']}">
                        <s:param name="planId" value="${plan.uuid}"/>
                        ${fn:escapeXml(plan.inspireidLocalid)}
                    </s:link>
                </td> 

                <td>${fn:escapeXml(plan.userBean.name)} ${fn:escapeXml(plan.userBean.surname)}</td>
                <td><fmt:formatDate value="${plan.datecreation}" pattern="dd/MM/yyyy HH:mm z"/></td>
                <td>${fn:escapeXml(plan.userLastUpdateBean.name)} ${fn:escapeXml(plan.userLastUpdateBean.surname)}</td>
                <td><fmt:formatDate value="${plan.datelastupdate}" pattern="dd/MM/yyyy HH:mm z"/></td>
                <td>${plan.completed ? res['common.complete'] : res['common.draft'] }</td>
                <td>
                    <c:choose>
                        <c:when test="${plan.editable}">
                            <cust:emptyLink cssclass="ftm edit" beanclass="eu.europa.ec.aqrsystem.action.EditPlanActionBean" event="edit" title="${res['common.editdetail']}" idName="planId" idValue="${plan.uuid}"/>
                        </c:when>

                        <c:otherwise>
                            <cust:emptyLink cssclass="ftm view" beanclass="eu.europa.ec.aqrsystem.action.EditPlanActionBean" event="edit" title="${res['common.viewdetail']}" idName="planId" idValue="${plan.uuid}"/>
                        </c:otherwise>
                    </c:choose>

                    <security:allowed event="cloneItem">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.PlanActionBean" event="cloneItem" class="ftm clone ajax-operation" title="${res['common.clone']}">
                            <s:param name="planId" value="${plan.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </security:allowed>
                    <c:if test="${plan.editable}">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.PlanActionBean" event="delete" class="ftm delete confirm ajax-operation" title="${res['common.delete']}">
                            <s:param name="planId" value="${plan.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </c:if>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.PlanActionBean" event="export" class="ftm generatexml" title="${res['common.export']}">
                        <s:param name="planId" value="${plan.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
