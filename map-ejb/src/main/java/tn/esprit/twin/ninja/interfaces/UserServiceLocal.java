package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Local;

import tn.esprit.twin.ninja.persistence.Request;

import tn.esprit.twin.ninja.persistence.User;

import tn.esprit.twin.ninja.persistence.UserRoles;

import java.util.List;


@Local
public interface UserServiceLocal {


	public boolean hasRole(int userId, UserRoles role);
	public void treatClientRequest(int userId, int requestId);
	public List<Request> getAllRequests();
	public List<Request> getTreatedRequests();
	public List<Request> getUnTreatedRequests();



}
