package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.Skill;

@Remote
public interface ResourceServiceRemote {

	public void addRessource(Ressource r);

	public void updateRessource(Ressource res);

	public void deleteRessource(Ressource res);

	public void updateSkills(Skill skill);

	public List<Ressource> getAllRessources();

	public Ressource getRessourceById(int ressourceId);

	public void addSkills(int ressourceId, int skillId);

	public void deleteSkills(int skillId);

	public void evaluateSkills(Skill skill);

	public void sendMessageToClient(Message message, int clientId);

	public void affectRessourceToProject(int projectId, int ressourceId);

	public void addLeave(int ressourceId, Leave l);

	public void updateLeave(Leave l);

	public void deleteLeave(int leaveId);

	public List<Leave> getLeavesByRessource(int ressourceId);

	public List<Leave> getAllLeaves();

	public List<Ressource> getRessourceByName(String FirstName);

}
