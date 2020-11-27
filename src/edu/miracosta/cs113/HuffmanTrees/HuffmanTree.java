package edu.miracosta.cs113.HuffmanTrees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;


public class HuffmanTree implements HuffmanInterface {
	
	private PriorityQueue<BinaryTree<Node>> pq;
	private ArrayList<Node> frequencyTable; 
	private Hashtable<Character, String> encodeTable;
	
	public HuffmanTree(String message) {
		pq = new PriorityQueue<BinaryTree<Node>>(new NodeCompare());
		frequencyTable = new ArrayList<>();
		encodeTable = new Hashtable<>();
		
		for(char c : message.toCharArray()) {
			if(c == ' ') c = '_';

			Node newNode = new Node(c);
			if(!frequencyTable.contains(newNode)) {
				frequencyTable.add(newNode);
				continue;
			}
			for(Node n : frequencyTable) {
				if(n.equals(newNode)) {
					n.setFreq(n.getFreq() + 1);
					break;
				}
			}
			
		}
		
		for(Node n : frequencyTable) {
			pq.add(new BinaryTree<Node>(n, null, null));
		}

		loadTree();

		
		
		
		
	}
	
	private void codesToHashtable(BinaryTree<Node> tree, String s) {
		if(tree.isLeaf()) {
			encodeTable.put(tree.getData().getChar(), s);
			return;
		}
		codesToHashtable(tree.getLeftSubtree(), s + "0");
		codesToHashtable(tree.getRightSubtree(), s + "1");
	}
	
	
	private void loadTree() {
		while(pq.size() > 1) {
			BinaryTree<Node> first = pq.poll();
			BinaryTree<Node> second = pq.poll();
			
			Node newRootNode = new Node(first.getData().getFreq() + second.getData().getFreq());
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
		for(char c : message.toCharArray()) {
			if(c == ' ') c = '_';
			output += encodeTable.get(c);
		}
		return output;
	}
	
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
	
	private class NodeCompare implements Comparator<BinaryTree<Node>>{

		@Override
		public int compare(BinaryTree<Node> o1, BinaryTree<Node> o2) {
			
			return o1.getData().compareTo(o2.getData());
		}
		
	}
	
}
