package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Local;

import tn.esprit.twin.ninja.persistence.Request;

@Local
public interface UserServiceLocal {

	public boolean hasRole(int userId, String role);
	public void treatRequest(int userId, int requestId);
	public void untreatRequest(int userId, int requestId);

}