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
import ngdemo.domain.Groep;
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
                                           String contactpersoon, String opmerking, String groepNaam) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        Groep groep;
        GroepDAO groepDao = new GroepDAO();
        groep = groepDao.findByGroepNaam(groepNaam);
        if(groep == null){
            groep = groepDao.createNewGroep(groepNaam);
        }
      
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
            lev.setGroep(groep);
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
    
     public Leverancier updateLeverancier(String leverancierNr, String lev_naam, String straat, String huisNr, String bus, String gemeente, String postcode , String telefoon , String fax,
                                           String contactpersoon, String opmerking, String groepNaam) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        Groep groep;
        Leverancier lev = null;
        
        GroepDAO groepDao = new GroepDAO();
        groep = groepDao.findByGroepNaam(groepNaam);
        if(groep == null){
            groep = groepDao.createNewGroep(groepNaam);
        }
         
        try{
            em.getTransaction().begin();
            lev = findLeverancier(leverancierNr);
            if(lev != null){
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
                lev.setGroep(groep);
                em.merge(lev);
                em.getTransaction().commit(); 
            }
          
            return lev;
        }
        finally{
            em.close();
          
        } 
    }
     
     public void deleteLeverancier(String leverancierNr){
         
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Leverancier leverancier = em.find(Leverancier.class, leverancierNr);
            if(leverancier != null){
                em.getTransaction().begin();
                em.remove(leverancier);
                em.getTransaction().commit();
            }
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






