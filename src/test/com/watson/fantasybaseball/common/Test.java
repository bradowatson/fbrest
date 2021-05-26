package com.watson.fantasybaseball.common;

import javax.ws.rs.NotAuthorizedException;

import com.watson.fantasybaseball.login.LoginDAO;
import com.watson.fantasybaseball.login.User;

public class Test {
	
	public static void main(String[] args ) {
		/*StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT count(*) FROM fantasybaseball.teams\n")
            .append("WHERE fantasybaseball.teams.email = 'brad.s.watson@gmail.com' ")
            .append("AND fantasybaseball.teams.password = 'password'");
        int result = Helper.getQueryResultAsInt(stmt.toString());
        System.out.println(result);*/
		//String url = "http://bradwatson.ddns.net:81/fantasybaseballREST/rest/ContractService/contracts/team/1";
		User user = new User("bradwatsonwork@gmail.com", "passwordasdfasdf");
		LoginDAO.authenticate(user);
		String result = LoginDAO.getApiKey(user);
		if(result != null) {
			System.out.println(result);
		} else {
			throw new NotAuthorizedException("401 - Unauthorized.");
		}
	}

}