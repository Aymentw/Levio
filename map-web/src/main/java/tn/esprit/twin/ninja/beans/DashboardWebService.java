package tn.esprit.twin.ninja.beans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin.ninja.interfaces.DashboardServicesLocal;

@Path("dashboard")
@RequestScoped
public class DashboardWebService {
	@EJB(beanName = "DashboardService")
	DashboardServicesLocal dashboardService;
	
	@Path("report/{resourceId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateResourceReport(@PathParam(value="resourceId") int resourceId) throws IOException{
		dashboardService.reportResource(resourceId);
		return Response.status(Status.OK).build();
	}
	@Path("numresclient/{clientId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response numberOfResourcesToClient(@PathParam(value="clientId")int clientId){
		return Response.ok(dashboardService.numberOfResourcesToClient(clientId),MediaType.APPLICATION_JSON).build();
	}
	@Path("skills")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response mostUsedSkills(){
		return Response.ok(dashboardService.mostUsedSkills(),MediaType.APPLICATION_JSON).build();
	}
	@Path("mostprofitproject")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response mostProfitProject(){
		return Response.ok(dashboardService.mostProfitProject(),MediaType.APPLICATION_JSON).build();
	}
	@Path("mostprofitclient")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response mostProfitClient(){
		return Response.ok(dashboardService.mostProfitClient(),MediaType.APPLICATION_JSON).build();
	}
	@Path("numresmandates")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNumberEmployeesInMandates() {
		return Response.ok(dashboardService.getNumberEmployeesInMandates(),MediaType.APPLICATION_JSON).build();
	}
}