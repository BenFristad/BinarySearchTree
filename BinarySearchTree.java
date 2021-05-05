// Ben Fristad

public class BinarySearchTree
{
   BSTNode root;
   
   BinarySearchTree() // default value constructor
   {
      root = null;
   
   }// end DVC
   
   public BSTNode getRoot() // returns the key of the root
   {
      return this.root;
   
   }// end getRoot
   
   
   // If the passed in key exists, return the reference of the Node that contains the key; otherwise, return null
   
   public BSTNode search(int key)
   {
      BSTNode temp = root; // create a new node and make it reference the beginning of the tree
      
      while(temp != null && key != temp.key) // run the loop until the key is found; otherwise, key was not in tree
      {
         if(key < temp.key)
         {
            temp = temp.left;
         }
         else
         {
            temp = temp.right;
         }
             
      }// end while loop
      
      return temp;
      
   }// end search

   
   // if the given key exists in the BinarySearchTree, return the reference to the Node that conatains the key;
   // otherwise, return null.
   
   public BSTNode insert(int key)
   {
      BSTNode newNode = new BSTNode(key); // create a new Node that holds the value of the key
      BSTNode curNode = root;
      BSTNode compareNode = null;
      
      while(curNode != null) // travel through the tree and find the node where the newNode will be attached
      {
         compareNode = curNode;
         
         if(key == curNode.key) // checks if key is already in tree
         {
            return null;
         }
         else if(key < curNode.key) // travel to the left child of parent
         {
            curNode = curNode.left;
         }
         else // travel to the right child of parent
         {
            curNode = curNode.right;
         }
         
      }// end while loop
      
      newNode.parent = compareNode; // establish link child -> parent
      
      if(compareNode == null) // the tree was empty
      {
         root = newNode;
      }
      else if(key < compareNode.key) // establish link from parent.left -> child
      {
         compareNode.left = newNode;
      }
      else // establish link from parent.right -> child
      {
         compareNode.right = newNode;
      }
      
      return newNode;
      
   }// end insert
      
   
   public BSTNode delete(int key)
   {
      BSTNode node = search(key); // check if the node key exists in the tree
      
      if(node != null)
      {
         delete(node);
      }
      
      return node;
      
   }// end delete
   
   
   public void delete(BSTNode node)
   {
      if(node.left == null && node.right == null) // the node doesn't have a child
      {
         transplant(node, null); // cut off the node
      }
      else if(node.left == null) // node only has a right child
      {
         transplant(node, node.right); // cut off the node and replace it with the right child
      }
      else if(node.right == null) // node only has a left child
      {
         transplant(node, node.left); // cut off the node and replace it with the left child
      }
      else // the node has two childern so the node is replaced by its successor and stray nodes are attached
      {
         BSTNode successor = min(node.right); // find the successor of the node
         
         if(successor.parent != node) // this case is for when node is successor's parent
         {
            transplant(successor, successor.right);
            successor.right = node.right;
            successor.right.parent = successor;
         }
         
      transplant(node, successor);
      successor.left = node.left;
      successor.left.parent = successor;            
         
      }// end else
      
   }// end delete helper
   
   
   public void transplant(BSTNode oldSubtree, BSTNode newSubtree)
   {
      if(oldSubtree.parent == null) // the oldSubtree is the current root
      {
         root = newSubtree; // replace the root with the newSubtree
      }
      else if(oldSubtree == oldSubtree.parent.left) // The oldSubtree is the left child of its parent
      {
         oldSubtree.parent.left = newSubtree; // establish parent -> newSubtree link
      }
      else // The oldSubtree is the right child of its parent 
      {
         oldSubtree.parent.right = newSubtree; // establish parent -> newSubtree link
      }
      
      if(newSubtree != null) // assign the pointer from the newSubtree to its position in the BST
      {
         newSubtree.parent = oldSubtree.parent;
      }
      
   }// end transplant
   
   
   // print all the nodes in ascending order
   
   public void inOrderTraversal(BSTNode userNode)
   {
      if(userNode != null)
      {
         inOrderTraversal(userNode.left);
         System.out.println(userNode.key);
         inOrderTraversal(userNode.right);
      }     
   
   }// end inOrderTraversal
   
   
   // print all the nodes of the passed in subtree top to bottom
   // ORDER [the root] [everything left of the root] [everything right of the root]
   
   public void preOrderTraversal(BSTNode userNode)
   {
      if(userNode != null)
      {
         System.out.println(userNode.key);
         preOrderTraversal(userNode.left);
         preOrderTraversal(userNode.right);
      }
   
   }// end preOrderTraversal
   
   
   // print all the nodes of the passed in subtree bottom to top
   // ORDER [everything left of the root] [everything right of the root] [the root]
   
   public void postOrderTraversal(BSTNode userNode)
   {
      if(userNode != null)
      {
         postOrderTraversal(userNode.left);
         postOrderTraversal(userNode.right);
         System.out.println(userNode.key);
      }
   
   }// end postOrderTraversal
   
   
   // print all the nodes at each level of the tree
   
   /*
   public void levelOrderTraversal(BSTNode userNode)
   {
      LinkedListQueue queue = new LinkedListQueue(); // initalize a LinkedList FIFO queue
      queue.enqueue(userNode);
      
      while(queue.size > 0)
      {
         BSTNode node = queue.dequeue();
         System.out.println(node.key);
         
         if(userNode.left != null)
         {
            queue.enqueue(userNode.left);  
         }
         if(userNode.right != null)
         {
            queue.enqueue(userNode.right);
         }
 
      }// end while loop
   
   }// end levelOrderTraversal
   */
   
   // find the smallest key in the binary search tree and return its reference
   
   public BSTNode min(BSTNode userNode)
   {
      BSTNode temp = userNode;
      
      while(temp.left != null) // run this loop which will walk to the smallest key in the list
      {
         temp = temp.left;
      }   
      
      return temp;
      
   }// end min
   
   
   // find the largest key in the binary search tree and return its reference
   
   public BSTNode max(BSTNode userNode)
   {
      BSTNode temp = userNode;
      
      while(temp.right != null) // run this loop which will walk to the largest key in the list
      {
         temp = temp.right;
      }   
      
      return temp;
      
   }// end min

   
   // find the next largest key after the passed in node's key and return that node
   
   public BSTNode successor(BSTNode userNode)
   {
      if(userNode.right != null) // checks userNode's right pointer exists, if it does then find the smallest key in that subtree
      {
         return min(userNode.right);
      }
      
      BSTNode parentNode = userNode.parent;
      
      while(parentNode != null && userNode == parentNode.right) // climb up the tree until the next largest value is found in comparsion to userNode
      {                                                         // if parentNode equals null, then the passed in node was the largest in the tree
         userNode = parentNode;
         parentNode = parentNode.parent;
      
      }// end while loop
      
      return parentNode;
      
   }// end successor
   
   
   // find the next smallest key after the passed in node's key and return that node
   
   public BSTNode predecessor(BSTNode userNode)
   {
      if(userNode.left != null) // checks userNode's left pointer exists, if it does then find the largest key in that subtree
      {
         return max(userNode.left);
      }
      
      BSTNode parentNode = userNode.parent;
      
      while(parentNode != null && userNode == parentNode.left) // climb up the tree until the next smallest value is found in comparsion to userNode
      {                                                         // if parentNode equals null, then the passed in node was the smallest in the tree
         userNode = parentNode;
         parentNode = parentNode.parent;
      
      }// end while loop
      
      return parentNode;
      
   }// end successor 
   
}// end class