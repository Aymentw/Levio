package tn.esprit.twin.ninja.interfaces.recruitement;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local

public interface FolderServiceLocal {
	public String getLetterEmp(int idApplication);
	public int setLetterEmpUser(String letter);
	public int setLetterEmpAdmin(String letter);	
	public boolean getStateMinister(int idApplication);
	public int setStateMinister(int idApplication);

}
