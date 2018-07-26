package test.threadlocal;

public class TestThreadLocal {

	public static void main(String[] args) {
		ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
			@Override
			protected String initialValue() {
				return new String("Pankaj");
			};
			
		};
		
		
		String val = threadLocal.get();
		threadLocal.set(val);
		System.out.println(val);
		TestThreadLocal1 localTest = new TestThreadLocal1();
		localTest.process();
	}
}
