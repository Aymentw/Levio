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

    public void AssignResource();
    public void CalculateFees();

    public void DisplayHistory();
    public void ArchiveHistory();
    public void TrackResource();
}
