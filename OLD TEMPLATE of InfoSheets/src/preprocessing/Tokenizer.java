package preprocessing;

import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer {
	
	/*
	 * nonAlphaNumericalRegex - regular expression used to find all non alphanumerical (or '-') characters
	 * whiteSpaceRegex - regular expression used to find all whitespace characters
	 */
	private static final String nonAlphaNumericalRegex = "[^a-zA-Z0-9 -]";
	private static final String whiteSpaceRegex = "\\s+";

	/**
	 * Returns a list of terms which were seperated by 'AND' in the user-entry.
	 * @param sentence - complete entry from the user
	 * @return result - list of terms which were seperated by an 'AND'
	 */
	public static ArrayList<String> tokenizeBooleanAND(String sentence) {
		//This splits the query at 'AND'
		ArrayList<String> result = new ArrayList<>(Arrays.asList(sentence.split("AND")));
		return format(result);
	}
	
	/**
	 * Returns a list of terms which were seperated by whitespace in the document.
	 * @param text - complete text from the document
	 * @return result - list of terms which were seperated by whitespace
	 */
	public static ArrayList<String> tokenize(String text) {
		// This splits the query by whitespace and converts the tokens to an ArrayList
		ArrayList<String> result = new ArrayList<>(Arrays.asList(text.split(whiteSpaceRegex)));
		return format(result);
	}
	
	// formats the entries in the result-list
	private static ArrayList<String> format(ArrayList<String> result) {
		for (int i=0; i<result.size(); i++) {
			// gets an entry from results, deletes unwanted characters,
			// deletes whitespace, converts to lower case
			result.set(i, result.get(i).replaceAll(nonAlphaNumericalRegex, "").trim().toLowerCase());
		}
		return result;
	}
}
	//Everything below this Point is currently not relevant
	
	/*

	//
	
	private static void format(ArrayList<String> result) {
		for (int i=0; i<result.size(); i++) {
			String current = result.get(i);
			current = current.replaceAll(nonAlphaNumericalRegex, "");
			current = current.trim();
			current = current.toLowerCase();
			result.set(i, current);
		}
	}
	
	public static ArrayList<String> tokenize(String sentence, boolean booleanRetrieval) {
		/*
		 *  This splits the query. If booleanRetrieval is true only "AND" is used as a delimiter and
		 *  EVERYTHING on both sides of the end is treated as ONE WORD WITH Whitespace
		 *
		ArrayList<String> result = new ArrayList<>();
		if(booleanRetrieval){
			result = new ArrayList<String>(Arrays.asList(sentence.split("AND")));
		} else {
			result = new ArrayList<String>(Arrays.asList(sentence.split(whiteSpaceRegex)));
		}
		format(result);
		result.removeAll(Arrays.asList("", null));
		return result;
	}
	*/

	/* Only for testpurposes
	private static void testOutput(ArrayList testOutput) {
		System.out.println();
		System.out.println("TESTOUTPUT");
		for (Object object : testOutput) {
			System.out.println(object);
		}
	}
	*/
