package ie.gmit.sw.cryptography;

import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.result.Result;
import ie.gmit.sw.result.Resultable;
import ie.gmit.sw.result.TextScorer;

/**
 * <h1>Decryptor</h1>
 * Decryptor objects are used in brute force decyphering. 
 * This works by spawning off several( n/2 ) threads each one with its own unique 
 * key to use but each with the same cypher text, quadgram map and blocking queue for handling the results.
 * <p>
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 06/01/2016
 * 
 * @see ie.gmit.sw.cryptography.CypherBreaker
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html">Runnable</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html">BlockingQueue</a>
 *
 */
public class Decryptor implements Runnable {
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;

	/**
	 * Default constructor. 
	 * 
	 * @param queue Concurrent queue is accessed by multiple threads to store results.
	 * @param cypherText String to store cypher text. 
	 * @param key Used for decryption
	 */
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key) { // Producer
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
	}
	
	/**
	 * Decrypts cypher text, gets score and puts result object into the result queue. 
	 * 
	 * @see ie.gmit.sw.cryptography.Decryptor#decrypt(String, int)
	 * @see ie.gmit.sw.result.TextScorer#getScore(String)
	 * @see ie.gmit.sw.result.Result
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html#run()">Runnable.run()</a>
	 */
	public void run(){
		// Decrypt cypher text and with key and store results in string
		String plainText = decrypt(cypherText, key);
		
		// Calculate & store the score in a variable	
		double textScore = TextScorer.getScore(plainText);
		
		// Pass score key and text into Resultable object
		Resultable r = new Result(plainText, this.key, textScore);

		// Add result to the PriorityBlockingQueue
		try {
			queue.put(r);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Decrypt a String cypherText using an integer key.
	 * 
	 * @param cypherText The cyphertext that is to be decrypted.
	 * @param key The key used for decryption.
	 * @return Plain text. This is the decrypted text and not necessarily correct as it depends on the key.
	 * 
	 */
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
