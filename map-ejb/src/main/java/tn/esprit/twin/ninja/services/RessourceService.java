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
	public boolean updateRessource(Ressource res) {
		try {
			Ressource r = em.find(Ressource.class, res.getId());
			r.setFirst_name(res.getFirst_name());
			r.setLast_name(res.getLast_name());
			r.setContract_type(res.getContract_type());
			r.setSector(res.getSector());
			r.setSeniority(res.getSeniority());
			r.setState(res.getState());

			em.merge(r);

			return true;
		} catch (Exception e) {
			return false;
		}
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
	public boolean addSkills(int ressourceId, int skillId) {
		try {
			Ressource r = em.find(Ressource.class, ressourceId);
			Skill s = em.find(Skill.class, skillId);
			s.setRessource(r);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateSkills(Skill skill) {
		try {
			Skill s = em.find(Skill.class, skill.getId());
			em.merge(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteSkills(int skillId) {
		try {
			Skill s = em.find(Skill.class, skillId);
			em.remove(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean evaluateSkills(Skill skill) {
		try {
			Skill s = em.find(Skill.class, skill.getId());
			s.setRating(skill.getRating());
			return true;
		} catch (Exception e) {
			return false;
		}
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

	@Override
	public boolean addLeave(int ressourceId, Leave l) {
		Ressource r = em.find(Ressource.class, ressourceId);
		if (l.getStart_date().compareTo(l.getEnd_date()) > 0 || l.getStart_date().compareTo(l.getEnd_date()) == 0) {
			return false;
		}
		em.persist(l);
		l.setRessource(r);
		return true;

	}

	@Override
	public boolean updateLeave(Leave l) {
		try {
			Leave leave = em.find(Leave.class, l.getId());
			leave.setStart_date(l.getStart_date());
			leave.setEnd_date(l.getEnd_date());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteLeave(int leaveId) {
		try {
			Leave l = em.find(Leave.class, leaveId);
			em.remove(l);
			return true;
		} catch (Exception e) {
			return false;
		}
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
