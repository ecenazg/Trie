import java.util.ArrayList;
import java.util.Scanner;



public class TernarySearchTree   {
    int size;
    TSTNode root;
    private ArrayList<String> index;

    public TernarySearchTree()  
    {  
        root = null;  
    }  
    /** function to check if empty **/  
    public boolean isEmpty()  
    {  
        return root == null;  
    }  
    public void insert(String word)  
    {  
        root = insert(root, word.toCharArray(), 0);  
    }  
    public TSTNode insert(TSTNode root, char[] word, int ptr){

        if(root==null)
        root=new TSTNode(word[ptr]);

        if(word[ptr]<root.data)
        root.left=insert(root.left, word, ptr);

        else if(word[ptr]>root.data)
        root.right=insert(root.right,word, ptr);

        else{
            if(ptr+1<word.length)
            root.middle=insert(root.middle, word, ptr+1);

            else
            root.isEndOfWord=true;
        }
        return root;


        }
        public void remove(String word){
            remove(root,word.toCharArray(),0);
        }
        public void remove(TSTNode root, char[] word, int ptr){
            if(root==null)
            return;

            if(word[ptr]<root.data){
                remove(root.left,word,ptr);
            }
            else if(word[ptr]>root.data){
                remove(root.right, word, ptr);
            }
              /** to delete a word just make isEnd false **/  
            else if(word[ptr]==root.data){
                if(root.isEndOfWord && ptr ==word.length-1)
                root.isEndOfWord=false;
                else if(ptr+1<word.length)
                remove(root.middle, word, ptr+1);
            }
        }
        public boolean search(String word){
            return search(root, word.toCharArray(), 0);

        }
        public boolean search(TSTNode root, char[] word, int ptr){
            if(root==null)
            return false;

            if(word[ptr]<root.data)
            search(root.left, word, ptr);

            if(word[ptr]>root.data)
            search(root.right, word, ptr);

            if(word[ptr]==root.data){
                if(root.isEndOfWord && ptr ==word.length-1)
                return true;

            
            else if(ptr==word.length-1)
            return false;

            else
            return search(root.middle, word, ptr+1);

            }
            return false;
            
        }
        public String toString(){
            index= new ArrayList<String>();
            traverse(root, "");
            return "Ternary Search Tree:" + index;
        }
        public void traverse(TSTNode root, String str){
            if(root!=null){
                traverse(root.left, str);
                str=str+root.data;
                if(root.isEndOfWord)
                index.add(str);
                traverse(root.middle, str);
                str=str.substring(0,str.length()-1);
                traverse(root.right, str);



            }
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

        }

    }

