package eg.edu.alexu.csd.filestructure.hash;

import java.util.LinkedList;
import java.util.List;

public class QuadraticProbingImplementor<K, V> implements IHash<K, V>, IHashQuadraticProbing{
	
	
	private int size = 0;
	private int collision = 0;
	private int SIZE = 1200;
	@SuppressWarnings("unchecked")
	private Node<K, V> table[] = (Node<K, V>[]) new Node[SIZE];
	private boolean deleted[] = new boolean[SIZE];
	


	private void insertToTable(K key, V value) {
		boolean flag = false;
		int h1 = hashFunction(key);
		int h = h1;
		int i = 1;
		do {
			if(table[h] == null){
				table[h] = new Node<K, V>(key, value);
				deleted[h] = false;
				size++;
				break;
			}
			h = (h1 + i * i) % SIZE;
			i++;
			collision++;
			flag = true;
		}while(h != h1);
		if (flag)
			collision++;
	}

	
	private int getPlace(K key) {
		int h1 = hashFunction(key);
		int h = h1;
		int i = 1;
		do {
			if ((table[h] != null) || deleted[h]) {
				if ((table[h] != null) && key.equals(table[h].getKey())) {
					return h;
				}
				h = (h1 + i * i) % SIZE;
				i++;
			} else
				return -1;
		}while (h != h1);
		return -1;
	}
	
	private int hashFunction(final K key) {
		int hashVal = key.hashCode();
		hashVal %= SIZE;
		if (hashVal < 0) {
			hashVal += SIZE;
		}
		return hashVal;
	}
	private boolean isEmptyPlace(K key){
		int h1 = hashFunction(key);
		int h = h1;
		int i = 1;
		do {
			if(table[h] == null){
				return true;
			}
			h = (h1 + i * i) % SIZE;
			i++;
		}while(h != h1);
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void put(K key, V value) {
		int i = getPlace(key);
		if (i != -1) {
			table[i] = new Node<K, V>(key, value);
			return;
		}
		// rehashing
		if (!isEmptyPlace(key)) {
			collision = collision + SIZE + 1;
			SIZE = SIZE * 2;
			size = 0;
			Node<K, V> oldTable[] = table;
			table = (Node<K, V>[]) new Node[SIZE];
			deleted = new boolean[SIZE];
			for (int j = 0; j < oldTable.length; j++) {
				if(oldTable[j] != null){
					insertToTable(oldTable[j].getKey(), oldTable[j].getValue());
				}
			}
		}
		insertToTable(key, value);
	}

	
	
	@Override
	public String get(K key) {
		int i = getPlace(key);
		if (i != -1) {
			return table[i].getValue().toString();
		}
		return null;
	}

	@Override
	public void delete(K key) {
		int i = getPlace(key);
		if (i != -1) {
			table[i] = null;
			deleted[i] = true;
			size--;
		}
	}



	@Override
	public boolean contains(K key) {
		if (getPlace(key) == -1)
			return false;
		return true;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
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
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				list.add(table[i].getKey());
			}
		}
		return list;
	}

	


}
