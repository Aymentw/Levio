package tn.esprit.twin.ninja.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.ProjectServiceLocal;
import tn.esprit.twin.ninja.interfaces.ProjectServiceRemote;
import tn.esprit.twin.ninja.persistence.Project;

@Stateless
public class ProjectService implements ProjectServiceLocal, ProjectServiceRemote {

	@PersistenceContext(unitName="LevioMap-ejb")
	EntityManager em;
	
	@Override
	public void addProject(Project p) {
		em.persist(p);
	}

	@Override
	public void deleteProject(int idProject) {
		em.remove(em.find(Project.class, idProject));
	}

	@Override
	public void updateProject(int idProject) {
		Project p = em.find(Project.class, idProject);
		p.setPhoto("image");
	}

}
