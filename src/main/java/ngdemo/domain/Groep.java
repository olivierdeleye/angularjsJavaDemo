/*
 * Entity class die een leveranciergroep voorstelt.
 */
package ngdemo.domain;

import java.io.Serializable;
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
@Table(name = "leveranciergroepen")
public class Groep implements Serializable {
    private static final long serialVersionUID = 1L;
    

    @Id
    private Long id;
    @Column
    private String groepNaam;
    
//    @OneToMany(mappedBy= "groep", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    private Set <Leverancier> leveranciers;

    public Groep() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroepNaam() {
        return groepNaam;
    }

    public void setGroepNaam(String groepNaam) {
        this.groepNaam = groepNaam;
    }

//    public Set<Leverancier> getLeveranciers() {
//        return leveranciers;
//    }
//
//    public void setLeveranciers(Set<Leverancier> leveranciers) {
//        this.leveranciers = leveranciers;
//    }
//
//    public void addLeverancier(Leverancier leverancier){
//        leveranciers.add(leverancier);
//        leverancier.setGroep(this);
//    }
//    
//    public void removeLeverancier(Leverancier leverancier){
//        leverancier.setGroep(null);
//        this.leveranciers.remove(leverancier);
//    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.groepNaam);
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
        final Groep other = (Groep) obj;
        if (!Objects.equals(this.groepNaam, other.groepNaam)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Groep{" + "id=" + id + ", groepNaam=" + groepNaam + '}';
    }
    
}
