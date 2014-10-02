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
package eu.europa.ec.aqrsystem.xml;

import eu.europa.ec.attainment.AttainmentBean;
import java.util.ArrayList;
import java.util.ResourceBundle;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * An interface for saving an object representing an XML file to the database
 */
public interface XMLSaveableObject {

    /**
     * Saving an object representing an XML file to the database
     *
     * @param userEmail The user
     * @param context ActionBeanContext to which errors and messages can be
     * reported
     * @param res
     */
    public void save(String userEmail, ActionBeanContext context, ResourceBundle res) throws Exception;
    
    public void save(String userEmail, ActionBeanContext context, ResourceBundle res, ArrayList<AttainmentBean> localId) throws Exception;
}
