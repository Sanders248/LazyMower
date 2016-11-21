/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.jaxrslabs.appimower;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * REST Web Service
 *
 * @author Alex
 */
@Path("Action")
public class ApiMower {
    private final String logFolder = "%h/LogMowers.log"; //path for the logs, default: User/Home
    
    private Logger logger = Logger.getLogger("MowersLog");  
    private FileHandler fh;  
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AppiMower
     */
    public ApiMower() {
        try { 
            fh = new FileHandler(logFolder); 
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);   

        } catch (SecurityException | IOException e) {  
            e.printStackTrace();  
        }  
    }

    @GET
    @Path("{id}/{action}")
    @Produces("application/xml")
    public Response applyActionForMowers(@PathParam("id") String id, @PathParam("action") String action) {
        logger.log(Level.INFO, "mower: {0}:{1}", new Object[]{id, action});
        
        return Response
            .status(Status.OK)
            .build();
    }

}
