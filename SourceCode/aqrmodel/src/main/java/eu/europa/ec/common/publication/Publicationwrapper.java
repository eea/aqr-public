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
package eu.europa.ec.common.publication;

import eu.europa.ec.aqrmodel.Publication;

public class Publicationwrapper {

    public Publicationwrapper() {
    }

    public static PublicationBean convertPublicationInPublicationBean(Publication publication) {
        PublicationBean publicationBean = new PublicationBean();

        String uuid = publication.getUuid();
        String title = publication.getTitle();
        String description = publication.getDescription();
        String author = publication.getAuthor();
        String publicationdateId = publication.getPublicationdateId();
        String publicationdateTimeposition = publication.getPublicationdateTimeposition();
        String publisher = publication.getPublisher();
        String weblink = publication.getWeblink();

        publicationBean.setUuid(uuid);
        publicationBean.setTitle(title);
        publicationBean.setDescription(description);
        publicationBean.setAuthor(author);
        publicationBean.setPublicationdateId(publicationdateId);
        publicationBean.setPublicationdateTimeposition(publicationdateTimeposition);
        publicationBean.setPublisher(publisher);
        publicationBean.setWeblink(weblink);

        return publicationBean;
    }

    public static Publication convertPublicationBeanInPublication(PublicationBean publicationBean) {
        Publication publication = new Publication();

        String uuid = publicationBean.getUuid();

        publication.setUuid(uuid);

        if (publicationBean.getTitle() != null) {
            publication.setTitle(publicationBean.getTitle());
        } else {
            publication.setTitle("");
        }

        if (publicationBean.getDescription() != null) {
            publication.setDescription(publicationBean.getDescription());
        } else {
            publication.setDescription("");
        }
        if (publicationBean.getAuthor() != null) {
            publication.setAuthor(publicationBean.getAuthor());
        } else {
            publication.setAuthor("");
        }
        if (publicationBean.getPublicationdateId() != null) {
            publication.setPublicationdateId(publicationBean.getPublicationdateId());
        } else {
            publication.setPublicationdateId("");
        }
        if (publicationBean.getPublicationdateTimeposition() != null) {
            publication.setPublicationdateTimeposition(publicationBean.getPublicationdateTimeposition());
        } else {
            publication.setPublicationdateTimeposition("");
        }
        if (publicationBean.getPublisher() != null) {
            publication.setPublisher(publicationBean.getPublisher());
        } else {
            publication.setPublisher("");
        }
        if (publicationBean.getWeblink() != null) {
            publication.setWeblink(publicationBean.getWeblink());
        } else {
            publication.setWeblink("");
        }

        return publication;
    }
}
