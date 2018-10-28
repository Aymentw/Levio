package tn.esprit.twin.ninja.persistence.recruitment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table

public class Interview implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInterview;
	@Enumerated(EnumType.STRING)
	private TypeInterview typeInterview;
	@ManyToOne
	private Application application;
	@Enumerated(EnumType.STRING)
	private StateInterview stateInterview;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateInterview() {
		return dateInterview;
	}
	public void setDateInterview(Date dateInterview) {
		this.dateInterview = dateInterview;
	}
	public TypeInterview getTypeInterview() {
		return typeInterview;
	}
	public void setTypeInterview(TypeInterview typeInterview) {
		this.typeInterview = typeInterview;
	}
	
	

}
