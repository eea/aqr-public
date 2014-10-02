<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <h2>${res['plan.label']}</h2>

        <security:allowed event="create">
            <s:link class="ftm insert btn" beanclass="eu.europa.ec.aqrsystem.action.PlanActionBean" event="create">${res['plan.create']}</s:link>
            <cust:jqueryLink cssclass="ftm insertfromfile btn" label="${res['plan.import']}" title="${res['plan.import.title']}" id="import-btn"/>
        </security:allowed>

        <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.PlanActionBean" event="table"/>

        <cust:ajaxtable>
            <jsp:attribute name="tableId">plansGUI</jsp:attribute>
            <jsp:attribute name="loadTableURL">${loadTableURL}</jsp:attribute>
            <jsp:attribute name="sorting_column">2</jsp:attribute>
            <jsp:attribute name="sorting_order">desc</jsp:attribute>
        </cust:ajaxtable>

        <cust:importDialog>
            <jsp:attribute name="label">${res['plan.import']}</jsp:attribute>
            <jsp:attribute name="beanclass">eu.europa.ec.aqrsystem.action.PlanActionBean</jsp:attribute>
            <jsp:attribute name="file">xmlexamples/plan.xml</jsp:attribute>
        </cust:importDialog>
    </s:layout-component>
</s:layout-render>