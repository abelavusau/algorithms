package com.abelavusau.algorithms.tasks;

import java.util.LinkedHashSet;
import java.util.Set;

public class RepeatableWords {
	public static void main(String[] args) throws java.lang.Exception {
		String str = "мама мыла раму мыла ли раму мама ";
		int start = 0;
		int end = 0;
		
		Set<String> result = new LinkedHashSet<>();
		
		while (true) {
			end = str.indexOf(" ", start);
			
			if (end == -1) {
				break;
			}
				
			String s = str.substring(start, end);
			System.out.println(start + " " + end);
			start = end + 1;
			result.add(s);
			System.out.println(s);

		}
		
		String clean = "";
		
		for (String s : result) {
			clean += s + " ";
		}

		System.out.println(clean.trim());
	}
}
