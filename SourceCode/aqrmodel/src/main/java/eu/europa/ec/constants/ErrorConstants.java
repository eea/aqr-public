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
package eu.europa.ec.constants;

public class ErrorConstants {

    public static final String CREATE_PLAN_EXCEPTION = "It was an error in creatin of the Plan";
    public static final String DELETE_SOURCEAPPORTIONMENT_EXCEPTION = "This sourceapportionment is refered by onother items in the system. Please cancel that items, and than retry to cancel this sourceapportionment.";
    public static final String DELETE_PLAN_EXCEPTION = "This plan is refered by onother items in the system. Please cancel that items, and than retry to cancel this plan.";
    public static final String DELETE_ATTAINMENT_EXCEPTION = "This attainment is refered by onother items in the system. Please cancel that items, and than retry to cancel this attainment.";
    public static final String ALREADY_EXIST_PLAN_EXCEPTION = "The INSPIRE Local ID of the plan that you want to rename is already existing in the database.";
    public static final String ALREADY_EXIST_EVALUATIONSCENARIO_EXCEPTION = "The INSPIRE Local ID of the evaluation scenario that you want to rename is already existing in the database.";
    public static final String ALREADY_EXIST_MEASURES_EXCEPTION = "The INSPIRE Local ID of the measures that you want to rename is already existing in the database.";
    public static final String ALREADY_EXIST_SOURCEAPPROTIONMENT_EXCEPTION = "The INSPIRE Local ID of the source apportionment that you want to rename is already existing in the database.";
    /**
     * USER
     */
    public static final String EMAIL_ALREADY_IN_DB = "Email already in the Database";
}
