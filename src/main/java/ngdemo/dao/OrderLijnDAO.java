/*
 * Persistence class LanceringNr (OrderLijn).
 */
package ngdemo.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.ArtikelType;
import ngdemo.domain.Barcode;
import ngdemo.domain.Kar;
import ngdemo.domain.Leverancier;
import ngdemo.domain.Locatie;
import ngdemo.domain.Order;
import ngdemo.domain.OrderLijn;
import ngdemo.util.PersistenceManager;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author olivier deleye
 */

public class OrderLijnDAO {
    
    /**
     *
     */
    public OrderLijnDAO() {
    }
   
        /*
    CRUD operations here
    */

    /**
     * Create new lanceringNr
     * @param planningNr
     * @param barcode
     * @param leverdatum
     * @param kleur
     * @param hoogte
     * @param breedte
     * @param dikte
     * @param omschrijving
     * @param aantal
     * @param draairichting
     * @param kleurLev
     * @param artNrLev
     * @param artikelType
     * @param leverancier
     * @param order
     * @return 
     */
    public OrderLijn createOrderLijn(int planningNr, String barcode, LocalDate leverdatum, String kleur, int hoogte, int breedte, int dikte, String omschrijving, 
                                      int aantal, String draairichting, String kleurLev, String artNrLev, ArtikelType artikelType, Leverancier leverancier, Order order){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            OrderLijn orderlijn = new OrderLijn();
         
            em.getTransaction().begin();
            orderlijn.setLanceringNr(planningNr);

            orderlijn.setLeverdatum(leverdatum);
            orderlijn.setKleur(kleur);
            orderlijn.setHoogte(hoogte);
            orderlijn.setBreedte(breedte);
            orderlijn.setDikte(dikte);
            orderlijn.setOmschrijving(omschrijving);
            orderlijn.setAantal(aantal); 
            orderlijn.setOntvangenAantal(0);
            orderlijn.setCompleet(false);
            orderlijn.setDraairichting(draairichting);
            orderlijn.setKleurLev(kleurLev);
            orderlijn.setArtNrLev(artNrLev);
            //orderlijn.setArtikelType(artikelType);
            orderlijn.setLeverancier(leverancier);
            orderlijn.setOrder(order);

            em.persist(orderlijn);
            em.getTransaction().commit();
         
            return orderlijn; 
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * Update lanceringNr
     * @param planningNr
     * @param barcode
     * @param leverdatum
     * @param kleur
     * @param hoogte
     * @param breedte
     * @param dikte
     * @param omschrijving
     * @param aantal
     * @param ontvangenAantal
     * @param draairichting
     * @param kleurLev
     * @param artNrLev
     * @param artikelType
     * @param leverancier
     * @param order
     * @return 
     */
    public OrderLijn updateOrderLijn(int planningNr, String barcode, LocalDate leverdatum, String kleur, int hoogte, int breedte, int dikte, String omschrijving, 
                                      int aantal, int ontvangenAantal, String draairichting, String kleurLev, String artNrLev, 
                                        ArtikelType artikelType, Leverancier leverancier, Order order){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            OrderLijn orderlijn = em.find(OrderLijn.class, planningNr);
            if(orderlijn != null){

                em.getTransaction().begin();
                orderlijn.setLeverdatum(leverdatum);
                orderlijn.setKleur(kleur);
                orderlijn.setHoogte(hoogte);
                orderlijn.setBreedte(breedte);
                orderlijn.setDikte(dikte);
                orderlijn.setOmschrijving(omschrijving);
                orderlijn.setAantal(aantal); 
                orderlijn.setOntvangenAantal(ontvangenAantal);
                if(orderlijn.getOntvangenAantal() >= orderlijn.getAantal()){
                    orderlijn.setCompleet(true);
                }else{
                    orderlijn.setCompleet(false);
                }
                orderlijn.setDraairichting(draairichting);
                orderlijn.setKleurLev(kleurLev);
                orderlijn.setArtNrLev(artNrLev);
                //orderlijn.setArtikelType(artikelType);
                orderlijn.setLeverancier(leverancier);
                orderlijn.setOrder(order);

                em.getTransaction().commit();
               
            }     
            return orderlijn; 
        }
        finally{
            em.close();
            
        }
    }
    
