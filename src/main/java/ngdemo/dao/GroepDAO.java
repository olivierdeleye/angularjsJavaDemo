/*
 * Persistence class voor tabel leveranciergroepen.
 */
package ngdemo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.Groep;
import ngdemo.util.PersistenceManager;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author olivier deleye
 */
public class GroepDAO {

    public GroepDAO() {
    }
    
    /**
     * Find Groep by id
     * @param id int
     * @return Groep 
     */
    
    public Groep createNewGroep(String groepNaam){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        try{
            em.getTransaction().begin();
            Groep groep = new Groep();
            groep.setGroepNaam(groepNaam);
            em.persist(groep);
            em.getTransaction().commit();
            return groep;
        }
        finally{
            em.close();
        }
        
    }
    
    
    public Groep findById(int id){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           Groep groep = em.find(Groep.class, id);
           return groep; 
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * Find Groep by groepNaam
     * @param groepNaam String
     * @return Groep 
     */
    public Groep findByGroepNaam(String groepNaam){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           List <Groep> groepen;
           Query q = em.createQuery("SELECT g FROM Groep g WHERE g.groepNaam = :groepNaam", Groep.class);
           q.setParameter("groepNaam", groepNaam);
           groepen = q.getResultList();
           return CollectionUtils.isEmpty(groepen) ? null : groepen.get(0);
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * List alle groepen
     * @return List groepn 
     */
    public List<Groep> listAllGroepen(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           List <Groep> groepen;
           Query q = em.createQuery("SELECT g FROM Groep g", Groep.class);
           groepen = q.getResultList();
           return groepen;
        }
        finally{
            em.close();
           
        }
    }
    
    
}
