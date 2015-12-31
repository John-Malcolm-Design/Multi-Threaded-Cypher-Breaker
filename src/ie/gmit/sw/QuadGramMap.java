package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class QuadGramMap {
	public static final int GRAM_SIZE = 4;
	
	// Return ordinary hash map to consturctor of conncurent hash map
	public static Map<String, Double> parseQuadGramFile(String file) throws IOException {
		Map<String, Double> map = new HashMap<String, Double>();
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		String next= null;
		while ((next = br.readLine()) != null) {
			String [] stuff = next.split(" ");
			map.put(stuff[0], Double.parseDouble(stuff[1]));
		}
		br.close();
		
		return map;
	}
	

}
