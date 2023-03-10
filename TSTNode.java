public class TSTNode {
    char data;
    boolean isEndOfWord;
    TSTNode left, middle, right;
    public TSTNode(char data) {
        this.data = data;
        this.isEndOfWord = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }


    
}
