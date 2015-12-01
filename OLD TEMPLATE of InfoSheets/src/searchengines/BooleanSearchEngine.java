package searchengines;

import java.util.ArrayList;
import java.util.ListIterator;

import indexing.InvertedIndex;
import indexing.BooleanQuery;

// this class provides methods to perform a boolean search and an 'AND'-merge

public class BooleanSearchEngine {

	/**
	 * This method takes a query, gets the first two searchterms which are safed
	 * in the query (if there were more they get ignored). with the postingslist
	 * for these terms an 'AND'-merge is performed and the result is returned
	 * 
	 * @param invertedIndex
	 *            - IndexObject representing the inverted Index
	 * @param query
	 *            - query object representing the query
	 * @return resultPostingsList - list of numbers of documents which contain
	 *         BOTH searchterms
	 */
	public static ArrayList<Integer> performSearch(InvertedIndex invertedIndex, BooleanQuery query) {

		ArrayList<String> queryWordList = query.getUniqueWordList();
		ArrayList<Integer> resultPostingsList;

		// case 0 and 1 are not relevant and are just there to catch errors
		// default handles all cases with 2 terms. cases with more than 2 terms
		// are handled as if there were only 2
		switch (queryWordList.size()) {

		// returns empty list
		case 0:
			resultPostingsList = new ArrayList<>();
			break;

		// returns list for single term
		case 1:
			String term = queryWordList.get(0);
			resultPostingsList = invertedIndex.searchForSingleWord(term);
			break;

		// gets the postingslist for both terms and performs the 'AND'-merge
		default:
			String firstTerm = queryWordList.get(0);
			String secondTerm = queryWordList.get(1);
			ArrayList<Integer> firstPostingsList = invertedIndex.searchForSingleWord(firstTerm);
			ArrayList<Integer> secondPostingsList = invertedIndex.searchForSingleWord(secondTerm);
			resultPostingsList = performANDMerge(firstPostingsList, secondPostingsList);
			break;
		}
		return resultPostingsList;
	}
	
	/*
	 * this method performs the actual 'AND'-merge just as we have seen in the lecture.
	 * The DocumentIDs in the postingslist MUST be sorted in ascending order for this to work
	 * this is ensured in a different part of the code
	 */
	private static ArrayList<Integer> performANDMerge(ArrayList<Integer> firstPostingsList, ArrayList<Integer> secondPostingsList) {
		ArrayList<Integer> result = new ArrayList<>();
		
		// adds a final 0 to the postingslist. no document has the number 0 so if this occurs
		// the postingslist can be treated as empty. This MUST happen BEFORE the iterator is created
		// or else the iterator becomes invalid
		firstPostingsList.add(0);
		secondPostingsList.add(0);
		
		// next() isn't supported by arraylists so we have to use an iterator.
		// there might be better solutions (treemaps)
		ListIterator<Integer> firstIt = firstPostingsList.listIterator();
		ListIterator<Integer> secondIt = secondPostingsList.listIterator();
		
		// actual ID which are compared
		int firstID = firstIt.next();
		int secondID = secondIt.next();
		
		// directly from lecture slides
		while(firstID!=0 && secondID!=0){
			if(firstID == secondID){
				result.add(firstID);
				firstID = firstIt.next();
				secondID = secondIt.next();
			} else {
				if(firstID < secondID) {
					firstID = firstIt.next();
				} else {
					secondID = secondIt.next();
				}
			}
		}
		return result;
	}
}
