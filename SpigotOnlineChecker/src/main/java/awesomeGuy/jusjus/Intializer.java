package awesomeGuy.jusjus;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import awesomeGuy.jusjus.commands.CommandOCGlobal;
import awesomeGuy.jusjus.listeners.CommandListener;
import awesomeGuy.jusjus.listeners.InventoryClickEvent;
import awesomeGuy.jusjus.listeners.JoinAndLeave;
import awesomeGuy.jusjus.listeners.SQLJoinLeave;

public class Intializer {

	public Intializer() {
		Listener[] listeners = new Listener[] {
				new CommandListener(),
				new CommandOCGlobal(),
				new JoinAndLeave(),
				new SQLJoinLeave(),
				new InventoryClickEvent(),
		};
		
		for (Listener listener : listeners)
			Bukkit.getServer().getPluginManager().registerEvents(listener, Core.getInstance());
	}

}
