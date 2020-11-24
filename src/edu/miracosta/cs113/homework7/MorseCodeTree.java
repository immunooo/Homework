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
	 * Constructor to initialize a binary tree that has elements for moorse code 
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
    	
    	//Splits the sequence of morse code into arrays to be processed
    	String[] morseCodeArr = morseCode.split(" ");
    	//Example: "* -* **" => ["*", "-*", "**"]
    	
    	String output = "";
    	
    	//Traverses the array to process each morse code
    	for(int i = 0; i < morseCodeArr.length; i++) {
    		
    		//Converts the nth String into a character array to process
    		char[] code = morseCodeArr[i].toCharArray();
    		
    		//Sets the temp tree to the origional root
    		BinaryTree<Character> temp = this;
        	
        	int index = 0;
        	
        	//Error handeling, Length cannot be greater than 4
        	if(morseCodeArr[i].length() > 4) {
        		throw new Exception("Length of morse code too big: " +  morseCodeArr[i].length());
        	}
        	
        	
        	while(!temp.isLeaf() && index != morseCodeArr[i].length() ) {
        		//Checks for invalid characters
        		if(code[index] != '*' && code[index] != '-') {
        			throw new Exception("Invalid Character: \"" + code[index] + "\"" );
        		}
        		
        		//Traverses the tree part
        		if(code[index] == '*') {
        			temp =  temp.getLeftSubtree();
        		} else if(code[index] == '-') {
        			temp =  temp.getRightSubtree();
        		}
        		
        		index++;
        		
        		//If Handels errors
        		if(temp == null) {
            		throw new Exception("Invalid Morse Code");
            	}
            }
        	
        	//Adds the data to the output
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
    		
    		//Converts the gets the first character of the line
    		char data = input.substring(0, 1).toCharArray()[0];
    		
    		//Converts the sequence of * and - into an character array
    		char[] code = input.substring(2).toCharArray();
    		
    		BinaryTree<Character> temp = this;
    		
    		//Traversed the array of * and - 
    		for(int i = 0; i < code.length; i++) {
    			
    			//goes left in the tree
    			if(code[i] == '*') {
    				//Sets the node if the left child is null
    				if(temp.getRootNode().getLeftNode() == null) {
    					temp.getRootNode().setLeftNode(new Node<Character>(data));
    				}
    				
    				
    				temp =  temp.getLeftSubtree();
    				
    			//Goes right in the tree
    			} else if(code[i] == '-') {
    				// Sets the node if the right child is null
    				
    				if(temp.getRootNode().getRightNode() == null) {
    					temp.getRootNode().setRightNode(new Node<Character>(data));
    				}
    				temp = temp.getRightSubtree();
    			}
    		}
    		
    	}
	}
    
    /**
     * Wrapper method for the load combos method that returns a arraylist of all combos
     * 
     * @return a arraylist of all combos
     */
    public ArrayList<String> getCombos(){
    	ArrayList<String> combos = new ArrayList<>();
    	loadCombos( combos, this, "");
    	return combos;
    }
    
    /**
	 * Performs a preorder traversal of the subtree whose root is node. 
	 * Appends the representation to the String.
	 * 
	 * @param combos the array list that the combos get loaded into
	 * @param tree is the binary tree that gets traversed
	 * @param combo the string object representation of the morse code
	 */
    private void loadCombos(ArrayList<String> combos, BinaryTree<Character> tree, String combo){
    	
    	if(tree != null && !combos.contains(tree.getData() + " - " + combo) && tree.getData() != '0') {
    		combos.add(tree.getData() + ": " + combo);
    	}
    	
    	if(tree.getLeftSubtree() != null) {
    		loadCombos(combos, tree.getLeftSubtree(),  combo + "*" );
    	}
    	
    	if(tree.getRightSubtree() != null ) {
    		loadCombos(combos, tree.getRightSubtree(), combo + "-");
    	}
    	
    }


} // End of class MorseCodeTree
