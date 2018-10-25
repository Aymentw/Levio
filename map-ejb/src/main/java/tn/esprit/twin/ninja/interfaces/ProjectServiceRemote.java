package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Remote;
import tn.esprit.twin.ninja.persistence.Project;

@Remote
public interface ProjectServiceRemote {
	
	public void addProject(Project p);
	public void deleteProject(int idProject);
	public void updateProject(int idProject);
}