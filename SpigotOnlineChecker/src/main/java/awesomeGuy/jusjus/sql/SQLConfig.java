package awesomeGuy.jusjus.sql;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import awesomeGuy.jusjus.Core;

public class SQLConfig {

	private File file;
	private FileConfiguration data;
	private Core core;

	public SQLConfig(Core core) {
		this.core = core;
		file = new File(core.getDataFolder(), "SQL.yml");
		data = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration get() {
		return data;
	}

	public void save() {
		try {
			data.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reload() {
		file = new File(core.getDataFolder(), "sql.yml");
		data = YamlConfiguration.loadConfiguration(file);
	}

	public void create() {
		data.options().header("#Database Config - Makes a file storage outdated :P");
		get().set("enabled", false);
		get().set("SQL.hostname", "localhost");
		get().set("SQL.username", "root");
		get().set("SQL.password", "");
		get().set("SQL.database", "DataBase");
		get().set("SQL.port", 3306);
		save();
		core.getLogger().info("SQL Config created for you ;)");
	}

	public boolean exists() {
		if (new File(core.getDataFolder(), "sql.yml").exists())
			return true;
		return false;
	}

}
