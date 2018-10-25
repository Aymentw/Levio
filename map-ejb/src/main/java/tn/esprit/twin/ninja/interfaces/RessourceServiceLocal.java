package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Ressource;

public interface RessourceServiceLocal {
	
	public void addRessource(Ressource r);
	public void deleteRessource(int ressourceId);
	public 	void updateRessource(Ressource res);
	public List<Ressource> getAllRessources();
	public Ressource getRessourceById(int ressourceId);
	public void addSkills(int skillId);
	public void updateSkills(int ressourceId);
	public void deleteSkills();
	public void evaluateSkills();
	public List<Ressource> getRessourcesBySkill();
	public void sendMessageToClient(Message message, int clientId);



}
