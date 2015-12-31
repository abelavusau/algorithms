package com.abelavusau.multithreading;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WaitOnDuplicateSetTest {

	private WaitOnDuplicateSet<Integer> testee;
	private HashSet<Integer> inner;

	@Before
	public void setUp() throws Exception {
		inner = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		testee = new WaitOnDuplicateSet<Integer>(inner);
	}

	@After
	public void tearDown() throws Exception {
		testee.clear();
		inner = null;
		testee = null;
	}

	@Test
	public void testAdd() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				testee.add(10);
			}
		});
		
		Thread.sleep(5000);
		
		executor.execute(new Runnable() {
			@Override
			public void run() {
				testee.remove(10);
			}
		});

		try {
			executor.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
