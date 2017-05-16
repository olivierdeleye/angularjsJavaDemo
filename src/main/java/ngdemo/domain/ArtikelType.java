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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author olivier deleye
 */
@Entity
@Table( name= "artikeltypes")
public class ArtikelType implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String artNr;
    @Column
    private String type;
    
    @OneToMany(mappedBy = "artikelType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set <OrderLijn> orderlijnen;
   
    /**
     *
     */
    public ArtikelType() {
    }

    /**
     *
     * @return
     */
    public String getArtNr() {
        return artNr;
    }

    /**
     *
     * @param artNr
     */
    public void setArtNr(String artNr) {
        this.artNr = artNr;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
        orderlijn.setArtikelType(this);
    }
    
    /**
     *
     * @param orderlijn
     */
    public void removeOrderlijn(OrderLijn orderlijn){
        orderlijn.setArtikelType(null);
        this.orderlijnen.remove(orderlijn);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.artNr);
        hash = 23 * hash + Objects.hashCode(this.type);
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
        final ArtikelType other = (ArtikelType) obj;
        if (!Objects.equals(this.artNr, other.artNr)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArtikelType{" + "artNr=" + artNr + ", type=" + type + '}';
    }
    
    
}
