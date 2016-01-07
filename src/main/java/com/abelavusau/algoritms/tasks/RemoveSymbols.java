package com.abelavusau.algoritms.tasks;

public class RemoveSymbols {
    public static String removeSymbols(String str, String removeSequence) {
    	char[] s = str.toCharArray();
    	char[] r = removeSequence.toCharArray();
    	boolean[] flags = new boolean[128];
    	int src, dst = 0;
    
    	for (src = 0; src < r.length; src++) {
    	    flags[r[src]] = true;
    	}
    
    	for (src = 0; src < s.length; src++) {
    	    if (!flags[s[src]]) {
    	    	s[dst++] = s[src];
    	    }
    	}

		return new String(s, 0, dst);
    }
}
