// Ben Fristad

import java.io.*;
import java.util.*;

public class BST
{
   public static void main(String[] args) throws Exception
   {
      BinarySearchTree BST = new BinarySearchTree(); // initialize BinarySearchTree
      String fileName = args[0]; // take command line argument and assign it to the fileName
      Scanner keyboard = new Scanner(System.in); // initialize a Scanner for user input
      BST = fillBinarySearchTree(BST, fileName);
      char choice;
      
      do
      {
         choice = menu(keyboard);
         
         switch(choice)
         {
            case '1': // search the tree for a value given by the user
            {
               System.out.print("Input the key: ");
               int userKey = Integer.parseInt(keyboard.nextLine());
               System.out.println();
               BSTNode temp = BST.search(userKey);
               
               if(temp == null)
               {
                  System.out.println("The given key does not exist.");  
               }
               else
               {
                  System.out.println("The given key exists.");  
               }
               
               break;
               
            }// end case 1
            
            case '2': // insert a node into the tree (key is provided by user)
            {
               System.out.print("Input the key: ");
               int userKey = Integer.parseInt(keyboard.nextLine());
               System.out.println();
               BSTNode temp = BST.insert(userKey);
               
               if(temp == null)
               {  
                  System.out.println("The given already key exists.");  
               }
               else
               {
                  System.out.println("The key was inserted successfully.");    
               }

               break;
               
            }// end case 2
            
            case '3': // delete a node from the tree (key is provided by user)
            {
               System.out.print("Input the key: ");
               int userKey = Integer.parseInt(keyboard.nextLine());
               System.out.println();
               BSTNode temp = BST.delete(userKey);
               
               if(temp == null)
               {
                  System.out.println("No such a key.");
               }
               else
               {
                  System.out.println("The given key has been successfully deleted.");
               }
                   
               break;
            }
            case '4': // traverse the tree and print the contents in ascending order
            {
               BST.inOrderTraversal(BST.getRoot());
               break;
            }
            case '5': // print all the nodes of the passed in subtree top to bottom
            {         // ORDER [the root] [everything left of the root] [everything right of the root]
                      
               BST.preOrderTraversal(BST.getRoot());
               break;
            }
            case '6': // print all the nodes of the passed in subtree bottom to top
            {         // ORDER [everything left of the root] [everything right of the root] [the root]
            
               BST.postOrderTraversal(BST.getRoot());
               break;
            }
            case '7': // print all the nodes at each level of the tree
            {
               // BST.levelOrderTraversal(BST.getRoot());
               break;
            }
            case '8': // find the smallest key in the binary search tree
            {
               BSTNode temp = BST.min(BST.getRoot());
               
               if(temp == null)
               {
                  System.out.println("The tree is empty.");
               }
               else
               {
                  System.out.println("The smallest key is " + temp.key);
               }
               
               break;
               
            }// end case 8
            
            case '9': // find the largest key in the binary search tree
            {
               BSTNode temp = BST.max(BST.getRoot());
               
               if(temp == null)
               {
                  System.out.println("The tree is empty.");
               }
               else
               {
                  System.out.println("The largest key is " + temp.key);
               }

               break;
               
            }// end case 9
            
            case 'a': // find the next largest key after the passed in key
            {
               System.out.print("Input the key: ");
               int userKey = Integer.parseInt(keyboard.nextLine());
               System.out.println();
               
               BSTNode userNode = BST.search(userKey);
               
               if(userNode == null)
               {
                  System.out.println("No such a key.");
                  
               }// end if
               
               else
               {
                  BSTNode temp = BST.successor(userNode);
                  
                  if(temp == null)
                  {
                     System.out.println("The given key exists but does not have a successor");
                  }
                  else
                  {
                     System.out.println("The successor of " + userKey + " is " + temp.key);
                  }
                    
               }// end else

               break;
            }
            case 'b': // find the next smallest key after the passed in key
            {
               System.out.print("Input the key: ");
               int userKey = Integer.parseInt(keyboard.nextLine());
               System.out.println();
               
               BSTNode userNode = BST.search(userKey);
               
               if(userNode == null)
               {
                  System.out.println("No such a key.");
                  
               }// end if
               
               else
               {
                  BSTNode temp = BST.predecessor(userNode);
                  
                  if(temp == null)
                  {
                     System.out.println("The given key exists but does not have a predecessor");
                  }
                  else
                  {
                     System.out.println("The predecessor of " + userKey + " is " + temp.key);
                  }
                    
               }// end else
               
               break;
            }
            case 'x': // end program
            {
               System.out.println("program ending...");
               break;
            }
         
         }// end switch
         
      }while(choice != 'x');
   
   }// end main
   
   public static BinarySearchTree fillBinarySearchTree(BinarySearchTree BST, String fileName) throws Exception
   {
      File inputFile = openInputFile(fileName); // initalize the input file
      Scanner fileScanner = new Scanner(inputFile); // make a Scanner to read the file
      
      while(fileScanner.hasNextLine()) // reads the file until the Scanner reaches EOF
      {
         int key = Integer.parseInt(fileScanner.nextLine()); // Scan the String from the file and parse it into an int
         BST.insert(key); // insert the key into the BinarySearchTree
      
      }// end while loop   
      
      return BST;
      
   }// end fillBinarySearchTree
   
   public static File openInputFile(String fileName)
   {
      File inputFile = new File(fileName); // make File obj out of String
      
      if(!inputFile.exists()) // check if file exists
      {
         throw new RuntimeException("File does not exist");
      }
      
      return inputFile;
      
   }// end openInputFile
   
   public static char menu(Scanner keyboard) throws Exception
   {
      if(keyboard == null)
      {  
         throw new IllegalArgumentException("Scanner is null");
      }
      
      char choice;
      String strChoice;
      
      do
      {
         System.out.println("\nChoose one of the following options.");
         System.out.println("====================================");
         System.out.println("1) Search for a key");
         System.out.println("2) Insert a new key");
         System.out.println("3) Delete an existing key");
         System.out.println("4) Inorder traversal of the BST");
         System.out.println("5) Preorder traversal of the BST");
         System.out.println("6) Postorder traversal of the BST");
         System.out.println("7) Level-order traversal of the BST");
         System.out.println("8) Find the smallest key");
         System.out.println("9) Find the largest key");
         System.out.println("a) Find the successor of a given key");
         System.out.println("b) Find the predecessor of a given key");
         System.out.println("x) quit");
         System.out.print("\nYour Choice: ");
         strChoice = keyboard.nextLine();
         choice = strChoice.charAt(0);
         System.out.println();
         
      }while((strChoice.length() > 1 || choice < '1' || choice > '9') && choice != 'a' && choice != 'b' && choice != 'x');
      
      return choice;
      
   }// end menu
   
}// end class