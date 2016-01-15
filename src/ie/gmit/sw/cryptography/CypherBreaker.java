package ie.gmit.sw.cryptography;

import java.util.concurrent.*;

import ie.gmit.sw.io.FileIO;
import ie.gmit.sw.result.Resultable;

/**
 * <h1>CypherBreaker</h1>
 * Responsible for creating the results queue and for 
 * spawning off threads that handle decrypting, 
 * the method that deals with that is called decrypt. 
 * <p>
 * The init method is used to inititialize the Quad Gram Map in memory. 
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
			// TODO: handle exception
		}
	}
}


