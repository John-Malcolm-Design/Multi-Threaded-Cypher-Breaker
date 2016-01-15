package ie.gmit.sw.result;

/**
 * Interface specifies methods common to all Result type objects.
 * Getters/ setters for key, score and plain text.
 * Allows abstraction to different types of results.
 * 
 * @author John Malcolm Anderson
 * @since 01/01/2016
 * @version 1.0
 * 
 * @see ie.gmit.sw.result.Result
 * @see ie.gmit.sw.result.ResultConsumer
 *
 */
public interface Resultable{

	/**
	 * Get plain text.
	 * @return Plain text/decyphered text.
	 */
	public abstract String getPlainText();

	/**
	 * Set the plain text/decyphered text.
	 * @param plainText Plain Text.
	 */
	public abstract void setPlainText(String plainText);

	/**
	 * Get the key used for decryption.
	 * @return Returns key.
	 */
	public abstract int getKey();

	/**
	 * Set the key.
	 * @param key Key used for decryption.
	 */
	public abstract void setKey(int key);

	/**
	 * Get plain text.
	 * @return Plain text/decyphered text.
	 */
	public abstract double getScore();

	/**
	 * Set the key.
	 * @param score Score given to decyphered text based on the TextScorer.
	 * @see ie.gmit.sw.result.TextScorer
	 */
	public abstract void setScore(double score);
	
	/**
	 * Comparison method needed for sorting.
	 * 
	 * @param o Restulable object to compare to.
	 * @return int containing 1 if greater than, 0 if equal and -1 if less than.
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html#compareTo(T)">Comparable.compareTo()</a>
	 */
	public abstract int compareTo(Resultable o);

}