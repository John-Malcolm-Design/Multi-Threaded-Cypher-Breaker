package ie.gmit.sw.cryptography;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncryptorTest {
	private String cypherTxt = "CSSBUNMTPRRORCINAOPTAAOYUTOEEWTAMEIEOEUMEHSNIOECSGDSPTECMRTORTTOSBLSESIKUNOCSRAEDJ";
	private String plainTxt = "COMPUTERSCIENCEISNOMOREABOUTCOMPUTERSTHANASTRONOMYISABOUTTELESCOPESEDSGERWDIJKSTRA";
	private int key = 5;

	@Test
	public void testEncrypt() {
		assertTrue(Encryptor.encrypt(plainTxt, key).equals(cypherTxt));
		assertFalse(Encryptor.encrypt(plainTxt, key).equals(plainTxt));
	}

}
