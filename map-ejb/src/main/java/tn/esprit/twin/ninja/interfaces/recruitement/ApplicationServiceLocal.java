 package tn.esprit.twin.ninja.interfaces.recruitement;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.recruitment.Application;

@Local
public interface ApplicationServiceLocal {
public int addApplication(Application a);
public Application getApplication(int idRessource);
public int deleteApplication(int idApplication);
public int setStateApplication(int idApplication);

}
