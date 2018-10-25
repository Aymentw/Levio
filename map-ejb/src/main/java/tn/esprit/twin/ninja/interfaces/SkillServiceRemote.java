package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.Skill;

@Remote
public interface SkillServiceRemote {

	public List<Skill> getAllSkills();

}