    /**
     * 
     * @param lanceringNr int
     * @param leverdatum LocalDate
     * @param aantal int
     * @return updated orderlijn OrderLijn
     */
    public OrderLijn updateLeverdatumAndAantalOrderLijn(int lanceringNr, LocalDate leverdatum, int aantal){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            OrderLijn orderlijn = em.find(OrderLijn.class, lanceringNr);
            if(orderlijn != null){

                em.getTransaction().begin();
                orderlijn.setLeverdatum(leverdatum);
                orderlijn.setAantal(aantal);    
                em.getTransaction().commit();           
            }     
            return orderlijn; 
        }
        finally{
            em.close();
            
        }
    }
    
    /**
     * Find lanceringNr by lanceringNr
     * @param lanceringNr
     * @return 
     */
    public OrderLijn findOrderLijn(int lanceringNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            OrderLijn ol;
            ol = em.find(OrderLijn.class, lanceringNr);
            return ol;
        }
        finally{
            em.close();
          
        } 
    }
    
    /**
     * Update barcode/lanceringNr
     * @param lanceringNr
     * @param barcode 
     */
    public void updateBarcodeOrderlijn(int lanceringNr, String barcode){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Barcode bc = new Barcode(barcode);
            OrderLijn ol;
            ol = em.find(OrderLijn.class, lanceringNr);    
            if(ol != null){
                em.getTransaction().begin();
                ol.addBarcode(bc);
                em.getTransaction().commit();
              
            } 
        }
        finally{
            em.close();
         
        }       
    }
    
     // HIER DIENT VEEL TE GEBEUREN -> KAR UPDATEN, ORDERLIJN EVT OP ONTVANGEN ZETTEN, CHECK OF ORDER NIET OP AFGEWERKT MAG GEZET WORDEN.
     /**
      * Opslaan/update lanceringNr na scannen van barcode -> kar update, set compleet, check order afgewerkt
      * @param barcode String
      * @param lanceringNr int
      * @param karNr String
      * @param locatieString String
      * @return lanceringNr na update OrderLijn
      */
     public OrderLijn storeOrderLijn(String barcode, int lanceringNr, String karNr, String locatieString){ 
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            em.getTransaction().begin();
            
            Kar kar = null;
            Locatie locatie = null;
            
            if(karNr != null){
                kar = em.find(Kar.class, karNr);      
                kar.setVrijePlaatsen(kar.getVrijePlaatsen() - 1);
                kar.setIsVrij(false);
            }
            if(locatieString != null){
                locatie = em.find(Locatie.class, locatieString);
            }

            Barcode bc;
            bc = em.find(Barcode.class, barcode);
            if(bc != null){
               bc.setOntvangen(true);
               if(kar != null){
                   bc.setKar(kar);
               }
               else{
                   if(locatie != null){
                       bc.setLocatie(locatie);
                   }
               }
            }
            OrderLijn ol;
            ol = em.find(OrderLijn.class, lanceringNr);  

            if(ol != null){
                int aantalOntvangen = 0;
                Set <Barcode> barcodes = ol.getBarcodes();
                if(barcodes != null && !barcodes.isEmpty()){
                    for(Barcode b : barcodes){
                        if(b.isOntvangen()){
                            aantalOntvangen++;
                        }
                    }
                }   
                ol.setOntvangenAantal(aantalOntvangen);
                if(ol.getOntvangenAantal() >= ol.getAantal()){
                    ol.setCompleet(true);
                }
                ol.getOrder().setAfgewerkt(ol.getOrder().checkAllOrderlijnenOntvangen());
                if(kar != null){
                    ol.getOrder().addKar(kar);
                }             
            }
            em.getTransaction().commit();
            
            return ol;
        }
        finally{
            em.close();
           
        }
    }
     
    /**
     * Verwijder een lanceringNr uit een kar
     * @param lanceringNr
     * @param barcode
     * @param karNr 
     */
    public void deleteFromKar(int lanceringNr, String barcode, String karNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Kar kar;
            kar = em.find(Kar.class, karNr);      
            kar.setVrijePlaatsen(kar.getVrijePlaatsen() + 1);

            Barcode bc;
            bc = em.find(Barcode.class, barcode);
            if(bc != null){
                bc.setOntvangen(false);
            }

            OrderLijn ol;
            ol = em.find(OrderLijn.class, lanceringNr);  

            if(ol != null){
                em.getTransaction().begin();
                int ontvangenAantal = ol.getOntvangenAantal() -1;
                ol.setOntvangenAantal(ontvangenAantal);
                ol.setCompleet(ol.checkOrderLijnIsCompleet());               
                ol.getOrder().setAfgewerkt(ol.getOrder().checkAllOrderlijnenOntvangen());
                em.getTransaction().commit();
                
            }
        }
        finally{
            em.close();
           
        }    
    }
    
    /**
     * Lijst van alle lanceringNrs
     * @return alle lanceringsNrs
     */
    public List <OrderLijn> listAllOrderlijnen(){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Query query = em.createQuery("SELECT ol FROM OrderLijn ol");
            List <OrderLijn> ol = query.getResultList();
            return ol; 
        }
        finally{
            em.close();
         
        }      
    }
    
    /**
     * Lijst van lanceringNrs volgens orderNr
     * @param orderNr
     * @return alle lanceringNrs van een order
     */
    public List <OrderLijn> listOrderLijnenByOrderNr(String orderNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try{
            Query query = em.createNamedQuery("listOrderLijnenByOrderNr");
            query.setParameter("orderNr", orderNr);
            List <OrderLijn> ol = query.getResultList();
            return ol;  
        }
        finally{
            em.close();
        }
            
    }
    
    /**
     * Lijst van alle complete of incomplete lanceringNrs
     * @param compleet boolean
     * @return List 
     */
    public List <OrderLijn> listOrderLijnenByCompleet(boolean compleet){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Query query = em.createQuery("SELECT ol FROM OrderLijn ol where ol.compleet = :compleet", OrderLijn.class);
            query.setParameter("compleet", compleet);
            List <OrderLijn> ol = query.getResultList();
            return ol; 
        }
        finally{
            em.close();
           
        }       
    }
    
    /**
     * Lijst van lanceringNrs volgens OrderNr of artikelNr leverancier ,kleur leverancier, hoogte, breedte
     * @param orderNr String
     * @param draairichting String
     * @param artNrLev String
     * @param kleurLev String
     * @param hoogte int
     * @param breedte int 
     * @return alle lanceringNrs die aan criteria voldoen
     */
    public List <OrderLijn> findOrderLijnByCriteria(String orderNr, String draairichting, String artNrLev, String kleurLev, int hoogte, int breedte){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Query query = em.createQuery("SELECT ol from OrderLijn ol where ol.order.orderNr LIKE CONCAT('%',:orderNr) AND ol.draairichting = :draairichting AND ol.artNrLev = :artNrLev "
                    + "AND ol.kleurLev = :kleurLev AND ol.hoogte = :hoogte AND ol.breedte = :breedte");
             
            query.setParameter("orderNr", orderNr);
            query.setParameter("draairichting", draairichting);
            query.setParameter("artNrLev", artNrLev);
            query.setParameter("kleurLev", kleurLev);
            query.setParameter("hoogte", hoogte);
            query.setParameter("breedte", breedte);
            List <OrderLijn> ol = query.getResultList();
            return ol;
        }
        finally{
            em.close();
        }  
    }
    
    /**
     * Find Groep by groepNaam
     * @param groepNaam String
     * @return Groep 
     */
    public List<OrderLijn> listOrderlijnenByGroepNaam(String groepNaam){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           List <OrderLijn> orderlijnen;
           Query q = em.createQuery("SELECT o FROM OrderLijn o WHERE o.leverancier.groep.groepNaam = :groepNaam", OrderLijn.class);
           q.setParameter("groepNaam", groepNaam);
           orderlijnen = q.getResultList();
           return orderlijnen;
        }
        finally{
            em.close();
           
        }
    }
    /**
     * Lijst van lanceringNrs volgens zoekfilter criteria
     * @param compleet
     * @param isFront
     * @return alle lanceringNrs volgens zoekfilter criteria
     */
    public List <OrderLijn> listOrderLijnenByCriteria(boolean compleet, boolean isFront){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Query query = em.createQuery("SELECT ol FROM OrderLijn ol WHERE ol.compleet= :compleet AND ol.isFront = :isFront", OrderLijn.class);
            query.setParameter("compleet", compleet);
            query.setParameter("isFront", isFront);
            List <OrderLijn> ol = query.getResultList();
            return ol;
        }
        finally{
            em.close();
            
        }    
    }
    
    /**
     * Find lanceringNr by variantNr en orderNr
     * @param variantNr
     * @param orderNr
     * @return lanceringNr
     */
    public List <OrderLijn> findOrderLijnenByVariantNrAndOrderNr (String variantNr, String orderNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
          
           List <OrderLijn> results;
           Query query = em.createQuery("SELECT ol FROM OrderLijn ol WHERE ol.variantNr = :variantNr AND ol.order.orderNr = :orderNr", OrderLijn.class);
           query.setParameter("variantNr", variantNr);
           query.setParameter("orderNr", orderNr);
           
           results = query.getResultList();
           return results;
        }
        finally{
            em.close();
            
        } 
    }
    
    /**
     * Find lanceringNr by Vika variantNr
     * @param variantNr
     * @return lanceringNr
     */
    public OrderLijn findOrderLijnByVikaVariantNr (String variantNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
          
           List <OrderLijn> results;
           Query query = em.createQuery("SELECT ol FROM OrderLijn ol WHERE ol.variantNr = :variantNr", OrderLijn.class);
           query.setParameter("variantNr", variantNr);
   
           
           results = query.getResultList();
           return CollectionUtils.isEmpty(results ) ? null : results.get(0);
        }
        finally{
            em.close();
            
        } 
    }
}

   