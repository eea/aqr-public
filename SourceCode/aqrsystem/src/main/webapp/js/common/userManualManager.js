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

console.log('Entering userManualManager.js');

/**
 * This object controls the button with the user manual.
 */
var userManualManager = {
    BUTTON_SELECTOR: ".dxbox"
};

/**
 * On entering the menu
 * @param object
 */
userManualManager.enter = function(object) {
    $(object).filter(':not(:animated)').animate({width: '150px'}, 200);
};

/**
 * On leaving the menu
 * @param object
 */
userManualManager.leave = function(object) {
    $(object).animate({width: '0px'}, 200);
};

/**
 * Setting up the user button.
 */
userManualManager.setUp = function() {
    $(this.BUTTON_SELECTOR).mouseenter(new ErrorProofExec("userManualManager.enter", this, this.enter).exec);
    $(this.BUTTON_SELECTOR).mouseleave(new ErrorProofExec("userManualManager.leave", this, this.leave).exec);
};
$(document).ready(new ErrorProofExec("userManualManager.setUp", userManualManager, userManualManager.setUp).exec);

