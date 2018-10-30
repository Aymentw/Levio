package tn.esprit.twin.ninja.services;

import tn.esprit.twin.ninja.interfaces.MandateServicesLocal;
import tn.esprit.twin.ninja.interfaces.MandateServicesRemote;
import tn.esprit.twin.ninja.persistence.Mandate;
import tn.esprit.twin.ninja.persistence.Project;
import tn.esprit.twin.ninja.persistence.Ressource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
@Stateless
public class MandateServices implements MandateServicesRemote, MandateServicesLocal {
    @PersistenceContext(unitName="LevioMap-ejb")
    EntityManager em;

    @Override
    public List<Mandate> getAll() {
        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m", Mandate.class);
        List<Mandate> results = query.getResultList();
        return results;
    }

    @Override
    public List<Mandate> SearchMandateByDate(Date date) {

        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.StartDate>=:date and m.EndDate<=:date", Mandate.class);
        query.setParameter("date", date);
        List<Mandate> results = query.getResultList();
        return results;
    }

    @Override
    public List<Mandate> getMandateByResource(int resourceId) {

        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.ressource=:resId", Mandate.class);
        query.setParameter("resId", resourceId);
        List<Mandate> results = query.getResultList();
        return results;
    }



    @Override
    public void AssignResource(int projetId,int resourceId) {
    	Project projetEntity = em.find(Project.class, projetId);
    	Ressource resourceEntity = em.find(Ressource.class, resourceId);
    	Mandate mand=new Mandate();
 
    	mand.setStartDate(projetEntity.getStart_date());
    	mand.setEndDate(projetEntity.getEnd_date());
    	mand.setProject(projetEntity);
    	mand.setRessource(resourceEntity);
    	em.persist(mand);
    
    }
    
     @Override
    public void EditMandate(Mandate m)
    {
    	Mandate mand = em.find(Mandate.class, m.getId());
    	if(m.getStartDate()!=null)
    	mand.setStartDate(m.getStartDate());
    	if(m.getEndDate()!=null)
    	mand.setEndDate(m.getEndDate());
    	if(m.getMontant()>0)
    	mand.setMontant(m.getMontant());

    	em.merge(mand);
    }

    @Override
    public void CalculateFees() {
    	
    }

    public void CalculateFees(int mandateID,float taux,float NbrH) {
    	Mandate mandateEntity = em.find(Mandate.class, mandateID);
    	float montant=taux*NbrH; 
    	mandateEntity.setMontant(montant);
    }

    @Override
    public List<Mandate> DisplayHistory() {
        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.EndDate<=CURRENT_DATE", Mandate.class);
        List<Mandate> results = query.getResultList();
        return results;
    }

    @Override
    public void ArchiveHistory(int mandateID) {
    	Mandate mandateEntity = em.find(Mandate.class, mandateID);
    	mandateEntity.setArchived(true);
    }

    @Override
    public void TrackResource() {

    }

	@Override
	public String SendMail(String username, String password, String from, String to, String subject, String msg) {
		// TODO Auto-generated method stub
		return null;
	}
}
