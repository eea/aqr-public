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

console.log('Entering formsManager.jsp');

/**
 * The object for showing a message when leaving a form and saving the data.
 */
var formsManager = {
    SAVEN_BUTTON: "input[name='save']",
    IMPORT_BUTTON: "input[name='importData']",
    SAVE_NAMESPACE_BUTTON: "input[name='saveNamespace']",
    SAVE_PROVIDER_BUTTON: "input[name='saveProvider']",
    modified: false
};

/**
 * The function executed when a link under development is clicked.
 * @param object
 * @param event the js event.
 */
formsManager.onFormChange = function(object, event) {
    console.log('Entering formsManager.onFormChange');
    this.modified = true;
};
formsManager.onSaveClicked = function(object, event) {
    console.log('Entering formsManager.onSaveClicked');
    this.modified = false;
};
formsManager.confirmExit = function(object, event) {
    console.log('Entering formsManager.confirmExit');
    if (this.modified) {
        return customMessagesManager.formsManager_notSaved;
    }
};

/**
 * Setting up messages in the whole file.
 */
formsManager.setUp = function() {
    console.log('Entering formsManager.setUp');
    $('form.protected *').filter("input:not(.tagged)").filter(":not(fieldset)").change(new ErrorProofExec("formsManager.onFormChange", this, this.onFormChange).exec);
    $(this.SAVEN_BUTTON).click(new ErrorProofExec("formsManager.onSaveClicked", this, this.onSaveClicked).exec);
    $(this.IMPORT_BUTTON).click(new ErrorProofExec("formsManager.onSaveClicked", this, this.onSaveClicked).exec);
    $(this.SAVE_NAMESPACE_BUTTON).click(new ErrorProofExec("formsManager.onSaveClicked", this, this.onSaveClicked).exec);
    $(this.SAVE_PROVIDER_BUTTON).click(new ErrorProofExec("formsManager.onSaveClicked", this, this.onSaveClicked).exec);
    window.onbeforeunload = (new ErrorProofExec("formsManager.confirmExit", this, this.confirmExit).exec);
};
$(document).ready(new ErrorProofExec("formsManager.setUp", formsManager, formsManager.setUp).exec);
