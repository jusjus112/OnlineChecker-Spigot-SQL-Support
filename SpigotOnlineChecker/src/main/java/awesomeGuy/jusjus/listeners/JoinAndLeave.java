package awesomeGuy.jusjus.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import awesomeGuy.jusjus.sql.StoreMaps;
import awesomeGuy.jusjus.util.UtilTime;

public class JoinAndLeave implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		StoreMaps.getInstance().onlineSince.put(e.getPlayer().getUniqueId(), UtilTime.formatDateFromLong(System.currentTimeMillis()));
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
	}

}
