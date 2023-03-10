public class TrieNode {

    int frequency;
    int numOfChildren;
    TrieNode[] children = new TrieNode[26];
    // isEndOfWord is true if the node 
    // represents end of a word 
    boolean isEndOfWord;
    
    TrieNode(){
        isEndOfWord = false;
        for (int i = 0; i < 26; i++)
            children[i] = null;
    }

    
}