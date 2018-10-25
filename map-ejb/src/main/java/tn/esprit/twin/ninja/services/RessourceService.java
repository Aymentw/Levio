package tn.esprit.twin.ninja.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin.ninja.interfaces.RessourceServiceLocal;
import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Message;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.Skill;

@Stateless
public class RessourceService implements RessourceServiceLocal {

	@PersistenceContext(unitName = "LevioMap-ejb")
	EntityManager em;

	@Override
	public void addRessource(Ressource r) {

		em.persist(r);

	}

	@Override
	public void updateRessource(Ressource res) {

		Ressource r = em.find(Ressource.class, res.getId());
		r.setFirst_name(res.getFirst_name());
		r.setLast_name(res.getLast_name());
		r.setContract_type(res.getContract_type());
		r.setSector(res.getSector());
		r.setSeniority(res.getSeniority());
		r.setState(res.getState());

		em.merge(r);

	}

	@Override
	public void deleteRessource(int ressourceId) {
		Ressource r = em.find(Ressource.class, ressourceId);
		em.remove(r);

	}

	@Override
	public List<Ressource> getAllRessources() {

		return em.createQuery("SELECT r FROM Ressource r", Ressource.class).getResultList();

	}

	@Override
	public Ressource getRessourceById(int ressourceId) {

		return em.createQuery("SELECT * FROM Ressource r WHERE r.id=:ressourceId", Ressource.class)
				.setParameter("ressourceId", ressourceId).getSingleResult();
	}

	@Override
	public void addSkills(int skillId) {

	}

	@Override
	public void updateSkills(int ressourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSkills() {
		// TODO Auto-generated method stub

	}

	@Override
	public void evaluateSkills() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Ressource> getRessourcesBySkill() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessageToClient(Message message, int clientId) {
		Client client = em.find(Client.class, clientId);
		message.setTarget("CLIENT");
		em.merge(message);
		// send an email to the client email with the message body

	}
}
