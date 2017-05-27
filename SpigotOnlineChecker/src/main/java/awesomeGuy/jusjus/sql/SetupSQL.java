package awesomeGuy.jusjus.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;

import awesomeGuy.jusjus.Core;
import awesomeGuy.jusjus.sql.util.SQLConnection;
import awesomeGuy.jusjus.sql.util.Table;

public class SetupSQL {

	public static Table table;
	public SQLConnection sqla;
	public static Connection co;
	private static boolean valid = false;

	public SetupSQL() {
		try {
			String hostname = Core.getInstance().sqlConfig.get().getString("sql.hostname");
			String portnumber = Core.getInstance().sqlConfig.get().getString("sql.port");
			String database = Core.getInstance().sqlConfig.get().getString("sql.database"); 
			String username = Core.getInstance().sqlConfig.get().getString("sql.username"); 
			String password = Core.getInstance().sqlConfig.get().getString("sql.password");
			sqla = new SQLConnection(hostname, portnumber, database, username, password);
			co = sqla.getConnection();
			PreparedStatement sql = co
					.prepareStatement(
							  "CREATE TABLE IF NOT EXISTS cache(uuid VARCHAR(255) NOT NULL PRIMARY KEY, name VARCHAR(100), totaltime INT default 0, firstjoined VARCHAR(100))");
			table = new Table(co, "cache");
			sql.executeUpdate();
			Core.getInstance().getLogger().info("Succesfull connected to MYSQL");
			valid = true;
		} catch (Exception e) {
			Core.getInstance().getLogger().info("-------- !WARNING! --------");
			Core.getInstance().getLogger().info("Can't connect to your SQL server. Make sure SQL.yml is correct.... Shutting down..");
			Core.getInstance().getLogger().warning("PROBLEM: "+e.getMessage());
			Core.getInstance().getLogger().info("-------- !WARNING! --------");
			Core.getInstance().getPluginLoader().disablePlugin(Core.getInstance());
			valid = false;
			return;
		}

	}
	
	public static Connection getConnection() {
		return co;
	}
	
	public static Table getTable() {
		return table;
	}
	
	public static boolean isValid(){
		return valid;
	}
}
