package com.abelavusau.algorithms.sorting;

import java.util.Random;

public class BubbleSort {
    
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[10];

        for (int i = 0; i < 10; i++) {
            array[i] = rand.nextInt(20);
        }

        sort(array);
    }

    private static void out(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
    
    private static void sort(int[] a) {
        out(a);
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    out(a);
                }
            }
        }
    }
    
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
