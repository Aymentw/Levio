package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

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
	
	
	public Integer getId() {
		return id;
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
	