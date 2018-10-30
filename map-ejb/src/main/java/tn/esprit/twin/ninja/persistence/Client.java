package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.twin.ninja.persistence.Request;

enum clientCategory {
	privateCat, publicCat;
}


@Entity
public class Client extends User implements Serializable {
	private String name;
	private clientCategory category;
	@JsonIgnore
	@ManyToMany
	private List<Request> requests;
	
	public List<Request> getRequests() {
		return requests;
	}
	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public clientCategory getCategory() {
		return category;
	}
	public void setCategory(clientCategory category) {
		this.category = category;
	}	
	
}
	