import java.util.ArrayList;
import java.util.List;

public class Trie {
    //Trie is a sorted tree-based data-structure that stores the set of strings.
    static TrieNode root;
    static int indexes;
//The character length determines the depth of the trie.
    /*If the present node already has a reference to the present letter
            set the present node to that referenced node. 
            Otherwise, create a new node, 
            set the letter to be equal to the present letter, 
            and even start the present node with this new node. */ 
            
            static void insert(String key)
            {
                int length = key.length();
                int index;
               
                TrieNode current = root;
               
                for (int level = 0; level < length; level++)
                {
                    index = key.charAt(level) - 'a';
                    if (current.children[index] == null)
                        current.children[index] = new TrieNode();
               
                    current = current.children[index];
                }
               
                // mark last node as leaf
                current.isEndOfWord = true;
            }

        public static List<String> getListOfMatchedWords(TrieNode root, String prefix) {
            List<String> prefixMatches = new ArrayList<>();
    
            // find node that ends in prefix
            TrieNode current = root;
            int currChar;
            for (int i=0; i<prefix.length(); i++) {
                currChar = prefix.charAt(i) - 'a';
                if (current.children[currChar] == null) return prefixMatches;
                current = current.children[currChar];
            }
    
            traverseTrie(current, prefixMatches, prefix);
    
            return prefixMatches;
        }
    
        public static List<String> traverseTrie(TrieNode root, List<String> leaves, String parent) {
            if (root.children == null) {
                leaves.add(parent);
                return leaves;
            }
            if (root.numOfChildren < root.frequency) {
                leaves.add(parent);
            }
            for (int i=0; i<root.children.length; i++) {
                if (root.children[i] != null) {
                    char letter = (char) (i + 'a');
                    traverseTrie(root.children[i], leaves, parent + letter);
                }
            }
            return leaves;
        }



        static boolean search(TrieNode root, String word){
            int length =word.length();
            TrieNode current=root;
            
            for(int i=0; i<length; i++){
                int index =word.charAt(i) -'a';
                if(current.children[index]==null)
                return false;

                current=current.children[index];


            }
            return current!=null && current.isEndOfWord;

        }
        static boolean isEmpty(TrieNode root)
    {
        for (int i = 0; i < 26; i++)
            if (root.children[i] != null)
                return false;
        return true;
    }
    static TrieNode remove(TrieNode root, String word, int depth){
        int length=word.length();
        if(root==null)
        return null;
        if(length==depth){

            if(root.isEndOfWord)
            root.isEndOfWord=false;

            if(isEmpty(root))
            root=null;

            return root;
        }

        int index =word.charAt(depth)-'a';
        root.children[index]=remove(root.children[index], word, depth+1);

        if(isEmpty(root) && root.isEndOfWord==false)
        root=null;

        return root;

        

    }
    static int countChildren(TrieNode node)
    {
        int count = 0;
        for (int i=0; i<26; i++)
        {
            if (node.children[i] != null)
            {
                count++;
                indexes = i;
            }
        }
        return (count);
    }
       
    // Perform a walk on the trie and return the
    // longest common prefix string
    static String walkTrie()
    {
        TrieNode current = root;
        indexes = 0;
        String prefix = "";
       
        while (countChildren(current) == 1 &&
                current.isEndOfWord == false)
        {
            current = current.children[indexes];
            prefix += (char)('a' + indexes);
        }
        return prefix;
    }
       
    // A Function to construct trie
    static void constructTrie(String arr[], int n)
    {
        for (int i = 0; i < n; i++)
            insert (arr[i]);
        return;
    }
       
    // A Function that returns the longest common prefix
    // from the array of strings
    static String commonPrefix(String arr[], int n)
    {
        root = new TrieNode();
        constructTrie(arr, n);
       
        // Perform a walk on the trie
        return walkTrie();
    }
    public static void main(String args[])
    { /* 
        // Input keys (use only 'a' through 'z'
        // and lower case)
        String keys[] = { "the", "a", "there",
                        "answer", "any", "by",
                        "bye", "their", "hero", "heroplane" };
        int n = keys.length;
 
        TrieNode root = new TrieNode();
 
        // Construct trie
        for (int i = 0; i < n; i++)
            insert(root, keys[i]);
 
        // Search for different keys
        if(search(root, "the"))
            System.out.println("Yes");
        else
            System.out.println("No");
 
        if(search(root, "these"))
            System.out.println("Yes");
        else
            System.out.println("No");
 
        remove(root, "heroplane", 0);
         
        if(search(root, "hero"))
            System.out.println("Yes");
        else
            System.out.println("No");
*/

 
            String[] lsOfWords = {"there", "their", "answer", "any", "bye", "an", "a"};
            TrieNode trie = new TrieNode();
 
            for (String word : lsOfWords) {
                insert(word);
            }
    
            List<String> treeToArr = traverseTrie(root, new ArrayList<String>(), "");
            System.out.println(treeToArr);
    
            List<String> matches = getListOfMatchedWords(trie, "an");
            System.out.println(matches);
     
           
      /*   String arr[] = {"geeksforgeeks", "geeks",
                        "geek", "geezer"};
        int n = arr.length;
       
        String ans = commonPrefix(arr, n);
       
        if (ans.length() != 0)
            System.out.println("The longest common prefix is "+ans);
        else
            System.out.println("There is no common prefix");
            */
    }
}
    

