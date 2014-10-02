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

import eu.europa.ec.sourceapprotionment.exceedancedescription.exceedancearea.ExceedanceareaBean;
import eu.europa.ec.aqrsystem.utils.BaseUtils;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.XMLManager;
import eu.europa.ec.aqrsystem.xml.gml.AreaType;
import eu.europa.ec.aqrsystem.xml.gml.ReferenceType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:ExceedanceAreaType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ExceedanceAreaType {

    @XmlElement(namespace = Namespaces.aqd)
    protected List<ReferenceType> areaClassification = new ArrayList<ReferenceType>();
    @XmlElement(namespace = Namespaces.aqd)
    protected AreaType surfaceArea;
    @XmlElement(namespace = Namespaces.aqd)
    protected LengthType roadLength;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<AQD_SamplingPointPropertyType> stationUsed;
    @XmlElement(namespace = Namespaces.aqd)
    protected List<AQD_ModelPropertyType> modelUsed;

    public void populate(ExceedanceareaBean p, String namespace) {
        if (p.getAreaclassificationList_uri() != null) {
            for (String uri : p.getAreaclassificationList_uri()) {
                areaClassification.add(new ReferenceType().setHref(uri, false));
            }
        }
        if (areaClassification.isEmpty()) {
            areaClassification.add(new ReferenceType());
        }
        if (BaseUtils.isDefined(p.getAreaestimate())) {
            surfaceArea = new AreaType();
            surfaceArea.populate("http://dd.eionet.europa.eu/vocabulary/uom/area/km2", p.getAreaestimate());
        }
        if (BaseUtils.isDefined(p.getRoadlenghtestimate())) {
            roadLength = new LengthType();
            roadLength.populate("http://dd.eionet.europa.eu/vocabulary/uom/length/km", p.getRoadlenghtestimate());
        }
        if (namespace.charAt(namespace.length() - 1) != '/') {
            namespace += '/';
        }
        if (BaseUtils.isDefined(p.getStationused())) {
            stationUsed = new ArrayList<AQD_SamplingPointPropertyType>();
            for (String s : BaseUtils.stringToListOfTags(p.getStationused())) {
                stationUsed.add(new AQD_SamplingPointPropertyType().setHref(namespace + s));
            }
        }
        if (BaseUtils.isDefined(p.getModelused())) {
            modelUsed = new ArrayList<AQD_ModelPropertyType>();
            for (String s : BaseUtils.stringToListOfTags(p.getModelused())) {
                modelUsed.add(new AQD_ModelPropertyType().setHref(namespace + s));
            }
        }
    }

    public void setObject(ExceedanceareaBean p) {
        p.setAreaclassificationList_uri(new ArrayList<String>());
        if (areaClassification != null) {
            for (ReferenceType ac : areaClassification) {
                if (BaseUtils.isDefined(ac.getHref())) {
                    p.getAreaclassificationList_uri().add(ac.getHref());
                }
            }
        }

        if (surfaceArea != null) {
            p.setAreaestimate(surfaceArea.getValue());
        }
        if (roadLength != null) {
            p.setRoadlenghtestimate(roadLength.getValue());
        }

        String param = null;
        if (stationUsed != null) {
            for (AQD_SamplingPointPropertyType a : stationUsed) {
                String href = a.getHref();
                if (href.length() >= 1) {
                    if (param == null) {
                        param = XMLManager.convertHrefToLocalId(href);
                    } else {
                        param += "," + XMLManager.convertHrefToLocalId(href);
                    }
                }
            }
        }
        p.setStationused(param);

        param = null;
        if (modelUsed != null) {
            for (AQD_ModelPropertyType a : modelUsed) {
                String href = a.getHref();
                if (href.length() >= 1) {
                    if (param == null) {
                        param = XMLManager.convertHrefToLocalId(href);
                    } else {
                        param += "," + XMLManager.convertHrefToLocalId(href);
                    }
                }
            }
        }
        p.setModelused(param);
    }
}
