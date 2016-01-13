package ie.gmit.sw.result;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * <h1>ResultConsumer</h1>
 * Consumer in producer/ consumer model used to handle decrypting cypher text on multiple threads concurrently.
 * <p>
 * <b>This class has been deprecated.</b> Producer/consumer model was slower,
 * obfuscates functionality, and makes code more complex than need be. Program is now relying on the ExecutorService,
 * Executors and PriorityBlockingQueue to manage threads. This includes job sumbission, starting, shutting down and sorting.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 01/01/2016
 * @deprecated The producer/ consumer model replaced with a PriorityArrayBlockingQueue and ExecutorService system.
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/PriorityBlockingQueue.html">PriorityBlockingQueue</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Executors.html">Executors</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html">ExecutorService</a>
 */
public class ResultConsumer implements Runnable {
	
	private PriorityBlockingQueue<Resultable> queue;

	@Deprecated
	public ResultConsumer(PriorityBlockingQueue<Resultable> q){
		this.queue = q;
	}

	/**
	 * While the queue is not empty it will take a result object with a score greater than -2000 
	 * and print it to the screen.
	 */
	@Deprecated
	@Override
	public void run() {
		Resultable r = null;
		
		// Run while the Queue is not empty
		while(!queue.isEmpty()){
			try {
				r = queue.take();
				if (r.getScore() > -2000) {
					// Print resutl to screen if score is over 2000
					// Arbitrary score is not scalable. Should be avoided.
					System.out.println(r.getScore() + "; " + r.getKey() + "; " + r.getPlainText());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				// Alert user of error.
				System.out.println("IO Error - sorry try again");
			}
		}
	}
}
