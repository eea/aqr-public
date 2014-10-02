<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="Stripes enhanced include statement" pageEncoding="UTF-8"%>
<%@attribute name="beanclass" required="true"%>
<%@attribute name="event" required="false"%>
<%@attribute name="name1" required="false"%>
<%@attribute name="value1" required="false"%>

<s:url var="url" beanclass="${beanclass}" event="${event}" prependContext="false">
    <s:param name="${name1}" value="${value1}"/>
</s:url>
<jsp:include page="${url}"/>
