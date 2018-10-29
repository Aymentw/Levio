package tn.esprit.twin.ninja.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin.ninja.interfaces.RessourceServiceLocal;
import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.Skill;

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

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRessource(Ressource res) {
		if (ressourceService.updateRessource(res))
			return Response.status(Status.ACCEPTED).build();
		return Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("archiver")
	public Response deleteRessource(Ressource res) {
		if (ressourceService.deleteRessource(res))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRessources(@QueryParam(value = "id") Integer id,
			@QueryParam(value = "FirstName") String FirstName) {
		if ((id == null) && (FirstName == null)) {
			if (ressourceService.getAllRessources() == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (ressourceService.getAllRessources().size() == 0)
				return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

			else
				return Response.ok(ressourceService.getAllRessources()).build();

		} else if ((id != null) && (FirstName == null)) {

			if (ressourceService.getRessourceById(id) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			else
				return Response.ok(ressourceService.getRessourceById(id)).build();

		} else if ((id == null) && (FirstName != null)) {

			if (ressourceService.getRessourceByName(FirstName) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (ressourceService.getRessourceByName(FirstName).size() == 0)
				return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

			else
				return Response.ok(ressourceService.getRessourceByName(FirstName)).build();

		} else
			return Response.status(Response.Status.BAD_REQUEST).entity("Requete eronnée").build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{ressourceId}/{skillId}")
	public Response addSkillsToRessource(@PathParam("ressourceId") int ressourceId, @PathParam("skillId") int skillId) {

		if (ressourceService.addSkills(ressourceId, skillId))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateSkills")
	public Response updateRessourceSkill(Skill skill) {

		if (ressourceService.updateSkills(skill))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{skillId}")
	public Response deleteSkill(@PathParam("skillId") int skillId) {
		if (ressourceService.deleteSkills(skillId))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("evaluate")
	public Response evaluateSkills(Skill skill) {

		if (ressourceService.evaluateSkills(skill))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addLeave/{ressourceId}")
	public Response addLeave(@PathParam("ressourceId") int ressourceId, Leave l) {

		if (ressourceService.addLeave(ressourceId, l))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateLeave")
	public Response updateLeave(Leave l) {

		if (ressourceService.updateLeave(l))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("leave/{leaveId}")
	public Response deleteLeave(@PathParam("leaveId") int leaveId) {
		if (ressourceService.deleteLeave(leaveId))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/artp/{projectId}/{ressourceId}")
	public Response affectRessourceToProject(@PathParam("projectId") int projectId,
			@PathParam("ressourceId") int ressourceId) {

		if (ressourceService.affectRessourceToProject(projectId, ressourceId))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();

	}

}
