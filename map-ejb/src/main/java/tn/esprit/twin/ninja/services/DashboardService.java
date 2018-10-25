package tn.esprit.twin.ninja.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin.ninja.interfaces.DashboardServicesRemote;
import tn.esprit.twin.ninja.persistence.Leave;
@Stateless
public class DashboardService implements DashboardServicesRemote {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Long getNumberFreelancers() {
		String sql = "SELECT COUNT(r.id) FROM Ressource r WHERE r.contract_type='freelancer'";
		Query q = em.createQuery(sql);
		Long count =(Long) q.getSingleResult();
		return count;
	}

	@Override
	public Long getNumberEmployees() {
		String sql = "SELECT COUNT(r.id) FROM Ressource r WHERE r.contract_type='employee'";
		Query q = em.createQuery(sql);
		Long count =(Long) q.getSingleResult();
		return count;
	}

	@Override
	public Long getNumberEmployeesInMandates() {
		Long leavescounter=new Long(0);
		Date d=new Date();
		String sql = "SELECT COUNT(r.id) FROM Ressource r WHERE r.state='notAvailable' or r.state='soonAvailable'";
		Query q = em.createQuery(sql);
		Long count =(Long) q.getSingleResult();
		String sql2 ="Select r.leaves from Ressource r WHERE r.state='notAvailable'";
		Query q2 = em.createQuery(sql2);
		List<Leave> leaves  =(List<Leave>) q2.getResultList();
		for (Leave l : leaves){
			if (d.after(l.getStart_date()) && d.before(l.getEnd_date())){
				leavescounter++;
			}
		}
		return count-leavescounter;
	}

}