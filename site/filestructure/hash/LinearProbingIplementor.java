package eg.edu.alexu.csd.filestructure.hash;

import java.util.LinkedList;
import java.util.List;

public class LinearProbingIplementor<K, V> implements IHashLinearProbing, IHash<K, V>{

	
	
	private int size = 0;
	private int collision = 0;
	private int SIZE = 1200;
	@SuppressWarnings("unchecked")
	private Node<K, V> hashTable[] = (Node<K, V>[]) new Node[SIZE];
	private boolean deleted[] = new boolean[SIZE];

	
	private void insertToTable(K key, V value) {
		boolean flag = false;
		int h = hashFunction(key);
		while (hashTable[h] != null) {
			collision++;
			h = (h + 1) % SIZE;
			flag = true;
		}
		if(flag) collision++;
		hashTable[h] = new Node<K, V>(key, value);
		deleted[h] = false;
		size++;
	}
	private int hashFunction(final K key) {
		int hashVal = key.hashCode();
		hashVal %= SIZE;
		if (hashVal < 0) {
			hashVal += SIZE;
		}
		return hashVal;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void put(K key, V value) {
		int i = getIndex(key);
		if (i != -1) {
			hashTable[i] = new Node<K, V>(key, value);
			return;
		}
		// rehashing
		if (size == SIZE) {
			collision = collision + SIZE+1;
			SIZE = SIZE * 2;
			size = 0;
			Node<K, V> temp[] = hashTable;
			hashTable = (Node<K, V>[]) new Node[SIZE];
			deleted = new boolean[SIZE];
			for (int j = 0; j < temp.length; j++) {
				insertToTable(temp[j].getKey(), temp[j].getValue());
			}
		}
		insertToTable(key, value);
	}

	

	@Override
	public String get(K key) {
		int i = getIndex(key);
		if (i != -1) {
			return hashTable[i].getValue().toString();
		}
		return null;
	}

	@Override
	public void delete(K key) {
		int i = getIndex(key);
		if (i != -1) {
			hashTable[i] = null;
			deleted[i] = true;
			size--;
		}
	}

	private int getIndex(K key) {
		int h = hashFunction(key);
		for(int i=0; i<SIZE; i++){
			if ((hashTable[h] != null) || deleted[h]) {
				if ((hashTable[h] != null) && key.equals(hashTable[h].getKey())) {
					return h;
				}
				h = (h + 1) % SIZE;
			}
			else break;
		}
		return -1;
	}

	@Override
	public boolean contains(K key) {
		if (getIndex(key) == -1)
			return false;
		return true;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return this.SIZE;
	}

	@Override
	public int collisions() {
		// TODO Auto-generated method stub
		return this.collision;
	}

	@Override
	public Iterable<K> keys() {
		List<K> list = new LinkedList<K>();
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				list.add(hashTable[i].getKey());
			}
		}
		return list;
	}

	
}

