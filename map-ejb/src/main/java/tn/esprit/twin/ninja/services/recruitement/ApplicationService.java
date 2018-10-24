package tn.esprit.twin.ninja.services.recruitement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.recruitement.ApplicationServiceLocal;
import tn.esprit.twin.ninja.persistence.recruitment.Application;

@Stateless
public class ApplicationService implements ApplicationServiceLocal {
	@PersistenceContext(unitName = "LevioMap-ejb")
	private EntityManager em;
	@Override
	public int addApplication(Application a) {
		em.persist(a);
		return a.getId();
	}

	@Override
	public Application getApplication(int idRessource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteApplication(int idApplication) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setStateApplication(int idApplication) {
		// TODO Auto-generated method stub
		return 0;
	}

}
