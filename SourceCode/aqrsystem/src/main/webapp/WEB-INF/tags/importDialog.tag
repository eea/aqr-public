<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="The import file dialog" pageEncoding="UTF-8"%>
<%@attribute name="label" required="true"%>
<%@attribute name="beanclass" required="true"%>
<%@attribute name="file" required="true"%>

<cust:dialog>
    <jsp:attribute name="height">255</jsp:attribute>
    <jsp:attribute name="width">500</jsp:attribute>
    <jsp:attribute name="dialogId">import</jsp:attribute>
    <jsp:attribute name="openDialogButtonId">import-btn</jsp:attribute>
    <jsp:body>
        <div class="dialog" id="import" title="${label}">
            <s:form beanclass="${beanclass}">
                <fieldset>
                    <legend>${res['common.upload']}</legend>
                    <div id="file_upload_div">
                        <cust:label key="xmlFile" optional="true"/><s:file name="xmlFile"/>
                        <br/><a href="${file}" download>(${res["common.fileexample"]})</a>
                    </div>
                    
                    <div id="progress">
                        <div class="center">${res['common.pleasewait']}</div>
                        <br/>
                        <img class="in_progress" src="img/tableicons/in_progress.gif"/>
                        <br/><br/>
                    </div>                    
                </fieldset>
                <br/>
                <cust:jqueryLink cssclass="ftm cancel btn btn_dialog_cancel" label="${res['common.cancel']}"/>
                <s:submit class="ftm save btn btn_dialog_save" name="importData" value="${res['common.import']}"/>
            </s:form>
        </div>
    </jsp:body>
</cust:dialog>
