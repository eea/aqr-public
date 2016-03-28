/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.europa.ec.aqrsystem.liquibase;

import liquibase.database.Database;
import liquibase.exception.CustomPreconditionErrorException;
import liquibase.exception.CustomPreconditionFailedException;
import liquibase.precondition.CustomPrecondition;

/**
 * Liquibase precondition: Fails when system property init.admins does not
 * exist.
 *
 * @author kopanalk
 */
public final class DBInitialAdminPrecondition implements CustomPrecondition {

    /**
     * The value of system property init.admins.
     */
    private String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String shortname) {
        this.admin = shortname;
    }

    @Override
    public void check(Database dtbs) throws CustomPreconditionFailedException, CustomPreconditionErrorException {
        if (admin.equals("${init.admins}")) {
            throw new CustomPreconditionFailedException("init.admin parameter is not defined!");
        }
    }

}
