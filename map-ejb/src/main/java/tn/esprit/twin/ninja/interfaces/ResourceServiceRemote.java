package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Ressource;

@Remote
public interface ResourceServiceRemote {
	
	public void addRessource(Ressource r);
	public void updateRessource(int ressourceId);
	public void deleteRessource(int ressourceId);
	public List<Ressource> getAllRessources();
	public Ressource getRessourceById(int ressourceId);
	public void addSkills(int skillId);
	public void updateSkills(int ressourceId);
	public void deleteSkills();
	public void evaluateSkills();
	public List<Ressource> getRessourcesBySkill();
	public void sendMessageToClient(Message message, int clientId);

}
