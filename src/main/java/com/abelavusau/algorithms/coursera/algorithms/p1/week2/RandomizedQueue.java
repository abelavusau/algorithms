package com.abelavusau.algorithms.coursera.algorithms.p1.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by abelavusau on 4/26/18.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final Object[] EMPTY_ARRAY = {};
    private Object[] source;
    private int size;

    public RandomizedQueue() {
        this.source = new Object[1];
    }

    private static class RandomizedQueueIterator<Item> implements Iterator<Item> {
        private int index = 0;
        private int size;
        private Object[] source;

        public RandomizedQueueIterator(Object[] source, int size) {
            this.source = source;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (index == size) {
                throw new NoSuchElementException();
            }

            return (Item) source[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == source.length) {
            int capacity = 2 * source.length;
            Object[] newSource = new Object[capacity];

            for (int i = 0; i < source.length; i++) {
                newSource[i] = source[i];
            }

            source = newSource;
        }

        source[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int remove = StdRandom.uniform(size);
        Item item = (Item) source[remove];
        Object[] newSource = size > 1 ? new Object[size - 1] : EMPTY_ARRAY;

        for (int i = 0, j = 0; i < size && size > 1; i++) {
            if (i != remove) {
                newSource[j++] = source[i];
            }
        }

        size--;
        source = newSource;
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int remove = StdRandom.uniform(size);
        return (Item) source[remove];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>(source, size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        int i = queue.sample();

        RandomizedQueueIterator<Integer> iterator = (RandomizedQueueIterator<Integer>) queue.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println(i);
    }
}
