package indexing;

import java.util.ArrayList;
import java.util.HashMap;

import preprocessing.Tokenizer;

/*
 * creating and storing these documents might be not sensible in an actual IR-system. This is done here
 * to represent all files (in there original form, so no stemming or anything else) and to give access
 * to all relevant information of the documents.
 */
public class Document implements Indexable {

	/*
	 * plainText - One String conaining the complete text
	 * wordList - List of the words as they appear in the text (with duplicates)
	 * wordcounts - Hashmap containing the number of times (value) a term (key) appears in the text
	 * name - Name of the document
	 */
	private String plainText;
	private ArrayList<String> wordList;
	private HashMap<String, Integer> wordCounts;
	private String name;

	/**
	 * Constructor for a representation of a document within the code.
	 * 
	 * @param unalteredWords - String containing the complete text of the document
	 * @param name - name of the document
	 */
	public Document(String unalteredWords, String name) {
		
		this.name = name;
		plainText = unalteredWords;
		wordList = Tokenizer.tokenize(plainText);
		
		// This fills the Hashmap which counts the occurences of a term
		wordCounts = new HashMap<>();
		for (String word : wordList) {
			Integer count = wordCounts.get(word);
			if (count == null) {
				wordCounts.put(word, 1);
			} else {
				wordCounts.put(word, count + 1);
			}
		}
	}
	
	/**
	 * Returns the total number of terms in this document (with duplicates)
	 * @return Number of total Words in this Document
	 */
	public int getTotalWordCount() {
		return wordList.size();
	}

	/**
	 * Returns the name of the document
	 * @return name - String containing the name of the document
	 */
	public String getName() {
		return name;
	}

	// Interface Method (Indexable)
	public HashMap<String, Integer> getWordCountList() {
		return wordCounts;
	}

	// Interface Method (Indexable)
	public int getWordCount(String word) {
		return wordCounts.get(word);
	}

	// Interface Method (Indexable)
	public int getUniqueWordCount() {
		return wordCounts.size();
	}

	// Interface Method (Indexable)
	public ArrayList<String> getWordList() {
		return wordList;
	}

	// Interface Method (Indexable)
	public ArrayList<String> getUniqueWordList() {
		ArrayList<String> uniqueWordList = new ArrayList<String>(wordCounts.keySet());
		return uniqueWordList;
	}
	
	// Interface Method (Indexable)
	public String getPlainText() {
		return plainText;
	}
}