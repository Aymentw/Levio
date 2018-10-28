package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties({ "mandate"})
@Entity
public class Ressource extends User implements Serializable {

	private int seniority;
	private String sector;
	@Enumerated(EnumType.STRING)
	private RessourceState state;
	private String profile;
	private String contract_type;
	@OneToMany(mappedBy = "ressource", fetch = FetchType.EAGER)
	private List<Leave> leaves;
	@OneToMany(mappedBy = "ressource", fetch = FetchType.EAGER)	
	private List<Skill> skills;
	@OneToMany(mappedBy = "ressource")
	private List<Mandate> mandate;
	@ManyToOne
	private Project project;

	@JsonBackReference(value="RessourceProject")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	@JsonManagedReference(value="RessourceLeaves")
	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	@JsonManagedReference(value="RessourceSkills")
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
