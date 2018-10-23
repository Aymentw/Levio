package tn.esprit.twin.ninja.services;

import tn.esprit.twin.ninja.interfaces.MandateServicesRemote;
import tn.esprit.twin.ninja.persistence.Mandate;

import java.util.List;

public class MandateServices implements MandateServicesRemote {
    @Override
    public List<Mandate> SearchMandate(String name) {
        return null;
    }

    @Override
    public void AssignResource() {

    }

    @Override
    public void DisplayHistory() {

    }

    @Override
    public void ArchiveHistory() {

    }

    @Override
    public void TrackResource() {

    }
}
