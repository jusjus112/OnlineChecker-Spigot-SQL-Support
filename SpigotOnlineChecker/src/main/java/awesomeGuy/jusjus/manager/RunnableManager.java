package awesomeGuy.jusjus.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import awesomeGuy.jusjus.Core;
import awesomeGuy.jusjus.sql.StoreMaps;

public class RunnableManager extends BukkitRunnable{
	
	private int delay = 5; /**@SECONDS*/
	
	@SuppressWarnings("deprecation")
	public RunnableManager(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), this, 20*delay, 20*delay);
	}

	@Override
	public void run() {
		for (Player p : Bukkit.getServer().getOnlinePlayers())
			StoreMaps.getInstance().totalTime.put(p.getUniqueId(), StoreMaps.getInstance().totalTime.get(p.getUniqueId())+delay);
	}
}