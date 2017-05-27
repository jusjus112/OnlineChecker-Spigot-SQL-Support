package awesomeGuy.jusjus.util;

import awesomeGuy.jusjus.Core;

public class UtilPrefix {

	public static String HEART = "❤";
	public static String DOUBLE_ARROW_FORWARD = "»";
	public static String DOUBLE_ARROW_BACKWARD = "«";
	public static String SERVER_NAME = Core.getInstance().devro.pluginName();
	public static String LINE_TITLE = "§7-------------" + DOUBLE_ARROW_FORWARD + "§r " + Core.getInstance().devro.serverColor()+ 
										"§l" + SERVER_NAME + " §7" + DOUBLE_ARROW_BACKWARD + "-------------";
	public static String LINE = "§7------------------------------------";

}
