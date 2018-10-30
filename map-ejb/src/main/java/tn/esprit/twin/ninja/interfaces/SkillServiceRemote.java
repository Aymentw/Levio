package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.Skill;

@Remote
public interface SkillServiceRemote {

	public void addSkill(Skill s);

	public List<Skill> getAllSkills();

}
