package eg.edu.alexu.csd.filestructure.avl;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

	private Node<T> root = null;
	
	private boolean isDeleted = false;

	
	private Node<T> insert(Node<T> node, T key) {

		if (node == null) {
			Node<T> newNode = new Node<T>();
			newNode.setValue(key);
			return newNode;
		}
		if (key.compareTo(node.getValue()) < 0) {
			node.setLeftNode(insert((Node<T>) node.getLeftChild(), key));
		} else {
			node.setRightNode(insert((Node<T>) node.getRightChild(), key));
		}
		fixHeight(node);
		return balance(node);
	}
	
	
	@Override
	public void insert(T key) {
		root = insert(root, key);
	}

	
	private Node<T> rotateLeft(Node<T> nodeToRotate) {

		Node<T> temp1 = (Node<T>) nodeToRotate.getRightChild();
		Node<T> temp2 = (Node<T>) temp1.getLeftChild();
		temp1.setLeftNode(nodeToRotate);
		nodeToRotate.setRightNode(temp2);
		fixHeight(nodeToRotate);
		fixHeight(temp1);
		return temp1;
	}

	private Node<T> rotateRight(Node<T> nodeToRotate) {

		Node<T> temp1 = (Node<T>) nodeToRotate.getLeftChild();
		Node<T> temp2 = (Node<T>) temp1.getRightChild();
		temp1.setRightNode(nodeToRotate);
		nodeToRotate.setLeftNode(temp2);
		fixHeight(nodeToRotate);
		fixHeight(temp1);
		return temp1;
	}

	private Node<T> balance(Node<T> node) {
		int balance = getHeight(node);
		if (balance > 1 && getHeight((Node<T>) node.getLeftChild()) >= 0) {
			return rotateRight(node);
		}
		if (balance < -1 && getHeight((Node<T>) node.getRightChild()) <= 0) {
			return rotateLeft(node);
		}
		if (balance > 1 && getHeight((Node<T>) node.getLeftChild()) < 0) {
			node.setLeftNode(rotateLeft((Node<T>) node.getLeftChild()));
			return rotateRight(node);
		}
		if (balance < -1 && getHeight((Node<T>) node.getRightChild()) > 0) {
			node.setRightNode(rotateRight((Node<T>) node.getRightChild()));
			return rotateLeft(node);
		}
		return node;
	}

	private int getHeight(Node<T> node) {
		if (node == null) {
			return 0;
		}
		int val1 = 0, val2 = 0;
		if (node.getLeftChild() != null) {
			val1 = ((Node<T>) node.getLeftChild()).getHeight();
		}
		if (node.getRightChild() != null) {
			val2 = ((Node<T>) node.getRightChild()).getHeight();
		}
		return val1 - val2;
	}

	private Node<T> delete(Node<T> node, T key) {

		if (node == null) {
			return node;
		}
		if (key.compareTo(node.getValue()) < 0) {
			node.setLeftNode(delete((Node<T>) node.getLeftChild(), key));
		} else if (key.compareTo(node.getValue()) > 0) {
			node.setRightNode(delete((Node<T>) node.getRightChild(), key));
		} else {
			this.isDeleted = true;
			if (node.getLeftChild() == null || node.getRightChild() == null) {
				Node<T> temp = null;
				if (temp == node.getLeftChild()) {
					temp = (Node<T>) node.getRightChild();
				} else {
					temp = (Node<T>) node.getLeftChild();
				}
				if (temp == null) {
					node = null;
				} else {
					node = temp;
				}
			} else {
				Node<T> temp = minimumNode((Node<T>) node.getRightChild());
				node.setValue(temp.getValue());
				node.setRightNode(delete((Node<T>) node.getRightChild(),
						temp.getValue()));
			}
		}
		if (node == null) {
			return node;
		}
		fixHeight(node);
		return balance(node);
	}
	
	@Override
	public boolean delete(T key) {

		root = delete(root, key);
		boolean deleted = this.isDeleted;
		this.isDeleted = false;
		return deleted;
	}

	

	@Override
	public boolean search(T key) {

		Node<T> current = root;
		while (current != null) {
			if (key.compareTo(current.getValue()) < 0) {
				current = (Node<T>) current.getLeftChild();
			} else if (key.compareTo(current.getValue()) > 0) {
				current = (Node<T>) current.getRightChild();
			} else
				return true;
		}
		return false;
	}

	@Override
	public int height() {

		if (getTree() == null) {
			return 0;
		}
		return ((Node<T>) getTree()).getHeight();
	}

	@Override
	public INode<T> getTree() {
		return root;
	}

	private void fixHeight(Node<T> node) {

		int val1 = 0, val2 = 0;
		if (node.getLeftChild() != null) {
			val1 = ((Node<T>) node.getLeftChild()).getHeight();
		}
		if (node.getRightChild() != null) {
			val2 = ((Node<T>) node.getRightChild()).getHeight();
		}
		node.setHeight(Math.max(val1, val2) + 1);
	}

	private Node<T> minimumNode(Node<T> minNode) {
		Node<T> temp = minNode;
		while (temp.getLeftChild() != null) {
			temp = (Node<T>) temp.getLeftChild();
		}
		return temp;
	}

}