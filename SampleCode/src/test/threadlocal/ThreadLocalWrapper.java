package test.threadlocal;

public class ThreadLocalWrapper {

	private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 999;
		};	
	};
	
	public Integer getValue() {
		return threadLocal.get();
	}
	
	public void setValue(Integer N) {
		threadLocal.set(N);;
	}
}
