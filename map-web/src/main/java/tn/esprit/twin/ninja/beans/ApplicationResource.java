package tn.esprit.twin.ninja.beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import tn.esprit.twin.ninja.interfaces.recruitement.ApplicationServiceLocal;
import tn.esprit.twin.ninja.persistence.recruitment.Application;
import tn.esprit.twin.ninja.persistence.recruitment.State;

@Path("application")
@RequestScoped
public class ApplicationResource {
	@EJB(beanName = "ApplicationService")
	ApplicationServiceLocal ApplicationService;
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String aaaApplication(){
		//ApplicationService.addApplication(a);
			return "zzzz";

	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	public Application addApplication(){
		Application a = new Application(new Date(),State.accepted);
			return a;

	}
	
}
