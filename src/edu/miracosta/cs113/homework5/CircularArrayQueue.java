package edu.miracosta.cs113.homework5;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
/**
 * CircularArrayQueue : a class responsible for being a queue ADT using a circular array to hold data.
 *
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @version 1.0
 */
public class CircularArrayQueue<E> implements Queue<E>{
	
	private Object[] queueArr;
	private int capacity;
	private int size;
	private int front;
	private int rear;
	
	 /**
     * Initializes Queue of an array with a capacity of 1.
     */
	public CircularArrayQueue() {
		this.capacity = 1;
		queueArr = new Object[capacity];
		size = 0;
		front = 0;
		rear = capacity - 1;
	}
	
	/**
     * Initializes Queue of an array with a capacity of the parameter
     * @param capacity number of the initial capacity; can't be less than one.
     */
	public CircularArrayQueue(int capacity) {
		if(capacity < 1) {
			capacity = 1;
		}
		this.capacity = capacity;
		
		queueArr = new Object[capacity];
		size = 0;
		front = 0;
		rear = capacity - 1;
	}
	
	 /**
     * Doubles the capacity of the array.
     */
	private void reallocate() {
			Object[] temp = new Object[capacity * 2];
			
			for(int i = 0; i < size; i++) {
				temp[i] = queueArr[(front + i) % size];
			}
			
			queueArr = temp;
			front = 0;
			rear = size - 1;
			capacity = capacity * 2;
			
	}
	
	@Override
	public boolean add(E e) {
		//Throws exception if cant add more
		if(size == capacity) {
			throw new IllegalStateException();
		}
		
		return offer(e);
	}

	@Override
	public E element() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return (E) queueArr[front];
	}

	@Override
	public boolean offer(E e) {
		//Reallocates of element cannot be inserted
		if(size == capacity) {
			reallocate();
		}
		//Increase the size 
		size++;
		//Increment the rear
		rear = (rear + 1) % capacity;
		queueArr[rear] = e;
		
		return true;
	}

	@Override
	public E peek() {
		try {
			return element();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public E poll() {
		//Tries to return the removed element
		try {
			return remove();
		} catch(NoSuchElementException e) {
			//if it no element then return null
			return null;
		}
	}

	@Override
	public E remove() {
		try {
			return element();
		} catch(NoSuchElementException e) {
			//Kind of weird doing this but easier implementation
			throw new NoSuchElementException();
		} finally {
			front++;
			size--;
		}
	}
	
	//Not needed to be implemented below
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		capacity = 1;
		queueArr = new Object[capacity];
		size = 0;
		front = 0;
		rear = capacity - 1;
		
	}
	@Override
	public boolean contains(Object o) {
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}
	@Override
	public boolean isEmpty() {
		return false;
	}
	@Override
	public Iterator<E> iterator() {
		return null;
	}
	@Override
	public boolean remove(Object o) {
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public Object[] toArray() {
		return null;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}
	
}
