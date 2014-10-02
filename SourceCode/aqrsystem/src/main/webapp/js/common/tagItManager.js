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

console.log('Entering tagItManager.js');

/**
 * This object manages the fields with a list of tags
 */
var tagItManager = {
    SELECTOR: ".tagged"
};
tagItManager.beforeTagAdded = function() {
    alert("beforeTagAdded");
};
tagItManager.beforeTagAdded = function() {
    alert("beforeTagAdded");
};

/**
 * Setting up the tagged fields
 */
tagItManager.setUp = function() {
    $(this.SELECTOR).livequery(function() {
        $(this).tagit();
        if ($(this).data('uiTagit').element.hasClass("error")) { // i.e. there is a validation error for this field
            $(this).data('uiTagit').tagList.addClass("error");
        }
    });
};
$(document).ready(new ErrorProofExec("tagItManager.setUp", tagItManager, tagItManager.setUp).exec);