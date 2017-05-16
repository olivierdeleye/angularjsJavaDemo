/*
 * Class die een karType representeert in DB. Alle eigenschappen van een specifieke kar.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author olivier deleye
 */

@Entity
@Table(name = "kartypes")
public class KarType implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String type; // unieke string TYPE
    @Column 
    private int aantalVastePlaatsen;
    @Column
    private int totaalCapaciteit;
    @Column
    private int onderCapaciteit;
    @Column
    private int bovenCapaciteit;
    @Column
    private int volleHoogteCapaciteit; // compartiment links van kar.
    @Column
    private int aantal;
    @Column
    private int maxDikte; // dikte van paneel die in een vak in kar past
    @Column
    private int maxBreedte; // diepte van kar.
    
    @OneToMany(mappedBy = "karType", cascade = {CascadeType.ALL})
    private Set <Kar> karren;

    /**
     *
     */
    public KarType() {
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
    public int getAantalVastePlaatsen() {
        return aantalVastePlaatsen;
    }

    /**
     *
     * @param aantalVastePlaatsen
     */
    public void setAantalVastePlaatsen(int aantalVastePlaatsen) {
        this.aantalVastePlaatsen = aantalVastePlaatsen;
    }
   
    /**
     *
     * @return
     */
    public int getTotaalCapaciteit() {
        return totaalCapaciteit;
    }

    /**
     *
     * @param totaalCapaciteit
     */
    public void setTotaalCapaciteit(int totaalCapaciteit) {
        this.totaalCapaciteit = totaalCapaciteit;
    }

    /**
     *
     * @return
     */
    public int getOnderCapaciteit() {
        return onderCapaciteit;
    }

    /**
     *
     * @param onderCapaciteit
     */
    public void setOnderCapaciteit(int onderCapaciteit) {
        this.onderCapaciteit = onderCapaciteit;
    }

    /**
     *
     * @return
     */
    public int getBovenCapaciteit() {
        return bovenCapaciteit;
    }

    /**
     *
     * @param bovenCapaciteit
     */
    public void setBovenCapaciteit(int bovenCapaciteit) {
        this.bovenCapaciteit = bovenCapaciteit;
    }

    /**
     *
     * @return
     */
    public int getVolleHoogteCapaciteit() {
        return volleHoogteCapaciteit;
    }

    /**
     *
     * @param volleHoogteCapaciteit
     */
    public void setVolleHoogteCapaciteit(int volleHoogteCapaciteit) {
        this.volleHoogteCapaciteit = volleHoogteCapaciteit;
    }

    /**
     *
     * @return
     */
    public int getAantal() {
        return aantal;
    }

    /**
     *
     * @param aantal
     */
    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    /**
     *
     * @return
     */
    public int getMaxDikte() {
        return maxDikte;
    }

    /**
     *
     * @param maxDikte
     */
    public void setMaxDikte(int maxDikte) {
        this.maxDikte = maxDikte;
    }

    /**
     *
     * @return
     */
    public int getMaxBreedte() {
        return maxBreedte;
    }

    /**
     *
     * @param maxBreedte
     */
    public void setMaxBreedte(int maxBreedte) {
        this.maxBreedte = maxBreedte;
    }
    
    /**
     *
     * @return
     */
    public Set <Kar> getKarren() {
        return karren;
    }

    /**
     *
     * @param karren
     */
    public void setKarren(Set <Kar> karren) {
        this.karren = karren;
    }
    
    /**
     *
     * @param kar
     */
    public void addKar(Kar kar){
        karren.add(kar);
        kar.setKarType(this);
    }
    
    /**
     *
     * @param kar
     */
    public void removeKar(Kar kar){
        kar.setKarType(null);
        this.karren.remove(kar);
    }
    
    /**
     * Geeft een String [] terug van karType omschrijvingen (type) 
     * @param karTypes List van KarTypes
     * @return String []
     **/
    public String [] listKarTypeNames(List <KarType> karTypes){
        
        String [] karTypeNamen = new String[karTypes.size()];
        int counter = 0;
        for(KarType kt : karTypes){
           karTypeNamen[counter] = kt.getType();
           counter ++;
        }
        return karTypeNamen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.type);
        hash = 83 * hash + this.totaalCapaciteit;
        hash = 83 * hash + this.onderCapaciteit;
        hash = 83 * hash + this.bovenCapaciteit;
        hash = 83 * hash + this.volleHoogteCapaciteit;
        hash = 83 * hash + this.aantal;
        hash = 83 * hash + this.maxDikte;
        hash = 83 * hash + this.maxBreedte;
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
        final KarType other = (KarType) obj;
        if (this.totaalCapaciteit != other.totaalCapaciteit) {
            return false;
        }
        if (this.onderCapaciteit != other.onderCapaciteit) {
            return false;
        }
        if (this.bovenCapaciteit != other.bovenCapaciteit) {
            return false;
        }
        if (this.volleHoogteCapaciteit != other.volleHoogteCapaciteit) {
            return false;
        }
        if (this.aantal != other.aantal) {
            return false;
        }
        if (this.maxDikte != other.maxDikte) {
            return false;
        }
        if (this.maxBreedte != other.maxBreedte) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KarType{" + "type=" + type + ", totaalCapaciteit=" + totaalCapaciteit + ", onderCapaciteit=" + onderCapaciteit + ", bovenCapaciteit=" + bovenCapaciteit + ", volleHoogteCapaciteit=" + volleHoogteCapaciteit + ", aantal=" + aantal + ", maxDikte=" + maxDikte + ", maxBreedte=" + maxBreedte + '}';
    }

    
    
    
    
    
}
