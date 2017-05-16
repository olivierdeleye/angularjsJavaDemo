/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngdemo.dao;

import java.util.List;
import ngdemo.domain.KarType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olivier deleye
 */
public class KarDAOTest {
    
    public KarDAOTest() {
    }
 
    /**
     * Test of createKar method, of class KarDAO.
     */
    @Test
    public void testCreateKar() {
        System.out.println("createKar");
        String karNr = "";
        KarType karType = null;
        boolean isVrij = false;
        int aantalPlaatsen = 0;
        KarDAO instance = new KarDAO();
        instance.createKar(karNr, karType, isVrij, aantalPlaatsen);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findKar method, of class KarDAO.
     */
    @Test
    public void testFindKar() {
        System.out.println("findKar");
        String karNr = "";
        KarDAO instance = new KarDAO();
        instance.findKar(karNr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listLegeKarrenByType method, of class KarDAO.
     */
    @Test
    public void testListLegeKarrenByType() {
        System.out.println("listLegeKarrenByType");
        KarType type = null;
        KarDAO instance = new KarDAO();
        List expResult = null;
        List result = instance.listLegeKarrenByType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listKarrenByType method, of class KarDAO.
     */
    @Test
    public void testListKarrenByType() {
        System.out.println("listKarrenByType");
        KarType type = null;
        KarDAO instance = new KarDAO();
        List expResult = null;
        List result = instance.listKarrenByType(type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetKar method, of class KarDAO.
     */
    @Test
    public void testResetKar() {
        System.out.println("resetKar");
        String karNr = "";
        KarDAO instance = new KarDAO();
        instance.resetKar(karNr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterVrijePlaatsen method, of class KarDAO.
     */
    @Test
    public void testAlterVrijePlaatsen() {
        System.out.println("alterVrijePlaatsen");
        KarType type = null;
        int nieuweCapaciteit = 0;
        KarDAO instance = new KarDAO();
        instance.alterVrijePlaatsen(type, nieuweCapaciteit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
