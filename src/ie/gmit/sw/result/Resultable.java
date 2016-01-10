package ie.gmit.sw.result;

/* Name: Resultable.java <<Interface>>
 * Author: John Malcolm Anderson
 * Date: 06/01/2016
 * Description: Specifies methods common to all Result type objects
 *  that inherit from this class. These are getters/ setters for key, score & plain text.
 */

public interface Resultable{

	// Get plain text method
	public abstract String getPlainText();

	// Set plain text method
	public abstract void setPlainText(String plainText);

	// Get Key
	public abstract int getKey();

	// Set Key
	public abstract void setKey(int key);

	// Get Score
	public abstract double getScore();

	// Set Score
	public abstract void setScore(double score);

}