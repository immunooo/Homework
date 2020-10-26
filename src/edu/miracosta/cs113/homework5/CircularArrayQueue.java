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
		if(size == capacity) {
			Object[] temp = new Object[capacity * 2];
			
			for(int i = 0; i < size; i++) {
				temp[i] = queueArr[(front + i) % size];
			}
			
			queueArr = temp;
			front = 0;
			rear = size - 1;
			capacity = capacity * 2;
			
		}
	}
	
	@Override
	public boolean add(E e) {
		// here
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
		
		if(size == capacity) {
			reallocate();
		}
		size++;
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
		try {
			return remove();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public E remove() {
		try {
			return element();
		} catch(NoSuchElementException e) {
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
