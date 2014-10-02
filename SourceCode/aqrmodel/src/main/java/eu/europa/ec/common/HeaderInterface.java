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
package eu.europa.ec.common;

import eu.europa.ec.common.relatedparty.RelatedpartyBean;

/**
 * An interface for objects that contain a header
 */
public interface HeaderInterface {

    public String getUuid();

    public void setUuid(String uuid);

    public String getInspireidLocalid();

    public void setInspireidLocalid(String inspireidLocalid);

    public String getInspireidNamespace();

    public void setInspireidNamespace(String inspireidNamespace);

    public String getInspireidVersionid();

    public void setInspireidVersionid(String inspireidVersionid);

    public boolean isChanges();

    public void setChanges(boolean changes);

    public String getDescriptionofchanges();

    public void setDescriptionofchanges(String descriptionofchanges);

    public String getReportingstartdate();

    public void setReportingstartdate(String reportingstartdate);

    public String getReportingenddate();

    public void setReportingenddate(String reportingenddate);

    public RelatedpartyBean getProviderBean();

    public void setProviderBean(RelatedpartyBean providerBean);
}
