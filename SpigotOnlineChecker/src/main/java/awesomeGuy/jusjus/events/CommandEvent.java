package awesomeGuy.jusjus.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CommandEvent extends Event implements Cancellable{

	private Player p;
	private String cmd;
	private String[] args;
	private boolean cancelled;

	public CommandEvent(Player p, String cmd, String[] args) {
		this.p = p;
		this.cmd = cmd;
		this.args = args;
	}

	public Player getPlayer() {
		return p;
	}

	public String getCommand() {
		return cmd;
	}

	public String[] getArguments() {
		return args;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
