package awesomeGuy.jusjus.sql;

import java.util.HashMap;
import java.util.UUID;

public class StoreMaps {
	
	private static StoreMaps instance;
	public StoreMaps() {instance = this;}
	public static StoreMaps getInstance() {return instance;}
	
	//TODO: Get instance
	public HashMap<UUID, String> firstJoined = new HashMap<UUID, String>();
	public HashMap<UUID, Integer> totalTime = new HashMap<UUID, Integer>();
	public HashMap<UUID, String> onlineSince = new HashMap<UUID, String>();
}
