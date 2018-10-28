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
	public void deleteRessource(Ressource res) {
		Ressource r = em.find(Ressource.class, res.getId());
		r.setArchived(true);

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
	public List<Ressource> getRessourceByName(String FirstName) {

		return em.createQuery(
				"SELECT r FROM Ressource r WHERE r.first_name LIKE :FirstName OR r.last_name LIKE:FirstName",
				Ressource.class).setParameter("FirstName", "%" + FirstName + "%").getResultList();
	}

	@Override
	public void addSkills(int ressourceId, int skillId) {
		Ressource r = em.find(Ressource.class, ressourceId);
		Skill s = em.find(Skill.class, skillId);
		s.setRessource(r);
	}

	@Override
	public void updateSkills(Skill skill) {
		Skill s = em.find(Skill.class, skill.getId());
		em.merge(s);

	}

	@Override
	public void deleteSkills(int skillId) {
		Skill s = em.find(Skill.class, skillId);
		em.remove(s);

	}

	@Override
	public void evaluateSkills(Skill skill) {
		Skill s = em.find(Skill.class, skill.getId());
		s.setRating(skill.getRating());

	}


	@Override
	public void sendMessageToClient(Message message, int clientId) {
		Client client = em.find(Client.class, clientId);
		message.setTarget("CLIENT");
		em.merge(message);
		// send an email to the client email with the message body

	}

	@Override
	public void affectRessourceToProject(int projectId, int ressourceId) {

		Project p = em.find(Project.class, projectId);
		Ressource r = em.find(Ressource.class, ressourceId);
		r.setProject(p);

	}

	@Override
	public void addLeave(int ressourceId, Leave l) {
		Ressource r = em.find(Ressource.class, ressourceId);
		em.persist(l);
		l.setRessource(r);

	}

	@Override
	public void updateLeave(Leave l) {
		Leave leave = em.find(Leave.class, l.getId());
		leave.setStart_date(l.getStart_date());
		leave.setEnd_date(l.getEnd_date());

	}

	@Override
	public void deleteLeave(int leaveId) {
		Leave l = em.find(Leave.class, leaveId);
		em.remove(l);

	}

	@Override
	public List<Leave> getLeavesByRessource(int ressourceId) {
		return em.createQuery("SELECT l FROM Leave l WHERE l.ressource=:ressourceId", Leave.class)
				.setParameter("ressourceId", ressourceId).getResultList();
	}

	@Override
	public List<Leave> getAllLeaves() {

		return em.createQuery("SELECT l FROM Leave l", Leave.class).getResultList();

	}

}
