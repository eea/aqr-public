<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<div id="cost-view">
    <c:if test="${actionBean.measure.costsBean != null}">
        <div id="cost-available" data-costavailable="${actionBean.measure.costsBean != null}" class="hidden"></div>
        <c:choose>
            <c:when test="${actionBean.measure.costsBean.extimatedimplementationcosts_nil != true}">
                <cust:readOnlyField key="measure.costsBean.extimatedimplementationcosts" value="${actionBean.measure.costsBean.extimatedimplementationcosts}"/>
                <cust:readOnlyField key="measure.costsBean.finalimplementationcosts" value="${actionBean.measure.costsBean.finalimplementationcosts}"/>
                <cust:readOnlyField key="measure.costsBean.currency" value="${actionBean.measure.costsBean.currency_label}"/>
            </c:when>
            <c:otherwise>
                <cust:readOnlyField key="measure.costsBean.extimatedimplementationcosts_nilreason" value="${actionBean.measure.costsBean.extimatedimplementationcosts_nilreason}"/>
            </c:otherwise>
        </c:choose>
        <cust:readOnlyField key="measure.costsBean.comment" value="${actionBean.measure.costsBean.comment}"/>
        <cust:valuesfrom href="http://dd.eionet.europa.eu/vocabulary/common/currencies"/>
    </c:if>
</div>
