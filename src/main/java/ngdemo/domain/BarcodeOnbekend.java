/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author olivier deleye
 */
@Entity
@Table(name = "barcodes_onbekend")
public class BarcodeOnbekend implements Serializable {
    
     private static final long serialVersionUID = 1L;
     
     @Id
     private String barcode;
     @Column
     private LocalDate scanDatum;

    /**
     *
     */
    public BarcodeOnbekend() {
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
    public LocalDate getScanDatum() {
        return scanDatum;
    }

    /**
     *
     * @param scanDatum
     */
    public void setScanDatum(LocalDate scanDatum) {
        this.scanDatum = scanDatum;
    }
    
    /**
     *
     * @return
     */
    public String getFormattedScandatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = scanDatum.format(formatter);
       
        return formattedDate;
    }
    
        /*Comparator voor sortering op scanDatum*/
    public static Comparator<BarcodeOnbekend> barcodeOnbekendComperator = new Comparator<BarcodeOnbekend>() {

 
         @Override
         public int compare(BarcodeOnbekend b1, BarcodeOnbekend b2) {
             
           LocalDate scanDatum1 = b1.getScanDatum();
	   LocalDate scanDatum2 = b2.getScanDatum();

	   //ascending order
	   return scanDatum1.compareTo(scanDatum2);
         }
    };

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.barcode);
        hash = 19 * hash + Objects.hashCode(this.scanDatum);
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
        final BarcodeOnbekend other = (BarcodeOnbekend) obj;
        if (!Objects.equals(this.barcode, other.barcode)) {
            return false;
        }
        return Objects.equals(this.scanDatum, other.scanDatum);
    }

    @Override
    public String toString() {
        return "BarcodeOnbekend{" + "barcode=" + barcode + ", scanDatum=" + scanDatum + '}';
    }
     
    
     
}
