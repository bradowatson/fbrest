package com.watson.fantasybaseball.contract;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.watson.fantasybaseball.login.LoginDAO;

@Path("/ContractService")
public class ContractService {

	@GET
	@Path("/contracts/team/{teamId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String getContracts(@PathParam("teamId") int teamId, ContainerRequestContext requestContext) throws NotAuthorizedException {
		String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if(LoginDAO.isAuthorized(token)) { 
			return ContractDAO.getContractsForTeamString(teamId);
		}
		throw new NotAuthorizedException("401 - Unauthorized.");
		//return null;
	}
	
	@GET
	@Path("/contracts/player/{playerId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Contract getPlayerContract(@PathParam("playerId") int playerId, ContainerRequestContext requestContext) throws NotAuthorizedException {
		String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if(LoginDAO.isAuthorized(token)) { 
			return ContractDAO.getContractForPlayer(playerId);
		}
		throw new NotAuthorizedException("401 - Unauthorized.");
	}
}