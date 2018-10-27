package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.Skill;

public interface RessourceServiceLocal {

	public void addRessource(Ressource r);

	public void updateRessource(Ressource res);

	public void deleteRessource(int ressourceId);

	public void updateSkills(int ressourceId, int skillId, Skill skill);

	public List<Ressource> getAllRessources();

	public Ressource getRessourceById(int ressourceId);

	public void addSkills(int ressourceId, int skillId);

	public void deleteSkills(int skillId, int ressourceId);

	public void evaluateSkills();

	public List<Ressource> getRessourcesBySkill();

	public void sendMessageToClient(Message message, int clientId);

}
