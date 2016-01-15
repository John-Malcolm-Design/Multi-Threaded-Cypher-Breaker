package ie.gmit.sw.result;

import java.util.concurrent.BlockingQueue;

/**
 * Consumer in producer/ consumer model used to handle decrypting cypher text on multiple threads concurrently.
 * <p>
 * <b>This class has been deprecated.</b> Producer/consumer model was slower,
 * obfuscates functionality, and makes code more complex than need be. Program is now relying on the ExecutorService,
 * Executors and PriorityBlockingQueue to manage threads. This includes job sumbission, starting, shutting down and sorting.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 01/01/2016
 * @deprecated The producer/ consumer model has been replaced with a PriorityArrayBlockingQueue and ExecutorService system.
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/PriorityBlockingQueue.html">PriorityBlockingQueue</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Executors.html">Executors</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html">ExecutorService</a>
 */
public class ResultConsumer implements Runnable {
	// Class variables
	private BlockingQueue<Resultable> queue;
	private double topScore= -99999999.999;
	private Resultable topResult = null;

	@Deprecated
	public ResultConsumer(BlockingQueue<Resultable> q){
		this.queue = q;
	}

	/**
	 * Get the top result
	 * @return topResult The top result
	 */
	public Resultable getTopResult() {
		return topResult;
	}

	/** Set the top result
	public void setTopResult(Resultable topResult) {
		this.topResult = topResult;
	}
	
	/**
	 * While the queue is not empty it will take a result object with a score greater than -2000 
	 * and print it to the screen.
	 */
	@Deprecated
	@Override
	public void run() {
		Resultable r = null;
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Run while the Queue is not empty
		while(!queue.isEmpty()){
			try {
				r = queue.take();
				if (r.getScore() > topScore) {
					topScore = r.getScore();
					topResult = r;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				// Alert user of error.
				System.out.println("Intperrupt - sorry try again");
			}
		}
	}
}
