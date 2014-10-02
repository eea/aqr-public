<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A Stripes label and field." pageEncoding="UTF-8"%>
<%@attribute name="key" required="true"%>
<%@attribute name="value" required="true"%>
<%@attribute name="optional" required="false"%>
<%@attribute name="hiddenField" required="false"%>

<cust:label key="${key}" optional="true"/><span class="preset-text">${fn:escapeXml(value)}</span><br/>
<c:if test="${hiddenField == true}"><s:hidden name="${key}"/></c:if>
