package com.abelavusau.algoritms.sorting;

import java.util.Random;

public class QuickSortLomuto {
	public static void main(String[] args) {
		Random rand = new Random();
		int[] array = new int[10];

		for (int i = 0; i < 10; i++) {
			array[i] = rand.nextInt(20);
		}

		System.out.println("initial array:");
		out(array);
		sort(array, 0, array.length - 1);
		System.out.println("--------------------------------------------");
		System.out.println("ordered array:");
		out(array);
	}

	private static void sort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}

		int pIndex = left - 1;
		int pivot = right;

		out(array, pivot, left, right);

		for (int j = left; j < right; j++) {
			if (array[j] <= array[pivot]) {
				pIndex++;
				swap(array, pIndex , j);
			}
		}

		swap(array, pIndex + 1, pivot);

		sort(array, left, pIndex);
		sort(array, pIndex + 1, right);
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private static void out(int[] array, int pivotIndex, int left, int right) {
		System.out.println("--------------------------------------------");

		System.out.println("Left array: ");

		for (int i = 0; i < pivotIndex; i++) {
			System.out.print(array[i] + " ");
		}

		System.out.println("\nPivot index = " + pivotIndex);

		for (int i = 0; i < array.length; i++) {
			if (i == pivotIndex) {
				System.out.print("(" + array[i] + ") ");
			} else {
				System.out.print(array[i] + " ");
			}
		}

		System.out.print("\nRight array: ");

		for (int i = pivotIndex; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

		System.out.println();
	}

	private static void out(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

		System.out.println();
	}
}
