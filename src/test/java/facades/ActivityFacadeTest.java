/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Activity;
import errorhandling.NotCityByThatNameException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import utils.EMF_Creator;

/**
 *
 * @author simon
 */
@Disabled
public class ActivityFacadeTest {

    private static EntityManagerFactory emf;
    private static ActivityFacade facade;

    public ActivityFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = ActivityFacade.getActivityFacade(emf);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Activity").executeUpdate();
        em.createQuery("DELETE FROM WeatherInfo").executeUpdate();
        em.createQuery("DELETE FROM CityInfo").executeUpdate();

        em.getTransaction().commit();
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        Activity act = new Activity("Løb", 0, 0, "", "admin");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(act);
        em.getTransaction().commit();
    }

    @AfterEach
    public void tearDown() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Activity").executeUpdate();
        em.createQuery("DELETE FROM WeatherInfo").executeUpdate();
        em.createQuery("DELETE FROM CityInfo").executeUpdate();

        em.getTransaction().commit();
    }

    /**
     * Test of saveActivity method, of class ActivityFacade.
     */
    @Test
    public void testAmoutnOfActivities() throws NotCityByThatNameException, Exception {

        int expected = 1;

        int actual = (int) facade.getAmountOfActivities();

        assertEquals(expected, actual);
        assertNotEquals(expected, 2);
        // TODO review the generated test code and remove the default call to fail.

    }

    @Test
    public void testSaveActivity() throws NotCityByThatNameException, Exception {

        Activity act = new Activity("Løb", 0, 0, "", "admin");

        int id = facade.saveActivity(act.getType(), act.getDuration(), act.getDistance(), act.getComment(), "Virum", "admin");

        int exspected = 2;

        int actual = (int) facade.getAmountOfActivities();

        assertEquals(exspected, actual);

        Activity actualActivity = facade.getActivityById(id);

        assertEquals(act.getType(), actualActivity.getType());
    }
}
