package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GenerationType;
import tn.esprit.twin.ninja.persistence.Request;

enum clientCategory {
	privateCat, publicCat;
}

enum clientType {
	currentClient, newClient, finishedContract;
}

@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private clientCategory category;
	private clientType type;
	@JsonIgnore
	@ManyToMany
	private List<Request> requests;
	
	public Integer getId() {
		return id;
	}
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
	public clientType getType() {
		return type;
	}
	public void setType(clientType type) {
		this.type = type;
	}
	
	
}
	