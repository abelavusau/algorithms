package com.abelavusau.algoritms.sorting;

import java.util.Random;

/**
 * Quick sort
 * @author abelavusau
 *
 * Time complexity:
 * The best time: O(n*log(n))
 * The worst time: O(n^2)
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
		int i = l;
		int j = r;
		int pivotIndex = l + (r - l) / 2;
		int pivot = array[pivotIndex];
		
		out(array, pivotIndex);
		
		while (i <= j) {
			// go to the element at the left part which is >= than pivot
			while (array[i] < pivot) {
				i++;
			}
			
			// go to the element at the right part which is <= than pivot
			while (array[j] > pivot) {
				j--;
			}
			
			// if the element at the left is bigger than the element at the right - exchange
			if (i <= j) {
				swap(array, i, j);
				i++;
				j--;
			}
		}
		
		if (l < j) {
			sort(array, l, j);
		}
		if (r > i) {
			sort(array, i, r);
		}
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
