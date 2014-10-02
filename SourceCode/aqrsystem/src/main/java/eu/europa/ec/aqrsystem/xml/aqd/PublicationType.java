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

import eu.europa.ec.common.publication.PublicationBean;
import eu.europa.ec.evaluationscenario.EvaluationScenarioManager;
import eu.europa.ec.plan.PlanManager;
import eu.europa.ec.aqrsystem.xml.Namespaces;
import eu.europa.ec.aqrsystem.xml.gml.TimeInstantPropertyType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents aqd:PublicationType
 */
@XmlAccessorType(XmlAccessType.NONE)
public class PublicationType {

    @XmlElement(namespace = Namespaces.aqd)
    protected String description;
    @XmlElement(namespace = Namespaces.aqd)
    protected String title;
    @XmlElement(namespace = Namespaces.aqd)
    protected String author;
    @XmlElement(namespace = Namespaces.aqd)
    protected TimeInstantPropertyType publicationDate = new TimeInstantPropertyType();
    @XmlElement(namespace = Namespaces.aqd)
    protected String publisher;
    @XmlElement(namespace = Namespaces.aqd)
    protected String webLink;

    public PublicationType populate(PublicationBean p) {
        if (p != null) {
            description = p.getDescription() == null ? "" : p.getDescription();
            title = p.getTitle() == null ? "" : p.getTitle();
            author = p.getAuthor();
            publicationDate.populate(p.getPublicationdateTimeposition(), "PUBLICATION_DATE_" + p.getUuid(), false, null);
            publisher = p.getPublisher() == null ? "" : p.getPublisher();
            webLink = p.getWeblink();
        } else {
            description = "";
            title = "";
            publicationDate.populate(null, "PUBLICATION_DATE_", false, null);
            publisher = "";
        }
        return this;
    }

    public void saveToPlan(String planId) throws Exception {
        if (isSet(description) && isSet(title) && isSet(publicationDate.getValue()) && isSet(publisher)) {
            PlanManager planManager = new PlanManager();

            PublicationBean pub = new PublicationBean();
            pub.setDescription(description);
            pub.setTitle(title);
            pub.setAuthor(author);
            pub.setPublicationdateTimeposition(publicationDate.getValue());
            pub.setPublisher(publisher);
            pub.setWeblink(webLink);
            planManager.savePublicationToPlan(planId, pub);
        }
    }

    public void saveToScenario(String scenarioId) throws Exception {
        if (isSet(description) && isSet(title) && isSet(publicationDate.getValue()) && isSet(publisher)) {
            EvaluationScenarioManager scenarioManager = new EvaluationScenarioManager();

            PublicationBean pub = new PublicationBean();
            pub.setDescription(description);
            pub.setTitle(title);
            pub.setAuthor(author);
            pub.setPublicationdateTimeposition(publicationDate.getValue());
            pub.setPublisher(publisher);
            pub.setWeblink(webLink);
            scenarioManager.savePublicationToEvaluationscenario(scenarioId, pub);
        }
    }

    private boolean isSet(String value) {
        return value != null && !value.equals("");
    }
}
