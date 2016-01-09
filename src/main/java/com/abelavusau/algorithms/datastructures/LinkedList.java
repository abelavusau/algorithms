package com.abelavusau.algorithms.datastructures;

public class LinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	private static class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> previous;
		
		public Node(T value) {
			this.value = value;
		}
	}
	
	private Node<T> getNode(int index) {
		Node<T> cursor;
		if (index < size >> 1) {
			cursor = head;
			
			for (int i = 0; i < index; i++) {
				cursor = cursor.next;
			}
		} else {
			cursor = tail;
			
			for (int i = size - 1; i > index; i--) {
				cursor = cursor.previous;
			}
		}
		
		return cursor;
	}
	
	public void add(T element) {
		if (head == null) {
			head = new Node<T>(element);
			head.value = element;
			tail = head;
		} else {
			Node<T> newElement = new Node<T>(element);
			newElement.previous = tail;
			tail.next = newElement;
			tail = tail.next;
		}
		
		size++;
	}
	
	public void add(T element, int position) {
		Node<T> node = getNode(position);
		Node<T> nodeToAdd = new Node<T>(element);
		
		if (node == head) {
			nodeToAdd.next = head;
			head.previous = nodeToAdd;
			head = nodeToAdd;
		} else {
			nodeToAdd.next = node;
			nodeToAdd.previous = node.previous;
			nodeToAdd.previous.next = nodeToAdd;
			node.previous = nodeToAdd;
		}
		
		size++;
	}
	
	public T get(int index) {
		return getNode(index).value;
	}
	
	public T remove(int index) {
		Node<T> remove = getNode(index);
		T value = remove.value;
		
		if (remove == head) {
			head = head.next;
			head.previous = null;
			remove = null;
		} else if (remove == tail) {
			tail = tail.previous;
			tail.next = null;
			remove = null;
		} else {
			Node<T> previous = remove.previous;
			Node<T> next = remove.next;
			
			previous.next = next;
			next.previous = previous;
			
			remove = null;
		}
		
		size--;
		
		return value;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for (Node<T> cursor = head; cursor != null; cursor = cursor.next) {
			sb.append(cursor.value);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> linkedList = new LinkedList<>();
		
		for (int i = 0; i < 10; i++) {
			linkedList.add(i);
		}
		
		System.out.println(linkedList.get(7));
		linkedList.remove(7);
		linkedList.remove(0);
		linkedList.remove(9);
		linkedList.add(7, 6);
		linkedList.add(0, 0);
		linkedList.add(10, 5);
		System.out.println(linkedList);
	}
}
