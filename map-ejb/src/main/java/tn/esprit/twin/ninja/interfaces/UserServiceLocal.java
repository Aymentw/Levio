package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Local;

import tn.esprit.twin.ninja.persistence.*;

import java.util.List;
import java.util.Set;


@Local
public interface UserServiceLocal {


	public boolean hasRole(int userId, UserRoles role);
	public void treatClientRequest(int userId, int requestId);
	public List<Request> getAllRequests();
	public List<Request> getTreatedRequests();
	public List<Request> getUnTreatedRequests();
	public List<Ressource> getRessourceBySkills(Set<Skill> skills);
	public void deleteTreatedRequests();


}
