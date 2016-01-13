package ie.gmit.sw.cryptography;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>QuadGramMap</h1>
 * Defines and initializes map containing quad gram key: score pairs.
 * Defines gram size and contains getters and setters for the map getter for the gram size.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 01/01/2016 *
 */
public class QuadGramMap {
	private static Map<String, Double> map = new HashMap<String, Double>();
	private static final int GRAM_SIZE = 4;

	// Getters & Setters
	public static Map<String, Double> getMap() {
		return map;
	}
	

	public static void setMap(Map<String, Double> map) {
		QuadGramMap.map = map;
	}
	

	public static int getGramSize() {
		return GRAM_SIZE;
	}

}
