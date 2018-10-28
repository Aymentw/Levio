package tn.esprit.twin.ninja.services.recruitement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.recruitement.InterviewServiceLocal;

@Stateless
public class InterviewService implements InterviewServiceLocal{
	@PersistenceContext(unitName = "LevioMap-ejb")
	private EntityManager em;
	

}
