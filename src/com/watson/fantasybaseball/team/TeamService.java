package com.watson.fantasybaseball.team;

import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.watson.fantasybaseball.login.LoginDAO;

@Path("/TeamService")
public class TeamService {
	
	@GET
	@Path("/teams")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String getTeams(ContainerRequestContext requestContext) throws NotAuthorizedException {
		String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if(LoginDAO.isAuthorized(token)) { 
			return TeamDAO.getTeamList();
		}
		throw new NotAuthorizedException("401 - Unauthorized.");
		//return null;
	}

}
