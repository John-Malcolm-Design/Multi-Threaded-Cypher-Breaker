/**
 * 
 */
package ie.gmit.sw.io;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.gmit.sw.result.QuadGramMap;

public class FileIOTest {
	private String wpIntro = "Well, Prince, so Genoa and Lucca are now just family estates of the";

	@Test
	public void testCheckFileExists() {
		assertFalse(FileIO.checkFileExists("fake/file/URI.txt"));
		assertTrue(FileIO.checkFileExists("./textfiles/wp-intro.txt"));
	}
	
	@Test
	public void testParseTextFile(){
		assertTrue(FileIO.parseTextFile("./textfiles/wp-intro-small.txt").equals(wpIntro.replaceAll("[^a-zA-Z]+", "").toUpperCase()));
	}
	
	@Test
	public void testParseQuadGramFile(){
		FileIO.parseQuadGramFile("./textfiles/4grams.txt");
		assertTrue(QuadGramMap.getMap() != null);
	}
}
