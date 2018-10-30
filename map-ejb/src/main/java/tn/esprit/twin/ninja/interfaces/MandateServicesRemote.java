package tn.esprit.twin.ninja.interfaces;

import tn.esprit.twin.ninja.persistence.Mandate;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;
@Remote
public interface MandateServicesRemote {
    public List<Mandate> getAll();
    public List<Mandate> SearchMandateByDate(Date date);
    public List<Mandate> getMandateByResource(int resourceId);

    public void AssignResource(int projetId,int resourceId);
    public void CalculateFees();

    public List<Mandate> DisplayHistory();
    public void ArchiveHistory(int mandateID);
    public void TrackResource();
    public String SendMail(String username,String password,String from,String to,String subject,String msg);
    public void EditMandate(Mandate m);

}
