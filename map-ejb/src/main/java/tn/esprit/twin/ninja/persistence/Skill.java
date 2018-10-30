package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Entity
public class Skill implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	private SkillName name;
	private int rating;
	@ManyToOne
	private Ressource ressource;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "request_id")
	private Request request;
	public Skill() {
		super();
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Ressource getRessource() {
		return ressource;
	}


	public SkillName getName() {
		return name;
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
