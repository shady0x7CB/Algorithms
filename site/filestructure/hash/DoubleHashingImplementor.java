package eg.edu.alexu.csd.filestructure.hash;

import java.util.LinkedList;
import java.util.List;

public class DoubleHashingImplementor<K, V> implements IHash<K, V>, IHashDouble{


		
		
		private int size = 0;
		private int collision = 0;
		private int SIZE = 1200;
		@SuppressWarnings("unchecked")
		private Node<K, V> hashTable[] = (Node<K, V>[]) new Node[SIZE];
		private boolean deleted[] = new boolean[SIZE];
		

		
		private boolean isEmptyPlace(K key){
			int hash1 = hashFunction1(key);
			int hash2 = hashFunction2(key);
			int hash = hash1;
			int i = 0;
			while(i < SIZE) {
				hash = (hash1 + i * hash2) % SIZE;
				if(hashTable[hash] == null){
					return true;
				}
				i++;
			}
			return false;
		}
		private int hashFunction1(final K key) {
			int hashVal = key.hashCode();
			hashVal %= SIZE;
			if (hashVal < 0) {
				hashVal += SIZE;
			}
			return hashVal;
		}

		private int hashFunction2(final K key) {
			
			return 1193 - (Integer)key%1193;
		}
		private void inserToTable(K key, V value) {
			int h1 = hashFunction1(key);
			int h2 = hashFunction2(key);
			int h = h1;
			int i = 1;
			do {
				if(hashTable[h] == null){
					hashTable[h] = new Node<K, V>(key, value);
					deleted[h] = false;
					size++;
					break;
				}
				h = (h1 + i * h2) % SIZE;
				i++;
				collision++;
			}while(i <= SIZE);
		}
		
		private int getPlace(K key) {
			int h1 = hashFunction1(key);
			int h2 = hashFunction2(key);
			int h = h1;
			int i = 1;
			do {
				if ((hashTable[h] != null) || deleted[h]) {
					if ((hashTable[h] != null) && key.equals(hashTable[h].getKey())) {
						return h;
					}
					h = (h1 + i * h2) % SIZE;
					i++;
				} else
					return -1;
			}while (i <= SIZE);
			return -1;
		}
		@SuppressWarnings("unchecked")
		@Override
		public void put(K key, V value) {
			int i = getPlace(key);
			if (i != -1) {
				hashTable[i] = new Node<K, V>(key, value);
				return;
			}
			// rehashing
			if (!isEmptyPlace(key)) {
				collision = collision + SIZE + 1;
				SIZE = SIZE * 2;
				size = 0;
				Node<K, V> temp[] = hashTable;
				hashTable = (Node<K, V>[]) new Node[SIZE];
				deleted = new boolean[SIZE];
				for (int j = 0; j < temp.length; j++) {
					if(temp[j] != null){
						inserToTable(temp[j].getKey(), temp[j].getValue());
					}
				}
			}
			inserToTable(key, value);
		}

		
		
		

		@Override
		public String get(K key) {
			int i = getPlace(key);
			if (i != -1) {
				return hashTable[i].getValue().toString();
			}
			return null;
		}

		@Override
		public void delete(K key) {
			int i = getPlace(key);
			if (i != -1) {
				hashTable[i] = null;
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
