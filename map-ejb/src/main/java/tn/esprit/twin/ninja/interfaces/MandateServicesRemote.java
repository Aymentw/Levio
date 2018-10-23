package tn.esprit.twin.ninja.interfaces;

import tn.esprit.twin.ninja.persistence.Mandate;

import java.util.List;

public interface MandateServicesRemote {
    public List<Mandate> SearchMandate(String name);
    public void AssignResource();
    public void DisplayHistory();
    public void ArchiveHistory();
    public void TrackResource();
}
