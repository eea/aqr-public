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
@Table(name = "localincrement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localincrement.findAll", query = "SELECT l FROM Localincrement l"),
    @NamedQuery(name = "Localincrement.findByUuid", query = "SELECT l FROM Localincrement l WHERE l.uuid = :uuid"),
    @NamedQuery(name = "Localincrement.findByTotal", query = "SELECT l FROM Localincrement l WHERE l.total = :total"),
    @NamedQuery(name = "Localincrement.findByTotalcomment", query = "SELECT l FROM Localincrement l WHERE l.totalcomment = :totalcomment"),
    @NamedQuery(name = "Localincrement.findByTraffic", query = "SELECT l FROM Localincrement l WHERE l.traffic = :traffic"),
    @NamedQuery(name = "Localincrement.findByTrafficcomment", query = "SELECT l FROM Localincrement l WHERE l.trafficcomment = :trafficcomment"),
    @NamedQuery(name = "Localincrement.findByHeatandpowerproduction", query = "SELECT l FROM Localincrement l WHERE l.heatandpowerproduction = :heatandpowerproduction"),
    @NamedQuery(name = "Localincrement.findByHeatandpowerproductioncomment", query = "SELECT l FROM Localincrement l WHERE l.heatandpowerproductioncomment = :heatandpowerproductioncomment"),
    @NamedQuery(name = "Localincrement.findByAgriculture", query = "SELECT l FROM Localincrement l WHERE l.agriculture = :agriculture"),
    @NamedQuery(name = "Localincrement.findByAgriculturecomment", query = "SELECT l FROM Localincrement l WHERE l.agriculturecomment = :agriculturecomment"),
    @NamedQuery(name = "Localincrement.findByCommercialandresidential", query = "SELECT l FROM Localincrement l WHERE l.commercialandresidential = :commercialandresidential"),
    @NamedQuery(name = "Localincrement.findByCommercialandresidentialcomment", query = "SELECT l FROM Localincrement l WHERE l.commercialandresidentialcomment = :commercialandresidentialcomment"),
    @NamedQuery(name = "Localincrement.findByShipping", query = "SELECT l FROM Localincrement l WHERE l.shipping = :shipping"),
    @NamedQuery(name = "Localincrement.findByShippingcomment", query = "SELECT l FROM Localincrement l WHERE l.shippingcomment = :shippingcomment"),
    @NamedQuery(name = "Localincrement.findByOffroadmobilemachinery", query = "SELECT l FROM Localincrement l WHERE l.offroadmobilemachinery = :offroadmobilemachinery"),
    @NamedQuery(name = "Localincrement.findByOffroadmobilemachinerycomment", query = "SELECT l FROM Localincrement l WHERE l.offroadmobilemachinerycomment = :offroadmobilemachinerycomment"),
    @NamedQuery(name = "Localincrement.findByNaturallocalincrement", query = "SELECT l FROM Localincrement l WHERE l.naturallocalincrement = :naturallocalincrement"),
    @NamedQuery(name = "Localincrement.findByNaturallocalincrementcomment", query = "SELECT l FROM Localincrement l WHERE l.naturallocalincrementcomment = :naturallocalincrementcomment"),
    @NamedQuery(name = "Localincrement.findByTransboundary", query = "SELECT l FROM Localincrement l WHERE l.transboundary = :transboundary"),
    @NamedQuery(name = "Localincrement.findByTransboundarycomment", query = "SELECT l FROM Localincrement l WHERE l.transboundarycomment = :transboundarycomment"),
    @NamedQuery(name = "Localincrement.findByOther", query = "SELECT l FROM Localincrement l WHERE l.other = :other"),
    @NamedQuery(name = "Localincrement.findByOthercomment", query = "SELECT l FROM Localincrement l WHERE l.othercomment = :othercomment"),
    @NamedQuery(name = "Localincrement.findByUnitmisure", query = "SELECT l FROM Localincrement l WHERE l.unitmisure = :unitmisure"),
    @NamedQuery(name = "Localincrement.findByUnitmisurecomment", query = "SELECT l FROM Localincrement l WHERE l.unitmisurecomment = :unitmisurecomment"),
    @NamedQuery(name = "Localincrement.deleteByUuid", query = "DELETE FROM Localincrement m WHERE m.uuid = :uuid")
})
public class Localincrement implements Serializable {

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
    @Column(name = "traffic")
    private String traffic;
    @Column(name = "trafficcomment")
    private String trafficcomment;
    @Column(name = "traffic_nil")
    private Boolean trafficNil;
    @Column(name = "traffic_nilreason")
    private String trafficNilreason;
    @Basic(optional = false)
    @Column(name = "heatandpowerproduction")
    private String heatandpowerproduction;
    @Column(name = "heatandpowerproductioncomment")
    private String heatandpowerproductioncomment;
    @Column(name = "heatandpowerproduction_nil")
    private Boolean heatandpowerproductionNil;
    @Column(name = "heatandpowerproduction_nilreason")
    private String heatandpowerproductionNilreason;
    @Basic(optional = false)
    @Column(name = "agriculture")
    private String agriculture;
    @Column(name = "agriculturecomment")
    private String agriculturecomment;
    @Column(name = "agriculture_nil")
    private Boolean agricultureNil;
    @Column(name = "agriculture_nilreason")
    private String agricultureNilreason;
    @Basic(optional = false)
    @Column(name = "commercialandresidential")
    private String commercialandresidential;
    @Column(name = "commercialandresidentialcomment")
    private String commercialandresidentialcomment;
    @Column(name = "commercialandresidential_nil")
    private Boolean commercialandresidentialNil;
    @Column(name = "commercialandresidential_nilreason")
    private String commercialandresidentialNilreason;
    @Basic(optional = false)
    @Column(name = "shipping")
    private String shipping;
    @Column(name = "shippingcomment")
    private String shippingcomment;
    @Column(name = "shipping_nil")
    private Boolean shippingNil;
    @Column(name = "shipping_nilreason")
    private String shippingNilreason;
    @Basic(optional = false)
    @Column(name = "offroadmobilemachinery")
    private String offroadmobilemachinery;
    @Column(name = "offroadmobilemachinerycomment")
    private String offroadmobilemachinerycomment;
    @Column(name = "offroadmobilemachinery_nil")
    private Boolean offroadmobilemachineryNil;
    @Column(name = "offroadmobilemachinery_nilreason")
    private String offroadmobilemachineryNilreason;
    @Basic(optional = false)
    @Column(name = "naturallocalincrement")
    private String naturallocalincrement;
    @Column(name = "naturallocalincrementcomment")
    private String naturallocalincrementcomment;
    @Column(name = "naturallocalincrement_nil")
    private Boolean naturallocalincrementNil;
    @Column(name = "naturallocalincrement_nilreason")
    private String naturallocalincrementNilreason;
    @Basic(optional = false)
    @Column(name = "transboundary")
    private String transboundary;
    @Column(name = "transboundarycomment")
    private String transboundarycomment;
    @Column(name = "transboundary_nil")
    private Boolean transboundaryNil;
    @Column(name = "transboundary_nilreason")
    private String transboundaryNilreason;
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

