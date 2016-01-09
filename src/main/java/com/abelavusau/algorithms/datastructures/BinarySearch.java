package com.abelavusau.algorithms.datastructures;

public class BinarySearch {
	private static final int ELEMENT_NOT_FOUND = -1;

	public static void main(String[] args) {
		int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(binarySearch(array, 7));
	}

	public static int binarySearch(int[] array, int target) {
		return binarySearch(array, target, 0, array.length - 1);
	}

	private static int binarySearch(int[] array, int target, int left, int right) {
		int range = right - left;

		if (range == 0 && array[left] != target) {
			return ELEMENT_NOT_FOUND;
		}

		if (array[left] > array[right]) {
			throw new IllegalArgumentException("The array is not sorted. Please sort before search.");
		}

		int center = range / 2 + left;

		if (array[center] == target) {
			return center;
		} else if (array[center] < target) {
			return binarySearch(array, target, center + 1, right);
		} else if (array[center] > target) {
			return binarySearch(array, target, left, center - 1);
		}

		return ELEMENT_NOT_FOUND;
	}
}
