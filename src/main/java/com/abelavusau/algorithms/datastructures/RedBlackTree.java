package com.abelavusau.algorithms.datastructures;

public class RedBlackTree<T> {
	private Node<T> root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	@SuppressWarnings("unused")
	private static class Node<T> {
		int key;
		T value;
		Node<T> left;
		Node<T> right;
		Node<T> parent;
		boolean color = BLACK;
		
		public Node(int key, T value, Node<T> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Node<T> getLeft() {
			return left;
		}

		public void setLeft(Node<T> left) {
			this.left = left;
		}

		public Node<T> getRight() {
			return right;
		}

		public void setRight(Node<T> right) {
			this.right = right;
		}

		public Node<T> getParent() {
			return parent;
		}

		public Node<T> getGrandfather() {
			Node<T> granny = null;

			if (parent != null && parent.parent != null) {
				granny = parent.parent;
			}

			return granny;
		}

		public Node<T> getUncle() {
			Node<T> granny = getGrandfather();
			Node<T> uncle = null;

			if (granny != null) {
				if (granny.left == parent) {
					uncle = granny.right;
				} else if (granny.right == parent) {
					uncle = granny.left;
				}
			}

			return uncle;
		}
	}

	private void addNode(int key, T value) {
		this.root = putNode(this.root, key, value, null);
	}

	private Node<T> putNode(Node<T> node, int key, T value, Node<T> parent) {
		if (node == null) {
			return new Node<T>(key, value, parent);
		}

		if (key < node.key) {
			node.left = putNode(node.left, key, value, node);
		} else if (key > node.key) {
			node.right = putNode(node.right, key, value, node);
		} else {
			node.value = value;
			node.color = RED;
		}

		return node;
	}
	
	private void insertCase1(Node<T> node) {
		if (node.parent == null) {
			node.color = BLACK;
		} else {
			insertCase2(node);
		}
	}
	
	private void insertCase2(Node<T> node) {
		// current node color is red.
		if (node.parent.color == BLACK) {
			return; /* Tree is still valid */
		} else {
			insertCase3(node);
		}
	}

	private void insertCase3(Node<T> node) {
		Node<T> uncle = node.getUncle();
		Node<T> grandfather = node.getGrandfather();

		if (uncle != null && uncle.color == RED && node.parent.color == RED) {
			node.parent.color = BLACK;
			uncle.color = BLACK;
			grandfather.color = RED;
			insertCase1(grandfather);
		} else {
			insertCase4(node);
		}
	}

	private void insertCase4(Node<T> node) {
		Node<T> grandfather = node.getGrandfather();

		if (node == node.parent.right && node.parent == grandfather.left) {
			rotateLeft(node.parent);
			node = node.left;
		} else if (node == node.parent.left && node.parent == grandfather.right) {
			rotateRight(node.parent);
			node = node.right;
		}
		
		insertCase5(node);
	}
	
	private void insertCase5(Node<T> node) {
		Node<T> grandfather = node.getGrandfather();

		node.parent.color = BLACK;
		grandfather.color = RED;
			
		if (node == node.parent.left && node.parent == grandfather.left) {
			rotateRight(grandfather);
		} else { /* (node == node.parent.right && node.parent == grandfather.right) */
			rotateLeft(grandfather);
		}
	}

	private void rotateLeft(Node<T> node) {
		Node<T> granny = node.getGrandfather();
		Node<T> savedParent = node.getGrandfather().left;
		Node<T> savedLeftChild = node.left;
		
		granny.left = node;
		node.left = savedParent;
		savedParent.right = savedLeftChild;
		
		// set backward links
		node.parent = granny;
		savedParent.parent = node;
		savedLeftChild.parent = savedParent;
	}

	private void rotateRight(Node<T> node) {
		Node<T> granny = node.getGrandfather();
		Node<T> savedParent = node.getGrandfather().right;
		Node<T> savedRightChild = node.right;
		
		granny.right = node;
		node.right = savedParent;
		savedParent.left = savedRightChild;
		
		// set backward links
		node.parent = granny;
		savedParent.parent = node;
		savedRightChild.parent = savedParent;
	}

	public static void main(String[] args) {
		
	}
}
