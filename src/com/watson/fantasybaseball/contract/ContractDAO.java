package com.watson.fantasybaseball.contract;

import java.util.ArrayList;
import java.util.List;

import com.watson.fantasybaseball.common.Helper;

public class ContractDAO {
	
	public static void main(String args[]) {
		List<Contract> contracts = getContractsForTeam(1);
		for(Contract contract : contracts) {
			System.out.println(contract.getName());
			System.out.println(contract.getPrice());
			System.out.println(contract.getLength());
		}
	}
	
	public static List<Contract> getContractsForTeam(int teamId) {
		List<Contract> contracts = new ArrayList<Contract>(); 
		String list[] = getContractsForTeamSQL(teamId).split("\n");
		for(String l : list) {
			String split[] = l.split(";");
			boolean rostered = false;
			if(split[4].toUpperCase().charAt(0) == 'Y') {
				rostered = true;
			}
			Contract contract = new Contract(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), rostered);
			contracts.add(contract);
		}
		return contracts;
	}
	
	public static String getContractsForTeamString(int teamId) {
        StringBuilder stmt = new StringBuilder();
        //stmt.append("SELECT fantasybaseball.players.Name, fantasybaseball.contracts.2019Price, fantasybaseball.contracts.extensionlength, fantasybaseball.contracts.startyear, fantasybaseball.contracts.CurrentlyRostered\n")
        stmt.append("SELECT players.PlayerID, players.Name, players.Team, players.Position, players.ESPNPrice, contracts.Number, contracts.TeamID, contracts.2014Price, contracts.2015Price, contracts.2016Price, contracts.2017Price, contracts.2018Price, contracts.2019Price, contracts.2020Price, contracts.2021Price, contracts.2022Price, contracts.2023Price, contracts.2024Price, contracts.2025Price, contracts.CurrentlyRostered, contracts.Franchise, contracts.AdditionType, contracts.Length, contracts.StartYear, contracts.ExtensionLength, contracts.ExtensionStartYear FROM contracts INNER JOIN players ON players.PlayerID=contracts.PlayerID WHERE contracts.TeamID = '").append(teamId).append("'");
        String result = Helper.getQueryResultAsJson(stmt.toString());
        return result.replaceAll("null", "0").replaceAll("NULL", "0").trim();
	}
	
    private static String getContractsForTeamSQL(int teamId) {
        StringBuilder stmt = new StringBuilder();
        //stmt.append("SELECT fantasybaseball.players.Name, fantasybaseball.contracts.2019Price, fantasybaseball.contracts.extensionlength, fantasybaseball.contracts.startyear, fantasybaseball.contracts.CurrentlyRostered\n")
        stmt.append("SELECT players.PlayerID, players.Name, players.Team, players.Position, players.ESPNPrice, contracts.Number, contracts.TeamID, contracts.2014Price, contracts.2015Price, contracts.2016Price, contracts.2017Price, contracts.2018Price, contracts.2019Price, contracts.2020Price, contracts.2021Price, contracts.2022Price, contracts.2023Price, contracts.2024Price, contracts.2025Price, contracts.CurrentlyRostered, contracts.Franchise, contracts.AdditionType, contracts.Length, contracts.StartYear, contracts.ExtensionLength, contracts.ExtensionStartYear FROM contracts INNER JOIN players ON players.PlayerID=contracts.PlayerID WHERE contracts.TeamID = '").append(teamId).append("'");
        String result = Helper.getQueryResult(stmt.toString());
        return result.replaceAll("null", "0").replaceAll("NULL", "0").trim();
    }
    
	public static Contract getContractForPlayer(int playerId) {
		String result = getContractForPlayerSQL(playerId);
		String split[] = result.split(";");
		Contract contract = new Contract(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), true);
		return contract;
	}
    
    private static String getContractForPlayerSQL(int playerId) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT fantasybaseball.players.Name, fantasybaseball.contracts.2019Price, fantasybaseball.contracts.extensionlength, fantasybaseball.contracts.startyear\n")
            .append("FROM fantasybaseball.contracts\n")
            .append("INNER JOIN fantasybaseball.players\n")
            .append("ON fantasybaseball.players.PlayerID=fantasybaseball.contracts.PlayerID\n")
            .append("WHERE fantasybaseball.contracts.PlayerID = '").append(playerId).append("' AND fantasybaseball.contracts.currentlyrostered = 'Y'");
        String result = Helper.getQueryResult(stmt.toString());
        return result.replaceAll("null", "0").replaceAll("NULL", "0").trim();
    }	

}
