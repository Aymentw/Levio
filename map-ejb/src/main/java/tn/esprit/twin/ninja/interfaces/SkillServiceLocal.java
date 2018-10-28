package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import tn.esprit.twin.ninja.persistence.Skill;

public interface SkillServiceLocal {
	
	public void addSkill(Skill s);
	public List<Skill> getAllSkills();
	

}
