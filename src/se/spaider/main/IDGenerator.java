package se.spaider.main;

import java.util.Random;

public class IDGenerator {
	
	static Random random = new Random();
	static String[] letters = new String[]{
			"a",
			"b",
			"c",
			"d",
			"e",
			"f",
			"g",
			"h",
			"i",
			"j",
			"k",
			"l",
			"m",
			"n",
			"o",
			"p",
			"q",
			"r",
			"s",
			"t",
			"u",
			"v",
			"w",
			"x",
			"y",
			"z"
	};
	
	public static String getRandomID(){
		String id = "";
		for(int i = 0; i < 5; i += 1){
			id += letters[random.nextInt(letters.length)]+random.nextInt(9);
		}
		return id;
	}

}
