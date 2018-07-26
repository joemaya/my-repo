package test.threadlocal;

public class TestThreadLocal1 {

	ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	public void process () {
		
		String value = (String)threadLocal.get();
		System.out.println(value);
	}
}
