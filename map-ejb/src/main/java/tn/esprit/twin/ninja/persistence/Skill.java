package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
public class Skill implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private SkillName name;
	private int rating;
	@ManyToOne	
	private Ressource ressource;
	
	public Skill() {
		super();
	}

	@JsonBackReference(value="RessourceSkills")
	public Ressource getRessource() {
		return ressource;
	}

	public void setName(SkillName name) {
		this.name = name;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
