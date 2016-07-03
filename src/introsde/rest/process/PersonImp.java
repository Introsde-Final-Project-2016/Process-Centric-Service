package introsde.rest.process;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.xml.ws.Holder;
import introsde.rest.process.Person;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;

public class  PersonImp{

      // get person details from the storage
       static ClientConfig clientConfig = new ClientConfig();
       static Client client = ClientBuilder.newClient(clientConfig);
       static WebTarget service = client.target(getBaseURI());
       static javax.ws.rs.core.Response response;
       
       private static URI getBaseURI() {
                return UriBuilder.fromUri("https://afternoon-bayou-12442.herokuapp.com/introsde/user/getDetail").build();
            }

       public static Person getUserDetail() throws Exception {
    	   System.out.println("Getting user details...");
            response = service
    				.request()
    				.accept(MediaType.APPLICATION_JSON)
    				.get();
    		          
    		Person body = response.readEntity(Person.class);
    		Person per = body;
    		return per;
       }   
       
}
