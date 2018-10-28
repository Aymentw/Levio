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
		ressourceService.updateRessource(res);
		return Response.status(Status.ACCEPTED).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("archiver")
	public Response deleteRessource(Ressource res) {
		ressourceService.deleteRessource(res);
		return Response.status(Status.OK).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRessources() {

		if (!ressourceService.getAllRessources().isEmpty())
			return Response.ok(ressourceService.getAllRessources()).build();
		return Response.status(Status.NOT_FOUND).build();

	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{ressourceId}")
	public Response getRessourceById(@PathParam("ressourceId") int ressourceId) {
		if (ressourceService.getRessourceById(ressourceId) == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(ressourceService.getRessourceById(ressourceId)).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ByName")
	public Response getRessourceByFirstName(@QueryParam(value="FirstName")String FirstName) {
		return Response.ok(ressourceService.getRessourceByName(FirstName)).build();

	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{ressourceId}/{skillId}")
	public Response addSkillsToRessource(@PathParam("ressourceId") int ressourceId,@PathParam("skillId") int skillId) {
		
		ressourceService.addSkills(ressourceId, skillId);
		return Response.status(Status.OK).build();


	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateSkills")
	public Response updateRessourceSkill(Skill skill) {
		
		ressourceService.updateSkills(skill);
		return Response.status(Status.OK).build();

	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{skillId}")
	public Response deleteSkill(@PathParam("skillId") int skillId) {
		ressourceService.deleteSkills(skillId);
		return Response.status(Status.OK).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("evaluate")
	public Response evaluateSkills(Skill skill) {
		
		ressourceService.evaluateSkills(skill);
		return Response.status(Status.OK).build();


	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addLeave/{ressourceId}")
	public Response addLeave(@PathParam("ressourceId") int ressourceId,Leave l) {
		
		ressourceService.addLeave(ressourceId, l);
		return Response.status(Status.OK).build();


	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateLeave")
	public Response updateLeave(Leave l) {
		
		ressourceService.updateLeave(l);
		return Response.status(Status.OK).build();

	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("leave/{leaveId}")
	public Response deleteLeave(@PathParam("leaveId") int leaveId) {
		ressourceService.deleteLeave(leaveId);
		return Response.status(Status.OK).build();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/artp/{projectId}/{ressourceId}")
	public Response affectRessourceToProject(@PathParam("projectId") int projectId,@PathParam("ressourceId") int ressourceId) {
		
		ressourceService.affectRessourceToProject(projectId, ressourceId);
		return Response.status(Status.OK).build();


	}
	
	
	
	
	

}
