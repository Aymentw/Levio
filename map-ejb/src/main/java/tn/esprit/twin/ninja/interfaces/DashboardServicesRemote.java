package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Project;
import tn.esprit.twin.ninja.persistence.Ressource;

@Remote
public interface DashboardServicesRemote {
public Long getNumberFreelancers();
public Long getNumberEmployees();
public Long getNumberEmployeesInMandates();
public Long getNumberEmployeesInterMandate();
public Long getNumberEmployeesAdministration();
public Long reclamationsPerTarget(Object o);
public Long satisfactionsPerTarget(Object o);
public float satisfactionRate(Object o);
public int numberOfResourcesToClient(Client c);
public void reportResource(int ressourceId);
public List<Object>mostUsedSkills();
}
