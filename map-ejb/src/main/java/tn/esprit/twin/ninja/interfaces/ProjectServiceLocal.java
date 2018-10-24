package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Local;
import tn.esprit.twin.ninja.persistence.Project;

@Local
public interface ProjectServiceLocal {
	
	public void addProject(Project p);
	public void deleteProject(int idProject);
	public void updateProject(int idProject);
}
