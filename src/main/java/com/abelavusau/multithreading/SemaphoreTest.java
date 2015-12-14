package com.abelavusau.multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	Semaphore s = new Semaphore(1);
	private int count;
	
	public static void main(String[] args) {
		SemaphoreTest st = new SemaphoreTest();
		
		Runnable action = new Runnable() {
			@Override
			public void run() {
				st.calculate();
				System.out.println(Thread.currentThread().getName());
			}
		};
		
		Thread t1 = new Thread(action);
		Thread t2 = new Thread(action);
		
		t1.start();
		t2.start();
	}
	
	private void calculate() {
		try {
			s.acquire();
			count++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			s.release();
		}
	}
}
