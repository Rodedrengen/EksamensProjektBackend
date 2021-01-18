/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Activity;
import entities.CityInfo;
import entities.WeatherInfo;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.HttpUtils;

/**
 *
 * @author simon
 */
public class ActivityFacade {

    private static ActivityFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ActivityFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ActivityFacade getActivityFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ActivityFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void saveActivity(String type, int duration, int distance, String comment, String city, String userName) {
        EntityManager em = getEntityManager();

        Activity act = new Activity(LocalDateTime.now(), type, duration, distance, comment, userName);

        String cityUrl = "https://dawa.aws.dk/steder?hovedtype=Bebyggelse&undertype=by&prim%C3%A6rtnavn=";
        String weatherStart = "https://vejr.eu/api.php?location=";
        String weatherEnd = "&degree=C";

        String cityJson = "";
        CityInfo cityinfo = null;
        String weatherJson = "";
        WeatherInfo weatherInfo = null;
        em.getTransaction().begin();
        
        try {
            TypedQuery<CityInfo> query = em.createQuery("SELECT ci FROM CityInfo ci WHERE ci.name = :name ", CityInfo.class);
            query.setParameter("name", city);
            cityinfo = query.getSingleResult();

        } catch (Exception e) {
            System.out.println(e + "fejl");
        }

        if (cityinfo == null) {

            try {
                cityJson = HttpUtils.fetchData(cityUrl + city);
            } catch (Exception e) {
                System.out.println(e);
            }

            JsonArray arr = new JsonParser().parse(cityJson).getAsJsonArray();

            for (int i = 0; i < arr.size(); i++) {
                JsonObject jsonObject = arr.get(i).getAsJsonObject();
                String name = jsonObject.get("primÃ¦rtnavn").getAsString();
                int population = jsonObject.getAsJsonObject("egenskaber").get("indbyggerantal").getAsInt();
                double lon = jsonObject.getAsJsonArray("visueltcenter").get(0).getAsDouble();
                double lat = jsonObject.getAsJsonArray("visueltcenter").get(1).getAsDouble();
                String municipality = jsonObject.getAsJsonArray("kommuner").get(0).getAsJsonObject().get("navn").getAsString();

                cityinfo = new CityInfo(name, lon, lat, municipality, population);
            }

            act.setCityInfo(cityinfo);
        } else {
            act.setCityInfo(cityinfo);
        }

        try {
            weatherJson = HttpUtils.fetchData(weatherStart + city + weatherEnd);
        } catch (Exception e) {
            System.out.println(e);
        }

        JsonObject jsonObject = new JsonParser().parse(weatherJson).getAsJsonObject();
        int temp = jsonObject.getAsJsonObject("CurrentData").get("temperature").getAsInt();
        String skyText = jsonObject.getAsJsonObject("CurrentData").get("skyText").getAsString();
        int humidity = jsonObject.getAsJsonObject("CurrentData").get("humidity").getAsInt();
        String windText = jsonObject.getAsJsonObject("CurrentData").get("windText").getAsString();

        weatherInfo = new WeatherInfo(temp, skyText, humidity, windText);
        act.setWeatherInfo(weatherInfo);

        try {
            em.persist(act);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }
}
