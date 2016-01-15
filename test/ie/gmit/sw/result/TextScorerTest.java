package ie.gmit.sw.result;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ie.gmit.sw.io.FileIO;

public class TextScorerTest {
	private String high12Gram = "TIONNTHETHER";
	private String low12Gram = "AAJZAAHXAAEY";
	private double high12Score = 1.1098303295024234;
	private double low12Score = -35.924584589875074;
	
	@Before
	public void before() {
		FileIO.parseQuadGramFile();
	}

	@Test
	public void testGetScore(){
		assertTrue(TextScorer.getScore(high12Gram) == high12Score);
		assertTrue(TextScorer.getScore(low12Gram) == low12Score);
	}

}
