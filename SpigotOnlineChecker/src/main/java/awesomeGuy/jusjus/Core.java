package awesomeGuy.jusjus;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import awesomeGuy.jusjus.gui.GlobalGUI;
import awesomeGuy.jusjus.manager.ActiveManager;
import awesomeGuy.jusjus.manager.RunnableManager;
import awesomeGuy.jusjus.sql.SQLUtil;
import awesomeGuy.jusjus.sql.SetupSQL;
import awesomeGuy.jusjus.sql.StoreMaps;
import awesomeGuy.jusjus.util.UtilFile;
import awesomeGuy.jusjus.util.UtilTime;
import awesomeGuy.jusjus.util.mani.Devro;

@Devro(pluginName = "OnlineChecker" , serverColor = ChatColor.RED, permissionsStartsWith = "onlinechecker.")
public class Core extends JavaPlugin {

	private static Core instance;
	public static Core getInstance() {return instance;}
	
	public UtilFile sqlConfig, data, config;
    private String dataFolder = getDataFolder() + File.separator + "Cache";
    
    public boolean useSQL;
    public Devro devro;
    
    /**
     * TODO
	 * - Use pex e.t.c. rank checker
	 * - Use essentials checks.
	 * - Use Factions checks
	 * 
	 * - Add maybe inventory checker / Enderchest.
	 * - Add gamemode checker.
	 * - Add Auto Updater
	 * - Add auto update inventories
	 * - Add counted since
	 * 
	 * @BUGS
	 * - Wanneer iemand joint terwijl sql enabled is maar niet kan connecten geeft ie duizende errors
	 */
	
	public void onEnable() {
		instance = this;
		sqlConfig = new UtilFile(this.getDataFolder().getPath(), "sql");
		config = new UtilFile(this.getDataFolder().getPath(), "config");
		
		if (!sqlConfig.exists())
			sqlConfig.setup(new Object[]{
					"enabled", false, "sql.hostname", "localhost", "sql.username", "root", "sql.password", "", "sql.database", "DataBase", "sql.port", 3306
				});
		
		if (!config.exists())
			config.setup(new Object[]{
					"inventory.max_users_global", 54, "information.use_bukkit_banSystem_lookup", true, "information.use_gamemode_lookup", true,
				});
		
		useSQL = sqlConfig.get().getBoolean("enabled");
		
		if (!useSQL) {
			getLogger().info("WARNING: SQL is disabled! Data is going in a shitty yml file");
			data = new UtilFile(dataFolder, "cache");
	    	data.save();
	    	useSQL=false;
		}else {
			useSQL=true;
			new SetupSQL();
			if (!SetupSQL.isValid())return;
		}
		
		devro = this.getClass().getAnnotation(Devro.class);
		
		if (this.devro == null)
			this.getLogger().warning("Using default values for Mani...");
		
		new StoreMaps();
		new Intializer();
		new RunnableManager();
		new GlobalGUI();
		new ActiveManager();
		SQLUtil sql = new SQLUtil();
		for (Player p : Bukkit.getServer().getOnlinePlayers()){
			if (useSQL)StoreMaps.getInstance().totalTime.put(p.getUniqueId(), sql.getTotalTime(p.getUniqueId()));
			else StoreMaps.getInstance().totalTime.put(p.getUniqueId(),data.get().getInt("cache."+p.getUniqueId()+".totalTime"));
			StoreMaps.getInstance().onlineSince.put(p.getUniqueId(), UtilTime.formatDateFromLong(System.currentTimeMillis()));
		}
		
		getLogger().info("/*-------------*\\");
		getLogger().info("Created by 'sellfy.com/DevroCoding'");
		getLogger().info("/*-------------*\\");
	}
	
	public boolean isSqlEnabled(){
		return useSQL;
	}
    
    public void onDisable(){
    	SQLUtil sql = new SQLUtil();
    	for (Player p : Bukkit.getServer().getOnlinePlayers()){
    		if (useSQL)sql.setTotalTime(p.getUniqueId(), StoreMaps.getInstance().totalTime.get(p.getUniqueId()));
    		else{
    			data.get().set("cache."+p.getUniqueId()+".totalTime", StoreMaps.getInstance().totalTime.get(p.getUniqueId()));
    			data.save();
    		}
    	}
    }
}
