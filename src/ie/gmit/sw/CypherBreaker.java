package ie.gmit.sw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;


public class CypherBreaker {
	private BlockingQueue<Resultable> queue;
	private int MAX_QUEUE_SIZE = 5;
	private String cypherText;
	
	public CypherBreaker(String cypherText){
		queue = new PriorityBlockingQueue<Resultable>(MAX_QUEUE_SIZE); // Priority Array Block Queue maybe??
		this.cypherText = cypherText;
		
		init();
	}

	private void init() {
		// start a load of producers 
		// put these in seperate classes loosly coupled
		
		FileParser fp = new FileParser();
		
		Map<String, Double> m = null;
		
		try {
			m = fp.parseQuadGramFile("/Users/johnmalcolm/4grams.txt");
			System.out.println("4Grams read in successfully");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i = 2; i < cypherText.length()/2; i++){
			new Thread(new Decryptor(queue, cypherText, i, m)).start();
		}
		
		Thread t1 = new Thread(new Runnable(){ // Consumer

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
			
		});
		
		t1.start();
	}
	
}
	
	
