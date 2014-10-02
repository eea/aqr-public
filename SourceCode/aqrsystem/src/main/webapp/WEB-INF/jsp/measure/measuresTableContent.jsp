<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<table id="measuresGUItable">
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
        <c:forEach var="measure" items="${actionBean.existingMeasures}">
            <tr>
                <td>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.EditMeasureActionBean" title="${res['common.viewdetail']}">
                        <s:param name="measureId" value="${measure.uuid}"/>
                        ${fn:escapeXml(measure.inspireidLocalid)}
                    </s:link>
                </td>
                <td><fmt:formatDate value="${measure.datecreation}" pattern="yy/MM/dd HH:mm z"/></td>
                <td><fmt:formatDate value="${measure.datelastupdate}" pattern="yy/MM/dd HH:mm z"/></td>
                <td>${measure.completed ? res['common.complete'] : res['common.draft'] }</td>
                <td>
                    <cust:emptyLink cssclass="ftm edit" beanclass="eu.europa.ec.aqrsystem.action.EditMeasureActionBean" event="edit" title="${res['common.edit']}" idName="measureId" idValue="${measure.uuid}"/>
                    <security:allowed event="cloneItem">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.MeasureActionBean" event="cloneItem" class="ftm clone ajax-operation" title="${res['common.clone']}">
                            <s:param name="measureId" value="${measure.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </security:allowed>
                    <c:if test="${measure.editable}">
                        <s:link beanclass="eu.europa.ec.aqrsystem.action.MeasureActionBean" event="delete" class="ftm delete confirm ajax-operation" title="${res['common.delete']}">
                            <s:param name="measureId" value="${measure.uuid}"/>
                            <div class="hidden"></div>
                        </s:link>
                    </c:if>
                    <s:link beanclass="eu.europa.ec.aqrsystem.action.MeasureActionBean" event="export" class="ftm generatexml" title="${res['common.export']}">
                        <s:param name="measureId" value="${measure.uuid}"/>
                        <div class="hidden"></div>
                    </s:link>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>