/*
 * Entity class die een order voorstelt.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name="orders")
@NamedQueries({@NamedQuery(name = "listOrdersAfgewerkt", query = "SELECT o from Order o where o.afgewerkt = true"),
               @NamedQuery(name = "listOrdersByDatumAndAfgewerkt", query = "SELECT o from Order o where o.afgewerkt = :afgewerkt AND o.leverdatum BETWEEN :datumVan AND :datumTot"),
               @NamedQuery(name = "listOrdersByLeverdatum", query = "SELECT o from Order o where o.leverdatum = :leverdatum"),
               @NamedQuery(name = "listOrdersByDatumAndAfgewerktAndStatus", query = "SELECT o from Order o where o.afgewerkt = :afgewerkt AND o.leverdatum BETWEEN :datumVan AND :datumTot AND o.status = :status")})
public class Order implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    private String orderNr;
    
    @Column
    private LocalDate leverdatum;
    @Column
    private int status;
    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean afgewerkt;
 
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set <OrderLijn> orderlijnen;
    
    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private Set <Kar> karren;
    
    //HISTORIEK_RECEPTIE
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set <HistoriekReceptieLijn> historiekLijnen;
    
    //RECEPTIE GOEDEREN AFWIJKING
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set <ReceptieGoederenAfwijking> recGoedAfwijkingen;
  
    /**
     *
     */
    public Order() {
    }

    /**
     *
     * @return String orderNr
     */
    public String getOrderNr() {
        return orderNr;
    }

    /**
     *
     * @param orderNr String
     */
    public void setOrderNr(String orderNr) {
        this.orderNr = orderNr;
    }

    /**
     *
     * @return LocalDate leverdatum
     */
    public LocalDate getLeverdatum() {
        return leverdatum;
    }

    /**
     *
     * @return String formattedLeverdatum
     */
    public String getFormattedLeverdatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = leverdatum.format(formatter);
       
        return formattedDate;
    }
    
    /**
     *
     * @param leverdatum LocalDate
     */
    public void setLeverdatum(LocalDate leverdatum) {
        this.leverdatum = leverdatum;
    }

    /**
     *
     * @return int status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status int
     */
    public void setStatus(int status) {
        this.status = status;
    }
 
    /**
     *
     * @return Set van orderlijnen
     */
    public Set getOrderlijnen() {
        return orderlijnen;
    }

    /**
     *
     * @param orderlijnen Set van orderlijnen
     */
    public void setOrderlijnen(Set <OrderLijn> orderlijnen) {
        this.orderlijnen = orderlijnen;
    }
    
    /**
     *
     * @param orderlijn OrderLijn
     */
    public void addOrderLijn(OrderLijn orderlijn){
        orderlijnen.add(orderlijn);
        orderlijn.setOrder(this);
    }

    /**
     *
     * @param orderlijn OrderLijn
     */
    public void removeOrderLijn(OrderLijn orderlijn){
        orderlijn.setOrder(null);
        this.orderlijnen.remove(orderlijn);
    }
    
    /**
     *
     * @return boolean afgewerkt
     */
    public boolean isAfgewerkt() {
        return afgewerkt;
    }

    /**
     *
     * @param afgewerkt boolean 
     */
    public void setAfgewerkt(boolean afgewerkt) {
        this.afgewerkt = afgewerkt;
    }

    /**
     *
     * @return Set karren
     */
    public Set<Kar> getKarren() {
        return Collections.unmodifiableSet(karren);
    }

    /**
     *
     * @param karren Set karren
     */
    public void setKarren(Set<Kar> karren) {
        this.karren = karren;
    }
    
    /**
     *
     * @param kar Kar
     */
    public void addKar(Kar kar){
        karren.add(kar);
        if (!kar.getOrders().contains(this)) {
           kar.addOrder(this);
        }
    }
    
    /**
     *
     * @param kar Kar
     */
    public void removeKar(Kar kar){
        karren.remove(kar);
        if (kar.getOrders().contains(this)) {
           kar.removeOrder(this);
        }
    }
    
    /**
     *
     * @return Set HistoriekReceptieLijnen
     */
    public Set<HistoriekReceptieLijn> getHistoriekLijnen() {
        return historiekLijnen;
    }

    /**
     *
     * @param historiekLijnen Set HistoriekReceptieLijnen
     */
    public void setHistoriekLijnen(Set<HistoriekReceptieLijn> historiekLijnen) {
        this.historiekLijnen = historiekLijnen;
    }
    
    /**
     *
     * @param historiekReceptie HistoriekReceptieLijn
     */
    public void addHistoriekLijn(HistoriekReceptieLijn historiekReceptie){
         historiekLijnen.add(historiekReceptie);
         historiekReceptie.setOrder(this);
    }
    
    /**
     *
     * @param historiekLijn HistoriekReceptieLijn
     */
    public void removeHistoriekLijn(HistoriekReceptieLijn historiekLijn){
        historiekLijn.setOrder(null);
        this.historiekLijnen.remove(historiekLijn);
    }
      

    public Set<ReceptieGoederenAfwijking> getRecGoedAfwijkingen() {
        return recGoedAfwijkingen;
    }

    public void setRecGoedAfwijkingen(Set<ReceptieGoederenAfwijking> recGoedAfwijkingen) {
        this.recGoedAfwijkingen = recGoedAfwijkingen;
    }
    
    public void addRecGoedAfwijking(ReceptieGoederenAfwijking recGoedAfwijking){
        recGoedAfwijkingen.add(recGoedAfwijking);
        recGoedAfwijking.setOrder(this);
    }
    
    public void removeRecGoedAfwijking(ReceptieGoederenAfwijking recGoedAfwijking){
        recGoedAfwijking.setOrder(null);
        this.recGoedAfwijkingen.remove(recGoedAfwijking);
    }

    /**
     *
     * @return boolean alle orderlijnen van dit order ontvangen
     */
    public boolean checkAllOrderlijnenOntvangen(){
        int aantalCompleet = 0;
        for(OrderLijn o : this.orderlijnen){
           if(o.isCompleet()){
                aantalCompleet++;
            } 
        }
      
        return orderlijnen.size() == aantalCompleet;
    }
    
    /**
     *
     * @return Map totaal aantal fronten voor normaal en breed
     */
    public Map <String, Integer> getTotaalAantalFronten(){
        Map <String, Integer> aantalFronten = new HashMap<>();
        
        Integer aantalNormaal = 0;
        Integer aantalBreed = 0;
        
        if(this.orderlijnen != null){
            for(OrderLijn ol : this.orderlijnen){
                if(ol.isFront()){
                   int h  = ol.getHoogte();
                   int b = ol.getBreedte(); 
              
                   int grootste = (b > h)?b: h;
                   int kleinste = (b <= h)? h:b;
                   if((kleinste > 200 || (kleinste > 60 && grootste < 1000) || (kleinste < 60 && grootste < 600))){ //IS FRONT EN MAG IN KAR
                        if(b <= 600 || h <=600){ //NORMAAL
                             aantalNormaal += ol.getAantal();
                        }
                        else{
                            aantalBreed += ol.getAantal();
                        }
                   }
                }        
            }
        }
        aantalFronten.put("NORMAAL", aantalNormaal);
        aantalFronten.put("BREED", aantalBreed);
        
        return aantalFronten;      
    }
     
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.orderNr);
        hash = 47 * hash + Objects.hashCode(this.leverdatum);
        hash = 47 * hash + this.status;
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
        final Order other = (Order) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.orderNr, other.orderNr)) {
            return false;
        }
        return Objects.equals(this.leverdatum, other.leverdatum);
    }

    @Override
    public String toString() {
        return "Order{" + "orderNr=" + orderNr + ", leverdatum=" + leverdatum + ", status=" + status + ", afgewerkt=" + afgewerkt + '}';
    }

    
    
    
}
