package awesomeGuy.jusjus.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import awesomeGuy.jusjus.events.CommandEvent;
import awesomeGuy.jusjus.util.UtilServer;
import awesomeGuy.jusjus.util.UtilString;

public class CommandListener implements Listener{
	
	@EventHandler
	public void Command(PlayerCommandPreprocessEvent e) {
		List<String> args = UtilString.convert(e.getMessage().split(" "));
		String cmd = args.get(0).replaceAll("/", "");
		args.remove(0);
		Player p = e.getPlayer();
		CommandEvent event = new CommandEvent(p, cmd, UtilString.convert(args));
		UtilServer.callEvent(event);
		if (event.isCancelled())e.setCancelled(true);
	}

}
