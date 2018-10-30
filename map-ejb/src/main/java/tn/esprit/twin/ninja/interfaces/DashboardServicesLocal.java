package tn.esprit.twin.ninja.interfaces;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin.ninja.persistence.Client;
import tn.esprit.twin.ninja.persistence.Project;
import tn.esprit.twin.ninja.persistence.Ressource;

@Local
public interface DashboardServicesLocal {
public Long getNumberFreelancers();
public Long getNumberEmployees();
public Long getNumberEmployeesInMandates();
public Long getNumberEmployeesInterMandate();
public Long getNumberEmployeesAdministration();
public Long reclamationsPerTarget(Object o);
public Long satisfactionsPerTarget(Object o);
public float satisfactionRate(Object o);
public int numberOfResourcesToClient(int clientId);
public void reportResource(int ressourceId) throws IOException;
public List<Object>mostUsedSkills();
public List<Object> mostProfitProject();
public List<Object> mostProfitClient();
}