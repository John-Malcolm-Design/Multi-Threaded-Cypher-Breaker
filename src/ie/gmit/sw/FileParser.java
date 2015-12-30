package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// Used for reading in QuadGramMap 4grams.txt
public class FileParser {
	
	// Return ordinary hash map to consturctor of conncurent hash map
	public Map<String, Double> parseQuadGramFile(String file) throws IOException {
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
	
	public String parsePlainText(String file) throws IOException{
		StringBuffer sb = new StringBuffer();
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line.replaceAll("[^a-zA-Z]+", "").toUpperCase());
		}
		br.close();
		
		return sb.toString();
		
	}
}