    public Localincrement() {
    }

    public Localincrement(String uuid) {
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
        if (!(object instanceof Localincrement)) {
            return false;
        }
        Localincrement other = (Localincrement) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.europa.ec.aqrmodel.Localincrement[ uuid=" + uuid + " ]";
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

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTrafficcomment() {
        return trafficcomment;
    }

    public void setTrafficcomment(String trafficcomment) {
        this.trafficcomment = trafficcomment;
    }

    public Boolean getTrafficNil() {
        return trafficNil;
    }

    public void setTrafficNil(Boolean trafficNil) {
        this.trafficNil = trafficNil;
    }

    public String getTrafficNilreason() {
        return trafficNilreason;
    }

    public void setTrafficNilreason(String trafficNilreason) {
        this.trafficNilreason = trafficNilreason;
    }

    public String getHeatandpowerproduction() {
        return heatandpowerproduction;
    }

    public void setHeatandpowerproduction(String heatandpowerproduction) {
        this.heatandpowerproduction = heatandpowerproduction;
    }

    public String getHeatandpowerproductioncomment() {
        return heatandpowerproductioncomment;
    }

    public void setHeatandpowerproductioncomment(String heatandpowerproductioncomment) {
        this.heatandpowerproductioncomment = heatandpowerproductioncomment;
    }

    public Boolean getHeatandpowerproductionNil() {
        return heatandpowerproductionNil;
    }

    public void setHeatandpowerproductionNil(Boolean heatandpowerproductionNil) {
        this.heatandpowerproductionNil = heatandpowerproductionNil;
    }

    public String getHeatandpowerproductionNilreason() {
        return heatandpowerproductionNilreason;
    }

    public void setHeatandpowerproductionNilreason(String heatandpowerproductionNilreason) {
        this.heatandpowerproductionNilreason = heatandpowerproductionNilreason;
    }

    public String getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(String agriculture) {
        this.agriculture = agriculture;
    }

    public String getAgriculturecomment() {
        return agriculturecomment;
    }

    public void setAgriculturecomment(String agriculturecomment) {
        this.agriculturecomment = agriculturecomment;
    }

    public Boolean getAgricultureNil() {
        return agricultureNil;
    }

    public void setAgricultureNil(Boolean agricultureNil) {
        this.agricultureNil = agricultureNil;
    }

    public String getAgricultureNilreason() {
        return agricultureNilreason;
    }

    public void setAgricultureNilreason(String agricultureNilreason) {
        this.agricultureNilreason = agricultureNilreason;
    }

    public String getCommercialandresidential() {
        return commercialandresidential;
    }

    public void setCommercialandresidential(String commercialandresidential) {
        this.commercialandresidential = commercialandresidential;
    }

    public String getCommercialandresidentialcomment() {
        return commercialandresidentialcomment;
    }

    public void setCommercialandresidentialcomment(String commercialandresidentialcomment) {
        this.commercialandresidentialcomment = commercialandresidentialcomment;
    }

    public Boolean getCommercialandresidentialNil() {
        return commercialandresidentialNil;
    }

    public void setCommercialandresidentialNil(Boolean commercialandresidentialNil) {
        this.commercialandresidentialNil = commercialandresidentialNil;
    }

    public String getCommercialandresidentialNilreason() {
        return commercialandresidentialNilreason;
    }

    public void setCommercialandresidentialNilreason(String commercialandresidentialNilreason) {
        this.commercialandresidentialNilreason = commercialandresidentialNilreason;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getShippingcomment() {
        return shippingcomment;
    }

    public void setShippingcomment(String shippingcomment) {
        this.shippingcomment = shippingcomment;
    }

    public Boolean getShippingNil() {
        return shippingNil;
    }

    public void setShippingNil(Boolean shippingNil) {
        this.shippingNil = shippingNil;
    }

    public String getShippingNilreason() {
        return shippingNilreason;
    }

    public void setShippingNilreason(String shippingNilreason) {
        this.shippingNilreason = shippingNilreason;
    }

    public String getOffroadmobilemachinery() {
        return offroadmobilemachinery;
    }

    public void setOffroadmobilemachinery(String offroadmobilemachinery) {
        this.offroadmobilemachinery = offroadmobilemachinery;
    }

    public String getOffroadmobilemachinerycomment() {
        return offroadmobilemachinerycomment;
    }

    public void setOffroadmobilemachinerycomment(String offroadmobilemachinerycomment) {
        this.offroadmobilemachinerycomment = offroadmobilemachinerycomment;
    }

    public Boolean getOffroadmobilemachineryNil() {
        return offroadmobilemachineryNil;
    }

    public void setOffroadmobilemachineryNil(Boolean offroadmobilemachineryNil) {
        this.offroadmobilemachineryNil = offroadmobilemachineryNil;
    }

    public String getOffroadmobilemachineryNilreason() {
        return offroadmobilemachineryNilreason;
    }

    public void setOffroadmobilemachineryNilreason(String offroadmobilemachineryNilreason) {
        this.offroadmobilemachineryNilreason = offroadmobilemachineryNilreason;
    }

    public String getNaturallocalincrement() {
        return naturallocalincrement;
    }

    public void setNaturallocalincrement(String naturallocalincrement) {
        this.naturallocalincrement = naturallocalincrement;
    }

    public String getNaturallocalincrementcomment() {
        return naturallocalincrementcomment;
    }

    public void setNaturallocalincrementcomment(String naturallocalincrementcomment) {
        this.naturallocalincrementcomment = naturallocalincrementcomment;
    }

    public Boolean getNaturallocalincrementNil() {
        return naturallocalincrementNil;
    }

    public void setNaturallocalincrementNil(Boolean naturallocalincrementNil) {
        this.naturallocalincrementNil = naturallocalincrementNil;
    }

    public String getNaturallocalincrementNilreason() {
        return naturallocalincrementNilreason;
    }

    public void setNaturallocalincrementNilreason(String naturallocalincrementNilreason) {
        this.naturallocalincrementNilreason = naturallocalincrementNilreason;
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
