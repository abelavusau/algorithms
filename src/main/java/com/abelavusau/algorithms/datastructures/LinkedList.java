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

	public void reverse() {
		Node<T> prev = null;
		Node<T> current = head;

		while (current != null) {
			Node<T> next = current.next; // Store the next node
			current.next = prev; // Reverse the current node's pointer
			prev = current;      // Move prev and current one step forward
			current = next;
		}

		head = prev; // Update the head to the new front of the list
	}

	public static Node<Integer> mergeLists(Node<Integer> list1, Node<Integer> list2) {
		Node<Integer> head = null;
		Node<Integer> current = null;

		while (list1 != null && list2 != null) {
			if (head == null) {
				current = new Node<>(null);
				head = current;
			}
			if (list1.value < list2.value) {
				current.value = list1.value;
				list1 = list1.next;
			} else {
				current.value = list2.value;
				list2 = list2.next;
			}

			current.next = new Node<>(null);
			current = current.next;
		}

		if (list1 != null) {
			if (head == null) {
				head = list1;
			} else {
				current.value = list1.value;
				current.next = list1.next;
			}
		}

		if (list2 != null) {
			if (head == null) {
				head = list2;
			} else {
				current.value = list2.value;
				current.next = list2.next;
			}
		}

		return head;
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

		LinkedList<Integer> linkedList1 = new LinkedList<>();
		linkedList1.add(1);
		linkedList1.add(3);
		linkedList1.add(5);
		linkedList1.add(7);
		linkedList1.add(9);

		LinkedList<Integer> linkedList2 = new LinkedList<>();
		linkedList2.add(2);
		linkedList2.add(4);
//		linkedList2.add(6);

		LinkedList.Node<Integer> headOfMerged = mergeLists(null, null);

		for (Node<Integer> cursor = headOfMerged; cursor != null; cursor = cursor.next) {
			System.out.println(cursor.value);
		}

//		System.out.println("Reverse List: " + linkedList);
//		linkedList.reverse();
//		System.out.println(linkedList);
//
//		System.out.println(linkedList.get(7));
//		linkedList.remove(7);
//		linkedList.remove(0);
//		linkedList.remove(9);
//		linkedList.add(7, 6);
//		linkedList.add(0, 0);
//		linkedList.add(10, 5);
//		System.out.println(linkedList);
	}
}
