/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.util.Comparator;
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
@Table(name="karren")
@NamedQueries( 
   { @NamedQuery(name = "listLegeKarrenByType", query = "SELECT k from Kar k WHERE k.karType = :type AND k.isVrij = true"),
       @NamedQuery(name = "listKarrenByType", query = "SELECT k from Kar k WHERE k.karType = :type"),
        @NamedQuery(name = "verhoogVrijPlaatsen", query = "UPDATE Kar k SET k.vrijePlaatsen = :vrijeplaatsen WHERE k.karType = :type")
    }
)
public class Kar implements Serializable{
     
    private static final long serialVersionUID = 1L;
    
    @Id
    private String karNr;
    @Column
    private int vrijePlaatsen;
    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isVrij;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="fk_karType")
    private KarType karType;
  
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_karren",  joinColumns = @JoinColumn(name = "fk_karNr"), 
                                inverseJoinColumns = @JoinColumn(name = "fk_orderNr")) 
    private Set <Order> orders;
    
    @OneToMany(mappedBy = "kar", fetch = FetchType.LAZY)
    private Set <Barcode> barcodes;
     
    /**
     *
     */
    public Kar(){
    }

    /**
     *
     * @return
     */
    public String getKarNr() {
        return karNr;
    }

    /**
     *
     * @param karNr
     */
    public void setKarNr(String karNr) {
        this.karNr = karNr;
    }

    /**
     *
     * @return
     */
    public KarType getKarType() {
        return karType;
    }

    /**
     *
     * @param karType
     */
    public void setKarType(KarType karType) {
        this.karType = karType;
    }

    /**
     *
     * @return
     */
    public int getVrijePlaatsen() {
        return vrijePlaatsen;
    }

    /**
     *
     * @param vrijePlaatsen
     */
    public void setVrijePlaatsen(int vrijePlaatsen) {
        this.vrijePlaatsen = vrijePlaatsen;
    }

    /**
     *
     * @return
     */
    public boolean isIsVrij() {
        return isVrij;
    }

    /**
     *
     * @param isVrij
     */
    public void setIsVrij(boolean isVrij) {
        this.isVrij = isVrij;
    }

    /**
     *
     * @return
     */
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     *
     * @param orders
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
    /**
     *
     * @param order
     */
    public void addOrder(Order order){     
        orders.add(order);
        if (!order.getKarren().contains(this)) {
           order.addKar(this);
        }
    }
    
    /**
     *
     * @param order
     */
    public void removeOrder(Order order){
        orders.remove(order);
         if (order.getKarren().contains(this)) {
           order.removeKar(this);
        }
    }
    
    /**
     *
     * @return
     */
    public Set getBarcodes() {
        return barcodes;
    }

    /**
     *
     * @param barcodes
     */
    public void setBarcodes(Set <Barcode> barcodes) {
        this.barcodes = barcodes;
    }
    
    /**
     *
     * @param barcode
     */
    public void addBarcode(Barcode barcode){
        barcodes.add(barcode);
        barcode.setKar(this);
    }

    /**
     *
     * @param barcode
     */
    public void removeBarcode(Barcode barcode){
        barcode.setKar(null);
        this.barcodes.remove(barcode);
    }
    /**
     * Berekenen totaal aantal artikels per order die reeds in kar zit.
     * @param karType "NORMAAL of "BREED"
     * @return 
     */
    public int getTotaalGereserveerdePlaatsen(String karType){
        int totaal = 0;
        if(this.orders != null && !this.orders.isEmpty()){
            for(Order o : this.orders){
                totaal +=  o.getTotaalAantalFronten().get(karType);
            }
        }
        return totaal;
    }
    
    /**
     * 
     * @param karType "NORMAAL of "BREED"
     * @return aantal fronten die in kar zitten
     */
    public int getAantalFronten(String karType){
        int aantal = 0;
        for(Order o : orders){
            aantal += o.getTotaalAantalFronten().get(karType);
        }
        return aantal;
    }
    
    /*Comparator voor sorteren karren op meest gevuld*/

    /**
     *
     */

    public static Comparator<Kar> karVrijePlaatsenComperator = new Comparator<Kar>() {

        @Override
	public int compare(Kar k1, Kar k2) {

	   int vrijePlaatsen1 = k1.getVrijePlaatsen();
	   int vrijePlaatsen2 = k2.getVrijePlaatsen();

	   /*DESC*/
	   return vrijePlaatsen1-vrijePlaatsen2;

	   /*ASC*/
	   //vrijePlaatsen2-vrijePlaatsen1;
        }
    };
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.karType);
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
        final Kar other = (Kar) obj;
        if (!this.karNr.equals(other.karNr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kar{" + "karNr=" + karNr + ", vrijePlaatsen=" + vrijePlaatsen + ", isVrij=" + isVrij + ", karType=" + karType + '}';
    }


//    @Override
//    public int compareTo(Kar karToComp) {
//        
//       int aantalGereserveerd =(karToComp.getTotaalGereserveerdePlaatsen());
//        /* DESC order*/
//        return aantalGereserveerd - this.getTotaalGereserveerdePlaatsen();
//        /* For Ascending order*/
//        //return this.getTotaalGereserveerdePlaatsen() - aantalGereserveerd;
//       
//    }

    

    
    
}
    
    
    
