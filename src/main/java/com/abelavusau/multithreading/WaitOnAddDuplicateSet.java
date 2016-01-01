package com.abelavusau.multithreading;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class WaitOnAddDuplicateSet<E> implements Set<E> {
	private Set<E> inner;
	private final Object lock = new Object();

	public WaitOnAddDuplicateSet(Set<E> collection) {
		this.inner = collection;
	}

	@Override
	public int size() {
		return this.inner.size();
	}

	@Override
	public boolean isEmpty() {
		return this.inner.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.inner.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return this.inner.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.inner.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.inner.toArray(a);
	}

	@Override
	public boolean add(E e) {
		boolean result = false;

		synchronized (lock) {
			while (this.inner.contains(e)) {
				try {
					lock.wait();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			result = this.inner.add(e);
		}

		return result;
	}

	@Override
	public boolean remove(Object o) {
		synchronized (lock) {
			boolean result = this.inner.remove(o);
			lock.notify();
			return result;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.inner.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		
		synchronized (lock) {
			for (E e : c) {
				while (this.inner.contains(e)) {
					try {
						lock.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
			
			result = this.inner.addAll(c);
		}

		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		synchronized (lock) {
			boolean result = this.inner.retainAll(c);
			lock.notifyAll();
			return result;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		synchronized (lock) {
			boolean result = this.inner.removeAll(c);
			lock.notifyAll();
			return result;
		}
	}

	@Override
	public void clear() {
		synchronized (lock) {
			this.inner.clear();
			lock.notifyAll();
		}
	}
}
