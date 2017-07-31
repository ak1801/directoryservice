package com.profactus.directory.service.rest;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.profactus.directory.configuration.AppConfig;
import com.profactus.directory.model.Bird;
import com.profactus.directory.service.BirdDirectoyService;
 
@Path("/birds")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class BirdDirectoryResource {
 

	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private BirdDirectoyService service = (BirdDirectoyService) context.getBean("birdDirectoryService");

	public void setService(BirdDirectoyService service) {
		this.service = service;
	}
@GET
    public Bird[] getBirds() {
		return service.getAllVisibleBirds();
	}
	
	@GET	
	@Path("/{id}")
	public Response getBirdsById(@PathParam("id") String id) {
		Bird bird = service.getBirdById(id);
		if(bird != null) {
			if(bird.isVisible()) {
				return Response.status(200).entity(bird).build();
			} else return Response.status(200).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@POST
	public Response getMsg(Bird bird) {
		service.addBird(bird);
		return Response.status(200).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response getMsg(@PathParam("id") String id) {
		service.deleteBird(id);
		return Response.status(204).build();
 
	}
 
}