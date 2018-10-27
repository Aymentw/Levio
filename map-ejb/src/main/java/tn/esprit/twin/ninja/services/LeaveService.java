package tn.esprit.twin.ninja.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.LeaveServiceLocal;
import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Ressource;

@Stateless
public class LeaveService implements LeaveServiceLocal {

	@PersistenceContext(unitName = "LevioMap-ejb")
	EntityManager em;

	@Override
	public void addLeave(int ressourceId, Leave l) {
		Ressource r = em.find(Ressource.class, ressourceId);
		em.persist(l);
		l.setRessource(r);

	}

	@Override
	public void updateLeave(int leaveId) {
		Leave l = em.find(Leave.class, leaveId);
		em.merge(l);

	}

	@Override
	public void deleteLeave(int ressourceId, int leaveId) {
		Ressource r = em.find(Ressource.class, ressourceId);
		Leave l = em.find(Leave.class, leaveId);
		r.getLeaves().remove(l);

	}

	@Override
	public List<Leave> getLeavesByRessource(int ressourceId) {
		return em.createQuery("SELECT l FROM Leave l WHERE l.ressource=:ressourceId", Leave.class)
				.setParameter("ressourceId", ressourceId).getResultList();
	}

	public List<Leave> getAllLeaves() {

		return em.createQuery("SELECT l FROM Leave l", Leave.class).getResultList();

	}

}
