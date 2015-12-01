package indexing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import preprocessing.Tokenizer;

public class BooleanQuery implements Indexable {
	
	/*
	 * plainText - One String conaining the complete text
	 * tokens - List of the words as they appear in the Query (allready split by Tokenizer)
	 * wordcounts - Hashmap containing the number of times (value) a term (key) appears in the text
	 */
	private String plainText;
	private ArrayList<String> tokens;
	private HashMap<String, Integer> wordCounts;
	
	/**
	 * Constructor for a representation of a boolean query within the code.
	 * 
	 * @param searchText - String containing the complete user-search-input
	 */
	public BooleanQuery(String searchText) {
		plainText = searchText;
		tokens = new ArrayList<>();
		tokens = Tokenizer.tokenizeBooleanAND(plainText);
		
		// This fills the Hashmap which counts the occurrences of a term
		wordCounts = new HashMap<>();
		for (String word : tokens) {
			Integer count = wordCounts.get(word);
			if (count == null) {
				wordCounts.put(word, 1);
			} else {
				wordCounts.put(word, count + 1);
			}
		}
	}

	// Interface Method (Indexable)
	public HashMap<String, Integer> getWordCountList() {
		return wordCounts;
	}

	// Interface Method (Indexable)
	public ArrayList<String> getWordList() {
		System.out.println("getting from query: "+tokens);
		return tokens;
	}

	// Interface Method (Indexable)
	public ArrayList<String> getUniqueWordList() {
		// This step eliminates all duplicates from the arraylist while maintaining the order
		ArrayList<String> uniqueWordList = new ArrayList<String>(new LinkedHashSet<>(tokens));
		return uniqueWordList;
	}

	// Interface Method (Indexable)
	public int getWordCount(String word) {
		return wordCounts.get(word);
	}

	// Interface Method (Indexable)
	public String getPlainText() {
		return plainText;
	}

}
