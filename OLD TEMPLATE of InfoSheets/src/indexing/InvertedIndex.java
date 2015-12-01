package indexing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvertedIndex implements Index {

	/*
	 * invertedIndex - Hashmap containing a term (key) and an ArrayList (value) with the numbers of the documents containing this term
	 * documentNameList - Hashmap containing the numbers (key) representing the documents and their names (value)
	 */
	private HashMap<String, ArrayList<Integer>> invertedIndex;
	private HashMap<Integer, String> documentNameList;

	/**
	 * Constructor for the invertedIndex which saves for every term the documents in which the term appears
	 * @param collection - ArrayList of document objects
	 */
	public InvertedIndex(ArrayList<Document> collection) {
		invertedIndex = new HashMap<>();
		documentNameList = new HashMap<>();
		int counter = 1;
		
		// This goes through the whole collection and passes every document along with
		// a unique number on to 'generateInvertedIndex()'
		for (Document document : collection) {
			documentNameList.put(counter, document.getName());
			generateInvertedIndex(document.getUniqueWordList(), counter);
			counter++;
		}
	}

	/*
	 * For every word in the document:
	 * if index allready contains an entry for this word, this documents number is added,
	 * else a new entry for this word is created and then this documents number is added
	 */
	private void generateInvertedIndex(ArrayList<String> wordsInDocument, int counter) {
		for (String word : wordsInDocument) {
			if (!invertedIndex.containsKey(word)) {
				ArrayList<Integer> newWordEntry = new ArrayList<>();
				invertedIndex.put(word, newWordEntry);
			} 
			invertedIndex.get(word).add(counter);
		}
	}

	/**
	 * returns a list of numbers representing the document which contain 'word'
	 * @param word - string which is searched for
	 * @return result - list of documentnumbers
	 */
	public ArrayList<Integer> searchForSingleWord(String word) {
		ArrayList<Integer> result = invertedIndex.get(word);
		if(result==null){
			return new ArrayList<Integer>();
		}
		return result;
	}
	
	/**
	 * returns the name of the document with the number 'number'
	 * @param number - number of the document
	 * @return name - name of the document
	 */
	public String getDocumentName(int number) {
		String name;
		name = documentNameList.get(number);
		return name;
	}

	@Override
	public float getIDF(String paramString) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTF(String paramString, Indexable paramIndexable) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDF(String paramString) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Indexable> getDocumentList(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}
}
