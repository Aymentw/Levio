package tn.esprit.twin.ninja.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin.ninja.interfaces.SkillServiceLocal;
import tn.esprit.twin.ninja.persistence.Skill;

@Path("skill")
@RequestScoped
public class SkillRessource {

	@EJB(beanName = "SkillService")
	SkillServiceLocal skillService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSkill(Skill s) {
		skillService.addSkill(s);
		return Response.status(Status.CREATED).entity(s).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBestSkills(){
		
		skillService.getBestSkills();
		return Response.ok(skillService.getBestSkills()).build();
		
	}

}
