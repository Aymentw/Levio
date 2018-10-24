package tn.esprit.twin.ninja.interfaces;

import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.Request;

@Remote
public interface UserServiceRemote {

	public boolean hasRole(int userId, String role);
	public void treatRequest(int userId, int requestId);
	public void untreatRequest(int userId, int requestId);

}
