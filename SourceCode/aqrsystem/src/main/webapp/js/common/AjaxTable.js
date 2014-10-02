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

console.log('Entering AjaxTable.js');

/**
 * The object representing a table.
 * @param htmlobj An object representing a container in which is the table.
 */
function AjaxTable(htmlobj) {
    console.log('Entering AjaxTable.constructor');
    var tableCont = $(htmlobj);
    var id = tableCont.data("tableid");
    this.sortingColumn = tableCont.data("sortingcolumn");
    this.sortingOrder = tableCont.data("sortingorder");
    this.tableSel = '#' + id + 'table';
    this.initialised = false;
    this.ajaxOperationSel = this.tableSel + ' .ajax-operation';
    this.editItemSel = this.tableSel + ' .edit-item';

    this.ajaxManager = new AjaxReloadableComponent(this.tableSel, id, tableCont.data("loadtableurl"));
    this.ajaxManager.registerOnBeforeComponentReloaded(new ErrorProofExec("AjaxTable.reloadCallback", this, this.destroy).exec);
    this.ajaxManager.registerOnAfterComponentReloaded(new ErrorProofExec("AjaxTable.reloadCallback", this, this.setUp).exec);
    if (tableCont.data("newitemdialogpresent")) {
        this.editItemDialog = new AjaxDialog(id + "NewItem");
        this.ajaxManager.registerAjaxDialog(this.editItemDialog);
    }
    livequeryClick($(this.ajaxOperationSel), new ErrorProofExec("AjaxReloadableComponent.ajaxOperationClicked", this.ajaxManager, this.ajaxManager.ajaxOperationClicked).exec);
    if (this.editItemDialog)
        this.editItemDialog.registerLinkToOpenDialog(this.editItemSel);
};

/**
 * Destroying the Data Tables plugin object.
 */
AjaxTable.prototype.destroy = function() {
    console.log('Entering AjaxTable.destroy');
    if (this.initialised)
        $(this.tableSel).dataTable().fnDestroy();
    this.initialised = true;
};

/**
 * Setting up the table.
 */
AjaxTable.prototype.setUp = function() {
    console.log('Entering AjaxTable.setUp');
    $(this.tableSel).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 10,
        "oLanguage": {
            "sUrl": "js/localization/data-table-languages/" + languageManager.getLng() + ".json"
        },
        "aaSorting": [[this.sortingColumn, this.sortingOrder]]
    });
};

$(document).ready(function() {
    var AJAX_TABLE_CONTAINER_SELECTOR = ".ajaxtable";

    var fun = new ErrorProofExec("AjaxTable.constructor", null, function(oldthis, index, htmlobj) {
        new AjaxTable(htmlobj);
    }).exec;
    $(AJAX_TABLE_CONTAINER_SELECTOR).each(fun);
});