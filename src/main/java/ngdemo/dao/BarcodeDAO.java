/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.dao;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.Barcode;
import ngdemo.domain.Kar;
import ngdemo.domain.Order;
import ngdemo.domain.OrderLijn;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */
public class BarcodeDAO {
   
    /**
     *
     */
    public BarcodeDAO() {
       
    }
    
    /**
     *
     * @param barcode
     * @return
     */
    public Barcode createBarcode(Barcode barcode, boolean gekoppeld){
        
       EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Barcode bc = barcode;
            em.getTransaction().begin();
            bc.setGekoppeld(gekoppeld);
            bc.setOntvangen(false);
            em.persist(bc);
            em.getTransaction().commit();
            return bc; 
        }
        finally{
            em.close();
       
        }
    }
    
    /**
     *
     * @param barcode
     * @return
     */
    public Barcode findBarcode(String barcode){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try{
           Barcode bc = em.find(Barcode.class, barcode);
           return bc; 
        }
        finally{
            em.close();
            
        }   
    }
    
    /**
     *
     * @param barcode
     * @param planningNr
     */
    public void updateBarcode(String barcode, int planningNr){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try{
            Barcode bc = em.find(Barcode.class, barcode);
            OrderLijn ol = em.find(OrderLijn.class, planningNr);
            em.getTransaction().begin();
            if(bc != null){
                bc.addOrderlijn(ol);
            }
            em.getTransaction().commit();
        }
        finally{
           em.close();
          
        }     
    }
    
     /**
     * Stockeren van niet gekoppelde barcodes in MANCO KAR
     * @param barcode
     */
    public void storeInManco(String barcode){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try{
            Barcode bc = em.find(Barcode.class, barcode);
            Kar kar = new Kar();
            kar.setKarNr("FKM01");
            bc.setKar(kar);
            em.getTransaction().commit();
        }
        finally{
           em.close();
          
        }     
    }
    
    /**
     * Lijst van alle barcodes
     * @return List
     */
    public List <Barcode> listAlleBarcodes(){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            List <Barcode> barcodes;
            Query query = em.createQuery("SELECT b FROM Barcode b", Barcode.class);
            barcodes = query.getResultList();
            return barcodes; 
        }
        finally{
          em.close();
         
        }
        
    }
    
    /**
     *
     * @param ontvangen boolean
     * @return List
     */
    public List <Barcode> listBarcodesOntvangen(boolean ontvangen){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            List <Barcode> barcodes;
            Query query = em.createNamedQuery("listBarcodesOntvangen");
            query.setParameter("ontvangen", ontvangen);
            barcodes = query.getResultList();
            return barcodes; 
        }
        finally{
          em.close();
         
        }
        
    }
    
    /**
     * Verplaats barcode van kar A naar kar B
     */
    public void verplaatsBarcode(String barcode, String orderNr, String vanKarNr , String naarKarNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            em.getTransaction().begin();
            Barcode bc = em.find(Barcode.class, barcode);
            Kar vanKar = em.find(Kar.class, vanKarNr);
            Kar naarKar = em.find(Kar.class, naarKarNr);
            
            //Aantal fronten van dit order die in vanKar zitten - als slechts 1 front betreft wordt relatie Order/Kar verwijderd.
            int countFronten = 0;
            
            if(bc != null && vanKar != null && naarKar != null){
                Order o = em.find(Order.class, orderNr);
                Set <OrderLijn> oLijnen = o.getOrderlijnen();
                for(OrderLijn oLijn : oLijnen){
                    Set <Barcode> barcodes = oLijn.getBarcodes();
                    for(Barcode b : barcodes){
                        if(!b.equals(bc) && b.getKar() != null){
                            if(b.getKar().equals(vanKar)){
                               countFronten++;
                            }                     
                        }
                    }
                }
                if(countFronten == 0){ // vanKar had slechts 1 front van dit Order en relatie Order/Kar wordt verwijderd.
                    o.removeKar(vanKar);
                }
                bc.setKar(naarKar);
                o.addKar(naarKar);
            }
            em.getTransaction().commit();
        } 
        finally{
            em.close();
        }
    }
}
