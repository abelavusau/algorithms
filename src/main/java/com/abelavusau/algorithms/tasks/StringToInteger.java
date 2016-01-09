package com.abelavusau.algorithms.tasks;

public class StringToInteger {
	public static int stringToInteger(String str) {
		int n = 0;
		int i = 0;
		boolean isNegative = false;
		
		if (str.charAt(0) == '-') {
			isNegative = true;
			i = 1;
		}
		
		while (i < str.length()) {
			n *= 10;
			n += str.charAt(i++) - '0';
		}
		
		if (isNegative) {
			n = -n;
		}
		
		return n;
	}
}
