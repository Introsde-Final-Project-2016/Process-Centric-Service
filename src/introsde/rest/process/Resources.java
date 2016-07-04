package introsde.rest.process;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import introsde.rest.process.*;
import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import org.json.*;

@Stateless // Used only if the the application is deployed in a Java EE container
@LocalBean // Used only if the the application is deployed in a Java EE container
@Path("/user")


public class Resources {
	
		// update the goal information
	    @PUT
	    @Produces({MediaType.APPLICATION_JSON})
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Path("/updategoal/{goalId}")
	    public Response updateGoal(@PathParam("goalId") int goalId, Goals goal) throws Exception {
	        System.out.println("Updating goal...");
	        Goals g = GoalsImp.updateGoalStatus(goalId, goal);	    
	        return Response.ok(g).build();
	    }

	    // update the life status information
	    @PUT
	    @Produces({MediaType.APPLICATION_JSON})
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Path("/updateLs/{LsId}")
	    public Response updateLs(@PathParam("LsId") int LsId, LifeStatus ls) throws Exception {
	        System.out.println("Updating life status...");
	        LifeStatus l = LifeStatusImp.updateLifeStatus(LsId, ls);	  
	        return Response.ok(l).build();
	    }
	       
//	    @PUT
//	    @Produces({MediaType.APPLICATION_JSON})
//	    @Consumes({MediaType.APPLICATION_JSON})
//	    @Path("/updateUser/{userId}")
//	    public void updateUsr(@PathParam("userId") int uId, Person user) throws Exception {
//	      System.out.println("Updating goal..."+uId+"---"+user.getAge()+"---"+user.getEmail()+"---"+user.getGender()+"---"+user.getLastname()+"---"+user.getName());
//	      PeopleClient.updateUserStatus(uId, user.getName(), user.getLastname(), user.getEmail(), user.getGender(), user.getAge());   
//	    }
	
	
}
       
