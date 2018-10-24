package tn.esprit.twin.ninja.services;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.twin.ninja.interfaces.ClientServiceRemote;
import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Request;

@Stateless
public class ClientService implements ClientServiceRemote{

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addRequest(int clientId, Request request) {
		Client client = em.find(Client.class, clientId);
		client.getRequests().add(request);
		//it's not mandatory to add client.merge(request) because the object client is laready in the persistance context.
	}

	@Override
	public void sendMessageToRessource(Message message, int ressourceId) {
		Resource resource = em.find(Resource.class, ressourceId);
		message.setTarget("RESOURCE");
		em.merge(message);
		//send an email to the resource email with the message body 
	}

	
}
