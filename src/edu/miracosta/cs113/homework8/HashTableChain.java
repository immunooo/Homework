package edu.miracosta.cs113.homework8;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

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
		// TODO Auto-generated method stub
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
		return new EntrySet();
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
	
	private class Entry<K, V> {
		private K key;
		private V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public V setValue(V val) {
			V oldVal = value;
			value = val;
			return oldVal;
		}
	}
	
	private class SetIterator implements Iterator {
		
		private V nextValue;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

		@Override
		public Iterator<java.util.Map.Entry<K, V>> iterator() {
			return new SetIterator();
		}

		@Override
		public int size() {
			return numKeys;
		}
		
	}

}
