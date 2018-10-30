package tn.esprit.twin.ninja.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.SkillServiceLocal;
import tn.esprit.twin.ninja.persistence.Skill;


@Stateless
public class SkillService implements SkillServiceLocal {
	
	@PersistenceContext(unitName="LevioMap-ejb")
	EntityManager em;

	@Override
	public List<Skill> getBestSkills() {
		return em.createQuery("SELECT s FROM Skill s ORDER BY s.rating DESC",Skill.class).getResultList();
	}

	@Override
	public void addSkill(Skill s) {
		em.persist(s);
		
	}


}
