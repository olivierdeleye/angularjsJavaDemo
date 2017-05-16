/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ngdemo.domain.Barcode;
import ngdemo.domain.Kar;
import ngdemo.domain.Order;
import ngdemo.domain.OrderLijn;
import ngdemo.util.PersistenceManager;

/**
 *
 * @author olivier deleye
 */
public class OrderDAO {

    /**
     *
     */
    public OrderDAO() { 
         
    }
    
    /*
    CRUD
    */
    
    /**
     * Create Order
     * @param orderNr String
     * @param leverDatum LocalDate
     * @param planningStatus int
     * @param afgewerkt boolean
     * @param orderlijn  OrderLijn
     * @return Order persisted order
     */
    public Order createOrder(String orderNr, LocalDate leverDatum, int planningStatus, boolean afgewerkt, OrderLijn orderlijn){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Order order = new Order();
       
            order.setOrderNr(orderNr);
            order.setLeverdatum(leverDatum);
            order.setStatus(planningStatus);
            order.setAfgewerkt(afgewerkt);
            if(orderlijn != null){
                 order.addOrderLijn(orderlijn);
            }      
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
         
            return order; 
        }
        finally{
            em.close();
            
        }     
    }
    
    /**
     * Find order by orderNr
     * @param orderNr String
     * @return Order 
     */
    public Order findOrder(String orderNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
           Order order = em.find(Order.class, orderNr);
           return order; 
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * Update Order
     * @param orderNr String
     * @param leverdatum LocalDate
     * @param planningStatus int
     * @param afgewerkt boolean
     * @param orderlijn OrderLijn
     * @return updated Order
     */
    public Order updateOrder(String orderNr, LocalDate leverdatum, int planningStatus, boolean afgewerkt, OrderLijn orderlijn){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Order order = em.find(Order.class, orderNr);
            if(order != null){
                em.getTransaction().begin();
                order.setLeverdatum(leverdatum);
                order.setStatus(planningStatus);
                order.setAfgewerkt(afgewerkt);
                if(orderlijn != null){
                   order.addOrderLijn(orderlijn);
                }
                em.getTransaction().commit();
            }
            return order;
        }
        finally{
            em.close();
         
        }
    }
    
    /**
     * Update Order afgewerkt
     * @param orderNr String
     * @param afgewerkt boolean 
     */
    public void updateAfgewerkt(String orderNr, boolean afgewerkt){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
            
        try{
            
           Order order = em.find(Order.class, orderNr);
           
           if(order != null){
                em.getTransaction().begin();
                order.setAfgewerkt(afgewerkt);
                em.getTransaction().commit();
           } 
        }
        finally{
            em.close();
          
        }
    }
    
    
    public int deleteStatus70Orders(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        LocalDate vervalDatum = LocalDate.now().minusMonths(1);
        int deletedCount = 0;
        try{
            Query query = em.createQuery("SELECT o FROM Order o WHERE o.status = 70 AND o.leverdatum < :vervalDatum");
            query.setParameter("vervalDatum", vervalDatum);
            List <Order> orders = query.getResultList();
            
            if(orders != null){
                for(Order o : orders){
                    em.getTransaction().begin();
                    em.remove(o);
                    em.getTransaction().commit();
                    deletedCount++;
                }
            }
            return deletedCount;
        }
        finally{
            em.close();
          
        }
    }
    /**
     * List alle orders
     * @return lijst van alle orders
     */
    public List <Order> listAllOrders(){
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
      
        try{
            List <Order> orders;
            Query query = em.createQuery("SELECT o FROM Order o");
            orders = query.getResultList();
            return orders; 
        }
        finally{
            em.close();
            
        }
    }
    
    /**
     * List alle afgewerkte orders
     * @return lijst van alle afgewerkte orders
     */
    public List<Order> listOrdersAfgewerkt(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{
            Query query = em.createNamedQuery("listOrdersAfgewerkt");
            List <Order> orders = query.getResultList();
            return orders; 
        }
        finally{
            em.close();
            
        }
    }
    
    /**
     * List alle orderNrs
     * @return lijst van alle orderNrs String
    */
    public List <String> listAllOrdersNrs(){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <String> orders;
         
        try{
           Query query = em.createQuery("SELECT o.orderNr FROM Order o");
           orders = query.getResultList();
           return orders; 
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * List alle orders adhv datum afgewerkt en status
     * @param datumVan LocalDate
     * @param datumTot LocalDate
     * @param afgewerkt boolean
     * @param status int
     * @return lijst van orders adhv datum afgewerkt en status
     */
    public List <Order> listOrdersByDatumAndAfgewerktAndStatus(LocalDate datumVan, LocalDate datumTot, boolean afgewerkt, int status){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <Order> orders;
        
        try{
            Query query = em.createNamedQuery("listOrdersByDatumAndAfgewerktAndStatus");
            query.setParameter("datumVan", datumVan);
            query.setParameter("datumTot", datumTot);
            query.setParameter("afgewerkt", afgewerkt);
            query.setParameter("status", status);
            orders = query.getResultList();
            return orders;
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * List aller orders adhv datum afgewerkt
     * @param datumVan LocalDate
     * @param datumTot LocalDate
     * @param afgewerkt boolean
     * @return lijst van orders adhv datum afgewerkt
     */
    public List <Order> listOrdersByDatumAndAfgewerkt(LocalDate datumVan, LocalDate datumTot, boolean afgewerkt){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <Order> orders;
        
        try{
            Query query = em.createNamedQuery("listOrdersByDatumAndAfgewerkt");
            query.setParameter("datumVan", datumVan);
            query.setParameter("datumTot", datumTot);
            query.setParameter("afgewerkt", afgewerkt);
            orders = query.getResultList();
            return orders;
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * List alle orders adhv leverdatum
     * @param leverdatum LocalDate
     * @return lijst van orders volgens leverdatum
     */
    public List <Order> listOrdersByLeverdatum (LocalDate leverdatum){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <Order> orders;
        
        try{
            Query query = em.createNamedQuery("listOrdersByLeverdatum");
            query.setParameter("leverdatum", leverdatum);
            orders = query.getResultList();
            return orders; 
        }
        finally{
            em.close();
           
        }
    }
    
      /**
     * List alle orders adhv groepNaam
     * @param groepNaam String leveranciergroep vb 'LEDRO'
     * @return lijst van orders volgens groepNaam
     */
    public List <Order> listOrdersByGroepNaam (String groepNaam){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        List <Order> orders = new ArrayList();
        List <Order> tempOrders = new ArrayList<>();
        
        try{
            Query query = em.createQuery("SELECT o FROM Order o");
            tempOrders = query.getResultList();
            if(!tempOrders.isEmpty()){
                for(Order o : tempOrders){
                    if(o.getOrderlijnen() != null && !o.getOrderlijnen().isEmpty()){
                        OrderLijn ol = (OrderLijn) o.getOrderlijnen().iterator().next();
                        if(ol.getLeverancier().getGroep().getGroepNaam().equals(groepNaam) && !o.isAfgewerkt()){
                            orders.add(o);
                        }
                    }           
                }
            }
            return orders; 
        }
        finally{
            em.close();
           
        }
    }
    
    /**
     * Volledig order verplaatsen van kar A naar kar B
     * @param orderNr String
     * @param vanKarNr String
     * @param naarKarNr String
     */
    public void verplaatsOrder(String orderNr, String vanKarNr, String naarKarNr){
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        try{            
            em.getTransaction().begin();
            Order o = em.find(Order.class, orderNr);
            Kar vanKar = em.find(Kar.class, vanKarNr);
            Kar naarKar = em.find(Kar.class, naarKarNr);
            
            if(o != null && vanKar != null && naarKar != null){
                naarKar.setIsVrij(false);
                o.removeKar(vanKar);
                o.addKar(naarKar); 
                Set <OrderLijn> oLijnen = o.getOrderlijnen();
                for(OrderLijn ol : oLijnen){
                    if(ol.isFront()){
                        Set <Barcode> barcodes = ol.getBarcodes();
                        for(Barcode b : barcodes){
                            if(b.getKar() != null && b.getKar().equals(vanKar)){
                               vanKar.setVrijePlaatsen(vanKar.getVrijePlaatsen() + 1);
                               naarKar.setVrijePlaatsen(naarKar.getVrijePlaatsen() - 1);
                               b.setKar(naarKar); 
                            }   
                        } 
                    }
                }
                int aantalResterendeFronten = vanKar.getAantalFronten(vanKar.getKarType().getType());
                if(aantalResterendeFronten == 0){
                    vanKar.setIsVrij(true);
                }
            }
            em.getTransaction().commit();        
        }
        finally{
            em.close();

        }
       
    }      
    
    
}

    
  
    
