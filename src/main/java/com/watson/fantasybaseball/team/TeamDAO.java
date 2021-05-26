package com.watson.fantasybaseball.team;

import com.watson.fantasybaseball.common.Helper;

public class TeamDAO {
	
	public static String getTeamList() {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT fantasybaseball.teams.TeamId, fantasybaseball.teams.TeamName, fantasybaseball.teams.LogoURL\n")
	        .append("FROM fantasybaseball.teams\n")
            .append("WHERE fantasybaseball.teams.display = '1'");
        String result = Helper.getQueryResultAsJson(stmt.toString());
        return result.replaceAll("null", "0").replaceAll("NULL", "0").trim();
	}

}
