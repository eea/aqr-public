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

console.log('Entering tabManager.js');

/**
 * This object manages tabs within the main tabs
 */
var tabManager = {
    PARAM_NAME: "activetab",
    TAB_STYLE: ".tabs",
    INDIVIDUAL_TABS_SEL: "#tabs div.one-tab"
};

/**
 * Setting up the tabs and selecting the right one.
 */
tabManager.setUp = function() {
    var tabs = $(this.TAB_STYLE);
    if (tabs) {
        var activeTab = $(this.TAB_STYLE).data(this.PARAM_NAME);
        if (activeTab)
            tabs.tabs({active: $(this.INDIVIDUAL_TABS_SEL).index($('#' + activeTab))});
        else
            tabs.tabs();
    }
};
$(document).ready(new ErrorProofExec("tabManager.setUp", tabManager, tabManager.setUp).exec);