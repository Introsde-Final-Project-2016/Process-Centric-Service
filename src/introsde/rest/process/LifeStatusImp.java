package introsde.rest.process;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.xml.ws.Holder;

import introsde.rest.process.Goals;
import introsde.rest.process.LifeStatus;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;

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

public class LifeStatusImp{

              
       // link to storage to update
       private static URI getUpdateURI() {
                return UriBuilder.fromUri("https://afternoon-bayou-12442.herokuapp.com/introsde/user/updateLs").build();
            }  
       // link to business layer for comparison of weight 
       private static URI getCompareURI() {
           return UriBuilder.fromUri("https://immense-mountain-93541.herokuapp.com/introsde/user/compare").build();
       } 

       public static LifeStatus updateLifeStatus(int lsId, LifeStatus ls) throws Exception {
    	    ClientConfig clientConfig = new ClientConfig();
            Client client = ClientBuilder.newClient(clientConfig);
            WebTarget service = client.target(getUpdateURI());
            Response response;
    	   
           response = service.path(String.valueOf(lsId)).request().accept(MediaType.APPLICATION_JSON).put(Entity.json(ls));
           LifeStatus l = response.readEntity(LifeStatus.class);
           
           WebTarget service2 = client.target(getCompareURI());
           Response response2;  
           response2 = service2.request().accept(MediaType.APPLICATION_JSON).get();   
           String[] result = response2.readEntity(String[].class);
           
           // these are to see and check while coding
           System.out.println("Gender value= "+result[3]);
		   System.out.println("BMI value= "+result[2]);
		   System.out.println("Extra weight value= "+result[4]);
		   
		   ls.setMeasureType("bmi");
		   //result[2] has the value of bmi from external api
		   ls.setValue(result[2]);
		   // it updates the bmi automatically each time when you change the life status
		   updateBmi(3,ls);    
			
			Goals g = new Goals();
			
			// lets set a new goal for losing or gaining KG
			// according to the result[3] which has "M" "L" or "O" 
			// "M" means you have extra weight
			// "L" means you need to gain kg
			// "O" means your weight is okay according to weight and height
			// update the first goal 
			if(result[3].equals("M"))
			{
				g.setGoalName("Lose");
				g.setGoalValue(result[4]);
				GoalsImp.updateGoalStatus(1, g);
			}
			else if(result[3].equals("L"))
			{
				g.setGoalName("Gain");
				g.setGoalValue(result[4]);
				GoalsImp.updateGoalStatus(1, g);
			}
			// if everything okay you do not need to lose weight :) 
			else if(result[3].equals("O"))
			{
				g.setGoalName("Lose");
				g.setGoalValue("0");
				GoalsImp.updateGoalStatus(1, g);
			}

			return l;
           
       }
       
       // to update bmi I used another function because othervise it would be infinite loop 
       public static void updateBmi(int lsId, LifeStatus ls) throws Exception {
   	      ClientConfig clientConfig = new ClientConfig();
          Client client = ClientBuilder.newClient(clientConfig);
          WebTarget service = client.target(getUpdateURI());
          Response response;
          response = service.path("3").request().accept(MediaType.APPLICATION_JSON).put(Entity.json(ls));
     	          
       }
       
       
       
       
}
