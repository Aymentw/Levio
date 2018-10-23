package tn.esprit.twin.ninja.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin.ninja.interfaces.DashboardServicesRemote;
@Stateless
public class DashboardService implements DashboardServicesRemote {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int getNumberFreelancers() {
		String sql = "SELECT COUNT(r.id) FROM Resource r WHERE r.contract_type='freelancer'";
		Query q = em.createQuery(sql);
		int count =(int) q.getSingleResult();
		return count;
	}

	@Override
	public int getNumberEmloyees() {
		String sql = "SELECT COUNT(r.id) FROM Resource r WHERE r.contract_type='employee'";
		Query q = em.createQuery(sql);
		int count =(int) q.getSingleResult();
		return count;
	}

}