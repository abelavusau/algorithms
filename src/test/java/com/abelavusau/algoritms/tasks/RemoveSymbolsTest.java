package com.abelavusau.algoritms.tasks;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoveSymbolsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testRemoveSymbols() {
	assertEquals(
		"The ntl cpcty s nthng ut the numer  uckets when the hsh tle s creted, cpcty s utmtclly ncresed when hsh tle get ull  element",
		RemoveSymbols.removeSymbols(
			"The initial capacity is nothing but the number of buckets when the hash table is created, capacity is automatically increased when hash table get full of element",
			"aoifb"));
    }

}
