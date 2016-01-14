package ie.gmit.sw.result;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ie.gmit.sw.io.FileIO;

public class TestQuadGramMap {
	
	@Before
	public void before(){
		FileIO.parseQuadGramFile("./4grams.txt");
	}

	@Test
	public void testGetters() {
		assertTrue(QuadGramMap.getGramSize() == 4);
		assertTrue(QuadGramMap.getMap().get("DTHE") == 6470280);
	}
	
	@Test
	public void testSetMap() {
		QuadGramMap.setMap(new HashMap<String, Double>());
		assertTrue(QuadGramMap.getMap().isEmpty());
	}
}
