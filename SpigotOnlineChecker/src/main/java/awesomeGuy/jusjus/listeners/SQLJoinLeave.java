package awesomeGuy.jusjus.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import awesomeGuy.jusjus.Core;
import awesomeGuy.jusjus.sql.SQLUtil;
import awesomeGuy.jusjus.sql.StoreMaps;

public class SQLJoinLeave implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onsqlJoin(PlayerJoinEvent e){
		if (Core.getInstance().isSqlEnabled()) {
			SQLUtil sql = new SQLUtil();
			sql.generateAccount(e.getPlayer());
			StoreMaps.getInstance().firstJoined.put(e.getPlayer().getUniqueId(), sql.getFirstJoined(e.getPlayer().getUniqueId()));
			StoreMaps.getInstance().totalTime.put(e.getPlayer().getUniqueId(), sql.getTotalTime(e.getPlayer().getUniqueId()));
		}else{
			if (Core.getInstance().data.get().contains("cache."+e.getPlayer().getUniqueId().toString())) {
				StoreMaps.getInstance().totalTime.put(e.getPlayer().getUniqueId(),Core.getInstance().data.get().getInt("cache."+e.getPlayer().getUniqueId()+".totalTime"));
				return;
			}else{
				StoreMaps.getInstance().totalTime.put(e.getPlayer().getUniqueId(),0);
			}
		}
	}
	
	@EventHandler
	public void onsqlQuit(PlayerQuitEvent e){
		if (Core.getInstance().isSqlEnabled()) {
			SQLUtil sql = new SQLUtil();
			if (StoreMaps.getInstance().totalTime.get(e.getPlayer().getUniqueId())!=null||StoreMaps.getInstance().totalTime.get(e.getPlayer().getUniqueId())!=0)
				sql.setTotalTime(e.getPlayer().getUniqueId(), StoreMaps.getInstance().totalTime.get(e.getPlayer().getUniqueId()));
		}else{
			Core.getInstance().data.get().set("cache."+e.getPlayer().getUniqueId()+".totalTime", StoreMaps.getInstance().totalTime.get(e.getPlayer().getUniqueId()));
			Core.getInstance().data.save();
		}
		StoreMaps.getInstance().onlineSince.remove(e.getPlayer().getUniqueId());
	}

}
