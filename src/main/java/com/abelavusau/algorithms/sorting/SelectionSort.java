package com.abelavusau.algorithms.sorting;

import java.util.Random;

public class SelectionSort {
	public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[10];

        for (int i = 0; i < 10; i++) {
            array[i] = rand.nextInt(20);
        }

        out(array);
        sort(array);
        out(array);
    }

    private static void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = i;
			
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			
			swap(array, min, i);
		}
	}
    
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

	private static void out(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
}
