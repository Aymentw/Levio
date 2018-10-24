package tn.esprit.twin.ninja.persistence.recruitment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table

public class Test implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String typeTest;
	private String version;
	private double note;
	@ManyToMany
	private List<Question> listQuestion;
	
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeTest() {
		return typeTest;
	}
	public void setTypeTest(String typeTest) {
		this.typeTest = typeTest;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Test(String typeTest, String version) {
		super();
		this.typeTest = typeTest;
		this.version = version;
	}
	public Test() {
		super();
	}
	

}
