package eg.edu.alexu.csd.filestructure.avl;

public class Node<T extends Comparable<T>> implements INode<T> {

	
	private int height = 1;
	private Node<T> left;
	private Node<T> right;
	private T key;
	

	@Override
	public INode<T> getLeftChild() {
		return left;
	}
	


	@Override
	public INode<T> getRightChild() {

		return right;
	}

	@Override
	public T getValue() {

		return key;
	}

	@Override
	public void setValue(T value) {

		key = value;
	}
	
	public void setLeftNode(Node<T> left) {
		this.left = left;
	}
	
	public void setRightNode(Node<T> right) {

		this.right = right;
	}

	public void setHeight(int height) {

		this.height = height;
	}

	public int getHeight() {

		return this.height;
	}

}