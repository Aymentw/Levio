package tn.esprit.twin.ninja.services;

import tn.esprit.twin.ninja.interfaces.MandateServicesRemote;
import tn.esprit.twin.ninja.persistence.Mandate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
@Stateless
public class MandateServices implements MandateServicesRemote {
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

        TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.listRessource=:resId", Mandate.class);
        query.setParameter("resId", resourceId);
        List<Mandate> results = query.getResultList();
        return results;
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
