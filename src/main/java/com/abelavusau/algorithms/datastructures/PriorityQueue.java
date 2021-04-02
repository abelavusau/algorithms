package com.abelavusau.algorithms.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> {
    private final List<E> heap;

    public PriorityQueue(E[] elements) {
        this(new ArrayList<E>(Arrays.asList(elements)));
    }

    public PriorityQueue(List<E> elements) {
        heap = elements;
        buildHeap();
    }

    public PriorityQueue(int size) {
        heap = new ArrayList<>(size);
    }

    public PriorityQueue() {
        this(1);
    }

    public void add(E e) {
        heap.add(e);
        swim(heap.size() - 1);
    }

    public E poll() {
        E root = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        sink(0);
        return root;
    }

    public E peek() {
        return heap.get(0);
    }

    private void buildHeap() {
        int heapSize = heap.size();

        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            sink(i);
        }
    }

    private void sink(int k) {
        int left = k * 2 + 1;
        int right = k * 2 + 2;
        int largest = k;

        if (left < heap.size()) {
            E leftChild = heap.get(left);
            E node = heap.get(largest);
            if (lessThan(node, leftChild)) {
                largest = left;
            }
        }

        if (right < heap.size()) {
            E rightChild = heap.get(right);
            E node = heap.get(largest);
            if (lessThan(node, rightChild)) {
                largest = right;
            }
        }

        if (largest != k) {
            swap(largest, k);
            sink(largest);
        }
    }

    private void swim(int k) {
        while (k > 0) {
            int p = (k - 1) / 2;
            E parent = heap.get(p);
            E child = heap.get(k);

            if (lessThan(parent, child)) {
                swap(p, k);
            }

            k = p;
        }
    }

    private boolean lessThan(E e1, E e2) {
        return e1.compareTo(e2) < 0;
    }

    private void swap(int i, int j) {
        E tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    private void print() {
        heap.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        PriorityQueue<Integer> p = new PriorityQueue<>(array);
        p.print();
        p.add(22);
        System.out.println("=======================");
        p.print();
        System.out.println("=======================");
        System.out.println("Poll: " + p.poll());
        System.out.println("=======================");
        p.print();

    }
}
