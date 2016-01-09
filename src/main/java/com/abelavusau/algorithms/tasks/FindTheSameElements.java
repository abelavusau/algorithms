package com.abelavusau.algorithms.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindTheSameElements {
	public static void main(String[] args) {
		Set<Integer> s1 = new HashSet<Integer>(Arrays.asList(3, 2, 4, 10, 11, 22, 30));
		Set<Integer> s2 = new HashSet<Integer>(Arrays.asList(1, 2, 11, 0, 30, 4));
		Set<Integer> s3 = new HashSet<Integer>(Arrays.asList(1, 2, 4, 22, 30));
		Set<Integer> s4 = new HashSet<Integer>(Arrays.asList(5, 7, 2, 30));
		Set<Integer> s5 = new HashSet<Integer>(Arrays.asList(2, 30));
		
		s1.retainAll(s2);
		s2.retainAll(s3);
		s3.retainAll(s4);
		s4.retainAll(s5);
		s1.retainAll(s4);
		
		System.out.println(s1);
	}
}
