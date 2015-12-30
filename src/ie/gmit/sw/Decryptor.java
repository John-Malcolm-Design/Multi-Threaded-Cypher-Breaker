package ie.gmit.sw;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Decryptor implements Runnable {
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	private Map m;

	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key, Map m) { // Producer
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
		this.m = m;
	}
	
	public void run(){
		Runner rf = new Runner();
		String plainText = rf.decrypt(cypherText, key);
		
		TextScorer score = new TextScorer(m);
		
		double testScore = score.getScore(plainText);
		
		Resultable r = new Result(plainText, this.key, testScore);

		try {
			queue.put(r);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
