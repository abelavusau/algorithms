package com.abelavusau.algoritms.sorting;

import java.util.Random;

public class Pyramid {
    private int heapSize = 10;

    public static void main(String[] args) {
        Pyramid pyramid = new Pyramid();

        int[] array = new int[10];
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            array[i] = rand.nextInt(10);
        }

        pyramid.sort(array);
        
        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i]);
        }
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void buildMaxHeap(int[] array) {
        heapSize = array.length;

        for (int i = array.length / 2; i >= 0; i--) {
            maxHeapify(array, i);
        }
    }

    private void maxHeapify(int[] array, int i) {
        int left = left(i);
        int right = right(i);
        int largest;

        if (left < heapSize && array[left] > array[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = array[largest];
            array[largest] = array[i];
            array[i] = temp;

            maxHeapify(array, largest);
        }
    }

    private void sort(int[] array) {
        buildMaxHeap(array);

        while (heapSize > 1) {
            int temp = array[0];
            array[0] = array[heapSize - 1];
            array[heapSize - 1] = temp;
            heapSize--;
            maxHeapify(array, 0);
        }
    }
}
