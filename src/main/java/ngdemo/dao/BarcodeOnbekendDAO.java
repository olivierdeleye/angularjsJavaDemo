/*
 * BarcodeOnbekend persistence class
 */
package ngdemo.dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.BarcodeOnbekend;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */
public class BarcodeOnbekendDAO {

    /**
     *
     */
    public BarcodeOnbekendDAO() {
    }
    
    /**
     * Create BarcodeOnbekend
     * @param barcode
     * @param scanDatum
     * @return BarcodeOnbekend
     */
    public BarcodeOnbekend createBarcodeOnbekend(String barcode, LocalDate scanDatum){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try {
            em.getTransaction().begin();
            BarcodeOnbekend barcodeOnbekend = em.find(BarcodeOnbekend.class, barcode);
            if(barcodeOnbekend == null){
                barcodeOnbekend = new BarcodeOnbekend();
                barcodeOnbekend.setBarcode(barcode);
                barcodeOnbekend.setScanDatum(scanDatum);
                em.persist(barcodeOnbekend);
                em.getTransaction().commit();           
            } 
            return barcodeOnbekend;
        }
        finally{
            em.close();       
        }
    }
    
    /**
     * Find BarcodeOnbekend
     * @param barcode
     * @return BarcodeOnbekend
     */
    public BarcodeOnbekend findBarcodeOnbekend(String barcode){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           BarcodeOnbekend barcodeOnbekend = em.find(BarcodeOnbekend.class, barcode);
           return barcodeOnbekend; 
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * Delete BarcodeOnbekend
     * @param barcode 
     */
    public void deleteBarcodeOnbekend(String barcode){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
         
         try {
            em.getTransaction().begin();
            BarcodeOnbekend barcodeOnbekend = em.find(BarcodeOnbekend.class, barcode);
            if(barcodeOnbekend != null){
               em.remove(barcodeOnbekend);
               em.getTransaction().commit();
            }           
        }
        finally{
            em.close();       
        }
    }
    
    /**
     * List alle BarcodesOnbekend
     * @return lijst van alle onbekende barcodes
     */
    public List <BarcodeOnbekend> listAllBarcodesOnbekend(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Query query = em.createQuery("SELECT b FROM BarcodeOnbekend b");
            List <BarcodeOnbekend> barcodesOnbekend = query.getResultList();
            return barcodesOnbekend;
        }
        finally{
            em.close();       
        }      
    }
    
    
}
