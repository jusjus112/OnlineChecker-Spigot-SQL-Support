package awesomeGuy.jusjus.util;

public class UtilInv {
	
	private static int[] globalSize = {9,18,27,36,45,54};
	
	public static int getInventoryFormatSize(int i){
		for (int gi : globalSize)
			if (i<=gi)return gi;
		return 54;
	}
}
