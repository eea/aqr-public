<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A Stripes component to show where the values come from." pageEncoding="UTF-8"%>
<%@attribute name="href" required="true"%>

<br/>
<label>&nbsp;</label>
<span class="dx">${res['common.valuesfrom']}<br/><a target="_blank" href="${href}">${href}</a><br/></span><br/><br/>					
