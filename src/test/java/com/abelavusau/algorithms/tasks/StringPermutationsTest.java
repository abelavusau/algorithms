package com.abelavusau.algorithms.tasks;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abelavusau.algorithms.tasks.StringPermutations;

public class StringPermutationsTest {
	private StringPermutations underTest;

	@Before
	public void setUp() throws Exception {
		underTest = new StringPermutations("abcd");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testPermute() {
		underTest.permute();
		assertEquals(24, underTest.getCount());
	}
}
