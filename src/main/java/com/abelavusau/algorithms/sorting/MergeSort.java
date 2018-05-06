package com.abelavusau.algorithms.sorting;

import java.util.Random;

public class MergeSort {
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
		int n = a.length;

		if (n < 2) {
			return;
		}

		int mid = n / 2;
		int[] left = new int[mid];
		int[] right = new int[n - mid];

		for (int i = 0; i < mid; i++) {
			left[i] = a[i];
		}

		for (int i = mid; i < n; i++) {
			right[i - mid] = a[i];
		}

		sort(left);
		sort(right);

		merge(left, right, a);
		out(a);
	}

	private static void merge(int[] left, int[] right, int[] a) {
		int i = 0, j = 0, k = 0;
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				a[k] = left[i];
				i++;
			} else {
				a[k] = right[j];
				j++;
			}

			k++;
		}

		while (i < left.length) {
			a[k++] = left[i++];
		}

		while (j < right.length) {
			a[k++] = right[j++];
		}
	}
}
