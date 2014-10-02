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

console.log('Entering confirmationMessageManager.js');

/**
 * The object responsible for displaying a confirmation message before an
 * important operation.
 */
var confirmationMessageManager = {
    CONFIRMATION_STYLE: '.confirm'
};

/**
 * The function executed on a button click.
 * @param object
 * @param event JavaScript event
 */
confirmationMessageManager.onClick = function(object, event) {
    if (confirm(customMessagesManager.confirmationMessageManager_confirm)) {
        return true;
    } else {
        event.preventDefault();
        return false;
    }
};

/**
 * Setting up the confirmation messages in the whole document.
 */
confirmationMessageManager.setUp = function() {
    livequeryClick($(this.CONFIRMATION_STYLE).filter("a:not(.todo)").filter("a:not(.ajax-operation)"), new ErrorProofExec("confirmationMessageManager.onClick", this, this.onClick).exec);
};
$(document).ready(new ErrorProofExec("confirmationMessageManager.setUp", confirmationMessageManager, confirmationMessageManager.setUp).exec);