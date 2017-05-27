package awesomeGuy.jusjus.util.mani;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.bukkit.ChatColor;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Devro {
	
	String pluginName()
		default "A Plugin";
	String author()
		default "JusJusOneOneTwo";
	ChatColor serverColor()
		default ChatColor.GOLD;
	String website()
		default "https://sellfy.com/DevroCoding";
	String permissionsStartsWith() 
		default "devro.";
	
}
