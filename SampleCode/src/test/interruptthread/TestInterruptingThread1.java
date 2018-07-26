package test.interruptthread;


/**
 * 
 * In this example, after interrupting the thread, we are propagating it, so it will stop working. 
 * If we don't want to stop the thread, we can handle it where sleep() or wait() method is invoked. 
 * Let's first see the example where we are propagating the exception.
 * @author Pankaj
 *
 */
class TestInterruptingThread1 extends Thread {
	public void run() {
		try {
			System.out.println("thread started");
			Thread.sleep(5000);
			System.out.println("task");
		} catch (InterruptedException e) {
			//System.out.println(Thread.currentThread().getName() + " Thread interrupted..." + e);
			throw new RuntimeException(Thread.currentThread().getName() + " Thread interrupted..." + e);
		}
		System.out.println("Thread " + Thread.currentThread().getName() + " about to finish");
	}

	public static void main(String args[]) {
		TestInterruptingThread1 t1 = new TestInterruptingThread1();
		t1.start();
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " about to interrupt");
			t1.interrupt();
		} catch (Exception e) {
			System.out.println("Exception handled " + e);
		}

	}
}
