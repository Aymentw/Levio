package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Remote;

@Remote
public interface DashboardServicesRemote {
public int getNumberFreelancers();
public int getNumberEmloyees();
}