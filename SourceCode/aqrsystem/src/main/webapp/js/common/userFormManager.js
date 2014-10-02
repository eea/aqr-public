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

console.log('Entering userFormManager.js');

/**
 * This object controls the values of the select boxes in the edit user form.
 */
var userFormManager = {
    ROLE_SELECT_SEL: 'select[name="user.userroleBean.uuid"]',
    COUNTRY_SELECT_SEL: 'select[name="user.countryBean.uuid"]',
    COUNTRY_LABEL_SEL: 'label[for="user.countryBean.uuid"]',
    DIALOG_ID: 'usersGUINewItemCont',
    EEA_ADMIN_ID: '0',
    NATIONAL_ADMIN_COUNTRY_LIST: [],
    EEA_ADMIN_COUNTRY_LIST: []
};

/**
 * The function executed when the value of the role select is changed.
 * @param object
 * @param event the js event.
 */
userFormManager.onRoleChange = function(object, event) {
    $(this.COUNTRY_SELECT_SEL).empty();
    if ($(this.ROLE_SELECT_SEL).val() === this.EEA_ADMIN_ID) {
        $(this.COUNTRY_SELECT_SEL).append(this.EEA_ADMIN_COUNTRY_LIST);
        $(this.COUNTRY_SELECT_SEL).val(0);
        $(this.COUNTRY_SELECT_SEL).hide();
        $(this.COUNTRY_LABEL_SEL).hide();
    }
    else {
        $(this.COUNTRY_SELECT_SEL).append(this.NATIONAL_ADMIN_COUNTRY_LIST);
        $(this.COUNTRY_SELECT_SEL).val(1);
        $(this.COUNTRY_SELECT_SEL).show();
        $(this.COUNTRY_LABEL_SEL).show();
    }
};

/**
 * Processing the options of the country select.
 * @param object
 * @param event the js event.
 */
userFormManager.processSelectOptions = function(object, event) {
    if (event.target.id === this.DIALOG_ID) { // the select exists
        var selectedCountry = $(this.COUNTRY_SELECT_SEL).val();
        if ($(this.COUNTRY_SELECT_SEL).length === 0) { // this is the national admin because the country select is not visible
            var roles = [];
            $(this.ROLE_SELECT_SEL + ' option').each(function() {
                if ($(this).val() !== "0")
                    roles.push(this);
            });
            $(this.ROLE_SELECT_SEL).empty().append(roles);
        } else { // this is an eea admin
            if (this.NATIONAL_ADMIN_COUNTRY_LIST.length === 0) { // the options have not been processed yet
                var parent = this;
                $(this.COUNTRY_SELECT_SEL + ' option').each(function() {
                    parent.EEA_ADMIN_COUNTRY_LIST.push(this);
                    if ($(this).val() !== "0")
                        parent.NATIONAL_ADMIN_COUNTRY_LIST.push(this);
                });
            }
            this.onRoleChange();
            $(this.COUNTRY_SELECT_SEL).val(selectedCountry);
        }
    }
};

/**
 * Setting up the user dialog.
 */
userFormManager.setUp = function() {
    $(document).on('DOMNodeInserted', new ErrorProofExec("userFormManager.processSelectOptions", this, this.processSelectOptions).exec);
    livequeryChange($(this.ROLE_SELECT_SEL), new ErrorProofExec("userFormManager.onRoleChange", this, this.onRoleChange).exec);
};
$(document).ready(new ErrorProofExec("userFormManager.setUp", userFormManager, userFormManager.setUp).exec);