package ie.gmit.sw.result;

import ie.gmit.sw.io.QuadGramMap;

/* Name: TextScorer.java 
 * Author: John Malcolm Anderson
 * Date: 31/12/2015
 * Description: Contains getScore & computeLogScore methods for 
 *  calculating and retrieving score of texts.
 */

public class TextScorer {
	
	public TextScorer(){
	}
	
	// Get Score method
	public static double getScore(String text){
		double score = 0f;

		for (int i = 0; i < text.length(); i++){
			if (i + QuadGramMap.getGramSize() <= text.length() -1){
				score += computeLogScore(text.substring(i, i + QuadGramMap.getGramSize()));
			}
		}
		return score;
	}
	
	// Compute Log Score method
	public static double computeLogScore(String quadgram){
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
