package tn.esprit.twin.ninja.interfaces.recruitement;

import javax.ejb.Local;

import tn.esprit.twin.ninja.persistence.recruitment.Letter;

@Local
public interface LetterServiceLocal {
	public Letter getLetterEmp(int idApplication);
	public boolean setLetterEmpUser(int idApplication,Letter letter);
	public boolean setLetterEmpAdmin(int idApplication,Letter letter);	

}
