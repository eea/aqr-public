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

console.log('Entering nilReasonFieldsManager.js');

/**
 * The object responsible for managing the GUI of fields with the nilReason
 */
var nilReasonFieldsManager = {
    NIL_STYLE: '.nil'
};

/**
 * The function executed on a checkbox click.
 * @param object
 * @param event JavaScript event
 */
nilReasonFieldsManager.onClick = function(object, event) {
    var checkboxName = $(object).attr("name");
    var linkedField = $("div[name='" + checkboxName + "_field']").data("linkedfield");
    var linkedField2 = $("div[name='" + checkboxName + "_field']").data("linkedfield2");
    $("div[name='" + checkboxName + "_field']").toggle(!object.checked);
    $("div[name='" + linkedField + "']").toggle(!object.checked);
    $("div[name='" + linkedField2 + "']").toggle(!object.checked);
    $("div[name='" + checkboxName + "_reason']").toggle(object.checked);
    if (object.checked) {
        $("div[name='" + checkboxName + "_field'] input").val("");
        $("div[name='" + linkedField + "'] select option:first").attr('selected', 'selected'); // select the first option
        $("div[name='" + linkedField2 + "'] input").val("");
    }
    else
        $("div[name='" + checkboxName + "_reason'] select option:first").attr('selected', 'selected'); // select the first option
};

/**
 * The function executed when a new nilReason element is added to the DOM.
 * @param htmlElement
 * @param object
 * event JavaScript event
 */
nilReasonFieldsManager.onAdded = function(object, htmlElement) {
    this.onClick(htmlElement);
};

/**
 * Setting up
 */
nilReasonFieldsManager.setUp = function() {
    livequeryClick($(this.NIL_STYLE), new ErrorProofExec("nilReasonFieldsManager.onClick", this, this.onClick).exec);
    livequeryAdded($(this.NIL_STYLE), new ErrorProofExec("nilReasonFieldsManager.onAdded", this, this.onAdded).exec);
};
$(document).ready(new ErrorProofExec("nilReasonFieldsManager.setUp", nilReasonFieldsManager, nilReasonFieldsManager.setUp).exec);