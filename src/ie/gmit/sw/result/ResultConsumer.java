package ie.gmit.sw.result;

import java.util.concurrent.PriorityBlockingQueue;

/* Name: ResultConsumer.java 
 * Author: John Malcolm Anderson
 * Date: 06/01/2016
 * Description: Consumer in producer/ consumer model used to handle
 * 	decrypting cypher text on multiple threads concurrently. 
 */

public class ResultConsumer implements Runnable {
	
	private PriorityBlockingQueue<Resultable> queue;

	public ResultConsumer(PriorityBlockingQueue<Resultable> q){
		this.queue = q;
	}

	// While the queue is not empty it will take a result object with a score greater than -2000 
    // and print it to the screen.
	@Override
	public void run() {
		Resultable r = null;
		while(!queue.isEmpty()){
			try {
				r = queue.take();
				if (r.getScore() > -2000) {
					System.out.println(r.getScore() + "; " + r.getKey() + "; " + r.getPlainText());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Oops");
			}
		}
	}
}
