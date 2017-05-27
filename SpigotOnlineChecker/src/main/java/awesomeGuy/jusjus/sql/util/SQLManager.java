package awesomeGuy.jusjus.sql.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import awesomeGuy.jusjus.Core;

public class SQLManager {

	List<String> loadList = Collections.synchronizedList(new ArrayList<String>());

	public SQLManager() {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (loadList.size() <= 0) {
					return;
				}
				Iterator<String> iter = loadList.iterator();
				while (iter.hasNext()) {
					try {
						Player p = Bukkit.getPlayer(UUID.fromString(iter.next()));
						if (p == null || !p.isOnline()) {
							iter.remove(); 
							continue;
						}
					} catch (Exception e) {
						iter.remove();
						continue;
					}
				}

			}
		}.runTaskTimer(Core.getInstance(), 0, 10);
	}

	public void addPreloadPlayer(UUID uuid) {
		Player p = Bukkit.getPlayer(uuid);
		if (p != null && p.isOnline()) {
			loadList.add(uuid.toString());
		}
	}
}
