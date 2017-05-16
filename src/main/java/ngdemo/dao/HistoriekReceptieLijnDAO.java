/*
 * Persistence class HistoriekReceptieLijn.
 */
package ngdemo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.HistoriekReceptieLijn;
import ngdemo.domain.Order;
import ngdemo.domain.OrderLijn;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */
public class HistoriekReceptieLijnDAO {
    
    /**
     *
     */
    public HistoriekReceptieLijnDAO(){  
    }
    
    /**
     * Create HistoriekReceptieLijn
     * @param receptieDatum LocalDate
     * @param aantalVerwacht int aantal verwacht voor dit order en deze variant
     * @param variantNr String variantNr
     * @param order 
     * @return created HistoriekReceptieLijn
     */
    public HistoriekReceptieLijn createHistoriekReceptieLijn (LocalDate receptieDatum, int aantalVerwacht, String variantNr, Order order){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try {
            em.getTransaction().begin();
        
            HistoriekReceptieLijn recLijn = new HistoriekReceptieLijn();
            recLijn.setReceptieDatum(receptieDatum);
            recLijn.setAantalVerwacht(aantalVerwacht);
            recLijn.setVariantNr(variantNr);     
            recLijn.setOrder(order);
            em.persist(recLijn);
            em.getTransaction().commit();
            return recLijn;
        }
        finally{
            em.close();       
        }
    }
    
    /**
     * Find by id
     * @param id
     * @return HistoriekReceptieLijn
     */
    public HistoriekReceptieLijn findHistoriekReceptieLijn (Long id){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try {
           HistoriekReceptieLijn recLijn;
           recLijn = em.find(HistoriekReceptieLijn.class, id);
           return recLijn;
        }
        finally{
            em.close();       
        }
    }
    
    /**
     * Update HistoriekReceptieLijn
     * @param id Long
     * @param receptieDatum LocalDate
     * @param aantalVerwacht int
     * @param orderlijn OrderLijn
     */
    public void updateHistoriekReceptieLijn (Long id, LocalDate receptieDatum, int aantalVerwacht, Order order){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try {
            em.getTransaction().begin();
            HistoriekReceptieLijn recLijn = em.find(HistoriekReceptieLijn.class, id);
            if(recLijn != null){
                recLijn.setReceptieDatum(receptieDatum);
                recLijn.setAantalVerwacht(aantalVerwacht);
                recLijn.setOrder(order);
                em.getTransaction().commit();
            }    
        }
        finally{
            em.close();       
        }
    }
    
    /**
     * Delete HistoriekReceptieLijn
     * @param id 
     */
    public void deleteHistoriekReceptieLijn(Long id){
         EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
         
         try {
            em.getTransaction().begin();
            HistoriekReceptieLijn recLijn = em.find(HistoriekReceptieLijn.class, id);
            if(recLijn != null){
              em.remove(recLijn);
              em.getTransaction().commit();  
            }
        }
        finally{
            em.close();       
        }
    }
    
    public List <HistoriekReceptieLijn> listAllHistoriekReceptieLijnen(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <HistoriekReceptieLijn> results;
        try {
           Query query = em.createQuery("SELECT h FROM HistoriekReceptieLijn h", HistoriekReceptieLijn.class);
           
           results = query.getResultList();
           return results;
        }
        finally{
            em.close();       
        }
    }
    
    /**
     * Lijst van Historiek Receptiegoederen waarvan orders nog niet geleverd zijn en voor bepaalde leverancier
     * @return List lijst van historiekReceptieGoederen
     */
    public List <HistoriekReceptieLijn> listHistoriekReceptieLijnenByOrdersLopend(String groepNaam){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <HistoriekReceptieLijn> results = new ArrayList<>();
        List <HistoriekReceptieLijn> tempResults = new ArrayList();
       
        try {
           Query query = em.createQuery("SELECT h FROM HistoriekReceptieLijn h", HistoriekReceptieLijn.class);       
           tempResults = query.getResultList();
           if(!tempResults.isEmpty()){
               for(HistoriekReceptieLijn hrl : tempResults){
                   OrderLijn ol = (OrderLijn) hrl.getOrder().getOrderlijnen().iterator().next();
                   if(hrl.getOrder().getStatus() != 70 && ol.getLeverancier().getGroep().getGroepNaam().equals(groepNaam)){
                       results.add(hrl);
                   }
               }
           }
           return results;
        }
        finally{
            em.close();       
        }
    }
    
