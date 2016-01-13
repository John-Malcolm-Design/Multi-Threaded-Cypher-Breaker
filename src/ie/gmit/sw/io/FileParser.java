package ie.gmit.sw.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// Used for reading in QuadGramMap 4grams.txt
public class FileParser { 
	
	public static String parsePlainText(String file) throws IOException{
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
