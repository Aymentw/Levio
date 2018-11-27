package tn.esprit.twin.ninja.services;

import tn.esprit.twin.ninja.communication.MailSender;
import tn.esprit.twin.ninja.interfaces.RessourceServiceLocal;
import tn.esprit.twin.ninja.persistence.*;

import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RessourceService implements RessourceServiceLocal {

	@PersistenceContext(unitName = "LevioMap-ejb")
	EntityManager em;


	@Override
	public void sendMessageToClient(Message message,int currentResource, int clientId) throws MessagingException {
		Ressource ressource = em.find(Ressource.class, currentResource);
		Client client = em.find(Client.class, clientId);
		Conversation conversation = new Conversation();
		message.setFromUser(ressource);
		message.setToUser(client);
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
				client.getEmail(),
				message.getSubject()+ ": " + message.getType(),
				message.getMessage()
		);

	}

	@Override
	public List<Conversation> getOpenedConversations(int resourceId) {
		return em.createQuery("SELECT c FROM Conversation c where c.state = 'open' and (c.fromUser.id = :user OR c.toUser.id = :user)", Conversation.class).setParameter("user", resourceId).getResultList();
	}

	@Override
	public void respondToAMessage(int conversationId,int currencResource, Message message) throws MessagingException {
		//rigel reciepient
		Conversation conversation = em.find(Conversation.class, conversationId);
		Ressource cr = em.find(Ressource.class, currencResource);
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
				"mohamed.abdelhafidh@esprit.tn",
				message.getSubject()+ ": " + message.getType(),
				message.getMessage()
		);

	}
	@Override
	public List<Conversation> getConversationByType(int currentResource, MessageType messageType) {
		Ressource cr = em.find(Ressource.class, currentResource);
		return  em.createQuery("SELECT  m.conversation from Message m where m.conversation.state = 'open' and m.type = :msgType and (m.conversation.toUser.email = :user or m.conversation.fromUser.email = :user) order by m.createDate", Conversation.class).setParameter("msgType" , messageType).setParameter("user", cr.getEmail()).getResultList();
	}

	@Override
	public List<Conversation> getConversationBySubject(String subject, int currentResource) {
		Ressource cr = em.find(Ressource.class, currentResource);
		return  em.createQuery("select m.conversation from Message m where m.subject LIKE :sub and (m.conversation.fromUser.id = :current or m.conversation.toUser.id = :current)", Conversation.class).setParameter("sub", "%"+subject+"%").setParameter("current", cr.getId()).getResultList();
	}
	@Override
	public boolean addRessource(Ressource r) {

		if (r.getFirst_name() == null || r.getLast_name() == null || r.getSector() == null
				|| r.getContract_type() == null) {
			return false;
		} else
			em.persist(r);
		r.setState(RessourceState.available);
		return true;

	}
	@Override
	public boolean deleteSkills(int skillId) {
		try {
			Skill s = em.find(Skill.class, skillId);
			em.remove(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean evaluateSkills(Skill skill) {
		try {
			Skill s = em.find(Skill.class, skill.getId());
			s.setRating(skill.getRating());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean addSkills(int ressourceId, int skillId) {
		try {
			Ressource r = em.find(Ressource.class, ressourceId);
			Skill s = em.find(Skill.class, skillId);
			s.setRessource(r);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public void addPhotoRessource(int ressourceId, String photo) {

		Ressource r = em.find(Ressource.class, ressourceId);
		r.setPhoto(photo);

	}

	@Override
	public boolean updateRessource(Ressource res) {

		Ressource r = em.find(Ressource.class, res.getId());
		if (res.getFirst_name() == null || res.getLast_name() == null || res.getSector() == null
				|| res.getContract_type() == null) {
			return false;
		} else
			r.setFirst_name(res.getFirst_name());
		r.setLast_name(res.getLast_name());
		r.setSector(res.getSector());
		r.setContract_type(res.getContract_type());

		em.merge(r);
		return true;

	}

	@Override
	public boolean deleteRessource(Ressource res) {
		try {
			Ressource r = em.find(Ressource.class, res.getId());
			r.setArchived(true);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Ressource> getAllRessources() {

		return em.createQuery("SELECT r FROM Ressource r WHERE r.archived=0", Ressource.class).getResultList();

	}

	@Override
	public Ressource getRessourceById(int ressourceId) {

		return em.createQuery("SELECT r FROM Ressource r WHERE r.id=:ressourceId", Ressource.class)
				.setParameter("ressourceId", ressourceId).getSingleResult();
	}

	@Override
	public List<Ressource> getRessourceByName(String FirstName) {

		return em.createQuery(
				"SELECT r FROM Ressource r WHERE r.archived=0 AND r.first_name LIKE :FirstName OR r.last_name LIKE:FirstName",
				Ressource.class).setParameter("FirstName", "%" + FirstName + "%").getResultList();
	}


	@Override
	public boolean affectRessourceToProject(int projectId, int ressourceId) {
		try {
			Project p = em.find(Project.class, projectId);
			Ressource r = em.find(Ressource.class, ressourceId);
			r.setProject(p);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addLeave(int ressourceId, Leave l) {
		Ressource r = em.find(Ressource.class, ressourceId);
		if (l.getStart_date().compareTo(l.getEnd_date()) > 0 || l.getStart_date().compareTo(l.getEnd_date()) == 0) {
			return false;
		}
		em.persist(l);
		l.setRessource(r);
		return true;

	}

	@Override
	public boolean updateLeave(Leave l) {
		try {
			Leave leave = em.find(Leave.class, l.getId());
			leave.setStart_date(l.getStart_date());
			leave.setEnd_date(l.getEnd_date());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteLeave(int leaveId) {
		try {
			Leave l = em.find(Leave.class, leaveId);
			em.remove(l);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Leave> getLeavesByRessource(int ressourceId) {
		return em.createQuery("SELECT l FROM Leave l WHERE l.ressource=:ressourceId", Leave.class)
				.setParameter("ressourceId", ressourceId).getResultList();
	}

	@Override
	public List<Leave> getAllLeaves() {

		return em.createQuery("SELECT l FROM Leave l", Leave.class).getResultList();

	}


}
