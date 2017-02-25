package eg.edu.alexu.csd.filestructure.hash;


import java.util.LinkedList;
import java.util.List;

public class ChainingImplementor<K, V> implements IHashChaining, IHash<K, V>{


	private static int SIZE = 1200;
	@SuppressWarnings("unchecked")
	private LinkedList<Node<K, V>> table[] = (LinkedList<Node<K, V>>[]) new LinkedList[SIZE];
	private int size = 0;
	private int collision = 0;

	

	private int hashFunction(final K key) {
		int hashVal = key.hashCode();
		hashVal %= SIZE;
		if (hashVal < 0) {
			hashVal += SIZE;
		}
		return hashVal;
	}
	@Override
	public void put(K key, V value) {
		int h = hashFunction(key);
		int i = getIndex(key);
		if (i != -1) {
			table[h].set(i, new Node<K, V>(key, value));
			return; 
		}
		if (table[h] == null)
			table[h] = new LinkedList<Node<K, V>>();
		collision += table[h].size();
		table[h].add(new Node<K, V>(key, value));
		size++;
	}

	@Override
	public String get(K key) {
		int h = hashFunction(key);
		int i = getIndex(key);
		if (i != -1) {
			return table[h].get(i).getValue().toString();
		}
		return null;
	}

	@Override
	public void delete(K key) {
		int h = hashFunction(key);
		int i = getIndex(key);
		if (i != -1) {
			table[h].remove(i);
			size--;
			if(table[h].isEmpty()){
				table[h] = null;
			}
		}
	}
	
	private int getIndex(K key){
		int h = hashFunction(key);
		if (table[h] == null)
			return -1;
		for(Node<K, V> p : table[h]){
			if(key.equals(p.getKey())){
				return table[h].indexOf(p);
			}
		}
		return -1;
	}
	
	@Override
	public boolean contains(K key) {
		if(getIndex(key) == -1) return false;
		return true;
	}

	@Override
	public boolean isEmpty() {
		for (List<Node<K, V>> ob : table) {
			if (ob != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int capacity() {
		return SIZE;
	}

	@Override
	public int collisions() {
		return this.collision;
	}

	@Override
	public Iterable<K> keys() {
		List<K> list = new LinkedList<K>();
		for(int i=0; i<table.length; i++){
			if(table[i] != null){
				for(Node<K, V> p : table[i]){
					list.add(p.getKey());
				}
			}
		}
		return list;
	}

	
}
