package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.test.HelloWorldService; 
 
public class Hello extends Thread{

	private static ApplicationContext context;
	
	@Override
	public void run() {
		ThreadLocalWrapper wrapper = (ThreadLocalWrapper) context
				.getBean("tlWrapper");
		TestModel model = wrapper.getThreadLocal().get();
		
		model.setName(Thread.currentThread().getName());
		model.setAge(Thread.currentThread().getId()+"");
		model.setSalary(Thread.currentThread().getId()+"");
		
		
		HelloWorldServiceHandler handler = (HelloWorldServiceHandler) context
				.getBean("serviceHandler");
		handler.process();
		
		
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
 
		// loading the definitions from the given XML file
		context= new ClassPathXmlApplicationContext(
				"applicationContext.xml");
 
		HelloWorldService service = (HelloWorldService) context
				.getBean("helloWorldService");
		String message = service.sayHello();
		System.out.println(message);
 
		//set a new name
		service.setName("Spring");
		message = service.sayHello();
		System.out.println(message);
		
		Hello thread1 = new Hello();
		Hello thread2 = new Hello();
		
		thread1.start();
		thread2.start();
		
	}
}