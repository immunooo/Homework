package edu.miracosta.cs113.homework5;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Vector;
/**
 * ArrayListStack : a class responsible for being a stack ADT using a liked list to hold data.
 *
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @version 1.0
 */
public class ArrayListStack<E> implements StackInterface<E>{
	
	private ArrayList<E> stackList;
	
	/**
     * Initializes empty stack
     */
	public ArrayListStack(){
		super();
		stackList = new ArrayList<E>();
	}
	
		
	@Override
	public boolean empty() {
		return stackList.size() == 0;
	}

	@Override
	public E peek() {
		if(empty()) {
			throw new EmptyStackException();
		}
		return stackList.get(0);
	}

	@Override
	public E pop() {
		E obj = peek();
		stackList.remove(0);
		
		return obj;
	}

	@Override
	public E push(E item) {
		stackList.add(0, item);
		
		return item;
	}
	
	@Override
	public boolean equals(Object other) {
		
		ArrayListStack<E> otherStack = (ArrayListStack<E>) other;
		
		//Base case for the equals 
	    if (empty() && otherStack.empty()) {
	    	return true; 
	    }
	    
	    //Removes one element from both stacks
	    E elementA = pop(); 
	    E elementB = otherStack.pop(); 
	    
	    //Iteratesthrough all the elements in 
	    try {
	    	
	    	//returns false if stack elements are not equal
	    	if(!elementA.equals(elementB)) {
	    		return false;
	    	}
	    	
	    	//Recursive case
	        return equals(otherStack); 
	        
	    } finally { 
	    	// restore elements into the stacks
	        push(elementA); 
	        otherStack.push(elementB);
	    }
		
	}
	

	
}
