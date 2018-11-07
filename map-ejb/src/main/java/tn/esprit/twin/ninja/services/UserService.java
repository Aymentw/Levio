package tn.esprit.twin.ninja.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.twin.ninja.interfaces.UserServiceLocal;
import tn.esprit.twin.ninja.interfaces.UserServiceRemote;
import tn.esprit.twin.ninja.persistence.*;

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
			request.setStatus(true);
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

	public List<Ressource> getRessourceBySkills(Set<Skill> skills) {

		TypedQuery<Ressource> query = em.createQuery("SELECT r FROM Ressource r where r.state = 'available'",Ressource.class);
		List<Ressource> availableResources = query.getResultList();
		List<Ressource> resources = new ArrayList<>();
		for (Ressource r : availableResources) {
			for(Skill rs : skills) {
				for(Skill s : r.getSkills()) {
					if(s.getName() == rs.getName()) {
						resources.add(r);
					}
				}
			}
		}
		return resources;

	}
	
	@Override
	public void deleteTreatedRequests() {
		List<Request> listRequests = new ArrayList<>();
		listRequests = em.createQuery("select r from Request r where r.status = :stat",Request.class).setParameter("stat", true).getResultList();
		for(Request r : listRequests) {
			em.createNativeQuery("delete from client_request where requests_id = :id").setParameter("id", r.getId()).executeUpdate();
			for(Skill s : r.getSkills()) {
				s.setRequest(null);
			}
			r.getSkills().clear();
			em.remove(r);
		}
		
	}

}
