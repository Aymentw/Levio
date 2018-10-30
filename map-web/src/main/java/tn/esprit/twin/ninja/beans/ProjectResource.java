package tn.esprit.twin.ninja.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin.ninja.interfaces.ProjectServiceLocal;
import tn.esprit.twin.ninja.interfaces.recruitement.ApplicationServiceLocal;
import tn.esprit.twin.ninja.persistence.Project;

@Path("project")
@RequestScoped
public class ProjectResource {

	@EJB
	ProjectServiceLocal projectLocal;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addProject(Project p){
			
		projectLocal.addProject(p);
		return "project added";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("update")
	public String updateProject(Project p){
			
		projectLocal.updateProject(p);
		return "project updated";
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{projectId}")
	public String DeleteProject(@PathParam ("projectId") int projectId) {
		
		projectLocal.deleteProject(projectId);
		return "project deleted";
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("affect/{projectId}/{clientId}")
	public String affectProjecttoClient(@PathParam ("projectId")int projectId, @PathParam ("clientId")int clientId)
	{
		projectLocal.affectProjecttoClient(projectId, clientId);
		return "ok";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects")
	public Response getAllProjects()
	{
		if (!projectLocal.getAllProject().isEmpty())
			return Response.ok(projectLocal.getAllProject()).build();
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projectbyclient/{idClient}")
	public Response getProjectByClient(@PathParam("idClient") int idClient) {
		if (projectLocal.getProjectByClient(idClient) == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(projectLocal.getProjectByClient(idClient)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projectbyadress/{adress}")
	public Response getProjectByAdress(@PathParam("adress") String adress) {
		if (projectLocal.getProjectByAdress(adress) == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(projectLocal.getProjectByAdress(adress)).build();
	}
}
