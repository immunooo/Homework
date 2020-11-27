package edu.miracosta.cs113.HuffmanTrees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

/**
 * Huffman : Class that creates a huffman tree from a priority queue with given string
 * 
 * @author Joseph Berlucchi
 * @version 1.0
 */
public class HuffmanTree implements HuffmanInterface {
	
	private PriorityQueue<BinaryTree<Node>> pq;
	private ArrayList<Node> frequencyTable; 
	private Hashtable<Character, String> encodeTable;
	
	/**
	 * constuctor to create a Huffman tree from elements from the string argument
	 * 
	 * @param message the string object to be made a huffman tree
	 */
	public HuffmanTree(String message) {
		pq = new PriorityQueue<BinaryTree<Node>>(new NodeCompare());
		frequencyTable = new ArrayList<>();
		encodeTable = new Hashtable<>();
		
		//Traverses the message as a character objects
		for(char c : message.toCharArray()) {
			
			if(c == ' ') c = '_';
			
			
			Node newNode = new Node(c);
			
			//If frequency table doesn't have the the element insert at the end
			if(!frequencyTable.contains(newNode)) {
				frequencyTable.add(newNode);
				continue;
			}
			
			//Traverses the table and updates the frequency
			for(Node n : frequencyTable) {
				if(n.equals(newNode)) {
					n.setFreq(n.getFreq() + 1);
					break;
				}
			}
			
		}
		
		//adds all the nodes as sub binary trees into the priority queue
		for(Node n : frequencyTable) {
			pq.add(new BinaryTree<Node>(n, null, null));
		}
		
		loadTree();

		
		
		
		
	}
	
	/**
	 * Inserts every code from a tree into a hash table
	 * 
	 * @param tree
	 * @param s
	 */
	private void codesToHashtable(BinaryTree<Node> tree, String s) {
		//If tree is a leaf insert key and vaue into the hash table
		if(tree.isLeaf()) {
			encodeTable.put(tree.getData().getChar(), s);
			return;
		}
		//recursive case to traverse the tree
		codesToHashtable(tree.getLeftSubtree(), s + "0");
		codesToHashtable(tree.getRightSubtree(), s + "1");
	}
	
	/**loads the into a priority queue*/
	private void loadTree() {
		
		while(pq.size() > 1) {
			//Polls the first two from prio queue
			BinaryTree<Node> first = pq.poll();
			BinaryTree<Node> second = pq.poll();
			
			//Creates the parent node of these queues
			Node newRootNode = new Node(first.getData().getFreq() + second.getData().getFreq());
			//Adds a new tree to prio queue
			pq.add(new BinaryTree<Node>(newRootNode, first, second));
			
		}
		
		codesToHashtable(pq.peek(), "");
		
	}
	
	
	@Override
	public String decode(String codedMessage) {
		BinaryTree<Node> rootTree = pq.peek();
		String output = "";
		BinaryTree<Node> subTree = rootTree;
		for(char c: codedMessage.toCharArray()) {
			if(subTree.isLeaf()) {
				if(subTree.getData().getChar() == '_') {
					output += ' ';
				} else {
					output += subTree.getData().getChar();
				}
				subTree = rootTree;
			}
			if(c == '1') {
				subTree = subTree.getRightSubtree();
			}
			if(c == '0') {
				subTree = subTree.getLeftSubtree();
			}
			
		}
		
		output += subTree.getData().getChar();
		
		return output;
	}

	@Override
	public String encode(String message) {
		String output = "";
		
		//For each character get the character value from the and append to the string
		for(char c : message.toCharArray()) {
			if(c == ' ') c = '_';
			output += encodeTable.get(c);
		}
		return output;
	}
	
	/**
	 * Node : Class that stores the frequency of a character
	 * 
	 * @author Joseph Berlucchi
	 * @version 1.0
	 */
	private class Node implements Comparable<Node>{
		private char c;
		private int freq;
		
		public Node(char c){
			this.c = c;
			this.freq = 1;
		}
		
		public Node(int freq){
			this.c = '-';
			this.freq = freq;
		}
		
		public char getChar() {
			return c;
		}
		
		public int getFreq() {
			return freq;
		}
		
		public void setChar(char c) {
			this.c = c;
		}
		
		public void setFreq(int freq) {
			this.freq = freq;
		}
		
		/**Equality based on character*/
		@Override
		public boolean equals(Object o) {
			if(o == null) return false;
			if(o.getClass() != this.getClass()) return false;
			Node other = (Node) o;
			return other.getChar() == getChar();			
		}
		
		@Override
		public String toString() {
			return c + ":" + freq;
		}
		
		/**Compares based on frequency*/
		@Override
		public int compareTo(Node o) {
			if(o == null) {
				throw new NullPointerException();
			}
			if(o.getFreq() < getFreq()) {
				return 1;
			}
			if(o.getFreq() > getFreq()) {
				return -1;
			}
			return 0;
		}
	}
	
	/**
	 * NodeCompare : Comparator object to compare root data from two bonary trees
	 * 
	 * @author Joseph Berlucchi
	 * @version 1.0
	 */
	private class NodeCompare implements Comparator<BinaryTree<Node>>{
		
		/**Compares root data*/
		@Override
		public int compare(BinaryTree<Node> o1, BinaryTree<Node> o2) {
			
			return o1.getData().compareTo(o2.getData());
		}
		
	}
	
}
