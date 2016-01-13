package ie.gmit.sw.cryptography;

/**
 * <h1>Encryptor</h1>
 * Rail fence encryption algorithm and print matrix function used for debugging.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 01/01/2016
 *
 */
public class Encryptor {
	
	/**
	 * Encrypt a String called cypherText using an integer key
	 * 
	 * @param plainText Plain text as String.
	 * @param key To be used to encrypt (specifies the width of the matrix in the railfence).
	 * @return String contaning cypher text.
	 */
	public static String encrypt(String plainText, int key){
		//Declare a 2D array of key rows and text length columns
		char[][] matrix = new char[key][plainText.length()]; //The array is filled with chars with initial values of zero, i.e. '0'.
		
		//Fill the array
		int row = 0; //Used to keep track of rows
		boolean down = true; //Used to zigzag
		for (int i = 0; i < plainText.length(); i++){ //Loop over the plaintext
			matrix[row][i] = plainText.charAt(i); //Add the next character in the plaintext to the array
			
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
		
		printMatrix(matrix); //Output the matrix (debug)
		
		//Extract the cypher text
		StringBuffer sb = new StringBuffer(); //A string buffer allows a string to be built efficiently
		for (row = 0; row < matrix.length; row++){ //Loop over each row in the matrix
			for (int col = 0; col < matrix[row].length; col++){ //Loop over each column in the matrix
				if (matrix[row][col] > '0') sb.append(matrix[row][col]); //Extract the character at the row/col position if it's not 0.
			}
		}
		
		return sb.toString(); //Convert the StringBuffer into a String and return it
	}
	
	/**
	 * Output the 2D array in CSV format
	 * 
	 * @param matrix takes 2D matrix representing the Rail Fence. See skytale for example.
	 */
	private static void printMatrix(char[][] matrix){
		for (int row = 0; row < matrix.length; row++){ //Loop over each row in the matrix
			for (int col = 0; col < matrix[row].length; col++){ //Loop over each column in the matrix
				System.out.print(matrix[row][col]); //Output the value at row/column index
				if (col < matrix[row].length - 1) System.out.print(",");
			}
			System.out.println();
		}
	}
}
