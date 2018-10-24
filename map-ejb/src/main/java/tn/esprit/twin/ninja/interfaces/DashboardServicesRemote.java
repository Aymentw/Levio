package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Remote;

@Remote
public interface DashboardServicesRemote {
public Long getNumberFreelancers();
public Long getNumberEmployees();
public Long getNumberEmployeesInMandates();
}