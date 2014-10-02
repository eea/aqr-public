<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <h2>${res['evaluation.label']}</h2>

        <security:allowed event="create">
            <s:link class="ftm insert btn" beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean" event="create">${res['evaluation.create']}</s:link>
            <cust:jqueryLink cssclass="ftm insertfromfile btn" label="${res['evaluation.import']}" title="${res['evaluation.import.title']}" id="import-btn"/>
        </security:allowed>

        <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean" event="table"/>

        <cust:ajaxtable>
            <jsp:attribute name="tableId">evaluationsGUI</jsp:attribute>
            <jsp:attribute name="loadTableURL">${loadTableURL}</jsp:attribute>
            <jsp:attribute name="sorting_column">2</jsp:attribute>
            <jsp:attribute name="sorting_order">desc</jsp:attribute>
        </cust:ajaxtable>

        <cust:importDialog>
            <jsp:attribute name="label">${res['source.import']}</jsp:attribute>
            <jsp:attribute name="beanclass">eu.europa.ec.aqrsystem.action.EvaluationActionBean</jsp:attribute>
            <jsp:attribute name="file">xmlexamples/evaluation.xml</jsp:attribute>
        </cust:importDialog>
    </s:layout-component>
</s:layout-render>