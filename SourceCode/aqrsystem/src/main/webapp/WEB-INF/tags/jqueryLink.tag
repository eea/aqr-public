<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A link that is managed by jQuery" pageEncoding="UTF-8"%>
<%@attribute name="cssclass" required="false"%>
<%@attribute name="label" required="false"%>
<%@attribute name="id" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name="dataitemid" required="false"%>

 <a class="${cssclass}" href="javascript:;" id="${id}" title="${title}" data-itemid="${dataitemid}">${label}</a>