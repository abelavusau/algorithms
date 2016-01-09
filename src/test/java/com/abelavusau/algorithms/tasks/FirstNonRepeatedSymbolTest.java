package com.abelavusau.algorithms.tasks;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abelavusau.algorithms.tasks.FirstNonRepeatedSymbol;

public class FirstNonRepeatedSymbolTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetFirstNonRepeatedSymbol() {
		assertEquals("v", FirstNonRepeatedSymbol.getFirstNonRepeatedSymbol("abvdabrefd"));
	}

}
