package assignment01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SuffixTrie {

    private SuffixTrieNode root = new SuffixTrieNode();

    /**
     * Insert a String into the suffix trie. For the assignment the string str
     * is a sentence from the given text file.
     *
     * @param str the sentence to insert
     * @param startIndex the starting index of the sentence
     * @return the final node inserted
     */
    public SuffixTrieNode insert(String str, int sentenceNum, int startIndex, int SentenceIndex) {
        SuffixTrieNode currentNode = root;
        char[] strChars = str.toCharArray();
        String indexStr = sentenceNum + ":";
        int sentenceIndex = SentenceIndex;
        int currentIndex = startIndex;
        for (char strChar : strChars) {
            if (currentNode.hasChild(strChar)) {
                currentNode = currentNode.getChild(strChar); //gets the child 
            } else { //if no child exists
                currentNode.addChild(strChar, new SuffixTrieNode());
                currentNode = currentNode.getChild(strChar);
            }
            currentNode.addData(indexStr + currentIndex + "~" + sentenceIndex);
        }

        if (str.length() >= 1) {
            insert(str.substring(1), sentenceNum, ++currentIndex, sentenceIndex);
        }

        return currentNode;
    }

    /**
     * Get the suffix trie node associated with the given (sub)string.
     *
     * @param str the (sub)string to search for
     * @return the final node in the (sub)string
     */
    public SuffixTrieNode get(String str) {
        System.out.println("Finding... " + str);
        char[] strArray = str.toCharArray();
        SuffixTrieNode currentNode = root;
        for (int i = 0; i < str.length(); ++i) {
            if (currentNode.getChild(strArray[i]) == null) {
                System.out.println("Found no child");
                return null;
            } else {
                currentNode = currentNode.getChild(strArray[i]);
            }
        }
        return currentNode;

    }

    /**
     * Helper/Factory method to build a SuffixTrie object from the text in the
     * given file. The text file is broken up into sentences and each sentence
     * is inserted into the suffix trie.
     *
     * It is called in the following way
     * <code>SuffixTrie st = SuffixTrie.readInFromFile("Frank.txt");</code>
     *
     * @param fileName
     * @return
     */
    public static SuffixTrie readInFromFile(String fileName) {
        System.out.println("Starting read in of file...");
        SuffixTrie sT = new SuffixTrie();
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new FileInputStream(fileName));
            int sentenceNum = 0;
            int SentenceIndex = 0;
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                String dataIn[] = nextLine.split("[!|?|;]|\\.");
                for (String string : dataIn) {
                    sT.insert(string, sentenceNum, 0, SentenceIndex);
                    ++sentenceNum;
                    SentenceIndex += string.length()+1; //+1 for delimiter character.
                }

            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished creating suffix trie");
        return sT;
    }
}
