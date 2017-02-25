package eg.edu.alexu.csd.filestructure.hash;

public class Node<K, V> {

	private K key;
	private V value;
	public Node (K key, V value){
		this.key = key;
		this.value = value;
	}
	public K getKey(){
		return this.key;
	}
	public V getValue(){
		return this.value;
	}
	
}
