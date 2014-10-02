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
package eu.europa.ec.aqrmodel;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "regionalbackground")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regionalbackground.findAll", query = "SELECT r FROM Regionalbackground r"),
    @NamedQuery(name = "Regionalbackground.findByUuid", query = "SELECT r FROM Regionalbackground r WHERE r.uuid = :uuid"),
    @NamedQuery(name = "Regionalbackground.findByTotal", query = "SELECT r FROM Regionalbackground r WHERE r.total = :total"),
    @NamedQuery(name = "Regionalbackground.findByTotalcomment", query = "SELECT r FROM Regionalbackground r WHERE r.totalcomment = :totalcomment"),
    @NamedQuery(name = "Regionalbackground.findByFromwithinms", query = "SELECT r FROM Regionalbackground r WHERE r.fromwithinms = :fromwithinms"),
    @NamedQuery(name = "Regionalbackground.findByFromwithinmscomment", query = "SELECT r FROM Regionalbackground r WHERE r.fromwithinmscomment = :fromwithinmscomment"),
    @NamedQuery(name = "Regionalbackground.findByTransboundary", query = "SELECT r FROM Regionalbackground r WHERE r.transboundary = :transboundary"),
    @NamedQuery(name = "Regionalbackground.findByTransboundarycomment", query = "SELECT r FROM Regionalbackground r WHERE r.transboundarycomment = :transboundarycomment"),
    @NamedQuery(name = "Regionalbackground.findByNaturalregionalbackground", query = "SELECT r FROM Regionalbackground r WHERE r.naturalregionalbackground = :naturalregionalbackground"),
    @NamedQuery(name = "Regionalbackground.findByNaturalregionalbackgroundcomment", query = "SELECT r FROM Regionalbackground r WHERE r.naturalregionalbackgroundcomment = :naturalregionalbackgroundcomment"),
    @NamedQuery(name = "Regionalbackground.findByOther", query = "SELECT r FROM Regionalbackground r WHERE r.other = :other"),
    @NamedQuery(name = "Regionalbackground.findByOthercomment", query = "SELECT r FROM Regionalbackground r WHERE r.othercomment = :othercomment"),
    @NamedQuery(name = "Regionalbackground.findByUnitmisure", query = "SELECT r FROM Regionalbackground r WHERE r.unitmisure = :unitmisure"),
    @NamedQuery(name = "Regionalbackground.findByUnitmisurecomment", query = "SELECT r FROM Regionalbackground r WHERE r.unitmisurecomment = :unitmisurecomment"),
    @NamedQuery(name = "Regionalbackground.deleteByUuid", query = "DELETE FROM Regionalbackground m WHERE m.uuid = :uuid")
})
public class Regionalbackground implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;

    @Basic(optional = false)
    @Column(name = "total")
    private String total;
    @Column(name = "totalcomment")
    private String totalcomment;
    @Column(name = "total_nil")
    private Boolean totalNil;
    @Column(name = "total_nilreason")
    private String totalNilreason;

    @Basic(optional = false)
    @Column(name = "fromwithinms")
    private String fromwithinms;
    @Column(name = "fromwithinmscomment")
    private String fromwithinmscomment;
    @Column(name = "fromwithinms_nil")
    private Boolean fromwithinmsNil;
    @Column(name = "fromwithinms_nilreason")
    private String fromwithinmsNilreason;

    @Basic(optional = false)
    @Column(name = "transboundary")
    private String transboundary;
    @Column(name = "transboundarycomment")
    private String transboundarycomment;
    @Column(name = "transboundary_nil")
    private Boolean transboundaryNil;
    @Column(name = "transboundary_nilreason")
    private String transboundaryNilreason;

    @Basic(optional = false)
    @Column(name = "naturalregionalbackground")
    private String naturalregionalbackground;
    @Column(name = "naturalregionalbackgroundcomment")
    private String naturalregionalbackgroundcomment;
    @Column(name = "naturalregionalbackground_nil")
    private Boolean naturalregionalbackgroundNil;
    @Column(name = "naturalregionalbackground_nilreason")
    private String naturalregionalbackgroundNilreason;

    @Column(name = "other")
    private String other;
    @Column(name = "othercomment")
    private String othercomment;
    @Column(name = "other_nil")
    private Boolean otherNil;
    @Column(name = "other_nilreason")
    private String otherNilreason;

    @Column(name = "unitmisure")
    private String unitmisure;
    @Column(name = "unitmisurecomment")
    private String unitmisurecomment;
    @Column(name = "unitmisure_nil")
    private Boolean unitmisureNil;
    @Column(name = "unitmisure_nilreason")
    private String unitmisureNilreason;

    public Regionalbackground() {
    }

    public Regionalbackground(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regionalbackground)) {
            return false;
        }
        Regionalbackground other = (Regionalbackground) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Regionalbackground[ uuid=" + uuid + " ]";
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalcomment() {
        return totalcomment;
    }

    public void setTotalcomment(String totalcomment) {
        this.totalcomment = totalcomment;
    }

    public Boolean getTotalNil() {
        return totalNil;
    }

    public void setTotalNil(Boolean totalNil) {
        this.totalNil = totalNil;
    }

    public String getTotalNilreason() {
        return totalNilreason;
    }

    public void setTotalNilreason(String totalNilreason) {
        this.totalNilreason = totalNilreason;
    }

    public String getFromwithinms() {
        return fromwithinms;
    }

    public void setFromwithinms(String fromwithinms) {
        this.fromwithinms = fromwithinms;
    }

    public String getFromwithinmscomment() {
        return fromwithinmscomment;
    }

    public void setFromwithinmscomment(String fromwithinmscomment) {
        this.fromwithinmscomment = fromwithinmscomment;
    }

    public Boolean getFromwithinmsNil() {
        return fromwithinmsNil;
    }

    public void setFromwithinmsNil(Boolean fromwithinmsNil) {
        this.fromwithinmsNil = fromwithinmsNil;
    }

    public String getFromwithinmsNilreason() {
        return fromwithinmsNilreason;
    }

    public void setFromwithinmsNilreason(String fromwithinmsNilreason) {
        this.fromwithinmsNilreason = fromwithinmsNilreason;
    }

    public String getTransboundary() {
        return transboundary;
    }

    public void setTransboundary(String transboundary) {
        this.transboundary = transboundary;
    }

    public String getTransboundarycomment() {
        return transboundarycomment;
    }

    public void setTransboundarycomment(String transboundarycomment) {
        this.transboundarycomment = transboundarycomment;
    }

    public Boolean getTransboundaryNil() {
        return transboundaryNil;
    }

    public void setTransboundaryNil(Boolean transboundaryNil) {
        this.transboundaryNil = transboundaryNil;
    }

    public String getTransboundaryNilreason() {
        return transboundaryNilreason;
    }

    public void setTransboundaryNilreason(String transboundaryNilreason) {
        this.transboundaryNilreason = transboundaryNilreason;
    }

    public String getNaturalregionalbackground() {
        return naturalregionalbackground;
    }

    public void setNaturalregionalbackground(String naturalregionalbackground) {
        this.naturalregionalbackground = naturalregionalbackground;
    }

    public String getNaturalregionalbackgroundcomment() {
        return naturalregionalbackgroundcomment;
    }

    public void setNaturalregionalbackgroundcomment(String naturalregionalbackgroundcomment) {
        this.naturalregionalbackgroundcomment = naturalregionalbackgroundcomment;
    }

    public Boolean getNaturalregionalbackgroundNil() {
        return naturalregionalbackgroundNil;
    }

    public void setNaturalregionalbackgroundNil(Boolean naturalregionalbackgroundNil) {
        this.naturalregionalbackgroundNil = naturalregionalbackgroundNil;
    }

    public String getNaturalregionalbackgroundNilreason() {
        return naturalregionalbackgroundNilreason;
    }

    public void setNaturalregionalbackgroundNilreason(String naturalregionalbackgroundNilreason) {
        this.naturalregionalbackgroundNilreason = naturalregionalbackgroundNilreason;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOthercomment() {
        return othercomment;
    }

    public void setOthercomment(String othercomment) {
        this.othercomment = othercomment;
    }

    public Boolean getOtherNil() {
        return otherNil;
    }

    public void setOtherNil(Boolean otherNil) {
        this.otherNil = otherNil;
    }

    public String getOtherNilreason() {
        return otherNilreason;
    }

    public void setOtherNilreason(String otherNilreason) {
        this.otherNilreason = otherNilreason;
    }

    public String getUnitmisure() {
        return unitmisure;
    }

    public void setUnitmisure(String unitmisure) {
        this.unitmisure = unitmisure;
    }

    public String getUnitmisurecomment() {
        return unitmisurecomment;
    }

    public void setUnitmisurecomment(String unitmisurecomment) {
        this.unitmisurecomment = unitmisurecomment;
    }

    public Boolean getUnitmisureNil() {
        return unitmisureNil;
    }

    public void setUnitmisureNil(Boolean unitmisureNil) {
        this.unitmisureNil = unitmisureNil;
    }

    public String getUnitmisureNilreason() {
        return unitmisureNilreason;
    }

    public void setUnitmisureNilreason(String unitmisureNilreason) {
        this.unitmisureNilreason = unitmisureNilreason;
    }

}
