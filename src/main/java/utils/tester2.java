/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.CityInfo;

/**
 *
 * @author simon
 */
public class tester2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cityJson = "";
        CityInfo cityinfo = null;
        try {
                cityJson = HttpUtils.fetchData("https://dawa.aws.dk/steder?hovedtype=Bebyggelse&undertype=by&prim%C3%A6rtnavn=Roskilde");
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(cityJson);
            JsonArray arr = new JsonParser().parse(cityJson).getAsJsonArray();

            for (int i = 0; i < arr.size(); i++) {
                JsonObject jsonObject = arr.get(i).getAsJsonObject();
                String name = jsonObject.get("primÃ¦rtnavn").getAsString();
                int population = jsonObject.getAsJsonObject("egenskaber").get("indbyggerantal").getAsInt();
                int lon = jsonObject.getAsJsonArray("visueltcenter").get(0).getAsInt();
                int lat = jsonObject.getAsJsonArray("visueltcenter").get(1).getAsInt();
                String municipality = jsonObject.getAsJsonArray("kommuner").get(0).getAsJsonObject().get("navn").getAsString();

                cityinfo = new CityInfo(name, lon, lat, municipality, population);
            }
    }
    
}
