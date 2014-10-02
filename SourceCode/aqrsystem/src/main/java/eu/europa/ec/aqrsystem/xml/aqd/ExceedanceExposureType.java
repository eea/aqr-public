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
package eu.europa.ec.aqrsystem.xml.aqd;

import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedanceexposure.ExceedanceexposureBean;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.gml.AreaType;
import eu.europa.ec.aqrsystem.xml.gml.TimeInstantPropertyType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:ExceedanceExposureType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ExceedanceExposureType {

    @XmlElement(namespace = Namespaces.aqd)
    protected String populationExposed;
    @XmlElement(namespace = Namespaces.aqd)
    protected AreaType ecosystemAreaExposed;
    @XmlElement(namespace = Namespaces.aqd)
    protected String sensitivePopulation;
    @XmlElement(namespace = Namespaces.aqd)
    protected String infrastructureServices;
    @XmlElement(namespace = Namespaces.aqd)
    protected TimeInstantPropertyType referenceYear;

    public void populate(ExceedanceexposureBean p) {
        if (BaseUtils.isDefined(p.getExposedpopulation())) {
            populationExposed = p.getExposedpopulation();
        }
        if (BaseUtils.isDefined(p.getExposedarea())) {
            ecosystemAreaExposed = new AreaType();
            ecosystemAreaExposed.populate("http://dd.eionet.europa.eu/vocabulary/uom/area/km2", p.getExposedarea());
        }
        if (BaseUtils.isDefined(p.getSensitiveresidentpopulation())) {
            sensitivePopulation = p.getSensitiveresidentpopulation();
        }
        if (BaseUtils.isDefined(p.getRelevantinfrastructure())) {
            infrastructureServices = p.getRelevantinfrastructure();
        }
        if (BaseUtils.isDefined(p.getReferenceyear())) {
            referenceYear = new TimeInstantPropertyType();
            referenceYear.populate(p.getReferenceyear(), "EXCEEDANCE_EXPOSURE_REFERENCE_YEAR_" + p.getUuid(), false, null);
        }
    }

    public void setObject(ExceedanceexposureBean p) {
        p.setExposedpopulation(populationExposed);
        if (ecosystemAreaExposed != null) {
            p.setExposedarea(ecosystemAreaExposed.getValue());
        }
        p.setSensitiveresidentpopulation(sensitivePopulation);
        p.setRelevantinfrastructure(infrastructureServices);
        if (referenceYear != null) {
            p.setReferenceyear(referenceYear.getValue());
        }
    }
}
