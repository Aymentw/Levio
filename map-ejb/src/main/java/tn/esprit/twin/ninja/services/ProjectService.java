package tn.esprit.twin.ninja.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.twin.ninja.interfaces.ProjectServiceLocal;
import tn.esprit.twin.ninja.interfaces.ProjectServiceRemote;
import tn.esprit.twin.ninja.persistence.Client;
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
	public void updateProject(Project p) {
		Project project = em.find(Project.class, p.getId());
		project.setAdress(p.getAdress());
		project.setPhoto(p.getPhoto());
	}
	
	@Override
	public void affectProjecttoClient(int projectId, int clientId) {
		Project p = em.find(Project.class, projectId);
		Client c = em.find(Client.class, clientId);
		p.setClients(c);
		//em.merge(p);
	}


}
