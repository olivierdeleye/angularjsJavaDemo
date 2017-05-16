/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author olivier deleye
 */
@Entity
@Table(name="barcodes")
@NamedQueries({@NamedQuery(name = "listBarcodesOntvangen", query = "SELECT b from Barcode b where b.ontvangen = :ontvangen")
})
public class Barcode implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String barcode;
    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean ontvangen;
  
    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean gekoppeld;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "barcodes_lanceringnrs", joinColumns = @JoinColumn(name = "fk_barcode"), 
                                inverseJoinColumns = @JoinColumn(name = "fk_lanceringNr")) 
    private Set <OrderLijn> orderlijnen;
    
    @ManyToOne
    @JoinColumn(name ="fk_karNr")
    private Kar kar;
    
    @ManyToOne
    @JoinColumn(name = "fk_locatie")
    private Locatie locatie;
    
    @OneToMany(mappedBy = "barcode", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set <ScanTimeStamp> scanTimeStamps;
    
    /**
     *
     * @param barcode
     */
    public Barcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     *
     */
    public Barcode() {
    }

    /**
     *
     * @return
     */
    public String getBarcode() {
        return barcode;
    }
 
    /**
     *
     * @param barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    /**
     *
     * @return
     */
    public boolean isOntvangen() {
        return ontvangen;
    }

    /**
     *
     * @param ontvangen
     */
    public void setOntvangen(boolean ontvangen) {
        this.ontvangen = ontvangen;
    }

    public boolean isGekoppeld() {
        return gekoppeld;
    }

    public void setGekoppeld(boolean gekoppeld) {
        this.gekoppeld = gekoppeld;
    }

    /**
     *
     * @return
     */
    public Set<OrderLijn> getOrderlijnen() {
        return orderlijnen;
    }

    /**
     *
     * @param orderlijnen
     */
    public void setOrderlijnen(Set<OrderLijn> orderlijnen) {
        this.orderlijnen = orderlijnen;
    }
    
    /**
     *
     * @param orderlijn
     */
    public void addOrderlijn(OrderLijn orderlijn){     
        orderlijnen.add(orderlijn);
        if (!orderlijn.getBarcodes().contains(this)) {
           orderlijn.addBarcode(this);
        }
    }
    
    /**
     *
     * @param orderlijn
     */
    public void removeOrderlijn(OrderLijn orderlijn){
        orderlijnen.remove(orderlijn);
         if (orderlijn.getBarcodes().contains(this)) {
           orderlijn.removeBarcode(this);
        }
    }

    /**
     *
     * @return
     */
    public Kar getKar() {
        return kar;
    }

    /**
     *
     * @param kar
     */
    public void setKar(Kar kar) {
        this.kar = kar;
    }

    /**
     *
     * @return
     */
    public Locatie getLocatie() {
        return locatie;
    }

    /**
     *
     * @param locatie
     */
    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

        /**
     *
     * @return
     */
    public Set<ScanTimeStamp> getScanTimeStamps() {
        return scanTimeStamps;
    }

    /**
     *
     * @param scanTimeStamps ScanTimeStamp
     */
    public void setScanTimeStamps(Set<ScanTimeStamp> scanTimeStamps) {
        this.scanTimeStamps = scanTimeStamps;
    }
    
    /**
     *
     * @param scanTimeStamp ScanTimeStamp
     */
    public void addScanTimeStamp(ScanTimeStamp scanTimeStamp){
        scanTimeStamps.add(scanTimeStamp);
        scanTimeStamp.setBarcode(this);
    }
    
    /**
     *
     * @param scanTimeStamp ScanTimeStamp
     */
    public void removeScanTimeStamp(ScanTimeStamp scanTimeStamp){
        scanTimeStamp.setBarcode(null);
        this.scanTimeStamps.remove(scanTimeStamp);
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.barcode);
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
        final Barcode other = (Barcode) obj;
        if (!Objects.equals(this.barcode, other.barcode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Barcode{" + "barcode=" + barcode + '}';
    }
    
    
}
