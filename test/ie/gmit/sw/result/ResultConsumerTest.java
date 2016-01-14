package ie.gmit.sw.result;

import static org.junit.Assert.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Before;
import org.junit.Test;

import ie.gmit.sw.cryptography.Decryptor;
import ie.gmit.sw.io.FileIO;

public class ResultConsumerTest {
	private BlockingQueue<Resultable> queue;
	@SuppressWarnings("deprecation")
	private ResultConsumer rc;
	private String cypherText = "TTFOHATGRNREEANOETYRCIMHHAKT";
	private String plainText = "THEYAREATTACKINGFROMTHENORTH";
	
	@Before
	public void before(){
		queue = new ArrayBlockingQueue<Resultable>(7);
		rc = new ResultConsumer(queue);
		
		FileIO.parseQuadGramFile("./4grams.txt");
         
		// These threads act as the producer, producing result objects and putting them on the queue.
        for(int i = 2; i < cypherText.length()/2; i++){
                new Thread(new Decryptor(queue, cypherText, i)).start();
        }
	}

	@Test
	public void testRun() {
		Thread t1 = new Thread(rc);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resultable result = rc.getTopResult();
		assertTrue(result.getPlainText().equals(plainText));
	}

}
