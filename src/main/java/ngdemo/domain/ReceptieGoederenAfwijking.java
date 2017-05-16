/*
 * Entity die een record representeerd uit table rec_goed_afwijkingen.
 */
package ngdemo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name= "rec_goed_afwijkingen")
public class ReceptieGoederenAfwijking implements Serializable{
    
    public ReceptieGoederenAfwijking(){ 
        
    }
    private static final long serialVersionUID = 1L;
  
    
    public enum TypeAfwijking{
        ONTVANGENNIETVERWACHT, VERWACHTNIETONTVANGEN, TEVEELONTVANGEN
    }
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate receptieDatum; 
    @Column
    @Enumerated(EnumType.STRING)
    private TypeAfwijking typeAfwijking;
    @Column
    private int aantalVerwacht;

    @Column
    private int aantalOntvangen;
    
    @Column
    private String variantNr;
    
    @Column
    private int printVersie;
    
    @ManyToOne
    @JoinColumn(name = "fk_orderNr")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReceptieDatum() {
        return receptieDatum;
    }

    public void setReceptieDatum(LocalDate receptieDatum) {
        this.receptieDatum = receptieDatum;
    }
  
    public String getFormattedReceptieDatum(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = receptieDatum.format(formatter);
       
        return formattedDate;
    }

    public TypeAfwijking getTypeAfwijking() {
        return typeAfwijking;
    }

    public void setTypeAfwijking(TypeAfwijking typeAfwijking) {
        this.typeAfwijking = typeAfwijking;
    }
    
    
    public int getAantalVerwacht() {
        return aantalVerwacht;
    }

    public void setAantalVerwacht(int aantalVerwacht) {
        this.aantalVerwacht = aantalVerwacht;
    }

    public int getAantalOntvangen() {
        return aantalOntvangen;
    }

    public void setAantalOntvangen(int aantalOntvangen) {
        this.aantalOntvangen = aantalOntvangen;
    }

    public String getVariantNr() {
        return variantNr;
    }

    public void setVariantNr(String variantNr) {
        this.variantNr = variantNr;
    }

    
    public int getPrintVersie() {
        return printVersie;
    }

    public void setPrintVersie(int printVersie) {
        this.printVersie = printVersie;
    }
    
    

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

     /*Comparator voor sortering op printVersie*/
    public static Comparator <ReceptieGoederenAfwijking> printversieComperator = new Comparator <ReceptieGoederenAfwijking>() {
        @Override
        public int compare(ReceptieGoederenAfwijking r1, ReceptieGoederenAfwijking r2) {
           Integer printVersie1 = r1.getPrintVersie();
	   Integer printVersie2 = r2.getPrintVersie();

	   //ascending order
	   return printVersie1.compareTo(printVersie2);

	   //descending order
	   //return printVersie2.compareTo(printVersie1);
        }
    };
    
     /*Comparator voor sortering op orderNr*/
    public static Comparator<ReceptieGoederenAfwijking> orderNrComperator = new Comparator<ReceptieGoederenAfwijking>() {

        @Override
	public int compare(ReceptieGoederenAfwijking rga1, ReceptieGoederenAfwijking rga2) {
	   String orderNr1 = rga1.getOrder().getOrderNr().toUpperCase();
	   String orderNr2 = rga2.getOrder().getOrderNr().toUpperCase();

	   //ascending order
	   return orderNr1.compareTo(orderNr2);

	   //descending order
	   //return orderNr2.compareTo(orderNr1);;
        }
    };
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.receptieDatum);
        hash = 37 * hash + this.aantalVerwacht;
        hash = 37 * hash + this.aantalOntvangen;
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
        final ReceptieGoederenAfwijking other = (ReceptieGoederenAfwijking) obj;
    
        if (this.aantalVerwacht != other.aantalVerwacht) {
            return false;
        }
        if (this.aantalOntvangen != other.aantalOntvangen) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.receptieDatum, other.receptieDatum)) {
            return false;
        }
    
        return true;
    }

    @Override
    public String toString() {
        return "ReceptieGoederenAfwijking{" + "id=" + id + ", receptieDatum=" + receptieDatum + ", typeAfwijking=" + typeAfwijking + ", aantalVerwacht=" + aantalVerwacht + ", aantalOntvangen=" + aantalOntvangen + ", variantNr=" + variantNr + ", printVersie=" + printVersie + ", orderNr=" + order.getOrderNr() + '}';
    }

    

    

    

    
    
}
