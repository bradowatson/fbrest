package com.watson.fantasybaseball.login;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Authentication")
public class LoginService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@POST
	@Path("/login")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes("application/json")
	public String authenticateUser(final User user) {
		if(LoginDAO.getApiKey(user) != null) {
			return LoginDAO.getApiKey(user);
		}
		throw new NotAuthorizedException("401 - Unauthorized.");
	}

}
