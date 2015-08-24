package com.abelavusau.algoritms.datastructures;

import java.util.Random;

public class BinarySearchTree<T> {
	private Node<T> root;

	@SuppressWarnings("unused")
	private static class Node<T> {
		int key;
		T value;
		Node<T> left;
		Node<T> right;

		public Node(int key, T value) {
			this.key = key;
			this.value = value;
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
	}

	private void addNode(int key, T value) {
		this.root = putNode(this.root, key, value);
	}

	private Node<T> putNode(Node<T> node, int key, T value) {
		if (node == null) {
			return new Node<T>(key, value);
		}

		if (key < node.key) {
			node.left = putNode(node.left, key, value);
		} else if (key > node.key) {
			node.right = putNode(node.right, key, value);
		} else {
			node.value = value;
		}

		return node;
	}

	private Node<T> getNode(int key) {
		Node<T> currentNode = this.root;

		while (currentNode != null) {
			if (key < currentNode.key) {
				currentNode = currentNode.left;
			} else if (key > currentNode.key) {
				currentNode = currentNode.right;
			} else {
				return currentNode;
			}
		}

		return null;
	}

	private Node<T> getMin(Node<T> node) {
		if (node.left == null) {
			return node;
		}

		return getMin(node.left);
	}

	private Node<T> getMax(Node<T> node) {
		if (node.right == null) {
			return node;
		}
			
		return getMax(node.right);
	}
	
	private void deleteNode(int key) {
		this.root = doDelete(this.root, key);
	}
	
	private Node<T> doDelete(Node<T> node, int key) {
		if (key < node.key) {
			return doDelete(node.left, key);
		} else if (key > node.key) {
			return doDelete(node.right, key);
		} else {
			if (node.left == null && node.right == null) {
				return null;
			} else if (node.left != null && node.right == null) {
				return node.left;
			} else if (node.right != null && node.left == null) {
				return node.right;
			} else if (node.left != null && node.right != null) {
				Node<T> min = getMin(node.right);
				node.key = min.key;
				node.value = min.value;
				min = min.right;
			}
			
			return node;
		}
	}
	
	private void infixTraverse(Node<T> node) {
		if (node != null) {
			infixTraverse(node.left);
			System.out.printf("%d ", node.key);
			infixTraverse(node.right);
		}
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
		
		for (int i = 0; i < 15; i++) {
			binaryTree.addNode(rand.nextInt(100), i);
		}
		
		System.out.printf("Max=%d\n", binaryTree.getMax(binaryTree.root).key);
		System.out.printf("Min=%d\n", binaryTree.getMin(binaryTree.root).key);
		System.out.println("Sotred tree: ");
		binaryTree.infixTraverse(binaryTree.root);
	}
}
