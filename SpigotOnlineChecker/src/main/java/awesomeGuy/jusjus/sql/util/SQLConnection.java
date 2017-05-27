package awesomeGuy.jusjus.sql.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	private final String hostname;
	private final String port;
	private final String database;
	private final String username;
	private final String password;

	public SQLConnection(String hostname, String port, String database, String username, String password) {
		//System.out.println(hostname + " -1 "+port + " - "+database + " - "+username + " - "+password);
		this.hostname = hostname;
		this.port = port; 
		this.database = database;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() throws Exception {
		String url = "jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database;
		return DriverManager.getConnection(url, this.username, this.password);
	}

}
