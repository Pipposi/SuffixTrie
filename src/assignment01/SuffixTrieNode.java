package assignment01;

import java.util.ArrayList;
import java.util.HashMap;
/** 
 * In determining the data structure to use to represent the child nodes, I decided on using a Hash Map
 * because I saw no need for the elements to be pre-sorted according to key size.
 * @author Daniel
 */

public class SuffixTrieNode {

    private SuffixTrieData data = new SuffixTrieData();
    boolean isTerminal = false;
    private HashMap<Character, SuffixTrieNode> children = new HashMap<>(); 
    int numChildren = 0;

    public SuffixTrieNode getChild(char label) {
        return children.get(label);
    }

    public boolean hasChild(char label) {
        return children.containsKey(label);
    }

    public void addChild(char label, SuffixTrieNode node) {
        children.put(label, node);
    }

    public void addData(String startIndex) {
        data.addStartIndex(startIndex);
    }

    public ArrayList<String> getAllData() {
        return data.getAllData();
    }

    public String toString() {
        if (data != null) {
            return "SuffixTrieNode: data=" + data.toString() + " children: " + children.size();
        }
        return "SuffixTrieNode: null";
    }
}
