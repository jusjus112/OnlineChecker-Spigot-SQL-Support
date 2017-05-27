package awesomeGuy.jusjus.util;

import java.util.ArrayList;
import java.util.List;

public class UtilString {

	public static String[] convert(List<String> l) {
		return l.toArray(new String[l.size()]);
	}

	public static List<String> convert(String[] array) {
		List<String> l = new ArrayList<>();
		for (String s : array)
			l.add(s);
		return l;
	}
	
	public static String removeBrackets(String text) {
		return text.replaceAll("[\\[\\](){}]", "");
	}

}
