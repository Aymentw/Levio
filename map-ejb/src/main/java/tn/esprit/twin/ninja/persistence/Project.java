package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;


enum projectType {
	currentClient, newClient, finishedContract;
}

@JsonIgnoreProperties({ "mandates"})
@Entity
@Table(name="project")
public class Project implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private projectType type;
	private int num_ressource_all;
	private int num_ressource_levio;
	private Date start_date;
	private Date end_date;
	private String adress;
	private String photo;
	private boolean archived;
	@ManyToOne
	private Client client;
	@JsonIgnore
	@OneToMany (mappedBy="project")
	List<Mandate> mandates;
	@JsonIgnore
	@OneToMany(mappedBy="project")
	private List<Ressource> ressources;
	
	@JsonManagedReference(value="RessourceProject")
	public List<Ressource> getRessources() {
		return ressources;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public projectType getType() {
		return type;
	}

	public void setType(projectType type) {
		this.type = type;
	}

	public int getNum_ressource_all() {
		return num_ressource_all;
	}

	public void setNum_ressource_all(int num_ressource_all) {
		this.num_ressource_all = num_ressource_all;
	}

	public int getNum_ressource_levio() {
		return num_ressource_levio;
	}

	public void setNum_ressource_levio(int num_ressource_levio) {
		this.num_ressource_levio = num_ressource_levio;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Mandate> getMandates() {
		return mandates;
	}

	public void setMandates(List<Mandate> mandates) {
		this.mandates = mandates;
	}

	public void setRessources(List<Ressource> ressources) {
		this.ressources = ressources;
	}
}
