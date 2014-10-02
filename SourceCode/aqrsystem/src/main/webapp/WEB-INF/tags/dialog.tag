<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A static dialog" pageEncoding="UTF-8"%>
<%@attribute name="height" required="true"%> <!-- the height of the dialog -->
<%@attribute name="width" required="true"%> <!-- the width of the dialog -->
<%@attribute name="dialogId" required="true"%> <!-- the id of the dialog main div -->
<%@attribute name="openDialogButtonId" required="true"%> <!-- the id of the open dialog button -->

<div class="nonajaxdialog" data-height="${height}" data-width="${width}" data-dialogid="${dialogId}" data-opendialogbuttonid="${openDialogButtonId}">
    <jsp:doBody/>
</div>