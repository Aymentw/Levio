package tn.esprit.twin.ninja.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.mail.MessagingException;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import tn.esprit.twin.ninja.interfaces.ClientServiceLocal;
import tn.esprit.twin.ninja.interfaces.UserServiceLocal;
import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Request;
import tn.esprit.twin.ninja.persistence.Skill;

import java.util.Set;

@Path("User")
@RequestScoped
public class UserResource {

	@EJB(beanName = "UserService")
	UserServiceLocal userLocal;



	/* Mohamed */

	@GET
	@Path("/treatClientRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String treatClientRequest(@QueryParam("userId") int userId, @QueryParam("requestId") int requestId) {
		userLocal.treatClientRequest(userId, requestId);
		return "Treating request ...";
	}

	/* Mohamed */
	@POST
	@Path("/getAllRequests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRequests() {
		return Response.ok(userLocal.getAllRequests()).build();
	}

	/* Mohamed */

	@POST
	@Path("/getTreatedRequests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTreatedRequests() {
		return Response.ok(userLocal.getTreatedRequests()).build();
	}

	/* Mohamed */

	@POST
	@Path("/getUnTreatedRequests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnTreatedRequests() {
		return Response.ok(userLocal.getUnTreatedRequests()).build();
	}

	/* Mohamed */
	@POST
	@Path("/getResourcesBySkills")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getResourcesBySkills(Set<Skill> skills) {
		return Response.ok(userLocal.getRessourceBySkills(skills)).build();
	}
	
	/* Mohamed */
	@GET
	@Path("/deleteTreatedRequets")
	public void deleteTreatedRequests() {
		userLocal.deleteTreatedRequests();
	}
}
