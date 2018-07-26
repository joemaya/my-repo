package test.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Program which demonstrates the perils of using if statements instead of while
 * in producer/consumer loops. If you run it, you should see "Under capacity"
 * and "Over capacity" warnings. Change the if to a while and they will
 * disappear.
 */
public class ProducerConsumer {

	// how many maximum items should the list store
	private static final int CAPACITY = 10;
	private static final int NUM_PUTTERS = 10;
	private static final int NUM_GETTERS = 10;

	private List<String> items = new ArrayList<String>(CAPACITY);
	private ReentrantLock lock = new ReentrantLock();
	private Condition hasSpace = lock.newCondition();
	private Condition hasItems = lock.newCondition();

	// consumer method
	public String get() {
		lock.lock();
		try {
			// see if the list is empty, !!DANGER!! should be a while loop
			if (items.isEmpty()) {
				try {
					// wait for the list to have items
					hasItems.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return null;
				}
			}
			String item = null;
			try {
				// remove the item from the front of the list
				item = items.remove(0);
				// signal a waiting producer (if any) that the list has space
				hasSpace.signal();
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Under capacity: " + items.size());
			}
			return item;
		} finally {
			lock.unlock();
		}
	}

	// producer method
	public void put(String item) {
		lock.lock();
		try {
			// see if the list is full, !!DANGER!! this should be a while loop
			if (items.size() >= CAPACITY) {
				try {
					// wait for the list to have space
					hasSpace.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// add an item to the end of the list
			items.add(item);
			int size = items.size();
			if (size > CAPACITY) {
				System.err.println("Over capacity: " + size);
			}
			// signal a waiting consumer (if any) that the list has an item
			hasItems.signal();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		new ProducerConsumer().doMain(args);
	}

	private void doMain(String[] args) {
		for (int i = 0; i < NUM_GETTERS; i++) {
			new Thread(new Getter()).start();
		}
		for (int i = 0; i < NUM_PUTTERS; i++) {
			new Thread(new Putter()).start();
		}
	}

	private class Getter implements Runnable {
		public void run() {
			while (true) {
				get();
			}
		}
	}

	private class Putter implements Runnable {
		public void run() {
			while (true) {
				put("foo");
			}
		}
	}
}
