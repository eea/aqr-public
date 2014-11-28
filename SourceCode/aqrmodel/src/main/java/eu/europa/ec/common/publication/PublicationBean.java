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

public class PublicationBean {

    private String uuid;
    private String title;
    private String description;
    private String author;
    private String publicationdateId;
    private String publicationdateTimeposition;
    private String publisher;
    private String weblink;

    public PublicationBean() {
    }

    public PublicationBean(String uuid) {
        this.uuid = uuid;
    }

    public PublicationBean(String uuid, String title, String description, String author, String publicationdateId, String publicationdateTimeposition, String publisher) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.author = author;
        this.publicationdateId = publicationdateId;
        this.publicationdateTimeposition = publicationdateTimeposition;
        this.publisher = publisher;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationdateId() {
        return publicationdateId;
    }

    public void setPublicationdateId(String publicationdateId) {
        this.publicationdateId = publicationdateId;
    }

    public String getPublicationdateTimeposition() {
        return publicationdateTimeposition;
    }

    public void setPublicationdateTimeposition(String publicationdateTimeposition) {
        this.publicationdateTimeposition = publicationdateTimeposition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }
}
