package com.test;

public class HelloWorldServiceHandler {

	private ThreadLocalWrapper wrapper;

	public ThreadLocalWrapper getWrapper() {
		return wrapper;
	}

	public void setWrapper(ThreadLocalWrapper wrapper) {
		this.wrapper = wrapper;
	}
	
	public void process() {
		TestModel model = wrapper.getThreadLocal().get();
		System.out.println(model.getName());
	}
	
}
