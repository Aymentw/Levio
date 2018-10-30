package tn.esprit.twin.ninja.interfaces;

import tn.esprit.twin.ninja.persistence.Mandate;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local
public interface MandateServicesLocal {
    public List<Mandate> getAll();
    public List<Mandate> SearchMandateByDate(Date date);
    public List<Mandate> getMandateByResource(int resourceId);

    public void AssignResource(int projetId,int resourceId);
    public void CalculateFees(int mandateID,float taux,float NbrH);

    public List<Mandate> DisplayHistory();
    public List<Mandate> ArchivedMandate();
    public void ArchiveMandate(int mandateID);
    public void TrackResource();
    public String SendMail(String username,String password,String from,String to,String subject,String msg);
    public void EditMandate(Mandate m);

}
