package tn.esprit.webservices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin.ninja.interfaces.RessourceServiceLocal;
import tn.esprit.twin.ninja.persistence.Ressource;

@Path("ressource")
@RequestScoped
public class RessourceRessource {

	@EJB(beanName = "RessourceService")
	RessourceServiceLocal ressourceService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRessource(Ressource r) {
		ressourceService.addRessource(r);
		return Response.status(Status.CREATED).entity(r).build();
	}

	public Response updateRessource(Ressource res) {
		ressourceService.updateRessource(res);
		return Response.status(Status.OK).entity(res).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRessources() {

		if (!ressourceService.getAllRessources().isEmpty())
			return Response.ok(ressourceService.getAllRessources()).build();
		return Response.status(Status.NOT_FOUND).build();

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRessource(Ressource res) {
		ressourceService.deleteRessource(res.getId());
		return Response.status(Status.OK).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{ressourceId}")
	public Response getRessourceById(@PathParam("ressourceId") int ressourceId) {
		if (ressourceService.getRessourceById(ressourceId) == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(ressourceService.getRessourceById(ressourceId)).build();

	}

}
