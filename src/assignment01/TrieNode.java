package assignment01;

import java.util.HashMap;

public class TrieNode {
    private TrieData data = null;
    private boolean terminal = false;
    private int numChildren = 0;
    private int rank;
    private HashMap<Character, TrieNode> children = new HashMap<>();
      
    /**
     * Lookup a child node of the current node that is associated with a
     * particular character label.
     *
     * @param label The label to search for
     * @return The child node associated with the provided label
     */
    public TrieNode getChild(char label) {
        return getChildren().get(label);
    }
    /**
     * Add a child node to the current node, and associate it with the provided
     * label.
     *
     * @param label The character label to associate the new child node with
     * @param node The new child node that is to be attached to the current node
     */
    public void addChild(char label, TrieNode node) {
        getChildren().put(label, node);
        numChildren++;
    }
    

    /**
     * Add a new data object to the node.
     *
     * @param dataObject The data object to be added to the node.
     */
    public void addData(TrieData dataObject) {
        this.data = dataObject;
    }

    public TrieData getData() {
        return data;
    }

    /*
     * The toString() method for the TrieNode that outputs in the format
     *   TrieNode; isTerminal=[true|false], data={toString of data}, #children={number of children} 
     * for example,
     *   TrieNode; isTerminal=true, data=3, #children=1
     * @return 
     */

    @Override
    public String toString() {
        return "TrieNode; isTerminal=" + isTerminal() + ", data=" + ((data!=null) ? data.getFrequency(): null)+ ", rank=" +  ((data!=null) ? data.getRank(): null) + ", #children=" + getChildren().size();
    }

    /**
     * @return the terminal
     */
    public boolean isTerminal() {
        return terminal;
    }

    /**
     * @param terminal the terminal to set
     */
    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    /**
     * @return the children
     */
    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    /**
     * @return the numChildren
     */
    public int getNumChildren() {
        return numChildren;
    }
    

}
