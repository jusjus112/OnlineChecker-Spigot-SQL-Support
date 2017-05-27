package awesomeGuy.jusjus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilTime {
	
	public static String formatTimeDigital(int time) {
		int mins = time / 60;
		int secondsInteger = time % 60;
		String seconds = String.valueOf(secondsInteger);
		if (seconds.length() == 1) {
			seconds = "0" + seconds;
		}
		return mins + ":" + seconds;
	}
	
	/***
	 * @MINUTES
	 * @return String in minuts
	 */
	public static String formatTime(int time) {
		if (time==0)return "§cNot counted yet ;)";
		String message = "";
	    if (time >= 86400) {
	      int days = time / 86400;
	      time %= 86400;
	      message = message + days + " Day(s) ";
	    }
	    if (time >= 3600) {
	      int hours = time / 3600;
	      time %= 3600;
	      message = message + hours + " Hour(s) ";
	    }
	    if (time >= 60) {
		     int hours = time / 60;
		     time %= 60;
		     message = message + hours + " Minute(s) ";
		 }
	    if (time >= 0) {
	    	if (time == 1)
	    		message = message + time + " Second";
	    	else message = message + time + " Seconds";
	    }
	    return message;
	}
	
	public static String formatDateFromLong(Long mil){
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
		Date resultdate = new Date(mil);
		return sdf.format(resultdate);
	}

}
