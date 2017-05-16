/*
 * LanceringNr (OrderLijn) Entity class.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author olivier deleye
 */
@Entity
@Table(name ="lanceringnrs")
@NamedQueries( 
   { @NamedQuery(name = "listOrderLijnenByCompleet", query = "SELECT ol from OrderLijn ol where ol.compleet = :compleet"),
       @NamedQuery(name = "listOrderLijnenByCriteria", query = "SELECT ol from OrderLijn ol where ol.compleet = :compleet OR ol.isFront = :isFront"),
        @NamedQuery(name = "listOrderLijnenByOrderNr", query = "SELECT ol from OrderLijn ol where ol.order.orderNr = :orderNr")
   }
)

public class OrderLijn implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private int lanceringNr;
     
    @Column
    private LocalDate leverdatum;
    @Column
    private String kleur;
    @Column
    private int hoogte;
    @Column
    private int breedte;
    @Column
    private int dikte;
    @Column
    private String omschrijving;
    @Column
    private String draairichting;
    @Column
    private int aantal;
    @Column
    private int ontvangenAantal;
    @Column
    private boolean compleet;
    @Column
    private String variantNr;
    @Column
    private boolean isFront;
    @Column
    private String kleurLev;
    @Column
    private String artNrLev;
    
    //FOREIGN KEY ORDER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="fk_orderNr")
    private Order order;
   
    //FOREIGN KEY LEVERANCIER
    @ManyToOne
    @JoinColumn(name="fk_leverancierNr")
    private Leverancier leverancier;
     
    //FOREIGN KEY ARTIKELTYPE
    @ManyToOne
    @JoinColumn(name = "fk_artNr")
    private ArtikelType artikelType;
    
    @ManyToMany(mappedBy = "orderlijnen", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set <Barcode> barcodes;

    /**
     *
     */
    public OrderLijn() {
    }

    /**
     *
     * @return
     */
    public int getLanceringNr() {
        return lanceringNr;
    }

    /**
     *
     * @param lanceringNr int
     */
    public void setLanceringNr(int lanceringNr) {
        this.lanceringNr = lanceringNr;
    }

    /**
     *
     * @return int aantal
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
     * @return int ontvangenAantal
     */
    public int getOntvangenAantal(){
        return ontvangenAantal;
    }
    
    /**
     *
     * @param ontvangenAantal int
     */
    public void setOntvangenAantal(int ontvangenAantal){
        this.ontvangenAantal = ontvangenAantal;
    }
    
    /**
     *
     * @return boolean compleet
     */
    public boolean isCompleet() {
        return compleet;
    }

    /**
     *
     * @param compleet boolean
     */
    public void setCompleet(boolean compleet) {
        this.compleet = compleet;
    }

    /**
     *
     * @return String variantNr
     */
    public String getVariantNr() {
        return variantNr;
    }

    /**
     *
     * @param variantNr String variantNr
     */
    public void setVariantNr(String variantNr) {
        this.variantNr = variantNr;
    }
   
    /**
     *
     * @return boolean isFront
     */
    public boolean isFront() {
        return isFront;
    }

    /**
     *
     * @param isFront boolean isFront
     */
    public void setIsFront(boolean isFront) {
        this.isFront = isFront;
    }
    
    /**
     *
     * @return Order 
     */
    public Order getOrder() {
        return order;
    }

    /**
     *
     * @param order Order
     */
    public void setOrder(Order order) {
        this.order = order;
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
     * @param leverdatum LocalDate leverdatum
     */
    public void setLeverdatum(LocalDate leverdatum) {
        this.leverdatum = leverdatum;
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
     * @return String kleur
     */
    public String getKleur() {
        return kleur;
    }

    /**
     *
     * @param kleur Stirng kleur
     */
    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    /**
     *
     * @return int hoogte
     */
    public int getHoogte() {
        return hoogte;
    }

    /**
     *
     * @param hoogte int hoogte
     */
    public void setHoogte(int hoogte) {
        this.hoogte = hoogte;
    }

    /**
     *
     * @return int breedte
     */
    public int getBreedte() {
        return breedte;
    }

    /**
     *
     * @param breedte int breedte
     */
    public void setBreedte(int breedte) {
        this.breedte = breedte;
    }

    /**
     *
     * @return int dikte
     */
    public int getDikte() {
        return dikte;
    }

    /**
     *
     * @param dikte int dikte
     */
    public void setDikte(int dikte) {
        this.dikte = dikte;
    }

    /**
     *
     * @return String draairichting
     */
    public String getDraairichting() {
        return draairichting;
    }

    /**
     *
     * @param draairichting String
     */
    public void setDraairichting(String draairichting) {
        this.draairichting = draairichting;
    }
    
    /**
     *
     * @return String omschrijving
     */
    public String getOmschrijving() {
        return omschrijving;
    }

    /**
     *
     * @param omschrijving String
     */
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    /**
     *
     * @return String kleurLeverancier
     */
    public String getKleurLev() {
        return kleurLev;
    }

    /**
     *
     * @param kleurLev String
     */
    public void setKleurLev(String kleurLev) {
        this.kleurLev = kleurLev;
    }

    /**
     *
     * @return String artNrLev
     */
    public String getArtNrLev() {
        return artNrLev;
    }

    /**
     *
     * @param artNrLev String
     */
    public void setArtNrLev(String artNrLev) {
        this.artNrLev = artNrLev;
    }
    
    /**
     *
     * @return Leverancier
     */
    public Leverancier getLeverancier() {
        return leverancier;
    }

    /**
     *
     * @param leverancier Leverancier
     */
    public void setLeverancier(Leverancier leverancier) {
        this.leverancier = leverancier;
    }

    /**
     *
     * @return ArtikelType
     */
    public ArtikelType getArtikelType() {
        return artikelType;
    }

    /**
     *
     * @param artikelType ArtikelType
     */
    public void setArtikelType(ArtikelType artikelType) {
        this.artikelType = artikelType;
    }

    /**
     *
     * @return Set Barcodes
     */
    public Set<Barcode> getBarcodes() {
        return barcodes;
    }

    /**
     *
     * @param barcodes Set barcodes
     */
    public void setBarcodes(Set<Barcode> barcodes) {
        this.barcodes = barcodes;
    }
    
    /**
     *
     * @param barcode Barcode
     */
    public void addBarcode(Barcode barcode){
        barcodes.add(barcode);
        if (!barcode.getOrderlijnen().contains(this)) {
           barcode.addOrderlijn(this);
        }
    }
    
    /**
     *
     * @param barcode Barcode
     */
    public void removeBarcode(Barcode barcode){
        barcodes.remove(barcode);
        if (barcode.getOrderlijnen().contains(this)) {
           barcode.removeOrderlijn(this);
        }
    }
    
    /**
     *
     * @return boolean 
     */
    public boolean checkOrderLijnIsCompleet(){
        return aantal == ontvangenAantal;
    }

  
     /*Comparator voor sortering op orderNr*/
    public static Comparator<OrderLijn> orderNrComperator = new Comparator<OrderLijn>() {

        @Override
	public int compare(OrderLijn ol1, OrderLijn ol2) {
	   String orderNr1 = ol1.getOrder().getOrderNr().toUpperCase();
	   String orderNr2 = ol2.getOrder().getOrderNr().toUpperCase();

	   //ascending order
	   return orderNr1.compareTo(orderNr2);

	   //descending order
	   //return orderNr2.compareTo(orderNr1);;
        }
    };
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.lanceringNr;
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
        final OrderLijn other = (OrderLijn) obj;
        if (this.lanceringNr != other.lanceringNr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderLijn{" + "lanceringNr=" + lanceringNr + ", leverdatum=" + leverdatum + ", kleur=" + kleur + ", hoogte=" + hoogte + ", breedte=" + breedte + ", dikte=" + dikte + ", omschrijving=" + omschrijving + ", draairichting=" + draairichting + ", aantal=" + aantal + ", ontvangenAantal=" + ontvangenAantal + ", compleet=" + compleet + ", isFront=" + isFront + ", kleurLev=" + kleurLev + ", artNrLev=" + artNrLev + '}';
    }
    
    
    
   

    

    
    
    

   

    
    
    
    
    
}
