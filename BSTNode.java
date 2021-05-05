// Ben Fristad

public class BSTNode
{
   public int key; // the key saved in the Node
   public BSTNode parent; // the reference to the left child Node if it exists; null, otherwise
   public BSTNode left; // the reference to the right child Node if it exists; null, otherwise
   public BSTNode right; // the reference to the parent Node if it exists; null, otherwise
   
   public BSTNode(int key) // explicit value constructor
   {
      this.key = key;
      parent = null;
      left = null;
      right = null;
   
   }// end EVC
   
}// end class