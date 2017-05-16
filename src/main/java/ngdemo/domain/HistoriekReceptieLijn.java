/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@Table(name = "historiek_receptie")

public class HistoriekReceptieLijn implements Serializable{

    /**
     *
     */
    public HistoriekReceptieLijn() {
    }
    
    /**
     *
     */
 
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate receptieDatum;
    
    @Column
    private int aantalVerwacht;
  
    @Column
    private String variantNr;
    
    @ManyToOne
    @JoinColumn(name = "fk_orderNr")
    private Order order;

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
    public LocalDate getReceptieDatum() {
        return receptieDatum;
    }

    /**
     *
     * @param receptieDatum
     */
    public void setReceptieDatum(LocalDate receptieDatum) {
        this.receptieDatum = receptieDatum;
    }

    /**
     *
     * @return
     */
    public String getFormattedReceptiedatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = receptieDatum.format(formatter);
       
        return formattedDate;
    }
    
    /**
     *
     * @return
     */
    public int getAantalVerwacht() {
        return aantalVerwacht;
    }

    /**
     *
     * @param aantalVerwacht
     */
    public void setAantalVerwacht(int aantalVerwacht) {
        this.aantalVerwacht = aantalVerwacht;
    }

    public String getVariantNr() {
        return variantNr;
    }

    public void setVariantNr(String variantNr) {
        this.variantNr = variantNr;
    }
  
    /**
     *
     * @return
     */
    public Order getOrder() {
        return order;
    }

    /**
     *
     * @param orderlijn
     */
    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.receptieDatum);
        hash = 67 * hash + this.aantalVerwacht;
        hash = 67 * hash + Objects.hashCode(this.order);
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
        final HistoriekReceptieLijn other = (HistoriekReceptieLijn) obj;
        if (this.aantalVerwacht != other.aantalVerwacht) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.receptieDatum, other.receptieDatum)) {
            return false;
        }
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HistoriekReceptieLijn{" + "id=" + id + ", receptieDatum=" + receptieDatum + ", aantalVerwacht=" + aantalVerwacht + ", variantNr=" + variantNr + ", orderNr=" + order.getOrderNr() + '}';
    }

    

    
    
}
