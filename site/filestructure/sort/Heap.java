package eg.edu.alexu.csd.filestructure.sort;

import java.util.Arrays;
import java.util.Collection;

public class Heap<T extends Comparable<T>> implements IHeap<T> {

	private Node<T>[] heap = null;
	private int size = 0;

	@Override
	public INode<T> getRoot() {

		if ((heap == null) || (this.size == 0))
			throw new RuntimeException();
		return heap[0];
	}

	@Override
	public int size() {

		return this.size;
	}


	
	public void swap(INode<T> node1, INode<T> node2) {

		T temp = node1.getValue();
		node1.setValue(node2.getValue());
		node2.setValue(temp);
	}

	@Override
	public void heapify(INode<T> node) {

		int largest;
		Node<T> left = (Node<T>) node.getLeftChild();
		Node<T> right = (Node<T>) node.getRightChild();
		if (left == null)
			return;
		if ((left.getValue().compareTo(node.getValue()) > 0)) {
			largest = left.index;
		} else
			largest = ((Node<T>) node).index;
		if ((right != null)
				&& (right.getValue().compareTo(heap[largest].getValue()) > 0)) {
			largest = right.index;
		}
		if (largest != ((Node<T>) node).index) {
			swap(node, heap[largest]);
			heapify(heap[largest]);
		}
	}



	@Override
	public T extract() {

		if ((heap == null) || (this.size < 1))
			return null;
		T max = heap[0].getValue();
		heap[0].setValue(heap[this.size - 1].getValue());
		this.size--;
		heapify(heap[0]);
		return max;
	}

	@Override
	public void insert(T element) {

		this.size++;
		if (heap == null) {
			heap = (new Node[this.size]);
			heap[this.size - 1] = new Node<T>();
		}
		if (this.size > heap.length) {
			heap = Arrays.copyOf(heap, this.size);
			heap[this.size - 1] = new Node<T>();
		}
		heap[this.size - 1].setValue(element);
		heap[this.size - 1].index = this.size - 1;
		for (int i = this.size - 1; i > 0; i = (i - 1) / 2) {
			if (heap[i].getValue().compareTo(heap[i].getParent().getValue()) > 0) {
				swap(heap[i], heap[i].getParent());
			} else
				break;
		}
	}

	public void updateSize(int size) {

		this.size = size;
	}

	public INode<T> getNodeByIndex(int index) {

		return heap[index];
	}
	
	@Override
	public void build(Collection<T> unordered) {

		this.size = unordered.size();
		heap = new Node[this.size];
		int ind = 0;
		for (T value : unordered) {
			heap[ind] = new Node<T>();
			heap[ind].setValue(value);
			heap[ind].index = ind;
			ind++;
		}
		for (int i = (this.size / 2) - 1; i >= 0; i--) {
			heapify(heap[i]);
		}
		
		
	}

	private class Node<T extends Comparable<T>> implements INode<T> {

		private T value;
		private int index;

		@Override
		public INode<T> getLeftChild() {

			if ((index * 2 + 1) >= Heap.this.size) {
				return null;
			}
			return  (INode<T>) heap[index * 2 + 1];
		}

		@Override
		public INode<T> getRightChild() {

			if ((index * 2 + 2) >= Heap.this.size) {
				return null;
			}
			return (INode<T>) heap[index * 2 + 2];
		}

		@Override
		public INode<T> getParent() {

			return (INode<T>) heap[(index - 1) / 2];
		}

		@Override
		public T getValue() {

			return this.value;
		}

		@Override
		public void setValue(T value) {

			this.value = value;
		}

	}
}