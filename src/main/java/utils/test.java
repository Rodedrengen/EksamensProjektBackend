/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Activity;
import entities.CityInfo;
import entities.WeatherInfo;
import facades.ActivityFacade;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author simon
 */
public class test {

    /**
     * @param args the command line arguments
     */
    private static EntityManagerFactory emf;
    private static ActivityFacade facade;

    public static void main(String[] args) {

        emf = EMF_Creator.createEntityManagerFactory();
        facade = ActivityFacade.getActivityFacade(emf);

        // TODO code application logic here
        String type = "Svøming";
        int duration = 10;
        int distance = 100;
        String comment = "Halløg";
        String city = "Virum";
        String username = "admin";
        ActivityFacade instance = ActivityFacade.getActivityFacade(emf);
        instance.saveActivity(type, duration, distance, comment, city, username);
    }

}
