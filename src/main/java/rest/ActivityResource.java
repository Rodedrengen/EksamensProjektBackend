/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import errorhandling.NotCityByThatNameException;
import facades.ActivityFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

/**
 *
 * @author simon
 */
@Path("activity")
public class ActivityResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ActivityFacade FACADE =  ActivityFacade.getActivityFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String saveFavourite(String json) throws NotCityByThatNameException {
        
        JsonObject jsonobj = JsonParser.parseString(json).getAsJsonObject();
        String comment = jsonobj.get("Activitycomment").getAsString();
        int distance = jsonobj.get("Activitydistance").getAsInt();
        int duration = jsonobj.get("Activityduration").getAsInt();
        String type = jsonobj.get("Activitytype").getAsString();
        String username = jsonobj.get("ActivityuserName").getAsString();
        String city = jsonobj.get("ActivityCity").getAsString();
        
        FACADE.saveActivity(type, duration, distance, comment, city, username);
        
        return "{\"msg\": \"Succes: \"}";
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getActivityCount() throws Exception {
        long count = FACADE.getAmountOfActivities();
        
        return "{\"count\":"+count+"}";  
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTwitchChannel(@PathParam("id") String userName) throws Exception {
        return Response.ok().entity(GSON.toJson(FACADE.getActivitiesByUserName(userName))).build();
    }
}
