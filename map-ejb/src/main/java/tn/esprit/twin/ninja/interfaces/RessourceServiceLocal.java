package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import tn.esprit.twin.ninja.persistence.*;

import javax.mail.MessagingException;

public interface RessourceServiceLocal {

	public boolean addRessource(Ressource r);

	public boolean updateRessource(Ressource res);

	public boolean deleteRessource(Ressource res);

	public List<Ressource> getAllRessources();

	public Ressource getRessourceById(int ressourceId);

	public boolean affectRessourceToProject(int projectId, int ressourceId);

	public List<Ressource> getRessourceByName(String FirstName);

	public void addPhotoRessource(int ressourceId, String photo);

	public boolean addSkills(int ressourceId, int skillId);

	public boolean deleteSkills(int skillId);

	public boolean evaluateSkills(Skill skill);

	public void sendMessageToClient(Message message,int currentResource, int clientId) throws MessagingException;

	public List<Conversation> getOpenedConversations(int ResourceId);

	public void respondToAMessage(int conversationId,int currencResource, Message message) throws MessagingException;

	public List<Conversation> getConversationByType(int currentResource, MessageType messageType);

	public List<Conversation> getConversationBySubject(String subject, int currentResource);

	public boolean addLeave(int ressourceId, Leave l);

	public boolean updateLeave(Leave l);

	public boolean deleteLeave(int leaveId);

	public List<Leave> getLeavesByRessource(int ressourceId);

	public List<Leave> getAllLeaves();


}
