package com.abelavusau.algorithms.sorting;

import java.util.Random;

/**
 * Quick sort
 *
 * @author abelavusau
 *         <p>
 *         Time complexity:
 *         The best time: O(n*log(n))
 *         The worst time: O(n^2)
 */
public class QuickSortHoare {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[10];

        for (int i = 0; i < 10; i++) {
            array[i] = rand.nextInt(20);
        }

        System.out.println("initial array:");
        out(array);
        sort(array, 0, array.length - 1);
        System.out.println("ordered array:");
        out(array);
    }

    private static void out(int[] array, int pivotIndex) {
        for (int i = 0; i < array.length; i++) {
            if (pivotIndex == i) {
                System.out.print("(" + array[i] + ") ");
            } else {
                System.out.print(array[i] + " ");
            }
        }

        System.out.println();
    }

    private static void out(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }

    public static void sort(int[] array, int l, int r) {
        if (l >= r) return;
        int pivotIndex = l + (r - l) / 2;
        int pivot = array[pivotIndex];
        int left = l;
        int right = r;

        out(array, pivotIndex);

        while (l <= r) {
            // go to the element at the left part which is >= than pivot
            while (array[l] < pivot) {
                l++;
            }

            // go to the element at the right part which is <= than pivot
            while (array[r] > pivot) {
                r--;
            }

            // if the element at the left is bigger than the element at the right - exchange
            if (l <= r) {
                swap(array, l, r);
                l++;
                r--;
            }
        }

        sort(array, left, l - 1);
        sort(array, l, right);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
