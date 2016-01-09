package com.abelavusau.algorithms.tasks;

import org.junit.Assert;
import org.junit.Test;

import com.abelavusau.algorithms.tasks.StringToInteger;

public class StringToIntegerTest {
	@Test
	public void testStringToInteger() {
		Assert.assertEquals(-2545, StringToInteger.stringToInteger("-2545"));
	}
}
