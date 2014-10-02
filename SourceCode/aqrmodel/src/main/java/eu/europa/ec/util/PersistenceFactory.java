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
package eu.europa.ec.util;

import eu.europa.ec.constants.Constants;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceFactory {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PersistenceFactory.class);

    public static EntityManagerFactory getEntityManagerFactory() throws Exception {

        String persistenceUnitName = Configuration.getInstance().getProperties().getProperty(Constants.KEY_PERSISTENCE_UNIT_NAME, null);

        if (persistenceUnitName == null || persistenceUnitName.trim().length() <= 0) {
            throw new Exception("@@ Error while getting the persistence unit name; check the Application.properties file.");
        }

        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }
}
