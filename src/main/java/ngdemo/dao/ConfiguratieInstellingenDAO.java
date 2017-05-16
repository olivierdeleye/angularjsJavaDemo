/*
 * Persistence configuratieInstellingen
 */
package ngdemo.dao;

import javax.persistence.EntityManager;
import ngdemo.domain.ConfiguratieInstellingen;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */

public class ConfiguratieInstellingenDAO {
    
    /**
     *
     */
    public ConfiguratieInstellingenDAO() {
        
    }
     
    // Enkel 1 record waar alle config bijgehouden wordt.

    /**
     *
     * @return
     */
    public ConfiguratieInstellingen find(){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
          ConfiguratieInstellingen cfi = em.find(ConfiguratieInstellingen.class,1);
          return cfi;  
        }
        finally{
            em.close();
         
        }
    }
   
    /**
     * COM-poort continue scannen instellen in DB
     * @param compoort String 
     * @return
     */
    public ConfiguratieInstellingen updateContinueScanCOMpoort(String compoort){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            ConfiguratieInstellingen cfi = em.find(ConfiguratieInstellingen.class, 1);
            em.getTransaction().begin();
            cfi.setContinueScan_COM_port(compoort);
            em.getTransaction().commit();
        return cfi;
        }
        finally{
            em.close();
            
        }
    }

    /**
     * COM-poort single scannen instellen in DB
     * @param compoort String 
     * @return
     */
    public ConfiguratieInstellingen updateSingleScanCOMpoort(String compoort){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            ConfiguratieInstellingen cfi = em.find(ConfiguratieInstellingen.class, 1);
            em.getTransaction().begin();
            cfi.setScan_COM_port(compoort);
            em.getTransaction().commit();
        return cfi;
        }
        finally{
            em.close();
            
        }
    }
    
    /**
     *
     * @param directory
     * @return
     */
    public ConfiguratieInstellingen updatePathToDirectoryBestanden(String directory){
         
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            ConfiguratieInstellingen cfi = em.find(ConfiguratieInstellingen.class, 1);
            em.getTransaction().begin();
            cfi.setPathToDirectoryBestanden(directory);
            em.getTransaction().commit();
            return cfi; 
        }
        finally{
            em.close();
            
        }
    }
   
    /**
     *
     * @param timeoutValue
     * @return
     */
    public ConfiguratieInstellingen updateTimeOutValue(int timeoutValue){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            ConfiguratieInstellingen cfi = em.find(ConfiguratieInstellingen.class, 1);
            em.getTransaction().begin();
            cfi.setTimeOutValue(timeoutValue);
            em.getTransaction().commit();
            return cfi; 
        }
        finally{
            em.close();
         
        }    
    }    
}
