/*
 * Entity class voor table scan_timestamp
   bij iedere scan van een barcode wordt datum en tijdstip bijgehouden
 */
package ngdemo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author olivier deleye
 */
@Entity
@Table(name = "scan_timestamp")
public class ScanTimeStamp implements Serializable{
    
    /**
     *
     */
    public ScanTimeStamp(){  
    }
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate datum;
    @Column
    private LocalTime tijdstip;
    
    @ManyToOne
    @JoinColumn(name= "fk_barcode")
    private Barcode barcode;
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public LocalDate getDatum() {
        return datum;
    }

    /**
     *
     * @param datum
     */
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    /**
     *
     * @return
     */
    public LocalTime getTijdstip() {
        return tijdstip;
    }

    /**
     *
     * @param tijdstip
     */
    public void setTijdstip(LocalTime tijdstip) {
        this.tijdstip = tijdstip;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.datum);
        hash = 97 * hash + Objects.hashCode(this.tijdstip);
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
        final ScanTimeStamp other = (ScanTimeStamp) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.tijdstip, other.tijdstip)) {
            return false;
        }
    
        return true;
    }

    @Override
    public String toString() {
        return "ScanTimeStamp{" + "id=" + id + ", datum=" + datum + ", tijdstip=" + tijdstip + '}';
    }
    
    
    
}
