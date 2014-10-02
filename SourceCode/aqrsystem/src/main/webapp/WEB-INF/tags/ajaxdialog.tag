<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A dialog, whose content may be loaded using AJAX" pageEncoding="UTF-8"%>
<%@attribute name="height" required="true"%> <!-- The hight of the dialog -->
<%@attribute name="width" required="true"%> <!-- The width of the dialog -->
<%@attribute name="title" required="true"%> <!-- The hight of the dialog -->
<%@attribute name="dialogId" required="true"%> <!-- the selector for the button that opens this dialog -->
<%@attribute name="addNewItemButtonId" required="false"%> <!-- the selector for the button that opens this dialog -->

<div class="dialog" data-height="${height}" data-width="${width}" data-addnewitembuttonid="${addNewItemButtonId}" id="${dialogId}" title="${title}">
    <div id="${dialogId}Cont"></div>
</div>