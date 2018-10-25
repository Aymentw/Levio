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

    public void AssignResource();
    public void CalculateFees();

    public void DisplayHistory();
    public void ArchiveHistory();
    public void TrackResource();
}
