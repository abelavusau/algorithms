package com.abelavusau.algoritms.tasks;

public class Fibonacci {
	public static void main(String[] args) {
		int n = 11;
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }
	}
	
	public static int fib(int n) {
		if (n == 0) {
			return 0; 
		} else if (n == 1 || n == 2) {
			return 1;
		}
		
		return fib(n - 2) + fib(n - 1);
	}
}
