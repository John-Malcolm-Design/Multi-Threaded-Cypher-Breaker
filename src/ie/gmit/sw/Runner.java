package ie.gmit.sw;

import org.apache.log4j.Logger;

import ie.gmit.sw.io.CommandLineIO;

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
	public static final Logger log = Logger.getLogger(Runner.class);

	/**
	 * Entry point of application, deals with creating and initializing objects
	 * to handle user input, encryption, decryption and decyphering.
	 * 
	 * @param args - Default string argument
	 * @throws Exception - Default Exception.
	 */
	public static void main(String[] args) throws Exception{
		CommandLineIO.startCLI();
	}	
}
