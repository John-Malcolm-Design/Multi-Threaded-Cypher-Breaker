package ie.gmit.sw.cryptography;

import java.util.concurrent.*;

import ie.gmit.sw.io.FileIO;
import ie.gmit.sw.result.Resultable;

/**
 * Brute force decyphering based on fingerprint analysis using quad grams.
 * <p>
 * This class works by spawning off Decryptor threads that handle decrypting, 
 * the method that deals with that is decrypt in the Decryptor class. 
 * The CypherBreaker uses an ExecutorService, Executors and a PriorityBlockingQueue to
 * circumvent previous producer/ consumer decypher style (see ResultConsumer class and ResultConsumerTest for more info).
 * The new implementation saves memory as I use a smaller queue and also makes the code more readable. Upon completion 
 * getQueue() method is used to access the top result based on the TextScorer clases.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 11/01/2016
 * 
 * @see ie.gmit.sw.cryptography.Decryptor
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/PriorityBlockingQueue.html">PriorityBlockingQueue</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Executors.html">Executors</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html">ExecutorService</a>
 *
 */
public class CypherBreaker {
	private BlockingQueue<Resultable> queue;
	private int MAX_QUEUE_SIZE = 5;
	private String cypherText;

	/**
	 * Default constructor that calls the init() function.
	 * @see ie.gmit.sw.cryptography.CypherBreaker#init()
	 */
	public CypherBreaker() {
		super();
		init();
	}

	/**
	 * Get method that exposes the queue for access to the top 5 results.
	 * @return
	 */
	public BlockingQueue<Resultable> getQueue() {
		return queue;
	}

	/**
	 * Initializes the Quad Gram Map (4 char long english n-gram ) which can be used for scoring decrypted text.
	 * @see ie.gmit.sw.io.FileIO#parseTextFile(String)
	 */
	public void init() {
		FileIO.parseQuadGramFile();
	}

	/**
	 * Initializes the PriorityBlockingQueue that is accessed concurrently to store result objects.
	 * Creates ExecutorService that handles thread management. ExecutorService also handlels shutting down threads.
	 * 
	 * @param cypherTxt String containing the cypher text.
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html#submit(java.lang.Runnable)">ExecutorService.submit(Runnable)</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html#shutdown()">ExecutorService.shutdown()</a>
	 */
	public void decypher(String cypherTxt){
		// Init queue and cypher text string.
		queue = new PriorityBlockingQueue<Resultable>(MAX_QUEUE_SIZE); 
		this.cypherText = cypherTxt;

		// Create executor service with a size of: (cypher text length / 2).
		ExecutorService executor = Executors.newFixedThreadPool(cypherText.length()/2);

		// Submit Decryptor runnable classes to the threads in the pool and then shutdown the executor.
		for(int i = 2; i < cypherText.length()/2; i++){
			executor.submit(new Decryptor(queue, cypherText, i));

		}
		// Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted. 
		// Invocation has no additional effect if already shut down.
		executor.shutdown();

		// Wait a max of one day. 
		// Used to ensure this thread of execution waits until all threads in executor are complete.
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
		}
	}
}


