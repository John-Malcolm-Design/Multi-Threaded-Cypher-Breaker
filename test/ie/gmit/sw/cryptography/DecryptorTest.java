package ie.gmit.sw.cryptography;

import static org.junit.Assert.*;

import org.junit.Test;

public class DecryptorTest {
	private String cypherTxt = "CSSBUNMTPRRORCINAOPTAAOYUTOEEWTAMEIEOEUMEHSNIOECSGDSPTECMRTORTTOSBLSESIKUNOCSRAEDJ";
	private String plainTxt = "COMPUTERSCIENCEISNOMOREABOUTCOMPUTERSTHANASTRONOMYISABOUTTELESCOPESEDSGERWDIJKSTRA";
	
	@Test
	public void testDecrypt(){
		assertTrue(Decryptor.decrypt(cypherTxt, 5).equals(plainTxt));
		assertFalse(Decryptor.decrypt(cypherTxt, 6).equals(plainTxt));
	}

}
