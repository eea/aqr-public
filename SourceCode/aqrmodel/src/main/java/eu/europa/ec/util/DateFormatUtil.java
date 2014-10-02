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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public DateFormatUtil() {
    }

    public String getToday() {
        // This is how to get today's date in Java
        Date today = new Date();

        //If you print Date, you will get un formatted output
//        System.out.println("Today is : " + today);
        //formatting date in Java using SimpleDateFormat
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(today);
//        System.out.println("Today in dd-MM-yyyy format : " + date);
//
//        //Another Example of formatting Date in Java using SimpleDateFormat
//        DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");
//        date = DATE_FORMAT.format(today);
//        System.out.println("Today in dd/MM/yy pattern : " + date);

        //formatting Date with time information
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd:HH:mm:SS");
        date = DATE_FORMAT.format(today).replace(":", ".");
//        System.out.println("Today in dd-MM-yy:HH:mm:SS : " + date);

//        //SimpleDateFormat example - Date with timezone information
//        DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:SS Z");
//        date = DATE_FORMAT.format(today);
//        System.out.println("Today in dd-MM-yy:HH:mm:SSZ : " + date);
        return date;
    }

    public static void main(String[] args) throws IOException {
        new DateFormatUtil().getToday();
    }
}
