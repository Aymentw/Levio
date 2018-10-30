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
@Path("User")
@RequestScoped
public class UserResource {

	@EJB(beanName = "UserService")
	UserServiceLocal userLocal;
	
	
	
	@GET
	@Path("/treatClientRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String treatClientRequest(@QueryParam("userId") int userId, @QueryParam("requestId") int requestId) {
		userLocal.treatClientRequest(userId, requestId);
		return "Treating request ...";
	}
	
	
}
