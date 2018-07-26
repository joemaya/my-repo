package test.threadlocal;

public class TestPojo {
	
	private ThreadLocalWrapper threadLocalWrapper;
	private String name;
	
	public ThreadLocalWrapper getThreadLocalWrapper() {
		return threadLocalWrapper;
	}
	public void setThreadLocalWrapper(ThreadLocalWrapper threadLocalWrapper) {
		this.threadLocalWrapper = threadLocalWrapper;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
