package ie.gmit.sw;

import java.util.concurrent.PriorityBlockingQueue;

public class ResultConsumer implements Runnable {
	
	private PriorityBlockingQueue<Resultable> queue;

	public ResultConsumer(PriorityBlockingQueue<Resultable> q){
		this.queue = q;
	}

	@Override
	public void run() {
		Resultable r = null;
		// might be able to get rid of this due to internal queue sync
		while(!queue.isEmpty()){
			try {
				// Stops consumer taking all from queue thus ending the while loop
				Thread.sleep(100);
				r = queue.take();
				if (r.getScore() > -2000) {
					System.out.println(r.getScore() + "; " + r.getKey() + "; " + r.getPlainText());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Oops");
			}
		}
	}
}
