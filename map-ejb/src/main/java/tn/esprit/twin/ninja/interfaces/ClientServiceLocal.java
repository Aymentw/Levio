package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Local;
import javax.mail.MessagingException;

import tn.esprit.twin.ninja.persistence.*;

import java.util.List;

@Local
public interface ClientServiceLocal {
	
	public void addRequest(int clientId, Request request) throws MessagingException;
	public void sendMessageToRessource(Message message, int currentClient, int resourceId) throws MessagingException;
	public void addClient(Client c);
	public void deleteClient(int idClient);
	public List<Conversation> getOpenedConversations(int clientId);
	public void respondToAMessage(int conversationId,int currencClient, Message message) throws MessagingException;
	public List<Conversation> getConversationByType(int currentClient, MessageType messageType);
	public List<Conversation> getConversationBySubject(String subject, int currentClient);
	

}
