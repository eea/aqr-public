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

console.log('Entering Listeners.js');

/** An objects for managing listeners to events
 */
function Listeners() {
    this.listeners = new Array();
    this.args = null;
}
;

/**
 * Registering a listener to an event
 * @param listener the listener method of another object.
 */
Listeners.prototype.register = function(listener) {
    this.listeners.push(listener);
};

/**
 * Notifing all the listeners about an event. Arguments are passed
 */
Listeners.prototype.notify = function() {
    this.args = arguments;
    this.listeners.map(this.notifyListener.bind(this));
};

/**
 * Notifying one listener about an event.
 * @param listener The listener
 */
Listeners.prototype.notifyListener = function(listener) {
    listener.apply(this, this.args);
};
