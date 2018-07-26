package test.interruptthread;

/**
 * The isInterrupted() method returns the interrupted flag either true or false.
 * The static interrupted() method returns the interrupted flag after that it
 * sets the flag to false if it is true.
 * 
 * @author Pankaj
 *
 */
public class TestInterruptingThread4 extends Thread {

	public void run() {
		for (int i = 1; i <= 2; i++) {
			if (Thread.interrupted()) {
				System.out.println("code for interrupted thread");
			} else {
				System.out.println("code for normal thread");
			}

		} // end of for loop
	}

	public static void main(String args[]) {

		TestInterruptingThread4 t1 = new TestInterruptingThread4();
		TestInterruptingThread4 t2 = new TestInterruptingThread4();

		t1.start();
		t1.interrupt();

		t2.start();

	}
}
