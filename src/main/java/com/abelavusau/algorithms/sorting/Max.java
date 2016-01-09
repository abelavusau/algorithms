package com.abelavusau.algorithms.sorting;

public class Max {
	public static void main(String[] args) {
		int[] a = new int[] { 6, 10, 1, 8, 5, 3, 9 };
		int min1, min2;

		if (a[0] < a[1]) {
			min1 = a[0];
			min2 = a[1];
		} else {
			min1 = a[1];
			min2 = a[0];
		}

		for (int i = 2; i < a.length; i++) {
			if (a[i] < min1) {
				min2 = min1;
				min1 = a[i];
			} else if (a[i] < min2) {
				min2 = a[i];
			}
		}

		System.out.println(min1);
		System.out.println(min2);
	}
}
