package com.watson.fantasybaseball.common;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.watson.simplesql.DBUtils;
import com.watson.simplesql.SimpleConnection;

public class Helper {
	
    private static String token = "b86d0036dc2b93115e752c9de2e36ba1cee0b5bf1e6a2c13364cfb4f03a5ae4d";
    private static String authType = "Bearer";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://redhat:30306/fantasybaseball?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PASSWORD = "clemson";
	
	/*private final static String PROPS_FILE = "/usr/local/tomcat/webapps/fantasybaseballREST/web/WEB-INF/config/database.properties";
	
    private static Properties getProps() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPS_FILE));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }*/
    
    private static SimpleConnection getConnection() {
    	/*Properties props = getProps();
        String driver = props.getProperty("driver");
        String host = props.getProperty("host");
        String port = props.getProperty("port");
        String name = props.getProperty("db.name");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String params = props.getProperty("additional.params");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + name + params;*/
    	String context = "java:comp/env/jdbc/mysql";
        SimpleConnection conn = new SimpleConnection(context);
    	//SimpleConnection conn = new SimpleConnection(MYSQL_DRIVER, MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
        return conn;
    }
    
    public static String getQueryResult(String query) {
    	SimpleConnection conn = getConnection();
        return DBUtils.getQueryResultAsString(conn, query, ";");
    }
    
    public static int getQueryResultAsInt(String query) {
    	SimpleConnection conn = getConnection();
        return DBUtils.getQueryResultAsInt(conn, query);
    }
    
    public static char getQueryResultAsChar(String query) {
    	SimpleConnection conn = getConnection();
        return DBUtils.getQueryResultAsChar(conn, query);
    }
    
    public static void writeToDb(String statement) {
    	SimpleConnection conn = getConnection();
    	DBUtils.writeStatementToDb(conn, statement);
    }
    
    public static String getQueryResultAsJson(String query) {
    	SimpleConnection conn = getConnection();
    	return DBUtils.getQueryResultAsJSON(conn, query, ";");
    }
    
    public static int getYear() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.YEAR);
    }

}
