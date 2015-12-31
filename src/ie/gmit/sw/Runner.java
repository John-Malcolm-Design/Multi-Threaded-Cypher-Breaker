package ie.gmit.sw;

/* Name: Runner.java 
 * Author: John Malcolm Anderson
 * Date: 30/12/2015
 * Description: Main entry point for application. 
 * 	Takes plain text and delegates to other classes to complete full,
 *  parsing, encrypting, decrypting, scoring, decyphering and other functionality.
 */

public class Runner {
	
	public static void main(String[] args) throws Exception{
		// Parses plain text and passes text to String called plainText.
		String plainText = FileParser.parsePlainText("/Users/johnmalcolm/Google Drive/Java/javaspace/OOPAssignment/wp-intro.txt");
		
		System.out.println("Finished parsing file!"); // DEBUG
		System.out.println(plainText); // DEBUG
		
		// Encrypts using plainText string and specified lenth key. 
		String cypherText = Encryptor.encrypt(plainText, 6);
		
		// Creates new CypherBreaker object.
		CypherBreaker cb = new CypherBreaker();
		
		// Initialises cypher breaker.
		cb.init();
		
		// Decyphers cypherText
		cb.decypher(cypherText);
	}	
}
