package edu.miracosta.cs113.homework4;
import java.util.*;

/**
 * DoubleLinkedList.java: a class responsible for holding a double linked list of gerneric node objects
 * 
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @Version 1.0
 * 
 */

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
  @Override
  	public void add(int index, E obj) {
	  	ListIterator<E> iter = listIterator(index);
	  	if(index > size || index < 0) {
	  		throw new IndexOutOfBoundsException("Invalid index " + index);
	  	}
	  	iter.add(obj);
	  
  	}
  	
  	/**
	 * adds a generic object to the front of the list
	 * 
	 * @param obj the object being added to the list
	 * 
	 */
  	public void addFirst(E obj) {
  		add(0, obj);
	  
  	}
  
  	/**
	 * adds a generic object to the end of the list
	 * 
	 * @param obj the object being added to the list
	 * 
	 */
  	public void addLast(E obj) {
  		add(size, obj);
  	}

  	@Override
  	public E get(int index) { 	
  		if(index > size - 1 || index < 0) {
  			throw new IndexOutOfBoundsException("Invalid index " + index);
  		}
  		ListIterator<E> iter = listIterator(index); 
	  
  		return iter.next();
  	}  
  	
  	/**
	 * gets the first object from the list
	 * 
	 * @return the first object in the list
	 * 
	 */
  	public E getFirst() {
  		if(head == null) {
  			return null;
  		}
  		return head.data;
  	}
  	
  	/**
	 * gets the last object from the list
	 * 
	 * @return the last object in the list
	 * 
	 */
  	public E getLast() {
  		if(tail == null) {
  			return null;
  		}
  		return tail.data;
  	}

  	@Override
  	public int size() {
  		return size;
  	} // Fill Here
 
  	@Override
  	public E remove(int index){    
  		E returnValue = null;
  		ListIterator<E> iter = listIterator(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
  	}
  	
  	@Override
  	public Iterator iterator() { 
  		return new ListIter(0);  
  		
  	}
  
  	@Override
  	public ListIterator listIterator() { 
  		return new ListIter(0);  
  	}
  
  	@Override
  	public ListIterator listIterator(int index){
  		return new ListIter(index);
  	}
  	
  	/**
	 * clones a ListIter object and returns it
	 * 
	 * @param iter the ListIter object to be cloned
	 * 
	 * @return the cloned ListIter object
	 * 
	 */
  	public ListIterator listIterator(ListIterator iter) {     
	  return new ListIter( (ListIter) iter);  
	  
  	}

  	/**
  	 * Node: a class responsible for being a node in a linked list
  	 * 
  	 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
  	 * @Version 1.0
  	 * 
  	 */
  	private static class Node<E> {     
  		private E data;
  		private Node<E> next = null;
        private Node<E> prev = null;
        
        /**
      	 * constructor for the node
      	 * 
      	 * @param dataItem the data being stored in the node
      	 * 
      	 */
        private Node(E dataItem) { //constructor
        	data = dataItem;   
        }
  	}  // end class Node
  	
  	
  	/**
  	 * ListIterator: a class responsible for traversing the double linked list in either direction, modify the list during iteration, and obtain the iterator's current position in the list.
  	 * 
  	 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
  	 * @Version 1.0
  	 * 
  	 */
  	public class ListIter implements ListIterator<E> {
	  
  		private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   // 
        
        /**
    	 * constructor for ListIter class that starts at the desired index position
    	 * 
    	 * @param i is the starting index
    	 * 
    	 */
        public ListIter(int i)  {   // constructor for ListIter class
        	if (i < 0 || i > size) {
        		throw new IndexOutOfBoundsException("Invalid index " + i); 
        	}
    	
        	lastItemReturned = null;
 
        	if (i == size) {// Special case of last item
        		index = size;    
        		nextItem = null;     
        	} else { // start at the beginning
        		nextItem = head;
        		for (index = 0; index < i; index++) {
        			nextItem = nextItem.next;   
        		}  
        	}// end else
        }  // end constructor
        
        /**
    	 * clone constructor for ListIter class that has the index and nextItem as param object
    	 * 
    	 * @param other the ListIter object being cloned
    	 * 
    	 */
        public ListIter(ListIter other) {  
        	nextItem = other.nextItem;
        	index = other.index;    
        }
    
        @Override
        public boolean hasNext() {
        	return nextItem != null;
        }
    
        @Override
        public boolean hasPrevious() {    	
        	if(previousIndex() > -1) {
        		return true;
        	}
        	
        	return false;
        }
    
        @Override
        public int previousIndex() {
        	return index - 1;
        }
    
        @Override
        public int nextIndex() {
        	return index;
        }
    
        @Override
        public void set(E o)  {
        	if(lastItemReturned == null) {
        		throw new IllegalStateException("Next nor previous has been called.");
        	}
        	if(index >= size || index < 0) {
        		throw new IndexOutOfBoundsException("Invalid index " + index);
        	}
        	lastItemReturned.data = o;
        }
    
        @Override
        public void remove(){
        	if(lastItemReturned == null) {
        		throw new IllegalStateException("Next nor previous has been called.");
        	}
        	if(lastItemReturned == head && size == 1) {
        		head = null;
        		tail = null;
        	}else if(lastItemReturned == head) {
        		lastItemReturned.next.prev = null;
        		head = lastItemReturned.next;
    		
        	} else if(lastItemReturned == tail) {
        		lastItemReturned.prev.next = null;
        		tail = lastItemReturned.prev;
        		
        	} else {
        		lastItemReturned.prev.next = lastItemReturned.next;
        		lastItemReturned.next.prev = lastItemReturned.prev;
        	}
        	size --;
        	index --;
        }
    
        @Override
        public E next()
        {  	
        	if(!hasNext()) {
        		throw new NoSuchElementException();
        	}
    	
        	lastItemReturned = nextItem;
        	nextItem = nextItem.next;
    	
        	index++;
    	
        	return lastItemReturned.data; // Fill Here 
        }
    
        @Override
        public E previous() 
        {  
        	if(!hasPrevious()) {
        		throw new NoSuchElementException();
        	}
    	
        	nextItem = nextItem == null ? tail : nextItem.prev;   	
    	
        	lastItemReturned = nextItem;
        	index--;
        	return lastItemReturned.data; // Fill Here 
        }
    
        @Override
        public void add(E obj) {
        	
        	Node<E> newObj = new Node<E>(obj);
  	  
        	if(head == null) {  	  
        		head = newObj;
        		tail = newObj;
  		  
        	} else if(nextItem == head) { 
  		  
        		newObj.next = nextItem;
        		nextItem.prev = newObj;
        		head = newObj;
         
        	} else if(nextItem == null){
  		  
        		tail.next = newObj; 
        		newObj.prev = tail;    
        		tail = newObj;
        	} else {
  		  
        		newObj.prev = nextItem.prev;
        		nextItem.prev.next = newObj; 
        		newObj.next = nextItem; 
        		nextItem.prev = newObj;
        	}
        	size++;
        	index++;
        }
    
  	}// end of inner class ListIter
}// end of class DoubleLinkedList
