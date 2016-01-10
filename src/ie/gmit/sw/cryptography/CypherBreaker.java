package ie.gmit.sw.cryptography;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import ie.gmit.sw.io.QuadGramMap;
import ie.gmit.sw.result.ResultConsumer;
import ie.gmit.sw.result.Resultable;

public class CypherBreaker {
	private BlockingQueue<Resultable> queue;
	private int MAX_QUEUE_SIZE = 5;
	private String cypherText;
	private Map<String, Double> map;

	public CypherBreaker() {
		super();
		init();
	}

	public void init() {
		try {
			map = QuadGramMap.parseQuadGramFile("/Users/johnmalcolm/4grams.txt");
			System.out.println("4Grams read in successfully");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void decypher(String cypherTxt){
		queue = new PriorityBlockingQueue<Resultable>(MAX_QUEUE_SIZE); 
		this.cypherText = cypherTxt;
		
		for(int i = 2; i < cypherText.length()/2; i++){
			new Thread(new Decryptor(queue, cypherText, i, map)).start();
		}
		
		Thread consumer = new Thread(new ResultConsumer((PriorityBlockingQueue<Resultable>) queue));
		
		consumer.start();
	}
	
}
	
	
