package tn.esprit.twin.ninja.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin.ninja.interfaces.LeaveServiceLocal;
import tn.esprit.twin.ninja.persistence.Leave;

public class LeaveService implements LeaveServiceLocal {
	
	@PersistenceContext(unitName="LevioMap-ejb")
	EntityManager em;

	@Override
	public void addLeave(int ressourceId, Leave l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLeave(int leaveId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLeave(int leaveId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Leave> getAllLeave() {
		// TODO Auto-generated method stub
		return null;
	}


}
