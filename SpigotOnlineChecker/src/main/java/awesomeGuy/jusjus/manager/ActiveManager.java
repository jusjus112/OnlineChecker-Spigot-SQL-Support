package awesomeGuy.jusjus.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Arrays;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import awesomeGuy.jusjus.Core;
import awesomeGuy.jusjus.gui.ActiveGUI;
import awesomeGuy.jusjus.gui.GlobalGUI;
import awesomeGuy.jusjus.sql.SQLUtil;
import awesomeGuy.jusjus.sql.StoreMaps;
import awesomeGuy.jusjus.util.UtilItem;
import awesomeGuy.jusjus.util.UtilTime;

public class ActiveManager {
	
	private SQLUtil sql = new SQLUtil();
	
	public static ActiveManager instance;
	public static ActiveManager getManager(){return instance;}
	public ActiveManager(){instance=this;}
	
	public GlobalGUI GlobalGUI(){
		return new GlobalGUI();
	}
	
	public ActiveGUI ActiveGUI(){
		return new ActiveGUI();
	}
	
	public String getFirstJoined(OfflinePlayer p){
		String s=UtilTime.formatDateFromLong(p.getFirstPlayed());
		if (s==null)s="§cError when loading...";
		return s;
	}
	
	public Integer getTotalTime(OfflinePlayer p){
		if (p.isOnline()){
			return StoreMaps.getInstance().totalTime.get(p.getUniqueId());
		}else{
			if (Core.getInstance().isSqlEnabled()){
				if (sql.exists(p.getUniqueId()))return sql.getTotalTime(p.getUniqueId());
			}else{
				if (Core.getInstance().data.get().contains("cache."+p.getUniqueId().toString()))
					return Core.getInstance().data.get().getInt("cache."+p.getUniqueId()+".totalTime");
			}
		}
		return 0;
	}
	
	public ItemStack createItem(OfflinePlayer p) throws Exception{
		 return UtilItem.createSkull("§a"+p.getName(), Arrays.asList(
					new String[]{"§7Here you can see the global info","§7for this selected player.","","§7Name: §6"+p.getName(),"§7UUID: §6"+p.getUniqueId(),"",
								"§7Status: §e"+(p.isOnline() ? "§a§lOnline §7§oSince "+StoreMaps.getInstance().onlineSince.get(p.getUniqueId()) : "§c§lOffline"),
								"§7First joined: §e"+ActiveManager.getManager().getFirstJoined(p),
							    (p.isOnline() ? null : "§7Left on: §e"+UtilTime.formatDateFromLong(p.getLastPlayed())),
							    "§7Total online time: §e"+UtilTime.formatTime(ActiveManager.getManager().getTotalTime(p)),
							    "§7Country §o(From IP): §6"+(p.isOnline() ? getCountry(p.getPlayer().getAddress()) : "§cOffline"),
							    (Core.getInstance().config.get().getBoolean("information.use_gamemode_lookup")==Boolean.TRUE ? "§7Banned: §6"+Boolean.valueOf(p.isBanned()).toString() : null)
						}), p.getName());
	}
	
	public static String getCountry(InetSocketAddress ip) throws Exception {
		URL url = new URL("http://ip-api.com/json/" + ip.getHostName());
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder entirePage = new StringBuilder();
		String inputLine;
		while ((inputLine = stream.readLine()) != null) {
			entirePage.append(inputLine);
		}
		stream.close();
		if (!entirePage.toString().contains("\"country\":\"")) {
			return null;
		}
		return entirePage.toString().split("\"country\":\"")[1].split("\",")[0];
	}
}
