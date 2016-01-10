package ie.gmit.sw.cryptography;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

import ie.gmit.sw.result.Result;
import ie.gmit.sw.result.Resultable;
import ie.gmit.sw.result.TextScorer;

/* Name: Decryptor.java
 * Author: John Malcolm Anderson
 * Date: 06/01/2016
 * Description: Decryptor objects are used in brute force decyphering. (run method is a producer)
 *  This works by spawning off several( n/2 ) threads each one with its own unique 
 *  key to use but each with the same cypher text, quadgram map and blocking queue for handling the results.
 *  These resultable objects are handled by the ResultsConsumer class (consumer class). 
 *  This is the producer class which produces resultable objects and adds them to the BlockingQueue. 
 */

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
		String plainText = decrypt(cypherText, key);
		
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
