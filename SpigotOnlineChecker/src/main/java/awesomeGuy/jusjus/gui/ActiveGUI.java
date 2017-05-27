package awesomeGuy.jusjus.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import awesomeGuy.jusjus.Core;
import awesomeGuy.jusjus.manager.ActiveManager;
import awesomeGuy.jusjus.util.UtilItem;

public class ActiveGUI {
	
	private int[] places = new int[]{2,3,4,5,6,11,12,13,14,15};
	
	public Inventory get(boolean active){
		Inventory inv = Bukkit.createInventory(null, 18, "§7OC - §8Sorted on most "+(active ? "active" : "inactive")+":");
		addItems(active, inv);
		return inv;
	}
	
	private ActiveGUI addItems(boolean active, Inventory inv){
		List<String> lissie = new ArrayList<>(sortedList(active).keySet());
				
		for (int i=0;i<18;i++)
			inv.setItem(i, UtilItem.create(Material.STAINED_GLASS_PANE, (byte)4, " "));
		
		int ia = 0;
		for (int place : places){
			if (ia<lissie.size()){
				OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(lissie.get(ia)));
				ItemStack skull = null;
				try {
					skull = ActiveManager.getManager().createItem(op);
				} catch (Exception e) {
					e.printStackTrace();
				}
				inv.setItem(place, skull);
			}else inv.setItem(place, UtilItem.create(Material.STAINED_GLASS_PANE, (byte)7, " "));
			ia++;
		}
		return this;
	}
	
	
	private Map<String, Integer> sortedList(boolean active){
		Map<String, Integer> mappie = new HashMap<>();
		if (!Core.getInstance().useSQL){
			for (String uuid : Core.getInstance().data.get().getConfigurationSection("cache.").getKeys(false))
				mappie.put(uuid, Core.getInstance().data.get().getInt("cache."+uuid+".totalTime"));
			return sort(active, mappie);
		}else{
			// TODO sql stuff
		}
		return mappie;
	}
	
	private static Map<String, Integer> sort(final boolean active, Map<String, Integer>  map){
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            	if (active)return (o2.getValue()).compareTo(o1.getValue());
            	else return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        
        Map<String, Integer> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            newMap.put(entry.getKey(), entry.getValue());
        }
        return newMap;
    }

}
