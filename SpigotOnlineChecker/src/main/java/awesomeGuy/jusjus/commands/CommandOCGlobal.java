package awesomeGuy.jusjus.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import awesomeGuy.jusjus.Core;
import awesomeGuy.jusjus.events.CommandEvent;
import awesomeGuy.jusjus.manager.ActiveManager;
import awesomeGuy.jusjus.util.UtilPrefix;
import awesomeGuy.jusjus.util.UtilString;

public class CommandOCGlobal implements Listener {
	
	private String[] commands = {"/oc","/oc global","/oc active","/oc inactive","/oc <player>","/oc help"};
	private String[] desc = {"Information about this plugin", "Opens a global users inventory", "All users sorted on most active",
			"All users sorted on most inactive", "Shows a single player", "Shows this screen ;)"};
	
	private String perms = Core.getInstance().devro.permissionsStartsWith();
	
	@EventHandler
	public void onVote(CommandEvent e){
		if (!(e.getCommand().equalsIgnoreCase("oc")||e.getCommand().equalsIgnoreCase("onlinechecker")))return;
		String[] args = e.getArguments();
		e.setCancelled(true);
		if (args.length==0){
			e.getPlayer().sendMessage(UtilPrefix.LINE_TITLE);
			e.getPlayer().sendMessage("§eA plugin made with §c"+UtilPrefix.HEART +" §efor spigot.");
			e.getPlayer().sendMessage("§eAuthor: §3"+UtilString.removeBrackets(Core.getInstance().devro.author()));
			e.getPlayer().sendMessage("§ePlugin made for: §6§lYou");
			e.getPlayer().sendMessage("§ePlugin Version: §6"+Core.getInstance().getDescription().getVersion());
			e.getPlayer().sendMessage("§eType §6/oc help §efor a help page");
			e.getPlayer().sendMessage(UtilPrefix.LINE);
		}else
		if (args.length>0&&args.length<2){
			if (args[0].equalsIgnoreCase("global")){
				if (e.getPlayer().hasPermission(perms+"global")) {
					e.getPlayer().sendMessage(" ");
					e.getPlayer().sendMessage("§eFetching all ofline player according to the config...");
					e.getPlayer().sendMessage("§ePrepare some lagg...");
					Inventory inv = ActiveManager.getManager().GlobalGUI().get();
					e.getPlayer().openInventory(inv);
					return;
				}
			}else
			if (args[0].equalsIgnoreCase("active")){
				if (e.getPlayer().hasPermission(perms+"active")) {
					e.getPlayer().sendMessage("§eSearching your database or files.....");
					Inventory inv = ActiveManager.getManager().ActiveGUI().get(true);
					e.getPlayer().openInventory(inv);
					e.getPlayer().sendMessage("§eDone..");
					return;
				}
			}else
			if (args[0].equalsIgnoreCase("inactive")){
				if (e.getPlayer().hasPermission(perms+"inactive")) {
					e.getPlayer().sendMessage("§eSearching your database or files.....");
					Inventory inv = ActiveManager.getManager().ActiveGUI().get(false);
					e.getPlayer().openInventory(inv);
					e.getPlayer().sendMessage("§eDone..");
					return;
				}
			}else
			if (args[0].equalsIgnoreCase("help")){
				if (e.getPlayer().hasPermission(perms+"help")) {
					e.getPlayer().sendMessage(UtilPrefix.LINE_TITLE);
					for (int i=0;i<commands.length;i++)
						e.getPlayer().sendMessage("§e#"+(i+1)+" - §6"+commands[i] + " §e| "+desc[i]);
					e.getPlayer().sendMessage(UtilPrefix.LINE);
					return;
				}
			}else{
				if (e.getPlayer().hasPermission(perms+"user")) {
					@SuppressWarnings("deprecation")
					OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
					if (target == null||!target.hasPlayedBefore()){
						e.getPlayer().sendMessage("§cThe name '"+args[0]+"' never joined your server..");
						return;
					}
					Inventory inv = ActiveManager.getManager().GlobalGUI().get(target);
					e.getPlayer().openInventory(inv);
					return;
				}else e.getPlayer().sendMessage("§cNo such command");
			}
		}
	}
}
