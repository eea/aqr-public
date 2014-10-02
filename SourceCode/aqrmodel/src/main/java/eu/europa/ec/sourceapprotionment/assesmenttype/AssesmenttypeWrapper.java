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
package eu.europa.ec.sourceapprotionment.assesmenttype;

import eu.europa.ec.aqrmodel.Assesmenttype;
import java.io.Serializable;

public class AssesmenttypeWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    public AssesmenttypeWrapper() {
    }

    public static AssesmenttypeBean convertAdjustmenttypeInAdjustmenttypeBean(Assesmenttype assesmenttype) {
        AssesmenttypeBean assesmenttypeBean = new AssesmenttypeBean();

        assesmenttypeBean.setUuid(assesmenttype.getUuid());
        assesmenttypeBean.setLabel(assesmenttype.getLabel());
        assesmenttypeBean.setUri(assesmenttype.getUri());
        assesmenttypeBean.setDefinition(assesmenttype.getDefinition());
        assesmenttypeBean.setNotation(assesmenttype.getNotation());

        return assesmenttypeBean;
    }

}
