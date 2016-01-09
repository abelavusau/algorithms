package com.abelavusau.algorithms.tasks;

public class IntegerToString {
	public static String integerToString(int n) {
		StringBuilder temp = new StringBuilder();
		boolean isNegative = false;
		
		if (n < 0) {
			n = -n;
			isNegative = true;
		}
		
		while (n > 0) {
			char c = (char) ((n % 10) + '0');
			temp.append(c);
			n /= 10;
		}
		
		if (isNegative) {
			temp.append("-");
		}

		return temp.reverse().toString();
	}
}