    /**
     *
     * @param orderNr orderNr
     * @param variantNr String variantNr
     * @return Lijst van HistoriekReceptieLijnen List
     */
    public List <HistoriekReceptieLijn> listHistoriekReceptieLijnenByOrderNrAndVariantNr(String orderNr, String variantNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <HistoriekReceptieLijn> results;
        try {
           Query query = em.createQuery("SELECT h FROM HistoriekReceptieLijn h where h.order.orderNr = :orderNr AND h.variantNr = :variantNr", HistoriekReceptieLijn.class);
           query.setParameter("orderNr", orderNr);
           query.setParameter("variantNr", variantNr);
          
           results = query.getResultList();
           return results;
        }
        finally{
            em.close();       
        }
    }
    
     /**
     *
     * @param orderNr orderNr
     * @return Lijst van HistoriekReceptieLijnen List
     */
    public List <HistoriekReceptieLijn> listHistoriekReceptieLijnenByGroepNaam(String groepNaam){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <HistoriekReceptieLijn> results = new ArrayList<>();
        try {
           Query query = em.createQuery("SELECT h FROM HistoriekReceptieLijn h", HistoriekReceptieLijn.class);
           List <HistoriekReceptieLijn> tempResults;
           tempResults = query.getResultList();
           for(HistoriekReceptieLijn hrl : tempResults){
               if(!hrl.getOrder().isAfgewerkt()){
                    if(hrl.getOrder().getOrderlijnen() != null && !hrl.getOrder().getOrderlijnen().isEmpty()){
                        OrderLijn ol = (OrderLijn) hrl.getOrder().getOrderlijnen().iterator().next();
                        if(ol.getLeverancier().getGroep().getGroepNaam().equals(groepNaam)){
                            results.add(hrl);
                        } 
                    }  
               }                   
           }
           return results;
        }
        finally{
            em.close();       
        }
    }

     public int deleteOldHistoriekReceptieLijnen(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        LocalDate vervalDatum = LocalDate.now().minusMonths(2);
        int deletedCount = 0;
        try{
            Query query = em.createQuery("SELECT hl FROM HistoriekReceptieLijn hl WHERE hl.receptieDatum < :vervalDatum");
            query.setParameter("vervalDatum", vervalDatum);
            List <HistoriekReceptieLijn> receptieLijnen = query.getResultList();
            
            if(receptieLijnen != null){
                for(HistoriekReceptieLijn hrl: receptieLijnen){
                    em.getTransaction().begin();
                    em.remove(hrl);
                    em.getTransaction().commit();
                    deletedCount++;
                }
            }
            return deletedCount;
        }
        finally{
            em.close();
          
        }
    }
    
    /**
     * 
     * @param lanceringNr int
     * @return Recentste updatedatum LocalDate
     */
    public LocalDate getDatumLaatsteReceptie(int lanceringNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try {
            Query query = em.createQuery("SELECT h.receptieDatum FROM HistoriekReceptieLijn h where h.orderlijn.lanceringNr = :lanceringNr");
            query.setParameter("lanceringNr", lanceringNr);
            List <LocalDate> datums = query.getResultList();
            Collections.sort(datums);
            if(datums != null && !datums.isEmpty()){
                return datums.get(datums.size()) ;
            }
            else{
                return LocalDate.now();
            }
            
        }
        finally{
            em.close();       
        }
    }
    
}
