 package tn.esprit.twin.ninja.interfaces.recruitement;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.recruitment.Application;
import tn.esprit.twin.ninja.persistence.recruitment.State;

@Local
public interface ApplicationServiceLocal {
public int addApplication(Application a);
public Application getApplication(int idRessource);
public boolean deleteApplication(int idApplication);
public boolean setStateApplication(Application application);
public List<Application> getAllApplication();
public List<Application> getApplicationByState(State state);
public boolean assignRessource(int idr,int idp);

}
