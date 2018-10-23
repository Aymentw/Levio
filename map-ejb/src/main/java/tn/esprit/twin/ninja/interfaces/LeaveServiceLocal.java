package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import tn.esprit.twin.ninja.persistence.Leave;

public interface LeaveServiceLocal {

	public void addLeave(int ressourceId,Leave l);
	public void updateLeave(int leaveId);
	public void deleteLeave(int leaveId);
	public List<Leave> getAllLeave();
	
	
}
