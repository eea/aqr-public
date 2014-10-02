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

console.log('Entering AjaxDialog.js');

/**
 * The object representing a dialog.
 * @param dialogId - The dialog id
 */
function AjaxDialog(dialogId) {
    console.log('Entering AjaxDialog.constructor');
    this.SUCCESS_AJAX_MESSAGE = "<Success/>";
    this.ERROR_AJAX_MESSAGE = "<Error/>";
    this.EM_LENGTH = this.ERROR_AJAX_MESSAGE.length;
    this.operationPerformedListeners = new Listeners();
    this.errorListeners = new Listeners();
    this.dialogOpenedListeners = new Listeners();
    this.dialogSel = '#' + dialogId;
    this.dialog = $(this.dialogSel);
    this.dialog.dialog({
        autoOpen: false,
        height: this.dialog.data("height"),
        width: this.dialog.data("width"),
        modal: true,
    });
    this.dialogContSel = this.dialogSel + "Cont";
    if (this.dialog.data("addnewitembuttonid"))
        this.newItemButtonSel = "#" + this.dialog.data("addnewitembuttonid");
    this.cancelButtonSel = this.dialogSel + " .btn_dialog_cancel";
    this.formSel = this.dialogSel + " form";
    if (this.newItemButtonSel)
        $(this.newItemButtonSel).click(new ErrorProofExec("dialog.editClicked", this, this.editClicked).exec);
}
;

/**
 * Registering on ajax operations completed successfully.
 * @param listener The listener
 */
AjaxDialog.prototype.registerOnOperationPerformed = function(listener) {
    this.operationPerformedListeners.register(listener);
};

/**
 * Notifying of an ajax operation completed successfully.
 */
AjaxDialog.prototype.notifyOperationPerformed = function() {
    this.operationPerformedListeners.notify();
};

/**
 * Registering on an error.
 * @param listener The listener
 */
AjaxDialog.prototype.registerOnError = function(listener) {
    this.errorListeners.register(listener);
};

/**
 * Notifying of an error.
 */
AjaxDialog.prototype.notifyError = function() {
    this.errorListeners.notify();
};

/**
 * Registering on opening the dialog.
 * @param listener The listener
 */
AjaxDialog.prototype.registerOnDialogOpened = function(listener) {
    this.dialogOpenedListeners.register(listener);
};

/**
 * Notifying of opening the dialog.
 */
AjaxDialog.prototype.notifyDialogOpened = function() {
    this.errorListeners.notify();
};

/**
 * A function executed when an operation on the server, which might return an
 * error has been performed.
 * @param object
 * @param response The response from the server
 */
AjaxDialog.prototype.operationPerformedCallback = function(object, response) {
    console.log('Entering AjaxDialog.operationPerformed');
    response = response.trim();
    if (response === this.SUCCESS_AJAX_MESSAGE) {
        this.operationPerformedListeners.notify();
        this.dialog.dialog("close");
    } else if (response.substring(0, 4) === "<div") { // i.e. it is a validation error
        this.loadDialogCallback(object, response);
    } else if (response.length >= this.EM_LENGTH && response.substring(0, this.EM_LENGTH) === this.ERROR_AJAX_MESSAGE) {
        var errorMessage = response.substring(this.EM_LENGTH, response.length);
        this.errorListeners.notify(errorMessage);
        this.cancelClicked();
    } else {
        this.errorListeners.notify();
        this.cancelClicked();
    }
};

/**
 * Opening the dialog using the html from the parameter.
 * @param object
 * @param html The HTML for the dialog.
 */
AjaxDialog.prototype.loadDialogCallback = function(object, html) {
    console.log('Entering AjaxDialog.loadDialog');
    $(this.dialogContSel).replaceWith(html);
    this.dialog.dialog("open");
    $(this.cancelButtonSel).click(new ErrorProofExec("dialog.cancelClicked", this, this.cancelClicked).exec);
    $(this.formSel).submit(new ErrorProofExec("AjaxDialog.saveClicked", this, this.saveClicked).exec);
};

/**
 * Called when the edit button is clicked.
 * @param object
 * @param e
 * param.data contains the reference to the dialog.
 */
AjaxDialog.prototype.editClicked = function(object, e) {
    console.log('Entering AjaxDialog.editClicked');
    this.dialogOpenedListeners.notify();
    e.preventDefault();

    var url = $(object).attr("href");
    var callback = new ErrorProofExec("AjaxDialog.loadDialogCallback", this, this.loadDialogCallback).exec;
    ajaxCall(url, null, callback);
};

/**
 * Destoying the jQuery UI dialog and closing it.
 */
AjaxDialog.prototype.cancelClicked = function() {
    console.log('Entering AjaxDialog.cancelClicked');
    this.dialog.dialog("close");
};

/**
 * Called when the save button is clicked.
 * @param form
 * @param e
 */
AjaxDialog.prototype.saveClicked = function(form, e) {
    console.log('Entering AjaxDialog.saveClicked');
    e.preventDefault();
    var url = form.action + '?' + $(":input[type=submit]:focus").attr('name') + '=';
    var params = $(form).serialize();
    var callback = new ErrorProofExec("AjaxDialog.operationPerformedCallback", this, this.operationPerformedCallback).exec;
    ajaxCall(url, params, callback);
};

/**
 * This method registeres the objects given as a selector to open the dialog.
 * @param selector To which the editClicked event will be attached.
 */
AjaxDialog.prototype.registerLinkToOpenDialog = function(selector) {
    console.log('Entering dialog.registerToOpenDialog');
    livequeryClick($(selector), new ErrorProofExec("AjaxDialog.editClicked", this, this.editClicked).exec);
};
