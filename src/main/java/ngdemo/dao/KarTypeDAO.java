/*
 * Persistence Class voor karTypes
 */
package ngdemo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ngdemo.domain.KarType;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */

public class KarTypeDAO {

    /**
     *
     */
    public KarTypeDAO() {
       
    }
    
    /*CRUD*/
    
    /*CREATE*/
    /**
     * 
     * @param type omschrijving van KarType
     * @param aantalVastePlaatsen
     * @param onderCapaciteit
     * @param bovenCapaciteit
     * @param volleHoogteCapaciteit
     * @param aantal aantal karren van dit type
     * @param maxDikte dikte van paneel die in één plaats past
     * @param maxBreedte diepte van kar
     * @return KarType Object
     */
    public KarType createKarType(String type, int aantalVastePlaatsen, int onderCapaciteit, int bovenCapaciteit, int volleHoogteCapaciteit, int aantal, int maxDikte, int maxBreedte) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            KarType karType = new KarType();
            karType.setType(type);
            karType.setAantalVastePlaatsen(aantalVastePlaatsen);
            karType.setTotaalCapaciteit(aantalVastePlaatsen);//BIJ CREATIE VAN KAR WORDT GEEN REKENING GEHOUDEN MET OVERLOAD DUS TOTAALCAPCITEIT = AANTALVASTEPLAATSEN
            karType.setOnderCapaciteit(onderCapaciteit);
            karType.setBovenCapaciteit(bovenCapaciteit);
            karType.setVolleHoogteCapaciteit(volleHoogteCapaciteit);
            karType.setAantal(aantal);
            karType.setMaxDikte(maxDikte);
            karType.setMaxBreedte(maxBreedte);
            em.getTransaction().begin();
            em.persist(karType);
            em.getTransaction().commit();
            return karType;  
        }
        finally{
            em.close();
           
        }
    }
     
    /*READ*/
     
    /*
     Find karType
     @param kartype: type (omschrijving: NORMAAL, BREED, ...)
     */

    /**
     *
     * @param karType
     * @return
     */

    public KarType findKarType(String karType){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            KarType kt;
            kt = em.find(KarType.class, karType); 
            return kt;  
        }
        finally{
            em.close();
           
        }    
    }
     
    /**
     * Lijst van alle karTypes
     * @return
     */
    public List<KarType> findAllKarTypes() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           TypedQuery<KarType> query = em.createQuery("SELECT kt FROM KarType kt", KarType.class);
           return query.getResultList(); 
        }
        finally{
            em.close();
            
        }
    }
    
    public List <String> listAllKarTypeNamen(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           Query query = em.createQuery("SELECT kt.type FROM KarType kt", String.class);
           return query.getResultList(); 
        }
        finally{
            em.close();         
        }
    }
    
    /*
    UPDATE
    */    
    /* Update Karparameters voor een specifiek karType
       @param kartype 
       @param overload aantal plaatsen extra bij 
    */

    /**
     *
     * @param karType
     * @param totaalCapaciteit
     * @param onderCapaciteit
     * @param bovenCapaciteit
     * @param volleHoogteCapaciteit
     * @param maxDikte
     * @param maxBreedte
     * @param aantalKarren
     * @return
     */

    public KarType updateKarParameters(String karType, int totaalCapaciteit, int onderCapaciteit, int bovenCapaciteit,
                                     int volleHoogteCapaciteit, int maxDikte, int maxBreedte, int aantalKarren){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            KarType kt =  em.find(KarType.class, karType); // find by karType
        
            em.getTransaction().begin();
            kt.setTotaalCapaciteit(totaalCapaciteit);
            kt.setOnderCapaciteit(onderCapaciteit);
            kt.setBovenCapaciteit(bovenCapaciteit);
            kt.setVolleHoogteCapaciteit(volleHoogteCapaciteit);
            kt.setAantal(aantalKarren);
            kt.setMaxDikte(maxDikte);
            kt.setMaxBreedte(maxBreedte);
            em.getTransaction().commit();
            return kt; 
        }
        finally{
            em.close();
            
        }
    }
    
    /*
    DELETE
    */

    /**
     *
     * @param karType
     */

    
    public void deleteKarType(String karType){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            KarType kt = em.find(KarType.class, karType);
            em.getTransaction().begin();
            em.remove(kt);
        }
        finally{
            em.close();
           
        }  
    }
}
