/* 
 *  Copyright (c) 2010-2014 EUROPEAN UNION
 *  Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 *  the European Commission - subsequent versions of the EUPL (the "Licence");
 *  You may not use this work except in compliance with the Licence.
 *  You may obtain a copy of the Licence at: 
 *  http://ec.europa.eu/idabc/eupl
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the Licence is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the Licence for the specific language governing permissions and
 *  limitations under the Licence.
 * 
 *  Date: __/__/____
 *  Authors: European Commission, Joint Research Centre
 *  Lucasz Cyra, Emanuela Epure, Daniele Francioli
 *  aq-dev@jrc.ec.europa.eu
 */

console.log('Entering NonAjaxDialog.js');

/**
 * The main object representing a dialog.
 * @param htmlobj The HTML div container in which the dialog is placed.
 */
function NonAjaxDialog(htmlobj) {
    console.log('Entering NonAjaxDialog.constructor');
    var dialogCont = $(htmlobj);
    var dialogSel = "#" + dialogCont.data("dialogid");
    var openDialogButtonSel = "#" + dialogCont.data("opendialogbuttonid");
    var cancelButtonSel = dialogSel + " .btn_dialog_cancel";
    this.dialog = $(dialogSel);
    this.dialog.dialog({
        autoOpen: false,
        height: dialogCont.data("height"),
        width: dialogCont.data("width"),
        modal: true
    });
    $(openDialogButtonSel).filter("a:not(.todo)").click(new ErrorProofExec("NonAjaxDialog.openDialogClicked", this, this.openDialogClicked).exec);
    $(cancelButtonSel).click(new ErrorProofExec("NonAjaxDialog.cancelClicked", this, this.cancelClicked).exec);
}
;

/**
 * The function called when the open dialog button is clicked.
 */
NonAjaxDialog.prototype.openDialogClicked = function() {
    console.log('Entering NonAjaxDialog.openDialogClicked');
    this.dialog.dialog("open");
};

/**
 * The function called when the cancel button is clicked.
 */
NonAjaxDialog.prototype.cancelClicked = function() {
    console.log('Entering NonAjaxDialog.cancelClicked');
    this.dialog.dialog("close");
};

$(document).ready(function() {
    var NON_AJAX_DIALOG_CONTAINER_SELECTOR = ".nonajaxdialog";

    var fun = new ErrorProofExec("NonAjaxDialog.constructor", null, function(oldthis, index, htmlobj) {
        new NonAjaxDialog(htmlobj);
    }).exec;
    $(NON_AJAX_DIALOG_CONTAINER_SELECTOR).each(fun);
});
