package ie.gmit.sw.io;

import org.apache.log4j.Logger;

/**
 * Initializes logger object.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 01/01/2016
 * @see <a href="https://logging.apache.org/log4j/1.2/manual.html">Log4J</a>
 */
public class Log4J {
	// Logger object
	public static final Logger log = Logger.getLogger(Log4J.class);

	public static void main(String[] args) {
		// Print a test message
		log.info("Test Message");
	}
}
