package ie.gmit.sw.cryptography;

import static org.junit.Assert.*;
import org.junit.Test;

import ie.gmit.sw.result.QuadGramMap;
import ie.gmit.sw.result.Resultable;

public class CypherBreakerTest {
	private CypherBreaker cb = new CypherBreaker();
	private String cypherTxt = "CSSBUNMTPRRORCINAOPTAAOYUTOEEWTAMEIEOEUMEHSNIOECSGDSPTECMRTORTTOSBLSESIKUNOCSRAEDJ";
	private String plainTxt = "COMPUTERSCIENCEISNOMOREABOUTCOMPUTERSTHANASTRONOMYISABOUTTELESCOPESEDSGERWDIJKSTRA";
	private int key = 5;
	private double score = -34.60493363301056;
	
	@Test
	public void testInit(){
		assertTrue(QuadGramMap.getMap() != null);
		assertTrue(QuadGramMap.getMap().get("TION") == 13168375);
	}
	
	@Test
	public void testDecypher(){
		cb.decypher(cypherTxt);
		Resultable result = cb.getQueue().peek();
		assertTrue(result.getPlainText().equals(plainTxt));
		assertTrue(result.getKey() == key);
		assertTrue(result.getScore() == score);
	}
}
