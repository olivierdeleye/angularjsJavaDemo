/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ngdemo.domain.Leverancier;
import ngdemo.util.PersistenceManager;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author olivier deleye
 */

public class LeverancierDAO {
    
    /**
     *
     */
    public LeverancierDAO() {

    } 
    /*
    CRUD
    */
    
    /*
    CREATE
    */

    /**
     *
     * @param leverancierNr
     * @param lev_naam
     * @param straat
     * @param huisNr
     * @param bus
     * @param gemeente
     * @param postcode
     * @param telefoon
     * @param fax
     * @param contactpersoon
     * @param opmerking
     * @return
     */

     public Leverancier createLeverancier(String leverancierNr, String lev_naam, String straat, String huisNr, String bus, String gemeente, String postcode , String telefoon , String fax,
                                           String contactpersoon, String opmerking) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Leverancier lev = new Leverancier();
            lev.setLeverancierNr(leverancierNr);
            lev.setLev_naam(lev_naam);
            lev.setStraat(straat);
            lev.setHuisNr(huisNr);
            lev.setBusNr(bus);
            lev.setGemeente(gemeente);
            lev.setPostcode(postcode);
            lev.setTelNr(telefoon);
            lev.setFax(fax);
            lev.setContactpersoon(contactpersoon);
            lev.setOpmerking(opmerking);
            em.getTransaction().begin();
            em.persist(lev);
            em.getTransaction().commit();
            return lev;
        }
        finally{
            em.close();
          
        } 
    }
     
    /**
     *
     * @param leverancierNr
     * @return
     */
    public Leverancier findLeverancier(String leverancierNr){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           Leverancier l;
        l = em.find(Leverancier.class, leverancierNr); 
        return l; 
        }
        finally{
            em.close();
           
        }      
    }
     
    public Leverancier findLeverancierByNaam(String leverancierNaam){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           Leverancier l;
           List <Leverancier> leveranciers;
           Query query = em.createQuery("SELECT l FROM Leverancier l where l.lev_naam = :leverancierNaam", Leverancier.class);
           query.setParameter("leverancierNaam", leverancierNaam);
           leveranciers = query.getResultList();
           return CollectionUtils.isEmpty(leveranciers ) ? null : leveranciers.get(0);
        }
        finally{
            em.close();        
        }      
    }
    /**
     *
     * @return
     */
    public List<Leverancier> findAllLeveranciers() {
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            TypedQuery<Leverancier> query = em.createQuery("SELECT l FROM Leverancier l", Leverancier.class);
            return query.getResultList();
        }
        finally{
            em.close();
           
        }      
    }   
}





