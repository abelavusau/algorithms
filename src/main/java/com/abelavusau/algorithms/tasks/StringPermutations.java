package com.abelavusau.algorithms.tasks;

public class StringPermutations {
	private final boolean[] used;
	private final StringBuilder out = new StringBuilder();
	private final String in;
	private int count;
	
	public StringPermutations(String str) {
		in = str;
		used = new boolean[in.length()];
	}
	
	public int getCount() {
		return count;
	}
	
	public void permute() {
		if (out.length() == in.length()) {
			count++;
			System.out.println(count + ": " + out);
			return;
		}
		
		for (int i = 0; i < in.length(); i++) {
			if (used[i]) {
				continue;
			}
			
			out.append(in.charAt(i));
			used[i] = true;
			
			permute();
			
			used[i] = false;
			out.setLength(out.length() - 1);
		}
	}
}
