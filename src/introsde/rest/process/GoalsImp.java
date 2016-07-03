package introsde.rest.process;

import java.util.List;



import java.util.ArrayList;
import java.util.LinkedList;
import javax.xml.ws.Holder;

import introsde.rest.process.Goals;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response;

import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
// set relations for updating goal
// connect to storage
public class GoalsImp{

      
       static ClientConfig clientConfig = new ClientConfig();
       static Client client = ClientBuilder.newClient(clientConfig);
       static WebTarget service = client.target(getBaseURI());
       static Response response;
           
       
       private static URI getBaseURI() {
                return UriBuilder.fromUri("https://afternoon-bayou-12442.herokuapp.com/introsde/user/updategoal").build();
            }
       
       public static Goals updateGoalStatus(int goalId, Goals goal) throws Exception {
           response = service.path(String.valueOf(goalId)).request().accept(MediaType.APPLICATION_JSON).put(Entity.json(goal));
           response = service.path(String.valueOf(goalId)).request().accept(MediaType.APPLICATION_JSON).get();
           Goals g = response.readEntity(Goals.class);
           return g;
       }
       
}
