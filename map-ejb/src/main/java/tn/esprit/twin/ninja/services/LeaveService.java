package tn.esprit.twin.ninja.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.LeaveServiceLocal;
import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.RessourceState;


@Stateless
public class LeaveService implements LeaveServiceLocal {
	
	@PersistenceContext(unitName="LevioMap-ejb")
	EntityManager em;

	@Override
	public boolean addLeave(int ressourceId, Leave l) {
		Ressource r = em.find(Ressource.class, ressourceId);

		if (l.getStart_date().compareTo(l.getEnd_date()) > 0 || l.getStart_date().compareTo(l.getEnd_date()) == 0
				|| l.getStart_date() == null || l.getEnd_date() == null || l.getType() == null) {
			return false;
		}
		em.persist(l);
		l.setRessource(r);
		r.setState(RessourceState.notAvailable);
		return true;

	}

	@Override
	public boolean updateLeave(Leave l) {

		Leave leave = em.find(Leave.class, l.getId());
		if (leave.getStart_date() == null || leave.getEnd_date() == null) {
			return false;
		}

		leave.setStart_date(l.getStart_date());
		leave.setEnd_date(l.getEnd_date());
		return true;
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
	public List<Leave> getAllLeave() {
		return em.createQuery("SELECT l from Leave l", Leave.class).getResultList();
		
	}

	
}
