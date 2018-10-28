package tn.esprit.twin.ninja.beans;

import java.text.ParseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin.ninja.interfaces.MandateServicesLocal;

@Path("mandate")
@RequestScoped
public class MandateResource {
	@EJB(beanName = "MandateServices")
	MandateServicesLocal mandateService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRessources() throws ParseException {

		if (!mandateService.getAll().isEmpty())
			return Response.ok(mandateService.getAll(),MediaType.APPLICATION_JSON).build();
		return Response.status(Status.NOT_FOUND).build();

	}

}
