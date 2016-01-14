package ie.gmit.sw.result;

/**
 * <h1>TextScorer</h1>
 * Calculate and retrieve the score of texts.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 01/01/2016
 *
 */
public class TextScorer {
	
	/**
	 * Returns score based on the Quad Gram map by comparing and scoring 4 char long segments.
	 *  
	 * @param text decyphered text.
	 * @return Cumulative score for decyphered text.
	 */
	public static double getScore(String text){
		double score = 0f;

		// For the length of the text score each 4 char segment.
		for (int i = 0; i < text.length(); i++){
			if (i + QuadGramMap.getGramSize() <= text.length() -1){
				score += computeLogScore(text.substring(i, i + QuadGramMap.getGramSize()));
			}
		}
		return score;
	}
	
	/**
	 * Compute the score for each 4 char segment based on the quad gram map.
	 * 
	 * @param quadgram 4 char segment to score.
	 * @return score as a double.
	 */
	private static double computeLogScore(String quadgram){
		if (QuadGramMap.getMap().containsKey(quadgram)){
			double frequency = QuadGramMap.getMap().get(quadgram);
			double total = (double) QuadGramMap.getMap().size();
			double probability = (double) (frequency/total);
			
			return Math.log10(probability);
		}else{
			return 0f;
		}
	}
}
