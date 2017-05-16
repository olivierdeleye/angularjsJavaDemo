/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author olivier deleye
 */
@Entity
@Table(name="locaties")
public class Locatie implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String locatie;
    
    @Column
    private String omschrijving;
    
    @OneToMany(mappedBy = "locatie", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set <Barcode> barcodes;

    /**
     *
     */
    public Locatie() {
    }

    /**
     *
     * @return
     */
    public String getLocatie() {
        return locatie;
    }

    /**
     *
     * @param locatie
     */
    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    /**
     *
     * @return
     */
    public String getOmschrijving() {
        return omschrijving;
    }

    /**
     *
     * @param omschrijving
     */
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    /**
     *
     * @return
     */
    public Set<Barcode> getBarcodes() {
        return barcodes;
    }

    /**
     *
     * @param barcodes
     */
    public void setBarcodes(Set<Barcode> barcodes) {
        this.barcodes = barcodes;
    }
    
    /**
     *
     * @param barcode
     */
    public void addBarcode(Barcode barcode){
        barcodes.add(barcode);
        barcode.setLocatie(this);
    }

    /**
     *
     * @param barcode
     */
    public void removeBarcode(Barcode barcode){
        barcode.setLocatie(null);
        this.barcodes.remove(barcode);
    }
    
    
}
