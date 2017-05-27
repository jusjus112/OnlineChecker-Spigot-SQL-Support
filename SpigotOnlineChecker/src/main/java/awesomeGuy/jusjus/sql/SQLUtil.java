package awesomeGuy.jusjus.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

import awesomeGuy.jusjus.sql.util.Table;

public class SQLUtil {
	
	Table table = SetupSQL.getTable();

	public void generateAccount(Player p) {
		try {
			if (!exists(p.getUniqueId())) {
				table.insert().insert("uuid").value(p.getUniqueId().toString()).execute();
				setFirstJoined(p.getUniqueId(), Long.toString(p.getFirstPlayed()));
				table.update().set("name", p.getPlayerListName())
						.where("uuid", p.getUniqueId().toString())
						.execute();
				return;
			} else {
				ResultSet res = table.select().where("uuid", p.getUniqueId().toString()).execute();
				res.first();
				String s = res.getString("name");
				if (s == null) {
					table.update().set("name", p.getName()).where("uuid", p.getUniqueId().toString())
							.execute();
					return;
				}
				if (!s.equals(p.getName())) {
					table.update().set("name", p.getName()).where("uuid", p.getUniqueId().toString())
							.execute();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean exists(UUID uuid) {
		try {
			ResultSet resultset = table.select().where("uuid", uuid.toString()).execute();
			return resultset.first();
		} catch (SQLException e) {
			return false;
		}
	}

	public String getName(UUID uuid) {
		try {
			ResultSet resultset = table.select().where("uuid", uuid.toString()).execute();
			resultset.first();
			return resultset.getString("name");
		} catch (SQLException e) {
			return "";
		}
	}

	public void setName(UUID uuid, String Name) {
		table.update().set("name", Name).where("uuid", uuid.toString()).execute();
	}
	
	public int getTotalTime(UUID uuid) {
		try {
			ResultSet resultset = table.select().where("uuid", uuid.toString()).execute();
			resultset.first();
			return resultset.getInt("totaltime");
		} catch (SQLException e) {
			return 0;
		}
	}

	public void setTotalTime(UUID uuid, int time) {
		table.update().set("totaltime", time).where("uuid", uuid.toString()).execute();
	}
	
	public String getFirstJoined(UUID uuid) {
		try {
			ResultSet resultset = table.select().where("uuid", uuid.toString()).execute();
			resultset.first();
			return resultset.getString("firstjoined");
		} catch (SQLException e) {
			return null;
		}
	}

	public void setFirstJoined(UUID uuid, String time) {
		table.update().set("firstjoined", time).where("uuid", uuid.toString()).execute();
	}
}
