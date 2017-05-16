/*
 * Persistence class ScanTimeStamp entities
 */
package ngdemo.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.Barcode;
import ngdemo.domain.ScanTimeStamp;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */
public class ScanTimeStampDAO {
    
    /**
     * Create new ScanTimeStamp 
     * @param datum
     * @param tijdstip
     * @param barcode
     * @return persisted ScanTimeStamp
     */
    public ScanTimeStamp createScanTimeStamp(LocalDate datum, LocalTime tijdstip, Barcode barcode){
         EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            ScanTimeStamp scanTimeStamp = new ScanTimeStamp();
         
            em.getTransaction().begin();
            scanTimeStamp.setDatum(datum);
            scanTimeStamp.setTijdstip(tijdstip);
            scanTimeStamp.setBarcode(barcode);
     
            em.persist(scanTimeStamp);
            em.getTransaction().commit();
            return scanTimeStamp; 
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * Find by id
     * @param id
     * @return ScanTimeStamp
     */
    public ScanTimeStamp findScanTimeStamp(Long id){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            ScanTimeStamp sts;
            sts = em.find(ScanTimeStamp.class, id);
            return sts;
        }
        finally{
            em.close();
          
        } 
    }
    
    /**
     * Delete 
     * @param id 
     */
    public void deleteScanTimeStamp(Long id){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
         
         try {
            em.getTransaction().begin();
            ScanTimeStamp sts = em.find(ScanTimeStamp.class, id);
            if(sts != null){
              em.remove(sts);
              em.getTransaction().commit();  
            }
        }
        finally{
            em.close();       
        }
    }
    
    /**
     * List alle ScanTimeStamp records
     * @return Lijst van alle ScanTimeStamps
     */
    public List<ScanTimeStamp> listAllScanTimeStamps(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <ScanTimeStamp> scanTimeStamps;
         try {
            Query query = em.createQuery("SELECT s FROM ScanTimeStamp s", ScanTimeStamp.class);
            return query.getResultList();
        }
        finally{
            em.close();       
        }
    }
    
    /**
     * List alle ScanTimeStamp records van bepaalde datum
     * @param datum
     * @return lijst van alle ScanTimeStamps met bepaalde datum
     */
    public List <ScanTimeStamp> listScanTimeStampsByDatum(LocalDate datum){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <ScanTimeStamp> scanTimeStamps;
         try {
            Query query = em.createQuery("SELECT s FROM ScanTimeStamp s WHERE s.datum = :datum", ScanTimeStamp.class);
            query.setParameter("datum", datum);
            return query.getResultList();
        }
        finally{
            em.close();       
        }
    }
    
    
}
