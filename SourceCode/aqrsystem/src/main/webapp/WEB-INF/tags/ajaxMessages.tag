<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A widget for rendering ajax success and error messages" pageEncoding="UTF-8"%>
<%@attribute name="prefix" required="true"%> <!-- The hight of the dialog -->

<br/>
<ul id="${prefix}-messages" class="messages"><li>${res['ajax.success']}</li></ul>
<ul id="${prefix}-errors" class="errors"><li>${res['ajax.error']}</li></ul>
<br/>