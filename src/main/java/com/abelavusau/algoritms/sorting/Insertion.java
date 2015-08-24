package com.abelavusau.algoritms.sorting;

import java.util.Random;

/**
 * Insertion sort
 * 
 * Time complexity: O(n^2)
 * 
 * @author abelavusau
 *
 */
public class Insertion {
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
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
        	if (a[j - 1] > a[j]) {
        	    swap(a, j - 1, j);
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
