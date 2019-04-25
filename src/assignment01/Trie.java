package assignment01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trie {

    private TrieNode root = new TrieNode();

    /**
     * Inserts a string into the trie and returns the last node that was
     * inserted.
     *
     * @param str The string to insert into the trie
     * @param data	The data associated with the string
     * @return The last node that was inserted into the trie
     */
    public TrieNode insert(String str, TrieData data) {
        char[] strArray = str.toCharArray();
        TrieNode currentNode = root;
        int terminal = str.length() - 1;
        for (int i = 0; i <= terminal; i++) {
            if (currentNode.getChild(strArray[i]) == null) {
                TrieNode child = new TrieNode();
                if (i == terminal) {
                    child.setTerminal(true);
                    child.addData(data);
                }
                currentNode.addChild(strArray[i], currentNode = child);
            } else {
                currentNode = currentNode.getChild(strArray[i]);
                if (i == terminal) {
                    currentNode.setTerminal(true);
                    currentNode.addData(data);
                }
            }
        }
        return currentNode;
    }

    /**
     * Search for a particular prefix in the trie, and return the final node in
     * the path from root to the end of the string, i.e. the node corresponding
     * to the final character. getNode() differs from get() in that getNode()
     * searches for any prefix starting from the root, and returns the node
     * corresponding to the final character of the prefix, whereas get() will
     * search for a whole word only and will return null if it finds the pattern
     * in the trie, but not as a whole word. A "whole word" is a path in the
     * trie that has an ending node that is a terminal node.
     *
     * @param str The string to search for
     * @return the final node in the path from root to the end of the prefix, or
     * null if prefix is not found
     */
    public TrieNode getNode(String str) {
        //System.out.println("Finding... " + str);
        char[] strArray = str.toCharArray();
        TrieNode currentNode = root;
        for (int i = 0; i < str.length(); ++i) {
            //System.out.println("Checking... " + strArray[i]);
            if (currentNode.getChild(strArray[i]) == null) {
                //System.out.println("Found no child");
                return null;
            } else {
                currentNode = currentNode.getChild(strArray[i]);
                //System.out.println("Found child: " + currentNode.toString());
            }
        }
        return currentNode;
    }

    /**
     * Searches for a word in the trie, and returns the final node in the search
     * sequence from the root, i.e. the node corresponding to the final
     * character in the word.
     *
     * getNode() differs from get() in that getNode() searches for any prefix
     * starting from the root, and returns the node corresponding to the final
     * character of the prefix, whereas get() will search for a whole word only
     * and will return null if it finds the pattern in the trie, but not as a
     * whole word. A "whole word" is a path in the trie that has an ending node
     * that is a terminal node.
     *
     * @param str The word to search for
     * @return The node corresponding to the final character in the word, or
     * null if word is not found
     */
    public TrieNode get(String str) {
        TrieNode currentNode = getNode(str);
        if (currentNode != null && currentNode.isTerminal()) {
            return currentNode;
        }
        return null;
    }

    /**
     * Retrieve from the trie an alphabetically sorted list of all words
     * beginning with a particular prefix.
     *
     * @param prefix The prefix with which all words start.
     * @return The list of words beginning with the prefix, or an empty list if
     * the prefix was not found.
     */
    public List<String> getAlphabeticalListWithPrefix(String prefix) {
        char[] prefixArray = prefix.toCharArray();
        TrieNode currentNode = root;
        TrieNode prefixEnd = getNode(prefix);
        ArrayList<String> suffixes = new ArrayList<>();

        if (prefixEnd != null && prefixEnd.getNumChildren() > 0) {
            suffixes = findTerminal(prefixEnd, prefix);
            if (prefixEnd.isTerminal()) {
                suffixes.add(prefix);
            }
        }
        Collections.sort(suffixes);
        return suffixes;
    }

    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Finds the most frequently
     * occurring word represented in the trie (according to the dictionary file)
     * that begins with the provided prefix.
     *
     * @param prefix The prefix to search for
     * @return The most frequent word that starts with prefix
     */
    public String getMostFrequentWordWithPrefix(String prefix) {
        List<String> words = getAlphabeticalListWithPrefix(prefix);
        TrieNode highestNode = null;
        String highestWord = "";
        for (String word : words) {
            TrieNode wordNode = get(word);            
            if (highestNode != null && wordNode.getData().getFrequency() > highestNode.getData().getFrequency()) {
                highestNode = wordNode;
                highestWord = word;            
            } else if (highestNode == null) {
                highestNode = wordNode;
                highestWord = word;
            }
        }
        return highestWord;
    }

    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Reads in a dictionary from file
     * and places all words into the trie.
     *
     * @param fileName the file to read from
     * @return the trie containing all the words
     */
    public static Trie readInDictionary(String fileName) {

        Trie t = new Trie();
        Scanner fileScanner;

        try {
            // use a FileInputStream to ensure correct reading end-of-file
            fileScanner = new Scanner(new FileInputStream(fileName));

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                //System.out.println(nextLine);
                String dataIn[] = nextLine.split("\\s+");

                TrieData data = new TrieData(Integer.parseInt(dataIn[2]), Integer.parseInt(dataIn[0]));
                //System.out.println("dataIn[1]: "+ dataIn[1] + " data:" + data);
                t.insert(dataIn[1], data);
                // TODO: call insert() here to insert the data object into the dictionary! 
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return t;
    }

    private ArrayList<String> findTerminal(TrieNode node, String current) {
        String currentString = current;
        ArrayList<String> returnArray = new ArrayList<>();
        for (Map.Entry<Character, TrieNode> pair : node.getChildren().entrySet()) {
            String wordString = currentString + pair.getKey();
            if (pair.getValue().isTerminal()) {
                returnArray.add(wordString);
                if (pair.getValue().getNumChildren() > 0) {
                    returnArray.addAll(findTerminal(pair.getValue(), wordString));
                }
            } else {
                returnArray.addAll(findTerminal(pair.getValue(), wordString));
            }
        }
        return returnArray;
    }
}
