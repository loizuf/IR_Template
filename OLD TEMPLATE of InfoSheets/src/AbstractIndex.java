
import java.util.List;

import indexing.Index;
import indexing.Indexable;
//Not used so far
public abstract class AbstractIndex implements Index {
	
	public float getIDF(String term) {
		return 0.0F;
	}

	public int getTF(String term, Indexable doc) {
		return 0;
	}

	public int getDF(String term) {
		return 0;
	}

	public List<Indexable> getDocumentList(String term) {
		return null;
	}
}
