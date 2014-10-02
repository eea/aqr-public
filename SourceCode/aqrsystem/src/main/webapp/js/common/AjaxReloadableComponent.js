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

console.log('Entering AjaxReloadableComponent.js');

/**
 * The object manages a reloadable component, e.g. a table.
 * @param containerSel The selector of the container to be reloaded
 * @param messagesPrefix The prefix for the id of the ajax messages html
 * component
 * @param reloadUrl The URL to reload the content
 */
function AjaxReloadableComponent(containerSel, messagesPrefix, reloadUrl) {
    console.log('Entering AjaxReloadableComponent.constructor');
    this.SUCCESS_AJAX_MESSAGE = "<Success/>";
    this.ERROR_AJAX_MESSAGE = "<Error/>";
    this.EM_LENGTH = this.ERROR_AJAX_MESSAGE.length;
    this.messagesSel = '#' + messagesPrefix + '-messages';
    this.errorsSel = '#' + messagesPrefix + '-errors';
    this.errorsContSel = '#' + messagesPrefix + '-errors li';
    this.reloadUrl = reloadUrl;
    this.beforeComponentReloadedListener = new Listeners();
    this.afterComponentReloadedListener = new Listeners();
    this.containerSel = containerSel;

    $(this.errorsSel).hide();
    $(this.messagesSel).hide();
    this.reloadComponent();
}
;

/**
 * Registering on a component is to be reloded
 * @param listener The listener
 */
AjaxReloadableComponent.prototype.registerOnBeforeComponentReloaded = function(listener) {
    this.beforeComponentReloadedListener.register(listener);
};

/**
 * Notifying a component is to be reloded
 */
AjaxReloadableComponent.prototype.notifyBeforeComponentReloaded = function() {
    this.beforeComponentReloadedListener.notify(html);
};

/**
 * Registering on after a component is reloded
 * @param listener The listener
 */
AjaxReloadableComponent.prototype.registerOnAfterComponentReloaded = function(listener) {
    this.afterComponentReloadedListener.register(listener);
};

/**
 * Notifying after a component is reloded
 */
AjaxReloadableComponent.prototype.notifyAfterComponentReloaded = function() {
    this.afterComponentReloadedListener.notify(html);
};

/**
 * Configure a new ajax dialog.
 * @param dialog The dialog.
 */
AjaxReloadableComponent.prototype.registerAjaxDialog = function(dialog) {
    console.log('Entering AjaxReloadableComponent.registerAjaxDialog');
    dialog.registerOnOperationPerformed(new ErrorProofExec("AjaxReloadableComponent.ajaxOperationPerformed", this, this.externalAjaxOperationPerformed).exec);
    dialog.registerOnError(new ErrorProofExec("AjaxReloadableComponent.error", this, this.errorOccured).exec);
    dialog.registerOnDialogOpened(new ErrorProofExec("AjaxReloadableComponent.dialogOpened", this, this.newExternalAjaxOperationStarted).exec);
};

/**
 * Respond to an error.
 * @param object
 * @param message
 */
AjaxReloadableComponent.prototype.errorOccured = function(object, message) {
    console.log('Entering table.errorOccured');
    if (typeof message !== 'undefined')
        $(this.errorsContSel).html(message);
    else
        $(this.errorsContSel).html(customMessagesManager.AjaxReloadableComponent_operationErrorMessage);
    $(this.errorsSel).show();
};

/**
 * Prepare for a new operation.
 */
AjaxReloadableComponent.prototype.newExternalAjaxOperationStarted = function() {
    console.log('Entering table.newOperationStarted');
    $(this.messagesSel).hide();
    $(this.errorsSel).hide();
};

/**
 * Update the gui after a successful performance of an ajax operation.
 */
AjaxReloadableComponent.prototype.externalAjaxOperationPerformed = function() {
    console.log('Entering table.ajaxOperationPerformed');
    $(this.messagesSel).show();
    this.reloadComponent();
};

/**
 * Substituting the existing table with the code in the parameter.
 * @param object the old this
 * @param html The code for the new table.
 */
AjaxReloadableComponent.prototype.reloadComponentCallback = function(object, html) {
    console.log('Entering AjaxReloadableComponent.reloadComponentCallback');
    this.beforeComponentReloadedListener.notify();
    $(this.containerSel).replaceWith(html);
    this.afterComponentReloadedListener.notify();
};

/**
 * Calling the server to reload the component.
 */
AjaxReloadableComponent.prototype.reloadComponent = function() {
    console.log('Entering AjaxReloadableComponent.reloadComponent');
    var callback = new ErrorProofExec("AjaxReloadableComponent.reloadComponentCallback", this, this.reloadComponentCallback).exec;
    ajaxCall(this.reloadUrl, null, callback);
};

/**
 * Handling a performed operation.
 * @param object the old this
 * @param response the response from the server
 */
AjaxReloadableComponent.prototype.operationPerformedCallback = function(object, response) {
    console.log('Entering AjaxReloadableComponent.reloadView');
    response = response.trim();
    if (response === this.SUCCESS_AJAX_MESSAGE) {
        this.externalAjaxOperationPerformed();
    } else if (response.length >= this.EM_LENGTH && response.substring(0, this.EM_LENGTH) === this.ERROR_AJAX_MESSAGE) {
        var errorMessage = response.substring(this.EM_LENGTH, response.length);
        this.errorOccured(null, errorMessage);
    }
    else
        this.errorOccured();
};

/**
 * Called when a button for an ajax operation, e.g. delete, is clicked.
 * @param object The object/url that was clicked.
 * @param e the event
 */
AjaxReloadableComponent.prototype.ajaxOperationClicked = function(object /* serializes to the url */, e) {
    console.log('Entering AjaxReloadableComponent.ajaxOperationClicked');
    e.preventDefault();

    if ($(object).is('.confirm') && !confirm(customMessagesManager.confirmationMessageManager_confirm))
        return;

    this.newExternalAjaxOperationStarted();
    ajaxCall(object, null, new ErrorProofExec("AjaxReloadableComponent.operationPerformedCallback", this, this.operationPerformedCallback).exec);
};
