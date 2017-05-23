/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author olivier deleye
 */
@Entity
@Table(name="leveranciers")
public class Leverancier implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String leverancierNr;
    @Column
    private String lev_naam;
    @Column
    private String straat;
    @Column
    private String huisNr;
    @Column
    private String busNr;
    @Column
    private String gemeente;
    @Column
    private String postcode;
    @Column
    private String telefoon;
    @Column
    private String fax;
    @Column
    private String contactpersoon;
    @Column
    private String opmerking;
    

//    @OneToMany(mappedBy = "leverancier", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    private Set <OrderLijn> orderlijnen;
    
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="fk_groep")
    private Groep groep;

    /**
     *
     */
    public Leverancier(){
    }

    /**
     *
     * @return
     */
    public String getLeverancierNr(){
        return leverancierNr;
    }
    
    /**
     *
     * @param leverancierNr
     */
    public void setLeverancierNr(String leverancierNr){
        this.leverancierNr = leverancierNr;
    }

    /**
     *
     * @return
     */
    public String getStraat() {
        return straat;
    }

    /**
     *
     * @param straat
     */
    public void setStraat(String straat) {
        this.straat = straat;
    }

    /**
     *
     * @return
     */
    public String getHuisNr() {
        return huisNr;
    }

    /**
     *
     * @param huisNr
     */
    public void setHuisNr(String huisNr) {
        this.huisNr = huisNr;
    }

    /**
     *
     * @return
     */
    public String getBusNr(){
        return busNr;
    }
    
    /**
     *
     * @param busNr
     */
    public void setBusNr(String busNr){
        this.busNr = busNr;
    }

    /**
     *
     * @return
     */
    public String getGemeente() {
        return gemeente;
    }

    /**
     *
     * @param gemeente
     */
    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    /**
     *
     * @return
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     *
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     *
     * @return
     */
    public String getTelNr() {
        return telefoon;
    }

    /**
     *
     * @param telefoon
     */
    public void setTelNr(String telefoon) {
        this.telefoon = telefoon;
    }

    /**
     *
     * @return
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     *
     * @return
     */
    public String getContactpersoon() {
        return contactpersoon;
    }

    /**
     *
     * @param contactpersoon
     */
    public void setContactpersoon(String contactpersoon) {
        this.contactpersoon = contactpersoon;
    }

    /**
     *
     * @return
     */
    public String getOpmerking() {
        return opmerking;
    }

    /**
     *
     * @param opmerking
     */
    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    /**
     *
     * @return
     */
    public String getTelefoon() {
        return telefoon;
    }

    /**
     *
     * @param telefoon
     */
    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

//    /**
//     *
//     * @return
//     */
//    public Set <OrderLijn> getOrderlijnen() {
//        return orderlijnen;
//    }
//
//    /**
//     *
//     * @param orderlijnen
//     */
//    public void setOrderlijnen(Set <OrderLijn> orderlijnen) {
//        this.orderlijnen = orderlijnen;
//    } 
//    
//    /**
//     *
//     * @param orderlijn
//     */
//    public void addOrderLijn(OrderLijn orderlijn){
//        orderlijnen.add(orderlijn);
//        orderlijn.setLeverancier(this);
//    }
//    
//    /**
//     *
//     * @param orderlijn
//     */
//    public void removeOrderlijn(OrderLijn orderlijn){
//        orderlijn.setLeverancier(null);
//        this.orderlijnen.remove(orderlijn);
//    }

    public Groep getGroep() {
        return groep;
    }

    public void setGroep(Groep groep) {
        this.groep = groep;
    }

    
    /**
     *
     * @return
     */
    public String getLev_naam() {
        return lev_naam;
    }

    /**
     *
     * @param lev_naam
     */
    public void setLev_naam(String lev_naam) {
        this.lev_naam = lev_naam;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.leverancierNr);
        hash = 71 * hash + Objects.hashCode(this.lev_naam);
        hash = 71 * hash + Objects.hashCode(this.straat);
        hash = 71 * hash + Objects.hashCode(this.huisNr);
        hash = 71 * hash + Objects.hashCode(this.busNr);
        hash = 71 * hash + Objects.hashCode(this.gemeente);
        hash = 71 * hash + Objects.hashCode(this.postcode);
        hash = 71 * hash + Objects.hashCode(this.telefoon);
        hash = 71 * hash + Objects.hashCode(this.fax);
        hash = 71 * hash + Objects.hashCode(this.contactpersoon);
        hash = 71 * hash + Objects.hashCode(this.opmerking);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Leverancier other = (Leverancier) obj;
        if (!Objects.equals(this.leverancierNr, other.leverancierNr)) {
            return false;
        }
        if (!Objects.equals(this.lev_naam, other.lev_naam)) {
            return false;
        }
        if (!Objects.equals(this.straat, other.straat)) {
            return false;
        }
        if (!Objects.equals(this.huisNr, other.huisNr)) {
            return false;
        }
        if (!Objects.equals(this.busNr, other.busNr)) {
            return false;
        }
        if (!Objects.equals(this.gemeente, other.gemeente)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.telefoon, other.telefoon)) {
            return false;
        }
        if (!Objects.equals(this.fax, other.fax)) {
            return false;
        }
        if (!Objects.equals(this.contactpersoon, other.contactpersoon)) {
            return false;
        }
        if (!Objects.equals(this.opmerking, other.opmerking)) {
            return false;
        }
//        if (!Objects.equals(this.orderlijnen, other.orderlijnen)) {
//            return false;
//        }
        return true;
    }

    @Override
    public String toString() {
        return "Leverancier{" + "leverancierNr=" + leverancierNr + ", lev_naam=" + lev_naam + ", straat=" + straat + ", huisNr=" + huisNr + ", busNr=" + busNr + ", gemeente=" + gemeente + ", postcode=" + postcode + ", telefoon=" + telefoon + ", fax=" + fax + ", contactpersoon=" + contactpersoon + ", opmerking=" + opmerking + '}';
    }
    
    
    
    
}
