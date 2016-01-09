package com.abelavusau.algorithms.tasks;

public class RemoveSymbols {
    public static String removeSymbols(String str, String removeSequence) {
    	char[] s = str.toCharArray();
    	char[] r = removeSequence.toCharArray();
    	boolean[] flags = new boolean[128];
    
    	for (int i = 0; i < r.length; i++) {
    	    flags[r[i]] = true;
    	}
    
    	int j = 0;
    	
    	for (int i = 0; i < s.length; i++) {
    	    if (!flags[s[i]]) {
    	    	s[j++] = s[i];
    	    }
    	}

		return new String(s, 0, j);
    }
}
