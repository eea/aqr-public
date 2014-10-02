<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@tag description="A table that can be reloaded using AJAX" pageEncoding="UTF-8"%>
<%@attribute name="tableId" required="true"%> <!-- the selector for the new item dialog -->
<%@attribute name="loadTableURL" required="true"%> <!-- The URL from which the table content can be loaded. -->
<%@attribute name="sorting_column" required="true"%> <!-- How the table should be sorted (using the JQuery tables syntax) -->
<%@attribute name="sorting_order" required="true"%> <!-- How the table should be sorted (using the JQuery tables syntax) -->

<%@attribute name="newItemDialogHeight" required="false"%> <!-- The hight of the new item dialog -->
<%@attribute name="newItemDialogWidth" required="false"%> <!-- The width of the new item dialog -->
<%@attribute name="newItemDialogTitle" required="false"%> <!-- The hight of the new item dialog -->
<%@attribute name="addNewItemButtonId" required="false"%> <!-- the id of the button that opens the new item dialog -->

<div class="tb_cont ajaxtable" id="${tableId}tableCont"
     data-tableid="${tableId}"
     data-sortingcolumn="${sorting_column}"
     data-sortingorder="${sorting_order}"
     data-loadtableurl="${loadTableURL}"
     data-newitemdialogpresent="${newItemDialogTitle != null}">
    <cust:ajaxMessages prefix="${tableId}"/>
    <cust:ajaxInProgress id="${tableId}table"/>

    <c:if test="${newItemDialogTitle != null}">
        <cust:ajaxdialog>
            <jsp:attribute name="height">${newItemDialogHeight}</jsp:attribute>
            <jsp:attribute name="width">${newItemDialogWidth}</jsp:attribute>
            <jsp:attribute name="title">${newItemDialogTitle}</jsp:attribute>
            <jsp:attribute name="dialogId">${tableId}NewItem</jsp:attribute>
            <jsp:attribute name="addNewItemButtonId">${addNewItemButtonId}</jsp:attribute>
        </cust:ajaxdialog>
    </c:if>
</div>