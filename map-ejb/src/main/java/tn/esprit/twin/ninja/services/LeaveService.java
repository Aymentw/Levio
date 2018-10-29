package tn.esprit.twin.ninja.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.LeaveServiceLocal;
import tn.esprit.twin.ninja.persistence.Leave;


@Stateless
public class LeaveService implements LeaveServiceLocal {
	
	@PersistenceContext(unitName="LevioMap-ejb")
	EntityManager em;


	@Override
	public List<Leave> getAllLeave() {
		return em.createQuery("SELECT l from Leave l", Leave.class).getResultList();
		
	}

	
}
