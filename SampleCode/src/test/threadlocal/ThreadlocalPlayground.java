package test.threadlocal;

public class ThreadlocalPlayground extends Thread{

	private TestPojo pojo;
	
	public ThreadlocalPlayground(TestPojo pojo) {
		this.setPojo(pojo);
	}
	
	@Override
	public void run() { 
		System.out.println("Thread " + Thread.currentThread().getName() + " value : " + pojo.getThreadLocalWrapper().getValue());
		pojo.getThreadLocalWrapper().setValue(10);
		System.out.println("Thread " + Thread.currentThread().getName() + " value : " + pojo.getThreadLocalWrapper().getValue());
	}

	public static void main(String[] args) {
		TestPojo pojo = new TestPojo();
		pojo.setName("Pankaj");
//		pojo.setThreadLocalWrapper(threadLocalWrapper);
	}

	public TestPojo getPojo() {
		return pojo;
	}

	public void setPojo(TestPojo pojo) {
		this.pojo = pojo;
	}
}
