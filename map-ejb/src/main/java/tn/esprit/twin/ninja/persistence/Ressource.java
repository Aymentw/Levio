package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"leaves","skills","mandate"})
@Entity
public class Ressource extends User implements Serializable {

	private int seniority;
	private String sector;
	@Enumerated(EnumType.STRING)
	private RessourceState state;
	private String profile;
	private String contract_type;
	
	
	@OneToMany(mappedBy = "ressource")
	private List<Leave> leaves;
	
	@ManyToMany(mappedBy = "ressources")
	private List<Skill> skills;
	@OneToMany (mappedBy = "ressource")
	private List<Mandate> mandate;

	
	
	public Ressource(int seniority, String sector, RessourceState state, String profile, String contract_type,
			List<Leave> leaves, List<Skill> skills) {
		super();
		this.seniority = seniority;
		this.sector = sector;
		this.state = state;
		this.profile = profile;
		this.contract_type = contract_type;
		this.leaves = leaves;
		this.skills = skills;
	}

	public Ressource() {
		super();
	}

	public int getSeniority() {
		return seniority;
	}

	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public RessourceState getState() {
		return state;
	}

	public void setState(RessourceState state) {
		this.state = state;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Mandate> getMandate() {
		return mandate;
	}

	public void setMandate(List<Mandate> mandate) {
		this.mandate = mandate;
	}
	
}
