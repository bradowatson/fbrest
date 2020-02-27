package com.watson.fantasybaseball.common;

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
		User user = new User("brad.s.watson@gmail.com", "clemson");
		String key = LoginDAO.getApiKey(user);
		System.out.println(key);
	}

}