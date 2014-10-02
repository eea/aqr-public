<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A Stripes link without a label. (Stripes by default puts the URL as the label in this situation.)" pageEncoding="UTF-8"%>
<%@attribute name="beanclass" required="true"%>
<%@attribute name="cssclass" required="false"%>
<%@attribute name="event" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name="idName" required="true"%>
<%@attribute name="idValue" required="true"%>
<%@attribute name="onclick" required="false"%>

<s:link onclick="${onclick}" class="${cssclass}" beanclass="${beanclass}" event="${event}" title="${title}">
    <s:param name="${idName}" value="${idValue}"/>
    <div class="hidden"></div>
</s:link>
