package com.abelavusau.algorithms.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PriorityQueue<E extends Comparable<E>> {
    private final Random r = new Random();
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

    public E sample() {
        int i = r.nextInt(heap.size());
        return heap.get(i);
    }

    public E delRandom() {
        int i = r.nextInt(heap.size());
        E e = heap.get(i);
        swap(i, heap.size() - 1);
        heap.remove(heap.size() - 1);
        fixInvariant(i);
        return e;
    }

    /**
     * Maintains heap's invariant.
     * Check nodes' children as well as its parent.
     * If heap's invariant is broken - sink or swim depending on the result of analysis.
     * @param k - a node
     */
    private void fixInvariant(int k) {
        int l = 2 * k + 1;
        int r = 2 * k + 2;
        int p = (k - 1) / 2;

        if (l < heap.size() && lessThan(heap.get(k), heap.get(l))) {
            sink(k);
        }

        if (r < heap.size() && lessThan(heap.get(k), heap.get(r))) {
            sink(k);
        }

        if (p >= 0 && lessThan(heap.get(p), heap.get(k))) {
            swim(k);
        }
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
        System.out.println("=======================");
        System.out.println("Delete random: " + p.delRandom());
        System.out.println("=======================");
        p.print();
    }
}
