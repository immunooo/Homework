package edu.miracosta.cs113.homework8;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
/**
 * HashTableChain : Uses an array of liked lists to create a hash map
 * 
 * @author Joseph Berlucchi
 * @version 1.0
 */
public class HashTableChain<K, V> implements Map<K, V>{
	
	private LinkedList<Entry<K, V>>[] table;
	private int numKeys;
	private static final int CAPACITY = 101;
	private static final double LOAD_THRESHOLD = 3.0;
	
	public HashTableChain() {
		table = new LinkedList[CAPACITY];
	}
	

	@Override
	public void clear() {
		table = new LinkedList[CAPACITY];
		numKeys = 0;
		
	}

	@Override
	public boolean containsKey(Object key) {
		return get(key) != null;
	}

	@Override
	public boolean containsValue(Object value) {
		for(int i = 0; i < table.length; i++) {
			if(table[i] == null) {
				continue;
			}
			for(Entry<K, V> nextItem : table[i]) {
				if(nextItem.getValue().equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return new EntrySet();
	}

	@Override
	public V get(Object key) {
		int index = key.hashCode() % table.length;
		if(index < 0) {
			index += table.length;
		}
		if(table[index] == null) {
			return null;
		}
		
		for(Entry<K, V> nextItem : table[index]) {
			if(nextItem.getKey().equals(key)) {
				return nextItem.getValue();
			}
		}
		
		return null;
	}

	@Override
	public boolean isEmpty() {
		return numKeys == 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> keySet = new HashSet<K>(size());
		for(int i = 0; i < table.length; i++) {
			if(table[i] == null) {
				continue;
			}
			for(Entry<K, V> entry : table[i]) {
				if(entry != null) {
					keySet.add(entry.getKey());
				}
			}
		}
		return keySet;
	}

	@Override
	public V put(K key, V value) {
		int index = key.hashCode() % table.length;
		if(index < 0) {
			index+= table.length;
		}
		if(table[index] == null) {
			table[index] = new LinkedList<Entry<K, V>>();
		}
		
		for(Entry<K, V> nextItem: table[index]) {
			if(nextItem.key.equals(key)) {
				V oldVal = nextItem.value;
				nextItem.setValue(value);
				return oldVal;
			}
		}
		table[index].addFirst(new Entry <K, V>(key, value));
		numKeys++;
		if(numKeys > (LOAD_THRESHOLD * table.length)) {
			rehash();
		}
		
		return null;
	}
	
	private void rehash() {
		LinkedList<Entry<K, V>>[] temp = table;
		table = new LinkedList[table.length * 2];
		numKeys = 0;
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] == null) {
				continue;
			}
			for(Entry<K, V> nextItem : temp[i]) {
				put(nextItem.getKey(), nextItem.getValue());
			}
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// Not needed to be implement
		
	}

	@Override
	public V remove(Object key) {
		int index = key.hashCode() % table.length;
		
		if(index < 0) {
			index+= table.length;
		}
		if(table[index] == null) {
			return null;
		}
		Entry<K, V> item;
		for(Entry<K, V> nextItem : table[index]) {
			if(nextItem.key.equals(key)) {
				item = nextItem;
				table[index].remove(item);
				if(table[index].isEmpty()) {
					table[index] = null;
				}
				return item.getValue();
				
			}
		}
		return null;
	}

	@Override
	public int size() {
		return numKeys;
	}

	@Override
	public Collection<V> values() {
		// Not needed to be implement
		return null;
	}
	
	@Override
	public String toString() {
		String output = "[";
		for(int i = 0; i < table.length; i ++) {
			if(table[i] == null) {
				continue;
			}
			output += "Index=" + i + " ";
			for(Entry<K, V> entry : table[i]) {
				output += "-> " + entry ; 
				
			}
			output += "\n";
		}
		return output;
	}
	
	@Override
	public int hashCode() {
		Iterator<Map.Entry<K, V>> iter = entrySet().iterator();
		int sum = 0;
		
		// "The hash code of a map is defined to be the sum of the hash codes of each entry in the map's entrySet() view"
		while(iter.hasNext()) {
			Map.Entry<K, V> temp = iter.next();
			sum += temp.getKey().hashCode() + temp.getValue().hashCode();
		}
		return sum;
		
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		Map<K, V> temp = (Map) other;
		
		for(int i = 0; i < table.length; i++) {
			if(table[i] == null) {
				continue;
			}
			for(Entry<K, V> nextItem : table[i]) {
				if(!temp.containsValue(nextItem.getValue())) {
					return false;
				}
			}
		}
		return true;

		
	}
	
	private class Entry<K, V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}
		
		@Override
		public V getValue() {
			return value;
		}
		
		@Override
		public V setValue(V val) {
			V oldVal = value;
			value = val;
			return oldVal;
		}
		
		@Override
		public String toString() {
			return key + "=" + value;
		}
	}
	
	private class SetIterator implements Iterator<Map.Entry<K, V>> {
		
		private Entry<K, V> lastItemReturned;
		private Iterator<Entry<K, V>> iterator;
		private int index;
		
		public SetIterator() {
			index = 0;
			lastItemReturned = null;
			iterator = null;
		}

		@Override
		public boolean hasNext() {
			if(iterator != null && iterator.hasNext()) {
				return true;
			}
			for(int i = index + 1; i < table.length; i++) {
				if(table[i] != null) {
					iterator = table[i].iterator();
					index = i;
					return iterator.hasNext();
				}
			}
			
			return false;
			
		}

		@Override
		public Map.Entry<K, V> next() {
			if(iterator.hasNext()) {
				lastItemReturned = iterator.next();
				return lastItemReturned;
			}
			throw new NoSuchElementException();
		}
		
		@Override
		public void remove() {
			if(lastItemReturned == null) {
				throw new IllegalStateException();
			}
			iterator.remove();
			lastItemReturned = null;
		}
		
	}
	
	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

		@Override
		public Iterator<Map.Entry<K, V>> iterator() {
			return new SetIterator();
		}

		@Override
		public int size() {
			return numKeys;
		}
		
	}

}

