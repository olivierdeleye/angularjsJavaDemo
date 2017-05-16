/*
 * persistence DB karren
 */
package ngdemo.dao;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.Barcode;
import ngdemo.domain.Kar;
import ngdemo.domain.KarType;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */

/*
CRUD operations here
*/

public class KarDAO{
   
    /**
     *
     */
    public KarDAO() {
         
    }

    /*
    CRUD operations here
    */
   
   // CREATE KAR

    /**
     *
     * @param karNr
     * @param karType
     * @param isVrij
     * @param aantalPlaatsen
     * @return
     */
   public Kar createKar(String karNr, KarType karType, boolean isVrij, int aantalPlaatsen){
       
       EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
       
       try{
            Kar kar = new Kar();
            em.getTransaction().begin();
            kar.setKarNr(karNr);
            kar.setKarType(karType);
            kar.setVrijePlaatsen(aantalPlaatsen);
            kar.setIsVrij(isVrij);

            em.persist(kar);
            em.getTransaction().commit();
            return kar;  
       }
       finally{
          em.close();
          
       }
       
   }
   
   // FIND KAR

    /**
     *
     * @param karNr
     * @return
     */
   public Kar findKar(String karNr){
       
      EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
       
       try{
            Kar kar;
            kar = em.find(Kar.class, karNr);
            return kar;  
       }
       finally{
          em.close();
          
       }  
   }
   
    /**
     *
     * @param type
     * @return
     */
    public List<Kar> listLegeKarrenByType(KarType type){
        
       EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
       
       try{
            Query query = em.createNamedQuery("listLegeKarrenByType");
            query.setParameter("type", type);
            List <Kar> karren = query.getResultList();
            return karren; 
       }
       finally{
           em.close();
          
       }
   }
   
    /**
     *
     * @param type
     * @return
     */
    public List <Kar> listKarrenByType(KarType type){
       
       EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
       
       try{
            Query query = em.createNamedQuery("listKarrenByType");
            query.setParameter("type", type);
            List <Kar> karren = query.getResultList();
            return karren;
       }
       finally{
           em.close();
           
       }   
   }
      
    /**
     *
     * @param karNr
     */
    public void resetKar(String karNr){ 
       
       EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
       
       try{
            Kar kar;
            kar = em.find(Kar.class, karNr);
            em.getTransaction().begin();  
            kar.setOrders(null);
            Set <Barcode> barcodes = kar.getBarcodes();
            if(barcodes != null && !barcodes.isEmpty()){
               for(Barcode bc : barcodes){
                   bc.setKar(null);
               }
            }  
            kar.setIsVrij(true);
            int aantalVrijePlaatsen = kar.getKarType().getTotaalCapaciteit();
            kar.setVrijePlaatsen(aantalVrijePlaatsen);
            em.getTransaction().commit();
       }
       finally{
           em.close(); 
          
       }   
   } 
   
    /**
     *
     * @param type
     * @param nieuweCapaciteit
     */
    public void alterVrijePlaatsen(KarType type, int nieuweCapaciteit){
       EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
       try{
                       
            em.getTransaction().begin();
            Query query = em.createNamedQuery("listKarrenByType");
            query.setParameter("type", type);
            List <Kar> karren = query.getResultList();
            
            for(Kar k : karren){
                int aantalVrijePlaatsen = k.getVrijePlaatsen();
                int vorigeCapactiteit = k.getKarType().getTotaalCapaciteit();
                //VERMEERDER PLAATSEN
                if(nieuweCapaciteit > vorigeCapactiteit){ 
                    int verschil = nieuweCapaciteit -vorigeCapactiteit;
                    k.setVrijePlaatsen( aantalVrijePlaatsen + verschil);
                }
                //VERMINDER PLAATSEN
                else{
                    int verschil = vorigeCapactiteit - nieuweCapaciteit;
                    k.setVrijePlaatsen(aantalVrijePlaatsen - verschil);
                }
                      
            }
            em.getTransaction().commit();
       }
       finally{
           em.close();
           
       }
   }
}
