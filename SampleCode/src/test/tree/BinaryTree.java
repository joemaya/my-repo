package test.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

	List<Integer> list = new ArrayList<Integer>();
	public Node root;
	public class Node {
	    int data;
	    Node left, right;
	  
	    Node(int item) 
	    {
	        data = item;
	        left = right = null;
	    }
	}
	
	public class TreeNode {
		    int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
	}
	
	public List<Integer> inorderTraversal(TreeNode root) {
		if(root!=null)
			{if(root.left != null) {
				inorderTraversal(root.left);
			}
			if(root != null) 
				list.add(root.val);
			
			if(root.right != null) {
				inorderTraversal(root.right);
			}
		}
		return list;
    }
	
	public List < Integer > inorderTraversal1(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
	
	
	public TreeNode createBST(String arr[]) {
		if(arr != null && arr.length > 0) {
			for(int i=0;i<arr.length;i++) {
//				TreeNode node
			}
		}
		return null;
	}
	
	 int maxDepth(Node node) 
	    {
	        if (node == null)
	            return 0;
	        else
	        {
	            /* compute the depth of each subtree */
	            int lDepth = maxDepth(node.left);
	            int rDepth = maxDepth(node.right);
	  
	            /* use the larger one */
	            if (lDepth > rDepth)
	                return (lDepth + 1);
	             else
	                return (rDepth + 1);
	        }
	    }
	 
	 
	 public int size(Node root) {
		 if(root == null) {
			 return 0;
		 } else {
			 int size = size(root.left) + size(root.right) +1;
			 return size;
		 }
	 }
	      
	 
	 public void printPaths() {
		  int[] path = new int[1000];
		  printPaths(root, path, 0);
		}
		/**
		 Recursive printPaths helper -- given a node, and an array containing
		 the path from the root node up to but not including this node,
		 prints out all the root-leaf paths.
		*/
		private void printPaths(Node node, int[] path, int pathLen) {
		  if (node==null) return;
		  // append this node to the path array
		  path[pathLen] = node.data;
		  pathLen++;
		  // it's a leaf, so print the path that led to here
		  if (node.left==null && node.right==null) {
		    printArray(path, pathLen);
		  }
		  else {
		  // otherwise try both subtrees
		    printPaths(node.left, path, pathLen);
		    printPaths(node.right, path, pathLen);
		  }
		}
		/**
		 Utility that prints ints from an array on one line.
		*/
		private void printArray(int[] ints, int len) {
		  int i;
		  for (i=0; i<len; i++) {
		   System.out.print(ints[i] + " ");
		  }
		  System.out.println();
		}
		
		public void doubleTree(Node n) {
			if(n == null) return;
			
			doubleTree(n.left);
			doubleTree(n.right);
			
			Node nNode = new Node(n.data);
			Node temp = n.left;
			n.left = nNode;
			nNode.left = temp;
			
			
			
		}
	    /* Driver program to test above functions */
	    public static void main(String[] args)  {
	        BinaryTree tree = new BinaryTree();
	  
	        tree.root = tree.new Node(1);
	        tree.root.left = tree.new Node(2);
	        tree.root.right = tree.new Node(3);
	        tree.root.left.left = tree.new Node(4);
	        tree.root.left.right = tree.new Node(5);
	  
//	        System.out.println("Height of tree is : " + tree.maxDepth(tree.root));
//	        System.out.println("Size of tree is : " + tree.size(tree.root));
//	        tree.printPaths();

	        tree.doubleTree(tree.root);
	        tree.printPaths();
	    }	
}
