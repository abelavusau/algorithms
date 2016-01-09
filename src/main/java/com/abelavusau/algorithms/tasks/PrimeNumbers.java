package com.abelavusau.algorithms.tasks;

public class PrimeNumbers {
	public static void main(String[] args) {
		getPrimeNumbers(30);
	}
	
	public static void getPrimeNumbers(int n) {
		boolean[] array = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			if (i == 0 || i == 1) {
				array[i] = true;
			}
			
			if (!array[i]) {
				for (int k = 2 * i; k < n; k += i) {
					array[k] = true;
				}
			}
		}
		
		
		for (int i = 0; i < n; i++) {
			if (!array[i]) {
				System.out.print(i + " ");
			}
		}
	}
}
