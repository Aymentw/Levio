package tn.esprit.twin.ninja.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import tn.esprit.twin.ninja.interfaces.ClientServiceLocal;
import tn.esprit.twin.ninja.persistence.Client;

@Path("client")
@RequestScoped
public class ClientResource {

	@EJB(beanName = "ClientService")
	ClientServiceLocal clientLocal;
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addClient(Client c){
			
		clientLocal.addClient(c);
		return "client added";
	}

}
