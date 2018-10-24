package tn.esprit.twin.ninja.persistence.recruitment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Folder implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String letterEmpUser;
	private String letterEmpAdmin;

	@Enumerated(EnumType.STRING)
	private StateFolder stateFolder;
	@Enumerated(EnumType.STRING)
	private StateMinister stateMinister;
	@Enumerated(EnumType.STRING)
	private StateLetter stateLetter;
	@OneToOne(mappedBy="folder")
	private Application application;
	
	
	
	
	public String getLetterEmpUser() {
		return letterEmpUser;
	}
	public void setLetterEmpUser(String letterEmpUser) {
		this.letterEmpUser = letterEmpUser;
	}
	public String getLetterEmpAdmin() {
		return letterEmpAdmin;
	}
	public void setLetterEmpAdmin(String letterEmpAdmin) {
		this.letterEmpAdmin = letterEmpAdmin;
	}
	
	public StateMinister getStateMinister() {
		return stateMinister;
	}
	public void setStateMinister(StateMinister stateMinister) {
		this.stateMinister = stateMinister;
	}
	public StateLetter getStateLetter() {
		return stateLetter;
	}
	public void setStateLetter(StateLetter stateLetter) {
		this.stateLetter = stateLetter;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StateFolder getStateFolder() {
		return stateFolder;
	}
	public void setStateFolder(StateFolder stateFolder) {
		this.stateFolder = stateFolder;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Folder(String letterEmpAdmin,String letterEmpUser, StateFolder stateFolder, StateMinister stateMinister, StateLetter stateLetter) {
		super();
		this.letterEmpAdmin = letterEmpAdmin;
		this.letterEmpUser = letterEmpUser;
		this.stateFolder = stateFolder;
		this.stateMinister = stateMinister;
		this.stateLetter = stateLetter;
	}
	public Folder() {
		super();
	}
	
	
	

}
