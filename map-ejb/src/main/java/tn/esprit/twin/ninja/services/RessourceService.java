package tn.esprit.twin.ninja.services;

import java.util.ArrayList;
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

		em.merge(res);

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

		return em.createQuery("SELECT r FROM Ressource r WHERE r.id=:ressourceId", Ressource.class)
				.setParameter("ressourceId", ressourceId).getSingleResult();
	}

	@Override
	public void addSkills(int ressourceId, int skillId) {
		Ressource r = em.find(Ressource.class, ressourceId);
		Skill s = em.find(Skill.class, skillId);
		s.setRessource(r);
	}

	@Override
	public void updateSkills(int ressourceId, int skillId, Skill skill) {
		Ressource r = em.find(Ressource.class, ressourceId);
		Skill s = em.find(Skill.class, skillId);

	}

	@Override
	public void deleteSkills(int skillId, int ressourceId) {
		Skill s = em.find(Skill.class, skillId);
		Ressource r = em.find(Ressource.class, ressourceId);
			r.getSkills().remove(s);

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
