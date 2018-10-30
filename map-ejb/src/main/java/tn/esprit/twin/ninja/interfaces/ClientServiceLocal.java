package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Local;

import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Request;

@Local
public interface ClientServiceLocal {
	
	public void addRequest(int clientId, Request request);	
	public void sendMessageToRessource(Message message, int ressourceId);
	public void addClient(Client c);
	public void deleteClient(int idClient);
}
