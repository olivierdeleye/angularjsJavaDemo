/*
 * Persistence class locaties.
 */
package ngdemo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.Locatie;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */
public class LocatieDAO {

    /**
     *
     */
    public LocatieDAO() {
    }
    
    /**
     * CREATE
     * @param locatie
     * @param omschrijving 
     * @return Lacatie object
     */
    public Locatie createLocatie(String locatie , String omschrijving){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Locatie loc = new Locatie();
            em.getTransaction().begin();
            loc.setLocatie(locatie);
            loc.setOmschrijving(omschrijving);
            em.persist(loc);
            em.getTransaction().commit();
            
            return loc;  
        }
        finally{
            em.close();          
        } 
    }
    
    /**
            KarType kt;
     * READ
     * @param locatie
     * @return 
     */
    public Locatie findLocatie(String locatie){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Locatie loc;
            loc = em.find(Locatie.class, locatie); 
            return loc;  
        }
        finally{
            em.close();          
        } 
    }
    
    /**
     * UPDATE
     * @param locatie
     * @param omschrijving 
     * @return  
     */
    public Locatie updateLocatie(String locatie , String omschrijving){
         EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Locatie loc;
            em.getTransaction().begin();
            loc = em.find(Locatie.class, locatie);
            if(loc != null){
               loc.setLocatie(locatie);
               loc.setOmschrijving(omschrijving);
            }
            em.getTransaction().commit();
            return loc;  
        }
        finally{
            em.close();          
        } 
    }
    
    /**
     * DELETE
     * @param locatie 
     */
    public void deleteLocatie(String locatie){
         EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
         
         try{
            em.getTransaction().begin();
            Locatie loc = em.find(Locatie.class, locatie);
            em.remove(loc);
            em.getTransaction().commit();
         }
         finally{
             em.close();
         }    
    }
    
    /**
     * List alle locaties
     * @return 
     */
    public List <Locatie> listAllLocaties(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Query query = em.createQuery("SELECT l FROM Locatie l");
            List <Locatie> locaties = query.getResultList();
            return locaties; 
        }
        finally{
            em.close();
         
        } 
    }
    
     /**
     * List alle locaties
     * @return 
     */
    public List <String> listAllLocatiesNamen(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Query query = em.createQuery("SELECT l.locatie FROM Locatie l");
            List <String> locaties = query.getResultList();
            return locaties; 
        }
        finally{
            em.close();
         
        } 
    }
    
}
