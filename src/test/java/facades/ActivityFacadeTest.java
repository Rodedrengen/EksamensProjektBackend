/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.time.LocalDateTime;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author simon
 */
public class ActivityFacadeTest {

    private static EntityManagerFactory emf;
    private static ActivityFacade facade;

    public ActivityFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = ActivityFacade.getActivityFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getActivityFacade method, of class ActivityFacade.
     */
    @Test
    public void testGetActivityFacade() {
        System.out.println("getActivityFacade");
        EntityManagerFactory _emf = null;
        ActivityFacade expResult = null;
        ActivityFacade result = ActivityFacade.getActivityFacade(_emf);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of saveActivity method, of class ActivityFacade.
     */
    @Test
    public void testSaveActivity() {
        System.out.println("saveActivity");
        LocalDateTime exerciseDate = null;
        String type = "Svøming";
        int duration = 10;
        int distance = 100;
        String comment = "Halløg";
        String city = "Hillerød";
        ActivityFacade instance = ActivityFacade.getActivityFacade(emf);
        instance.saveActivity(type, duration, distance, comment, city, "admin");
        
        // TODO review the generated test code and remove the default call to fail.
       
    }

}
