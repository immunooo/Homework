package edu.miracosta.cs113.homework7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.miracosta.cs113.homework7.BinaryTree.Node;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.
	
	
	/**
	 * Constructor to create a binary tree 
	 */
	public MorseCodeTree() {
		super('0', null, null);
		try {
			readMorseCodeTree(new Scanner(new File("src/edu/miracosta/cs113/homework7/CharacterFile.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     * @throws Exception if length of morse code too big, invalid character or invalid morse code combo
     */
    public String translateFromMorseCode(String morseCode) throws Exception {
    	String[] morseCodeArr = morseCode.split(" ");
    	String output = "";
    	
    	for(int i = 0; i < morseCodeArr.length; i++) {
    		
    		char[] code = morseCodeArr[i].toCharArray();
    		BinaryTree<Character> temp = this;
        	
        	int index = 0;
        	if(morseCodeArr[i].length() > 4) {
        		throw new Exception("Length of morse code too big: " +  morseCodeArr[i].length());
        	}
        	
        	while(!temp.isLeaf() && index != morseCodeArr[i].length() ) {
        		if(code[index] != '*' && code[index] != '-') {
        			throw new Exception("Invalid Character: \"" + code[index] + "\"" );
        		}
        		if(code[index] == '*') {
        			temp =  temp.getLeftSubtree();
        		} else if(code[index] == '-') {
        			temp =  temp.getRightSubtree();
        		}
        		index++;
        		if(temp == null) {
            		throw new Exception("Invalid Morse Code");
            	}
            }
        	
        	output += temp.getData();
        	temp = this;
    	}
        return output;
    }
    
    /**
	 * Constructs a morse code binary tree by reading its data using Scanner scan
	 * 
	 * @param scan the scanner object for to be read in
	 */
    private void readMorseCodeTree(Scanner scan) {
    	
    	while(scan.hasNextLine()) {
    		String input = scan.nextLine();
    		char data = input.substring(0, 1).toCharArray()[0];
    		char[] code = input.substring(2).toCharArray();
    		
    		BinaryTree<Character> temp = this;
    		
    		for(int i = 0; i < code.length; i++) {
    			if(code[i] == '*') {
    				if(temp.root.left == null) {
    					temp.root.left = new Node<Character>(data);
    				}
    				temp =  temp.getLeftSubtree();
    			} else if(code[i] == '-') {
    				if(temp.root.right == null) {
    					temp.root.right = new Node<Character>(data);
    				}
    				temp = temp.getRightSubtree();
    			}
    		}
    		
    	}
	}
    
    private ArrayList<String> combos = new ArrayList<String>();
    public ArrayList<String> getCombos(){
    	loadCombos(this, "");
    	return combos;
    }
    
    private void loadCombos(BinaryTree<Character> tree, String combo){
    	if(tree != null && !combos.contains(tree.getData() + " - " + combo) && tree.getData() != '0') {
    		combos.add(tree.getData() + ": " + combo);
    	}
    	
    	if(tree.getLeftSubtree() != null) {
    		loadCombos(tree.getLeftSubtree(),  combo + "*" );
    	}
    	
    	if(tree.getRightSubtree() != null ) {
    		loadCombos(tree.getRightSubtree(), combo + "-");
    	}
    	
    }


} // End of class MorseCodeTree
