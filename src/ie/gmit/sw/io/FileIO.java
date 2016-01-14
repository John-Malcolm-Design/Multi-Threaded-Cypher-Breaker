package ie.gmit.sw.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import ie.gmit.sw.result.QuadGramMap;

/**
 * <h1>FileParser</h1>
 * Contains methods for reading in files and parsing to strings.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 01/01/2016
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html">StringBuffer</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html">BufferedReader</a>
 *
 */
public class FileIO { 

	/**
	 * Parse plain text file into a string using the StringBuffer and BufferedReader.
	 * 
	 * @param file The URI of the file for parsing.
	 * @return String containing text the from the text file.
	 * @throws IOException Regular IOException.
	 * 
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#replaceAll(java.lang.String,%20java.lang.String)">String.replaceAll()</a>
	 */
	public static String parseTextFile(String file){
		// Initialize and instantiate files.
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String line = null;

		// While the Buffered Reader is not null read and append to StringBuffer.
		try {
			while ((line = br.readLine()) != null) {
				/* Regex is used via the String class for data sanitizing. All characters that are 
				 * outside of the range a-z are removed. All characters are also converted to uppercase for consistency. */
				sb.append(line.replaceAll("[^a-zA-Z]+", "").toUpperCase());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * Parses quad gram file from into Map.
	 * 
	 * @param file  containing URI for n-gram file.
	 * @throws IOException Regular IOException.
	 * @see ie.gmit.sw.result.QuadGramMap
	 */
	public static void parseQuadGramFile(String file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String next= null;

		// While the BufferedReader is not null split on spaces and add to Map.
		try {
			while ((next = br.readLine()) != null) {
				String [] stuff = next.split(" ");
				QuadGramMap.getMap().put(stuff[0], Double.parseDouble(stuff[1]));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean checkFileExists(String fileURI){

		File f = new File(fileURI);

		// Checks too see if file url provided can be used in as a file input stream
		try {
			@SuppressWarnings({ "unused", "resource" })
			BufferedReader testBr = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
