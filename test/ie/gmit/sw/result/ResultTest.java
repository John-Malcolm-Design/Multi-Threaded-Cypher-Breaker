package ie.gmit.sw.result;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultTest {
	private String cypherTxt = "CSSBUNMTPRRORCINAOPTAAOYUTOEEWTAMEIEOEUMEHSNIOECSGDSPTECMRTORTTOSBLSESIKUNOCSRAEDJ";
	private String plainTxt = "COMPUTERSCIENCEISNOMOREABOUTCOMPUTERSTHANASTRONOMYISABOUTTELESCOPESEDSGERWDIJKSTRA";
	private int key = 5;
	private double score = -34.60493363301056;
	private String newPlainText = "Sample text for set method";
	private int newKey = 10;
	private double newScore = 2.2212;
	Result result = new Result(plainTxt, key, score);
	
	@Test
	public void testGetters() {
		assertTrue(result.getKey() == key);
		assertTrue(result.getPlainText() == plainTxt);
		assertTrue(result.getScore() == score);
	}
	
	@Test
	public void testSetters() {
		result.setKey(newKey);
		result.setPlainText(newPlainText);
		result.setScore(newScore);
		
		assertTrue(result.getKey() == newKey);
		assertTrue(result.getPlainText() == newPlainText);
		assertTrue(result.getScore() == newScore);
	}
	
	@Test 
	public void testCompareTo(){
		int compareResult = result.compareTo(new Result(cypherTxt, key, score));
		assertFalse(compareResult > 1);
	}

}
