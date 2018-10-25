package tn.esprit.twin.ninja.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin.ninja.persistence.Leave;

@Remote
public interface LeaveServiceRemote {
	
	public void addLeave(int ressourceId,Leave l);
	public void updateLeave(int leaveId);
	public void deleteLeave(int leaveId);
	public List<Leave> getAllLeave();
	

}
