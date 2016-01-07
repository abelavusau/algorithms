package com.abelavusau.algoritms.tasks;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatedSymbol {
	public static String getFirstNonRepeatedSymbol(String str) {
		Map<Integer, Object> charMap = new HashMap<>();
		Object seenOnce = new Object(), seenMultiple = new Object(), seen;
		
		for (int i = 0; i < str.length();) {
			int codePoint = str.codePointAt(i);

			i += Character.charCount(codePoint);
			seen = charMap.get(codePoint);

			if (seen == null) {
				charMap.put(codePoint, seenOnce);
			} else if (seen == seenOnce) {
				charMap.put(codePoint, seenMultiple);
			}
		}

		for (int i = 0; i < str.length();) {
			int codePoint = str.codePointAt(i);

			i += Character.charCount(codePoint);

			if (charMap.get(codePoint) == seenOnce) {
				return new String(Character.toChars(codePoint));
			}
		}

		return null;
	}
}
