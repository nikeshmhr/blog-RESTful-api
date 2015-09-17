package com.nikesh.restapi.blog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Test001 {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getTest(){
		return "Test";
	}
	
}
