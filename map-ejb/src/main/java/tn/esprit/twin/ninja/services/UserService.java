package tn.esprit.twin.ninja.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.UserServiceLocal;
import tn.esprit.twin.ninja.interfaces.UserServiceRemote;
import tn.esprit.twin.ninja.persistence.Request;
import tn.esprit.twin.ninja.persistence.Ressource;
import tn.esprit.twin.ninja.persistence.User;
import tn.esprit.twin.ninja.persistence.UserRoles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class UserService implements UserServiceLocal {
    @PersistenceContext(unitName="LevioMap-ejb")
    EntityManager em;
	
	public boolean hasRole(int userId, UserRoles role) {
		User user = em.find(User.class, userId);
		return user.getRole() == role;
	}


	@Override
	public void treatClientRequest(int userId, int requestId) {
		if (this.hasRole(userId, UserRoles.ROLE_SUPERVISOR)) {
			Request request = em.find(Request.class, requestId);
			RessourceService rs = new RessourceService();
			List<Ressource> availableResources = rs.getRessourceBySkills(request.getSkills());
			if(!availableResources.isEmpty()) {
				request.setStatus(true);	
			}
			request.setStatus(false);
        }
	}

	@Override
	public List<Request> getAllRequests() {
		return  em.createQuery("select r from Request r", Request.class).getResultList();
	}

	@Override
	public List<Request> getTreatedRequests() {
		return  em.createQuery("select r from Request r where r.status = true", Request.class).getResultList();
	}

	@Override
	public List<Request> getUnTreatedRequests() {
		return  em.createQuery("select r from Request r where r.status = false or r.status is null", Request.class).getResultList();
	}
}
