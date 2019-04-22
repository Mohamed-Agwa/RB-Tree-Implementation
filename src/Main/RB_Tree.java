package Main;

import static java.lang.Math.pow;


public class RB_Tree {
    public Node root;
    public static final boolean RED   = true;
    public static final boolean BLACK = false;
    
    public boolean isRed(Node x){
        if(x==null)
            return false;
        return x.color==RED;
    }
    
    public float generateKey(String val)
    {
        float key = 0;
        val = val.toUpperCase();
        char[] arr = val.toCharArray();
        for(int i=0; i<val.length();i++)
        {
            System.out.println(arr[i]);
            key += arr[i]/pow(10,2*i);
        }
        System.out.println(key);
        return key;
    }    
    
    public void insert(String val){
        if(val==null){
            return;
        }
        float key = generateKey(val);
        root=insert(root,key,val);
        root.color=BLACK;        
    }
    
    public Node insert(Node h, float key, String val){
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
}
