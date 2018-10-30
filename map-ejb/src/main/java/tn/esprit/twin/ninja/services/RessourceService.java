package tn.esprit.twin.ninja.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.RessourceServiceLocal;
import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Project;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.RessourceState;
import tn.esprit.twin.ninja.persistence.Skill;

@Stateless
public class RessourceService implements RessourceServiceLocal {

	@PersistenceContext(unitName = "LevioMap-ejb")
	EntityManager em;

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
	public void sendMessageToClient(Message message, int clientId) {
		Client client = em.find(Client.class, clientId);
		message.setTarget("CLIENT");
		em.merge(message);
		// send an email to the client email with the message body

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

	
	
}
