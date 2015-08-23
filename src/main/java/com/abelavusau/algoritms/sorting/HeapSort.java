package com.abelavusau.algoritms.sorting;

import java.util.Random;

public class HeapSort {
	private int heapSize = 10;

	public static void main(String[] args) {
		HeapSort pyramid = new HeapSort();

		int[] array = new int[10];
		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			array[i] = rand.nextInt(10);
		}

		pyramid.sort(array);

		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i]);
		}
	}

	/**
	 * Calculate left child index
	 * 
	 * @param i
	 *            - index of the current heap node
	 * @return index of the left child
	 */
	private int left(int i) {
		return 2 * i + 1;
	}

	/**
	 * Calculate right child index
	 * 
	 * @param i
	 *            - index of the current heap node
	 * @return index of the right child
	 */
	private int right(int i) {
		return 2 * i + 2;
	}

	/**
	 * Reorder sub-trees with indexes from 0 to a.length. The indexes from
	 * a.length / 2 + 1 to a.length are leaves.
	 * 
	 * @param array
	 *            - array to be heapified.
	 */
	private void buildHeap(int[] array) {
		heapSize = array.length;

		for (int i = array.length / 2; i >= 0; i--) {
			heapify(array, i);
		}
	}

	/**
	 * Reorder the sub-tree from node i in a way to a[parent] >= a[child]
	 * 
	 * @param array
	 *            - array to be heapified.
	 * @param i
	 *            - node we are starting to re-order from.
	 */
	private void heapify(int[] array, int i) {
		int left = left(i);
		int right = right(i);
		int largest = i;

		if (left < heapSize && array[left] > array[i]) {
			largest = left;
		}

		if (right < heapSize && array[right] > array[largest]) {
			largest = right;
		}

		if (largest != i) {
			swap(array, i, largest);
			heapify(array, largest);
		}
	}

	/**
	 * ���������� � ������� ����. ������� ����������� ����:
	 * 
	 * @see HeapSort#buildHeap(int[]) ������ ������������ ������� �������
	 *      ��������� � ����� ����. ��� ����� �������� ������� � ���������
	 *      ��������� � ������ �� ���� (��������� ������ ���� �� 1). ������ �
	 *      ����� ���� ��������� �������, ������� ������ ��� ��������� �
	 *      �������. ����� ��������������� ���� ���, ����� ����������� ��������
	 *      ������� ���� - a[parent]>=a[child]:
	 * @see #heapify(int[], int) ����� ����� � ����� �������� ������������ ��
	 *      ���������� ���������. ��������� �� ��� ���, ���� � ���� �� ���������
	 *      1 �������
	 * 
	 * @param a
	 *            ����������� ������
	 */
	private void sort(int[] array) {
		buildHeap(array);

		while (heapSize > 1) {
			swap(array, 0, heapSize - 1);
			heapSize--;
			heapify(array, 0); // put max element in the root of the heap
		}
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
