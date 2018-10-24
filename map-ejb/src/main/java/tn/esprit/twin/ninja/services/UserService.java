package tn.esprit.twin.ninja.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.UserServiceRemote;
import tn.esprit.twin.ninja.persistence.Request;
import tn.esprit.twin.ninja.persistence.User;

@Stateless
public class UserService implements UserServiceRemote {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean hasRole(int userId, String role) {
		User user = em.find(User.class, userId);
		if (user.getRole().compareTo(role) == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void treatRequest(int userId, int requestId) {
		if (this.hasRole(userId, "ROLE_SUPERVISOR")) {
			Request request = em.find(Request.class, requestId);
			request.setStatus(true);
		}
	}

	@Override
	public void untreatRequest(int userId, int requestId) {
		if (this.hasRole(userId, "ROLE_SUPERVISOR")) {
		Request request = em.find(Request.class, requestId);
		request.setStatus(false);		
	}
	}

}
