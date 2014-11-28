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
package eu.europa.ec.aqrsystem.action;

import eu.europa.ec.common.CountryBean;
import eu.europa.ec.user.UserroleBean;
import eu.europa.ec.user.EmailAlreadyInTheDBException;
import eu.europa.ec.user.UserBean;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * The controller for user management
 */
@RolesAllowed({"administrator", "superuser"})
public class UserActionBean extends BaseSettingsActionBean {

    /**
     * The location of the user table content view.
     */
    private static final String TABLE_CONTENT = "WEB-INF/jsp/settings/usersTableContent.jsp";

    /**
     * An event handler that renders the user table.
     *
     * @return The table view.
     */
    @DefaultHandler
    public Resolution table() {
        return new ForwardResolution(TABLE_CONTENT);
    }
    /**
     * Location of the dialog view.
     */
    private static final String DIALOG_VIEW = "WEB-INF/jsp/settings/userDialogContent.jsp";

    /**
     * Rendering the edit dialog
     *
     * @return Standard Stripes Resolution
     */
    public Resolution form() {
        return new ForwardResolution(DIALOG_VIEW);
    }
    /**
     * The ID of the currently selected user
     */
    private String userId;

    /**
     * Getter
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * The edited user
     */
    @ValidateNestedProperties({
        @Validate(field = "name", required = true, maxlength = 250, on = "save"),
        @Validate(field = "surname", required = true, maxlength = 250, on = "save"),
        @Validate(field = "email", required = true, maxlength = 250, converter = EmailTypeConverter.class, on = "save")
    })
    protected UserBean user;

    /**
     * @return the UserBean for the selected user
     */
    public UserBean getUser() {
        if (user == null && userId != null) {
            user = userManager.getUserByUuid(userId);
        }
        return user;
    }

    /**
     * Setter
     *
     * @param user
     */
    public void setUser(UserBean user) {
        this.user = user;
    }
    /**
     * The db ID of the superuser
     */
    private static final String SUPERUSER_ID = "0";
    /**
     * The db ID of the national admin
     */
    private static final String NATIONALADMIN_ID = "1";

    /**
     * Saving or adding a user
     *
     * @return Standard Stripes Resolution
     * @throws java.lang.Exception
     */
    @RolesAllowed({"administrator", "superuser"})
    public Resolution save() throws Exception {
        if (getCurrentUser().getUserroleBean().getUuid().equals(NATIONALADMIN_ID) && user.getUserroleBean().getUuid().equals(SUPERUSER_ID)) {
            return null; // this should never happen unless somebody starts hacking the GUI. Therefore nice error messages are not important.
        }

        if (getCurrentUser().getUserroleBean().getRolename().equals("administrator")) {
            if (user.getCountryBean() == null) {
                user.setCountryBean(new CountryBean());
            }
            user.getCountryBean().setUuid(currentUser.getCountryBean().getUuid());
        }

        if (user.getUuid() != null) { // this is an existing user
            user.setProviderBean(userManager.getUserByUuid(user.getUuid()).getProviderBean()); // setting the old provider as it should not be changed
        }

        if (currentUser.getUuid().equals(user.getUuid()) && !user.isEnable()) {
            return ajaxError("user.disableItself.error");
        }

        try {
            userManager.updateUserByUserID(user);
        } catch (EmailAlreadyInTheDBException e) {
            return ajaxError("user.duplicate.error");
        }

        return ajaxSuccess();
    }
    /**
     * Informs whether when enable() is clicked it should enable the user
     */
    protected boolean enable;

    /**
     * Setter
     *
     * @param enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Resolution enable() {
        if (userId.equals(getCurrentUser().getUuid())) {
            return ajaxError("user.disableItself.error");
        }
        userManager.enableUser(userId, enable);
        return ajaxSuccess();
    }
    /**
     * A list of users of the system.
     */
    private List<UserBean> existingUsers;

    /**
     * @return The list of countries.
     */
    public List<UserBean> getExistingUsers() {
        if (existingUsers == null) {
            existingUsers = userManager.getAllUsers(email);
        }
        return existingUsers;
    }
    /**
     * A list of possible users roles.
     */
    private static final List<UserroleBean> possibleRoles = userManager.getAllRoles();

    /**
     * @return The list of roles.
     */
    public List<UserroleBean> getPossibleRoles() {
        return possibleRoles;
    }
    /**
     * A list of possible countries.
     */
    private static final List<CountryBean> possibleCountries = userManager.getAllCountries();

    /**
     * @return The list of countries.
     */
    public List<CountryBean> getPossibleCountries() {
        return possibleCountries;
    }
}
