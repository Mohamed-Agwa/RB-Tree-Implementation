package Main;
 
import java.util.NoSuchElementException;
import java.lang.String;
 
 
public class RB_Tree {
    public Node root;
    public static final boolean RED   = true;
    public static final boolean BLACK = false;
   
    public boolean isRed(Node x){
        if(x==null)
            return false;
        return x.color==RED;
    }
    public void insert(int key,String val){
        if(val==null){
            return;
        }
        root=insert(root,key,val);
        root.color=BLACK;        
    }
   
   
    public Node insert(Node h,int key,String val){
        if(h==null)
            return new Node(key,val,RED);
       
        int cmp= h.compareTo(key);
       
        if(cmp<0)
            h.left=insert(h.left,key,val);
        else if(cmp>0)
            h.right = insert(h.right, key, val);
        else
            h.val=val;        
        return h;
    }
        public void delete(float key) {
        if (0.0f == key) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
 
        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
 
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }
 
    // delete the key-String pair with the given key rooted at h
    private Node delete(Node h, float key) {
        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
     private Node balance(Node h) {
 
        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);
 
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }
   private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }
 
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
   
     private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
 public boolean isEmpty() {
        return root == null;
    }
 public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
 
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;
 
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
 
        h.left = deleteMin(h.left);
        return balance(h);
    }
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
 
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
       
    }
    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);
 
        if (h.right == null)
            return null;
 
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
 
        h.right = deleteMax(h.right);
 
        return balance(h);
    }
 
    public float min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }
 
    public float max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) return x;
        else                 return max(x.right);
    }
     private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }
    public int size() {
        return size(root);
    }
    public boolean contains(float key) {
        return get(key) != null;
    }
     public String get(float key) {
        if (0.0f == key) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }
 
    // String associated with the given key in subtree rooted at x; null if no such key
    private String get(Node x, float key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }
 
}
