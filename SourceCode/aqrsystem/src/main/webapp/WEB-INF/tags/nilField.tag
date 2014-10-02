<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A Stripes label and field." pageEncoding="UTF-8"%>
<%@attribute name="key" required="true"%>
<%@attribute name="linkedField" required="false"%>
<%@attribute name="linkedField2" required="false"%>
<%@attribute name="linkedFieldCollection" required="false" type="java.lang.Object"%>
<%@attribute name="linkedFieldCollectionURL" required="false"%>
<%@attribute name="cssclass" required="false"%>
<%@attribute name="optional" required="false"%>

<cust:label key="${key}_nil" optional="true"/><s:checkbox name="${key}_nil" class="nil"/><br/>
<div name="${key}_nil_field" data-linkedfield="${linkedField}" data-linkedfield2="${linkedField2}"><cust:field cssclass="${cssclass}" key="${key}" optional="${optional}"/></div>
<c:if test="${linkedField2 != null}">
    <div name="${linkedField2}"><cust:field key="${linkedField2}" optional="true"/></div>
</c:if>
<c:if test="${linkedField != null}">
    <div name="${linkedField}">
        <cust:label key="${linkedField}"/>
        <s:select name="${linkedField}">
            <s:options-collection collection="${linkedFieldCollection}" value="uri" label="label"/>
        </s:select>
        <cust:valuesfrom href="${linkedFieldCollectionURL}"/>
    </div>
</c:if>
<div name="${key}_nil_reason">
    <cust:label key="${key}_nilreason"/>
    <s:select name="${key}_nilreason">
        <s:option value="Unpopulated">Unpopulated</s:option>
        <s:option value="Unknown">Unknown</s:option>
        <s:option value="Withheld">Withheld</s:option>
    </s:select>
</div>
