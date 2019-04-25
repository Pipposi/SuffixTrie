/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment01;

/**
 *
 * @author lewi0146
 */
public class SuffixTrieTester {

    public static void main(String[] args) {
        /*
        text files used in testing.
        Frankenstein1.txt is a short version of Frankenstein.txt - it only contains the first few chapters
        */
        
        //String fileName = "Frankenstein.txt";
        String fileName = "Frankenstein1.txt";
       
        SuffixTrie st = SuffixTrie.readInFromFile(fileName);
        /**
        below are sample input strings to match
        the number of strings to match was varied to see if it would have an impact on performance
        **/
        
        //String[] ss = {"the"};
        //String[] ss = {"the", "was", "here"};
        //String[] ss = {"hideous", "the only", "onster", ", the", "ngeuhhh"};
        String[] ss = {"sadness", "forest that day, ", "and applied the words, 'fire,' 'milk,' 'bread,' and '", ", the", "expressed", "where", "how", "therefore", "wherever", "I'm"};
        for (String s : ss) {
            SuffixTrieNode sn = st.get(s);
            System.out.println("[" + s + "]: " + sn);
        }
    }
}
