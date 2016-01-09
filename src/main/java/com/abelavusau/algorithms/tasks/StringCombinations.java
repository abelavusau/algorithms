package com.abelavusau.algorithms.tasks;

public class StringCombinations {
	private final StringBuilder out;
	private final String in;
	private int count = 0;
	
	public StringCombinations(String str) {
		this.out = new StringBuilder();
		this.in = str;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void combine() {
		combine(0);
	}

	private void combine(int start) {
		for (int i = start; i < in.length(); i++) {
			out.append(in.charAt(i));
			count++;
			System.out.println(count + ": " + out);
			
			if (i < in.length()) {
				combine(i + 1);
			}
			
			out.setLength(out.length() - 1);
		}
	}
}
