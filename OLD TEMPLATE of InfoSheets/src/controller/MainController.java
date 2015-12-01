package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import indexing.Document;
import indexing.InvertedIndex;
import indexing.BooleanQuery;
import searchengines.BooleanSearchEngine;

// This performs a boolean search with two terms and the operator 'AND' over a testcollection

public class MainController {

	/* 
	 * collection - List of all document-objects
	 * invertedIndex - 
	 */
	private static ArrayList<Document> collection;
	private static InvertedIndex invertedIndex;

	/*
	 * LoremIpsumCollectionDirectoryPath - Name of the directory containing the files
	 */
	private static final String LoremIpsumCollectionDirectoryPath = "collections";

	public static void main(String[] args) throws IOException {
		readCollection();
		invertedIndex = new InvertedIndex(collection);
		BooleanQuery query = getQuery();

		// This is a list with all numbers of documents which contain BOTH searchterms
		ArrayList<Integer> resultPostingsList = BooleanSearchEngine.performSearch(invertedIndex, query);

		/*
		 * This just creates a List which contains all NAMES of the files in the
		 * resultPostingsList (which contains just the numbers)
		 */
		ArrayList<String> mergeResult = new ArrayList<>();
		for (int docNumber : resultPostingsList) {
			mergeResult.add(invertedIndex.getDocumentName(docNumber));
		}
		postResults(mergeResult, query);
	}

	/*
	 * directory is the overall document location. this method iterates over all
	 * documents in directory. for each file in directory we create a document
	 * object with relevant information and store that in collection.
	 */
	private static void readCollection() throws FileNotFoundException {
		collection = new ArrayList<>();
		File directory = new File(LoremIpsumCollectionDirectoryPath);
		File[] directoryListing = directory.listFiles();

		for (File child : directoryListing) {
			/*
			 * We use Scanner object to read in documents, but we will use our
			 * own Tokenizer to see how various pre-processing steps work.
			 */
			Scanner scanner = new Scanner(child).useDelimiter("\\A");
			String entireTextFile = scanner.next();
			scanner.close();

			Document document = new Document(entireTextFile, child.getName());
			collection.add(document);
		}
	}

	/*
	 * This method reads in a Query. The query is expected to contain ONE 'AND'
	 * and is only developed for Boolean Retrieval. A Query-Object is created
	 * and returned
	 */
	private static BooleanQuery getQuery() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter your query:");
		String input = reader.readLine();
		BooleanQuery currentQuery = new BooleanQuery(input);
		return currentQuery;
	}

	/*
	 * This method does not contain functionality of any significance
	 * whatsoever. This is just a formatted Output for the results
	 */
	private static void postResults(ArrayList<String> mergeResult, BooleanQuery query) {
		ArrayList<String> queryWordList = query.getUniqueWordList();
		System.out.println("+++++++");
		System.out.println("");
		switch (queryWordList.size()) {
		case 0:
			System.out.println("No query recognized.");
			break;
		case 1:
			if (mergeResult.size() > 0) {
				System.out.println("The word '" + queryWordList.get(0) + "' can be found in the documents:");
			} else {
				System.out.println("No match for the word '" + queryWordList.get(0) + "'");
			}
			break;
		default:
			if (mergeResult.size() > 0) {
				System.out.println("The words '" + queryWordList.get(0) + "' and '" + queryWordList.get(1)
						+ "' can be found in the documents:");
			} else {
				System.out.println(
						"No match for the words '" + queryWordList.get(0) + "' and '" + queryWordList.get(1) + "'");
			}
			break;
		}
		for (String string : mergeResult) {
			System.out.println(string);
		}
	}
}
