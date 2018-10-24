package tn.esprit.twin.ninja.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Request implements Serializable {
	@Id
	private Integer id;
	private String resourceType;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private float costs;
	private Boolean status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public float getCosts() {
		return costs;
	}
	public void setCosts(float costs) {
		this.costs = costs;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	@ManyToMany (mappedBy="requests")
	private List<Client> clients;

}
