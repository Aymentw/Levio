package tn.esprit.twin.ninja.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin.ninja.interfaces.DashboardServicesRemote;
import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Project;
import tn.esprit.twin.ninja.persistence.Ressource;
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

	@Override
	public Long getNumberEmployeesInterMandate() {
		String sql = "SELECT COUNT(r.id) FROM Ressource r WHERE r.state='available' and r.admin=false";
		Query q = em.createQuery(sql);
		Long count =(Long) q.getSingleResult();
		return count;
	}

	@Override
	public Long getNumberEmployeesAdministration() {
		String sql = "SELECT COUNT(r.id) FROM Ressource r WHERE r.admin=true";
		Query q = em.createQuery(sql);
		Long count =(Long) q.getSingleResult();
		return count;
	}

	@Override
	public Long reclamationsPerTarget(Object o) {
		Long count = null;
		if(o instanceof Ressource){
		Ressource r =(Ressource)o;
		String sql = "Select count(m.id) from Message m where m.targetId="+r.getId()+" and m.messageType=reclamation";
		Query q = em.createQuery(sql);
		count =(Long) q.getSingleResult();
		}
		else if(o instanceof Project){
			Project p =(Project)o;
			String sql = "Select count(m.id) from Message m where m.targetId="+p.getId()+" and m.messageType=reclamation";
			Query q = em.createQuery(sql);
			count =(Long) q.getSingleResult();
			}
		else if(o instanceof Client){
			Client c =(Client)o;
			String sql = "Select count(m.id) from Message m where m.targetId="+c.getId()+" and m.messageType=reclamation";
			Query q = em.createQuery(sql);
			count =(Long) q.getSingleResult();
			}
		return count;
	}

	@Override
	public Long satisfactionsPerTarget(Object o) {
		Long count = null;
		if(o instanceof Ressource){
		Ressource r =(Ressource)o;
		String sql = "Select count(m.id) from Message m where m.targetId="+r.getId()+" and m.messageType=satisfaction";
		Query q = em.createQuery(sql);
		count =(Long) q.getSingleResult();
		}
		else if(o instanceof Project){
			Project p =(Project)o;
			String sql = "Select count(m.id) from Message m where m.targetId="+p.getId()+" and m.messageType=satisfactionn";
			Query q = em.createQuery(sql);
			count =(Long) q.getSingleResult();
			}
		else if(o instanceof Client){
			Client c =(Client)o;
			String sql = "Select count(m.id) from Message m where m.targetId="+c.getId()+" and m.messageType=satisfaction";
			Query q = em.createQuery(sql);
			count =(Long) q.getSingleResult();
			}
		return count;
	}

	@Override
	public float satisfactionRate(Object o) {
		Long reclamation = reclamationsPerTarget(o);
		Long satisfaction = satisfactionsPerTarget(o);
		return (satisfaction/satisfaction+reclamation)*100;
	}
}

