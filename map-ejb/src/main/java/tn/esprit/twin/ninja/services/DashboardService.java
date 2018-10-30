package tn.esprit.twin.ninja.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;
import tn.esprit.twin.ninja.interfaces.DashboardServicesLocal;
import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Leave;
import tn.esprit.twin.ninja.persistence.Mandate;
import tn.esprit.twin.ninja.persistence.Project;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.Skill;
@Stateless
public class DashboardService implements DashboardServicesLocal {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Long getNumberFreelancers() {//ok
		String sql = "SELECT COUNT(r.id) FROM Ressource r WHERE r.contract_type='freelancer'";
		Query q = em.createQuery(sql);
		Long count =(Long) q.getSingleResult();
		return count;
	}

	@Override
	public Long getNumberEmployees() {//ok
		String sql = "SELECT COUNT(r.id) FROM Ressource r WHERE r.contract_type='employee'";
		Query q = em.createQuery(sql);
		Long count =(Long) q.getSingleResult();
		return count;
	}

	@Override
	public Long getNumberEmployeesInMandates() {//ok
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

	@Override
	public int numberOfResourcesToClient(int clientId) {//ok
		Client c=em.find(Client.class, clientId);
		int numRes=0;
		String sql = "Select p from Peoject p where p.client.id="+c.getId();
		Query q = em.createQuery(sql);
		List<Project> projects  =(List<Project>) q.getResultList();
		for (Project p : projects){
			String sql2="Select Count(distinct m.ressource) from Mandate m where m.project.id="+p.getId();
			Query q2 = em.createQuery(sql2);
			Long count = (Long)q2.getSingleResult();
			numRes+=count;
		}
		return numRes;
	}

	@Override
	public void reportResource(int ressourceId) throws IOException {//okish
		Ressource r=em.find(Ressource.class, ressourceId);
		String html="<html><head><style></style></head><body>";
		html+="<p>First name : "+r.getFirst_name()+"</p><br>";
		html+="<p>Last name : "+r.getLast_name()+"</p><br>";
		html+="<p>Contract Type : "+r.getContract_type()+"</p><br>";
		html+="<p>Leaves List :</p><br>";
		int i=0;
		for(Leave l : r.getLeaves()){
			i++;
			html+="<p>"+i+"- ";
			html+="Start Date : "+l.getStart_date();
			html+="		End Date : "+l.getEnd_date()+"</p><br>";
		}
		html+="<p>Mandates affected to resource : </p><br>";
		for(Mandate m : r.getMandate()){
			html+="-------------------------------------------------------------------<br>";
			html+="Mandate : "+m.getId()+"<br>";
			html+="Start Date : "+m.getStartDate()+"<br>";
			html+="End Date : "+m.getEndDate()+"<br>";
			html+="Charges : "+m.getMontant()+"<br>";
			html+="Project "+m.getProject().getName()+" for Client \""+m.getProject().getClients().getName()+"\"<br>";
			html+="-------------------------------------------------------------------<br>";
		}
		html+="Skills : <br>";
		for(Skill s : r.getSkills()){
			html+="-------------------------------------------------------------------<br>";
			html+=""+s.getName()+"<br>";
			html+="Rating : "+s.getRating()+"<br>";
			html+="-------------------------------------------------------------------<br>";
		}
		HtmlToPdf.create()
	    .object(HtmlToPdfObject.forHtml(html))
	    .convert("C:/Users/Firassov/Desktop/pdf/file.pdf");
	}

	@Override
	public List<Object> mostUsedSkills() {//ok
		String sql = "SELECT s.name,COUNT(s.id) as value_occurrence FROM Skill s GROUP BY s.name ORDER BY value_occurrence DESC";
		Query q = em.createQuery(sql);
		q.setMaxResults(5);
		List<Object> mostSkills=(List<Object>) q.getResultList();
		return mostSkills;
	}
	@Override
	public List<Object> mostProfitProject() {//ok
		String sql = "SELECT m.project.name,SUM(m.Montant) as sum_profit FROM Mandate m GROUP BY m.project ORDER BY sum_profit DESC";
		Query q = em.createQuery(sql);
		q.setMaxResults(5);
		List<Object> mostSkills=(List<Object>) q.getResultList();
		return mostSkills;
	}
	@Override
	public List<Object> mostProfitClient() {//ok
		String sql = "SELECT m.project.client.name,COUNT(distinct m.ressource) as num_res FROM Mandate m GROUP BY m.project.client ORDER BY num_res DESC";
		Query q = em.createQuery(sql);
		q.setMaxResults(5);
		List<Object> mostSkills=(List<Object>) q.getResultList();
		return mostSkills;
	}

	@Override
	public float mandateEfficiency(int mandateId) {
		Mandate m=em.find(Mandate.class, mandateId);
		float optimumDuration=(m.getEndDate().getTime()-m.getStartDate().getTime())/ (24 * 60 * 60 * 1000);
		float actualDuration=(m.getActualEndDate().getTime()-m.getStartDate().getTime())/ (24 * 60 * 60 * 1000);
		return (optimumDuration/actualDuration)*100;
		
	}

	@Override
	public float resourceEfficiency(int resourceId) {
		float sum=0;
		int i=0;
		Ressource r = em.find(Ressource.class,resourceId);
		for (Mandate m : r.getMandate()) {
			float optimumDuration=(m.getEndDate().getTime()-m.getStartDate().getTime())/ (24 * 60 * 60 * 1000);
			float actualDuration=(m.getActualEndDate().getTime()-m.getStartDate().getTime())/ (24 * 60 * 60 * 1000);
			sum+=(optimumDuration/actualDuration)*100;
			i++;
		}
		return sum/i;
	}

	@Override
	public float projectEfficiency(int projectID) {
		float sum=0;
		int i=0;
		Project p = em.find(Project.class,projectID);
		for (Mandate m : p.getMandates()) {
			float optimumDuration=(m.getEndDate().getTime()-m.getStartDate().getTime())/ (24 * 60 * 60 * 1000);
			float actualDuration=(m.getActualEndDate().getTime()-m.getStartDate().getTime())/ (24 * 60 * 60 * 1000);
			sum+=(optimumDuration/actualDuration)*100;
			i++;
		}
		return sum/i;
	}
	
}

