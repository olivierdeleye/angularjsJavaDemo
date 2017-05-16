/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.dao;

import javax.persistence.EntityManager;
import ngdemo.domain.ArtikelType;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */

public class ArtikelTypeDAO {
    
    /**
     *
     */
    public ArtikelTypeDAO() {
        
    }
    
    // CRUD OPERATIONS
    
    /**
     * FindBy artNr
     * @param artNr
     * @param type
     * @return ArtikelType Object
     */  
    public ArtikelType createArtikelType(String artNr, String type){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
      
        try{
            ArtikelType artikelType = new ArtikelType();
            artikelType.setArtNr(artNr);
            artikelType.setType(type);
            em.getTransaction().begin();
            em.persist(artikelType);
            em.getTransaction().commit();
            return artikelType;
        }
        finally{
            em.close();
            
        }     
    }
    
    /**
     *
     * @param artNr
     * @return
     */
    public ArtikelType findArtikelType(String artNr){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        ArtikelType at;
        try{
          at = em.find(ArtikelType.class, artNr);
          return at;  
        }
        finally{
            em.close();
            
        }      
    }
    
    /**
     *
     * @param artNr
     * @param type
     * @return
     */
    public ArtikelType updateArtikelType(String artNr, String type){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try{
            ArtikelType artikelType = em.find(ArtikelType.class, artNr);
            if(artikelType != null){
                em.getTransaction().begin();
                artikelType.setType(type);
                em.getTransaction().commit();
            }    
            return artikelType;
        }
        finally{
            em.close();
           
        }     
    }    
}
