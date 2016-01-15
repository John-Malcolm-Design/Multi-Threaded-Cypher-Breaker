package ie.gmit.sw.result;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines and initializes map containing quad gram, key:score pairs.
 * Defines gram size and contains getters and setters for the map getter for the gram size.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 01/01/2016
 * 
 * @see ie.gmit.sw.io.FileIO
 */
public class QuadGramMap {
	// Class variables
	private static Map<String, Double> map = new HashMap<String, Double>();
	private static final int GRAM_SIZE = 4;

	/**
	 * Get the Quad Gram Map 
	 * @return Quad Gram Map
	 */
	public static Map<String, Double> getMap() {
		return map;
	}
	
	/**
	 * Set the Quad Gram Map 
	 * @param map Quad Gram Map
	 */
	public static void setMap(Map<String, Double> map) {
		QuadGramMap.map = map;
	}
	
	/**
	 * Get the Gram Size
	 * @return Gram Size
	 */
	public static int getGramSize() {
		return GRAM_SIZE;
	}

}
