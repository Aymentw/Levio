package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.Skill;

public interface RessourceServiceRemote {

	public boolean addRessource(Ressource r);

	public boolean updateRessource(Ressource res);

	public boolean deleteRessource(Ressource res);

	public List<Ressource> getAllRessources();

	public Ressource getRessourceById(int ressourceId);

	public void sendMessageToClient(Message message, int clientId);

	public boolean affectRessourceToProject(int projectId, int ressourceId);

	public List<Ressource> getRessourceByName(String FirstName);

	public void addPhotoRessource(int ressourceId, String photo);

}
