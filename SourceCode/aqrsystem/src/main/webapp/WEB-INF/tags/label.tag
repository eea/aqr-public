<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A Stripes label with the support for tooltips." pageEncoding="UTF-8"%>
<%@attribute name="key" required="true"%>
<%@attribute name="optional" required="false"%>

<c:set var="title" value="${key}.title"/>
<label title="${res[title]}" for="${key}" class="${optional ? '' : 'required'}">${res[key]}</label>