package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* Name: TextScorer.java 
 * Author: John Malcolm Anderson
 * Date: 31/12/2015
 * Description: Contains getScore & computeLogScore methods for 
 *  calculating and retrieving score of texts.
 */

public class TextScorer {
	private Map<String, Double> map = new ConcurrentHashMap<String, Double>(); 
	
	public TextScorer(Map<String, Double> m){
		this.map = m;
	}
	
	// Get Score method
	public double getScore(String text){
		double score = 0f;

		for (int i = 0; i < text.length(); i++){
			if (i + QuadGramMap.GRAM_SIZE <= text.length() -1){
				score += computeLogScore(text.substring(i, i + QuadGramMap.GRAM_SIZE));
			}
		}
		return score;
	}
	
	// Compute Log Score method
	public double computeLogScore(String quadgram){
		if (map.containsKey(quadgram)){
			double frequency = map.get(quadgram);
			double total = (double) map.size();
			double probability = (double) (frequency/total);
			
			return Math.log10(probability);
		}else{
			return 0f;
		}
	}
}
