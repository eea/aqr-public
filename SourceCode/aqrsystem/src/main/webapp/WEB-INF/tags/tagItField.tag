<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A Stripes label and a tagged field." pageEncoding="UTF-8"%>
<%@attribute name="key" required="true"%>
<%@attribute name="optional" required="false"%>

<cust:label key="${key}" optional="${optional}"/><s:hidden class="tagged" name="${key}"/><br/>