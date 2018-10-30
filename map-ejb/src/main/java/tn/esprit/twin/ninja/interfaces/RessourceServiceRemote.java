package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.Skill;

public interface RessourceServiceRemote {

	public void addRessource(Ressource r);

	public boolean updateRessource(Ressource res);

	public boolean deleteRessource(Ressource res);

	public boolean updateSkills(Skill skill);

	public List<Ressource> getAllRessources();

	public Ressource getRessourceById(int ressourceId);

	public boolean addSkills(int ressourceId, int skillId);

	public boolean deleteSkills(int skillId);

	public boolean evaluateSkills(Skill skill);

	public void sendMessageToClient(Message message, int clientId);

	public boolean affectRessourceToProject(int projectId, int ressourceId);

	public boolean addLeave(int ressourceId, Leave l);

	public boolean updateLeave(Leave l);

	public boolean deleteLeave(int leaveId);

	public List<Leave> getLeavesByRessource(int ressourceId);

	public List<Leave> getAllLeaves();

	public List<Ressource> getRessourceByName(String FirstName);

}
