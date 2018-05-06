package com.abelavusau.algorithms.coursera.algorithms.p1.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public Deque() {
    }

    private static class Node<Item> {
        private Item value;
        private Node<Item> next;
        private Node<Item> previous;

        public Node(Item value) {
            this.value = value;
        }
    }

    private static class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public void addFirst(Item element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> first = new Node<Item>(element);
        if (isEmpty()) {
            head = first;
            tail = first;
        } else {
            first.next = head;
            head.previous = first;
            head = first;
        }

        size++;
    }

    public void addLast(Item element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> first = new Node<Item>(element);
        if (isEmpty()) {
            head = first;
            tail = first;
        } else {
            Node<Item> last = new Node<Item>(element);
            last.previous = tail;
            tail.next = last;
            tail = last;
        }

        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<Item> first = head;
        head = head.next;

        if (head != null) {
            head.previous = null;
        }

        size--;
        return first.value;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<Item> last = tail;
        tail = tail.previous;

        if (tail != null) {
            tail.next = null;
        }

        size--;
        return last.value;
    }


    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(head);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.size();
        deque.size();
        deque.size();
        deque.isEmpty();
        deque.addLast(4);
        deque.size();
        deque.isEmpty();
        deque.addFirst(7);
        deque.removeFirst();
        deque.removeLast();

    }
}
