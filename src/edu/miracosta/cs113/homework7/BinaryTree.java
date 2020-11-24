package edu.miracosta.cs113.homework7;
import java.io.*;
import java.util.Scanner;
/**
 * BinaryTree : Class that simulates a binary tree
 * 
 * @author Joseph Berlucchi
 * @version 1.0
 */
public class BinaryTree<E> implements Serializable{
	
	protected Node<E> root;
	/**
	 * Constructs an empty binary tree.
	 */
	public BinaryTree() {
		root = null;
	}
	
	/**
	 * Constructs a binary tree with the given node as the root.
	 * 
	 * @param root the given root node
	 */
	protected BinaryTree(Node<E> root) {
		this.root = root;
	}
	
	/**
	 * Constructs a binary tree with the given data as the root and the two given subtrees
	 * 
	 * @param data the data for the root
	 * @param leftTree
	 * @param rightTree
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<E>(data);
		if(leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		if(rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
		
	}
	
	
	/**
	 * Returns the left subtree
	 * 
	 * @return the left subtree
	 */
	public BinaryTree<E> getLeftSubtree() {
		if(root != null && root.left != null) {
			return new BinaryTree<E>(root.left);
		}
		return null;
	}
	
	/**
	 *  
	 * @return the right subtree
	 */
	public BinaryTree<E> getRightSubtree() {
		if(root != null && root.right != null) {
			return new BinaryTree<E>(root.right);
		}
		return null;
	}
	
	/**
	 *  
	 * @return the root node
	 */
	public Node<E> getRootNode() {
		return root;
	}
	
	
	/**
	 * 
	 * @return the left subtree
	 */
	public boolean isLeaf() {
		return (root.left == null && root.right == null);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	/**
	 * Performs a preorder traversal of the subtree whose root is node. 
	 * Appends the representation to the StringBuilder.
	 * Increments the value of depth(the current tree level).
	 * 
	 * @param node is the beginning node of the traversal
	 * @param depth is the depth where the node is at
	 * @param sb is a string builder object
	 */
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		for(int i = 1; i < depth; i++) {
			sb.append(" ");
		}
		if(node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString() + "\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}
	
	/**
	 * Constructs a binary tree by reading its data using Scanner scan.
	 * 
	 * @param scan the scanner object for to be read in
	 */
	public static BinaryTree<String> readBinaryTree(Scanner scan) {
		String data = scan.next();
		if(data.equals("null")) {
			return null;
		} else {
			BinaryTree<String> leftTree = readBinaryTree(scan);
			BinaryTree<String> rightTree = readBinaryTree(scan);
			return new BinaryTree<String>(data, leftTree, rightTree);
		}
		
	}
	/**
	 * @return the data of in the root
	 */
	public E getData() {
		
		return root.data;
	}
	
	/**
	 * Node : Inner class to store data and children of a tree
	 * 
	 * @author Joseph Berlucchi
	 * @version 1.0
	 */
	protected static class Node<E> implements Serializable {
		protected E data;
		protected Node<E> left;
		protected Node<E> right;
		
		public Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public Node<E> getRightNode() {
			return right;
		}
		
		public Node<E> getLeftNode() {
			return left;
		}
		
		public void setRightNode(Node<E> right) {
			this.right = right;
		}
		
		public void setLeftNode(Node<E> left) {
			this.left= left;
		}
		
		public String toString() {
			return data.toString();
		}
		
	}



}
