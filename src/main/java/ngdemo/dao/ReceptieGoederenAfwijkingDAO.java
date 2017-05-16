/*
 * Persistence class rec_goed_afwijkingen.
 */
package ngdemo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.Order;
import ngdemo.domain.OrderLijn;
import ngdemo.domain.ReceptieGoederenAfwijking;
import ngdemo.domain.ReceptieGoederenAfwijking.TypeAfwijking;
import ngdemo.util.PersistenceManager;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author olivier deleye
 */
public class ReceptieGoederenAfwijkingDAO {

    public ReceptieGoederenAfwijkingDAO() {
    }
    
  
     /**
     * 
     * @param receptieDatum LocalDate
     * @param typeAfwijking TypeAfwijking ONTVANGENNIETVERWACHT, VERWACHTNIETONTVANGEN
     * @param aantalOntvangen int
     * @param aantalVerwacht int
     * @param status Status LOPEND, AFGEHANDELD
     * @param orderlijn OrderLijn
     * @return ReceptieGoederenAfwijking
     */
 
    public ReceptieGoederenAfwijking createReceptieGoederenAfwijking(LocalDate receptieDatum, TypeAfwijking typeAfwijking, int aantalOntvangen, int aantalVerwacht, String variantNr, Order order){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            ReceptieGoederenAfwijking recGoedAfwijking = new ReceptieGoederenAfwijking();
         
            em.getTransaction().begin();
            recGoedAfwijking.setReceptieDatum(receptieDatum);
            recGoedAfwijking.setTypeAfwijking(typeAfwijking);
            recGoedAfwijking.setAantalOntvangen(aantalOntvangen);
            recGoedAfwijking.setAantalVerwacht(aantalVerwacht);
            recGoedAfwijking.setVariantNr(variantNr);
            recGoedAfwijking.setPrintVersie(1);
            recGoedAfwijking.setOrder(order);
            
            em.persist(recGoedAfwijking);
            em.getTransaction().commit();
         
            return recGoedAfwijking; 
        }
        finally{
            em.close();
           
        }       
    }
    
    /**
     * Find by id
     * @param id Long
     * @return ReceptieGoederenAfwijking
     */
    public ReceptieGoederenAfwijking findReceptieGoederenAfwijking(Long id){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try {
            ReceptieGoederenAfwijking recGoedAfwijking;
            recGoedAfwijking = em.find(ReceptieGoederenAfwijking.class, id);
            return recGoedAfwijking;
        }
        finally{
            em.close();
        } 
    }
    
   
    /**
     * 
     * @param id Long
     * @param receptieDatum LocalDate
     * @param typeAfwijking
     * @param aantalOntvangen
     * @param aantalVerwacht
     * @param status Status LOPEND, AFGEHANDELD
     * @return ReceptieGoederenAfwijking
     */
     public ReceptieGoederenAfwijking updateReceptieGoederenAfwijking(Long id, LocalDate receptieDatum, TypeAfwijking typeAfwijking, int aantalOntvangen, int aantalVerwacht, int printVersie){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            ReceptieGoederenAfwijking recGoedAfwijking = em.find(ReceptieGoederenAfwijking.class, id);
            if(recGoedAfwijking != null){
                em.getTransaction().begin();
                recGoedAfwijking.setReceptieDatum(receptieDatum);
                recGoedAfwijking.setTypeAfwijking(typeAfwijking);
                recGoedAfwijking.setAantalOntvangen(aantalOntvangen);
                recGoedAfwijking.setAantalVerwacht(aantalVerwacht);
                recGoedAfwijking.setPrintVersie(printVersie);
            
                em.getTransaction().commit();
            }
                  
            return recGoedAfwijking; 
        }
        finally{
            em.close();
           
        }       
    }
    
    /**
     * Delete ReceptieGoederenAfwijking
     * @param id Long
     */
    public void deleteReceptieGoederenAfwijking(Long id){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try {
            ReceptieGoederenAfwijking recGoedAfwijking;
            em.getTransaction().begin();
            recGoedAfwijking = em.find(ReceptieGoederenAfwijking.class, id);
            if(recGoedAfwijking != null){
                em.remove(recGoedAfwijking);
            }
            em.getTransaction().commit();      
        }
        finally{
            em.close();
        } 
    }
    
    /**
     * Find by lanceringNr
     * @param orderNr String orderNr
     * @return receptieGoederenAfwijking object ReceptieGoederenAfwijking
     */
    public ReceptieGoederenAfwijking findByDateAndOrderNrAndVariantNr(LocalDate datum, String orderNr, String variantNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
          
           List <ReceptieGoederenAfwijking> results;
           Query query = em.createQuery("SELECT r FROM ReceptieGoederenAfwijking r WHERE r.receptieDatum = :datum AND r.order.orderNr = :orderNr AND r.variantNr = :variantNr", ReceptieGoederenAfwijking.class);
           query.setParameter("datum", datum);
           query.setParameter("orderNr", orderNr);
           query.setParameter("variantNr", variantNr);
         
           results = query.getResultList();
           
           return CollectionUtils.isEmpty(results ) ? null : results.get(0);
        }
        finally{
            em.close();
            
        } 
    }
  
    /**
     * List alle ReceptieGoederenAfwijkingen
     * @return List ReceptieGoederenAfwijkingen
     */
    public List <ReceptieGoederenAfwijking> listAllReceptieGoederenAfwijkingen(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            List <ReceptieGoederenAfwijking> recGoedAfwijkingen;
            Query query = em.createQuery("SELECT r FROM ReceptieGoederenAfwijking r");
            recGoedAfwijkingen = query.getResultList();
            return recGoedAfwijkingen;
        }
        finally{
            em.close();
        }    
    }
    
    /**
     * List alle ReceptieGoederenAfwijkingen adhv receptieDatum
     * @param receptieDatum LocalDate
     * @return List ReceptieGoederenAfwijkingen
     */
    public List <ReceptieGoederenAfwijking> listReceptieGoederenAfwijkingenByReceptieDatum(LocalDate receptieDatum){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            List <ReceptieGoederenAfwijking> recGoedAfwijkingen;
            Query query = em.createQuery("SELECT r FROM ReceptieGoederenAfwijking r where r.receptieDatum = :receptieDatum");
            query.setParameter("receptieDatum", receptieDatum);
            recGoedAfwijkingen = query.getResultList();
            return recGoedAfwijkingen;
        }
        finally{
            em.close();
        }    
    }
    
     /**
     * List alle ReceptieGoederenAfwijkingen adhv status en groepId (leveranciergroep)
     * @param groepNaam String
     * @return List ReceptieGoederenAfwijkingen
     */
    public List <ReceptieGoederenAfwijking> listReceptieGoederenAfwijkingenByStatusAndLeverancier(String groepNaam){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            List <ReceptieGoederenAfwijking> recGoedAfwijkingen = new ArrayList<>();
            List <ReceptieGoederenAfwijking> tempList = new ArrayList<>();
            Query query = em.createQuery("SELECT r FROM ReceptieGoederenAfwijking r");
            tempList = query.getResultList();
            if(!tempList.isEmpty()){
                for(ReceptieGoederenAfwijking rga : tempList){
                    if(rga.getOrder().getOrderlijnen() != null && !rga.getOrder().getOrderlijnen().isEmpty()){
                        OrderLijn o = (OrderLijn) rga.getOrder().getOrderlijnen().iterator().next();
                        if(o.getLeverancier().getGroep().getGroepNaam().equals(groepNaam)){
                            recGoedAfwijkingen.add(rga);
                        }
                    }     
                }
            }
            return recGoedAfwijkingen;
        }
        finally{
            em.close();
        }    
    }
    
     /**
     * List alle ReceptieGoederenAfwijkingen adhv typeAfwijking
     * @param typeAfwijking TypeAfwijking ONTVANGENNIETVERWACHT, VERWACHTNIETONTVANGEN
     * @return List ReceptieGoederenAfwijkingen
     */
    public List <ReceptieGoederenAfwijking> listReceptieGoederenAfwijkingenByTypeAfwijking(TypeAfwijking typeAfwijking){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            List <ReceptieGoederenAfwijking> recGoedAfwijkingen;
            Query query = em.createQuery("SELECT r FROM ReceptieGoederenAfwijking r where r.typeAfwijking = :typeAfwijking");
            query.setParameter("typeAfwijking", typeAfwijking);
            recGoedAfwijkingen = query.getResultList();
            return recGoedAfwijkingen;
        }
        finally{
            em.close();
        }    
    }
    
    /**
     * List alle ReceptieGoederenAfwijkingen adhv typeAfwijking, status en leverancierNr
     * @param typeAfwijking TypeAfwijking ONTVANGENNIETVERWACHT, VERWACHTNIETONTVANGEN
     * @param status Status LOPEND, AFGEHANDELD
     * @param groepNaam String
     * @return List ReceptieGoederenAfwijkingen
     */
    public List <ReceptieGoederenAfwijking> listAfwijkingenByTypeAfwijkingAndStatusAndLeverancierNr(TypeAfwijking typeAfwijking, String groepNaam){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            List <ReceptieGoederenAfwijking> recGoedAfwijkingen;
            Query query = em.createQuery("SELECT r FROM ReceptieGoederenAfwijking r where r.typeAfwijking = :typeAfwijking AND r.orderlijn.leverancier.groep.groepNaam = :groepNaam");
            query.setParameter("typeAfwijking", typeAfwijking);
            query.setParameter("groepNaam", groepNaam);
            recGoedAfwijkingen = query.getResultList();
            return recGoedAfwijkingen;
        }
        finally{
            em.close();
        }    
    }
    
     /**
     * List alle ReceptieGoederenAfwijkingen adhv status en receptieDatum
     * @param receptieDatum LocalDate
     * @param status Status LOPEND, AFGEHANDELD
     * @return List ReceptieGoederenAfwijkingen
     */
    public List <ReceptieGoederenAfwijking> listReceptieGoederenAfwijkingenByCriteria(LocalDate receptieDatum){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            List <ReceptieGoederenAfwijking> recGoedAfwijkingen;
            Query query = em.createQuery("SELECT r FROM ReceptieGoederenAfwijking r where r.receptieDatum = :receptieDatum");
            query.setParameter("receptieDatum", receptieDatum);
            recGoedAfwijkingen = query.getResultList();
            return recGoedAfwijkingen;
        }
        finally{
            em.close();
        }    
    }
    
    /**
     * Printversie verhogen 
     * @param id Long
     */
    public void verhoogPrintversie(Long id){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try{
            em.getTransaction().begin();
            ReceptieGoederenAfwijking rga = em.find(ReceptieGoederenAfwijking.class, id);
            if(rga != null){
                rga.setPrintVersie(rga.getPrintVersie() + 1);
            }
            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }
}
