package awesomeGuy.jusjus.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public class UtilServer{
	
	public static void callEvent(Event e) {
		Bukkit.getServer().getPluginManager().callEvent(e);
	}
}