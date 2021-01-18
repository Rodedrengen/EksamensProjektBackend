/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.User;
import facades.UserFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author simon
 */
@Path("user")
public class UserResource {

    Gson GSON = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade FACADE = UserFacade.getUserFacade(EMF);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public String getUserCount() {

        long count = FACADE.getUserCount();

        return "{\"count\":" + count + "}";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("register")
    public String registerUser(String jsonString) {
        JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        int age = json.get("userAge").getAsInt();
        int weight = json.get("userWeight").getAsInt();
        User user = new User(username, password, age, weight);

        FACADE.createUser(user);

        return "{\"msg\": \"Succes: \"}";
    }
}
