<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<s:layout-render name="/WEB-INF/jsp/common/layout_with_menu.jsp">
    <s:layout-component name="content">
        <h2 class="evsc">${res['evaluation.label']}</h2>

        <security:allowed event="create">
            <s:link class="ftm insert btn evsc" beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean" event="create">${res['evaluation.create']}</s:link>

            <cust:jqueryLink cssclass="ftm insertfromfile btn evsc" label="${res['evaluation.import']}" title="${res['evaluation.import.title']}" id="import-btn"/>

            <a class="ftm exportall btn plan" onclick="toggle();">${res['evaluation.exportall']}</a>
        </security:allowed>

        <div id="exportalldiv" style="display: none">

            <s:form beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean">
                <br/>
                <fieldset>
                    <legend style="color: #074B8C">${res['evaluation.exportall.multiple']}</legend>

                    <p>${res['evaluation.exportall.dateinterval']}:</p>

                    <label style="display: inline; vertical-align: inherit; margin-right: 5px" for="from" >
                        ${res['common.from']}
                    </label>
                    <input style="display: inline; width: 300px" type="text" id="from" name="from" onchange="showExportButton();">

                    <label style="display: inline; vertical-align: inherit; margin-left: 10px; margin-right: 5px" for="to">
                        ${res['common.to']}
                    </label>
                    <input style="display: inline; width: 300px" type="text" id="to" name="to" onchange="showExportButton();">

                    <br/>

                    <p>${res['evaluation.exportall.description']}:</p>

                    <input type="radio" name="radio" value="completed" checked="checked" style="width: 10px; display: inline">
                    <label for="radio1" style="vertical-align: inherit; width: 100px">${res['common.completed']}</label>

                    <input type="radio" name="radio" value="all" style="width: 10px; display: inline">
                    <label for="radio2" style="vertical-align: inherit; width: 200px">${res['common.all.completed.draft']}</label>

                    <a class="ftm exportallcancel btn plan" style="float: right" onclick="closeDialog();">${res['common.cancel']}</a>
                    <s:submit name="exportall" style="display: none" id="exportAndClose" value="${res['evaluation.export']}" class="ftm export btn btn_dialog_save" onclick="closeDialog();"/>

                </s:form>
                <br/>
            </fieldset>
        </div>

        <s:url var="loadTableURL" beanclass="eu.europa.ec.aqrsystem.action.EvaluationActionBean" event="table"/>

        <cust:ajaxtable>
            <jsp:attribute name="tableId">evaluationsGUI</jsp:attribute>
            <jsp:attribute name="loadTableURL">${loadTableURL}</jsp:attribute>
            <jsp:attribute name="sorting_column">2</jsp:attribute>
            <jsp:attribute name="sorting_order">desc</jsp:attribute>
        </cust:ajaxtable>

        <cust:importDialog>
            <jsp:attribute name="label">${res['evaluation.import']}</jsp:attribute>
            <jsp:attribute name="beanclass">eu.europa.ec.aqrsystem.action.EvaluationActionBean</jsp:attribute>
            <jsp:attribute name="file">xmlexamples/evaluation.xml</jsp:attribute>
        </cust:importDialog>

        <script>
                $(function() {
                    $("#from").datepicker({
                        numberOfMonths: 1,
                        showOn: "button",
                        buttonImage: "img/main/calendar.png",
                        buttonImageOnly: true,
                        buttonText: "Select start creation date",
                        dateFormat: "dd-mm-yy",
                        onClose: function(selectedDate) {
                            $("#to").datepicker("option", "minDate", selectedDate);
                        }
                    });

                    $("#to").datepicker({
                        numberOfMonths: 1,
                        showOn: "button",
                        buttonImage: "img/main/calendar.png",
                        buttonImageOnly: true,
                        buttonText: "Select end creation date",
                        dateFormat: "dd-mm-yy",
                        onClose: function(selectedDate) {
                            $("#from").datepicker("option", "maxDate", selectedDate);
                        }
                    });
                });

                function toggle() {
                    var div = document.getElementById("exportalldiv");
                    if (div.style.display === 'block') {
                        div.style.display = 'none';
                        return;
                    }
                    div.style.display = 'block';
                }
                function closeDialog() {
                    var div = document.getElementById("exportalldiv");
                    if (div.style.display === 'block') {
                        div.style.display = 'none';
                        return;
                    }
                    div.style.display = 'block';
                }
                function showExportButton() {
                    var exportbutton = document.getElementById("exportAndClose");
                    var fromText = document.getElementById("from");
                    var toText = document.getElementById("to");
                    if (fromText.value.lenght !== 0 && toText.value.length !== 0) {
                        exportbutton.style.display = 'block';
                        return;
                    } else {
                        exportbutton.style.display = 'none';
                        return;
                    }

                }
                $('.from').change(function() {
                    var fromText = document.getElementById("from");
                    var toText = document.getElementById("to");
                    if (fromText.value.lenght !== 0 && toText.value.length !== 0) {
                        exportbutton.style.display = 'block';
                        return;
                    } else {
                        exportbutton.style.display = 'none';
                        return;
                    }
                });
                $('.to').change(function() {
                    var fromText = document.getElementById("from");
                    var toText = document.getElementById("to");
                    if (fromText.value.lenght !== 0 && toText.value.length !== 0) {
                        exportbutton.style.display = 'block';
                        return;
                    } else {
                        exportbutton.style.display = 'none';
                        return;
                    }
                });
        </script>

    </s:layout-component>
</s:layout-render>