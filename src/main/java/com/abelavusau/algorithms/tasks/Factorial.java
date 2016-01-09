package com.abelavusau.algorithms.tasks;

public class Factorial {
	public static void main(String[] args) {
		System.out.println(factorial(5));
	}
	
	public static int factorial(int n) {
		if (n > 1) {
			return factorial(n - 1) * n;
		} else {
			return 1;
		}
	}
}
