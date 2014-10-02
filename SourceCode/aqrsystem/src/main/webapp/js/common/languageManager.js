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

console.log('Entering languageManager.jsp');

/**
 * An object for managing the change language form.
 */
var languageManager = {
    formSel: "#langChange",
    selectSel: "select[name=currentLanguage]"
};

/**
 * The method is invoked when selection of a language changes. It submits the
 * form.
 */
languageManager.onChange = function() {
    $(this.formSel).submit();
};

/**
 * Registering the object on the language change event.
 */
languageManager.setUp = function() {
    $(this.selectSel).on("change", new ErrorProofExec("languageManager.onChange", this, this.onChange).exec);
};

/**
 * Get the currently selected locale.
 */
languageManager.getLng = function() {
    return $(this.selectSel).val();
};
$(document).ready(new ErrorProofExec("languageManager.setUp", languageManager, languageManager.setUp).exec);
