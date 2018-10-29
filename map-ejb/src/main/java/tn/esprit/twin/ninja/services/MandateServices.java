package tn.esprit.twin.ninja.services;

import tn.esprit.twin.ninja.interfaces.MandateServicesLocal;
import tn.esprit.twin.ninja.interfaces.MandateServicesRemote;
import tn.esprit.twin.ninja.persistence.Mandate;
import tn.esprit.twin.ninja.persistence.Project;
import tn.esprit.twin.ninja.persistence.Ressource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.Properties;
@Stateless
public class MandateServices implements MandateServicesRemote, MandateServicesLocal {
    @PersistenceContext(unitName="LevioMap-ejb")
    EntityManager em;

    @Override
    public List<Mandate> getAll() {
        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where Archived=false", Mandate.class);
        List<Mandate> results = query.getResultList();
        return results;
    }

    @Override
    public List<Mandate> SearchMandateByDate(Date date) {

        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.StartDate<=:date and m.EndDate>=:date and Archived=false", Mandate.class);
        query.setParameter("date", date,TemporalType.DATE);
        List<Mandate> results = query.getResultList();
        return results;
    }

    @Override
    public List<Mandate> getMandateByResource(int resourceId) {

        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.ressource.id=:resId and Archived=false", Mandate.class);
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
    	SendMail("notifmaplevio@gmail.com","NinjaC0ders","notifmaplevio@gmail.com","slimen.mami@esprit.tn","Assign Notification","You have new assignation ");
    
    }

    @Override
    public void CalculateFees(int mandateID,float taux,float NbrH) {
    	Mandate mandateEntity = em.find(Mandate.class, mandateID);
    	float montant=taux*NbrH;
    	mandateEntity.setMontant(montant);
    }

    @Override
    public List<Mandate> DisplayHistory() {
        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.EndDate<=CURRENT_DATE and Archived=false", Mandate.class);
        List<Mandate> results = query.getResultList();
        return results;
    }

    @Override
    public void ArchiveMandate(int mandateID) {
    	Mandate mandateEntity = em.find(Mandate.class, mandateID);
    	mandateEntity.setArchived(true);
    }

    @Override
    public void TrackResource() {

    }
    @Override
    public List<Mandate> ArchivedMandate(){
        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where Archived=true", Mandate.class);
        List<Mandate> results = query.getResultList();
        return results;
    
    }
    @Override
    public String SendMail(String username,String password,String from,String to,String subject,String msg)
    {
     // user=	notifmaplevio@gmail.com
    // pass=NinjaC0ders	

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(msg);

			Transport.send(message);

			return "Done";

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	
    	
    }
}
