/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Activity;
import entities.Role;
import entities.User;
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
public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;

    public UserFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserFacade.getUserFacade(emf);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM User").executeUpdate();

        em.getTransaction().commit();
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        User user = new User("Simon", "1234", 24, 85);
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        
        em.getTransaction().begin();
        em.persist(userRole);
        em.persist(user);
        em.persist(adminRole);
        em.getTransaction().commit();
    }

    @AfterEach
    public void tearDown() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM User").executeUpdate();
        em.createQuery("DELETE FROM Role").executeUpdate();

        em.getTransaction().commit();
    }

    /**
     * Test of saveActivity method, of class ActivityFacade.
     */
    @Test
    public void testGetUserCount() throws NotCityByThatNameException, Exception {

        int expected = 1;

        int actual = (int) facade.getUserCount();

        assertEquals(expected, actual);

    }

    @Test
    public void testRegisterUser() throws NotCityByThatNameException, Exception {

        User user = new User("JohnDoe", "JohnDear", 99, 1000);

        facade.createUser(user);
        int expected = 2;
        int actual = (int) facade.getUserCount();

        assertEquals(expected,actual);

    }
}
