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

console.log('Entering changeCheckboxManager.js');

/**
 * The object responsible for managing the change checkbox in edit forms.
 */
var changeCheckboxManager = {
//    CHANGE_CHECKBOX: 'input[type="checkbox"][name$=".changes"]',
//    CHANGE_DESCRIPTION: 'textarea[name$=".descriptionofchanges"]',
//    CHANGE_DESCRIPTION_LABEL: 'label[for$=".descriptionofchanges"]'
};

/**
 * The function executed on a change checkbox clicked.
 * @param object The checkbox
 * @param event JavaScript event
 */
changeCheckboxManager.onClick = function(object, event) {
//    $(this.CHANGE_DESCRIPTION).toggle(object.checked);
//    $(this.CHANGE_DESCRIPTION_LABEL).toggle(object.checked);
//    if (!object.checked)
//        $(this.CHANGE_DESCRIPTION).val("");
};

/**
 * Setting up the change checkbox.
 */
changeCheckboxManager.setUp = function() {
//    livequeryClick($(this.CHANGE_CHECKBOX), new ErrorProofExec("changeCheckboxManager.onClick", this, this.onClick).exec);
//    this.onClick({checked: $(this.CHANGE_CHECKBOX).is(':checked')});
};
$(document).ready(new ErrorProofExec("changeCheckboxManager.setUp", changeCheckboxManager, changeCheckboxManager.setUp).exec);