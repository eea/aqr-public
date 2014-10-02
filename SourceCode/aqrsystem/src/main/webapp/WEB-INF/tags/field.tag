<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A Stripes label and field." pageEncoding="UTF-8"%>
<%@attribute name="key" required="true"%>
<%@attribute name="cssclass" required="false"%>
<%@attribute name="optional" required="false"%>

<cust:label key="${key}" optional="${optional}"/><s:text class="${cssclass}" name="${key}"/><br/>