package tn.esprit.twin.ninja.services;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.ClientServiceLocal;
import tn.esprit.twin.ninja.interfaces.ClientServiceRemote;
import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Project;
import tn.esprit.twin.ninja.persistence.Request;
import tn.esprit.twin.ninja.persistence.Ressource;

@Stateless
public class ClientService implements ClientServiceRemote, ClientServiceLocal{

	@PersistenceContext(unitName="LevioMap-ejb")
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

	@Override
	public void addClient(Client c) {
		em.persist(c);
		
	}

	@Override
	public void deleteClient(Client c) {
		//em.remove(em.find(Client.class, idClient));
		Client client = em.find(Client.class, c.getId());
		client.setArchived(true);
	}

	@Override
	public void updateClient(Client c) {
		Client client = em.find(Client.class, c.getId());
		client.setName(c.getName());
		client.setArchived(c.isArchived());
		client.setCategory(c.getCategory());
		client.setType(c.getType());
	}

	@Override
	public List<Client> getAllClients() {
		return em.createQuery("SELECT c FROM Client c where archived=false", Client.class).getResultList();
	}

	@Override
	public Client getClientById(int idClient) {
		return em.createQuery("SELECT c FROM Client c WHERE c.id=:idClient and archived=false", Client.class)
				.setParameter("idClient", idClient).getSingleResult();
	}
	
}
