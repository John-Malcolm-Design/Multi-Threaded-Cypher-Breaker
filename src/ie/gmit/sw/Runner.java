package ie.gmit.sw;

import ie.gmit.sw.cryptography.CypherBreaker;
import ie.gmit.sw.cryptography.Encryptor;
import ie.gmit.sw.io.FileIO;

/**
 * <h1>Runner</h1>
 * Main entry point for application.
 * Takes plain text and delegates to other classes to complete full,
 * parsing, encrypting, decrypting, scoring, decyphering and other functionality.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 30/12/2015
 * 
 * @see ie.gmit.sw.cryptography
 * @see ie.gmit.sw.result
 * @see ie.gmit.sw.io
 *
 */
public class Runner {
	
	/**
	 * Entry point of application, deals with creating and initializing objects
	 * to handle user input, encryption, decryption and decyphering.
	 * 
	 * @param args - Default string argument
	 * @throws Exception - Default Exception.
	 */
	public static void main(String[] args) throws Exception{
		// Parses the introduction to "War & Peace" plain text and passes text to String called plainText.
		String plainText = FileIO.parsePlainText("./wp-intro.txt");
				
		// Encrypts using plainText string and specified key lenght as six. 
		String cypherText = Encryptor.encrypt(plainText, 6);
		
		// Creates new CypherBreaker object.
		CypherBreaker cb = new CypherBreaker();
		
		// Decyphers cypherText
		cb.decypher(cypherText);
	}	
}
