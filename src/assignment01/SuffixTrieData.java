package assignment01;

import java.util.ArrayList;

public class SuffixTrieData {

    private ArrayList<String> startIndexes = new ArrayList<String>();

    public void addStartIndex(String startIndex) {
        startIndexes.add(startIndex);
    }

    public String toString() {
        return "Num: " + numberOfIndexes() + " " + startIndexes.toString();
    }

    public int numberOfIndexes() {
        return startIndexes.size();
    }

    public ArrayList<String> getAllData() {
        return startIndexes;
    }
}
