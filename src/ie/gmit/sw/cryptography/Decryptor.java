package ie.gmit.sw.cryptography;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.result.Result;
import ie.gmit.sw.result.Resultable;
import ie.gmit.sw.result.TextScorer;

/**
 * <h1>Decryptor</h1>
 * Decryptor objects are used in brute force decyphering. (run method is a producer in producer/ consumer model) 
 * This works by spawning off several( n/2 ) threads each one with its own unique 
 * key to use but each with the same cypher text, quadgram map and blocking queue for handling the results.
 * <p>
 * These resultable objects are handled by the ResultsConsumer class (consumer class). 
 * This is the producer class which produces resultable objects and adds them to the BlockingQueue. 
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 06/01/2016
 * 
 * @see ie.gmit.sw.cryptography.CypherBreaker
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html">Runnable</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html">BlockingQueue</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Map.html">Map</a>
 *
 */
public class Decryptor implements Runnable {
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;

	/**
	 * Default constructor. 
	 * 
	 * @param queue BlockingQueue<Resultable> queue is accessed by multiple threads to store results.
	 * @param cypherText String to store cypher text. 
	 * @param key Used for decryption
	 * @param m Quad Gram Map 
	 */
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key) { // Producer
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
	}
	
	public void run(){
		String plainText = decrypt(cypherText, key);
		
		TextScorer score = new TextScorer();
		
		double testScore = score.getScore(plainText);
		
		Resultable r = new Result(plainText, this.key, testScore);

		try {
			queue.put(r);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//***** Decrypt a String cypherText using an integer key ***** 
	public String decrypt(String cypherText, int key){
		//Declare a 2D array of key rows and text length columns
		char[][] matrix = new char[key][cypherText.length()]; //The array is filled with chars with initial values of zero, i.e. '0'.
		
		//Fill the array
		int targetRow = 0;
		int index = 0;
		do{
			int row = 0; //Used to keep track of rows		
			boolean down = true; //Used to zigzag
			for (int i = 0; i < cypherText.length(); i++){ //Loop over the plaintext
				if (row == targetRow){
					matrix[row][i] = cypherText.charAt(index); //Add the next character in the plaintext to the array
					index++;
				}
				
				if (down){ //If we are moving down the array
					row++;
					if (row == matrix.length){ //Reached the bottom
						row = matrix.length - 2; //Move to the row above
						down = false; //Switch to moving up
					} 
				}else{ //We are moving up the array
					row--;
					
					if (row == -1){ //Reached the top
						row = 1; //Move to the first row
						down = true; //Switch to moving down
					}
				}
			}

			targetRow++;
		}while (targetRow < matrix.length);
		
		// printMatrix(matrix); //Output the matrix (debug)
		
		//Extract the cypher text
		StringBuffer sb = new StringBuffer(); //A string buffer allows a string to be built efficiently
		int row = 0;
		boolean down = true; //Used to zigzag
		for (int col = 0; col < matrix[row].length; col++){ //Loop over each column in the matrix
			sb.append(matrix[row][col]); //Extract the character at the row/col position if it's not 0.
			
			if (down){ //If we are moving down the array
				row++;
				if (row == matrix.length){ //Reached the bottom
					row = matrix.length - 2; //Move to the row above
					down = false; //Switch to moving up
				} 
			}else{ //We are moving up the array
				row--;
				
				if (row == -1){ //Reached the top
					row = 1; //Move to the first row
					down = true; //Switch to moving down
				}
			}

		}
		
		return sb.toString(); //Convert the StringBuffer into a String and return it
	}
}
