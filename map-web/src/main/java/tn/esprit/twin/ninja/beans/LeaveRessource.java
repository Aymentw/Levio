package tn.esprit.twin.ninja.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.twin.ninja.interfaces.LeaveServiceLocal;
import tn.esprit.twin.ninja.interfaces.RessourceServiceLocal;

@Path("leave")
@RequestScoped
public class LeaveRessource {

	@EJB(beanName = "LeaveService")
	LeaveServiceLocal leaveService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLeaves() {

		if (leaveService.getAllLeave() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (leaveService.getAllLeave().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			leaveService.getAllLeave();
		return Response.ok(leaveService.getAllLeave()).build();

	}

}
