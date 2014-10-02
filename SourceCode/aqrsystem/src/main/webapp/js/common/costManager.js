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

console.log('Entering costManager.js');

/**
 * This object manages the cost section in the measurement edit form.
 */
var costManager = {
    DIALOG_ID: 'costNewItem',
    NEW_SEL: '#new-cost',
    DELETE_SEL: '#delete-cost',
    EDIT_SEL: '#edit-cost',
    reloadViewLinkSel: '#reload-view-link',
    viewSel: '#cost-view',
    messagesPrefix: "cost",
    costAvailableSel: '#cost-available'
};

/**
 * Show the right buttons depending on whether the cost is defined or not.
 */
costManager.setButtons = function() {
    console.log('Entering costManager.setButtons');
    if ($(this.costAvailableSel).data('costavailable')) {
        $(this.NEW_SEL).hide();
        $(this.DELETE_SEL).show();
        $(this.EDIT_SEL).show();
    } else {
        $(this.NEW_SEL).show();
        $(this.DELETE_SEL).hide();
        $(this.EDIT_SEL).hide();
    }
};

/**
 * Setting up the cost manager.
 */
costManager.setUp = function() {
    if ($("#" + this.DIALOG_ID).length !== 0) {
        this.dialog = new AjaxDialog(this.DIALOG_ID);
        this.dialog.registerLinkToOpenDialog(this.EDIT_SEL);
        this.ajaxManager = new AjaxReloadableComponent(this.viewSel, this.messagesPrefix, $(this.reloadViewLinkSel).attr('href'));
        this.ajaxManager.registerAjaxDialog(this.dialog);
        this.ajaxManager.registerOnAfterComponentReloaded(new ErrorProofExec("costManager.setButtons", this, this.setButtons).exec);
        livequeryClick($(this.DELETE_SEL), new ErrorProofExec("AjaxReloadableComponent.ajaxOperationClicked", this.ajaxManager, this.ajaxManager.ajaxOperationClicked).exec);
    }
};
$(document).ready(new ErrorProofExec("costManager.setUp", costManager, costManager.setUp).exec);