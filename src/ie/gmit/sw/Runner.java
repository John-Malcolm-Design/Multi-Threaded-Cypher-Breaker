package ie.gmit.sw;

import ie.gmit.sw.io.CommandLineIO;

/**
 * Entry point for the application.
 * <p>
 * This class should always be the entry point to the application.
 * Delegates to either CLI, GUI, network or other I/O.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 30/12/2015
 * @see ie.gmit.sw.io.CommandLineIO
 */
public class Runner {
	/**
	 * Delegates to CommandLineIO.startCLI() to handle User input via the command line.
	 * 
	 * @param args Default main() function string argument
	 * @see ie.gmit.sw.io.CommandLineIO#startCLI()
	 */
	public static void main(String[] args){
		CommandLineIO.startCLI();
	}	
}
