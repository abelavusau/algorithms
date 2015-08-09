package com.abelavusau.algoritms.sorting;

import java.util.Random;

public class Quick {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[10];

        for (int i = 0; i < 10; i++) {
            array[i] = rand.nextInt(20);
        }
        
        out(array);
        sort(array, 0, array.length - 1);
        out(array);
    }
    
    private static void out(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
    
    public static void sort(int[] array, int l, int r) {
        int i = l;
        int j = r;
        int x = array[l + (r - l) / 2];
        while (i <= j) {
            while (array[i] < x) {
                i++;
            }
            while (array[j] > x) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (j > l) {
            sort(array, l, j);
        }
        if (i < r) {
            sort(array, i, r);
        }
    }
}
