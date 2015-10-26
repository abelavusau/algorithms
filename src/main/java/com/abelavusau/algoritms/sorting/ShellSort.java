package com.abelavusau.algoritms.sorting;

import java.util.Random;

public class ShellSort {
	public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[12];

        for (int i = 0; i < 12; i++) {
            array[i] = rand.nextInt(100);
        }

        out(array);
        sort(array);
        out(array);
    }

    private static void out(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
    
    private static void sort(int[] a) {
    	for (int gap = a.length / 2; gap > 0; gap /= 2) {
    		for (int i = gap; i < a.length; i++) {
    			int tmp = a[i];
    			int j;
    			
    			for (j = i; j >= gap && tmp < a[j - gap]; j -= gap) {
					a[j] = a[j - gap];
    			}
    			
    			a[j] = tmp;
    		}
    	}
    }
}
