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

console.log('Entering pollutantManager.jsp');

/**
 * Making sure that proper targets are loaded when a pollutant is changed.
 */
var pollutantManager = {
    pollutantSelectSel: "#pollutant-select",
    targetSelectSel: "#target-select",
    getTargetsLinkSel: "#get-targets-link"
};

/**
 * The method is invoked when selection of a pollutant changes.
 * @param object
 * @param e
 */
pollutantManager.onChange = function(object, e) {
    var url = $(this.getTargetsLinkSel).attr("href");
    var callback = new ErrorProofExec("pollutantManager.onChangeCallback", this, this.onChangeCallback).exec;
    var params = {pollutantId: $(this.pollutantSelectSel).val()};
    ajaxCall(url, params, callback);
};

/**
 * The handles the response from the server.
 * @param object 
 * @param html 
 */
pollutantManager.onChangeCallback = function(object, html) {
    $(this.targetSelectSel).replaceWith(html);
};

/**
 * Setting up the object.
 */
pollutantManager.setUp = function() {
    console.log('Entering pollutantManager.setUp');
    livequeryChange($(this.pollutantSelectSel), new ErrorProofExec("pollutantManager.onChange", this, this.onChange).exec);
};
$(document).ready(new ErrorProofExec("pollutantManager.setUp", pollutantManager, pollutantManager.setUp).exec);
