package com.watson.fantasybaseball.login;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import com.watson.fantasybaseball.common.Helper;

public class LoginDAO {
	
	private static final int KEY_LENGTH = 256;
	private static final String AUTHENTICATION_SCHEME = "Bearer";
	
    private static String genKey() {

        KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        keyGen.init(KEY_LENGTH);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }
    
    public static String getApiKey(User user) {
    	if(user.isAuthenticated()) {
    		String key = genKey();
    		user.setApiKey(key);
    		updateUserKey(user, key);
    		writeNewKeyToDb(user, key);
    		return "{\r\n"
    				+ "	\"result\": {\r\n"
    				+ "		\"Key\": \"" + key + "\",\r\n"
    				+ "		\"TeamId\": " + user.getTeamId() + ",\r\n"
    	    		+ "		\"TeamName\": \"" + user.getName() + "\"\r\n"
    				+ "	}\r\n"
    				+ "}";
    	}
    	return null;
    }
    
    public static void updateUserKey(User user, String key) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("UPDATE fantasybaseball.teams\n")
        	.append("SET fantasybaseball.teams.token = '").append(key).append("'\n")
            .append("WHERE fantasybaseball.teams.teamID = '").append(user.getTeamId()).append("'");
        Helper.writeToDb(stmt.toString());
	}
    
    public static void writeNewKeyToDb(User user, String key) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("INSERT INTO fantasybaseball.teams\n")
        	.append("(Email, Token)\n")
            .append("VALUES ('").append(user.getName()).append("', '").append(key).append("')");
        Helper.writeToDb(stmt.toString());
        System.out.println(stmt.toString());
	}
    
    private static String getTeamName(int teamId) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT TeamName FROM fantasybaseball.teams\n")
        .append("WHERE fantasybaseball.teams.teamID = '").append(teamId).append("' ");
        String result = Helper.getQueryResult(stmt.toString());
        return result.trim();
    }

	public static void authenticate(User user) {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT TeamID FROM fantasybaseball.teams\n")
            .append("WHERE fantasybaseball.teams.email = '").append(user.getName()).append("' ")
            .append("AND fantasybaseball.teams.password = '").append(user.getPassword()).append("'");
        try {
	        int result = Helper.getQueryResultAsInt(stmt.toString());
	        if(result > 0) {
	        	user.setAuthenticated(true);
	        	user.setTeamId(result);
	        	user.setName(getTeamName(result));
	        } else {
	        	user.setAuthenticated(false);
	        }
        } catch (Exception e) {
        	user.setAuthenticated(false);
        }
        
    }
    
    public static boolean isAuthorized(String apiKey) {
    	if(null == apiKey) {
    		return false;
    	}
    	apiKey = apiKey.replace(AUTHENTICATION_SCHEME, "").trim();
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT count(*) FROM fantasybaseball.teams\n")
            .append("WHERE fantasybaseball.teams.token = '").append(apiKey).append("'");
        int result = Helper.getQueryResultAsInt(stmt.toString());
        if(result > 0) {
        	return true;
        }
        return false;
    }

}
