package tn.esprit.twin.ninja.services;

import tn.esprit.twin.ninja.communication.MailSender;
import tn.esprit.twin.ninja.interfaces.ClientServiceLocal;
import tn.esprit.twin.ninja.persistence.*;

import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Stateless
public class ClientService implements ClientServiceLocal {

	@PersistenceContext(unitName="LevioMap-ejb")
	private EntityManager em;

	@Override
	public void addRequest(int clientId, Request request) throws MessagingException {
		Client client = em.find(Client.class, clientId);
		client.getRequests().add(request);
		em.persist(request);
		em.flush();
		for(Skill s : request.getSkills()) {
			s.setRequest(request);
			em.persist(s);
			em.flush();
		}
		MailSender mailSender = new MailSender();
		String messageBody = "Dear Supervisor, <br>"
						   + "You have a new resource request from the client : " + client.getName() + ". <br>"
						   + "<b>Request details:</b> <br>"
						   + "Request context: " + request.getContext() + "<br>"
						   + "Resource type: " + request.getResourceType() + "<br>"
						   + "Delivery date: " + request.getDeliveryDate() + "<br>"
						   + "Resource profile: " + this.skillsToString(request.getSkills());
		try {
		mailSender.sendMessage(
				"smtp.gmail.com",
				"mohamed@pixelwilderness.com",
				"V4Vendetta",
				"587",
				"true",
				"true",
				"mohamed@pixelwilderness.com",
				"New Resource Request",
				messageBody
		);
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendMessageToRessource(Message message,int currentClient, int ressourceId) throws MessagingException {
		Ressource resource = em.find(Ressource.class, ressourceId);
		Client client = em.find(Client.class, currentClient);
		Conversation conversation = new Conversation();
		conversation.setFromUser(resource);
		conversation.setToUser(client);
		conversation.setState("open");
		em.persist(conversation);
		em.flush();
		message.setConversation(conversation);
		em.persist(message);
		em.flush();
		MailSender mailSender = new MailSender();
		mailSender.sendMessage(
				"smtp.gmail.com",
				"mohamed@pixelwilderness.com",
				"V4Vendetta",
				"587",
				"true",
				"true",
				resource.getEmail(),
				message.getSubject() + ": " + message.getType(),
				message.getMessage()
		);
	}

	@Override
	public List<Conversation> getOpenedConversations(int clientId) {
		return em.createQuery("SELECT c FROM Conversation c where c.state = 'open' and (c.fromUser.id = :user OR c.toUser.id = :user)", Conversation.class).setParameter("user", clientId).getResultList();
	}

	@Override
	public void respondToAMessage(int conversationId,int currencClient, Message message) throws MessagingException {
		Conversation conversation = em.find(Conversation.class, conversationId);
		Client cc = em.find(Client.class, currencClient);
		String recipient = (conversation.getToUser().getEmail().equals(cc.getEmail())) ? conversation.getFromUser().getEmail() : conversation.getToUser().getEmail();
		message.setConversation(conversation);
		em.persist(message);
		MailSender mailSender = new MailSender();
		mailSender.sendMessage(
				"smtp.gmail.com",
				"mohamed@pixelwilderness.com",
				"V4Vendetta",
				"587",
				"true",
				"true",
				recipient,
				message.getSubject()+ ": " + message.getType(),
				message.getMessage()
		);
	}

	@Override
	public List<Conversation> getConversationByType(int currentClient, MessageType messageType) {
		Client cc = em.find(Client.class, currentClient);
		return  em.createQuery("SELECT  m.conversation from Message m where m.conversation.state = 'open' and m.type = :msgType and (m.conversation.toUser.email = :user or m.conversation.fromUser.email = :user) order by m.createDate", Conversation.class).setParameter("msgType" , messageType).setParameter("user", cc.getEmail()).getResultList();
	}

	public List<Conversation> getConversationBySubject(String subject, int currentClient) {
		Client cc = em.find(Client.class, currentClient);
		return  em.createQuery("select m.conversation from Message m where m.subject LIKE :sub and (m.conversation.fromUser.email = :current or m.conversation.toUser.email = :current)", Conversation.class).setParameter("sub", "%"+subject+"%").setParameter("current", cc.getEmail()).getResultList();

	}
	@Override
	public void addClient(Client c) {
		em.persist(c);
	}

	@Override
	public void deleteClient(int idClient) {
		em.remove(em.find(Client.class, idClient));
	}

	private String skillsToString(Set<Skill> skills) {
		String returnedSkills = " ";
		for(Skill s : skills) {
			returnedSkills+=s.getName() + ", ";
		}
		return  returnedSkills;
	}
	
}
