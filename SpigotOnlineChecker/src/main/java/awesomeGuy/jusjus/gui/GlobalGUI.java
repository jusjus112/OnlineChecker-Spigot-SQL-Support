package awesomeGuy.jusjus.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import awesomeGuy.jusjus.manager.ActiveManager;
import awesomeGuy.jusjus.util.UtilInv;
import awesomeGuy.jusjus.util.UtilItem;

public class GlobalGUI {
	
	public Inventory get(){
		Inventory inv = Bukkit.createInventory(null, UtilInv.getInventoryFormatSize(Bukkit.getServer().getOfflinePlayers().length), "§7OC - §8Global Status all players:");
		addItems(null, inv);
		return inv;
	}
	
	public Inventory get(OfflinePlayer p){
		Inventory inv = Bukkit.createInventory(null, 9, "§7OC - §7§o"+p.getName());
		addItems(p, inv);
		return inv;
	}
	
	private void addItems(OfflinePlayer op ,Inventory inv){
		if (op!=null){
			for (int i=0;i<9;i++)
				inv.setItem(i, UtilItem.create(Material.STAINED_GLASS_PANE, (byte)4, " "));
			try {
				inv.setItem(4, ActiveManager.getManager().createItem(op));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			for (OfflinePlayer p : Bukkit.getServer().getOfflinePlayers()){
				if (p.isBanned())continue;
				ItemStack skull = null;
				try {
					skull = ActiveManager.getManager().createItem(p);
				} catch (Exception e) {
					e.printStackTrace();
				}
				inv.addItem(skull);
			}
		}
	}
}
