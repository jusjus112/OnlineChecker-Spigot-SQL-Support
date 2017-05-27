package awesomeGuy.jusjus.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryClickEvent implements Listener{
	
	@EventHandler
	public void inInvClick(org.bukkit.event.inventory.InventoryClickEvent e){
		if (e.getInventory().getName().contains("OC")){
			e.setCancelled(true);
			return;
		}
	}

}
