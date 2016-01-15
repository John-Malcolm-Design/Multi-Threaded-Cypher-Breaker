package ie.gmit.sw.result;

/**
 * Encapsulates decyphered text, key used for decryption and the score given by the TextScorer.
 * <p>
 * Implements Comparable to allow objects to be sorted.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 30/12/2015
 * 
 * @see ie.gmit.sw.result.ResultConsumer
 * @see ie.gmit.sw.result.Resultable
 * @see ie.gmit.sw.result.TextScorer
 *
 */
public class Result implements Resultable, Comparable<Resultable> {
	private String plainText;
	private int key;
	private double score;
	
	
	/**
	 * Constructor takes String, int, and double. 
	 * 
	 * @param plainText text from decypher algorithm. May or may not be accurate plain text.
	 * @param key key used for decryption.
	 * @param score score given by the TextScorer
	 */
	public Result(String plainText, int key, double score) {
		super();
		this.plainText = plainText;
		this.key = key;
		this.score = score;
	}

	/**
	 * Returns the plainText field.
	 * @return String plainText
	 */
	public String getPlainText() {
		return plainText;
	}

	/**
	 * Sets the plainText field.
	 * @param plainText Decyphered text.
	 */
	@Override
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	/**
	 * Returns the key field.
	 * @return int key
	 */
	@Override
	public int getKey() {
		return key;
	}

	/**
	 * Sets the key field.
	 * @param key Key value.
	 */
	@Override
	public void setKey(int key) {
		this.key = key;
	}

	/**
	 * Returns the score field.
	 * @return int score
	 */
	@Override
	public double getScore() {
		return score;
	}

	/**
	 * Sets the score field.
	 * @param score Score value.
	 */
	@Override
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * Used in sorting.
	 * @param o Resultable object to comare to.
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html#compareTo(T)">Comparable.compareTo()</a>
	 */
	@Override
	public int compareTo(Resultable o) {
		if (this.getScore() > o.getScore()) {
			return -1;
		} else if (this.getScore() < o.getScore()){
			return 1;
		} else {
			return 0;
		}
	}
}
