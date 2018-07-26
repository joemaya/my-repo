package com.test;

public class ThreadLocalWrapper {
	
	private ThreadLocal<TestModel> threadLocal = new ThreadLocal<TestModel>() {
		protected TestModel initialValue() {
			return new TestModel();};
	};

	public ThreadLocal<TestModel> getThreadLocal() {
		return threadLocal;
	}

	public void setThreadLocal(ThreadLocal<TestModel> threadLocal) {
		this.threadLocal = threadLocal;
	}

	
}
