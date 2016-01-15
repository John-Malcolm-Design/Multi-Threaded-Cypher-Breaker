package ie.gmit.sw.io;

import org.apache.log4j.Logger;

public class Log4J {
	public static final Logger log = Logger.getLogger(Log4J.class);

	public static void main(String[] args) {
		log.info("Test Message");
	}
}
